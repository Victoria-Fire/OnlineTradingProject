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

<c:choose>
    <c:when test="${param.action==\"addLot\"}">
        <h2>Добавить Лот</h2>

        <div class="container">
            <form name="addLot" method="post" action="adminLot">
                <label>Название:
                    <input name="nameLot" type="text" required placeholder="Название">
                </label> <br/><br/>
                <label>Описание:
                    <input name="description" type="text" required placeholder="Описание">
                </label><br/><br/>
                <label>Начальная дата:
                    <input name="startDate" type="date" required min="2000-01-01" max="2099-12-31">
                </label><br/><br/>
                <label>Конечная дата:
                    <input name="endDate" type="date" required min="2000-01-01" max="2099-12-31">
                </label><br/><br/>
                <label>Начальная цена:
                    <input name="startPrice" type="text" title="Используйте формат: 00.00" required pattern="\d+\.\d{2}"
                           placeholder="00.00">
                </label><br/><br/>
                <label>Конечная цена:
                    <input name="endPrice" type="text" title="Используйте формат: 00.00" required pattern="\d+\.\d{2}"
                           placeholder="00.00">
                </label><br/><br/>
                <div class="form-check form-switch">
                    <input name="status" class="form-check-input" type="checkbox" id="statusForAddLot" value="true">
                    <label class="form-check-label" for="statusForAddLot">В продажу</label><br/><br/>
                </div>

                <input name="action" type="hidden" value="addLot">
                <button class="btn btn-outline-dark">Сохранить</button>
                <a class="btn btn-dark" type="button" href="adminLot">Отмена</a>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"updateLot\"}">
        <h2>Редактирование лота</h2>

        <div class="container">
            <form name="updateLot" method="post" action="adminLot">
                <input name="id" type="hidden" value="${param.id}">

                <label>Название:
                    <input name="nameLot" type="text" required placeholder="Название" value="${param.nameLot}">
                </label><br/><br/>

                <label>Описание:
                    <input name="description" type="text" required placeholder="Описание" value="${param.description}">
                </label><br/><br/>

                <label>Начальная дата:
                    <input name="startDate" type="date" required min="2000-01-01" max="2099-12-31"
                           value="${param.startDate}">
                </label><br/><br/>

                <label>Конечная дата:
                    <input name="endDate" type="date" required min="2000-01-01" max="2099-12-31"
                           value="${param.endDate}">
                </label><br/><br/>

                <label>Начальная цена:
                    <input name="startPrice" type="text" title="Используйте формат: 00.00" required pattern="\d+\.\d{2}"
                           placeholder="00.00" value="${param.startPrice}">
                </label><br/><br/>

                <label>Конечная цена:
                    <input name="endPrice" type="text" title="Используйте формат: 00.00" required pattern="\d+\.\d{2}"
                           placeholder="00.00" value="${param.endPrice}">
                </label><br/><br/>

                <c:choose>
                    <c:when test="${param.status==\"true\"}">
                        <div class="form-check form-switch">
                            <input name="status" class="form-check-input" type="checkbox" id="statusForUpdateLotTrue"
                                   value="true" checked>
                            <label class="form-check-label" for="statusForUpdateLotTrue">В продажу</label><br/><br/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check form-switch">
                            <input name="status" class="form-check-input" type="checkbox" id="statusForUpdateLotFalse"
                                   value="true">
                            <label class="form-check-label" for="statusForUpdateLotFalse">В продажу</label><br/><br/>
                        </div>
                    </c:otherwise>
                </c:choose>

                <input name="action" type="hidden" value="updateLot">
                <button class="btn btn-outline-dark">Сохранить</button>
                <a class="btn btn-dark" type="button" href="adminLot">Отмена</a>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"deleteLot\"}">
        <h2 style="text-align: center">Удаление Лота</h2>

        <div style="text-align: center" class="container">
            <form name="deleteLot" method="post" action="adminLot">
                <input name="id" type="hidden" value="${param.id}">

                <label>Название:
                    <input name="nameLot" type="text" disabled value="${param.nameLot}">
                </label><br/><br/>

                <label>Описание:
                    <input name="description" type="text" disabled value="${param.description}">
                </label><br/><br/>

                <button class="btn btn-outline-dark" name="action" type="submit" value="deleteLot">Удалить</button>
                <button class="btn btn-dark" name="action" type="submit">Отмена</button>
            </form>
        </div>
    </c:when>
</c:choose>
</body>
</html>
