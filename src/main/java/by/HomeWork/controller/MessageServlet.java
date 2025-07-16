package by.HomeWork.controller;

import by.HomeWork.dto.Message;
import by.HomeWork.service.MessageService;
import by.HomeWork.storage.MessageRepository;
import by.HomeWork.dto.User;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static by.HomeWork.database.ConnectionDB.getDataSource;
import static by.HomeWork.database.ConnectionDB.getInstConnectionDB;

@WebServlet("/api/message")
public class MessageServlet extends HttpServlet {
    private final MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("user");
        req.setAttribute("listMessage", messageService.getUserMessages(currentUser));

        req.getRequestDispatcher("/WEB-INF/jsp/views/user/chats.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        try {
            User user = (User) req.getSession().getAttribute("user");
            messageService.sendMessage(
                    user,
                    req.getParameter("recipient"),
                    req.getParameter("message")
            );
            resp.sendRedirect(req.getContextPath() + "/ui/user/message");

        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}