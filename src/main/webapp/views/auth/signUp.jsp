<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация | ChatApp</title>
    <jsp:include page="../pipa/styleNavbar.jsp"></jsp:include>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #e4edf9 100%);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }



        /* Основное содержимое */
        .main-content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px 20px;
        }

        /* Карточка регистрации */
        .register-card {
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(67, 97, 238, 0.15);
            width: 100%;
            max-width: 480px;
            padding: 45px 40px;
            text-align: center;
            margin: 20px 0;
            position: relative;
            overflow: hidden;
        }

        .register-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 5px;
            background: linear-gradient(90deg, #4361ee, #3a0ca3);
        }

        .register-icon {
            background: linear-gradient(135deg, #4361ee 0%, #3a0ca3 100%);
            width: 80px;
            height: 80px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 25px;
            box-shadow: 0 5px 15px rgba(67, 97, 238, 0.3);
        }

        .register-icon i {
            font-size: 36px;
            color: white;
        }

        h1 {
            color: #2b2d42;
            margin-bottom: 30px;
            font-weight: 700;
            font-size: 2rem;
        }

        .form-group {
            margin-bottom: 22px;
            text-align: left;
            position: relative;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #4a5568;
            font-weight: 600;
            font-size: 15px;
            display: flex;
            align-items: center;
            gap: 6px;
        }

        label i {
            color: #718096;
            font-size: 14px;
        }

        input {
            width: 100%;
            padding: 15px;
            border: 2px solid #e2e8f0;
            border-radius: 10px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        input:focus {
            border-color: #4361ee;
            outline: none;
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.2);
            transform: translateY(-2px);
        }

        .btn-register {
            background: linear-gradient(135deg, #4361ee 0%, #3a0ca3 100%);
            color: white;
            border: none;
            border-radius: 10px;
            padding: 16px;
            font-size: 17px;
            font-weight: 600;
            cursor: pointer;
            width: 100%;
            margin-top: 15px;
            transition: all 0.4s ease;
            box-shadow: 0 5px 15px rgba(67, 97, 238, 0.3);
            letter-spacing: 0.5px;
        }

        .btn-register:hover {
            transform: translateY(-3px);
            box-shadow: 0 7px 20px rgba(67, 97, 238, 0.4);
        }

        .error {
            background: #ffebee;
            color: #d32f2f;
            padding: 15px;
            border-radius: 10px;
            margin: 25px 0;
            font-size: 15px;
            text-align: left;
            border-left: 4px solid #d32f2f;
        }

        .links {
            margin-top: 30px;
            font-size: 16px;
            color: #666;
        }

        .links a {
            color: #4361ee;
            text-decoration: none;
            transition: all 0.3s;
            font-weight: 600;
            position: relative;
            display: inline-block;
        }

        .links a:hover {
            text-decoration: underline;
            transform: translateX(3px);
        }

        .password-hint {
            font-size: 13px;
            color: #718096;
            margin-top: 6px;
            display: block;
        }

    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-container">
        <div class="logo">
            <i class="fas fa-comments"></i>
            <span>ChatApp</span>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/" class="nav-link"><i class="fas fa-home"></i> Главная</a></li>
            <li><a href="${pageContext.request.contextPath}/views/auth/signIn.jsp" class="nav-link"><i class="fas fa-sign-in-alt"></i> Войти</a></li>
            <li><a href="${pageContext.request.contextPath}/views/auth/signUp.jsp" class="nav-link active"><i class="fas fa-user-plus"></i> Регистрация</a></li>
        </ul>
    </div>
</nav>

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
                <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Повторите ваш пароль">
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
            Уже есть аккаунт? <a href="${pageContext.request.contextPath}/views/auth/signIn.jsp"><i class="fas fa-sign-in-alt"></i> Войти</a>
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