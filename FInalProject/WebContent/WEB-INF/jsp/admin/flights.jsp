<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><fmt:message key="title.flights" /></title>
	</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<c:if test="${user.level == 'Administrator' }">
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
	  <fmt:message key="button.addNew" />
	</button>
	<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"><fmt:message key="modal.newFlight" /></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        	<form action="./Controller?command=flightAddNew" method="POST" name="newFlight" onsubmit="return checkDate()">
				<label for="name"><fmt:message key="flights_table.name" /></label>
				<input name="name" class="form-control" required>
				<label><fmt:message key="flights_table.depature" /></label>
				<select name="dept_air" class="form-control" required>
					<c:forEach var="airport" items="${airports}">
							<option value="${airport.getId()}">${airport.getAirportName() }</option>
					</c:forEach>
				</select>
				<label><fmt:message key="flights_table.arrival" /></label>
				<select name="ariv_air" class="form-control" required>
					<c:forEach var="airport" items="${airports}">
						<option value="${airport.getId()}">${airport.getAirportName() }</option>
					</c:forEach>
				</select>
				<label><fmt:message key="flights_table.date" /></label>
				<input type="date" name="date" class="form-control" required>
				<label><fmt:message key="flights_table.time" /></label>
				<input type="time" name="time" class="form-control" required>
				<label><fmt:message key="flights_table.status" /></label>
				<select	name="status" class="form-control" required>
					<c:forEach var="status" items="${statuses}">
						<option value="${status.getId()}"><fmt:message key="flights_table.status.${status }" /></option>
					</c:forEach>
				</select>
				<input type="submit" class="btn btn-primary" value="<fmt:message key="button.add" />">
			</form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="button.close" /></button>
      </div>

    </div>
  </div>
</div>
</c:if>
	<max:flights flightsList="${flights}"/>
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