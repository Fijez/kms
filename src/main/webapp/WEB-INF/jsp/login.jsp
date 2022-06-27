<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
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

        input::placeholder {
            font-family: 'Montserrat', sans-serif;
            font-size: 16px;
            text-align: center;
        }

        button {
            font-family: 'Montserrat', sans-serif;
            font-size: 16px;
            text-align: center;
            margin: 20px;
        }

        .loginForm {
            margin: 100px auto auto;
            width: 60%;
            border: 3px solid MediumSeaGreen;
            padding: 10px;
            text-align: center;
        }
    </style>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class=loginForm style="background-color:PaleGreen;">
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <button type="submit">Log In</button>
            <button><a href="/registration">Зарегистрироваться</a></button>
        </div>
    </form>
</div>

</body>
</html>