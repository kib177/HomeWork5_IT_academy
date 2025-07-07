package by.HomeWork.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class User {
    private String login;
    private String password;
    private String FIO;
    private Date dateOfBirth;
    private LocalDateTime dateOfRegistration;
    private int role;

    // для дат поменять на localdate

    public User(String login, String password, String FIO,
                Date dateOfBirth, LocalDateTime dateOfRegistration, int role) {
        this.login = login;
        this.password = password;
        this.FIO = FIO;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.role = role;
    }

    public User(String login) {
        this.login = login;
    }

    public User(String senderLogin, Object o) {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFIO() {
        return FIO;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public int getRole() {
        return role;
    }
}
