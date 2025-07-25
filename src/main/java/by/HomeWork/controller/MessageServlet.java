package by.HomeWork.controller;

import by.HomeWork.service.MessageService;
import by.HomeWork.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
            throws IOException{

            User user = (User) req.getSession().getAttribute("user");
            messageService.sendMessage(
                    user,
                    req.getParameter("recipient"),
                    req.getParameter("message")
            );
            resp.sendRedirect(req.getContextPath() + "/ui/user/messages");
    }
}