function validate(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	if(username === "" || password === ""){
		alert("Fields are empty!!");
		return false;
	}
	return true;
}