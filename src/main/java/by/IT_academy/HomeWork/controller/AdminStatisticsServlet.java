package by.IT_academy.HomeWork.controller;

import by.IT_academy.HomeWork.service.ServiceFactory;
import by.IT_academy.HomeWork.service.api.IStatisticsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/admin/statistics")
public class AdminStatisticsServlet extends HttpServlet {
    private IStatisticsService statisticsService;

    public void init() {
        statisticsService = ServiceFactory.getStatisticsService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int activeUsers = (int) req.getServletContext().getAttribute("activeUsersCount");

        req.setAttribute("stats", statisticsService.getStatistics(activeUsers));

        req.getRequestDispatcher("/WEB-INF/jsp/views/admin/statistics.jsp").forward(req, resp);
    }
}