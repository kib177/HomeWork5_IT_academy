package by.IT_academy.HomeWork.service.api;

import by.IT_academy.HomeWork.dto.User;
import jakarta.servlet.http.HttpSession;

public interface IAuthService {
    User login (String login, String password);
    void logout(HttpSession session);
}
