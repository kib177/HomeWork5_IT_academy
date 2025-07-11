<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная страница</title>
    <style>
        .site-header {
            background-color: white;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .navbar {
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }

        .nav-menu {
            display: flex;
            justify-content: center;
            list-style: none;
            margin: 0;
            padding: 0;
            flex-wrap: wrap;
        }

        .nav-link {
            display: flex;
            align-items: center;
            padding: 1rem 1.5rem;
            color: #555;
            text-decoration: none;
            transition: all 0.3s ease;
            font-weight: 500;
            position: relative;
            border-bottom: 3px solid transparent;
        }

        .nav-link i {
            margin-right: 10px;
            font-size: 1.1rem;
            transition: transform 0.3s;
        }

        .nav-link:hover {
            color: #4361ee;
            background-color: #f8f9ff;
        }

        .nav-link:hover i {
            transform: translateY(-2px);
        }

        .nav-link.active {
            color: #4361ee;
            border-bottom: 3px solid #4361ee;
            font-weight: 600;
        }

        .nav-link.active i {
            color: #4361ee;
        }

        /* Анимация подчеркивания */
        .nav-link:after {
            content: '';
            position: absolute;
            bottom: -3px;
            left: 0;
            width: 0;
            height: 3px;
            background: #4361ee;
            transition: width 0.3s;
        }

        .nav-link:hover:after {
            width: 100%;
        }

        /* Адаптивность */
        @media (max-width: 992px) {
            .nav-menu {
                justify-content: flex-start;
                overflow-x: auto;
                -webkit-overflow-scrolling: touch;
                padding: 5px 0;
            }

            .nav-link {
                padding: 0.8rem 1rem;
                font-size: 0.9rem;
                white-space: nowrap;
            }
        }

        @media (max-width: 576px) {
            .container {
                padding: 0 10px;
            }

            .nav-link i {
                margin-right: 5px;
                font-size: 1rem;
            }

            .nav-link span {
                display: none;
            }

            .nav-link i {
                margin-right: 0;
                font-size: 1.2rem;
            }
        }
    </style>
</head>
<body>
<div class = "container">
    <header class="site-header">
        <nav class="navbar">
            <div class="container">
                <ul class="nav-menu">
                    <li><a href="${pageContext.request.contextPath}/" class="nav-link"><i class="fas fa-home"></i> Главная</a></li>
                    <li><a href="${pageContext.request.contextPath}/views/auth/signIn.jsp" class="nav-link active"><i class="fas fa-sign-in-alt"></i> Войти</a></li>
                    <li><a href="${pageContext.request.contextPath}/views/auth/signUp.jsp" class="nav-link"><i class="fas fa-user-plus"></i> Регистрация</a></li>
                    <li><a href="${pageContext.request.contextPath}/views/user/message.jsp" class="nav-link"><i class="fas fa-envelope"></i> Сообщения</a></li>
                    <li><a href="${pageContext.request.contextPath}/api/message" class="nav-link"><i class="fas fa-comments"></i> Чат</a></li>
                    <li><a href="${pageContext.request.contextPath}/api/admin/statistics" class="nav-link"><i class="fas fa-chart-bar"></i> Статистика</a></li>
                </ul>
            </div>
        </nav>
    </header>


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
