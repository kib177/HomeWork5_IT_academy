package by.HomeWork.controller.filter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
/**
 * Слушатель для отслеживания количества активных пользователей.
 * Реализует интерфейс {@link HttpSessionListener} для подсчёта сессий в контексте приложения.
 * <p>
 * При создании новой сессии увеличивает счётчик активных пользователей в контексте сервлета.
 * При уничтожении сессии уменьшает значение счётчика.
 * </p>
 * @WebListener Регистрирует класс как слушатель событий жизненного цикла сессий
 */
@WebListener
public class ActiveUsersListener implements HttpSessionListener {
    /**
     * Обрабатывает создание новой сессии.
     * <p>
     * Автоматически вызывается контейнером сервлетов при создании сессии.
     * Атомарно увеличивает счётчик активных пользователей в атрибутах контекста приложения.
     * </p>
     * - Если атрибут "activeUsersCount" отсутствует, инициализирует его значением 1
     * - Если атрибут существует, увеличивает его значение на 1
     */
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        synchronized (context) {
            Integer count = (Integer) context.getAttribute("activeUsersCount");
            context.setAttribute("activeUsersCount", count == null ? 1 : count + 1);
        }
    }
    /**
     * Обрабатывает уничтожение сессии.
     * <p>
     * Автоматически вызывается контейнером сервлетов при завершении сессии (истечении времени/инвалидации).
     * Атомарно уменьшает счётчик активных пользователей в атрибутах контекста приложения.
     * </p>
     * - Если атрибут "activeUsersCount" отсутствует или равен 0, изменения не производятся
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        synchronized (context) {
            Integer count = (Integer) context.getAttribute("activeUsersCount");
            if (count != null && count > 0) {
                context.setAttribute("activeUsersCount", count - 1);
            }
        }
    }
}