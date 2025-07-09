<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Мои сообщения</title>
</head>
<body>

<h2>Входящие сообщения</h2>
<p>Количество сообщений: ${list}</p>
<form action="${pageContext.request.contextPath}/api/message" method="get">
<table>
    <tr>
        <th>Дата/Время</th>
        <th>От кого</th>
        <th>Текст</th>
    </tr>
    <c:forEach items="${list}" var="entry">
    <tr>
        <td>${entry.text}</td>
        </c:forEach>
    </tr>
</table>

</form>
<!-- Ссылка на отправку сообщения -->
<p><a href="${pageContext.request.contextPath}/views/user/message">Отправить новое сообщение</a></p>
</body>
</html>