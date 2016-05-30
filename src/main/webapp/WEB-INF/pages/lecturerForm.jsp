
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
            <h4 class="text-center">Добавить преподавателя</h4>
            <form:form  id="lecturerRegisterForm" class="form-inline" method="post" modelAttribute="lecturer" action="saveLecturer" name="frmAddLecturer">

                <div class="form-group">
                 	<form:hidden path="id" value="${lecturer.id}"/>
                    <form:input type="text" class="form-control" path="firstName" maxlength="50" placeholder="Имя" value="${lecturer.firstName}"/>
                </div>
                <div class="form-group">
                    <form:input type="text" class="form-control" path="lastName" maxlength="50" name="lastName" id="lastName" placeholder="Фамилия" value="${lecturer.lastName}"/>
                </div>
                <div class="form-group">
                    <form:input type="text" class="form-control" path="contactInformation" maxlength="255" name="contactInformation" id="contactInformation" placeholder="Контакт. инфо."
                    value="${lecturer.contactInformation}"/>
                </div>
				<div class="form-group">
				<select class="form-control" id="qualification" name="qualification">
				  <option value ="FULL_PROFESSOR">FULL_PROFESSOR</option>
				  <option value ="ASSOCIATE_PROFESSOR">ASSOCIATE_PROFESSOR</option>
				  <option value ="SENIOR_RESEARCHER">SENIOR_RESEARCHER</option>
				  <option value ="ASSISTANT_PROFESSOR">ASSISTANT_PROFESSOR</option>
				  <option value ="SENIOR_LECTURER">SENIOR_LECTURER</option>
				  <option value ="LECTURER">LECTURER</option>
				  <option value ="SENIOR_RESEARCH">SENIOR_RESEARCH</option>
				  <option value ="RESEARCH">RESEARCH</option>
				</select>
			   </div>
				<div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit"  name="saveLecturer" id="send" value="Добавить" >
                </div>
            </form:form>

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
