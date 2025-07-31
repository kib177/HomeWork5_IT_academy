package by.IT_academy.HomeWork.controller.filter;

import by.IT_academy.HomeWork.core.dto.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
/**
 * Фильтр безопасности для защиты административных ресурсов приложения.
 * Осуществляет проверку аутентификации пользователя и наличия роли `ADMIN`
 * перед предоставлением доступа к защищенным путям.
 *
 * <p>Защищаемые пути (указаны в аннотации {@code @WebFilter}):
 * <ul>
 *   <li>{@code /api/admin/*}</li>
 *   <li>{@code /ui/admin/*}</li>
 * </ul>
 *
 * <p>Логика работы фильтра:
 * <ol>
 *   <li>Проверяет наличие активной сессии и объекта пользователя в атрибутах сессии</li>
 *   <li>Если пользователь аутентифицирован и имеет роль {@link User.Role#ADMIN} - запрос передается дальше по цепочке</li>
 *   <li>Во всех остальных случаях перенаправляет на страницу входа ({@code /ui/signIn})</li>
 * </ol>
 */
@WebFilter(urlPatterns = {"/api/admin/*", "/ui/admin/*"})
public class AdminSecurityFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole() == User.Role.ADMIN) {
                chain.doFilter(req, resp);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/ui/signIn");
    }
}