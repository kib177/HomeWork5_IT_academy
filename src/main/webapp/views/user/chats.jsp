<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Мои сообщения</title>
    <jsp:include page="../pipa/styleNavbar.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../../views/pipa/href.jsp"></jsp:include>
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
<p><a href="${pageContext.request.contextPath}/views/user/message.jsp">Отправить новое сообщение</a></p>
</body>
</html>