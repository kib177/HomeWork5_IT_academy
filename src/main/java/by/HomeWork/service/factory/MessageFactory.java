package by.HomeWork.service.factory;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;

public class MessageFactory {
    public Message createMessage(User sender, User recipient, String text){
        return Message.builder()
                        .sender(sender)
                        .recipient(recipient)
                        .text(text)
                        .build();
    }
}
