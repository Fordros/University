
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
   <title>University</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/jquery-2.2.3.min.js"></script>
    <script src="/resources/js/jquery.validate.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/js.js"></script>
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
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Groups
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="getAllGroups">Все группы</a></li>
                        <li><a href="addGroup">Добавить группу</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Students
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="getAllStudents">Все студенты</a></li>
                        <li><a href="addStudent">Добавить студента</a></li>
                        <li><a href="timetable?searchFor=2">Поиск расписания</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Lecturer
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="getAllLecturers">Все преподы</a></li>
                        <li><a href="addLecturer">Добавить препода</a></li>
                        <li><a href="timetable?searchFor=1">Поиск расписания</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="">Lesson
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="getAllLessons">Расписание занятий</a></li>
                        <li><a href="addLesson">Добавить занятие</a></li>
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

            <div class="row box">

                <table class="table table-striped table-bordered table-hover table-condensed">
                        <caption class="text-center">Список занятий</caption>
                        <thead>
                        <tr class="info">
                            <th style="vertical-align: middle"><h5 class="text-center">Номер группы</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">ФИО преподавателя</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Номер аудитории</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Тип занятия</h5></th>
                            <th style="vertical-align: middle"><h5 class="text-center">Дата и время</h5></th>
                            <th style="vertical-align: middle" colspan="2"><h5 class="text-center">Действие</h5></th>
                        </tr>
                        </thead>
                        <c:forEach var="l" items="${lessons}">
                        <tbody>
                            <tr>
                                <th style="vertical-align: middle"><h6 class="text-center">${l.group.groupNumber}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${l.lecturer.lastName}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${l.classroom.number}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${l.studiesTypes}</h6></th>
                                <th style="vertical-align: middle"><h6 class="text-center">${l.lessonTime}</h6></th>
                              	<td style="vertical-align: middle"><h6 class="text-center"><a href="/editLesson?id=<c:out value="${l.id}"/>">Изменить</a></h6></td>
                              	<td style="vertical-align: middle"><h6 class="text-center"><a href="/deleteLesson?id=<c:out value="${l.id}"/>">Удалить</a></h6></td>
                           </tr>
                        </c:forEach>
                        </tbody>
                    </table>

            </div>
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
