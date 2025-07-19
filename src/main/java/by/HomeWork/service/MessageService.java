package by.HomeWork.service;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;
import by.HomeWork.service.api.IMessageService;
import by.HomeWork.service.factory.MessageFactory;
import by.HomeWork.storage.MessageRepository;
import by.HomeWork.storage.UserRepository;
import java.util.List;
import java.util.Optional;

public class MessageService implements IMessageService {
    private final MessageFactory messageFactory = new MessageFactory();

    @Override
    public List<Message> getUserMessages(User user) {
        return MessageRepository.getInstMsgRepository()
                .findByRecipient(user.getLogin());
    }

    @Override
    public void sendMessage(User sender, String recipientLogin, String text) {
        Optional<User> recipient = UserRepository.getInstUserRep()
                .findByLogin(recipientLogin);

        if (recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient not found");
        }

        Message message = messageFactory.createMessage(recipient.get(), sender, text);
        MessageRepository.getInstMsgRepository().save(message);

    }
}
