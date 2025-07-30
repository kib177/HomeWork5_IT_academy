package by.IT_academy.HomeWork.repository;

import by.IT_academy.HomeWork.repository.api.IMessageMapper;
import by.IT_academy.HomeWork.repository.connectionDB.ConnectionDB;
import by.IT_academy.HomeWork.dto.Message;

import by.IT_academy.HomeWork.repository.api.AbstractRepository;
import by.IT_academy.HomeWork.repository.api.IMessageRepository;
import by.IT_academy.HomeWork.repository.mapper.MessageMapper;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
/**
 * Реализация репозитория для работы с сообщениями ({@link Message}).
 * Использует Singleton для управления экземпляром. Наследует {@link AbstractRepository}
 * и реализует {@link IMessageRepository}.
 */
public class MessageRepository extends AbstractRepository<Message> implements IMessageRepository {
    private static volatile MessageRepository instMsgRepository;
    private final IMessageMapper messageMapper;

    /**
     * Приватный конструктор с внедрением источника данных.
     * @param dataSource Источник данных для подключения к БД.
     */
    public MessageRepository(DataSource dataSource, IMessageMapper messageMapper) {
        super(dataSource);
        this.messageMapper = messageMapper;
    }
    /**
     * Сохраняет сообщение в базу данных.
     * @param message Объект сообщения для сохранения.
     */
    @Override
    public void save(Message message) {
        String sql = "INSERT INTO messages (sender, recipient, text, sent_datetime) VALUES (?, ?, ?, ?)";
        update(sql,
                message.getSender().getLogin(),
                message.getRecipient().getLogin(),
                message.getText(),
                new Timestamp(System.currentTimeMillis())
        );
    }
    /**
     * Возвращает все сообщения из базы данных.
     * @return Список объектов {@link Message}.
     */
    @Override
    public List<Message> getAll() {
        return query("SELECT * FROM messages",
                messageMapper::mapMessage);
    }
    /**
     * Ищет сообщения по получателю.
     * @param recipient Логин получателя.
     * @return Список сообщений, адресованных указанному получателю.
     */
    @Override
    public List<Message> findByRecipient(String recipient) {

        return query("SELECT * FROM messages WHERE recipient = ?",
                messageMapper::mapMessage,
                recipient);
    }

    /**
     * Возвращает единственный экземпляр класса (реализация Singleton).
     * Использует двойную проверку для потокобезопасности.
     * @return Экземпляр {@link MessageRepository}.
     */
    public static MessageRepository getInstMsgRepository() {
        if (instMsgRepository == null) {
            synchronized (MessageRepository.class) {
                if (instMsgRepository == null) {
                    instMsgRepository = new MessageRepository(
                            ConnectionDB.getInstConnectionDB().getDataSource(),
                            MessageMapper.getInstMessageMapper()
                    );
                }
            }
        }
        return instMsgRepository;
    }
}

