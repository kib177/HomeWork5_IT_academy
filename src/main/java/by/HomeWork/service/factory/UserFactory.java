package by.HomeWork.service.factory;

import by.HomeWork.dto.User;

import java.sql.Date;
/**
 * Фабричный класс для создания объектов типа {@link User}.
 * <p>
 * Предоставляет метод для создания экземпляров пользователей с использованием паттерна Builder.
 * Упрощает процесс инициализации обязательных полей пользователя.
 * </p>
 */
public class UserFactory {
/**
 * @param login Логин пользователя (уникальный идентификатор)
 * @param password Пароль пользователя (в открытом виде, требует дальнейшего хеширования)
 * @param FIO Полное имя пользователя в формате "Фамилия Имя Отчество"
 * @param dob Дата рождения пользователя в формате java.sql.Date (YYYY-MM-DD)
 * @param role Роль пользователя из перечисления {@link User.Role}
 * @return Новый экземпляр класса {@link User} с заданными параметрами
 */
    public User createUser(String login, String password, String FIO, Date dob, User.Role role) {
        return User.builder()
                .login(login)
                .password(password)
                .FIO(FIO)
                .dateOfBirth(dob)
                .role(role)
                .build();
    }
}
