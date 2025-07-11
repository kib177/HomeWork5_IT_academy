<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../pipa/styleNavbar.jsp"></jsp:include>
<jsp:include page="../../views/pipa/href.jsp"></jsp:include>
<h2>Статистика системы</h2>
<p>Активных пользователей: ${stats.activeUsers}</p>
<p>Всего пользователей: ${stats.totalUsers}</p>
<p>Всего сообщений: ${stats.totalMessages}</p>