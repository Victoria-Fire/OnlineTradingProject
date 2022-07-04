<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Заказ оплачен</title>
    <%@include file="/header.jsp" %>
</head>
<body>
<div class="bg-img">
    <div id="header" class="container">
        <div class="topnav">
            <a href="buyer">Авторизация</a>
            <a class="active" href="#">Корзина</a>
            <%--            <a href="search">Поиск</a>--%>
        </div>
    </div>
</div>

<div class="topnav">
    <a href="index.jsp">Главная страница</a>
    <a href="buyer">Пользователь</a>
    <a href="catalog">Каталог</a>
    <%--    <a href="stok">Акция</a>--%>
    <a href="admin">Админ</a>
</div>

<div style="text-align: center">
    <h3>Поздравляем! Вы успели купить чудесные товары :)</h3>
    <a class="btn btn-dark" type="button" href="catalogBuyer?idBuyer=${param.idBuyer}">Вернуться
        в
        каталог</a><br><br>
    <img src="image/01.jpg">
</div>
</body>
</html>
