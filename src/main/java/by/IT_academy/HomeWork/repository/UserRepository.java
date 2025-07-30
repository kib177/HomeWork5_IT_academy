package by.IT_academy.HomeWork.repository;

import by.IT_academy.HomeWork.repository.connectionDB.ConnectionDB;
import by.IT_academy.HomeWork.dto.User;
import by.IT_academy.HomeWork.repository.api.AbstractRepository;
import by.IT_academy.HomeWork.repository.api.IUserMapper;
import by.IT_academy.HomeWork.repository.api.IUserRepository;
import by.IT_academy.HomeWork.repository.mapper.UserMapper;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

/**
 * Реализация репозитория для работы с сущностями {@link User}.
 * Использует шаблон проектирования Singleton для обеспечения единственного экземпляра.
 * Наследует базовую функциональность из {@link AbstractRepository} и реализует {@link IUserRepository}.
 */
public class UserRepository extends AbstractRepository<User> implements IUserRepository {
    private static volatile UserRepository instUserRep;
    private final IUserMapper userMapper;
    /**
     * Конструктор с внедрением источника данных.
     * @param dataSource Источник данных для подключения к БД.
     */
    public UserRepository(DataSource dataSource, IUserMapper userMapper) {
        super(dataSource);
        this.userMapper = userMapper;
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

        return queryForObject("SELECT * FROM users WHERE login = ?",
                userMapper::mapUser,
                login);
    }
    /**
     * Возвращает список всех пользователей.
     * @return Список объектов {@link User}.
     */
    @Override
    public List<User> getAll() {
        return query("SELECT * FROM users",
                userMapper::mapUser);
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
                            ConnectionDB.getInstConnectionDB().getDataSource(),
                            UserMapper.getInstUserMapper()
                    );
                }
            }
        }
        return instUserRep;
    }
}



