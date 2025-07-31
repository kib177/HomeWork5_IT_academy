package by.IT_academy.HomeWork.service.api;

import by.IT_academy.HomeWork.core.dto.Message;
import by.IT_academy.HomeWork.core.dto.User;

import java.util.List;

public interface IMessageService {
    List<Message> getUserMessages(User user);
    void sendMessage(User sender, String recipientLogin, String text);
}
