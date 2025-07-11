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
import java.util.Optional;

import static by.HomeWork.database.Connection.getDataSource;

@WebServlet("/api/message")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }*/

        User currentUser = (User) req.getSession().getAttribute("user");
        UserRepository user = new UserRepository(getDataSource());
        MessageRepository messageRepository = new MessageRepository(getDataSource(), user);
        List<Message> messages = messageRepository.findByRecipient(currentUser.getLogin());
        req.setAttribute("listMessage", messages);
        req.getRequestDispatcher("/views/user/chats.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserRepository userRepository = new UserRepository(getDataSource());
        MessageRepository messageRepository = new MessageRepository(getDataSource(), userRepository);

        Optional<User> recipient = userRepository.findByLogin(req.getParameter("recipient"));
        if (recipient.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Recipient not found");
            return;
        }

        messageRepository.save(Message.builder()
                .sender((User) req.getSession().getAttribute("user"))
                .recipient(recipient.get())
                .text(req.getParameter("message"))
                .build());

        resp.sendRedirect(req.getContextPath() + "/views/user/message.jsp");
    }
}