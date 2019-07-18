<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit flight</title>
</head>
<body>
${requestScope.flightBean.toString() }
	<form action="./Controller?command=flightUpdate" method="POST">
		<input type="hidden" name="id" value="${requestScope.flight.getNumber() }">
		<label for="name">Flight Name</label>
		<input name="name" value="${requestScope.flight.getFlightName() }">
		
		<label>Depature Airport</label>
		<select name="dept_air">
		<c:forEach var="airport" items="${airports}">
			<option value="${airport.getId()}" <c:if test="${airport.getId() eq requestScope.flight.getFromId()}">selected</c:if>>
				${airport.getAirportName() }
			</option>
		</c:forEach>
		</select>
		
		<label>Arival Airport</label>
		<select name="ariv_air">
		<c:forEach var="airport" items="${airports}">
			<option value="${airport.getId()}" <c:if test="${airport.getId() eq requestScope.flight.getFromId()}">selected</c:if>>
				${airport.getAirportName() }
			</option>
		</c:forEach>
		</select>
		
		<label>date</label>
		<input type="date" name="date" value="${flightBean.getDate() }">
		<label>time</label>
		<input type="time" name="time" value="${flightBean.getTime() }">
		
		<label>Flight status</label>
		<select name="status">
		<c:forEach var="status" items="${statuses}">
			<option value="${status.getId()}" <c:if test="${status eq requestScope.flight.getStatus()}">selected</c:if>>
				${status }
			</option>
		</c:forEach>
		</select>
		<input type="submit">
	</form>
	<form
		action="./Controller?command=flightDelete&id=${requestScope.flight.getNumber() }"
		method="POST">
		<input type="submit" value="delete">
	</form>
</body>
</html>