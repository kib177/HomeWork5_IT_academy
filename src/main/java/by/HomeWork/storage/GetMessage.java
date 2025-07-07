package by.HomeWork.storage;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class GetMessage {
    private final DataSource dataSource;

    public GetMessage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final String sql = "SELECT m.id, m.text, m.created_at, " +
            "       s.id AS sender_id, s.login AS sender_login, s.fio AS sender_fio, " +
            "       r.id AS recipient_id, r.login AS recipient_login, r.fio AS recipient_fio " +
            "FROM public.message m " +
            "JOIN public.users s ON m.sender_id = s.id " +
            "JOIN public.users r ON m.recipient_id = r.id " +
            "WHERE m.recipient_id = ? " +
            "ORDER BY m.created_at DESC";

    /**
     * Получает сообщения для получателя по его логину
     * @param login Логин получателя
     * @return Список сообщений
     * @throws SQLException При ошибках базы данных
     */
    public List<Message> getMessages(String login) throws SQLException {
        int recipientId = getUserIdByLogin(login);

        List<Message> messages = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recipientId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User sender = new User(rs.getString("sender_login"));

                    User recipient = new User(rs.getString("recipient_login"));
                    Message message = new Message(
                            rs.getTimestamp("created_at"),
                            sender,
                            recipient,
                            rs.getString("text")
                    );
                    messages.add(message);
                }
            }
        }
        return messages;
    }

    /**
     * Находит ID пользователя по логину
     * @param login Логин пользователя
     * @return ID пользователя
     * @throws SQLException             Если произошла ошибка базы данных
     * @throws IllegalArgumentException Если пользователь не найден
     */
    private int getUserIdByLogin(String login) throws SQLException {
        String sql = "SELECT id FROM public.users WHERE login = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new IllegalArgumentException("Пользователь не найден: " + login);
            }
        }
    }
}

