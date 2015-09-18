function validateForm(inputvalue) {	
	var name = document.forms["frm"]["firstName"].value;
	var surname=document.forms["frm"]["lastName"].value;
	var login = document.forms["frm"]["login"].value;
	var password = document.forms["frm"]["password"].value;
	
	valid=true;	

	if (name.length == 0) {
		document.getElementById("firstName").innerHTML = "*";
		var input = document.getElementById('firstName');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (name.length != 0) {
		document.getElementById("firstName").innerHTML = "";
		var input = document.getElementById('firstName');
		input.style.border = "";
	}
	
	if (surname.length == 0) {
		document.getElementById("lastName").innerHTML = "*";
		var input = document.getElementById('lastName');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (surname.length != 0) {
		document.getElementById("lastName").innerHTML = "";
		var input = document.getElementById('lastName');
		input.style.border = "";
	}
	
	if (login.length == 0) {
		document.getElementById("login").innerHTML = "*";
		var input = document.getElementById('login');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (login.length != 0) {
		document.getElementById("login").innerHTML = "";
		var input = document.getElementById('login');
		input.style.border = "";
	}
	
	if (password.length == 0) {
		document.getElementById("password").innerHTML = "*";
		var input = document.getElementById('password');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (password.length != 0) {
		document.getElementById("password").innerHTML = "";
		var input = document.getElementById('password');
		input.style.border = "";
	}
	
	return valid;	

}