<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            line-height: 36px;
            font-weight: 700;
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

            font-family: 'Montserrat', sans-serif;
            font-size: 18px;
            text-align: center;
            margin: 10px;
        }

        .searchForm {
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
    <title>Поиск</title>
</head>
<body>
<div align="right">
    <h4>${pageContext.request.userPrincipal.name}</h4>
</div>

<div class="searchForm">
    <h2>Поиск статей</h2>
    <div>
        <a href="/">Главная</a>
    </div>
</div>
</body>
</html>