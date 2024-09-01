<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.bean.Underwriter" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin - Search Underwriter</title>
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
        height:100%;
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

      .form-group input,
      .form-group select {
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

      .result {
        margin-top: 20px;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      }

      .result p {
        margin: 0;
        font-size: 1.1em;
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
      <a href="#welcome">Search</a>
      <a href="updateUnderwriterPassword.jsp">Update</a>
      <a href="deleteUnderwriter.jsp">Delete</a>
      <a href="AdminServlet?action=View All Underwriters">View Underwriters</a>
      <a href="viewAllVehicles.jsp">View Vehicles</a>
      <a href="index.html">Logout</a>
    </div>

    <div class="content" id="content">
      <h2 id="welcome">Search Underwriter by ID</h2>
      <form
        id="searchUnderwriterForm"
        action="AdminServlet"
        method="GET"
        onsubmit="return validateForm()"
      >
        <div class="form-group">
          <label for="underwriterId">Underwriter ID:</label>
          <input type="text" id="underwriterId" name="underwriterId" required />
          <span id="underwriterIdError" class="error"></span>
        </div>
        <div class="form-group">
          <input type="submit" name="action" value="Search Underwriter" />
        </div>
      </form>

      <div class="result" id="result">
        <% 
          Underwriter underwriter = (Underwriter) request.getAttribute("underwriter");
          if (underwriter != null) {
        %>
          <p><strong>Underwriter ID:</strong> <%= underwriter.getUnderwriterId() %></p>
          <p><strong>Name:</strong> <%= underwriter.getName() %></p>
          <p><strong>Date of Birth:</strong> <%= underwriter.getDob() %></p>
          <p><strong>Joining Date:</strong> <%= underwriter.getJoiningDate() %></p>
          <p><strong>Password:</strong> <%= underwriter.getPassword() %></p>
        <% 
          } else if (request.getParameter("underwriterId") != null) {
        %>
          <% if(request.getAttribute("createError") != "" ){%>
          <p>${createError}
          <%} %>
        <% 
          }
        %>
      </div>
    </div>

    <script>
      function validateForm() {
        let isValid = true;

        // Underwriter ID validation
        const underwriterId = document.getElementById("underwriterId").value;
        const idRegex = /^[0-9]+$/;
        if (!idRegex.test(underwriterId)) {
          document.getElementById("underwriterIdError").textContent =
            "Underwriter ID must contain only numbers.";
          isValid = false;
        } else {
          document.getElementById("underwriterIdError").textContent = "";
        }

        return isValid;
      }
    </script>
  </body>
</html>
