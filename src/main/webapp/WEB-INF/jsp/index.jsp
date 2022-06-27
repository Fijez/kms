<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <style type="text/css">
        @import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@1,300&family=Montserrat:wght@300;400;500;700;900&display=swap');

        html {
            font-family: 'Montserrat', sans-serif;
            font-size: 16px;
            line-height: 24px;
            font-weight: 400;
            color: #222;
        }

        a, a:hover, a:focus, a:active {
            text-decoration: none;
            color: inherit;
        }

        div {
            font-size: 16px;
            line-height: 24px;
            font-weight: 400;
            color: #222;
        }

        h2 {
            font-size: 36px;
            line-height: 42px;
            font-weight: 700;
            margin: 20px;
            color: DarkSlateGrey;
        }

        h4 {
            font-size: 24px;
            line-height: 42px;
            font-weight: 700;
            margin: 20px;
            color: DarkSlateGrey;
        }

        input::placeholder {
            font-family: 'Montserrat', sans-serif;
            font-size: 16px;
            text-align: center;
        }

        button {
            font-family: 'Montserrat', sans-serif;
            font-size: 18px;
            text-align: center;
            margin: 20px;
        }

        .pageForm {
            margin: 100px auto auto;
            width: 60%;
            border: 3px solid MediumSeaGreen;
            padding: 10px;
            text-align: center;
        }
    </style>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div align="right">
    <h4>${pageContext.request.userPrincipal.name}</h4>
</div>

<div class=pageForm style="background-color:PaleGreen;">
    <h2>Главная</h2>
    <sec:authorize access="!isAuthenticated()">
        <button><a href="/login">Войти</a></button>
        <button><a href="/registration">Зарегистрироваться</a></button>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <button><a href="/logout">Выйти</a></button>
    </sec:authorize>
    <button><a href="/search">Поиск статей</a></button>
    <button><a href="/admin">Пользователи (только админ)</a></button>
</div>
</body>
</html>