package by.HomeWork.storage;

import by.HomeWork.dto.User;
import by.HomeWork.storage.api.AbstractRepository;
import by.HomeWork.storage.api.IUserRepository;

import javax.sql.DataSource;
import java.sql.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {
    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public UserRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (login, password, full_name, birth_date, role) VALUES (?, ?, ?, ?, ?)";
        update(sql,
                user.getLogin(),
                user.getPassword(),
                user.getFIO(),
                user.getDateBirth(),
                user.getRole().name()
        );
    }

    public Optional<User> findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        return queryForObject(sql, this::mapUser, login);
    }

    @Override
    public List<User> getAll() {
        return query("SELECT * FROM users", this::mapUser);
    }

    private User mapUser(ResultSet rs) throws SQLException {
        return User.builder()
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .FIO(rs.getString("full_name"))
                .dateOfBirth(rs.getDate("birth_date"))
                .dateRegistration(rs.getTimestamp("registration_date"))
                .role(User.Role.valueOf(rs.getString("role")))
                .build();
    }
}



