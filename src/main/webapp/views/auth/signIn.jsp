<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вход в систему</title>
    <jsp:include page="../pipa/styleNavbar.jsp"></jsp:include>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: #f5f7fa;
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

        /* Карточка входа */
        .login-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
            width: 100%;
            max-width: 420px;
            padding: 40px 35px;
            text-align: center;
            margin: 20px 0;
        }

        .login-icon {
            background: #eef5ff;
            width: 70px;
            height: 70px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 25px;
        }

        .login-icon i {
            font-size: 32px;
            color: #4361ee;
        }

        h2 {
            color: #333;
            margin-bottom: 30px;
            font-weight: 600;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: 500;
        }

        input {
            width: 100%;
            padding: 14px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s;
        }

        input:focus {
            border-color: #4361ee;
            outline: none;
            box-shadow: 0 0 0 3px rgba(67, 97, 238, 0.15);
        }

        .btn-login {
            background: #4361ee;
            color: white;
            border: none;
            border-radius: 8px;
            padding: 14px;
            font-size: 17px;
            font-weight: 600;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            transition: background 0.3s;
        }

        .btn-login:hover {
            background: #3a56e0;
        }

        .error {
            background: #ffebee;
            color: #d32f2f;
            padding: 12px;
            border-radius: 8px;
            margin: 20px 0;
            font-size: 15px;
        }

        .links {
            margin-top: 25px;
            font-size: 15px;
            color: #666;
        }

        .links a {
            color: #4361ee;
            text-decoration: none;
            transition: color 0.3s;
            font-weight: 500;
        }

        .links a:hover {
            text-decoration: underline;
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
            <li><a href="${pageContext.request.contextPath}/views/auth/signUp.jsp" class="nav-link"><i class="fas fa-user-plus"></i> Регистрация</a></li>
        </ul>
    </div>
</nav>

<!-- Основное содержимое -->
<div class="main-content">
    <div class="login-card">
        <div class="login-icon">
            <i class="fas fa-lock"></i>
        </div>
        <h2>Вход в систему</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/api/login" method="POST">
            <div class="form-group">
                <label for="login"><i class="fas fa-user"></i> Логин:</label>
                <input type="text" id="login" name="login" placeholder="Введите логин" required>
            </div>
            <div class="form-group">
                <label for="password"><i class="fas fa-key"></i> Пароль:</label>
                <input type="password" id="password" name="password" placeholder="Введите пароль" required>
            </div>
            <button type="submit" class="btn-login">Войти</button>
        </form>

        <div class="links">
            <p>Ещё нет аккаунта? <a href="${pageContext.request.contextPath}/views/auth/signUp.jsp">Зарегистрируйтесь</a></p>
        </div>
    </div>
</div>
</body>
</html>