<%@ page language="java" %>
<%@ page session="true" %>
<%@ page  import="com.kudos.recruitment.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="java.util.*" %>
<html>
	<head>
		<title>www.hrsolutions.com/Selected Candidates</title>
		<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
	</head>
	
	<body>
		<table width="900" border="0" align="center"> 
			<tr>
				<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
			</tr>
			<tr>
				<td width="150"><%@ include file="/jsp/hrmsMainMenu.jsp" %></td>  
				<td width ="750" valign="top">
			<p>&nbsp;</p>
			<div align=center class=boldblack>List of Selected Candidates to be Appointed.</div>
			<hr width=400 color=#34383E>
			<table  border="0"  width=100% >
			<% 
			    String dbopr = "";
			    dbopr = (String)session.getAttribute("dbopr");
			%>
				<tr height=20>
				<td class=whitetext bgcolor ='#843f8b' align='center' colspan='2'>Applicant Id</td>
				<td class=whitetext bgcolor ='#843f8b' align='center' colspan='2' >Applicant Name</td>
				<td class=whitetext bgcolor ='#843f8b' align='center' colspan='2'></td>
				</tr>
			<%
				ArrayList applicantTestDetailList = new ArrayList();
				applicantTestDetailList = (ArrayList)session.getAttribute("applicantTestDetailList");
				ArrayList selectApplicantTechnicalList = new ArrayList();
				selectApplicantTechnicalList = (ArrayList)session.getAttribute("selectApplicantTechnicalList");
				if ( applicantTestDetailList != null && applicantTestDetailList.size() > 0 ){
					for ( int size = 1; size <= applicantTestDetailList.size() ; size++ ){
					HrmsApplicantTestDetail applicantTestDetailDBObj = new  HrmsApplicantTestDetail();
				applicantTestDetailDBObj = ( HrmsApplicantTestDetail)applicantTestDetailList.get(size-1);
			%>
						<form name="form1" method="post">
						<tr bgcolor ='#C58DC'>
						
						<input type='hidden' name='test_id' id='test_id' size ='20' value='<%=applicantTestDetailDBObj.testId%>'/>
						<input type='hidden' name='test_name' id='test_name' size ='20' value='<%=applicantTestDetailDBObj.testName%>'/>
						<input type='hidden' name='aplicant_id' id='aplicant_id' size ='20' value='<%=applicantTestDetailDBObj.applicantId%>'/>
						<td align='center' colspan='2' ><%=applicantTestDetailDBObj.applicantId%></td>
						<input type='hidden' name='applicant_name' id='applicant_name' size ='20' value='<%=applicantTestDetailDBObj.applicantName%>'/>
						<td align='center' colspan='2' ><%=applicantTestDetailDBObj.applicantName%></td>
						<input type='hidden' name='test_date' id='test_date' size ='20' value='<%=applicantTestDetailDBObj.testDate%>'/>
						<input type='hidden' name='test_time' id='test_time' size ='20' value='<%=applicantTestDetailDBObj.testTime%>'/>
						<td align='center' bgcolor="#843f8b">
						<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=detail&&applicantId=<%=applicantTestDetailDBObj.applicantId %>' class=yellowlink>Detail </a>
						</td >
						<td align='center' bgcolor="#843f8b">
						<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=issueOfferLetter&&applicantId=<%=applicantTestDetailDBObj.applicantId %>' class=yellowlink>Issue OFFER Letter </a>
						</td >
						<%
					}
				}
				out.println("</tr>");
			%>
			</table>
			</td>
			</tr>
			<tr>
			    <td colspan="2"><%@include file="/jsp/hrmsFooter.jsp"%></td>
			</tr>
		</table>
	</body>
</html>