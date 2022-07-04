<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Редактирование лота</title>
    <%@include file="/header.jsp" %>
</head>
<body>
<div class="bg-img">
    <div id="header" class="container">
        <div class="topnav">
            <a href="buyer">Авторизация</a>
            <a href="#">Корзина</a>
<%--            <a href="search">Поиск</a>--%>
        </div>
    </div>
</div>

<div class="topnav">
    <a href="index.jsp">Главная страница</a>
    <a href="buyer">Пользователь</a>
    <a href="catalog">Каталог</a>
<%--    <a href="stok">Акция</a>--%>
    <a class="active" href="admin">Админ</a>
</div>

        <h2 style="text-align: center">Удаление Лота</h2>

        <div style="text-align: center" class="container">
            <form name="deleteLot" method="post" action="adminLot">
                <input name="id" type="hidden" value="${param.id}">
                <h5 style="color: #dc0606">Этот лот содержится в корзине покупателей!</h5>
                <h4 style="color: maroon">Вы уверены, что хотите удалить?</h4>

                <button class="btn btn-outline-dark" name="action" type="submit" value="deleteLotAnyway">Удалить все равно</button>
                <button class="btn btn-dark" name="action" type="submit">Отмена</button>
            </form>
        </div>
</body>
</html>
