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
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <div class="content">
                <p class="welcome"> Для дальнейшей работы войдите или зарегистрируйтесь пожалуйста </p>
                <p>admin admin</p>
                <p>Контекст приложения: ${pageContext.request.contextPath}</p>
            </div>
        </c:when>
        <c:otherwise>
            <p>Добро пожаловать ${empty sessionScope.user.FIO}</p>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <p>Админа на мыло</p>
            </c:if>
        </c:otherwise>
    </c:choose>

</div>
</body>

</html>
