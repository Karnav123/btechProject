<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.kudos.time.EmployeeDailyAttendance"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>www.hrsolution.com/Attendance summary...</title>
     <link rel = "stylesheet" href =".../css/mystyle.css" type = "text/css"/>
</head>
<body>
<table width = "900" border ="0" align ="center">
<tr>
   <td colspan = "2"> <%@ include file="/jsp/ersHeader.jsp" %></td>
</tr>
         <tr>
         <td width="150" valign="top"><%@ include file="/jsp/hrmsMainMenu.jsp"  %></td>
             <td width ="150" valign ="top">
             <p>&nbsp;</p>
             <div align="center" class=boldblack>Daily Attendance Summary</div>
             <hr width=100% color=#843F8B>
             <table border="0" width=100%>
             <%
               String dBOperation ="";
             dBOperation=(String)session.getAttribute("dBOperation");
             %>
             <tr>
                  <td bgcolor='#843F8B' align="center" class=whitetext>Employee id</td>  
                   <td bgcolor='#843F8B' align="center" class=whitetext>Employee Name</td>
                    <td bgcolor='#843F8B' align="center" class=whitetext>In Time</td> 
                     <td bgcolor='#843F8B' align="center" class=whitetext>Out Time</td> 
                      <td bgcolor='#843F8B' align="center" class=whitetext>Remark</td> 
                       <td bgcolor='#843F8B' align="center" class=whitetext>Opr</td> 
                </tr>
                <%
                    ArrayList empDailyAttendanceList = new ArrayList();
                    empDailyAttendanceList =( ArrayList)session.getAttribute("empDailyAttendanceList");
                    
                	if(empDailyAttendanceList !=null && empDailyAttendanceList.size()>0)
                	{
                		System.out.println("========================================= ");
                		for(int size=1; size<=empDailyAttendanceList.size(); size++)
                		{
                			EmployeeDailyAttendance empDailyAttendanceDBObj = new EmployeeDailyAttendance();
                			empDailyAttendanceDBObj = (EmployeeDailyAttendance)empDailyAttendanceList.get(size-1);
                	
             	%>
             	
             	<form name="form1" method="post"></form>
             		<tr bgcolor='#C58DC'>
             			<td align='center'><%=empDailyAttendanceDBObj.empId %></td>
             			<td align='center'><%=empDailyAttendanceDBObj.empName %></td>
             			<td align='center'><%=empDailyAttendanceDBObj.inTime %></td>
             			<td align='center'><%=empDailyAttendanceDBObj.outTime %></td>
             			<td align='center'>
             			<%
             	   		 if(empDailyAttendanceDBObj.remark !=null)
             		   		out.print(empDailyAttendanceDBObj.remark);
             	   		 else
             		   out.print("--");
             			%>
             			<td align="center" bgcolor=#843F8B>
             				<a href='http://localhost:8080/EnterprizeResourceSystem/timeManagementServlet?dBOperation=edit&&empId=<%=empDailyAttendanceDBObj.empId%>&&todayDate=<%=empDailyAttendanceDBObj.todayDate%>' class=yellowlink>Edit</a>
             			</td>
             		</tr>
             	<%
                	}
                		 
                }
                else
                {
                	out.println("Application does not exist!!!");
                }
             
             %>    
                          
              </table>
              </tr>             
             </td>
             <tr>
                 <td colspan="2"><%@include file= "/jsp/hrmsFooter.jsp"%></td>
                 </tr>
             </table> 

</body>
</html>