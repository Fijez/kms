<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="ru">
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

        .registrationForm {
            margin: 100px auto auto;
            width: 60%;
            border: 3px solid MediumSeaGreen;
            padding: 10px;
            text-align: center;
        }
    </style>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div class="registrationForm" style="background-color:PaleGreen;">
    <%--@elvariable id="userForm" type="com.rtkit.fifth.element.kms.model.user.info.UserRegistrationInfo"--%>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="email" placeholder="Email" autofocus="true"/>
            <form:errors path="email"/>
                ${emailError}
        </div>
        <div>
            <form:input type="text" path="name" placeholder="Name" autofocus="true"/>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"/>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm" placeholder="Confirm password"/>
            <form:errors path="password"/>
                ${passwordError}
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
        <button><a href="/">Главная</a></button>
</div>
</body>
</html>