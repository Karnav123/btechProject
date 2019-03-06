<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page  import="com.kudos.recruitment.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <base href="<%=basePath%>">
    
    <title>www.hrsolutions.com/Applicant Test Details</title>
	<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
  <form name="form2">
  
       <% 
       HrmsApplicantTestDetail appId=new HrmsApplicantTestDetail();
       appId=(HrmsApplicantTestDetail)session.getAttribute("appId");
       
       %>
     <table width="900" border="0" align="center">
     <div align="center">List of candidates to be selected </div>
     <hr width=400 color=#843f8b/>
      <tr>
      <td>Test ID</td><td><input type="text" readonly="readonly" name="testId" id="testId" size="10" value="<%=appId.testId %>"/></td>
      <td>Test Name</td><td><input type="text" readonly="readonly" name="testName" id="testName" value="<%=appId.testName %>"/></td>
      </tr>
      <tr>
      <td>Applicant ID</td><td><input type="text" readonly="readonly" id="applicantId" name="applicantId" size="10" value="<%=appId.applicantId %>"/></td>
      <td>Applicant Name</td><td><input type="text" readonly="readonly" id="applicantName" name="applicantName" value="<%=appId.applicantName %>"/></td>
      </tr>
      <tr>
       <td>Test Date</td><td><input type="text" name="testDate" id="testDate" size="10" value="<%=appId.testDate %>"/></td>
       <td>Test Time</td><td><input type="text" name="testTime" id="testTime" size="10" value="<%=appId.testTime %>"/></td>
      </tr>
      <tr>
      <td>Total Marks</td><td><input type="text" name="totalMarks"  id="totalMarks" size="10" value="<%=appId.totalMarks %>"/></td>
      <td>Marks Gained</td><td><input type="text" name="marksGained" id="marksGained" size="10" value="<%=appId.marksGained %>"/></td>
      
      </tr>
      <h></h>
      <tr><td>Status in Test</td>
      <td>
	  <select name='presentStatus'>
	    <option value=Absent >Absent</option>
	    <option value=Present selected>Present</option>
	  </select>
	</td>
	<td>Pass OR Fail</td>	
      <td>
	  <select name='presentStatus'>
	    <option value=pass >Pass</option>
	    <option value=fail selected>Fail</option>
	  </select>
	 </td><td>&nbsp;</td></tr>
	 <tr><td></td></tr>
	 
	 <tr><td colspan="5" align="center"><input type="submit" name="submit" id="submit" size="20" value="Submit"/>
	 <input type="hidden" name="actionSubmit" id="actionSubmit" size="20" value="hrmsTestUpdateDetails"/></td></tr>
	 
     </table>&nbsp;
     <hr width=400 color=#843F8B/>
  
  </form>
  </table>
  </body>
</html>
