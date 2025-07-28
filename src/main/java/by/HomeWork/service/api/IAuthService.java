package by.HomeWork.service.api;

import by.HomeWork.dto.User;
import jakarta.servlet.http.HttpSession;

public interface IAuthService {
    User login (String login, String password);
    void logout(HttpSession session);
}
