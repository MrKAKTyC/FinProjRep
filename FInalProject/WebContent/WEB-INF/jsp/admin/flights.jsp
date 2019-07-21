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
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
	  Add New
	</button>
	<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
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
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
	<max:flights flightsList="${requestScope.flights}"/>
</body>