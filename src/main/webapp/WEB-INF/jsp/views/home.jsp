<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная страница</title>

</head>
<body>
<div class = "container">
    <jsp:include page="../resources/includesJSP/href.jsp"></jsp:include>


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
