package by.IT_academy.HomeWork.repository.mapper;

import by.IT_academy.HomeWork.dto.User;
import by.IT_academy.HomeWork.repository.api.IUserMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IUserMapper {
    private static volatile UserMapper instUserMapper;
    /**
     * Преобразует строку {@link ResultSet} в объект {@link User}.
     * @param rs Набор результатов SQL-запроса.
     * @return Объект пользователя.
     */
    @Override
    public User mapUser(ResultSet rs) throws SQLException {
        return User.builder()
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .FIO(rs.getString("full_name"))
                .dateOfBirth(rs.getDate("birth_date"))
                .dateRegistration(rs.getTimestamp("registration_date"))
                .role(User.Role.valueOf(rs.getString("role")))
                .build();
    }

    public static UserMapper getInstUserMapper() {
        if (instUserMapper == null) {
            synchronized (UserMapper.class) {
                if (instUserMapper == null) {
                    instUserMapper = new UserMapper();
                }
            }
        }
        return instUserMapper;
    }
}
