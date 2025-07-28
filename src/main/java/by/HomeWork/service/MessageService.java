package by.HomeWork.service;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;
import by.HomeWork.service.api.IMessageService;
import by.HomeWork.service.factory.MessageFactory;
import by.HomeWork.storage.MessageRepository;
import by.HomeWork.storage.UserRepository;
import java.util.List;
import java.util.Optional;
/**
 * Реализация сервиса для работы с сообщениями между пользователями.
 * Предоставляет методы для получения сообщений пользователя и отправки новых сообщений.
 * Использует {@link MessageFactory} для создания объектов сообщений,
 * {@link MessageRepository} для хранения сообщений и {@link UserRepository} для доступа к данным пользователей.
 */
public class MessageService implements IMessageService {
    private final MessageFactory messageFactory;

    public MessageService(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
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
        return MessageRepository.getInstMsgRepository()
                .findByRecipient(user.getLogin());
    }
    /**
     * Отправляет новое сообщение от имени указанного пользователя.
     * Проверяет существование получателя в системе, создает сообщение через {@link MessageFactory}
     * и сохраняет его в репозитории.
     *
     * @param sender Пользователь-отправитель (не может быть {@code null}).
     * @param recipientLogin Логин пользователя-получателя.
     * @param text Текст сообщения.
     */
    @Override
    public void sendMessage(User sender, String recipientLogin, String text) {
        Optional<User> recipient = UserRepository.getInstUserRep()
                .findByLogin(recipientLogin);

        if (recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient not found");
        }

        Message message = messageFactory.createMessage(sender, recipient.get(), text);
        MessageRepository.getInstMsgRepository().save(message);

    }
}
