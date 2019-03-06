/******** Use this code and edit as you need *************/
/**************  Developed by k k chandan *******************/
/********  Contact kkchandan75@gmail.com ****************/



/**************  Empty field check  *************************/
function isEmptyField(fieldName,massege)
{
	if(trim(fieldName.value)=="")
	{
	alert(massege)	;
	fieldName.value="";
	fieldName.focus();
	return true;
	}
	return false;
}

/******************  Minimum characters check  **********************/
function isMinimumcharacter(fieldName,massege,characterLength)
{
	if(fieldName.value.length<characterLength)
	{
	alert(massege)	;
	fieldName.focus();
	return true;
	}
	return false;
}
/******************  Maximum characters check  **********************/
function isMaximumcharacter(fieldName,massege,characterLength)
{
	if(fieldName.value.length>characterLength)
	{
	alert(massege)	;
	fieldName.focus();
	return true;
	}
	return false;
}

/******************  Check for number start with nonzero  **********************/

function isNonZeroStart(fieldName,massege)
{
	var i;
	var s=fieldName.value;
	if(s.charAt(0) == "0")
	{
		alert(massege)	;
		fieldName.focus();
		return false;
	}
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
		{
		alert(massege)	;
		fieldName.focus();
		return false;
		}
    }
	
	
	
    // All characters are numbers.
    return true;
}
/*******************************  Rate Format *************************************/
//$45-50
function isRateFormate(fieldName,massege)
{	
	var strValidChars = "0123456789-";   //  Valid Characters 
   	var strString=fieldName.value;
   	var strChar;
   	var blnResult = true;
	if(strString.charAt(0) == "0")
	{
		alert(massege)	;
		fieldName.focus();
		return false;
	}
   	//  strValidChars consists of valid characters listed above
   	for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
			alert(massege)	;
			fieldName.focus(); 
         	blnResult = false;
         }
      }
   	return blnResult;
}


   
/******************************  Check Numbers  ***********************************/
function isNumeric(fieldName,massege)
   //  check for valid numeric strings 
   {
   var strValidChars = "0123456789";   //  Valid Characters 
   var strString=fieldName.value;
   var strChar;
   var blnResult = true;
   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
			alert(massege)	;
			fieldName.focus(); 
         	blnResult = false;
         }
      }
   return blnResult;
   }	

	
/******************************  Check Alfa Numeric  ***********************************/
function isAlfaNumeric(fieldName,massege)
   //  check for valid numeric strings 
   {
   var strValidChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";   //  Valid Characters 
   var strString=fieldName.value;
   var strChar;
   var blnResult = true;
   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
			alert(massege)	;
			fieldName.focus(); 
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

/* check for pin/zip code */
function zip(s)
{
var s=trim(s);
return (isInteger(s) && (s.length == 5));
}
function zipCode(fieldName,massege)
{
var s=trim(fieldName.value);
if(isInteger(s) && (s.length == 5))
return true;
else
{
alert(massege);
fieldName.focus();
return false;
}
}
	
/*******************************  Email Validation Start ***********************************************/
	function isValidEmail(fieldName,massege)
	{
	var str=fieldName.value;
	var emailfilter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!emailfilter.test(str)) 
		{
		alert(massege);
		fieldName.focus();
		return false;
		}
	return true;
	}

/*********************************   Open popup window ********************************/
var win=null;
function openWin(mypage,myname,w,h,scroll,pos){
if(pos=="random"){
	LeftPosition=(screen.width)?Math.floor(Math.random()*(screen.width-w)):100;TopPosition=(screen.height)?Math.floor(Math.random()*((screen.height-h)-75)):100;
	}
if(pos=="center"){
	LeftPosition=(screen.width)?(screen.width-w)/2:100;TopPosition=(screen.height)?(screen.height-h)/2:100;
	}
else if((pos!="center" && pos!="random") || pos==null){
	LeftPosition=0;TopPosition=20
	}
settings='width='+w+',height='+h+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no';
win=window.open(mypage,myname,settings);
}



/****************************  Form Validation functions ******************************************/
/******************************  Login Form Validation *****************************************/
function validateLogin(frm){

	
	if(isEmptyField(frm.userId,"Please enter your user id")==true || isMinimumcharacter(frm.userId,"User id must contain a minimum of 1 characters",1)==true || isMaximumcharacter(frm.userId,"User id must not contain more than 5 characters",5)==true)
	{
	 return false;
	}
	else if(isEmptyField(frm.userName,"Please enter your name")==true || isMinimumcharacter(frm.userName,"Name must contain a minimum of 1 characters",1)==true || isMaximumcharacter(frm.userName,"User Name must not contain more than 25 characters",25)==true)
	{
	 return false;
	}
	else if(isEmptyField(frm.userPassword,"Please enter your password again.")==true || isMinimumcharacter(frm.userPassword,"Password must contain a minimum of 1 characters",1)==true || isMaximumcharacter(frm.userPassword,"User Password must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	return true;
}
/******************************  Change Password Form Validation *****************************************/
function validateChangePassword(changePassword){

	
	if(isEmptyField(changePassword.oldPassword,"Please enter your current password")==true || isMaximumcharacter(changePassword.oldPassword,"Current Password must contain a miximum of 10 characters",10)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.newPassword,"Please enter your new password")==true || isMaximumcharacter(changePassword.newPassword,"New Password must contain a minimum of 10 characters",10)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.reTypePassword,"Please enter your new password again.")==true || isMaximumcharacter(changePassword.reTypePassword,"New Password must contain a minimum of 10 characters",10)==true)
	{
	 return false;
	}
	if(changePassword.newPassword.value!=changePassword.reTypePassword.value)
	{
	alert("Your new password and confirm password not matched.");
	changePassword.newPassword.focus();
	return false;
	}
	return true;
}


/****************************** Employee Insert Form Validation *****************************************/

function validateProfileInsert (profileInsert) {
	if(isEmptyField(profileInsert.empId,"Please enter your Employee Id")==true ||
			isMinimumcharacter(profileInsert.empId,"Employee Id must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.empId,"Employee Id must not contain more than 10 characters",10)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.empFirstName,"Please enter your Employee First Name")==true ||
			isMaximumcharacter(profileInsert.empFirstName,"Employee First Name must not contain more than 20 characters",20)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.empMiddleName,"plese enter your Empoloy middleName")==true ||
			SisMaximumcharacter(profileInsert.empMiddleName,"Employee Middle Name must not contain more than 20 characters",20)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.empLastName,"Please enter your Employee Last Name")==true ||
			isMaximumcharacter(profileInsert.empLastName,"Employee Last Name must not contain more than 20 characters",20)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.orgId,"Please enter your Employee Orgnization Id")==true ||
			isMinimumcharacter(profileInsert.orgId,"Employee Orgnization Id must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.orgId,"Employee Orgnization Id must not contain more than 5 characters",5)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.levelId,"Please enter your Designation")==true ||
			isMinimumcharacter(profileInsert.levelId,"Designation must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.levelId,"Designation must not contain more than 20 characters",20)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.deptId,"Please enter your Department")==true ||
			isMinimumcharacter(profileInsert.deptId,"Department must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.deptId,"Department must not contain more than 10 characters",10)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.dateOfBirth,"Please enter your Employee date Of Birth")==true ||
			isMaximumcharacter(profileInsert.dateOfBirth,"Employee date Of Birth must not contain more than 10 characters",10)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.dateOfJoin,"Please enter your Employee dateOfJoin")==true ||
			isMaximumcharacter(profileInsert.dateOfJoin,"Employee date Of Join must not contain more than 10 characters",10)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.address1,"Please enter your Employee address1")==true ||
			isMaximumcharacter(profileInsert.address1,"Employee address1 must not contain more than 25 characters",25)==true) {
		return false;
	}
	
	if(//isEmptyField(profileInsert.address2,"Please enter your Employee address2")==true ||
			//isMinimumcharacter(profileInsert.address2,"Employee address2 must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.address2,"Employee address2 must not contain more than 25 characters",25)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.city,"Please enter your Employee city")==true ||
			//isMinimumcharacter(profileInsert.city,"Employee city must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.city,"Employee city must not contain more than 15 characters",15)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.state,"Please enter your Employee state")==true ||
			//isMinimumcharacter(profileInsert.state,"Employee state must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.state,"Employee state must not contain more than 15 characters",15)==true) {
		return false;
	}
	
	if(isEmptyField(profileInsert.nationality,"Please enter your Employee nationality")==true ||
			//isMinimumcharacter(profileInsert.nationality,"Employee nationality must contain a minimum of 2 characters",2)==true ||
			isMaximumcharacter(profileInsert.nationality,"Employee nationality must not contain more than 15 characters",15)==true) {
		return false;
	}
}

/****************************** Employee Aggrement Form Validation *****************************************/
function validate_employee_aggrement(changePassword){
	if(isEmptyField(changePassword.allowanceName,"Please enter your Allowance Name")==true || isMinimumcharacter(changePassword.allowanceName,"Allowance Name must contain a minimum of 2 characters",2)==true || isMaximumcharacter(changePassword.allowanceName,"Allowance Name must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.allowanceType,"Please enter your Allowance Type")==true || isMinimumcharacter(changePassword.allowanceType,"allowanceType must contain a minimum of 2 characters",2)==true || isMaximumcharacter(changePassword.allowanceType,"Allowance Type must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.amount,"Please enter your amount")==true || isMinimumcharacter(changePassword.amount,"Amount must contain a minimum of 3 characters",3)==true || isMaximumcharacter(changePassword.amount,"Amount must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.taxable,"Please enter your taxable")==true || isMinimumcharacter(changePassword.taxable,"Taxable must contain a minimum of 2 characters",2)==true || isMaximumcharacter(changePassword.taxable,"taxable must not contain more than 3 characters",3)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.percentage,"Please enter your percentage")==true || isMinimumcharacter(changePassword.percentage,"Percentage must contain a minimum of 1 characters",1)==true || isMaximumcharacter(changePassword.percentage,"Percentage must not contain more than 3 characters",3)==true)
	{
	 return false;
	}
	else if(isEmptyField(changePassword.agreementDate,"Please enter your Agreement Date")==true || isMinimumcharacter(changePassword.agreementDate,"Agreement Date must contain a minimum of 10 characters",10)==true || isMaximumcharacter(changePassword.agreementDate,"Agreement Date must not contain more than 10 characters",10)==true)
	{
	 return false;
	}
	return true;
}
