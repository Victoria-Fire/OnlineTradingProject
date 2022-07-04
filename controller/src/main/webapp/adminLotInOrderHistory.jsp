<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Проданные лоты</title>
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

<h2 style="text-align: center">Проданные лоты</h2>

<c:url var="add" value="/adminChangeLot.jsp">
    <c:param name="action" value="addLot"/>
</c:url>

<div style="text-align: center">
    <form name="lotsForPurchase" method="post" action="adminLot">
        <button class="btn btn-outline-dark" name="action" value="lotsForPurchase">Для продажи</button>
        <a class="btn btn-outline-dark active" type="button" href="#">Продано</a>
    </form>
</div><br>

<a class="btn btn-dark" type="button" href="admin">Назад</a>

<br><br>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Описание</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lot" items="${lotListInOrderHistory}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${lot.nameLot}"/></td>
            <td><c:out value="${lot.description}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
