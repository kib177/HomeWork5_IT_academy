package by.HomeWork.service;

import by.HomeWork.dto.User;

import java.sql.Date;

public class UserFactory {
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
