<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" %>
<%@page import="com.kudos.profile.*" %>
<%@page import="com.kudos.time.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" language="javascript" src="scripts/validation.js"></script>
</head>
<body>
	<table width = "900" border = "0" align="center">
		<tr> 
 			<td colspan="2"><%@ include file ="/jsp/ersHeader.jsp" %></td>
 		</tr>
		 <tr>
		 	<td width="150" valign="top"><%@ include file="/jsp/hrmsMainMenu.jsp"  %></td>
		 	<td  width ="750" valign="top">
		 	  <table border="0" width=100%>
		 	  
		 	  	<p>&nbsp;</p>
		 	  	<hr width=100% color=#843F8B>
		 	  		<%
		 	  		String dBOperation ="";
		 	  		dBOperation = (String)session.getAttribute("dBOperation");
		 	  		System.out.println("dBOperation  of employeeDailyAttendance.jsp-"+dBOperation);
		            HrmsEmployee employeeDBObj = new  HrmsEmployee();
		            employeeDBObj =( HrmsEmployee)session.getAttribute("employeeDBObj");
		            if(employeeDBObj == null){
		            	employeeDBObj = new HrmsEmployee();
		            }
		            DayObject dateYearMonthDayDBObj = new DayObject();
		            dateYearMonthDayDBObj =(DayObject)session.getAttribute("dateYearMonthDayDBObj");
		           if(dateYearMonthDayDBObj == null){
		        	   dateYearMonthDayDBObj = new DayObject();
		            }
		            String toDaydate = (String)session.getAttribute("toDaydate");
		            EmployeeDailyAttendance empDailyAttendanceDBObj = new EmployeeDailyAttendance();
		            empDailyAttendanceDBObj = ( EmployeeDailyAttendance)session.getAttribute("empDailyAttendanceDBObj");
		            if(empDailyAttendanceDBObj == null) {
		            	empDailyAttendanceDBObj = new EmployeeDailyAttendance();
		            }
		            
		 	  		%>
		 	  	<form name ="form1" method="post" onSubmit="return validate_daily_attendance(this);">
		 	  		<tr>
		 	  			<td bgcolor ='#843F8B' colspan='4' class=whitetext height=20align=center><b>Enter In/Out Time</b></td>
		 	  			
		 	  		</tr>
		 	  	   <tr>
		 	  		   <td>Employee Id</td>
		 	  		   <td> <%=employeeDBObj.empId %>		 	  		     
		 	  		      <input type="hidden" name='empId' id='empId' size='10' value='<%=employeeDBObj.empId %>'/>
		 	  		    </td>
		 	  		    <td> Day Date</td>
		 	  		   <% System.out.println("toDayDate.... employeeDailyAttendance.jsp.............."+dateYearMonthDayDBObj.todayDate);%> 
		 	  		    <td><%=toDaydate %>
		 	  		        <!--  <%=dateYearMonthDayDBObj.day %> -->
		 	  		        <input type ="hidden" name ='todayDate' id='todayDate 'size='10' 
		 	  		        value="<%=toDaydate %>>"/>
		 	  		        <!-- value="<%=dateYearMonthDayDBObj.todayDate %>" -->
		 	  		        
		 	  		        
		 	  		     </td>    	        
		 	  		      	  		
		 	  		</tr>
		 	  	<tr>
		 	  		 <td>Employee Name</td>
		 	  		  <td><%=employeeDBObj.empFirstName %>
                         <input type ='hidden' name='empName' id='empName' size='10'
                         value ='<%=employeeDBObj.empFirstName %>'  />
                         
                       </td>
                       <td>Day</td>
                       <td> 
                            <%=dateYearMonthDayDBObj.day %>
                            <input type='hidden' name='day' id='day' size='10' value='<%=dateYearMonthDayDBObj.day %>' />
                            <%=dateYearMonthDayDBObj.day %>
                        </td>
                         
		 	  		</tr>
		 	  		<tr>
		 	  		   <td>Department</td>
		 	  		   <td><%=employeeDBObj.deptId %></td>
		 	  		   <td>Month</td>
		 	  		   <td>
		 	  		   <input type ='hidden' name='month' id='month' size='10' value = '<%=dateYearMonthDayDBObj.month %>'/>
		 	  		   <%=dateYearMonthDayDBObj.month %>
		 	  		   </td>
		 	  		</tr>
		 	  		<tr>  
		 	  		 <td>&nbsp;
		 	  		 </td>
		 	  		 <td>&nbsp;</td><td>year</td>
		 	  		  <td><%=dateYearMonthDayDBObj.year %>
                           <input type='hidden' name='year' id='year' size ='10'
                               value='<%=dateYearMonthDayDBObj.year %>'/></td>
		 	  		</tr>
		 	  		<tr>
		 	  		  <td>In Time</td>
		 	  		  <%
		 	  		  if( empDailyAttendanceDBObj.inTime !=null)
		 	  			  out.println("<td><input type ='text' name='inTime' id='inTime' size='10'  value='"+ empDailyAttendanceDBObj.inTime+"'/>(HH:MM)</td>");
		 	  		  else
		 	  			  out.print("<td align='left'><input type='text' name='inTime' id='inTime' size='10' value=''/>(HH:MM)</td>");
		 	  			  
		 	  		  %>
		 	  		  <td>Out Time</td>
		 	  		  <%
		 	  		     if( empDailyAttendanceDBObj.outTime !=null)
		 	  			  out.println("<td><input type ='text' name='outTime' id='outTime' size='10'  value='"+ empDailyAttendanceDBObj.outTime+"'/>(HH:MM)</td>");
		 	  		      else
		 	  			  out.print("<td align='left'><input type='text' name='outTime' id='outTime' size='10' value=''/>(HH:MM)</td>");
		 	  			  
		 	  		   %>
		 	  		  </tr>
		 	  		  <tr>
		 	  		       <td> Remark </td>
		 	  		       <td colspan=3><input type='text' name='remark' id='remark' size='85' value=''/>
		 	  		       </td>
		 	  		  <tr>
		 	  		      <td colspan=4 align='center'>
		 	  		     	<input type='submit' name='submit' id='submit' size ='10' value='submit Detail'/>
		 	  		     	<input type='hidden' name='actionSubmit' id='actionSubmit' size='10' value='employeeDailyAttendanceSubmit'/>
		 	  		     </td>
		 	  		   </tr>
		 	  		    		  
		 	  		
		 	  	</form>
		 	  	
		 	  </table>
		 	         <p>&nbsp;</p>
		 	          <hr width=100% color=#843F8B>
		 	           </td>
		 	  
		      </tr>
		      <tr>
		           <td colspan="2"><%@include file= "/jsp/hrmsFooter.jsp" %></td>
		        </tr>   
		 
	</table> 
	</body> 
	</html>