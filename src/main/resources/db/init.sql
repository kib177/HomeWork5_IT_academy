-- Создание последовательности для ID сообщений
CREATE SEQUENCE IF NOT EXISTS messages_id_seq;

-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS users (
    login VARCHAR(50) PRIMARY KEY NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    registration_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(10) NOT NULL CHECK (role IN ('USER', 'ADMIN'))
    );

-- Создание таблицы сообщений
CREATE TABLE IF NOT EXISTS messages (
    id INT PRIMARY KEY NOT NULL DEFAULT nextval('messages_id_seq'),
    sent_datetime TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    sender VARCHAR(50) REFERENCES public.users(login),
    recipient VARCHAR(50) REFERENCES public.users(login),
    text TEXT NOT NULL
    );

INSERT INTO users(
    login, password, full_name, birth_date, role)
VALUES (admin, admin, admin, "admin"	2025-07-01,	"ADMIN");