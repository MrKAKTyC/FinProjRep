<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "max" uri = "/WEB-INF/taglib/maxtags.tld"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit manager</title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<form action="./Controller?command=updateManager" method="POST">
	<input type="hidden" name='id' value=${requestScope.manager.getId() }>
		<input name="login" value="${requestScope.manager.getLogin()}">
		<select name="level">
			<c:forEach var="level" items="${requestScope.levelsList}">
				<option <c:if test="${level.getIntValue() eq requestScope.manager.getLevel().getIntValue()}">selected</c:if> value=${level.getIntValue() } > ${level }
				</option>
			</c:forEach>
		</select> <input type="submit" value="<fmt:message key="button.update" />">
	</form>
	<form
		action="./Controller?command=deleteManager&id=${requestScope.manager.getId() }"
		method="POST">
		<input type="submit" value="<fmt:message key="button.remove" />">
	</form>
</body>
</html>