document
  .getElementById("underWriterLoginForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    // Handle the login logic here
    // For now, just log the values
    console.log(`Username: ${username}, Password: ${password}`);
    // Redirect to admin dashboard or show a message
    if (username === "user1" && password === "pass123") {
      window.location.href = "underwriter-landing.html";
    } else {
      window.alert("Invalid username or password");
    }
  });
