package by.IT_academy.HomeWork.repository.api;

import by.IT_academy.HomeWork.core.dto.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMessageMapper {
    Message mapMessage(ResultSet rs) throws SQLException;
}
