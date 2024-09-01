<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.bean.Policy" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Underwriter-View Policy</title>
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
.policy-details {
    margin-top: 20px;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.policy-details h3 {
    margin-top: 0;
    color: #0c2d48;
}
.policy-details p {
    margin: 10px 0;
}
.policy-details strong {
    color: #189AB4;
}
</style>
</head>
<body>
    <nav>
        <h1>Welcome to Insurance Policy Management</h1>
    </nav>
    <div class="sidebar">
        <h2>Underwriter Menu</h2>
        <a href="underwriterLanding.jsp">Create Insurance</a> 
        <a href="underwriterLanding2.jsp">Renew Policy</a> 
        <a href="underwriterLanding3.jsp">Change Policy Type</a> 
        <a href="#welcome">View Policy</a> 
        <a href="index.html">Logout</a>
    </div>

    <div class="content" id="content">
        <h2 id="welcome">View Policy</h2>
        <form id="viewPolicyForm" action="InsurenceServlet" method="POST"
            onsubmit="return validateForm()">
            <div class="form-group">
                <label for="policyID">Policy ID:</label> 
                <input type="text" id="policyID" name="vPolicyID" required /> 
                <span id="policyIDError" class="error"></span>
            </div>
            <div class="form-group">
                <input type="submit" name="action" value="View Policy" />
            </div>
        </form>

        <%
        Policy viewedPolicy = (Policy) request.getAttribute("viewedPolicy");
        if (viewedPolicy != null) {
        	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        %>
        <div class="policy-details">
            <h3>Policy Details</h3>
            <p><strong>Policy No:</strong> <%= viewedPolicy.getPolicyNo() %></p>
            <p><strong>Vehicle No:</strong> <%= viewedPolicy.getVehicleNo() %></p>
            <p><strong>Vehicle Type:</strong> <%= viewedPolicy.getVehicleType() %></p>
            <p><strong>Customer Name:</strong> <%= viewedPolicy.getCustomerName() %></p>
            <p><strong>Engine No:</strong> <%= viewedPolicy.getEngineNo() %></p>
            <p><strong>Chassis No:</strong> <%= viewedPolicy.getChasisNo() %></p>
            <p><strong>Phone No:</strong> <%= viewedPolicy.getPhoneNo() %></p>
            <p><strong>Insurance Type:</strong> <%= viewedPolicy.getInsuranceType() %></p>
            <p><strong>Premium Amount:</strong> <%= String.format("%.2f", viewedPolicy.getPremiumAmt()) %></p>
           <p><strong>From Date:</strong> <%= viewedPolicy.getFromDate().format(dateFormatter) %></p>
<p><strong>To Date:</strong> <%= viewedPolicy.getToDate().format(dateFormatter) %></p>
            <p><strong>Registered By(Underwriter ID):</strong> <%= viewedPolicy.getUnderwriterId() %></p>
        </div>
        <%
        } else if (request.getAttribute("ViewErrorMessage") != null) {
        %>
        <div class="error-message">
            <p><%= request.getAttribute("ViewErrorMessage") %></p>
        </div>
        <%
        }
        %>
    </div>

    <script>
        function validateForm() {
            const policyID = document.getElementById("policyID").value;
            const policyIDError = document.getElementById("policyIDError");
            const policyIDRegex = /^[0-9]+$/;

            if (!policyIDRegex.test(policyID)) {
                policyIDError.textContent = "Policy ID must be a numeric value.";
                return false;
            } else {
                policyIDError.textContent = "";
            }

            return true;
        }
    </script>
</body>
</html>