package by.HomeWork.controller;

import by.HomeWork.storage.MessageRepository;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static by.HomeWork.database.Connection.getDataSource;

@WebServlet("/api/admin/statistics")
public class AdminStatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletContext context = req.getServletContext();
        UserRepository userRepository = new UserRepository(getDataSource());
        MessageRepository messageRepository = new MessageRepository(getDataSource(), userRepository);

        int totalUsers = userRepository.getAll().size();
        int totalMessages = messageRepository.getAll().size();
        int activeUsers = (int) context.getAttribute("activeUsersCount");

        req.setAttribute("stats", Map.of(
                "totalUsers", totalUsers,
                "totalMessages", totalMessages,
                "activeUsers", activeUsers
        ));

        req.getRequestDispatcher("/views/admin/statistics.jsp").forward(req, resp);
    }
}