<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit manager</title>
</head>
<body>
	<form action="./Controller?command=updateManager" method="POST">
	<input type="hidden" name='id' value=${requestScope.manager.getId() }>
		<input name="login" value="${requestScope.manager.getLogin()}">
		<select name="level">
			<c:forEach var="level" items="${requestScope.levelsList}">
				<option <c:if test="${level.getIntValue() eq requestScope.manager.getLevel().getIntValue()}">selected</c:if> value=${level.getIntValue() } > ${level }
				</option>
			</c:forEach>
		</select> <input type="submit" value="update">
	</form>
	<form
		action="./Controller?command=deleteManager&id=${requestScope.manager.getId() }"
		method="POST">
		<input type="submit" value="delete">
	</form>
</body>
</html>