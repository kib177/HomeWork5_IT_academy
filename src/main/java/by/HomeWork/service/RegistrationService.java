package by.HomeWork.service;

import by.HomeWork.dto.User;
import by.HomeWork.service.api.IRegistrationService;
import by.HomeWork.service.factory.UserFactory;
import by.HomeWork.storage.UserRepository;

import java.sql.Date;
/**
 * Сервис регистрации пользователей.
 * Реализует функционал создания учетной записи пользователя и сохранения данных в репозитории.
 * Использует {@link UserFactory} для создания объектов пользователей.
 */
public class RegistrationService implements IRegistrationService {
    private final UserFactory userFactory = new UserFactory();
/**
 * Регистрирует нового пользователя в системе.
 * Создает объект пользователя с ролью {@link User.Role#USER} через {@link UserFactory},
 * после чего сохраняет его в {@link UserRepository}.
 *
 * @param login Логин пользователя (уникальный идентификатор). Не должен быть {@code null}.
 * @param password Пароль пользователя. Не должен быть {@code null}.
 * @param FIO ФИО пользователя в формате "Фамилия Имя Отчество". Не должно быть {@code null}.
 * @param dateOfBirth Дата рождения пользователя. Формат: {@code java.sql.Date}. Не должна быть {@code null}.
 */
    @Override
    public void registerUser(String login,
            String password,String FIO, Date dateOfBirth) {

        User user = userFactory.createUser(
                login, password, FIO, dateOfBirth, User.Role.USER
        );

        UserRepository.getInstUserRep().save(user);
    }
}
