package by.IT_academy.HomeWork.controller;

import by.IT_academy.HomeWork.core.ContextFactory;
import by.IT_academy.HomeWork.service.api.IRegistrationService;
import jakarta.servlet.ServletException;
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
    public void init() throws ServletException {
        super.init();
        userService = ContextFactory.getBean(IRegistrationService.class);
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
