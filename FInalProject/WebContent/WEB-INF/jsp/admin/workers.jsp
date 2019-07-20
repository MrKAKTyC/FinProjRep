<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Workers list</title>
	</head>
	<body>
		<%@include file="/WEB-INF/jspf/header.jspf"%>
		Add New
		<form action="./Controller?command=workerAddNew" method="POST">
			<label for="name">Worker name</label>
			<input name="name">
			<label>Worker specialization</label>
			<select name="spec">
				<c:forEach var="spec" items="${specs}">
					<option value="${spec.getIntVal()}"><fmt:message key="specializations.${spec }" /></option>
				</c:forEach>
			</select>
			<input type="<fmt:message key="button.send" />">
		</form>
		All
		<table>
			<tr>
				<th><fmt:message key="common_table.id" /></th>
				<th><fmt:message key="worker_table.name" /></th>
				<th><fmt:message key="worker_table.specialization" /></th>
				<th><fmt:message key="common_table.action" /></th>
			</tr>
			<c:forEach items="${requestScope.workers}" var="worker">
				<tr>
					<td>${worker.workerId}</td>
					<td>${worker.workerName}</td>
					<td><fmt:message key="specializations.${worker.workerSpecialization}" /></td>
					<td>
						<button onclick='document.location.href="./Controller?command=workerEdit&id=${worker.getWorkerId()}"'>
							<fmt:message key="button.mod" />
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>