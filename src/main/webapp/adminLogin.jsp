<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Login</title>
    <link rel="stylesheet" href="role-styles.css" />
    <script type="text/javascript">
        function validateForm() {
            var username = document.getElementById("username").value.trim();
            var password = document.getElementById("password").value.trim();

            if (username === "" || password === "") {
                alert("Both username and password must be filled out.");
                return false;
            }
            return true; // Allow the form to be submitted
        }
    </script>
</head>
<body>
    <nav>
        <div class="navbar">
            <h1>ABC Vehicle Insurance</h1>
        </div>
    </nav>
    <div class="container">
        <form action="AdminServlet" method="POST" id="adminLoginForm" onsubmit="return validateForm()">
            <h1>Admin Login</h1>
            <%
			if (request.getParameter("loginErrorMsg") != null) {
			%>
			<p align='center'>
				<font color='red'><%=request.getParameter("loginErrorMsg")%></font>
			</p>
			<%
			}
			%>
            <label for="username">User Name:</label>
            <input type="text" id="username" name="username" required />
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />
            <input type="submit" name="action"value="Login">
        </form>
    </div>
</body>
</html>
