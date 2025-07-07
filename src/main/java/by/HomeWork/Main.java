package by.HomeWork;

import by.HomeWork.dto.Message;
import by.HomeWork.storage.AddUser;
import by.HomeWork.storage.GetMessage;
import by.HomeWork.storage.SendMessage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static by.HomeWork.service.Connection.getDataSource;

public class Main {
    public static void main(String[] args) throws SQLException {
        SendMessage sendMessage = new SendMessage(getDataSource());
        sendMessage.saveMessage("kirill", "kirill123", "kirill");

        AddUser addUser = new AddUser(getDataSource());
        addUser.registerUser("kirill123", "brigi", Date.valueOf("2025-01-01"), "brigi kirill");


    }
}