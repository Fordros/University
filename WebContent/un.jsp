
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
                <li class="passive"><a href="/university">Home</a></li>
                <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="/university/un?action=">Groups
			        <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			        	<li><a href="un?action=">Все группы</a></li>
			          	<li><a href="un?action=insert">Добавить группу</a></li>
			        </ul>
			     </li>
                <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="/university/un?action=">Students
			        <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			        	<li><a href="group?action=">Все студенты</a></li>
			          	<li><a href="group?action=insert">Добавить студента</a></li>
			        </ul>
			     </li>
			     <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="/university/lecturer?action=">Lecturer
			        <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			        	<li><a href="lecturer?action=">Все преподы</a></li>
			          	<li><a href="lecturer?action=insert">Добавить препода</a></li>
			        </ul>
			     </li>
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

                <table class="table table-striped table-bordered table-hover table-condensed">
                        <caption class="text-center">Список групп</caption>
                        <thead>
                        <tr class="info">
                            <th style="vertical-align: middle"><h5 class="text-center">Номер группы</h5></th>
                           	<th style="vertical-align: middle"><h5 class="text-center">Действие</h5></th>
                        </tr>
                        </thead>
                        <c:forEach var="g" items="${groups}">
                        <tbody>
                            <tr>
                                <th style="vertical-align: middle"><h6 class="text-center">${g.groupNumber}</h6></th>
                              	<td style="vertical-align: middle"><h6 class="text-center"><a href="?action=delete&id=<c:out value="${g.id}"/>">Удалить</a></h6></td>
                           </tr>
                        </c:forEach>
                        </tbody>
                    </table>

        </div>
        <div class="col-sm-1 sidenav">

        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>by Fordros</p>
</footer>
</body>
</html>
