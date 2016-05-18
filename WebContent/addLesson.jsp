
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
			     <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="">Lesson
			        <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			        	<li><a href="lesson?action=">Расписание занятий</a></li>
			          	<li><a href="lesson?action=insert">Добавить занятие</a></li>
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
            <h4 class="text-center">Добавить занятие</h4>
            <form  id="jform" class="form-inline" method="post" action="lesson" name="frmAddLesson">

                <div class="form-group">
                    <select class="form-control" id="optionGroup" name="optionGroup">
						<c:forEach var="g" items="${groups}">
							<option value ="<c:out value="${g.id}"/>">${g.groupNumber}</option>
						</c:forEach>
					</select>
                </div>
                <div class="form-group">
                	<select class="form-control" id="optionLecturer" name="optionLecturer">
						<c:forEach var="l" items="${lecturers}">
							<option value ="<c:out value="${l.id}"/>">${l.lastName}</option>
						</c:forEach>
					</select>
                </div>
                <div class="form-group">
                    <select class="form-control" id="optionClassroom" name="optionClassroom">
						<c:forEach var="c" items="${classrooms}">
							<option value ="<c:out value="${c.id}"/>">${c.number}</option>
						</c:forEach>
					</select>
                </div>
				<div class="form-group">
					<select class="form-control" id="stydiesType" name="studiesType">
						  	<option value ="LECTURE">LECTURE</option>
						  	<option value ="TUTORIAL">TUTORIAL</option>
						  	<option value ="SEMINAR">SEMINAR</option>
						  	<option value ="LABORATORY_CLASS">LABORATORY_CLASS</option>
					</select>
			   </div>
			   <div class="form-group">
                    <input type="datetime-local" class="form-control" name="lessonTime" id="lessonTime" placeholder="Время занятия"/>
                </div>
				<div class="form-group">
                    <input class="form-control" class="btn btn-default" type="submit"  name="addLesson" id="send" value="Добавить" >
                </div>
            </form>

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