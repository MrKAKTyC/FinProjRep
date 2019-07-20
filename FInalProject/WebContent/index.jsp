<%@ taglib prefix = "max" uri = "WEB-INF/taglib/maxtags.tld"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<fmt:setLocale value="uk" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Max Airline</title>
	</head>
	<body>
		<nav>
			<a href="login.jsp"><fmt:message key="button.login" /></a>
			<form action="./Controller" method="GET">
				Search by number
				<input type="hidden" name="command" value="flights">
				<input type="hidden" name="action" value="searchNumber">
				<input name="number">
				<input type="submit" value="<fmt:message key="button.search" />">
			</form>
		</nav>
		<div>
			<form action="./Controller" method="GET">
				Advanced search
				<input type="hidden" name="command" value="flights">
				<input type="hidden" name="action" value="searchFull">
				<input name="CountryFrom" placeholder="Country From">
				<input name="CityFrom" placeholder="City From">
				<input name="CountryTo" placeholder="Country To">
				<input name="CityTo" placeholder="City To">
				<input type="date" name="date">
				<input type="submit" value="<fmt:message key="button.search" />">
			</form>
		</div>
		 <max:flights flightsList="${flights}"/>
	</body>
</html>