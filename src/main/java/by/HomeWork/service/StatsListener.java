package by.HomeWork.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class StatsListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Инициализация сессии
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // При уничтожении сессии проверяем наличие пользователя
        if (se.getSession().getAttribute("user") != null) {
            decrementActiveUsers(se.getSession().getServletContext());
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        handleAttributeChange(event.getName(), event.getValue(), event.getSession().getServletContext());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        Object newValue = event.getSession().getAttribute(event.getName());
        handleAttributeChange(event.getName(), newValue, event.getSession().getServletContext());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())) {
            decrementActiveUsers(event.getSession().getServletContext());
        }
    }

    private void handleAttributeChange(String name, Object value, ServletContext context) {
        if ("user".equals(name) && value != null) {
            incrementActiveUsers(context);
        } else if ("user".equals(name) && value == null) {
            decrementActiveUsers(context);
        }
    }

    private void incrementActiveUsers(ServletContext context) {
        AtomicInteger activeUsers = getActiveUsersCounter(context);
        activeUsers.incrementAndGet();
    }

    private void decrementActiveUsers(ServletContext context) {
        AtomicInteger activeUsers = getActiveUsersCounter(context);
        activeUsers.decrementAndGet();
    }

    private AtomicInteger getActiveUsersCounter(ServletContext context) {
        AtomicInteger activeUsers = (AtomicInteger) context.getAttribute("activeUsers");
        if (activeUsers == null) {
            activeUsers = new AtomicInteger();
            context.setAttribute("activeUsers", activeUsers);
        }
        return activeUsers;
    }
}
