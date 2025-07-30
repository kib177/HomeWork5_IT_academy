package by.IT_academy.HomeWork.repository;

import by.IT_academy.HomeWork.repository.api.IMessageRepository;
import by.IT_academy.HomeWork.repository.api.IUserRepository;

public class RepositoryFactory {
    private static final IUserRepository userRepository = UserRepository.getInstUserRep();
    private static final IMessageRepository messageRepository = MessageRepository.getInstMsgRepository();

    public static IUserRepository getUserRepository() {
        return userRepository;
    }
    public static IMessageRepository getMessageRepository() {
        return messageRepository;
    }
}
