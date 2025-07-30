package by.IT_academy.HomeWork.service;

import by.IT_academy.HomeWork.dto.Message;
import by.IT_academy.HomeWork.dto.User;
import by.IT_academy.HomeWork.service.api.IMessageService;
import by.IT_academy.HomeWork.repository.MessageRepository;
import by.IT_academy.HomeWork.repository.UserRepository;
import by.IT_academy.HomeWork.repository.api.IMessageRepository;
import by.IT_academy.HomeWork.repository.api.IUserRepository;

import java.util.List;
/**
 * Реализация сервиса для работы с сообщениями между пользователями.
 * Предоставляет методы для получения сообщений пользователя и отправки новых сообщений.
 * {@link MessageRepository} для хранения сообщений и {@link UserRepository} для доступа к данным пользователей.
 */
public class MessageService implements IMessageService {
    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;

    public MessageService(IMessageRepository messageRepository,
                          IUserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }
    /**
     * Возвращает список входящих сообщений для указанного пользователя.
     * Поиск сообщений осуществляется по логину пользователя-получателя.
     *
     * @param user Пользователь, для которого запрашиваются сообщения (не может быть {@code null}).
     * @return Список объектов {@link Message}, адресованных пользователю.
     *         Если сообщений нет, возвращает пустой список.
     */
    @Override
    public List<Message> getUserMessages(User user) {
        return messageRepository.findByRecipient(user.getLogin());
    }
    /**
     * Отправляет новое сообщение от имени указанного пользователя.
     *
     * @param sender Пользователь-отправитель (не может быть {@code null}).
     * @param recipientLogin Логин пользователя-получателя.
     * @param text Текст сообщения.
     */
    @Override
    public void sendMessage(User sender, String recipientLogin, String text) {
        User recipient = userRepository.findByLogin(recipientLogin)
                .orElseThrow(() -> new SecurityException("Получатель не найден"));

        Message message = Message.builder()
                .sender(sender)
                .recipient(recipient)
                .text(text)
                .build();

        messageRepository.save(message);

    }
}
