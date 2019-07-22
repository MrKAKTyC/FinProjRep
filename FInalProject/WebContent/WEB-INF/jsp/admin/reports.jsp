<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title><fmt:message key="title.reports" /></title>
	</head>
	<body>
		<%@include file="/WEB-INF/jspf/header.jspf"%>
		<table class="table table-striped">
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
						<button
							class="btn btn-success"
							onclick='updateStatus(${report.getId() }, 1)'>
							<fmt:message key="button.accept" />
						</button>
						<button
							class="btn btn-danger"
							onclick='updateStatus(${report.getId() }, 0)'>
							<fmt:message key="button.reject" />
						</button>
						<button
							class="btn btn-primary"
							onclick='document.location.href="./Controller?command=crewList&id=${report.getFlightId()}"'>
							<fmt:message key="button.crew" />
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@include file="/WEB-INF/jspf/footer.jspf"%>
		<script>
		
		function updateStatus(repId, newStatus){
			$.post( "./Controller?command=reportUpdate",
					{ 
						reportId: repId,
						status: newStatus
					},
					function( data ) {
							document.location.reload(true);
						}
			);
		}
		</script>
	</body>
</html>