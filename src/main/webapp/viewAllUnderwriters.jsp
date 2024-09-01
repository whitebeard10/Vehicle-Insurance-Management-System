<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.bean.Underwriter" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin - View All Underwriters</title>
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

h2 {
    margin-bottom: 20px;
}

.table-container {
    margin-top: 20px;
    background-color: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

table {
    width: 100%;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 12px;
    text-align: left;
}

th {
    background-color: #0c2d48;
    color: #ffffff;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}
</style>
</head>
<body>
    <nav>
        <h1>Welcome to Insurance Policy Management</h1>
    </nav>
    <div class="sidebar">
        <h2>Admin Menu</h2>
        <a href="UnderwriterRegistration.jsp">Registration</a> 
        <a href="searchUnderwriter.jsp">Search</a> 
        <a href="updateUnderwriterPassword.jsp">Update</a>
        <a href="deleteUnderwriter.jsp">Delete</a> 
        <a href="AdminServlet?action=View All Underwriters">View Underwriters</a>
        <a href="viewAllVehicles.jsp">View Vehicles</a> 
        <a href="index.html">Logout</a>
    </div>

    <div class="content" id="content">
        <h2>View All Underwriters</h2>

        <!-- Display total number of underwriters -->
        <div class="table-container">
            <% 
                // Retrieve attributes from request
                List<Underwriter> underwriters = (List<Underwriter>) request.getAttribute("underwriters");
                Integer totalUnderwriters = (Integer) request.getAttribute("totalUnderwriters");
            %>

            <p>Total Underwriters: <strong><%= totalUnderwriters %></strong></p>

            <!-- Display underwriters in a table -->
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Date of Birth</th>
                        <th>Joining Date</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (underwriters != null && !underwriters.isEmpty()) {
                            for (Underwriter underwriter : underwriters) {
                    %>
                    <tr>
                        <td><%= underwriter.getUnderwriterId() %></td>
                        <td><%= underwriter.getName() %></td>
                        <td><%= underwriter.getDob() %></td>
                        <td><%= underwriter.getJoiningDate() %></td>
                        <td><%= underwriter.getPassword() %></td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5">No underwriters found.</td>
                        <td>
                        	<% String isFoundMessage = (String) request.getAttribute("errMessage"); %>
            <% if (isFoundMessage != null) { %>
                <p><strong style="color: red;"><%= isFoundMessage %></strong></p>
            <% } %>
                        </td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
