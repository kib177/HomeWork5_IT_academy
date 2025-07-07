package by.HomeWork.controller;

import by.HomeWork.storage.SendMessage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static by.HomeWork.service.Connection.getDataSource;

@WebServlet("/api/message")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // список сообщений для текущего пользователя
        request.getRequestDispatcher("/ui/user/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    String recipient = request.getParameter("recipient");
    String text = request.getParameter("message");


        SendMessage sendMessage = new SendMessage(getDataSource());
        sendMessage.saveMessage("kirill", recipient, text);

       /* // Обновляем счетчик сообщений
        ServletContext context = request.getServletContext();
        AtomicInteger totalMessages = (AtomicInteger) context.getAttribute("totalMessages");
        if (totalMessages == null) {
            totalMessages = new AtomicInteger();
            context.setAttribute("totalMessages", totalMessages);
        }
        totalMessages.incrementAndGet();*/
    }
}