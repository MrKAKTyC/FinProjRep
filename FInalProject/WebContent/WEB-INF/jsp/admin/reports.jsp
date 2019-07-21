<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Reports list</title>
	</head>
	<body>
		<%@include file="/WEB-INF/jspf/header.jspf"%>
		<table>
			<tr>
				<th><fmt:message key="common_table.id" /></th>
				<th><fmt:message key="report_table.description" /></th>
				<th><fmt:message key="common_table.action" /></th>
			</tr>
			<c:forEach var="report" items="${reports }">
				<tr>
					<td>${report.getId() }</td>
					<td>${report.getDescription() }</td>
					<td>
						<form action="./Controller?command=reportUpdate" method="POST">
							<input type="hidden" value="${report.getId() }" name="reportId">
							<input type="hidden" value="1" name="status"> <input
								type="submit" value="<fmt:message key="button.accept" />">
						</form>
						<form action="./Controller?command=reportUpdate" method="POST">
							<input type="hidden" value="${report.getId() }" name="reportId">
							<input type="hidden" value="0" name="status"> <input
								type="submit" value="<fmt:message key="button.reject" />">
						</form>
						<button
							onclick='document.location.href="./Controller?command=crewList&id=${report.getFlightId()}"'><fmt:message key="button.crew" /></button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>