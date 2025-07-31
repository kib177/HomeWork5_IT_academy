package by.IT_academy.HomeWork.repository.mapper;

import by.IT_academy.HomeWork.core.dto.Message;
import by.IT_academy.HomeWork.core.dto.User;
import by.IT_academy.HomeWork.repository.api.IMessageMapper;
import by.IT_academy.HomeWork.repository.api.IUserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements IMessageMapper {
    private final IUserRepository userRepository;

    public MessageMapper(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Преобразует строку {@link ResultSet} в объект {@link Message}.
     * Автоматически заполняет связанные сущности {@link User} (отправитель/получатель).
     * @param rs Набор результатов SQL-запроса.
     * @return Объект сообщения.
     */
    @Override
    public Message mapMessage(ResultSet rs) throws SQLException {
        User sender = userRepository.findByLogin(rs.getString("sender"))
                .orElseThrow(() -> new SQLException("In mapMessage not found: sender"));

        User recipient = userRepository.findByLogin(rs.getString("recipient"))
                .orElseThrow(() -> new SQLException("In mapMessage not found: recipient"));

        return Message.builder()
                .timeSend(rs.getTimestamp("sent_datetime"))
                .sender(sender)
                .recipient(recipient)
                .text(rs.getString("text"))
                .build();
    }
}
