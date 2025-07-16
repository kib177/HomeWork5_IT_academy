package by.HomeWork.controller;

import by.HomeWork.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/logout")
public class LogoutServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        authService.logout(request.getSession(false));
        response.sendRedirect(request.getContextPath() + "/ui/");
    }
}

