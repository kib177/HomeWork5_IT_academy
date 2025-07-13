<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация | ChatApp</title>
    <style>
        <%@include file='../../resources/css/styleSignIn.css' %>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<jsp:include page="../../resources/includesJSP/href.jsp"></jsp:include>
<div class="main-content">
    <div class="register-card">
        <div class="register-icon">
            <i class="fas fa-user-plus"></i>
        </div>

        <h1>Создать аккаунт</h1>

        <form action="${pageContext.request.contextPath}/api/user" method="post">
            <div class="form-group">
                <label for="login"><i class="fas fa-user"></i> Имя пользователя</label>
                <input type="text" id="login" name="login" required placeholder="Придумайте уникальное имя">
            </div>

            <div class="form-group">
                <label for="password"><i class="fas fa-lock"></i> Пароль</label>
                <input type="password" id="password" name="password" required placeholder="Не менее 1 символов">
                <span class="password-hint">Используйте буквы, цифры и спецсимволы</span>
            </div>

            <div class="form-group">
                <label for="confirmPassword"><i class="fas fa-check-circle"></i> Подтвердите пароль</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required
                       placeholder="Повторите ваш пароль">
            </div>

            <div class="form-group">
                <label for="FIO"><i class="fas fa-id-card"></i> ФИО</label>
                <input type="text" id="FIO" name="FIO" required placeholder="Ваше полное имя">
            </div>

            <div class="form-group">
                <label for="date_birth"><i class="fas fa-birthday-cake"></i> Дата рождения</label>
                <input type="date" id="date_birth" name="date_birth" required>
            </div>

            <button type="submit" class="btn-register">
                <i class="fas fa-user-plus"></i> Зарегистрироваться
            </button>

            <%-- Отображение ошибок --%>
            <% if (request.getAttribute("error") != null) { %>
            <div class="error">
                <i class="fas fa-exclamation-circle"></i> <%= request.getAttribute("error") %>
            </div>
            <% } %>
        </form>

        <div class="links">
            Уже есть аккаунт? <a href="${pageContext.request.contextPath}/WEB-INF/views/auth/signIn.jsp"><i
                class="fas fa-sign-in-alt"></i> Войти</a>
        </div>
    </div>
</div>

<script>
    // Клиентская валидация паролей
    document.querySelector('form').addEventListener('submit', function (event) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password.length < 1) {
            event.preventDefault();
            alert('Пароль должен содержать не менее 1 символов!');
            return;
        }

        if (password !== confirmPassword) {
            event.preventDefault();
            alert('Пароли не совпадают!');
        }
    });

    // Установка минимальной и максимальной даты для рождения
    const today = new Date().toISOString().split('T')[0];
    const minDate = new Date();
    minDate.setFullYear(minDate.getFullYear() - 100);
    document.getElementById('date_birth').setAttribute('max', today);
    document.getElementById('date_birth').setAttribute('min', minDate.toISOString().split('T')[0]);
</script>
</body>
</html>