<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<th>Id</th>
<th>Description</th>
<th>Action</th>
</tr>
<c:forEach var="report" items=${reports }>
<tr>
<td>${report.getId() }</td>
<td>${report.getDescription() }</td>
<td><button>Accept</button><button>Reject</button><button>CrewView</button></td>
</tr>
</c:forEach>
</table>
</body>
</html>