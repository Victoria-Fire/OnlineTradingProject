<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Корзина</title>
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

<h3 style="text-align: center">Ваша Корзина</h3>


<c:choose>
    <c:when test="${!requestScope.lotOfBuyer.isEmpty()}">
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

            <c:forEach var="lotOfBuyer" items="${requestScope.lotOfBuyer}" varStatus="status">
                <tr>
                    <td>${lotOfBuyer.nameLot}</td>
                    <td>${lotOfBuyer.description}</td>
                    <td>${lotOfBuyer.price}$</td>
                    <td>
                        <form name="deleteFromBasketBuyer" method="post" action="basket">
                            <input name="id" type="hidden" value="${lotOfBuyer.id}">
                            <input name="buyerId" type="hidden" value="${param.idBuyer}">
                            <input name="action" type="hidden" value="deleteFromBasketBuyer">
                            <button class="btn btn-secondary">Удалить</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form name="placeOrder" method="post" action="orderConfirmation?idBuyer=${param.idBuyer}">
            <input name="idBuyer" type="hidden" value="${param.idBuyer}">
            <button class="btn btn-outline-dark">Оформить заказ</button>
            <a class="btn btn-dark" type="button" href="catalogBuyer?idBuyer=${param.idBuyer}">Вернуться в каталог</a>
        </form>

    </c:when>
    <c:otherwise>
        <div style="text-align: center;">
            <h2>Пуста :(</h2>
            <form name="returnToCatalogBuyer" method="get" action="catalogBuyer">
                <input name="idBuyer" type="hidden" value="${param.idBuyer}">
                <button class="btn btn-secondary">Вернуться в каталог</button>
            </form><br><br>
            <img src="image/03.png">
        </div>
    </c:otherwise>
</c:choose>


</body>
</html>
