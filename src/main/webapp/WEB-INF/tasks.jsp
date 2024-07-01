<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between align-items-center ">
			<h1>Welcome, ${userInSession.firstName}</h1>
			<a class="btn btn-success" href="/priority-high-low">Priority High-Low</a>
			<a class="btn btn-success" href="/priority-low-high">Priority Low-High</a>
			<a class="btn btn-danger" href="/logout" >Log out</a>
		</header>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Task</th>
						<th>Creator</th>
						<th>Assignee</th>
						<th>Priority</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td><a href="/tasks/${task.id}">${task.taskName}</a></td>
							<td>${task.creator.firstName} ${task.creator.lastName}</td>
							 <td>
                                <c:forEach items="${task.assignee}" var="user">
                                   <b>${user.firstName} ${user.lastName}</b>
                                </c:forEach>
                            </td>
							<td>${task.priority}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/tasks/new" class="btn btn-primary" style="width:180px">Create Task</a>
		</div>
	</div>	

</body>
</html>