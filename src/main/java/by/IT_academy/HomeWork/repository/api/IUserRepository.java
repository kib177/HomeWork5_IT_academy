package by.IT_academy.HomeWork.repository.api;

import by.IT_academy.HomeWork.dto.User;

import java.util.Optional;

public interface IUserRepository extends IRepository<User> {
    Optional<User> findByLogin(String login);
}
