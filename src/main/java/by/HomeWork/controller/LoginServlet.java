package by.HomeWork.controller;

import by.HomeWork.dto.User;
import by.HomeWork.service.AuthService;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserRepository userRepository = UserRepository.getInstUserRep();
        User user = userRepository.findByLogin(login).orElse(null);

        if (user == null || !user.getPassword().equals(password)) {
            setErrorAndForward(req, resp, "Неверный логин или пароль");
            return;
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/ui/");
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        resp.sendRedirect(req.getContextPath() + "/ui/signIn");
    }
}