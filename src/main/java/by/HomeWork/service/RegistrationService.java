package by.HomeWork.service;

import by.HomeWork.dto.User;
import by.HomeWork.storage.UserRepository;

import java.sql.Date;

public class RegistrationService {
    private final UserFactory userFactory = new UserFactory();

    public void registerUser(String login,
            String password,String FIO, Date dateOfBirth) {

        User user = userFactory.createUser(
                login, password, FIO, dateOfBirth, User.Role.USER
        );

        UserRepository.getInstUserRep().save(user);
    }
}
