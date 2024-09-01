<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Underwriter-Policy Registration</title>
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

      .sidebar h2,h3 {
        margin-bottom: 20px;
      }

      .sidebar a {
        color: #050A30;
        font-weight:bold;
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
      <h2>Underwriter Menu</h2>
      <a href="#welcome" >Create Insurance</a>
      <a href="underwriterLanding2.jsp">Renew Policy</a>
      <a href="underwriterLanding3.jsp">Change Policy Type</a>
      <a href="underwriterLanding4.jsp">View Policy</a>
      <a href="index.html">Logout</a>
    </div>

    <div class="content" id="content">
      <h2 id = "welcome">Create New Vehicle Insurance</h2>
<h5 style="text-align: center;">
            <% 
                String message = (String) request.getAttribute("message");
                Integer newID = (Integer) request.getAttribute("newID");
                
                if ("Creation Success!!".equals(message) && newID != null) {
            %>
                <p style="color: green; font-size: 1.2em;">Creation Success with ID: <%= newID %></p>
            <% 
                } else if ("Policy was not created!".equals(message)) { 
            %>
                <p style="color: red; font-size: 1.2em;">Some Error Occurred, Try again..</p>
            <% 
                } 
            %>
        </h5>


      <form
        id="createInsuranceForm"
        action="InsurenceServlet"
        method="POST"
        onsubmit="return validateForm()"
      >
        <div class="form-group">
          <label for="vehicleNo">Vehicle Number:</label>
          <input type="text" id="vehicleNo" name="vehicleNo" required />
          <span id="vehicleNoError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="vehicleType">Vehicle Type:</label>
          <select id="vehicleType" name="vehicleType" required>
            <option value="2-wheeler">2-wheeler</option>
            <option value="4-wheeler">4-wheeler</option>
          </select>
        </div>
        <div class="form-group">
          <label for="customerName">Customer Name:</label>
          <input type="text" id="customerName" name="customerName" required />
          <span id="customerNameError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="engineNo">Engine Number:</label>
          <input type="text" id="engineNo" name="engineNo" required />
          <span id="engineNoError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="chasisNo">Chassis Number:</label>
          <input type="text" id="chasisNo" name="chasisNo" required />
          <span id="chasisNoError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="phoneNo">Phone Number:</label>
          <input type="tel" id="phoneNo" name="phoneNo" required />
          <span id="phoneNoError" class="error"></span>
        </div>
        <div class="form-group">
          <label for="insuranceType">Insurance Type:</label>
          <select id="insuranceType" name="insuranceType" required>
            <option value="Full Insurance">Full Insurance</option>
            <option value="ThirdParty">Third Party</option>
          </select>
        </div>
        <div class="form-group">
          <label for="fromDate">From Date:</label>
          <input type="date" id="fromDate" name="fromDate" required readonly />
        </div>
        <div class="form-group">
          <input type="submit" name="action" value="Create Insurance" />
        </div>
      </form>
    </div>

    <script>
      // Set today's date as the default for fromDate
      document.getElementById("fromDate").valueAsDate = new Date();

      function loadContent(page) {
        // This function would typically use AJAX to load content dynamically
        alert("Loading " + page + " content");
      }

      function validateForm() {
        let isValid = true;

        // Vehicle Number validation
        const vehicleNo = document.getElementById("vehicleNo").value;
        const vehicleNoRegex = /^[A-Za-z]{2}\s\d{2}\s[A-Za-z]\s\d{4}$/;
        if (!vehicleNoRegex.test(vehicleNo)) {
          document.getElementById("vehicleNoError").textContent =
            "Invalid format. Must be like 'AN 01 Z 0123'.";
          isValid = false;
        } else {
          document.getElementById("vehicleNoError").textContent = "";
        }

        // Customer Name validation
        const customerName = document.getElementById("customerName").value;
        const customerNameRegex = /^[A-Za-z\s]+$/;
        if (!customerNameRegex.test(customerName)) {
          document.getElementById("customerNameError").textContent =
            "Must contain only letters and spaces.";
          isValid = false;
        } else {
          document.getElementById("customerNameError").textContent = "";
        }

        // Engine Number validation
        const engineNo = document.getElementById("engineNo").value;
        if (isNaN(engineNo)) {
          document.getElementById("engineNoError").textContent =
            "Must be a numeric value.";
          isValid = false;
        } else {
          document.getElementById("engineNoError").textContent = "";
        }

        // Chassis Number validation
        const chasisNo = document.getElementById("chasisNo").value;
        if (isNaN(chasisNo)) {
          document.getElementById("chasisNoError").textContent =
            "Must be a numeric value.";
          isValid = false;
        } else {
          document.getElementById("chasisNoError").textContent = "";
        }

        // Phone Number validation
        const phoneNo = document.getElementById("phoneNo").value;
        const phoneNoRegex = /^\d{10}$/;
        if (!phoneNoRegex.test(phoneNo)) {
          document.getElementById("phoneNoError").textContent =
            "Must be exactly 10 digit number.";
          isValid = false;
        } else {
          document.getElementById("phoneNoError").textContent = "";
        }

        return isValid;
      }
    </script>
  </body>
</html>
