<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.flight" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<form action="./Controller?command=flightUpdate" method="POST" name="newFlight" onsubmit="return checkDate()">
		<input type="hidden" name="id" value="${requestScope.flight.id }">
		<label for="name"><fmt:message key="flights_table.name" /></label>
		<input name="name" value="${requestScope.flight.name }" <c:if test="${user.level != 'Administrator' }">disabled</c:if> required>
		
		<label><fmt:message key="flights_table.depature" /></label>
		<select name="dept_air" <c:if test="${user.level != 'Administrator' }">disabled</c:if> required>
		<c:forEach var="airport" items="${airports}">
			<option value="${airport.getId()}" <c:if test="${airport.getId() eq requestScope.flight.from.getId()}">selected</c:if>>
				${airport.airportName }
			</option>
		</c:forEach>
		</select>
		
		<label><fmt:message key="flights_table.arrival" /></label>
		<select name="ariv_air" <c:if test="${user.level != 'Administrator' }">disabled</c:if> required>
		<c:forEach var="airport" items="${airports}">
			<option value="${airport.getId()}" <c:if test="${airport.getId() eq requestScope.flight.from.getId()}">selected</c:if>>
				${airport.airportName }
			</option>
		</c:forEach>
		</select>
		
		<label><fmt:message key="flights_table.date" /></label>
		<input type="date" name="date" value="${flightBean.getDate() }" <c:if test="${user.level != 'Administrator' }">disabled</c:if> required>
		
		<label><fmt:message key="flights_table.time" /></label>
		<input type="time" name="time" value="${flightBean.getTime() }" <c:if test="${user.level != 'Administrator' }">disabled</c:if> required>
		
		<label><fmt:message key="flights_table.status" /></label>
		<select name="status" required>
		<c:forEach var="status" items="${statuses}">
			<option value="${status.getId()}" <c:if test="${status eq requestScope.flight.getStatus()}">selected</c:if>>
				<fmt:message key="flights_table.status.${status }" />
			</option>
		</c:forEach>
		</select>
		<input class="btn btn-primary"type="submit" value="<fmt:message key="button.update" />">
	</form>
	<form
		action="./Controller?command=flightDelete&id=${requestScope.flight.id }"
		method="POST">
		<input class="btn btn-danger" type="submit" value="<fmt:message key="button.remove" />">
	</form>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	<script>
		function checkDate(){
			let date = document.forms["newFlight"]["date"].value;
			console.log(date);
			if(Date.parse(date) < Date.now()){
				console.log("")
				alert("Incorect date");
				return false;
			}
		}
	</script>
</body>
</html>