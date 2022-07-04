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

<h2 style="text-align: center">Редактирование лотов</h2>

<c:url var="add" value="/adminChangeLot.jsp">
    <c:param name="action" value="addLot"/>
</c:url>

<div style="text-align: center">
    <form name="lotsInOrderHistory" method="post" action="adminLot">
        <a class="btn btn-outline-dark active" type="button" href="#">Для продажи</a>
        <button class="btn btn-outline-dark" name="action" value="lotsInOrderHistory"> Продано</button>
    </form>
</div><br>

<a class="btn btn-outline-dark" type="button" href="${add}">Добавить лот</a>
<a class="btn btn-dark" type="button" href="admin">Назад</a>

<br><br>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Статус</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lot" items="${lotList}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${lot.nameLot}"/></td>
            <td><c:out value="${lot.description}"/></td>
            <td><c:out value="${lot.price}$"/></td>
            <td><c:choose>
                <c:when test="${lot.status==\"true\"}">
                    <div class="form-check">
                        <input style="background-color: black" class="form-check-input" type="checkbox" value="" id="flexCheckCheckedDisabled" checked disabled>
                        <label class="form-check-label" for="flexCheckCheckedDisabled">
                            В продаже
                        </label>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDisabled" disabled>
                        <label class="form-check-label" for="flexCheckDisabled">
                            Не в продаже
                        </label>
                    </div>
                </c:otherwise>
            </c:choose></td>
            <td>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        Выбрать
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li>
                            <c:url var="update" value="/adminChangeLot.jsp">
                                <c:param name="id" value="${lot.id}"/>
                                <c:param name="nameLot" value="${lot.nameLot}"/>
                                <c:param name="description" value="${lot.description}"/>
                                <c:param name="startDate" value="${lot.startDate}"/>
                                <c:param name="endDate" value="${lot.endDate}"/>
                                <c:param name="startPrice" value="${lot.startPrice}"/>
                                <c:param name="endPrice" value="${lot.endPrice}"/>
                                <c:param name="status" value="${lot.status}"/>
                                <c:param name="action" value="updateLot"/>
                            </c:url>
                            <a class="dropdown-item" href="${update}">Редактировать</a>
                        </li>

                        <li>
                            <c:url var="delete" value="/adminChangeLot.jsp">
                                <c:param name="id" value="${lot.id}"/>
                                <c:param name="nameLot" value="${lot.nameLot}"/>
                                <c:param name="description" value="${lot.description}"/>
                                <c:param name="action" value="deleteLot"/>
                            </c:url>
                            <a class="dropdown-item" href="${delete}">Удалить</a>
                    </ul>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
