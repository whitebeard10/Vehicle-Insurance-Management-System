document.getElementById("roleForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const role = document.getElementById("role").value;
    if (role === "underwriter") {
      window.location.href = "underwriterLogin.jsp";
    } else if (role === "admin") {
      window.location.href = "adminLogin.jsp";
    }
  });
