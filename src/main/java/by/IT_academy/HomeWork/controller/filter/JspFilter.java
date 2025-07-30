package by.IT_academy.HomeWork.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
/**
 * Фильтр для обработки запросов к JSP-страницам в пути {@code /ui/*}.
 * <p>
 * Перенаправляет запросы на соответствующие JSP-файлы, расположенные в директории {@code /WEB-INF/jsp/views},
 * или делегирует обработку другим сервлетам для специфических маршрутов.
 *
 * <p><strong>Обрабатываемые маршруты:</strong>
 * <ul>
 *   <li>{@code /ui}, {@code /ui/} → {@code /WEB-INF/jsp/views/home.jsp}</li>
 *   <li>{@code /ui/signIn} → страница входа ({@code auth/signIn.jsp})</li>
 *   <li>{@code /ui/signUp} → страница регистрации ({@code auth/signUp.jsp})</li>
 *   <li>{@code /ui/user/messages} → страница сообщений пользователя</li>
 *   <li>{@code /ui/user/chats} → делегирует обработку сервлету {@code /api/message}</li>
 *   <li>{@code /ui/admin/statistics} → делегирует обработку сервлету {@code /api/admin/statistics}</li>
 * </ul>
 */
@WebFilter("/ui/*")
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith("/ui/")) {
            String jspPath = null;

            switch (path) {
                case "/ui/":
                case "/ui":
                    jspPath = "/WEB-INF/jsp/views/home.jsp";
                    break;
                case "/ui/signIn":
                    jspPath = "/WEB-INF/jsp/views/auth/signIn.jsp";
                    break;
                case "/ui/signUp":
                    jspPath = "/WEB-INF/jsp/views/auth/signUp.jsp";
                    break;
                case "/ui/user/messages":
                    jspPath = "/WEB-INF/jsp/views/user/message.jsp";
                    break;
                case "/ui/user/chats":
                    req.getRequestDispatcher("/api/message").forward(req, res);
                    return;
                case "/ui/admin/statistics":
                    req.getRequestDispatcher("/api/admin/statistics").forward(req, res);
                    return;
            }

            if (jspPath != null) {
                req.getRequestDispatcher(jspPath).forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}
