package by.HomeWork.controller;

import by.HomeWork.service.StatisticsService;
import by.HomeWork.service.api.IStatisticsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/admin/statistics")
public class AdminStatisticsServlet extends HttpServlet {
    private final IStatisticsService statisticsService = new StatisticsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("stats", statisticsService.getStatistics(req.getServletContext()));

        req.getRequestDispatcher("/WEB-INF/jsp/views/admin/statistics.jsp").forward(req, resp);
    }
}