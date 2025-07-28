package by.HomeWork.service;

import by.HomeWork.dto.User;
import by.HomeWork.service.api.IAuthService;
import by.HomeWork.storage.UserRepository;
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
     *
     * @return user - авторизированный пользователь
     */
    @Override
    public User login(String login, String password) {
        User user = UserRepository.getInstUserRep()
                .findByLogin(login)
                .orElse(null);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new SecurityException("Неверные учетные данные");
        }
        return user;
    }
    /**
     * Выполняет выход пользователя из системы.
     */
    @Override
    public void logout(HttpSession session) {
        if (session != null) session.invalidate();
    }
}
