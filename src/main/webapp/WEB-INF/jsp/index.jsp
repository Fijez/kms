<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <style type="text/css">
        @import url('https://fonts.googleapis.com/css2?family=Lato:ital,wght@1,300&family=Montserrat:wght@300;400;500;700;900&display=swap');

        body {
            background-color: #21D4FD;
            background-image: linear-gradient(90deg, #21D4FD 0%, #B721FF 100%);
        }

        html {
            font-family: 'Montserrat', sans-serif;
            font-size: 18px;
            line-height: 26px;
            font-weight: 400;
            color: #222;
        }

        a, a:hover, a:focus, a:active {
            font-size: 18px;
            text-decoration: none;
            color: black;
        }

        div {
            font-size: 18px;
            line-height: 24px;
            font-weight: 400;
            color: #222;
            margin: 12px;
        }

        h2 {
            font-size: 40px;
            line-height: 42px;
            font-weight: 700;
            margin: 20px;
            color: white;
        }

        h4 {
            font-size: 28px;
            line-height: 42px;
            font-weight: 700;
            margin: 20px;
            color: white;
        }

        input {
            background: rgba(255, 255, 255, 0.1);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(4px);
            -webkit-backdrop-filter: blur(4px);
            border-radius: 10px;
            padding: 5px 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);

            font-family: 'Montserrat', sans-serif;
            font-size: 18px;
            text-align: center;
        }

        input::placeholder {
            color: rgba(255, 255, 255, 0.5);
        }

        button {
            background: rgba(255, 255, 255, 0.6);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(4px);
            -webkit-backdrop-filter: blur(4px);
            border-radius: 10px;
            padding: 5px 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);

            width: 300px;
            font-family: 'Montserrat', sans-serif;
            font-size: 18px;
            text-align: center;
            margin: 5px;
        }

        .pageForm {
            background: rgba(255, 255, 255, 0.25);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(4px);
            -webkit-backdrop-filter: blur(4px);
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);

            margin: 200px auto auto;
            width: 60%;
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

<div class=pageForm>
    <h2>Главная</h2>
    <sec:authorize access="!isAuthenticated()">
        <div>
            <button><a href="/login">Войти</a></button>
        </div>
        <div>
            <button><a href="/registration">Зарегистрироваться</a></button>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <button><a href="/logout">Выйти</a></button>
    </sec:authorize>
    <div>
        <button><a href="/search">Поиск статей</a></button>
    </div>
    <div>
        <button><a href="/admin">Пользователи (только админ)</a></button>
    </div>
    <div></div>
</div>
</body>
</html>