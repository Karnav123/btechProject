<%@ page language="java" %>
<%@ page session="true" %>
<html>
	<head>
		<title>www.hrsolutions.com</title>
		<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
	</head>

<body>
<table width="900" border="0" align="center"> 
<tr>
	<td colspan="2" ><%@ include file="/jsp/ersHeader.jsp" %></td>
</tr>
<tr>
<td width="150" valign="top"><%@ include file="/jsp/hrmsMainMenu.jsp" %></td>  
<td width ="750" valign="top">
<p>&nbsp;</p>
<hr width=500 color=#843f8b>
<table  border="0"  width=500 align=center>
  <form name="form1" method="post">
  <tr>
  <td bgcolor ='#843f8b' colspan='4' align=center class=whitetext><b>Test Detail</b></td>
  </tr>
  <tr>
  <td >Test Id</td>
  <td align='left'><input type='text' name='testId' id='testId 'size ='10' value=''/></td>
  <td>Test Name</td>
  <td align='left'><input type='text' name='testName' id='testName' size ='10' value=''/></td>
  </tr>
  <tr> 
  <td>Test Date(yyyy-mm-dd)</td>
  <td align='left'><input type='text' name='testDate' id='testDate 'size ='10' value='' /> </td>
  <td>Test Time(hh:mm)</td>
  <td align='left'><input type='text' name='testTime' id='testTime' size ='10' value=''/> </td>
  </tr>
  <tr>
  <td>Total Marks </td>
  <td align='left' ><input type='text' name='totalMarks' id='totalMarks' size ='10' value=''/></td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  </tr>
  <tr>
  <td align='center' colspan=4>
  <input type='submit' name='submit' id='submit' size ='10' value='Submit Detail'/>
  <input type='hidden' name='actionSubmit' id='actionSubmit' size ='10' value='hrmsApplicantWrittenTestDetailSubmit'/>
  </td>
  </tr>
</table>
<hr width=500 color=#843F8B>
</td>
</tr>
<tr>
    <td colspan="2"><%@include file="/jsp/hrmsFooter.jsp"%></td>
</tr>
</table></body></html>