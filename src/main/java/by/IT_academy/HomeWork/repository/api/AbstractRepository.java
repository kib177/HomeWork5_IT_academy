package by.IT_academy.HomeWork.repository.api;

import by.IT_academy.HomeWork.service.api.exceptions.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Абстрактный базовый класс для реализации репозиториев, работающих с JDBC.
 * Предоставляет общие методы для выполнения SQL-запросов и обработки результатов.
 * Реализует паттерн "шаблонный метод" для операций с базой данных.
 * @param <T> Тип сущности, с которой работает репозиторий
 */
public abstract class AbstractRepository<T> implements IRepository<T> {

    protected final DataSource dataSource;
    /**
     * Конструктор с внедрением источника данных.
     * @param dataSource Источник данных для подключения к БД
     */
    protected AbstractRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    /**
     * Выполняет SQL-команду.
     * @param sql    SQL-запрос с параметрами (?)
     * @param params Параметры для подстановки в запрос
     * @throws StorageException При ошибках выполнения SQL-команды
     */
    protected void update(String sql, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    /**
     * Функциональный интерфейс для преобразования строк ResultSet в объекты.
     * @param <T> Тип возвращаемого объекта
     */
    @FunctionalInterface
    protected interface RowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }
    /**
     * Выполняет SQL-запрос, возвращающий не более одного результата.
     * @param sql       SQL-запрос с параметрами (?)
     * @param rowMapper Маппер для преобразования ResultSet в объект
     * @param params    Параметры для подстановки в запрос
     * @return Optional с объектом, если результат найден, иначе пустой Optional
     * @throws StorageException При ошибках выполнения запроса
     */
    protected Optional<T> queryForObject(String sql, RowMapper<T> rowMapper, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();

            return rs.next() ? Optional.of(rowMapper.map(rs)) : Optional.empty();

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    /**
     * Выполняет SQL-запрос, возвращающий список результатов.
     * @param sql       SQL-запрос с параметрами (?)
     * @param rowMapper Маппер для преобразования ResultSet в объекты
     * @param params    Параметры для подстановки в запрос
     * @return Список объектов (может быть пустым)
     * @throws StorageException При ошибках выполнения запроса
     */
    protected List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rowMapper.map(rs));
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
        return result;
    }
    /**
     * Устанавливает параметры в PreparedStatement.
     * @param stmt   Подготавливаемый запрос
     * @param params Массив параметров для подстановки
     */
    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}


