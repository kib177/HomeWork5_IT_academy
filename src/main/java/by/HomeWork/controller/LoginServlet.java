package by.HomeWork.controller;

import by.HomeWork.dto.User;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.HomeWork.database.Connection.getDataSource;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserRepository userRepository = new UserRepository(getDataSource());
        User user = userRepository.findByLogin(login).get();

        if (user == null || !user.getPassword().equals(password)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/views/user/messages");
    }
}