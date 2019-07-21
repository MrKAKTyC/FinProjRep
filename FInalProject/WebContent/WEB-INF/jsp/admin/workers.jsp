<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Workers list</title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#myModal"><fmt:message key="button.addNew" /></button>
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title"><fmt:message key="modal.newWorker" /></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="./Controller?command=workerAddNew" method="POST">
						<label for="name"><fmt:message key="worker_table.name" /></label>
						<input class="form-control" name="name">
						<label for="spec"><fmt:message key="worker_table.specialization" /></label>
						<select class="form-control" name="spec">
							<c:forEach var="spec" items="${specs}">
								<option value="${spec.getIntVal()}"><fmt:message key="specializations.${spec }" /></option>
							</c:forEach>
						</select>
						<input class="btn btn-primary" type="submit" value="<fmt:message key="button.add" />">
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="button.close" /></button>
				</div>
			</div>
		</div>
	</div>
	<max:workers workersList="${workers}"/>
</body>
</html>