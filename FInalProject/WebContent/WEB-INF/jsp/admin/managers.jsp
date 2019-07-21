<%@ taglib prefix="max" uri="/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>All managers</title>
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
					<h4 class="modal-title"><fmt:message key="button.addNew" /></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="./Controller?command=addNewManager" method="POST">
						<fmt:message key="admin_table.login" />
						<input name="login" class="form-control">
						<fmt:message key="admin_table.password" />
						<input name="password" type="password" class="form-control">
						<fmt:message key="admin_table.role" />
						<select name="level" class="form-control">
							<option value="1"><fmt:message key="Administrator" /></option>
							<option value="2"><fmt:message key="Dispatcher" /></option>
						</select>
						<input type="submit" value="<fmt:message key="button.add" />">
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="button.close" /></button>
				</div>
			</div>
		</div>
	</div>
	<max:managers managersList="${managers}"/>
	</body>
</html>
