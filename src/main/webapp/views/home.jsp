<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная страница</title>
    <style>
    </style>
</head>
<body>
<div class = "container">
    <header>
        <h1>Добро пожаловать</h1>
    </header>

    <jsp:include page="../views/pipa/href.jsp"></jsp:include>
    <div class="content">
        <p class="welcome">Главная страница веб-приложения</p>
        <p>Контекст приложения: ${pageContext.request.contextPath}</p>
    </div>
</div>
</body>
</html>
