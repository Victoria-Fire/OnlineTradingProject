<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Редактирование пользователя</title>
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

<c:choose>
    <c:when test="${param.action==\"addBuyer\"}">
        <h2 style="text-align: center">Добавить Пользователя</h2>

        <div style="text-align: center" class="container">
            <form name="addBuyer" method="post" action="buyer">
                <label>Имя:
                    <input name="nameBuyer" type="text" required placeholder="Имя">
                </label> <br/><br/>
                <label>Фамилия:
                    <input name="surnameBuyer" type="text" required placeholder="Фамилия">
                </label><br/><br/>

                <input name="action" type="hidden" value="addBuyer">
                <button class="btn btn-outline-dark">Сохранить</button>
                <a class="btn btn-dark" type="button" href="buyer">Отмена</a>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"updateBuyer\"}">
        <h2 style="text-align: center">Редактирование Пользователя</h2>

        <div style="text-align: center" class="container">
            <form name="updateBuyer" method="post" action="buyer">
                <input name="id" type="hidden" value="${param.id}">

                <label>Имя:
                    <input name="nameBuyer" type="text" required placeholder="Имя" value="${param.nameBuyer}">
                </label><br/><br/>

                <label>Фамилия:
                    <input name="surnameBuyer" type="text" required placeholder="Фамилия" value="${param.surnameBuyer}">
                </label><br/><br/>

                <input name="action" type="hidden" value="updateBuyer">
                <button class="btn btn-outline-dark">Сохранить</button>
                <a class="btn btn-dark" type="button" href="buyer">Отмена</a>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"deleteBuyer\"}">
        <h2 style="text-align: center">Удаление Пользователя</h2>

        <div style="text-align: center" class="container">
            <form name="deleteBuyer" method="post" action="buyer">
                <input name="id" type="hidden" value="${param.id}">

                <label>Имя:
                    <input name="nameBuyer" type="text" disabled value="${param.nameBuyer}">
                </label><br/><br/>

                <label>Фамилия:
                    <input name="surnameBuyer" type="text" disabled value="${param.surnameBuyer}">
                </label><br/><br/>

                <button class="btn btn-outline-dark" name="action" type="submit" value="deleteBuyer">Удалить</button>
                <button class="btn btn-dark" name="action" type="submit">Отмена</button>
            </form>
        </div>
    </c:when>
</c:choose>
</body>
</html>

