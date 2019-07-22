<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/popper.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/jquery-ui.min.js"></script>
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/footer.css">
		<link rel="stylesheet" href="assets/css/jquery-ui.min.css">
		<title><fmt:message key="title.index" /></title>
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
				<input class="form-control" name="number" pattern="\d+" placeholder="<fmt:message key="text.numberSearch" />" required>
				<input class="btn btn-info" type="submit" value="<fmt:message key="button.search" />">
		  </form>
		</nav>
		<div class="d-flex">
		<div class="p-2 flex-grow-1">
			<max:userFlights flightsList="${flights}" />
		</div>
		<div class="p-2 bg-light">
			<form action="./Controller" method="GET">
				<fmt:message key="text.advancedSearch" />
				<input type="hidden" name="command" value="flights">
				<br/>
				<input type="hidden" name="action" value="searchFull">
				<br/>
				<input name="CountryFrom" placeholder="<fmt:message key="text.CountryFrom" />">
				<br/>
				<input name="CityFrom" placeholder="<fmt:message key="text.CityFrom" />">
				<br/>
				<input name="CountryTo" placeholder="<fmt:message key="text.CountryTo" />">
				<br/>
				<input name="CityTo" placeholder="<fmt:message key="text.CityTo" />">
				<br/>
				<input type="date" name="<fmt:message key="flights_table.date" />">
				<br/>
				<input class="btn btn-info" type="submit" value="<fmt:message key="button.search" />">
			</form>
		</div>
		</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
<script>
	var countryF = "%";
	var countryT = "%";
	

$( function() {
    $( "input[name=CountryFrom]" ).autocomplete({
      source: "./Controller?command=autocomplete&field=country",
      select: function( event, ui ) {
    	  countryF = ui.item.value;
      }
    });
    $( "input[name=CityFrom]" ).autocomplete({
        source: "./Controller?command=autocomplete&field=city&term=".countryF
      });
  } );
</script>
</body>
</html>