<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
		Add New
		<form action="./Controller?command=addNewManager" method="POST">
			<input name="login"> <input name="password" type="password">
			<select name="level">
				<option value="1"><fmt:message key="Administrator" /></option>
				<option value="2"><fmt:message key="Dispatcher" /></option>
			</select> <input type="submit" value="<fmt:message key="button.add" />">
		</form>
		All
		<table>
			<tr>
				<th><fmt:message key="common_table.id" /></th>
				<th><fmt:message key="admin_table.login" /></th>
				<th><fmt:message key="admin_table.role" /></th>
				<th><fmt:message key="common_table.action" /></th>
			</tr>
			<c:forEach items="${managers}" var="manager">
				<tr>
					<td>${manager.id}</td>
					<td>${manager.login}</td>
					<td>${manager.level}</td>
					<td><button
							onclick='document.location.href="./Controller?command=managerEdit&id=${manager.id}"'><fmt:message key="button.mod" /></button></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
