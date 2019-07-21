<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="managersList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped">
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
			<td><button class="btn btn-primary"
					onclick='document.location.href="./Controller?command=managerEdit&id=${manager.id}"'>
					<fmt:message key="button.mod" />
				</button></td>
		</tr>
	</c:forEach>
</table>