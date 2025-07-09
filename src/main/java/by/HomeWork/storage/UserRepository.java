package by.HomeWork.storage;

import by.HomeWork.dto.User;
import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(User user) {
        String sql = "INSERT INTO users (login, password, full_name, birth_date, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFIO());
            stmt.setDate(4, user.getDateBirth());
            stmt.setString(5, user.getRole().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    public Optional<User> findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUser(rs));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return Optional.empty();
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return users;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User(
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("full_name"),
                rs.getDate("birth_date"),
                User.Role.valueOf(rs.getString("role")));
        return user;
    }
}


