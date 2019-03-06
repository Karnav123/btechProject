<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page session = "true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>localhost:hrsolution.com/Register New Applicant</title>
	<link rel="stylesheet" href=".../css/mystyle.css" type="text/css" />
	
	<!-- script type="text/javascript" language="javascript" src="scripts/validation.js"></script> -->
	
</head>


<body>
	<table width="900" border="0" align="center">
		<tr>
			<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
		</tr>
		<tr>
			<td width="150" valign="top"><%@ include file="../hrmsMainMenu.jsp" %></td>
		
			<td  width="750" valign="top" align="center">
				<p>&nbsp;</p>
				<table border="0" align="top" width=100%>
					<form name="recruitment" method="post" onsubmit="return validateApplicantRegister(this);">
						<tr>
							<td bgcolor=#34383E colspan="4" align="center" hight=20><b>Contact Information</b></td>
						</tr>
						<tr>
							<td width="150">Applicant ID</td>
							<td align="left"><input type="text" name='applicantId' id='applicantId' size='10' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="150">Applicant Name</td>
							<td align="left"><input type="text" name='applicantName' id='applicantName' size='40' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="150">Address 1 </td>
							<td align="left"><input type="text" name='address1' id='address1' size='40' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="150">Address 2</td>
							<td align="left"><input type="text" name='address2' id='address2' size='40' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Current Location </td>
							<td ><input type="text" name='currentLocation' id='currentLocation' size='15' value=""/></td>
							<td >Email</td>
							<td ><input type="text" name='email' id='email' size='25' value=""/></td>
						</tr>
						<tr>
							<td>Phone</td>
							<td ><input type="text" name='phone' id='phone' size='15' value=""/></td>
							<td >Mobile</td>
							<td ><input type="text" name='mobile' id='mobile' size='25' value=""/></td>
						</tr>
						<tr>
							<td bgcolor=#34383E colspan="4" align="center" hight=20><b>Personal Information</b></td>
						</tr>
						<tr>
							<td colspan="4"><br>Date Of Birth&nbsp;&nbsp;&nbsp;<input type="text" name='dateOfBirth' id='dateOfBirth' size='10' value=""/>(yyyy-mm-dd) 
								&nbsp;&nbsp;&nbsp;Gender&nbsp;&nbsp;&nbsp;<input type="text" name='gender' id='gender' size='10' value=""/>
								&nbsp;&nbsp;&nbsp;Nationality&nbsp;&nbsp;&nbsp;<input type="text" name='nationality' id='nationality' size='10' value=""/><br>.
							</td>							
						</tr>
						<tr>
							<td bgcolor=#34383E colspan="4" align="center" hight=20><b>Professional & Educational Details</b></td>
						</tr>
						<tr>
							<td >Work Experience</td>
							<td align="left"><input type="text" name='workExp' id='workExp' size='10' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Skill</td>
							<td align="left"><input type="text" name='skill' id='skill' size='30' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Industry</td>
							<td align="left"><input type="text" name='industry' id='industry' size='30' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Category</td>
							<td align="left"><input type="text" name='category' id='category' size='10' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Roles</td>
							<td align="left"><input type="text" name='roles' id='roles' size='10' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>Current Employer</td>
							<td ><input type="text" name='currentEmployer' id='currentEmployer' size='30' value=""/></td>
							<td colspan="2" align="right" >Current Salary&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" name='currentSalary' id='currentSalary' size='10' value=""/>
							</td>
						</tr>
						<tr>
							<td>Highest Degree</td>
							<td ><input type="text" name='highestDegree' id='highestDegree' size='10' value=""/></td>
							<td colspan="2" align="right">Second Highest Degree&nbsp;&nbsp;
								<input type="text" name='secondHighestDegree' id='secondHighestDegree' size='10' value=""/>
							</td>
						</tr>
						<tr>
							<td bgcolor=#34383E colspan="6" align="center" hight=20><b>Domain Knowledge</b></td>
						</tr>
						<tr>
							<td>Domain</td>
							<td align="left"><input type="text" name='domain' id='domain' size='10' value=""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
							<input type="submit" name='submit' id='submit' size='10' value='Submit'/></td>
						</tr>
							<input type="hidden" name='actionSubmit' id='actionSubmit' size='10' value='hrmsApplicantRegisterSubmit'/>													
						
						
					</form>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><%@include file="../hrmsFooter.jsp" %></td>
		</tr>
	</table>
		

</body>
</html>