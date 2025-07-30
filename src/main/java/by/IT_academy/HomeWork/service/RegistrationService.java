package by.IT_academy.HomeWork.service;

import by.IT_academy.HomeWork.dto.User;
import by.IT_academy.HomeWork.repository.api.IUserRepository;
import by.IT_academy.HomeWork.service.api.IRegistrationService;
import by.IT_academy.HomeWork.repository.UserRepository;

import java.sql.Date;
/**
 * Сервис регистрации пользователей.
 * Реализует функционал создания учетной записи пользователя и сохранения данных в репозитории.
 */
public class RegistrationService implements IRegistrationService {
    private final IUserRepository userRepository;

    public RegistrationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
/**
 * Регистрирует нового пользователя в системе.
 * Создает объект пользователя с ролью {@link User.Role#USER}
 * после чего сохраняет его в {@link UserRepository}.
 *
 * @param login Логин пользователя (уникальный идентификатор). Не должен быть {@code null}.
 * @param password Пароль пользователя. Не должен быть {@code null}.
 * @param FIO ФИО пользователя в формате "Фамилия Имя Отчество". Не должно быть {@code null}.
 * @param dob Дата рождения пользователя. Формат: {@code java.sql.Date}. Не должна быть {@code null}.
 */
    @Override
    public void registerUser(String login,
            String password,String FIO, Date dob) {

        User user = User.builder()
                .login(login)
                .password(password)
                .FIO(FIO)
                .dateOfBirth(dob)
                .role(User.Role.USER)
                .build();

        userRepository.save(user);
    }
}
