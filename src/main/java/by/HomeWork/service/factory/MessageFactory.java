package by.HomeWork.service.factory;

import by.HomeWork.dto.Message;
import by.HomeWork.dto.User;
/**
 * Фабрика для создания объектов сообщений ({@link Message}).
 * <p>
 * Предоставляет метод для создания экземпляров сообщений с использованием паттерна Builder.
 * Упрощает процесс инициализации обязательных полей сообщения.
 * </p>
 */
public class MessageFactory {
/**
 * Создает объект сообщения с указанными отправителем, получателем и текстом.
 * <p>
 * Автоматически инициализирует обязательные поля сообщения:
 * </p>
 * <ul>
 *   <li>{@code sender} - отправитель сообщения</li>
 *   <li>{@code recipient} - получатель сообщения</li>
 *   <li>{@code text} - содержимое сообщения</li>
 * </ul>
 */
    public Message createMessage(User sender, User recipient, String text){
        return Message.builder()
                        .sender(sender)
                        .recipient(recipient)
                        .text(text)
                        .build();
    }
}
