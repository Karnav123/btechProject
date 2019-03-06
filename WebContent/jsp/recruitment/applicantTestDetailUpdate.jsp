<%@ page language="java" %>
<%@ page session="true" %>
<%@ page  import="com.kudos.recruitment.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="java.util.*" %>
<html>
<head>
<title>www.hrsolutions.com/Test Detail Update</title>
<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
</head>
<body>
<table width="900" border="0" align="center"> 
<tr>
	<td colspan="2" ><%@ include file="/jsp/ersHeader.jsp" %></td>
</tr>
<tr>
<td width="150"  valign="top"><%@ include file="/jsp/hrmsMainMenu.jsp" %></td>  
<td width ="750" valign="top">
<p>&nbsp;</p>
<div align=center class=boldblack>Update Result</div>
<hr width=500 color=#843F8B>
<table  border="0"  width=300 align=center >
<form name="form1" method="post">
<%
   System.out.println("..............applicantTestDetailUpdate.jsp start.........................");
	HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
	applicantTestDetailDBObj = (HrmsApplicantTestDetail)session.getAttribute("applicantTestDetailDBObj");
%>
	<input type='hidden' name='applicantId' id='applicantId' size ='20' value='<%=applicantTestDetailDBObj.applicantId%>' />
	<input type='hidden' name='testId' id='testId' size ='20' value='<%=applicantTestDetailDBObj.testId%>' />
	<input type='hidden' name='testName' id='testName' size ='20' value='<%=applicantTestDetailDBObj.testName%>' />
	<tr><td>Status in Test</td>
	<td>
	<select name='presentStatus'>
	<option value=Absent >Absent</option>
	<option value=Present selected>Present</option>
	</select>
	</td></tr>
	<tr><td>Total Marks</td>
	<td>
	<input type='hidden' name='totalMarks' id='totalMarks' size ='20' value='"+applicantTestDetailDBObj.totalMarks+"'/>
	<%=applicantTestDetailDBObj.totalMarks%>
	</td></tr>
	<tr>
	<td> Marks Gained</td>
	<td><%
	if( applicantTestDetailDBObj.marksGained != 0 )
    out.println("<input type='text' name='marksGained' id='marksGained' size= '5' value = '"+applicantTestDetailDBObj.marksGained+"' />");
	else
	out.println("<input type='text' name='marksGained' id='marksGained' size= '5' value = '0' />");
   %>
	</td></tr>	
	<tr><td>Result</td>
	<td>
	<%
	if( !applicantTestDetailDBObj.passFail.equals("") )
	out.println("<input type='text' name='passFail' id='passFail' size ='5' value='"+applicantTestDetailDBObj.passFail+"' />");
	else{
	out.println("<SELECT name='passFail' ><option value=></option><option value=Pass>Pass</option><option value=Fail>Fail</option></SELECT></td>");
	}
	%>
	</td></tr>
	<tr><td colospan=2 align=center>
	
	<input type='submit' name='submit' id='submit' value='Update Detail'/>
	<input type='hidden' name='actionSubmit' id='actionSubmit' size ='10' value='hrmsApplicantWrittenTestDetailUpdateSubmit'/>
	</td></tr>
</table></td>
</tr>
<tr>
    <td colspan="2"><%@include file="/jsp/hrmsFooter.jsp"%></td>
</tr>
</table></body></html>

