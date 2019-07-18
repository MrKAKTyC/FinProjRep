<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flights List</title>
</head>
<body>
	Add New
	<form action="./Controller?command=flightAddNew" method="POST">
		<label for="name">Flight Name</label> <input name="name"> <label>Depature
			Airport</label> <select name="dept_air">
			<c:forEach var="airport" items="${airports}">
				<option value="${airport.getId()}">${airport.getAirportName() }</option>
			</c:forEach>
		</select> <label>Arival Airport</label> <select name="ariv_air">
			<c:forEach var="airport" items="${airports}">
				<option value="${airport.getId()}">${airport.getAirportName() }</option>
			</c:forEach>
		</select> <label>date</label> <input type="date" name="date"> <label>time</label>
		<input type="time" name="time"> <label>Flight status</label> <select
			name="status">
			<c:forEach var="status" items="${statuses}">
				<option value="${status.getId()}">${status }</option>
			</c:forEach>
		</select> <input type="submit">
	</form>
	All
	<%@include file="..\..\jspf\flightList.jspf"%>
</body>
</html>