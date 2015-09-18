function validateForm(inputvalue) {	
	var name = document.forms["frm"]["firstName"].value;
	var surname=document.forms["frm"]["lastName"].value;
	var birthday = document.forms["frm"]["birthday"].value;
	
	year=birthday.substring(0, 4);
	//window.alert(year);	
	
	valid=true;	

	if (name.length == 0) {
		var input = document.getElementById('firstName');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (name.length != 0) {
		var input = document.getElementById('firstName');
		input.style.border = "";
	}
	
	if (surname.length == 0) {
		var input = document.getElementById('lastName');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (surname.length != 0) {
		var input = document.getElementById('lastName');
		input.style.border = "";
	}
	
	if(year>2015 || year< 1895) {
		var input = document.getElementById('birthday');
		input.style.border = "thin solid red";
		valid= false;
	}
	
	if(year<2016 && year>1895) {
		var input = document.getElementById('birthday');
		input.style.border = "";
	}
	
	return valid;
	

}