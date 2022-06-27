<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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

        h3 {
            font-size: 28px;
            line-height: 36px;
            font-weight: 700;
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
            font-size: 24px;
            text-align: center;
            margin: 20px;
        }

        .searchForm {
            margin: 100px auto auto;
            width: 60%;
            border: 3px solid MediumSeaGreen;
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

<div class="searchForm" style="background-color:PaleGreen;">
    <h3>Поиск статей</h3>
    <button><a href="/">Главная</a></button>
</div>
</body>
</html>