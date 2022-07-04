<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Пользователь</title>
    <%@include file="/header.jsp" %>
</head>
<body>
<div class="bg-img">
    <div id="header" class="container">
        <div class="topnav">
            <a class="active" href="buyer">Авторизация</a>
            <a href="#">Корзина</a>
<%--            <a href="search">Поиск</a>--%>
        </div>
    </div>
</div>

<div class="topnav">
    <a href="index.jsp">Главная страница</a>
    <a class="active" href="buyer">Пользователь</a>
    <a href="catalog">Каталог</a>
<%--    <a href="stok">Акция</a>--%>
    <a href="admin">Админ</a>
</div>

<h2 style="text-align: center">Список пользователей</h2>

<c:url var="add" value="/changeBuyer.jsp">
    <c:param name="action" value="addBuyer"/>
</c:url>
<a class="btn btn-outline-dark" type="button" href="${add}">Добавить пользователя</a>
<br><br>
<table class="table table-hover">
    <thead>
    <tr>
        <th>№</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Перейти в каталог</th>
        <th>Корзина</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="buyer" items="${buyerList}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${buyer.nameBuyer}"/></td>
            <td><c:out value="${buyer.surnameBuyer}"/></td>
            <td>
                <c:url var="catalogBuyer" value="catalogBuyer">
                    <c:param name="idBuyer" value="${buyer.id}"/>
                    <c:param name="nameBuyer" value="${buyer.nameBuyer}"/>
                    <c:param name="surnameBuyer" value="${buyer.surnameBuyer}"/>
                </c:url>
                <a type="button" class="btn btn-secondary" href="${catalogBuyer}">Каталог</a>
            </td>
            <td>
                <c:url var="basketBuyer" value="basket">
                    <c:param name="idBuyer" value="${buyer.id}"/>
                    <c:param name="nameBuyer" value="${buyer.nameBuyer}"/>
                    <c:param name="surnameBuyer" value="${buyer.surnameBuyer}"/>
                </c:url>
                <a type="button" class="btn btn-secondary" href="${basketBuyer}">Корзина</a>
            </td>
            <td>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        Выбрать
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li>
                            <c:url var="update" value="/changeBuyer.jsp">
                                <c:param name="id" value="${buyer.id}"/>
                                <c:param name="nameBuyer" value="${buyer.nameBuyer}"/>
                                <c:param name="surnameBuyer" value="${buyer.surnameBuyer}"/>
                                <c:param name="action" value="updateBuyer"/>
                            </c:url>
                            <a class="dropdown-item" href="${update}">Редактировать</a>
                        </li>

                        <li>
                            <c:url var="delete" value="/changeBuyer.jsp">
                                <c:param name="id" value="${buyer.id}"/>
                                <c:param name="nameBuyer" value="${buyer.nameBuyer}"/>
                                <c:param name="surnameBuyer" value="${buyer.surnameBuyer}"/>
                                <c:param name="action" value="deleteBuyer"/>
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
