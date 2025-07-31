package by.IT_academy.HomeWork.controller;

import by.IT_academy.HomeWork.core.ContextFactory;
import by.IT_academy.HomeWork.service.api.IAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/logout")
public class LogoutServlet extends HttpServlet {
    private IAuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        authService = ContextFactory.getBean(IAuthService.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        authService.logout(request.getSession(false));
        response.sendRedirect(request.getContextPath() + "/ui/");
    }
}

