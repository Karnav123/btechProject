<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page session = "true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>localhost:hrsolution.com/Search</title>
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
			<div align="center"> Search</div>
			<div align="center"> Enter Employee ID Employee Name</div>
			<hr  bgcolor="#34383E" width=500 >
			<table border="0" align="top" width=50% align="right">
				<form name="profile" method="post" onsubmit="return validateProfileSearch(this);">
					<tr>
						<td>Employee ID</td>
						<td align="left"><input type="text" name='empId' id='empId' size='10' value=''/></td>							
					</tr>
					<tr>
						<td>First Name</td>
						<td align="left"><input type="text" name='empFirstName' id='empFirstName' size='20' value=''/></td>							
					</tr>
					<tr>
						<td align="center" colspan="4">
						<input type="submit" name='submit' id='submit' size='10' value='Submit'/>
						</td>
					</tr>
					
						<input type="hidden" name='actionSubmit' id='actionSubmit' size='10' value='hrmsEmployeeSearchSubmit'/></td>													
					
				</form>
			</table>
			<hr  bgcolor="#34383E" width=500 >
		</td>
		</tr>
		<tr>
			<td colspan="2"><%@include file="../hrmsFooter.jsp" %></td>
		</tr>
	</table>

</body>
</html>