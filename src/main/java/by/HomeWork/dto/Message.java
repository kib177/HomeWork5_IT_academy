package by.HomeWork.dto;

import java.sql.Timestamp;

public class Message {
    private Timestamp timeSend;
    private User sender;
    private User recipient;
    private String text;


    // для дат поменять на localdate

    public Message(Timestamp timeSend, User sender,
                   User recipient, String text) {
        this.timeSend = timeSend;
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
    public String toString() {
        return "Message{" +
                "timeSend=" + timeSend +
                ", sender=" + sender.getLogin() +
                ", recipient=" + recipient.getLogin() +
                ", text='" + text + '\'' +
                '}';
    }
}
