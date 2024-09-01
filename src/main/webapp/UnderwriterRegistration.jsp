<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin-Underwriter Registration</title>
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
        font-weight:bold;
        text-decoration: none;
        display: block;
        padding: 10px;
        margin-bottom: 10px;
        border-radius:5px;
        box-shadow:#00000095;
      }

      .sidebar a:hover {
        background-color: #555;
      }

      .content {
      	width:85%;
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
    </style>
  </head>
  <body>
    <nav>
      <h1>Welcome to Insurance Policy Management</h1>
    </nav>
    <div class="sidebar">
  <h2>Admin Menu</h2>
  <a href="#welcome">Registration</a>
  <a href="searchUnderwriter.jsp">Search</a>
  <a href="updateUnderwriterPassword.jsp">Update</a>
  <a href="deleteUnderwriter.jsp">Delete</a>
  <a href="AdminServlet?action=View All Underwriters">View Underwriters</a>
  <a href="viewAllVehicles.jsp">View Vehicles</a>
  <a href="index.html">Logout</a>
</div>

    <div class="content" id="content">
      <h2 id="welcome">Register New Underwriter</h2>
      <h5 style="text-align: center;">
            <% 
                String message = (String) request.getAttribute("message");
                Integer newID = (Integer) request.getAttribute("newID");
                
                if ("Creation Success!!".equals(message) && newID != null) {
            %>
                <p style="color: green; font-size: 1.2em;">Creation Success with ID: <%= newID %></p>
            <% 
                } else if ("Underwriter was not registered!".equals(message)) { 
            %>
                <p style="color: red; font-size: 1.2em;">Some Error Occurred, Try again..</p>
            <% 
                } 
            %>
        </h5>
      
      <form
        id="registerUnderwriterForm"
        action="AdminServlet"
        method="POST"
        onsubmit="return validateForm()"
      >
        <div class="form-group">
          <label for="underwriterName">Underwriter Name:</label>
          <input type="text" id="underwriterName" name="underwriterName" required />
          <span id="underwriterNameError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="dob">Date of Birth:</label>
          <input type="date" id="dob" name="dob" required />
          <span id="dobError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="joiningDate">Joining Date:</label>
          <input type="date" id="joiningDate" name="joiningDate" required readonly />
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required />
          <span id="passwordError" class="error"></span>
        </div>
        <div class="form-group">
          <input type="submit" name="action" value="Register Underwriter" />
        </div>
      </form>
    </div>

    <script>
      // Set today's date as the default for joiningDate
      document.getElementById("joiningDate").valueAsDate = new Date();

      function validateForm() {
        let isValid = true;

        // Underwriter Name validation
        const underwriterName = document.getElementById("underwriterName").value;
        const nameRegex = /^[A-Za-z\s]+$/;
        if (!nameRegex.test(underwriterName)) {
          document.getElementById("underwriterNameError").textContent =
            "Must contain only letters and spaces.";
          isValid = false;
        } else {
          document.getElementById("underwriterNameError").textContent = "";
        }

        // Date of Birth validation
        const dob = new Date(document.getElementById("dob").value);
        const today = new Date();
        const age = today.getFullYear() - dob.getFullYear();
        if (age < 18 || age > 60) {
          document.getElementById("dobError").textContent =
            "Underwriter must be between 18 and 60 years old.";
          isValid = false;
        } else {
          document.getElementById("dobError").textContent = "";
        }

        // Password validation
        const password = document.getElementById("password").value;
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,30}$/;

        if (!passwordRegex.test(password)) {
          document.getElementById("passwordError").textContent =
            "Password must be 8 to 30 characters long and contain alphanumeric and atleast a special character.";
          isValid = false;
        } else {
          document.getElementById("passwordError").textContent = "";
        }

        return isValid;
      }
    </script>
  </body>
</html>