<%@ page language="java" %>
<%@ page session="true" %>
<%@ page  import="com.kudos.profile.*" %>
<%@ page  import="com.kudos.payroll.*" %>
<%@ page  import="java.util.*" %>
<html>
<head>
<title>www.hrsolutions.com/Salary Slip</title>
<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
</head>
<body>
<table width="900" border="0" align="center"> 
<tr>
	<td colspan="2" ><%@ include file="/jsp/ersHeader.jsp" %></td>
</tr>
<tr>
<td width="150" valign="top"><%@ include file="../hrmsMainMenu.jsp" %></td>  
<td width ="750" valign="top">
<table  border="0"  width=500 align=center>
<% 
  String dBOperation = "";
  dBOperation = (String)session.getAttribute("dBOperation");
  HrmsEmployee hrmsEmployee = new HrmsEmployee();
  hrmsEmployee = (HrmsEmployee)session.getAttribute("hrmsEmployee");
  int totalAttendance  = 0;
  int totalLeave      = 0;
  int year  = 0;
  int month = 0;
  totalAttendance = Integer.parseInt((String)session.getAttribute("totalAttendance"));
  totalLeave = Integer.parseInt((String)session.getAttribute("totalLeave"));
  year = Integer.parseInt((String)session.getAttribute("year"));
  month = Integer.parseInt((String)session.getAttribute("month"));
%>
<p>&nbsp;</p>
<hr bgcolor="#843f8b" width=500>
  <form name="form1" method="post">
	<tr>
	<td colspan=4 align=center bgcolor="#843f8b" height=20 class=whitetext>Salary Slip For <%=month%>/<%=year%></td>
	</tr>
	<tr><td>Employee Id</td>
	<td><%=hrmsEmployee.empId%>
	<input type='hidden' name='empId' id='empId 'size ='10' value='<%=hrmsEmployee.empId%>'/></td>
	</tr>
	<tr><td>Employee Name</td>
	<td>
	<%=hrmsEmployee.empFirstName%> 
	<%
	if(!"null".equals(hrmsEmployee.empMiddleName))
	out.print(hrmsEmployee.empMiddleName);
	%>
	<%=hrmsEmployee.empLastName%>
	<input type='hidden' name='empName' id='empName' size ='10' value='<%=hrmsEmployee.empFirstName%> 
	<%=hrmsEmployee.empMiddleName%> <%=hrmsEmployee.empLastName%>'/></td>
	</tr>
	<tr><td>Department</td><td><%=hrmsEmployee.deptId%></td></tr> 
	<tr><td>Desination</td><td>
	<%=hrmsEmployee.levelId%>
	<input type='hidden' name='levelId' id='levelId 'size ='10' value='<%=hrmsEmployee.levelId%>'/></td>
	</tr>
	</table>
<%
	ArrayList empSalList  = new ArrayList();
	empSalList = (ArrayList)session.getAttribute("empSalList");
	if ( empSalList != null && empSalList.size() > 0){  
%>
		<table align=center width=500>
		<tr bgcolor ='#843f8b' align=center>
		<td class=whitetext>Allowance Name</td>
		<td class=whitetext>Amount(Rs.)</td>
		</tr>
		<%
		double totalSalary = 0;
		double taxAmt  = 0 ;
		for ( int rec = 1; rec <= empSalList.size(); rec++ ){
			EmployeeSalary empSal = new EmployeeSalary();
			empSal = (EmployeeSalary)empSalList.get(rec-1);
			
			out.println("<tr bgcolor ='#C58DC'>");
			out.println("<td align='center' >"+empSal.allowanceName+"</td>");
			if ( empSal.allowanceType.equals("Income") ){
				out.println("<td align='center' >(+)"+empSal.amount+"</td>");
				totalSalary	=   totalSalary + empSal.amount;
			}
			else
			if ( empSal.allowanceType.equals("Deduction") ){
				out.println("<td align='center' >(-)"+empSal.amount+"</td>");
			}
			if ( empSal.taxable.equals("Yes") ){
				taxAmt = taxAmt + (empSal.amount * empSal.percentage/100);
			}
			out.println("</tr>");
		}
		%>
	  <tr>
		<td class=boldblack>Total Attendence: <%=totalAttendance%></td>
		<td class=boldblack>Total Leave: <%=totalLeave%></td>
	  </tr>
	<tr>
		<td class=boldblack>Salary: <%=totalSalary%></td>
		<td class=boldblack>Tax: <%=taxAmt%></td>
	 </tr>
	 </table>
	<%}	  
  String lErrorMsg="";
  lErrorMsg = (String)session.getAttribute("lErrorMsg");
  out.println("<div align=center class=boldred"+lErrorMsg+"</div>");
  %>
  </table>
</td></tr>
<tr>
    <td colspan="2">
	<%@include file="../hrmsFooter.jsp"%>
	</td>
</tr></table></body></html>