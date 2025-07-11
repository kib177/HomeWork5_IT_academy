<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="site-header">
  <nav class="navbar">
    <div class="container">
      <ul class="nav-menu">
        <li><a href="${pageContext.request.contextPath}/" class="nav-link"><i class="fas fa-home"></i> Главная</a></li>
        <li><a href="${pageContext.request.contextPath}/views/auth/signIn.jsp" class="nav-link"><i class="fas fa-sign-in-alt"></i> Войти</a></li>
        <li><a href="${pageContext.request.contextPath}/views/auth/signUp.jsp" class="nav-link"><i class="fas fa-user-plus"></i> Регистрация</a></li>
        <li><a href="${pageContext.request.contextPath}/views/user/message.jsp" class="nav-link"><i class="fas fa-envelope"></i> Сообщения</a></li>
        <li><a href="${pageContext.request.contextPath}/api/message" class="nav-link"><i class="fas fa-comments"></i> Чат</a></li>
        <li><a href="${pageContext.request.contextPath}/api/admin/statistics" class="nav-link"><i class="fas fa-chart-bar"></i> Статистика</a></li>
      </ul>
    </div>
  </nav>
</header>
