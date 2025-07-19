package by.HomeWork.storage;

import by.HomeWork.database.ConnectionDB;
import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;

import by.HomeWork.storage.api.AbstractRepository;
import by.HomeWork.storage.api.IMessageRepository;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
/**
 * Реализация репозитория для работы с сообщениями ({@link Message}).
 * Использует Singleton для управления экземпляром. Наследует {@link AbstractRepository}
 * и реализует {@link IMessageRepository}.
 */
public class MessageRepository extends AbstractRepository<Message> implements IMessageRepository {
    private final UserRepository userRepository = UserRepository.getInstUserRep();
    private static volatile MessageRepository instMsgRepository;
    /**
     * Приватный конструктор с внедрением источника данных.
     * @param dataSource Источник данных для подключения к БД.
     */
    private MessageRepository(DataSource dataSource) {
        super(dataSource);
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
        return query("SELECT * FROM messages", this::mapMessage);
    }
    /**
     * Ищет сообщения по получателю.
     * @param recipient Логин получателя.
     * @return Список сообщений, адресованных указанному получателю.
     */
    @Override
    public List<Message> findByRecipient(String recipient) {
        String sql = "SELECT * FROM messages WHERE recipient = ?";
        return query(sql, this::mapMessage, recipient);
    }
    /**
     * Преобразует строку {@link ResultSet} в объект {@link Message}.
     * Автоматически заполняет связанные сущности {@link User} (отправитель/получатель).
     * @param rs Набор результатов SQL-запроса.
     * @return Объект сообщения.
     */
    private Message mapMessage(ResultSet rs) throws SQLException {
        User sender = userRepository.findByLogin(rs.getString("sender"))
                    .orElseThrow(() -> new SQLException("In mapMessage not found: sender"));

        User recipient = userRepository.findByLogin(rs.getString("recipient"))
                .orElseThrow(() -> new SQLException("In mapMessage not found: recipient"));

        return Message.builder()
                .timeSend(rs.getTimestamp("sent_datetime"))
                .sender(sender)
                .recipient(recipient)
                .text(rs.getString("text"))
                .build();
    }
    /**
     * Возвращает единственный экземпляр класса (реализация Singleton).
     * Использует двойную проверку для потокобезопасности.
     * @return Экземпляр {@link MessageRepository}.
     */
    public static MessageRepository getInstMsgRepository() {
        if (instMsgRepository == null) { // Первая проверка (без блокировки)
            synchronized (MessageRepository.class) { // Синхронизация по классу
                if (instMsgRepository == null) { // Вторая проверка (внутри блокировки)
                    instMsgRepository = new MessageRepository(
                            ConnectionDB.getInstConnectionDB().getDataSource()
                    );
                }
            }
        }
        return instMsgRepository;
    }
}

