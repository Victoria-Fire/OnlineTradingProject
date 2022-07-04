<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Покупка</title>
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

<h3 style="text-align: center">Покупка</h3>

<h4>Ваши товары</h4>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Название</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listLotForPurchase" items="${requestScope.listLotForPurchase}">
        <tr>
            <td>${listLotForPurchase.nameLot}</td>
            <td>${listLotForPurchase.price}$</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h5>Окончательный итог: ${requestScope.sumPrice}$</h5><br>

<form name="buyLot" method="post" action="purchase?idBuyer=${param.idBuyer}">
    <input name="idBuyer" type="hidden" value="${param.idBuyer}">
    <button class="btn btn-outline-dark">Купить</button>
    <a class="btn btn-dark" type="button" href="basket?idBuyer=${param.idBuyer}&action=returnCatalogFromPurchase">Вернуться
        в
        корзину</a>
</form>
</body>
</html>
