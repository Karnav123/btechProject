


/**************  Empty field check  *************************/

function isEmptyField(fieldName,message) {
	if(trim(fieldName.value)=="") {
		alert(message)	;
		fieldName.value="";
		fieldName.focus();
		return true;
	}
	return false;
	
}


/****************************  Form Validation functions ******************************************/
/******************************  Login Form Validation *****************************************/

function validateLogin(login){
	if(isEmptyField(login.userId,"Please enter your user id ")==true) {
					
		return false;
	}
	
	return true;
}