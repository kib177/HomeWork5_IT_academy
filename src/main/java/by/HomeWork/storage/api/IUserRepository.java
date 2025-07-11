package by.HomeWork.storage.api;

import by.HomeWork.dto.User;

import java.util.Optional;

public interface IUserRepository extends IRepository<User> {
    Optional<User> findByLogin(String login);
}
