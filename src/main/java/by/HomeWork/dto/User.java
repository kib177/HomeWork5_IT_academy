package by.HomeWork.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
/**
 * Класс, представляющий сущность пользователя системы.
 * Содержит информацию для аутентификации, персональные данные и роли.
 */
public class User {
    private String login;
    private String password;
    private String FIO;
    private Date dateBirth;
    private Timestamp dateRegistration;
    private Role role;

    private User(String login, String password, String FIO, Date dateOfBirth, Timestamp dateRegistration, Role role) {
        this.login = login;
        this.password = password;
        this.FIO = FIO;
        this.dateBirth = dateOfBirth;
        this.dateRegistration = dateRegistration;
        this.role = role;
    }

    private User (String login, String password, String FIO, Date dateOfBirth, Role role) {
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
        return dateRegistration;
    }

    public Role getRole() {
        return role;
    }

    public enum Role {
        USER, ADMIN
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(FIO, user.FIO)
                && Objects.equals(dateBirth, user.dateBirth)
                && Objects.equals(dateRegistration, user.dateRegistration)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, FIO, dateBirth, dateRegistration, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", FIO='" + FIO + '\'' +
                ", dateBirth=" + dateBirth +
                ", DateRegistration=" + dateRegistration +
                ", role=" + role +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    /**
     * Внутренний класс для пошагового конструирования объекта User.
     *  Позволяет установить поля пользователя и выполнить валидацию.
     * Реализует паттерн Builder.
     */
    public static class Builder {
        private String login;
        private String password;
        private String FIO;
        private Date dateOfBirth;
        private Timestamp dateRegistration;
        private Role role;

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder FIO(String FIO) {
            this.FIO = FIO;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder dateRegistration(Timestamp dateRegistration) {
            this.dateRegistration = dateRegistration;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            // Проверяем поля
            if (login == null || password == null || FIO == null || dateOfBirth == null || role == null) {
                throw new IllegalArgumentException("Sender, recipient, and text must be set");
            }

            // выбираю конструктор в зависимости от задачи
            if (dateRegistration != null) {
                return new User(login, password, FIO, dateOfBirth, dateRegistration, role);
            } else {
                return new User(login, password, FIO, dateOfBirth, role);
            }
        }
    }
}
