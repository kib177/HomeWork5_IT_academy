package by.HomeWork.storage;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;

import by.HomeWork.storage.api.AbstractRepository;
import by.HomeWork.storage.api.IMessageRepository;


import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MessageRepository extends AbstractRepository<Message> implements IMessageRepository {
    private final UserRepository userRepository;

    public MessageRepository(DataSource dataSource, UserRepository userRepository) {
        super(dataSource);
        this.userRepository = userRepository;
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
                .orElseThrow(() -> {
                    try {
                        return new SQLException("Sender not found: " + rs.getString("sender"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

        User recipient = userRepository.findByLogin(rs.getString("recipient"))
                .orElseThrow(() -> {
                    try {
                        return new SQLException("Recipient not found: " + rs.getString("recipient"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

        return Message.builder()
                .timeSend(rs.getTimestamp("sent_datetime"))
                .sender(sender)
                .recipient(recipient)
                .text(rs.getString("text"))
                .build();
    }
}

