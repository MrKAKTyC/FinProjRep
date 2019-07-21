<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title }</title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	Assigned:
	<br />
	<table>
		<tr>
			<th><fmt:message key="common_table.id" /></th>
			<th><fmt:message key="worker_table.name" /></th>
			<th><fmt:message key="worker_table.specialization" /></th>
			<th><fmt:message key="common_table.action" /></th>
		</tr>
		<c:forEach var="crewMember" items="${assigned }">
			<tr>
				<td>${crewMember.getWorker().getWorkerId() }</td>
				<td>${crewMember.getWorker().getWorkerName() }</td>
				<td><fmt:message key="specializations.${crewMember.getWorker().getWorkerSpecialization() }" /></td>
				<td>
					<form action="./Controller?command=crewDelete&id=${crewMember.getId() }"
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
				<td><fmt:message key="specializations.${worker.getWorkerSpecialization() }" /></td>
				<td>
					<form
						action="./Controller?command=crewAddNew&flightId=${param.id }&workerId=${worker.getWorkerId() }"
						method="POST">
						<input type="submit" value="+">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	Send Report:
	<form action="./Controller?command=reportAdd" method="POST">
		<label>Problem description</label><br />
		<textarea rows="10" cols="20" name="reason"></textarea>
		<input type="hidden" value="${param.id }" name="flightID">
		<input type="submit" value="<fmt:message key="button.send" />">
	</form>
</body>
</html>