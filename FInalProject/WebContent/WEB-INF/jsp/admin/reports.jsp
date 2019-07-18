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
	<table>
		<tr>
			<th>Id</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<c:forEach var="report" items="${reports }">
			<tr>
				<td>${report.getId() }</td>
				<td>${report.getDescription() }</td>
				<td>
					<form action="./Controller?command=reportUpdate" method="POST">
						<input type="hidden" value="${report.getId() }" name="reportId">
						<input type="hidden" value="1" name="status"> <input
							type="submit" value="Accept">
					</form>
					<form action="./Controller?command=reportUpdate" method="POST">
						<input type="hidden" value="${report.getId() }" name="reportId">
						<input type="hidden" value="0" name="status"> <input
							type="submit" value="Reject">
					</form>
					<button
						onclick='document.location.href="./Controller?command=crewList&id=${report.getFlightId()}"'>CrewView</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>