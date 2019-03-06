/******** Use this code and edit as you need *************/
/**************  Developed by k k chandan *******************/
/********  Contact at kkchandan75@gmail.com ****************/



/**************  Empty field check  *************************/
function isemptyfield(fieldname,msg)
{
	if(trim(fieldname.value)=="")
	{
	alert(msg)	;
	fieldname.value="";
	fieldname.focus();
	return true;
	}
	return false;
}

/******************  Minimum characters check  **********************/
function isminchars(fieldname,msg,charlength)
{
	if(fieldname.value.length<charlength)
	{
	alert(msg)	;
	fieldname.focus();
	return true;
	}
	return false;
}
/******************  Maximum characters check  **********************/
function ismaxchars(fieldname,msg,charlength)
{
	if(fieldname.value.length>charlength)
	{
	alert(msg)	;
	fieldname.focus();
	return true;
	}
	return false;
}

/******************  Check for number start with nonzero  **********************/

function isnonzerostart(fieldname,msg)
{
	var i;
	var s=fieldname.value;
	if(s.charAt(0) == "0")
	{
		alert(msg)	;
		fieldname.focus();
		return false;
	}
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
		{
		alert(msg)	;
		fieldname.focus();
		return false;
		}
    }
	
	
	
    // All characters are numbers.
    return true;
}
/*******************************  Rate Format *************************************/
//$45-50
function israte_formate(fieldname,msg)
{	
	var strValidChars = "0123456789-";   //  Valid Characters 
   	var strString=fieldname.value;
   	var strChar;
   	var blnResult = true;
	if(strString.charAt(0) == "0")
	{
		alert(msg)	;
		fieldname.focus();
		return false;
	}
   	//  strValidChars consists of valid characters listed above
   	for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
			alert(msg)	;
			fieldname.focus(); 
         	blnResult = false;
         }
      }
   	return blnResult;
}
   
/******************************  Check Numbers  ***********************************/
function isnumeric(fieldname,msg)
   //  check for valid numeric strings 
   {
   var strValidChars = "0123456789";   //  Valid Characters 
   var strString=fieldname.value;
   var strChar;
   var blnResult = true;
   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
			alert(msg)	;
			fieldname.focus(); 
         	blnResult = false;
         }
      }
   return blnResult;
   }	

	
/******************************  Check Alfa Numeric  ***********************************/
function isalfanumeric(fieldname,msg)
   //  check for valid numeric strings 
   {
   var strValidChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   //  Valid Characters 
   var strString=fieldname.value;
   var strChar;
   var blnResult = true;
   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
			alert(msg)	;
			fieldname.focus(); 
         	blnResult = false;
         }
      }
   return blnResult;
   }	


/***************************  Validation for Phone Number******************/
// Declaring required variables
var digits = "0123456789";
// non-digit characters which are allowed in phone numbers
var phoneNumberDelimiters = "()-.";
// characters which are allowed in international phone numbers
// (a leading + is OK)
var validWorldPhoneChars = phoneNumberDelimiters + "+";
// Minimum no of digits in an international phone no.
var minDigitsInIPhoneNumber = 7;
var maxDigitsInIPhoneNumber = 12;

function isInteger(s)
{   var i;
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}
function trim(s)
{   var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not a whitespace, append to returnString.
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (c != " ") returnString += c;
    }
    return returnString;
}
function stripCharsInBag(s, bag)
{   var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function checkInternationalPhone(strPhone){
var bracket=3
strPhone=trim(strPhone)
if(strPhone.indexOf("+")>1) return false
if(strPhone.indexOf("-")!=-1)bracket=bracket+1
if(strPhone.indexOf("(")!=-1 && strPhone.indexOf("(")>bracket)return false
var brchr=strPhone.indexOf("(")
if(strPhone.indexOf("(")!=-1 && (strPhone.indexOf(")")==-1 || strPhone.indexOf(")") < brchr+2))return false
//if(strPhone.indexOf("(")!=-1 && strPhone.charAt(brchr+2)!=")")return false
if(strPhone.indexOf("(")==-1 && strPhone.indexOf(")")!=-1)return false
s=stripCharsInBag(strPhone,validWorldPhoneChars);
return (isInteger(s) && s.length >= minDigitsInIPhoneNumber && s.length <= maxDigitsInIPhoneNumber);
}

// change the phone validation

/* check for pin/zip code */
function zip(s)
{
var s=trim(s);
return (isInteger(s) && (s.length == 5));
}
function zip_code(fieldname,msg)
{
var s=trim(fieldname.value);
if(isInteger(s) && (s.length == 5))
return true;
else
{
alert(msg);
fieldname.focus();
return false;
}
}
	
/*******************************  Email Validation Start ***********************************************/
	function isvalidemail(fieldname,msg)
	{
	var str=fieldname.value;
	var emailfilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!emailfilter.test(str)) 
		{
		alert(msg);
		fieldname.focus();
		return false;
		}
	return true;
	}

/*********************************   Open popup window ********************************/
var win=null;
function openWin(mypage,myname,w,h,scroll,pos){
if(pos=="random"){LeftPosition=(screen.width)?Math.floor(Math.random()*(screen.width-w)):100;TopPosition=(screen.height)?Math.floor(Math.random()*((screen.height-h)-75)):100;}
if(pos=="center"){LeftPosition=(screen.width)?(screen.width-w)/2:100;TopPosition=(screen.height)?(screen.height-h)/2:100;}
else if((pos!="center" && pos!="random") || pos==null){LeftPosition=0;TopPosition=20}
settings='width='+w+',height='+h+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no';
win=window.open(mypage,myname,settings);
}
/******** Use this code and edit as you need ************* ************* ************* ************* ************* *************/
/**************  Developed and changed by kkchandan *******************/

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


/******************  Minimum characters check  **********************/
function isMinimumChars(fieldName,message,charLength) {
	if(fieldName.value.length<charLength) {
		alert(message)	;
		fieldName.focus();
		return true;
	}
	return false;
}

/******************  Maximum characters check  **********************/
function isMiximumChars(fieldName,message,charLength) {
	if(fieldName.value.length>charLength) {
		alert(message)	;
		fieldName.focus();
		return true;
	}
	return false;
}

/******************  Check for number start with nonzero  **********************/
function isNonZeroStart(fieldName,message){
	var i;
	var s=fieldName.value;
	if(s.charAt(0) == "0") {
		alert(message)	;
		fieldName.focus();
		return false;
	} for (i = 0; i < s.length; i++) {
		// Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) {
        	alert(message)	;
        	fieldName.focus();
    		return false;
        }
	}
	// All characters are numbers.
    return true;
}

/*******************************  Rate Format *************************************/
function isRateFormate(fieldName,massege){	
	
	var strValidChars = "0123456789-";   //  Valid Characters 
   	var strString=fieldName.value;
   	var strChar;
   	var blnResult = true;
	if(strString.charAt(0) == "0"){
		
		alert(massege)	;
		fieldName.focus();
		return false;
	}
	
   	//  strValidChars consists of valid characters listed above
   	for (i = 0; i < strString.length && blnResult == true; i++) {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1) {
			alert(massege)	;
			fieldName.focus(); 
         	blnResult = false;
         }
      }
   	return blnResult;
}

/******************************  Check Numbers  ***********************************/
function isNumeric(fieldName,message) {
	var strValidChars = "0123456789";   //  Valid Characters 
	var strString=fieldname.value;
	var strChar;
	var blnResult = true;
	//  test strString consists of valid characters listed above
	for (i = 0; i < strString.length && blnResult == true; i++) {
		strChar = strString.charAt(i);
	    if (strValidChars.indexOf(strChar) == -1) {
	    	alert(message)	;
	    	fieldName.focus(); 
         	blnResult = false;
	    }
	}
	return blnResult;
}

/******************************  Check Alfa Numeric  ***********************************/
function isAlfaNumeric(fieldName,massege) {
	var strValidChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   //  Valid Characters 
	var strString=fieldName.value;
	var strChar;
	var blnResult = true;
//test strString consists of valid characters listed above
	for (i = 0; i < strString.length && blnResult == true; i++) {
		strChar = strString.charAt(i);
		if (strValidChars.indexOf(strChar) == -1) {
			alert(massege)	;
			fieldName.focus(); 
			blnResult = false;
		}
	}

	return blnResult;
}

/*******************************  Email Validation Start ***********************************************/
function isValidEmail(fieldName,massege) {
	
	if (document.fieldName.value.indexOf("@") < 1 || 
			document.fieldName.value.lastIndexOf(".") < document.fieldNamel.value.indexOf("@")+2 ||
			document.fieldName.value.lastIndexOf(".") +2 > document.fieldName.value.indexOf("@").length) {
		
		alert("massege");
		return false;
		
	}
	return true;
	}


/****************************  Form Validateion functions ******************************************/
/******************************  Login Form Validation *****************************************/
function validateLogin(login){

	
	if(isEmptyField(login.userId,"Please enter your user id !!")==true || 
			isMinimumChars(login.userId,"User id must contain a minimum of 2 characters!!",2)==true || 
			isMiximumChars(login.userId,"User id must not contain more than 5 characters!!!!",5)==true){
		 
		return false;
	} else if(isEmptyField(login.userName,"Please enter your name!!")==true || 
			isMinimumChars(login.userName,"Name must contain a minimum of 3 characters !!",3)==true || 
			isMiximumChars(login.userName,"User Name must not contain more than 15 characters !!",15)==true) {
		
		return false;
	} else if(isEmptyField(login.userPassword,"Please enter your password again.!!")==true || 
			isMinimumChars(login.userPassword,"Password must contain a minimum of 1 characters !!",1)==true ||
			isMiximumChars(login.userPassword,"User Password must not contain more than 5 characters !!",5)==true) {
		 return false;
	}
	return true;
}

/******************************  Change Password Form Validation *****************************************/
function validateChangePassword(changePassword){
	
	if(isEmptyField(changePassword.userId,"Please enter your user id !!")==true || 
			isMinimumChars(changePassword.userId,"User id must contain a minimum of 2 characters!!",2)==true || 
			isMiximumChars(changePassword.userId,"User id must not contain more than 5 characters!!!!",5)==true){
		 
		return false;
	} else if(isEmptyField(changePassword.userName,"Please enter your name!!")==true || 
			isMinimumChars(changePassword.userName,"Name must contain a minimum of 3 characters !!",3)==true || 
			isMiximumChars(changePassword.userName,"User Name must not contain more than 15 characters !!",15)==true) {
		
		return false;
	} else if(isEmptyField(changePassword.oldPassword,"Please enter your current password")==true ||
			isMiximumChars(changePassword.oldPassword,"Current Password must contain a miximum of 5 characters",5)==true){
			
		return false;
	}
	else if(isEmptyField(changePassword.newPassword,"Please enter your new password")==true ||
			isMiximumChars(changePassword.newPassword,"New Password must contain a miximum of 5 characters",5)==true){
		 return false;
	}
	else if(isEmptyField(changePassword.reTypePassword,"Please enter your new password again.")==true || 
			isMiximumChars(changePassword.reTypePassword,"New Password must contain a miximum of 5 characters",5)==true){
		return false;
	}
	
	if(changePassword.newPassword.value!=changePassword.reTypePassword.value){
		alert("Your new password and confirm password not matched.");
		changePassword.newPassword.focus();
		return false;
	}
	return true;
}


/****************************** Employee Insert Form Validation *****************************************/

function validateProfileInsert (profile) {
	
	if(isEmptyField(profile.empId,"Please enter your Employee Id!!")==true ||
			isMinimumChars(profile.empId,"Employee Id must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empId,"Employee Id must not contain more than 5 characters !!",5)==true) {
		return false;
	} else if(isEmptyField(profile.empFirstName,"Please enter your Employee First Name!!")==true ||
			isMinimumChars(profile.empFirstName,"Employee First Name must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empFirstName,"Employee First Name must not contain more than 15 characters !!",15)==true) {
		return false;
	}  else if(isEmptyField(profile.empMiddleName,"Please enter your Employee Middle Name!!")==true ||
			isMinimumChars(profile.empMiddleName,"Employee Middle Name must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empMiddleName,"Employee Middle Name must not contain more than 15 characters !!",15)==true) {
		return false;
	} 
	else if(isEmptyField(profile.empLastName,"Please enter your Employee Last Name!!")==true ||
			isMinimumChars(profile.empLastName,"Employee Last Name must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empLastName,"Employee Last Name must not contain more than 15 characters !!",15)==true) {
		return false;
	} else if(isEmptyField(profile.orgId,"Please enter your Employee Orgnization Id")==true ||
			isMinimumChars(profile.orgId,"Employee Orgnization Id must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.orgId,"Employee Orgnization Id must not contain more than 5 characters",5)==true) {
		return false;
	} else if(isEmptyField(profile.levelId,"Please enter your Designation")==true ||
			isMinimumChars(profile.levelId,"Designation must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.levelId,"Designation must not contain more than 20 characters",20)==true) {
		return false;
	} else if(isEmptyField(profile.deptId,"Please enter your Department")==true ||
			isMinimumChars(profile.deptId,"Department must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.deptId,"Department must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(profile.dateOfBirth,"Please enter your Employee date Of Birth")==true ||
			isMiximumChars(profile.dateOfBirth,"Employee date Of Birth must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(profile.dateOfJoin,"Please enter your Employee dateOfJoin!!")==true ||
			isMiximumChars(profile.dateOfJoin,"Employee date Of Join must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(profile.address1,"Please enter your Employee address1")==true ||
			isMiximumChars(profile.address1,"Employee address1 must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.address2,"Please enter your Employee address2")==true ||
			isMinimumChars(profile.address2,"Employee address2 must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.address2,"Employee address2 must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.city,"Please enter your Employee city")==true ||
			isMinimumChars(profile.city,"Employee city must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.city,"Employee city must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.state,"Please enter your Employee state")==true ||
			isMinimumChars(profile.state,"Employee state must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.state,"Employee state must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.nationality,"Please enter your Employee counrty")==true ||
			isMinimumChars(profile.nationality,"Employee counrty must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.nationality,"Employee counrty must not contain more than 15 characters",15)==true) {
		return false;
	}
	
	return true;
}

/****************************** Employee Search Form Validation *****************************************/
function validateProfileSearch (profile) {
	if(isEmptyField(profile.empId,"Please enter your Employee Id!!")==true ||
			isMinimumChars(profile.empId,"Employee Id must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empId,"Employee Id must not contain more than 5 characters !!",5)==true) {
		return false;
	}
	
	return true;
}
/****************************** Employee Edit Form Validation *****************************************/
function validateProfileEdit (profile) {
	if(isEmptyField(profile.empMiddleName,"Please enter your Employee Middle Name!!")==true ||
			isMinimumChars(profile.empMiddleName,"Employee Middle Name must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empMiddleName,"Employee Middle Name must not contain more than 15 characters !!",15)==true) {
		return false;
	} 
	else if(isEmptyField(profile.empLastName,"Please enter your Employee Last Name!!")==true ||
			isMinimumChars(profile.empLastName,"Employee Last Name must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(profile.empLastName,"Employee Last Name must not contain more than 15 characters !!",15)==true) {
		return false;
	} else if(isEmptyField(profile.orgId,"Please enter your Employee Orgnization Id")==true ||
			isMinimumChars(profile.orgId,"Employee Orgnization Id must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.orgId,"Employee Orgnization Id must not contain more than 5 characters",5)==true) {
		return false;
	} else if(isEmptyField(profile.levelId,"Please enter your Designation")==true ||
			isMinimumChars(profile.levelId,"Designation must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.levelId,"Designation must not contain more than 20 characters",20)==true) {
		return false;
	} else if(isEmptyField(profile.deptId,"Please enter your Department")==true ||
			isMinimumChars(profile.deptId,"Department must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.deptId,"Department must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(profile.dateOfBirth,"Please enter your Employee date Of Birth")==true ||
			isMiximumChars(profile.dateOfBirth,"Employee date Of Birth must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(profile.dateOfJoin,"Please enter your Employee dateOfJoin!!")==true ||
			isMiximumChars(profile.dateOfJoin,"Employee date Of Join must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(profile.address1,"Please enter your Employee address1")==true ||
			isMiximumChars(profile.address1,"Employee address1 must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.address2,"Please enter your Employee address2")==true ||
			isMinimumChars(profile.address2,"Employee address2 must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.address2,"Employee address2 must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.city,"Please enter your Employee city")==true ||
			isMinimumChars(profile.city,"Employee city must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.city,"Employee city must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.state,"Please enter your Employee state")==true ||
			isMinimumChars(profile.state,"Employee state must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.state,"Employee state must not contain more than 15 characters",15)==true) {
		return false;
	} else if(isEmptyField(profile.nationality,"Please enter your Employee counrty")==true ||
			isMinimumChars(profile.nationality,"Employee counrty must contain a minimum of 2 characters",2)==true ||
			isMiximumChars(profile.nationality,"Employee counrty must not contain more than 15 characters",15)==true) {
		return false;
	}
	
	
	
	return true;
}

/****************************** Applicant Register  Form Validation *****************************************/
function validateApplicantRegister(recruitment) {
	if(isEmptyField(recruitment.applicantId,"Please enter your Applicant Id!!")==true ||
			isMinimumChars(recruitment.applicantId,"Applicant Id must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(recruitment.applicantId,"Applicant Id must not contain more than 5 characters !!",5)==true) {
		return false;
	} else if(isEmptyField(recruitment.applicantName,"Please enter your Applicant Name!!")==true ||
			isMinimumChars(recruitment.applicantName,"Applicant Name must contain a minimum of 3 characters!!",3)==true ||
			isMiximumChars(recruitment.applicantName,"Applicant Name must not contain more than 25 characters !!",25)==true) {
		return false;
	} else if(isEmptyField(recruitment.address1,"Please enter your Applicant address1!!")==true ||
			isMinimumChars(recruitment.address1,"Applicant address1 must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(recruitment.address1,"Applicant address1 must not contain more than 25 characters !!",25)==true) {
		return false;
	} else if(isEmptyField(recruitment.address2,"Please enter your Applicant address2!!")==true ||
			isMinimumChars(recruitment.address2,"Applicant address2 must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(recruitment.address2,"Applicant address2 must not contain more than 25 characters !!",25)==true) {
		return false;
	} else if(isEmptyField(recruitment.currentLocation,"Please enter your Applicant current Location!!")==true ||
			isMinimumChars(recruitment.currentLocation,"Applicant current Location must contain a minimum of 2 characters!!",2)==true ||
			isMiximumChars(recruitment.currentLocation,"Applicant current Location must not contain more than 25 characters !!",25)==true) {
		return false;
	}  else if(isEmptyField(recruitment.email,"Please enter your Applicant email!!")==true ) {
		return false;
	} else if(isEmptyField(recruitment.phone,"Please enter your Applicant phone!!")==true ) {
		return false;
	} else if(isEmptyField(recruitment.mobile,"Please enter your Applicant mobile!!")==true ) {
		return false;
	}
	
	else if(isEmptyField(recruitment.dateOfBirth,"Please enter your Applicant date Of Birth")==true ||
			isMiximumChars(recruitment.dateOfBirth,"Employee date Of Birth must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.gender,"Please enter your Applicant gender")==true ||
			isMiximumChars(recruitment.gender,"Applicant gender must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.nationality,"Please enter your Applicant country")==true ||
			isMiximumChars(recruitment.nationality,"Applicant country must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.workExp,"Please enter your Applicant workExp")==true ||
			isMiximumChars(recruitment.workExp,"Applicant workExp must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.skill,"Please enter your Applicant skill")==true ||
			isMiximumChars(recruitment.skill,"Applicant skill must not contain more than 25 characters",25)==true) {
		return false;
	} else if(isEmptyField(recruitment.industry,"Please enter your Applicant industry")==true ||
			isMiximumChars(recruitment.industry,"Applicant industry must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.category,"Please enter your Applicant category")==true ||
			isMiximumChars(recruitment.category,"Applicant category must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.roles,"Please enter your Applicant roles")==true ||
			isMiximumChars(recruitment.roles,"Applicant roles must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.currentEmployer,"Please enter your Applicant currentEmployer")==true ||
			isMiximumChars(recruitment.currentEmployer,"Applicant currentEmployer must not contain more than 25 characters",25)==true) {
		return false;
	} else if(isEmptyField(recruitment.currentSalary,"Please enter your Applicant current Salary")==true ||
			isMiximumChars(recruitment.currentSalary,"Applicant currentSalary must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.highestDegree,"Please enter your Applicant highest Degree")==true ||
			isMiximumChars(recruitment.highestDegree,"Applicant highest Degree must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.secondHighestDegree,"Please enter your Applicant second Highest Degree")==true ||
			isMiximumChars(recruitment.secondHighestDegree,"Employee second Highest Degree must not contain more than 10 characters",10)==true) {
		return false;
	} else if(isEmptyField(recruitment.domain,"Please enter your Applicant domain!!!")==true ||
			isMiximumChars(recruitment.domain,"Employee domain must not contain more than 10 characters",10)==true) {
		return false;
	}
	
	return true;
}

/****************************** Employee Aggrement Form Validation *****************************************/
function validate_employee_aggrement(frm){
	if(isemptyfield(frm.allowanceName,"Please enter your Allowance Name")==true || isminchars(frm.allowanceName,"Allowance Name must contain a minimum of 2 characters",2)==true || ismaxchars(frm.allowanceName,"Allowance Name must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	else if(isemptyfield(frm.allowanceType,"Please enter your Allowance Type")==true || isminchars(frm.allowanceType,"allowanceType must contain a minimum of 2 characters",2)==true || ismaxchars(frm.allowanceType,"Allowance Type must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	else if(isemptyfield(frm.amount,"Please enter your amount")==true || isminchars(frm.amount,"Amount must contain a minimum of 3 characters",3)==true || ismaxchars(frm.amount,"Amount must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	else if(isemptyfield(frm.taxable,"Please enter your taxable")==true || isminchars(frm.taxable,"Taxable must contain a minimum of 2 characters",2)==true || ismaxchars(frm.taxable,"taxable must not contain more than 3 characters",3)==true)
	{
	 return false;
	}
	else if(isemptyfield(frm.percentage,"Please enter your percentage")==true || isminchars(frm.percentage,"Percentage must contain a minimum of 1 characters",1)==true || ismaxchars(frm.percentage,"Percentage must not contain more than 3 characters",3)==true)
	{
	 return false;
	}
	else if(isemptyfield(frm.agreementDate,"Please enter your Agreement Date")==true || isminchars(frm.agreementDate,"Agreement Date must contain a minimum of 10 characters",10)==true || ismaxchars(frm.agreementDate,"Agreement Date must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	return true;
}
