<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form:form action="/create_task" method="post" modelAttribute="task" >
			<div class="mb-3">
                <form:label path="taskName">Task:</form:label>
                <form:input path="taskName" class="form-control" />
                <form:errors path="taskName" class="text-danger" />
            </div>
            <div class="mb-3">
            	<form:label path="assignee">Assignee:</form:label>
            	<form:select path="assignee" class="form-select" multiple="false">
            		<c:forEach items="${users}" var="user">
            			<form:option value="${user.id}">${user.firstName} ${user.lastName}</form:option>
            		</c:forEach>
            	</form:select>
            </div>
            <div class="mb-3">
                <form:label path="priority">Priority:</form:label>
                <form:select path="priority" class="form-select">
                    <c:forEach items="${priority}" var="priority">
                        <form:option value="${priority}">${priority}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <input type="submit" class="btn btn-success mt-3" value="Create" >
            <a href="/tasks" class="btn btn-danger mt-3" >Cancel</a>
		</form:form>
	</div>
</body>
</html>