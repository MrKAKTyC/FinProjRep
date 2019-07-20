<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit worker</title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<form action="./Controller?command=workerUpdate" method="POST">
		<input type="hidden" name='id' value=${requestScope.worker.getId() }>
		<input name="name" value="${requestScope.worker.getName()}">
		<select name="spec">
			<c:forEach var="spec" items="${requestScope.specs}">
				<option value="${spec.getIntVal()}" <c:if test="${spec.getIntVal() eq requestScope.worker.getSpec()}">selected</c:if>>${spec }</option>
			</c:forEach>
		</select>
		<input type="submit" value="<fmt:message key="button.update" />">
		
	</form>
	<form
		action="./Controller?command=workerDelete&id=${requestScope.worker.getId() }"
		method="POST">
		<input type="submit" value="<fmt:message key="button.remove" />">
	</form>
</body>
</html>