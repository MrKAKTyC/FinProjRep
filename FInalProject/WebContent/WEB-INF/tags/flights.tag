<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="flightsList" required="true" rtexprvalue="true"
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
		<th><fmt:message key="flights_table.number" /></th>
		<th><fmt:message key="flights_table.name" /></th>
		<th><fmt:message key="flights_table.arrival" /></th>
		<th><fmt:message key="flights_table.depature" /></th>
		<th><fmt:message key="flights_table.date" /></th>
		<th><fmt:message key="flights_table.time" /></th>
		<th><fmt:message key="flights_table.status" /></th>
		<c:if test="${not empty user }">
			<th><fmt:message key="common_table.action" /></th>
		</c:if>
	</tr>
	<c:forEach items="${flightsList}" var="flight">
		<tr>
			<td>${flight.getId()}</td>
			<td>${flight.getName()}</td>
			<td>${flight.getFrom()}</td>
			<td>${flight.getTo()}</td>
			<td>${flight.getDate()}</td>
			<td>${flight.getTime()}</td>
			<td><fmt:message key="flights_table.status.${flight.getStatus()}" /></td>
			<c:if test="${not empty user }">
				<td>
					<button class="btn btn-primary" onclick='document.location.href="./Controller?command=flightEdit&id=${flight.getId()}"'>
						<fmt:message key="button.mod" />
					</button>
					<button	class="btn btn-primary" onclick='document.location.href="./Controller?command=crewList&id=${flight.getId()}"'>
						<fmt:message key="button.crew" />
					</button>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
