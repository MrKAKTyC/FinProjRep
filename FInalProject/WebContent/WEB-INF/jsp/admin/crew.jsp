<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crew managment</title>
</head>
<body>
	Assigned:
	<br />
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Specialization</th>
			<th>Action</th>
		</tr>
		<c:forEach var="crewMember" items="${assigned }">
			<tr>
				<td>${crewMember.getWorker().getWorkerId() }</td>
				<td>${crewMember.getWorker().getWorkerName() }</td>
				<td>${crewMember.getWorker().getWorkerSpecialization() }</td>
				<td>
					<form action="/Controller?command=crewDelete&id=${param.id }"
						method="POST">
						<input type="submit" value="X">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	Available:
	<table>
		<c:forEach var="worker" items="${available }">
			<tr>
				<td>${worker.getWorkerId() }</td>
				<td>${worker.getWorkerName() }</td>
				<td>${worker.getWorkerSpecialization() }</td>
				<td>
					<form
						action="/Controller?command=crewAddNew&flightId=${param.id }&workerId=${worker.getWorkerId() }"
						method="POST">
						<input type="submit" value="+">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	Send Report:
	<form action="/Controller?command=reportAdd" method="POST">
		<label>Problem description</label><br />
		<textarea rows="10" cols="20" name="reason"></textarea>
		<input type="hidden" value="${param.id }" name="flightID">
		<input type="submit" value="Send">
	</form>
</body>
</html>