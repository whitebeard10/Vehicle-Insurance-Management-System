<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Underwriter-Policy Renewal</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	height: 100vh;
}

nav {
	background-color: #0c2d48;
	color: #b1d4e0;
	text-align: center;
	padding: 1rem;
	position: absolute;
	top: 0;
	width: 100%;
	z-index: 1000;
	margin-bottom: 15px;
}

.sidebar {
	margin-top: 90px;
	width: 15%;
	background-color: #0c2d4885;
	color: white;
	padding: 20px;
	height: 100%;
}

.sidebar h2 {
	margin-bottom: 20px;
}

.sidebar a {
	color: #050A30;
	font-weight: bold;
	text-decoration: none;
	display: block;
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.sidebar a:hover {
	background-color: #555;
}

.content {
	width: 85%;
	margin-top: 90px;
	flex-grow: 1;
	padding: 20px;
	background-color: #f0f0f0;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
}

.form-group input {
	width: 100%;
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.form-group input[type="submit"] {
	background-color: #189AB4;
	color: white;
	cursor: pointer;
}

.form-group input[type="submit"]:hover {
	background-color: #45a049;
}

.error {
	color: red;
	font-size: 0.9em;
	margin-top: 5px;
}

.updated-details {
	margin-top: 20px;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.updated-details h3 {
	margin-top: 0;
}

.updated-details p {
	margin: 5px 0;
}
</style>
</head>
<body>
	<nav>
		<h1>Welcome to Insurance Policy Management</h1>
	</nav>
	<div class="sidebar">
		<h2>Underwriter Menu</h2>
		<a href="underwriterLanding.jsp">Create Insurance</a> <a
			href="#welcome">Renew Policy</a> <a href="underwriterLanding3.jsp">Change
			Policy Type</a> <a href="underwriterLanding4.jsp">View Policy</a> <a
			href="index.html">Logout</a>
	</div>

	<div class="content" id="content">
		<h2 id="welcome">Renew Policy</h2>
		<form id="renewPolicyForm" action="InsurenceServlet" method="POST"
			onsubmit="return validateAndConfirm()">
			<div class="form-group">
				<label for="policyID">Policy ID:</label> <input type="text"
					id="policyID" name="policyID" required /> <span id="policyIDError"
					class="error"></span>
			</div>
			<div class="form-group">
				<input type="submit" name="action" value="Renew Policy" />
			</div>
		</form>

		<%
		Integer renewPolicyID = (Integer) request.getAttribute("renewPolicyID");
		String renewedCustomerName = (String) request.getAttribute("renewedCustomerName");
		String renewedUpdatedType = (String) request.getAttribute("renewedUpdatedType");
		String renewedExpirationDate = (String) request.getAttribute("renewedExpirationDate");
		Double renewedPremiumAmount = (Double) request.getAttribute("renewedPremiumAmount");

		if (renewPolicyID != null) {
		%>
		<div class="updated-details">
			<h3>Policy Renewal Successful</h3>
			<p>
				<strong>Policy ID:</strong>
				<%=renewPolicyID%></p>
			<p>
				<strong>Customer Name:</strong>
				<%=renewedCustomerName%></p>
			<p>
				<strong>Insurance Type:</strong>
				<%=renewedUpdatedType%></p>
			<p>
				<strong>Expiration Date:</strong>
				<%=renewedExpirationDate%></p>
			<p>
				<strong>New Premium Amount:</strong>
				<%=renewedPremiumAmount%></p>
		</div>
		<%
		} else if(request.getAttribute("RenewerrorMessage")!=null) {
		%>
		<center style="color: red;">
			<%=request.getAttribute("RenewerrorMessage")%>
		</center>
		<%
		}
		%>
	</div>

	<script>
		function validateAndConfirm() {
			const policyID = document.getElementById("policyID").value;
			const policyIDError = document.getElementById("policyIDError");
			const policyIDRegex = /^[0-9]+$/;

			if (!policyIDRegex.test(policyID)) {
				policyIDError.textContent = "Policy ID must be a numeric value.";
				return false;
			} else {
				policyIDError.textContent = "";
			}

			const confirmation = confirm("Are you sure you want to renew this policy?\nThe premium amount will be increased by 5%.");
			return confirmation;
		}
	</script>
</body>
</html>
