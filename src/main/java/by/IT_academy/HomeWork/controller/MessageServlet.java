package by.IT_academy.HomeWork.controller;

import by.IT_academy.HomeWork.core.ContextFactory;
import by.IT_academy.HomeWork.core.dto.User;
import by.IT_academy.HomeWork.service.api.IMessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/message")
public class MessageServlet extends HttpServlet {
    private IMessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = ContextFactory.getBean(IMessageService.class);
    }

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