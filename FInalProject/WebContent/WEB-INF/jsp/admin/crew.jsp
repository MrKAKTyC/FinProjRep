<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<title><fmt:message key="title.crew" /></title>
</head>
<body>
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#myModal">
		<fmt:message key="text.sendReport" />
	</button>
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">
						<fmt:message key="text.sendReport" />
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="./Controller?command=reportAdd" method="POST">
						<label><fmt:message key="text.reportReason" /></label><br />
						<textarea rows="10" cols="20" name="reason" class="form-control" required></textarea>
						<input type="hidden" value="${param.id }" name="flightID">
						<input class="btn btn-primary" type="submit"
							value="<fmt:message key="button.send" />">
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">
						<fmt:message key="button.close" />
					</button>
				</div>

			</div>
		</div>
	</div>
	<br/>
	<fmt:message key="text.assigned" />:
	<br />
	<table class="table table-striped">
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
						<input class="btn btn-danger" type="submit" value="X">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<fmt:message key="text.available" />:
	<table class="table table-striped">
		<tr>
			<th><fmt:message key="common_table.id" /></th>
			<th><fmt:message key="worker_table.name" /></th>
			<th><fmt:message key="worker_table.specialization" /></th>
			<th><fmt:message key="common_table.action" /></th>
		</tr>
		<c:forEach var="worker" items="${available }">
			<tr>
				<td>${worker.getWorkerId() }</td>
				<td>${worker.getWorkerName() }</td>
				<td><fmt:message key="specializations.${worker.getWorkerSpecialization() }" /></td>
				<td>
					<form
						action="./Controller?command=crewAddNew&flightId=${param.id }&workerId=${worker.getWorkerId() }"
						method="POST">
						<input class="btn btn-success" type="submit" value="+">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>