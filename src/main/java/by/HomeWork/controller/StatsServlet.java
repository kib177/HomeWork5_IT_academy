package by.HomeWork.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        AtomicInteger activeUsers = getCounter("activeUsers");
        AtomicInteger totalMessages = getCounter("totalMessages");

        out.println("<h1>Статистика приложения</h1>");
        out.println("<p>Активных пользователей: " + activeUsers.get() + "</p>");
        out.println("<p>Отправлено сообщений: " + totalMessages.get() + "</p>");
    }

    private AtomicInteger getCounter(String name) {
        AtomicInteger counter = (AtomicInteger) getServletContext().getAttribute(name);
        return counter != null ? counter : new AtomicInteger();
    }
}
