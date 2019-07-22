<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.managers" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<form action="./Controller?command=updateManager" method="POST" name="newManager" onsubmit="return validate()">
		<input type="hidden" name='id' value=${requestScope.manager.getId() } required>
		<fmt:message key="admin_table.login" />
		<input name="login" value="${requestScope.manager.getLogin()}" required>
		<fmt:message key="admin_table.role" />
		<select name="level" required>
			<c:forEach var="level" items="${requestScope.levelsList}">
				<option 
					<c:if test="${level.getIntValue() eq requestScope.manager.getLevel().getIntValue()}">selected</c:if>
					value=${level.getIntValue() } >
					<fmt:message key="${level }" />
				</option>
			</c:forEach>
		</select>
		<input class="btn btn-primary" type="submit" value="<fmt:message key="button.update" />">
	</form>
	<form
		action="./Controller?command=deleteManager&id=${requestScope.manager.getId() }"
		method="POST">
		<input class="btn btn-danger" type="submit" value="<fmt:message key="button.remove" />">
	</form>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>