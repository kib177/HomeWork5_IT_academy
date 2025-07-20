package by.HomeWork.filter;

import by.HomeWork.dto.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
/**
 * Фильтр безопасности для защиты административных ресурсов приложения.
 * Осуществляет проверку аутентификации пользователя и наличия роли `USER`
 * перед предоставлением доступа к защищенным путям.
 *
 * <p>Защищаемые пути (указаны в аннотации {@code @WebFilter}):
 * <ul>
 *   <li>{@code /api/message}</li>
 *   <li>{@code /ui/user/*}</li>
 * </ul>
 *
 * <p>Логика работы фильтра:
 * <ol>
 *   <li>Проверяет наличие активной сессии и объекта пользователя в атрибутах сессии</li>
 *   <li>Если пользователь аутентифицирован и имеет роль {@link User.Role#USER} - запрос передается дальше по цепочке</li>
 *   <li>Во всех остальных случаях перенаправляет на страницу входа ({@code /ui/signIn})</li>
 * </ol>
 */
@WebFilter(urlPatterns = {"/api/message", "/ui/user/*"})
public class UserSecurityFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/ui/signIn");
        }
    }

}
