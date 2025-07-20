package by.HomeWork.service;

import by.HomeWork.dto.User;
import by.HomeWork.service.api.IAuthService;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
/**
 * Сервис аутентификации пользователей.
 * Реализует логику входа и выхода пользователя с использованием сессий.
 * При аутентификации проверяет соответствие предоставленных учетных данных
 */
public class AuthService implements IAuthService {
    /**
     * Выполняет вход пользователя в систему.
     * Проверяет логин и пароль. При успешной проверке сохраняет объект пользователя в сессию.
     */
    @Override
    public void login(HttpServletRequest req, String login, String password) {
        User user = UserRepository.getInstUserRep()
                .findByLogin(login)
                .orElse(null);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new SecurityException("Неверные учетные данные");
        }
        req.getSession().setAttribute("user", user);
    }
    /**
     * Выполняет выход пользователя из системы.
     */
    @Override
    public void logout(HttpSession session) {
        if (session != null) session.invalidate();
    }
}
