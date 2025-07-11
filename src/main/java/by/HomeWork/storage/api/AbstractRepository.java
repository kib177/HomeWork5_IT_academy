package by.HomeWork.storage.api;

import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T> implements IRepository<T> {

    protected final DataSource dataSource;

    protected AbstractRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void update(String sql, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @FunctionalInterface
    protected interface RowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

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

    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}


