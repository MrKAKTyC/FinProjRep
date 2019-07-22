<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="workersList" required="true" rtexprvalue="true"
	type="java.util.List"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${empty sessionScope.locale}">
	<c:set var="locale" value="en" scope="session" />
</c:if>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources" />

<table class="table table-striped">
	<tr>
		<th><fmt:message key="common_table.id" /></th>
		<th><fmt:message key="worker_table.name" /></th>
		<th><fmt:message key="worker_table.specialization" /></th>
		<th><fmt:message key="common_table.action" /></th>
	</tr>
	<c:forEach items="${workers}" var="worker">
		<tr>
			<td>${worker.workerId}</td>
			<td>${worker.workerName}</td>
			<td><fmt:message
					key="specializations.${worker.workerSpecialization}" /></td>
			<td>
				<button class="btn btn-primary"
					onclick='document.location.href="./Controller?command=workerEdit&id=${worker.workerId}"'>
					<fmt:message key="button.mod" />
				</button>
			</td>
		</tr>
	</c:forEach>
</table>