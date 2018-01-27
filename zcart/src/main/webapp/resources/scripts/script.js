function checkPassword(id1,id2) {
	var pass = document.getElementById(id1);
	var cpass = document.getElementById(id2);
	if (pass.value == cpass.value) {
		return true;
	} else {
		alert("Password missmatch.");
		pass.value = "";
		cpass.value = "";
		pass.focus();
		return false;
	}

}