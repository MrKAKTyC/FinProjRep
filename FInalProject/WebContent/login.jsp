<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/imports.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title><fmt:message key="title.login" /></title>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/signin.css">
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
</head>
<body class="text-center">
	 <form name="loginForm" action="./Controller?command=login" method="POST" class="form-signin">
		<h1 class="h3 mb-3 font-weight-normal"><fmt:message key="text.plsSignIn" /></h1>
		<label for="inputEmail" class="sr-only"><fmt:message key="admin_table.login" /></label>
		<input type="text" name='login' id="inputEmail" class="form-control" placeholder="<fmt:message key="admin_table.login" />" required autofocus>
		<label for="inputPassword" class="sr-only"><fmt:message key="admin_table.password" /></label>
		<input type="password" name='password' id="inputPassword" class="form-control" placeholder="<fmt:message key="admin_table.password" />" required>
		<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="button.login" /></button>
	 </form>
</body>
</html>