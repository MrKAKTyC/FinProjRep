<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flights list</title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
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
		<select	name="status">
			<c:forEach var="status" items="${statuses}">
				<option value="${status.getId()}">${status }</option>
			</c:forEach>
		</select>
		<input type="submit">
	</form>
	All
	<max:flights flightsList="${requestScope.flights}"/>
</body>