
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <title>Test servlet</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/js.js"></script>

</head>
<body style="height: 100%">

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li ><a href="/university">Home</a></li>
                <li class="passive"><a href="/university/un?action=">Groups</a></li>
                <li class="active"><a href="/university/group?action=">Student</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row">
        <div class="col-sm-1 sidenav">


        </div>
        <div class="col-sm-10 content">

            <div class="row box">
            <h4 class="text-center">Поиск группы</h4>
            <form  id="jform" class="form-inline" method="post" action="/university/find/" >
                <div class="form-group">
                    <input type="text" class="form-control" maxlength="17" name="groupNumber" id="groupNumber" placeholder="Номер группы">
                </div>
                <div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit"  name="findGroup" id="send" value="Найти" >
                </div>
            </form>
			<p><a href="group?action=insert">Add User</a></p>
                <table class="table table-striped table-bordered table-hover table-condensed">
                        <caption class="text-center">Список студентов</caption>
                        <thead>
                        <tr class="info">
                            <th style="vertical-align: middle"><h5 class="text-center">Имя</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Фамилия</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Контактная информация</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Номер группы</h5></th>
                           	<th style="vertical-align: middle"><h5 class="text-center">Действие</h5></th>
                        </tr>
                        </thead>
                        <c:forEach var="s" items="${students}">
                        <tbody>
                            <tr>
                                <th style="vertical-align: middle"><h6 class="text-center">${s.firstName}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${s.lastName}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${s.contactInformation}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${s.group.groupNumber}</h6></th>
                              	<td style="vertical-align: middle"><h6 class="text-center"><a href="group?action=delete&id=<c:out value="${s.id}"/>">Удалить</a></h6></td>

                           </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal">Посмотреть таблицу</button>
            </div>
        </div>
        <div class="col-sm-1 sidenav">

        </div>
    </div>
</div>

    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-body">
                    <table class="table table-striped table-bordered table-hover table-condensed">
                        <caption class="text-center">Откорректировать данные</caption>
                        <thead>
                        <tr class="info">
                            <th style="vertical-align: middle"><h5 class="text-center">Имя</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Фамилия</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Контактная информация</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Номер группы</h5></th>

                        </tr>
                        </thead>
                        <c:set var="s1" value="${student}"/>
                        <tbody>
                            <tr>
                                <th style="vertical-align: middle"><input class="text-center" value="${s1.firstName}"></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${s1.lastName}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${s1.contactInformation}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${s1.group.groupNumber}</h6></th>

                           </tr>

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>

        </div>
    </div>
<footer class="container-fluid text-center">
    <p>by Fordros</p>
</footer>
</body>
</html>
