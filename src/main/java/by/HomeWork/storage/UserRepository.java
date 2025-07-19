package by.HomeWork.storage;

import by.HomeWork.database.ConnectionDB;
import by.HomeWork.dto.User;
import by.HomeWork.storage.api.AbstractRepository;
import by.HomeWork.storage.api.IUserRepository;

import javax.sql.DataSource;
import java.sql.*;

import java.util.List;
import java.util.Optional;

/**
 * Реализация репозитория для работы с сущностями {@link User}.
 * Использует шаблон проектирования Singleton для обеспечения единственного экземпляра.
 * Наследует базовую функциональность из {@link AbstractRepository} и реализует {@link IUserRepository}.
 */
public class UserRepository extends AbstractRepository<User> implements IUserRepository {
    private static volatile UserRepository instUserRep;
    /**
     * Конструктор с внедрением источника данных.
     * @param dataSource Источник данных для подключения к БД.
     */
    public UserRepository(DataSource dataSource) {
        super(dataSource);
    }
    /**
     * Сохраняет пользователя в базу данных.
     * @param user Объект пользователя для сохранения.
     */
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
    /**
     * Ищет пользователя по логину.
     * @param login Логин пользователя.
     * @return {@link Optional}, содержащий пользователя если найден, иначе пустой.
     */
    public Optional<User> findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        return queryForObject(sql, this::mapUser, login);
    }
    /**
     * Возвращает список всех пользователей.
     * @return Список объектов {@link User}.
     */
    @Override
    public List<User> getAll() {
        return query("SELECT * FROM users", this::mapUser);
    }
    /**
     * Преобразует строку {@link ResultSet} в объект {@link User}.
     * @param rs Набор результатов SQL-запроса.
     * @return Объект пользователя.
     */
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
    /**
     * Возвращает единственный экземпляр класса (реализация Singleton).
     * Использует двойную проверку для потокобезопасности.
     * @return Экземпляр {@link UserRepository}.
     */
    public static UserRepository getInstUserRep() {
        if (instUserRep == null) {
            synchronized (UserRepository.class) {
                if (instUserRep == null) {
                    instUserRep = new UserRepository(
                            ConnectionDB.getInstConnectionDB().getDataSource()
                    );
                }
            }
        }
        return instUserRep;
    }
}



