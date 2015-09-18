function validateForm(inputvalue) {
	var login = document.forms["frm"]["name"].value;
	var pass = document.forms["frm"]["password"].value;	
	
	valid=true;	
	
	if (login.length == 0) {
		//document.getElementById("name").innerHTML = "*";
		var input = document.getElementById('name');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (pass.length == 0) {
		//document.getElementById("pass").innerHTML = "*";
		var input = document.getElementById('pass');
		input.style.border = "thin solid red";	
		valid= false;
	}	
	
	if (login.length != 0) {
		//document.getElementById("name").innerHTML = "";
		var input = document.getElementById('name');
		input.style.border = "";
	}
	if (pass.length != 0) {
		//document.getElementById("pass").innerHTML = "";
		var input = document.getElementById('pass');
		input.style.border = "";
	}	
	
	return valid;	

}