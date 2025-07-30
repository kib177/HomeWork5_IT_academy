package by.IT_academy.HomeWork.controller;

import by.IT_academy.HomeWork.service.ServiceFactory;
import by.IT_academy.HomeWork.service.api.IAuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/logout")
public class LogoutServlet extends HttpServlet {
    private IAuthService authService;

    public void init() {
        authService = ServiceFactory.getAuthService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        authService.logout(request.getSession(false));
        response.sendRedirect(request.getContextPath() + "/ui/");
    }
}

