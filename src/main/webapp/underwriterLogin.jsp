<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Underwriter Login</title>
<link rel="stylesheet" href="role-styles.css" />
</head>
<%
String errMessgae = (String) request.getAttribute("error_msg");
%>
<body>
	<nav>
		<div class="navbar">
			<h1>ABC Vehicle Insurance</h1>
		</div>
	</nav>
	<div class="container">
		<form id="underWriterLoginForm" action="InsurenceServlet" method="GET"
			onsubmit="return validateForm()">
			<%
			if (request.getParameter("error") != null) {
			%>
			<p align='center'>
				<font color='red'><%=request.getParameter("error")%></font>
			</p>
			<%
			}
			%>
			<h1>Underwriter Login</h1>
			<label for="username">User Name:</label> 
			<input type="text" id="username" name="username" required /> 
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required /> 
			
			<input type="submit" name="action" value="Login">
		</form>
	</div>
	<script src="underwriterLogin.js"></script>
</body>
</html>
