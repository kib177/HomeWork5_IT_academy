package by.HomeWork.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ActiveUsersListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        synchronized (context) {
            Integer count = (Integer) context.getAttribute("activeUsersCount");
            context.setAttribute("activeUsersCount", count == null ? 1 : count + 1);
        }
    }

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