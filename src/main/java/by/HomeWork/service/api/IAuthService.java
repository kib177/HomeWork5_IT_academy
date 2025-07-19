package by.HomeWork.service.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface IAuthService {
    void login (HttpServletRequest req, String login, String password);
    void logout(HttpSession session);
}
