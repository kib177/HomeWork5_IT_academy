package by.HomeWork.controller;

import by.HomeWork.dto.Message;
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

import static by.HomeWork.database.Connection.getDataSource;

@WebServlet("/api/message")
public class MessageServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository(getDataSource());
    private final MessageRepository messageRepository = new MessageRepository(getDataSource());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }*/

        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser == null) {
            resp.sendRedirect("views/user/singing.jsp"); // Перенаправление на логин
            return;
        }
        MessageRepository messageRepository1 = new MessageRepository(getDataSource());
        List<Message> messages = messageRepository1.findByRecipient(currentUser.getLogin());
        req.setAttribute("list", messages);
        req.getRequestDispatcher("/views/user/chats.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User fromUser = (User) req.getSession().getAttribute("user");
        String recipient = req.getParameter("recipient");
        String text = req.getParameter("message");

        User toUser = userRepository.findByLogin(recipient).get();
        if (toUser == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Recipient not found");
            return;
        }

        Message message = new Message(
                fromUser, toUser, text
        );

        messageRepository.save(message);
        resp.sendRedirect(req.getContextPath() + "/views/user/message.jsp");
    }
}