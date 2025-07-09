package by.HomeWork.controller;

import by.HomeWork.dto.User;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import static by.HomeWork.database.Connection.getDataSource;

@WebServlet("/api/user")
public class UserRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

            UserRepository userRepo = new UserRepository(getDataSource());

            String login = req.getParameter("login");

            if (userRepo.findByLogin(login).equals(login)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Логин уже существует");
                return;
            }

            User newUser = new User(
                    login,
                    req.getParameter("password"),
                    req.getParameter("FIO"),
                    Date.valueOf(req.getParameter("date_birth")),
                    User.Role.USER
            );

            userRepo.save(newUser);
            resp.sendRedirect(req.getContextPath() + "/views/auth/signing.jsp");

    }
}
