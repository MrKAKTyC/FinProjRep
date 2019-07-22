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
		<th><a
			href="./Controller?command=flights&action=sort&field=flightNumb">
				<fmt:message key="flights_table.number" />
		</a></th>
		<th><a
			href="./Controller?command=flights&action=sort&field=flightName">
				<fmt:message key="flights_table.name" />
		</a></th>
		<th><fmt:message key="flights_table.arrival" /></th>
		<th><fmt:message key="flights_table.depature" /></th>
		<th><fmt:message key="flights_table.date" /></th>
		<th><fmt:message key="flights_table.time" /></th>
		<th><fmt:message key="flights_table.status" /></th>
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
		</tr>
	</c:forEach>
</table>
