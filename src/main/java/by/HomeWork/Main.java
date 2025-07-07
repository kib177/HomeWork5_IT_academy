package by.HomeWork;

import by.HomeWork.dto.Message;
import by.HomeWork.storage.GetMessage;
import by.HomeWork.storage.SendMessage;

import java.sql.SQLException;
import java.util.List;

import static by.HomeWork.service.Connection.getDataSource;

public class Main {
    public static void main(String[] args) throws SQLException {
        SendMessage sendMessage = new SendMessage(getDataSource());
        sendMessage.saveMessage("kirill", "kirill123", "kirill");


    }
}