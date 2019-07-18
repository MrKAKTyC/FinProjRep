<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit worker</title>
</head>
<body>
	<form action="./Controller?command=workerUpdate" method="POST">
		<input type="hidden" name='id' value=${requestScope.worker.getId() }>
		<input name="name" value="${requestScope.worker.getName()}">
		<select name="spec">
			<c:forEach var="spec" items="${requestScope.specs}">
				<option value="${spec.getIntVal()}" <c:if test="${spec.getIntVal() eq requestScope.worker.getSpec()}">selected</c:if>>${spec }</option>
			</c:forEach>
		</select> <input type="submit" value="update">
	</form>
	<form
		action="./Controller?command=workerDelete&id=${requestScope.worker.getId() }"
		method="POST">
		<input type="submit" value="delete">
	</form>
</body>
</html>