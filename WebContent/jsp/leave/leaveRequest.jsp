<%@ page language="java" %>
<%@ page session="true" %>
<html>
<head>
<title>www.hrsolutions.com/Leave Request</title>
   <link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
   <script type="text/javascript" language="javascript" src="scripts/validation.js"></script>
</head>
<body>
<table width="900" border="0" align="center">
<tr>
	<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
</tr>
<tr>
<td width="150" ><%@ include file="/jsp/hrmsMainMenu.jsp" %></td>  
<td width ="750" valign="top" align = "center">
 <table  border="0" align="top" width="600">
  <tr>
  <td colspan='4' height=20 height=20 bgcolor ='#34383E' class=whitetext align=center>Leave Application</td>
  </tr>
  <tr>
  <td >Request ID</td>
  <td align='left'><input type='text' name='reqId' id='reqId 'size ='10' value=''/> </td>
  <td >Date(YYYY-MM-DD)</td>
  <td align='left'><input type='text' name='todayDate' id='todayDate 'size ='10' value=''/> </td>
  </tr>
  <tr>
  <td>Employee Id</td>
  <td align='left'><input type='text' name='empId' id='empId' size ='10' value=''/> </td>
  <td>Name</td>
  <td align='left'><input type='text' name='empName' id='empName' size ='20' value=''/> </td>
  </tr>
  <tr>
  <td>Designation</td>
  <td align='left'><input type='text' name='levelId' id='levelId' size ='10' value=''/> </td>
  <td>Department</td>
  <td align='left'><input type='text' name='deptId' id='deptId' size ='10' value=''/> </td>
  </tr>   
  <tr>
  <td colspan='4' height=20 bgcolor ='#34383E' class=whitetext align=center>Leave Detail</td>
  </tr>
  <tr>
  <td>From<br>(YYYY-MM-DD)</td>
  <td>To(YYYY-MM-DD)</td>
  <td>Days</td>
  <td>&nbsp;</td>
  </tr>
  <tr>
  <td align='left' ><input type='text' name='fromDate' id='fromDate' size ='10' value=''/> </td>
  <td align='left' ><input type='text' name='toDate' id='toDate' size ='10' value=''/> </td>
  <td align='left'><input type='text' name='days' id='days' size ='5' value=''/> </td>
  <td>&nbsp;</td>
  </tr>
  <tr>
  <td colspan=2>Reason For Leave(100 letters only)</td>
  <td align='left' colspan=2><textarea name='reason' id='reason' col='80' rows='4' value=''/></textarea></td>
  </tr>
  <tr>
  <td colspan=2>Type Of Leave Applied For></td>
  <td align='left' colspan=2><SELECT name='leaveType' >
  <OPTION VALUE=></OPTION> <OPTION VALUE=CL>CL</OPTION><OPTION VALUE=SL>SL</OPTION></SELECT>
  </td>
  </tr>
  <tr>
  <td colspan='4' height=20 bgcolor ='#34383E' class=whitetext align=center>Major Activity to Be Handled During The Leave Applied</td>
  </td>
  </tr>  
  <tr>
  <td align=right>S.No.</td>
  <td>Activity Deatils</td>
  <td>Name Of the Person Responsible</td>
  <td>Details</td>
  </tr>
  <tr>
  <td align=right>1.</td>
  <td align='left'><input type='text' name='activity1' id='activity1' size ='20' value=''/> </td>
  <td align='left'><input type='text' name='person1' id='person1' size ='20' value=''/> </td>
  <td align='left'><input type='text' name='detail1' id='detail1' size ='20' value=''/> </td>
  </tr><tr>
  <td align=right>2.</td>
  <td align='left'><input type='text' name='activity2' id='activity2' size ='20' value=''/> </td>
  <td align='left'><input type='text' name='person2' id='person2' size ='20' value=''/> </td>
  <td align='left'><input type='text' name='detail2' id='detail2' size ='20' value=''/> </td>
  </tr>
  <tr>
  <td align=right>3.</td>
  <td align='left'><input type='text' name='activity3' id='activity3' size ='20' value=''/> </td>
  <td align='left'><input type='text' name='person3' id='person3' size ='20' value=''/> </td>
  <td align='left'><input type='text' name='detail3' id='detail3' size ='20' value=''/> </td>
  </tr>
  <tr>
  <td colspan='4' height=20 bgcolor ='#34383E' class=whitetext align=center>Contact Details of the applicant during the leave period</td>
  </tr>
  <tr>
  <td>Address</td>
  <td align='left' colspan='2'><input type='text' name='address' id='address' size ='20' value=''/></td>
  <td></td>
  </tr>
  <tr>
  <td>Remark</td>
  <td align='left' colspan='2'><input type='text' name='remark' id='remark' size ='20' value=''/> </td>
  <td></td>
  </tr>
  <tr>
  <td align='center' colspan='4'>
  <input type='submit' name='submit' id='submit' size ='10' value='Submit'/>
  <input type='hidden' name='actionSubmit' id='actionSsubmit' size ='10' value='employeeLeaveReqSubmit'/>
  </td>
  </tr>
</table>
</td>
</tr>
<tr>
    <td colspan="2"><%@include file="/jsp/hrmsFooter.jsp"%></td>
</tr>
</table>
</body>                                                                                                             
</html>