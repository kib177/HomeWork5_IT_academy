package by.HomeWork.storage;

import by.HomeWork.dto.Message;
import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private static final List<Message> messages = new ArrayList<>();
    private final DataSource dataSource;

    public MessageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Message message) {
        String sql = "INSERT INTO messages (sender, recipient, text) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, message.getSender().getLogin());
            stmt.setString(2, message.getRecipient().getLogin());
            stmt.setString(3, message.getText());
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new StorageException(e);
        }
    }


    public List<Message> findByRecipient(String recipient) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT m.*, u1.login AS from_name, u2.login AS to_name " +
                "FROM messages m " +
                "JOIN users u1 ON m.sender = u1.login " +
                "JOIN users u2 ON m.recipient = u2.login " +
                "WHERE m.recipient = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, recipient);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(mapMessage(rs));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return messages;
    }

    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                messages.add(mapMessage(rs));
            }
        }catch (SQLException e) {
            throw new StorageException(e);
        }
        return messages;
    }

    private Message mapMessage(ResultSet rs) throws SQLException {
        UserRepository userRepository = new UserRepository(dataSource);

        Message message = new Message(
                rs.getTimestamp("sent_datetime"),
                userRepository.findByLogin(rs.getString("sender")).get(),
                userRepository.findByLogin(rs.getString("recipient")).get(),
                rs.getString("text"));
        return message;
    }
}

