<%@ page language="java" %>
<%@ page session="true" %>
<%@ page  import="com.kudos.payroll.*" %>
<html>
<head>
	<title>www.hrsolutions.com/Employee Agreeement Edit</title>
	<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
	<script type="text/javascript" language="javascript" src="scripts/validation.js"></script>
</head>

<body>

<table width="900" border="0" align="center"> 
	<tr>
		<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
	</tr>
	<tr>
		<td width="130"><%@ include file="../hrmsMainMenu.jsp" %></td>  
		<td width ="730" valign="top" align = "center">
		<table  border="0" align="top" width=100%>
		<% 

  			EmployeeAgreement employeeAgreement = new EmployeeAgreement();
  			employeeAgreement = (EmployeeAgreement)session.getAttribute("employeeAgreement");
			%>
			<form name="form1" method="post" onSubmit="return validate_employee_aggrement(this);">
			<p>&nbsp;</p>
			<table align=center>
			<tr>
			<td colspan=4 bgcolor=843f8b class=whitetext align=center height=20><b>Employee Agreement Salary Head</b></td>	
			</tr>
			<tr><td>Employee Id</td>
			<td><input type='text' name='empId' id='empId 'size ='10' value='<%=employeeAgreement.empId%>'/></td>
			<td>Name</td>
			<td><input type='text' name='empName' id='empName 'size ='30' value='<%=employeeAgreement.empName%>' /></td>
			</tr>
		<tr><td>Designation</td>
			<td><input type='text' name='levelId' id='levelId' size ='10' value='<%=employeeAgreement.levelId%>'/></td>
			<td></td><td></td></tr>
			<tr>
				<td>Allowance Name</td>
		<td>
				<input type='hidden' name='allowanceName' id='allowanceName' size ='5' value='<%=employeeAgreement.allowanceName%>'/>
				<input type='text' disabled='disabled' name='allowanceNameDuplicate' id='allowanceNameDuplicate' size ='5' value='<%=employeeAgreement.allowanceName %>'/>
		</td>
		<td>Allowance Type</td>
			<td><SELECT name='allowanceType' > <OPTION VALUE=></OPTION> <OPTION VALUE=Income>Income</OPTION><OPTION VALUE=Deduction>Deduction</OPTION></SELECT></td>
		</tr>
	<tr>
	<td>Amount</td>
	<td><input type='text' name='amount' id='amount' size ='5' value='<%=employeeAgreement.amount%>'/></td>
	<td>Taxable</td>
	<td><SELECT name='taxable' > <OPTION VALUE=></OPTION> <OPTION VALUE=Yes>Yes</OPTION><OPTION VALUE=No>No</OPTION></SELECT></td>
	</tr>
	<tr>
	<td>Percentage</td>
	<td><input type='text' name='percentage' id='percentage' size ='5' value='<%=employeeAgreement.percentage%>'/></td>
	<td>Date</td>
	<td><input type='text' name='agreementDate' id='agreementDate' size ='10' value='<%= employeeAgreement.agreementDate==null ? "" :employeeAgreement.agreementDate%>'/> (yyyy-mm-dd)</td>
	</tr>
	<tr>
	<td colspan=4><input type='submit' name='submit' id='submit' size ='10' value='Edit'/>
	<input type='hidden' name='actionSubmit' id='actionSubmit' size ='10' value='employeeSalaryHeadEditSubmit'/> </td>
	</tr>
	</table>
	</form>
</td></tr>
<tr>
    <td colspan="2"><%@include file="../hrmsFooter.jsp"%></td>
</tr>
</table></body></html>