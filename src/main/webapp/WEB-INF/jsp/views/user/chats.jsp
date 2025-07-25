<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Мои сообщения</title>
    <style>
        <%@include file='../../resources/css/styleChats.css' %>
    </style>
</head>
<body>
<jsp:include page="../../resources/includesJSP/navbar.jsp"></jsp:include>
<h2>Входящие сообщения</h2>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>От кого</th>
        <th>Текст</th>
    </tr>
    <c:forEach items="${listMessage}" var="message">
        <tr>
            <td>
                <fmt:formatDate value="${message.timeSend}" pattern="yyyy.MM.dd HH:mm:ss"/>
            </td>
            <td>${message.sender.login}</td>
            <td>${message.text}</td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/ui/user/messages">Отправить новое сообщение</a></p>
</body>
</html>