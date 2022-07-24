<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html lang="eng">
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
        }

        h2 {
            font-size: 36px;
            line-height: 42px;
            font-weight: 700;
            color: white;
            margin: 1px;
            padding: 5px;
        }

        h3 {
            font-size: 30px;
            line-height: 42px;
            font-weight: 700;
            color: white;
            margin: 1px;
            padding: 5px;
        }

        h4 {
            font-size: 20px;
            line-height: 42px;
            font-weight: 700;
            color: white;
            margin: 1px;
            padding: 5px;
        }

        p {
            margin: 1px;
            padding: 5px;
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

        .article {
            background: rgba(255, 255, 255, 0.25);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(4px);
            -webkit-backdrop-filter: blur(4px);
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);

            margin: 100px auto auto;
            width: 80%;
            padding: 10px;
            text-align: center;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title> ${title} </title>
</head>
<body>
<div class="article">
    <h2>${title}</h2>
    <h3>${author}</h3>
    <h4 align="left">${date}</h4>
    <h4 align="left">${topic}</h4>
    <p align="left">${content}</p><br>
</div>
</body>
</html>