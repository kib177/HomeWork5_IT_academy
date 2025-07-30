package by.IT_academy.HomeWork.controller;

import by.IT_academy.HomeWork.service.api.IRegistrationService;
import by.IT_academy.HomeWork.service.ServiceFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/api/user")
public class UserRegistrationServlet extends HttpServlet {
    private IRegistrationService userService;

    @Override
    public void init() {
        userService = ServiceFactory.getRegistrationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        userService.registerUser(
                req.getParameter("login"),
                BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()),
                req.getParameter("FIO"),
                Date.valueOf(req.getParameter("date_birth"))
        );

        resp.sendRedirect(req.getContextPath() + "/ui/signIn");

    }
}
