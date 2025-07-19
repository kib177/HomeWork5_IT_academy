package by.HomeWork.service;

import by.HomeWork.dto.User;
import by.HomeWork.service.api.IRegistrationService;
import by.HomeWork.service.factory.UserFactory;
import by.HomeWork.storage.UserRepository;

import java.sql.Date;

public class RegistrationService implements IRegistrationService {
    private final UserFactory userFactory = new UserFactory();

    @Override
    public void registerUser(String login,
            String password,String FIO, Date dateOfBirth) {

        User user = userFactory.createUser(
                login, password, FIO, dateOfBirth, User.Role.USER
        );

        UserRepository.getInstUserRep().save(user);
    }
}
