package by.IT_academy.HomeWork.repository.api;

import by.IT_academy.HomeWork.core.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IUserMapper {
    User mapUser(ResultSet rs) throws SQLException;
}
