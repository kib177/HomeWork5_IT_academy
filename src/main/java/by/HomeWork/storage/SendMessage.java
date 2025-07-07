package by.HomeWork.storage;

import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendMessage {
    private final DataSource dataSource;

    public SendMessage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final String sqlSaveM = "INSERT INTO public.message (sender_id, recipient_id, text) " +
            "VALUES (?, ?, ?)";
    private final String sqlGetId  = "SELECT id FROM users WHERE login = ?";
    /**
     * Сохраняет сообщение в базе данных
     *
     * @param sLogin    Логин отправителя
     * @param rLogin Логин получателя
     * @param text           Текст сообщения
     * @throws SQLException             Если произошла ошибка базы данных
     * @throws IllegalArgumentException Если пользователь не найден
     */
    public void saveMessage(String sLogin, String rLogin, String text){

        int senderId = getUserIdByLogin(sLogin);
        int recipientId = getUserIdByLogin(rLogin);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlSaveM)) {
            statement.setInt(1, senderId);
            statement.setInt(2, recipientId);
            statement.setString(3, text);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Сообщение не было сохранено");
            }
        } catch (SQLException e) {
            throw new StorageException("Ошибка подключения при отправке сообщения", e);
        }
    }

    /**
     * Находит ID пользователя по логину
     *
     * @param login Логин пользователя
     * @return ID пользователя
     * @throws SQLException             Если произошла ошибка базы данных
     * @throws IllegalArgumentException Если пользователь не найден
     */
    private int getUserIdByLogin(String login){

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlGetId)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new IllegalArgumentException("Пользователь не найден: " + login);
            }
        } catch (SQLException e) {
            throw new StorageException("Ошибка подключения при идентификации пользователей", e);
        }
    }
}

