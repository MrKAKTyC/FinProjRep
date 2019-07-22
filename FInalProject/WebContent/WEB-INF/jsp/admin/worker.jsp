<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.worker" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<form action="./Controller?command=workerUpdate" method="POST">
		<input type="hidden" name='id' value=${requestScope.worker.getId() } required>
		<fmt:message key="worker_table.name" />
		<input name="name" value="${requestScope.worker.getName()}" required>
		<fmt:message key="worker_table.specialization" />
		<select name="spec" required>
			<c:forEach var="spec" items="${requestScope.specs}">
				<option value="${spec.getIntVal()}" 
					<c:if test="${spec.getIntVal() eq requestScope.worker.getSpec()}">selected</c:if>>
					<fmt:message key="specializations.${spec }" />
				</option>
			</c:forEach>
		</select>
		<input class="btn btn-primary" type="submit" value="<fmt:message key="button.update" />">
		
	</form>
	<form
		action="./Controller?command=workerDelete&id=${requestScope.worker.getId() }"
		method="POST">
		<input class="btn btn-danger" type="submit" value="<fmt:message key="button.remove" />">
	</form>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>