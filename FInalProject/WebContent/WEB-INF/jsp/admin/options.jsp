<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin panel</title>
</head>
<body>
	<button
		onclick="document.location.href='./Controller?command=flightList'">Show
		flights</button>
	<button
		onclick="document.location.href='./Controller?command=workerList'">Show
		workers</button>
	<button
		onclick="document.location.href='./Controller?command=managerList'">Show
		managers</button>
	<button
		onclick="document.location.href='./Controller?command=reportList'">Show
		reports</button>
</body>
</html>