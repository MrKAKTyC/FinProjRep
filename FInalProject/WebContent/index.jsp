<%@ taglib prefix = "max" uri = "WEB-INF/taglib/maxtags.tld"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<fmt:setLocale value="uk" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/popper.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<title>Max Airline</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg bg-light justify-content-between">
		  <ul class="nav navbar-nav">
		    <li class="nav-item active">
		      <a class="nav-link" href="login.jsp"><fmt:message key="button.login" /></a>
		    </li>
		  </ul>
		  <form class="form-inline pull-xs-right" action="./Controller">
				<input type="hidden" name="command" value="flights">
				<input type="hidden" name="action" value="searchNumber">
				<input class="form-control" name="number" placeholder="Search by number">
				<input class="btn btn-info" type="submit" value="<fmt:message key="button.search" />">
		  </form>
		</nav>
		<div class="d-flex">
		<div class="p-2 flex-grow-1">
			<max:flights flightsList="${flights}" />
		</div>
		<div class="p-2 bg-light">
			<form action="./Controller" method="GET">
				Advanced search
				<input type="hidden" name="command" value="flights">
				<br/>
				<input type="hidden" name="action" value="searchFull">
				<br/>
				<input name="CountryFrom" placeholder="Country From">
				<br/>
				<input name="CityFrom" placeholder="City From">
				<br/>
				<input name="CountryTo" placeholder="Country To">
				<br/>
				<input name="CityTo" placeholder="City To">
				<br/>
				<input type="date" name="date">
				<br/>
				<input type="submit" value="<fmt:message key="button.search" />">
			</form>
		</div>
		</div>
</body>
</html>