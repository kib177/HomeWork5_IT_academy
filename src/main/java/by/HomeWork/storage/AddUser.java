package by.HomeWork.storage;

import javax.sql.DataSource;
import java.sql.*;

public class AddUser {
    private final DataSource dataSource;

    public AddUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final String sql = "INSERT INTO users (login, password, dateofbirth, fio) " +
            "VALUES (?, ?, ?, ?) RETURNING id";

    private final String checkSql = "SELECT 1 FROM users WHERE login = ?";

    public int registerUser(String login, String password,
                            Date dateOfBirth, String fio) throws SQLException {


        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setObject(3, dateOfBirth);
            statement.setString(4, fio);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("User creation failed, no ID returned");
            }

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                CheckForUser(e, login);
            }
            throw e;
        }
    }

    private void CheckForUser(SQLException e, String login)
            throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setString(1, login);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new SQLException("Логин '" + login + "' уже занят", "23505");
            }
        }
    }
}
