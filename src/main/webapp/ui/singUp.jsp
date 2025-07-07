<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
        }

        .container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 400px;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 24px;
        }

        .form-group {
            margin-bottom: 16px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error {
            color: #d9534f;
            font-size: 14px;
            margin-top: 5px;
        }

        .login-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<jsp:include page="pipa/href.jsp"></jsp:include>

<div class="container">
    <h1>Создать аккаунт</h1>
    <form action="user" method="post">
        <div class="form-group">
            <label for="login">Имя пользователя:</label>
            <input type="text" id="login" name="login" required>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Подтвердите пароль:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>

        <div class="form-group">
            <label for="dateOfBirth">Введите дату рождения</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required>
        </div>

        <div class="form-group">
            <label for="FIO">ФИО</label>
            <input type="text" id="FIO" name="FIO" required>
        </div>

        <input type="submit" value="Зарегистрироваться">

        <%-- Отображение ошибок --%>
        <% if (request.getAttribute("error") != null) { %>
        <div class="error">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>
    </form>

    <div class="login-link">
        Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a>
    </div>
</div>

<script>
    // Клиентская валидация паролей
    document.querySelector('form').addEventListener('submit', function (event) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            event.preventDefault();
            alert('Пароли не совпадают!');
        }
    });
</script>
</body>
</html>
