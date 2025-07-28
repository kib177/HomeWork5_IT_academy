package by.HomeWork.controller;

import by.HomeWork.service.AuthService;
import by.HomeWork.service.api.IAuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private final IAuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.getSession().setAttribute("user",
                authService.login(req.getParameter("login"), req.getParameter("password")));
        resp.sendRedirect(req.getContextPath() + "/ui/");


    }
}