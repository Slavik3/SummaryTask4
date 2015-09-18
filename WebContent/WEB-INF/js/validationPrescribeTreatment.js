function validateForm(inputvalue) {	
	var diagnosis = document.forms["frm"]["diagnosis"].value;
	var nameOfMedication=document.forms["frm"]["nameOfMedication"].value;
		
	valid=true;	

	if (diagnosis.length == 0) {
		document.getElementById("diagnosis").innerHTML = "*";
		var input = document.getElementById('diagnosis');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (diagnosis.length != 0) {
		document.getElementById("diagnosis").innerHTML = "";
		var input = document.getElementById('diagnosis');
		input.style.border = "";
	}
	
	if (nameOfMedication.length == 0) {
		document.getElementById("nameOfMedication").innerHTML = "*";
		var input = document.getElementById('nameOfMedication');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (nameOfMedication.length != 0) {
		document.getElementById("nameOfMedication").innerHTML = "";
		var input = document.getElementById('nameOfMedication');
		input.style.border = "";
	}
	
	return valid;

}