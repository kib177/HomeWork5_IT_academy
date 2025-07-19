<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная страница</title>
<style>
    body {
        background: #f5f7fa;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        margin: 20px;
    }
</style>
</head>
<body>
<div class = "container">
    <jsp:include page="../resources/includesJSP/navbar.jsp"></jsp:include>


    <header>
        <h1>Добро пожаловать</h1>
    </header>

    <div class="content">
        <p class="welcome">Главная страница веб-приложения</p>
        <p>Контекст приложения: ${pageContext.request.contextPath}</p>
    </div>
</div>
</body>

</html>
