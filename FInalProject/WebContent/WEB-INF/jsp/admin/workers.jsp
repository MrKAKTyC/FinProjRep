<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Add New
	<form action="./Controller?command=workerAddNew" method="POST">
		<label for="name">Worker name</label> <input name="name"> <label>Worker
			specialization</label> <select name="spec">
			<c:forEach var="spec" items="${specs}">
				<option value="${spec.getIntVal()}">${spec }</option>
			</c:forEach>
		</select> <input type="submit">
	</form>
	All
	<c:forEach items="${requestScope.workers}" var="worker">
		<p>${worker}
			<button
				onclick='document.location.href="./Controller?command=workerEdit&id=${worker.getId()}"'>Mod</button>
		</p>
	</c:forEach>
</body>
</html>