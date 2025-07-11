package by.HomeWork;


import by.HomeWork.dto.Message;
import by.HomeWork.storage.MessageRepository;

import java.sql.SQLException;

import java.util.List;

import static by.HomeWork.database.Connection.getDataSource;


public class Main {
    public static void main(String[] args) throws SQLException {
        //MessageRepository messageRepository = new MessageRepository(getDataSource());
       // List<Message> messages = messageRepository.findByRecipient("admin");
        //System.out.println(messages);

    }
}