package by.HomeWork.service;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;
import by.HomeWork.storage.MessageRepository;
import by.HomeWork.storage.UserRepository;
import java.util.List;
import java.util.Optional;

public class MessageService {
    public List<Message> getUserMessages(User user) {
        return MessageRepository.getInstMsgRepository()
                .findByRecipient(user.getLogin());
    }

    public void sendMessage(User sender, String recipientLogin, String text) {
        Optional<User> recipient = UserRepository.getInstUserRep()
                .findByLogin(recipientLogin);

        if (recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient not found");
        }

        MessageRepository.getInstMsgRepository().save(
                Message.builder()
                        .sender(sender)
                        .recipient(recipient.get())
                        .text(text)
                        .build()
        );
    }
}
