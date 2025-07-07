package by.HomeWork.controller;

import by.HomeWork.dto.User;
import by.HomeWork.service.StatsListener;
import by.HomeWork.storage.UserAuthentication;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static by.HomeWork.service.Connection.getDataSource;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/ui/singIn.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html; charset=UTF-8");

        UserAuthentication auth = new UserAuthentication(getDataSource());

        // Пытаемся авторизовать пользователя

        try {
            User user = auth.authenticate(
                    username, password);

            if (user != null) {
                response.getWriter().write("Успешная авторизация!");
                response.getWriter().write("Добро пожаловать, " + user.getFIO());
                response.getWriter().write("Ваша роль: " + user.getRole());
                init(user,request, response);
            } else {
                response.getWriter().write("Неверный логин или пароль");
            }
        } catch (SQLException e) {
            response.getWriter().write("Ошибка при авторизации: " + e.getMessage());
        }

        // при проверке пользователя выдавать ошибку
        // вход = сохранить текущего пользователя в сессию в атрибут user
    }

    public void init(User user, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User tom = (User) session.getAttribute("User");

        PrintWriter out = response.getWriter();
        try {
            if (tom == null) {

                tom = user;
                session.setAttribute("User", tom);
                out.println("Session data are set");
            } else {
                out.println("Name: " + tom.getLogin());
                session.removeAttribute("person");
            }
        } finally {
            out.close();
        }
    }
}
