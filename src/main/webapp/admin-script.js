document
  .getElementById("adminLoginForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    //login is handled in the client side itself for this case 
    console.log(`Username: ${username}, Password: ${password}`);
    // Redirect to admin dashboard or show error
    if (username === "admin" && password === "admin") {
      window.location.href = "adminDashboard.jsp";
    } else {
      window.alert("Invalid username or password");
    }
  });
