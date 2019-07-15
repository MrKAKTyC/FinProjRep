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
	<form action="./Controller?command=flightAddNew" method="POST">
		<label for="name">Flight Name</label>
		<input name="name">
		
		<label>Depature Airport</label>
		<select name="dept_air">
		<c:forEach var="airport" items="${airports}">
			<option value="${airport.getId()}">${airport.getAirportName() }</option>
		</c:forEach>
		</select>
		
		<label>Arival Airport</label>
		<select name="ariv_air">
		<c:forEach var="airport" items="${airports}">
			<option value="${airport.getId()}">${airport.getAirportName() }</option>
		</c:forEach>
		</select>
		
		<label>date</label>
		<input type="date" name="date">
		<label>time</label>
		<input type="time" name="time">
		
		<label>Flight status</label>
		<select name="status">
		<c:forEach var="status" items="${statuses}">
			<option value="${status.getId()}">${status }</option>
		</c:forEach>
		</select>
		<input type="submit">
	</form>
	All
	<c:forEach items="${requestScope.flights}" var="flight">
		<p>${flight}
			<button
				onclick='document.location.href="./Controller?command=flightEdit&id=${flight.getNumber()}"'>Mod
			</button>
			<button
				onclick='document.location.href="./Controller?command=crewList&id=${flight.getNumber()}"'>Crew
			</button>
		</p>
	</c:forEach>
</body>
</html>