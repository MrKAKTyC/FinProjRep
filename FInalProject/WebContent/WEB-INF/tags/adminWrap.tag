<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title }</title>
</head>
<body>
	<nav>
		<button
			onclick="document.location.href='./Controller?command=flightList'">
			<fmt:message key="admin_menu.flights" />
		</button>
		<button
			onclick="document.location.href='./Controller?command=workerList'">
			<fmt:message key="admin_menu.workers" />
		</button>
		<button
			onclick="document.location.href='./Controller?command=managerList'">
			<fmt:message key="admin_menu.managers" />
		</button>
		<button
			onclick="document.location.href='./Controller?command=reportList'">
			<fmt:message key="admin_menu.reports" />
		</button>
		<button onclick="document.location.href='./Controller?command=logout'">
			<fmt:message key="admin_menu.logout" />
		</button>
	</nav>
	<div>
		<jsp:doBody />
	</div>
</body>
</html>