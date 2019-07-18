<%@ taglib prefix = "max" uri = "WEB-INF/taglib/maxtags.tld"%>
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
	<a href="login.jsp">login</a>
	<form action="./Controller" method="GET">
		Search by number
		<input type="hidden" name="command" value="flights">
		<input type="hidden" name="action" value="searchNumber">
		<input name="number">
		<input type="submit" value="Search">
	</form>
	<form action="./Controller" method="GET">
		Advanced search
		<input type="hidden" name="command" value="flights">
		<input type="hidden" name="action" value="searchFull">
		<input name="CountryFrom" placeholder="Country From">
		<input name="CityFrom" placeholder="City From">
		<input name="CountryTo" placeholder="Country To">
		<input name="CityTo" placeholder="City To">
		<input type="date" name="date">
		<input type="submit" value="Search">
	</form>
	<max:Hello flights="${requestScope.flights }"/>
</body>
</html>