<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session = "true" %>
<%@ page import = "com.kudos.profile.*" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>localhost:hrsolution.com/Edit Employee</title>
	<link rel="stylesheet" href=".../css/mystyle.css" type="text/css" />
	<script type="text/javascript" language="javascript" src="scripts/validation.js"></script>
	
</head>
<body>
	<table width="900" border="0" align="center">
		<tr>
			<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
		</tr>
		<tr>
			<td width="150"><%@ include file="../hrmsMainMenu.jsp" %></td>
		
			<td  width="750" valign="top" align="center">
				<p>&nbsp;</p>
				Employee Detail
				<hr  width=100% color=#34383E>
				<table border="0" align="top" width=100%>
					<%
					
						HrmsEmployee employeeDBObj = new HrmsEmployee();
					
						employeeDBObj = (HrmsEmployee)session.getAttribute("employeeDBObj");
						if(employeeDBObj == null )
						{
							employeeDBObj = new HrmsEmployee();
						}
						
						
					%>
					<form name="profile" method="post" onsubmit="return validateProfileEdit(this);" >
						<input type="hidden" name='empId' id='empId' size='10' value="<%=employeeDBObj.empId %>"/>
						<input type="hidden" name='empFirstName' id='empFirstName' size='20' value="<%=employeeDBObj.empFirstName %>"/>
						<tr>
							<td> Employee ID </td>
							<td align="left"><input type="text" disabled="disabled" name='empIdDup' id='empIdDup' size='10' value="<%=employeeDBObj.empId %>"/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>First Name</td>
							<td align="left"><input type="text" disabled="disabled" name='empFirstNameDup' id='empFirstNameDup' size='10' value="<%=employeeDBObj.empFirstName %>"/></td>
							<td colspan="2">Middle Name&nbsp;&nbsp;<input type="text" name='empMiddleName' id='empMiddleName' size='10' value="<%=employeeDBObj.empMiddleName %>"/>
								&nbsp;&nbsp;&nbsp;Last Name&nbsp;&nbsp;&nbsp;<input type="text" name='empLastName' id='empLastName' size='10' value="<%=employeeDBObj.empLastName %>"/>
							</td>							
						</tr>
						<tr>
							<td>Organization ID</td>
							<td align="left"><input type="text" name='orgId' id='orgId' size='10' value="<%=employeeDBObj.orgId %>"/></td>
							<td >Designation</td>
							<td align="left"><input type="text" name='levelId' id='levelId' size='20' value="<%=employeeDBObj.levelId %>"/></td>													
						</tr>
						<tr>
							<td>Department</td>
							<td align="left"><input type="text" name='deptId' id='deptId' size='20' value="<%=employeeDBObj.deptId %>"/></td>
							<td >Date of Birth</td>
							<td align="left"><input type="text" name='dateOfBirth' id='dateOfBirth' size='10' value="<%=employeeDBObj.dateOfBirth %>"/>(yyyy-mm-dd)</td>													
						</tr>
						<tr>
							<td>Join Date</td>
							<td align="left"><input type="text" name='dateOfJoin' id='dateOfJoin' size='10' value="<%=employeeDBObj.dateOfJoin %>"/>(yyyy-mm-dd)</td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>													
						</tr>
						<tr>
							<td>Address 1</td>
							<td align="left" colspan="2"><input type="text" name='address1' id='address1' size='40' value="<%=employeeDBObj.address1 %>"/></td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>													
						</tr>
						<tr>
							<td>Address 2</td>
							<td align="left" colspan="2"><input type="text" name='address2' id='address2' size='40' value="<%=employeeDBObj.address2 %>"/></td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>													
						</tr>
						<tr>
							<td>City</td>
							<td align="left"><input type="text" name='city' id='city' size='10' value="<%=employeeDBObj.city %>"/></td>
							<td >&nbsp;</td>
							<td >&nbsp;</td>													
						</tr>
						<tr>
							<td>State</td>
							<td align="left"><input type="text" name='state' id='state' size='10' value="<%=employeeDBObj.state %>"/></td>
							<td >Nationality</td>
							<td align="left"><input type="text" name='nationality' id='nationality' size='10' value="<%=employeeDBObj.nationality %>"/></td>													
						</tr>
						<tr>
							<td align="center" colspan="4">
							<input type="submit" name='submit' id='submit' size='10' value='Edit'/>
							<input type="hidden" name='actionEdit' id='actionEdit' size='10' value='hrmsEmployeeEditSubmit'/></td>													
						</tr>
					</form>
				</table>
				<hr  width=100% color=#34383E>
			</td>
		</tr>
		<tr>
			<td colspan="2"><%@include file="../hrmsFooter.jsp" %></td>
		</tr>
	</table>

</body>
</html>