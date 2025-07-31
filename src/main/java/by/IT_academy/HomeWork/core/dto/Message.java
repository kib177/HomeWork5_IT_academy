package by.IT_academy.HomeWork.core.dto;

import java.sql.Timestamp;
import java.util.Objects;
/**
 * Класс, представляющий сообщение между пользователями.
 * Содержит информацию об отправителе, получателе, тексте сообщения и времени отправки.
 * Для создания экземпляров используется паттерн Builder.
 */
public class Message {
    private Timestamp timeSend;
    private final User sender;
    private final User recipient;
    private final String text;

    private Message(Timestamp timeSend, User sender,
                   User recipient, String text) {
        this.timeSend = timeSend;
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }

    private Message(User sender, User recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }

    public Timestamp getTimeSend() {
        return timeSend;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(timeSend, message.timeSend) && Objects.equals(sender, message.sender) && Objects.equals(recipient, message.recipient) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSend, sender, recipient, text);
    }

    @Override
    public String toString() {
        return "Message{" +
                "timeSend=" + timeSend +
                ", sender=" + sender.getLogin() +
                ", recipient=" + recipient.getLogin() +
                ", text='" + text + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    /**
     * Внутренний класс для пошагового создания объектов Message.
     * Позволяет установить поля сообщения и выполнить валидацию.
     * Реализует паттерн Builder.
     */
    public static class Builder {
        private Timestamp timeSend;
        private User sender;
        private User recipient;
        private String text;

        public Builder timeSend(Timestamp timeSend) {
            this.timeSend = timeSend;
            return this;
        }

        public Builder sender(User sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(User recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            // Проверяем поля
            if (sender == null || recipient == null || text == null) {
                throw new IllegalArgumentException("Sender, recipient, and text must be set");
            }

            // выбираю конструктор в зависимости от задачи
            if (timeSend != null) {
                return new Message(timeSend, sender, recipient, text);
            } else {
                return new Message(sender, recipient, text);
            }
        }
    }

}
