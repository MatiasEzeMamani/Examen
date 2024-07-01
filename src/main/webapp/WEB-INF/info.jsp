<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Task: ${task.taskName}</h1>
		<h3>Creator: ${task.creator.firstName} ${task.creator.lastName}</h3>
		<h3>Assignees:</h3>
        <ul>
            <c:forEach items="${task.assignee}" var="user">
                <li>${user.firstName} ${user.lastName}</li>
            </c:forEach>
        </ul>
		<h3>Priority: ${task.priority}</h3>
		<div class="d-flex gap-3">	
			<a href="/edit/${task.id}" class="btn btn-secondary ">Edit</a>
			<form action="/delete/${task.id}" method="post"">
				<input type="hidden" name="_method" value="DELETE" >
				<input type="submit" value="Delete" class="btn btn-danger " >
			</form>
		</div>
	</div>
</body>
</html>