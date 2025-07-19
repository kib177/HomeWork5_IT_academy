package by.HomeWork.service.api;

import java.sql.Date;

public interface IRegistrationService {
    void registerUser(String login,
                      String password,
                      String FIO,
                      Date dateOfBirth);
}
