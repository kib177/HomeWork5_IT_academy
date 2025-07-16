<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Отправить сообщение</title>
    <style>
        <%@include file='../../resources/css/styleMessage.css' %>
    </style>
</head>
<body>
<!-- Блок для отображения ошибок -->
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<jsp:include page="../../resources/includesJSP/navbar.jsp"></jsp:include>
<h1>Отправить сообщение</h1>
<div class="form-container">
    <form action="${pageContext.request.contextPath}/api/message" method="post">
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
    <p><a href="${pageContext.request.contextPath}/ui/user/chats">Полученные сообщения</a></p>
</div>
</body>
</html>