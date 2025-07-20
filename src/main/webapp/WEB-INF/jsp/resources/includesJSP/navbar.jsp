<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <%@include file='../css/styleNavbar.css' %>
</style>


<nav class="navbar">
    <div class="nav-container">
        <div class="logo">
            <a href = "${pageContext.request.contextPath}/ui/" style="text-decoration: none"> ChatApp</a>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/ui/" class="nav-link">Главная</a></li>

            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/ui/signIn" class="nav-link">Войти</a></li>
                    <li><a href="${pageContext.request.contextPath}/ui/signUp" class="nav-link">Регистрация</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/ui/user/messages" class="nav-link">Сообщения</a></li>
                    <li><a href="${pageContext.request.contextPath}/ui/user/chats" class="nav-link">Чат</a></li>
                    <c:if test="${sessionScope.user.role == 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/ui/admin/statistics" class="nav-link">Статистика</a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/api/logout" class="nav-link">Выйти</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

