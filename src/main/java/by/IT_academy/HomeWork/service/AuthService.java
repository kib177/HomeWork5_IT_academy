package by.IT_academy.HomeWork.service;

import by.IT_academy.HomeWork.dto.User;
import by.IT_academy.HomeWork.service.api.IAuthService;
import by.IT_academy.HomeWork.repository.api.IUserRepository;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
/**
 * Сервис аутентификации пользователей.
 * Реализует логику входа и выхода пользователя с использованием сессий.
 * При аутентификации проверяет соответствие предоставленных учетных данных
 */
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;

    // Внедрение зависимости
    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Выполняет вход пользователя в систему.
     * Проверяет логин и пароль. При успешной проверке сохраняет объект пользователя в сессию.
     *
     * @return user - авторизированный пользователь
     */
    @Override
    public User login(String login, String password) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new SecurityException("Неверный логин"));;

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new SecurityException("Неверный пароль");
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
