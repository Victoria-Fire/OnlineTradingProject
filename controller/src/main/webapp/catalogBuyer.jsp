<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Каталог</title>
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
    <a class="active" href="catalog">Каталог</a>
<%--    <a href="stok">Акция</a>--%>
    <a href="admin">Админ</a>
</div>

<h2 style="text-align: center">Каталог</h2>

<table class="table table-hover">
    <thead>
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="lot" items="${requestScope.lots}" varStatus="status">
        <tr>
            <td>${lot.nameLot}</td>
            <td>${lot.description}</td>
            <td>${lot.price}$</td>
            <td>
                <c:choose>
                    <c:when test="${!requestScope.lotListIdOfBuyer.contains(lot.id)}">
                        <form name="addToBasketCatalog" method="post" action="basket">
                            <input name="id" type="hidden" value="${lot.id}">
                            <input name="buyerId" type="hidden" value="${param.idBuyer}">
                            <input name="action" type="hidden" value="addToBasketCatalog">
                            <button class="btn btn-secondary">Добавить в корзину</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form name="deleteFromBasketCatalog" method="post" action="basket">
                            <input name="id" type="hidden" value="${lot.id}">
                            <input name="buyerId" type="hidden" value="${param.idBuyer}">
                            <input name="action" type="hidden" value="deleteFromBasketCatalog">
                            <button class="btn btn-secondary">Удалить из корзины</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
