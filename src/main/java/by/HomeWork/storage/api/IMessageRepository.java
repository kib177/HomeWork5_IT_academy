package by.HomeWork.storage.api;

import by.HomeWork.dto.Message;

import java.util.List;

public interface IMessageRepository extends IRepository<Message> {
    List<Message> findByRecipient(String recipient);
}
