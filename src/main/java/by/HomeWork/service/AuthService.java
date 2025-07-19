package by.HomeWork.service;

import by.HomeWork.dto.User;
import by.HomeWork.service.api.IAuthService;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthService implements IAuthService {

    @Override
    public void login(HttpServletRequest req, String login, String password) {
        User user = UserRepository.getInstUserRep()
                .findByLogin(login)
                .orElse(null);

        if (user == null || !user.getPassword().equals(password)) {
            throw new SecurityException("Invalid credentials");
        }
        req.getSession().setAttribute("user", user);
    }

    @Override
    public void logout(HttpSession session) {
        if (session != null) session.invalidate();
    }
}
