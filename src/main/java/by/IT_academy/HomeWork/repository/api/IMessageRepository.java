package by.IT_academy.HomeWork.repository.api;

import by.IT_academy.HomeWork.core.dto.Message;

import java.util.List;

public interface IMessageRepository extends IRepository<Message> {
    List<Message> findByRecipient(String recipient);
}
