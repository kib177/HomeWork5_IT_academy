package by.HomeWork.controller;

import by.HomeWork.storage.AddUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import static by.HomeWork.service.Connection.getDataSource;

@WebServlet("/api/user")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/ui/singUp.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("login");
        String password = request.getParameter("password");
        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        String FIO = request.getParameter("FIO");
        // присвоить пользователю уровень доступа пользователя

        AddUser addUser = new AddUser(getDataSource());
        try {
            addUser.registerUser(username, password, dateOfBirth, FIO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/plain");
        response.getWriter().write("Пользователь создан: " + username);
    }
}

