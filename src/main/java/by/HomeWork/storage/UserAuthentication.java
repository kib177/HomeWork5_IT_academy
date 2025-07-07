package by.HomeWork.storage;

import by.HomeWork.dto.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

public class UserAuthentication {
    private final DataSource dataSource;

    public UserAuthentication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User authenticate(String login, String password) throws SQLException {
        String sql = "SELECT fio, date_birth, created_at, id_role "
                + "FROM users WHERE login = ? AND password = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            login,
                            password,
                            resultSet.getString("fio"),
                            resultSet.getObject("dateofbirth", Date.class),
                            resultSet.getObject("created_at", LocalDateTime.class),
                            resultSet.getInt("id_role")
                    );
                }
            }
        }
        return null; // Пользователь не найден или неверные данные
    }
}