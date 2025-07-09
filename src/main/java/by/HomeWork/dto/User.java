package by.HomeWork.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private String login;
    private String password;
    private String FIO;
    private Date dateBirth;
    private Timestamp DateRegistration;
    private Role role;

    public User(String login, String password, String FIO, Date dateOfBirth, Timestamp DateRegistration, Role role) {
        this.login = login;
        this.password = password;
        this.FIO = FIO;
        this.dateBirth = dateOfBirth;
        this.DateRegistration = DateRegistration;
        this.role = role;
    }

    public User (String login, String password, String FIO, Date dateOfBirth, Role role) {
        this.login = login;
        this.password = password;
        this.FIO = FIO;
        this.dateBirth = dateOfBirth;
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public Timestamp getDateRegistration() {
        return DateRegistration;
    }

    public Role getRole() {
        return role;
    }

    public enum Role {
        USER, ADMIN
    }
}
