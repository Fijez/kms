<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
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
            color: rgba(255, 255, 255, 0.75);
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

            font-family: 'Montserrat', sans-serif;
            font-size: 18px;
            text-align: center;
            margin: 10px;
        }

        .loginForm {
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
    <meta charset="utf-8">
    <title>Вход</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class=loginForm>
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
        </div>
        <div>
            <input name="password" type="password" placeholder="Password"/>
        </div>
        <button type="submit">Log In</button>
    </form>
    <div>
        <a href="/registration">Зарегистрироваться</a>
    </div>
</div>

</body>
</html>