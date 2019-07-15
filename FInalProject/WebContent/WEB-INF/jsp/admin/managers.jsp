<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Managers list</title>
</head>
<body>
	Add New
	<form action="./Controller?command=addNewManager" method="POST">
		<input name="login"> <input name="password" type="password">
		<select name="level">
			<option value="1">Administrator</option>
			<option value="2">Dispatcher</option>
		</select> <input type="submit">
	</form>
	All
	<c:forEach items="${requestScope.managers}" var="manager">
		<p>${manager}
			<button onclick='document.location.href="./Controller?command=managerEdit&id=${manager.getId()}"'>Mod</button>
		</p>
	</c:forEach>
</body>
</html>