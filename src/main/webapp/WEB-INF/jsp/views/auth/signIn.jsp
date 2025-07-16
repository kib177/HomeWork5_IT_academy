<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вход в систему</title>
    <style>
        <%@include file='../../resources/css/styleSignIn.css' %>
    </style>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<jsp:include page="../../resources/includesJSP/navbar.jsp"></jsp:include>
<!-- Основное содержимое -->
<div class="main-content">
    <div class="login-card">
        <div class="login-icon">
            <i class="fas fa-lock"></i>
        </div>
        <h1>Вход в систему</h1>

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
            <p>Ещё нет аккаунта? <b href="${pageContext.request.contextPath}/ui/signUp">Зарегистрируйтесь</b></p>
        </div>
    </div>
</div>
</body>
</html>