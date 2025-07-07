<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Отправить сообщение</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        .form-container {
            max-width: 500px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        button {
            padding: 10px 15px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background: #45a049;
        }
    </style>
</head>
<body>
<!-- Блок для отображения ошибок -->
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<h1>Отправить сообщение</h1>
<div class="form-container">
    <form action="message" method="post">
        <div class="form-group">
            <label for="recipient">Логин получателя:</label>
            <input type="text" id="recipient" name="recipient" required>
        </div>
        <div class="form-group">
            <label for="message">Текст сообщения:</label>
            <textarea id="message" name="message" rows="5" required></textarea>
        </div>
        <button type="submit">Отправить</button>
    </form>
</div>
</body>
</html>