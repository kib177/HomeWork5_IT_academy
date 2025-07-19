package by.HomeWork.service.api;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;

import java.util.List;

public interface IMessageService {
    List<Message> getUserMessages(User user);
    void sendMessage(User sender, String recipientLogin, String text);
}
