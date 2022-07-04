<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Оформить заказ</title>
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


<h3 style="text-align: center">Оформляем заказ</h3>


<h4>Ваши товары</h4>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Название</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listLotStatusTrue" items="${requestScope.listLotStatusTrue}">
        <tr>
            <td>${listLotStatusTrue.nameLot}</td>
            <td>${listLotStatusTrue.price}$</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h5>Предварительный итог: ${requestScope.sumPrice}$</h5>
<h6 style="color: #dc0606">Успей купить, пока тебя не опередили</h6>
<br><br>

<c:if test="${!requestScope.listLotStatusFalse.isEmpty()}">
    <h4>Товар покупается другим покупателем или уже был куплен</h4>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Название</th>
            <th>Цена</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="listLotStatusFalse" items="${requestScope.listLotStatusFalse}">
            <tr>
                <td>${listLotStatusFalse.nameLot}</td>
                <td>${listLotStatusFalse.price}$</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<form name="placeOrder" method="post" action="order?idBuyer=${param.idBuyer}">
    <input name="idBuyer" type="hidden" value="${param.idBuyer}">
    <button class="btn btn-outline-dark">Продолжить</button>
    <a class="btn btn-dark" type="button" href="basket?idBuyer=${param.idBuyer}&action=returnCatalogFromOrder">Вернуться
        в
        корзину</a>
</form>

</body>
</html>
