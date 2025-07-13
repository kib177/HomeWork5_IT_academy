package by.HomeWork.storage;

import by.HomeWork.database.ConnectionDB;
import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;

import by.HomeWork.storage.api.AbstractRepository;
import by.HomeWork.storage.api.IMessageRepository;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class MessageRepository extends AbstractRepository<Message> implements IMessageRepository {
    private final UserRepository userRepository = UserRepository.getInstUserRep();
    private static volatile MessageRepository instMsgRepository;

    private MessageRepository(DataSource dataSource) {
        super(dataSource);
    }

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

    @Override
    public List<Message> getAll() {
        return query("SELECT * FROM messages", this::mapMessage);
    }

    @Override
    public List<Message> findByRecipient(String recipient) {
        String sql = "SELECT * FROM messages WHERE recipient = ?";
        return query(sql, this::mapMessage, recipient);
    }

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

