package by.HomeWork.controller;

import by.HomeWork.dto.User;
import by.HomeWork.service.RegistrationService;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/api/user")
public class UserRegistrationServlet extends HttpServlet {
    private final RegistrationService userService = new RegistrationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        userService.registerUser(
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("FIO"),
                Date.valueOf(req.getParameter("date_birth"))
        );

        resp.sendRedirect(req.getContextPath() + "/ui/signIn");

    }
}
