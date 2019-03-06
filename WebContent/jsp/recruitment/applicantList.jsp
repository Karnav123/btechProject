<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session = "true" %>

<%@ page import = "java.io.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.kudos.recruitment.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>localhost:hrsolution.com/Applicant List</title>
		<link rel="stylesheet" href=".../css/mystyle.css" type="text/css" />
	</head>
	
	<body>
		<table width="900" border="0" align="center">
			<tr>
				<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
			</tr>
			<tr>
				<td width="150" ><%@ include file="../hrmsMainMenu.jsp" %></td>
				<td  width="750" valign="top" >
					<div align="center" Class=boldBlack>List Of Applicants</div>
					<hr bgcolor="#34383E" >
					<table border="0" align="top" width=100% >
					<%
						String dBOperation = "";
						dBOperation = (String)session.getAttribute("dBOperation");
						System.out.println("**********i m in  dBOperation- " +dBOperation);
					%>	
						<tr>
							<td bgcolor="#843f8b" align="center">Applicant ID</td>
							<td bgcolor="#843f8b" align="center">Name</td>
							<td bgcolor="#843f8b" align="center">Work Experience</td>
							<td bgcolor="#843f8b" align="center" colspan="2">Skill</td>
							<td bgcolor="#843f8b" align="center">Highest Degree</td>
						<%
							System.out.println("**********first If condition Start" );
							if(dBOperation != null && (dBOperation.equals("callForWritten") || dBOperation.equals("call") || dBOperation.equals("remove"))) {	
								
								
						%>
							<td bgcolor="#843f8b" align="center">Select</td>
							<td bgcolor="#843f8b" align="center">Detail</td>
						<%
							} else {
						%>
							<td bgcolor="#843f8b" align="center">Edit</td>
							<td bgcolor="#843f8b" align="center">Delete</td>
							<td bgcolor="#843f8b" align="center">Detail</td>
						<% } 
							System.out.println("**********first If and else condition closed" );
						%>
						</tr>
						
					<%
						ArrayList applicantList = new  ArrayList();
						applicantList =(ArrayList)session.getAttribute("applicantList");
						System.out.println("**********i m in  applicantList =" +applicantList);
						ArrayList applicantTestList = new  ArrayList();
						applicantTestList =(ArrayList)session.getAttribute("applicantTestList");
						System.out.println("**********i m in  applicantTestList =" +applicantTestList);
						System.out.println("**********2nd If condition Start" );
						if(applicantTestList!=null){
						System.out.println("size of applicantTestList-"+applicantTestList.size());
						}
						if(applicantList!=null){
						System.out.println("size of applicantList-"+applicantList.size());
						}
						if(applicantList != null && applicantList.size() > 0) {
							System.out.println("**********first for  loop Start" );
							for( int size = 1; size <= applicantList.size(); size++) {
								System.out.println("**********i m in  size.size() " +size);
								HrmsApplicant applicantDBObj = new HrmsApplicant();
								applicantDBObj = (HrmsApplicant)applicantList.get(size-1);
					%>
						<form name="form1" method="post">
							<tr bgcolor='#843f8b' hight=18>
								<td align="center"><%=applicantDBObj.applicantId %></td>
								<td align="left"><%=applicantDBObj.applicantName %></td>
								<td align="center"><%=applicantDBObj.workExp %></td>
								<td align="left" colspan="2"><%=applicantDBObj.skill %></td>
								<td align="center"><%=applicantDBObj.highestDegree %></td>
							<%
								if(dBOperation != null && (dBOperation.equals("callForWritten") || dBOperation.equals("call") || dBOperation.equals("remove") )) {
							%>
								<td bgcolor="#843f8b" align="center">
									<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=call&&applicantId=<%=applicantDBObj.applicantId %>' class=yellowlink>Select</a>
								</td>
								<td bgcolor="#843f8b" align="center">
									<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=detail&&applicantId=<%=applicantDBObj.applicantId %>' class=yellowlink>Detail</a>
								</td>
							<%
								} else {
							%>
								<td bgcolor="#843f8b" align="center">
									<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantServlet?dBOperation=edit&&applicantId=<%=applicantDBObj.applicantId %>' class=yellowlink>Edit</a>
								</td>
								<td bgcolor="#843f8b" align="center">
									<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantServlet?dBOperation=delete&&applicantId=<%=applicantDBObj.applicantId %>' class=yellowlink>Delete</a>
								</td>
								<td bgcolor="#843f8b" align="center">
									<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantServlet?dBOperation=detail&&applicantId=<%=applicantDBObj.applicantId %>' class=yellowlink>Detail</a>
								</td>
							<%
								}
							%>
							</tr>
							<%
							}
							//change at th else loop
							
						System.out.println("**********2nd If and else condition closed" );
						
						System.out.println("**********applicantTestList >>" +applicantTestList);
						if (applicantTestList != null && applicantTestList.size() > 0) {
							
					%>	
							<th bgcolor="#843f8b" align="center" colspan="8"><font color=white>The Selected Application</font></th>
						<%
							//System.out.println("**********i m in  size1.size() " + size1);
							for (int size1 = 1; size1 <= applicantTestList.size(); size1++) {
								HrmsApplicant applicantDBObj = new HrmsApplicant();
								applicantDBObj = (HrmsApplicant)applicantTestList.get(size1-1);
							
						%>
							<tr bgcolor='#C58DC4' >
								<td align="center">
								<%=applicantDBObj.applicantId
										
								%>
								</td>
								<td align="left"><%=applicantDBObj.applicantName %></td>
								<td align="center"><%=applicantDBObj.workExp %></td>
								<td align="left" colspan="2"><%=applicantDBObj.skill %></td>
								<td align="center"><%=applicantDBObj.highestDegree %></td>
							<%
							System.out.println("**********applicantTestList >>" +applicantDBObj.applicantId);
								System.out.println("**********i m in  dBOperation.size() " + dBOperation);
								if( dBOperation != null && ( dBOperation.equals("callForWritten") || dBOperation.equals("call") || dBOperation.equals("remove") ) ) {
								
							
							%>
								<td bgcolor="#34383E" align="center" colspan="2">
									<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=remove&&applicantId=<%=applicantDBObj.applicantId %>' class=yellowlink>Remove </a>
								</td>
							<%
								}
							%>
							</tr>
							<%
							}
						}// change here
							
							System.out.println("**********i m in  applicantTestList.size() " + applicantTestList);
							if ( dBOperation != null && ( dBOperation.equals("call") || dBOperation.equals("remove") || (applicantTestList != null &&  applicantTestList.size() > 0 ) ) ) { 
							
							%>
							<tr>
								<td align="center" colspan="8">
									<input type="submit" name='submit' id='submit' size='10' value='Enter The Test detail'/>
								</td>
									<input type="hidden" name='actionSelect' id='actionSelect' size='10' value='applicantCallForWrittenTestSubmit'/>
								
							</tr>
							<%
							//}
							}//close the if condition
						//} close for loop
						//}
						} else {
							out.print("Applicant does not Exit!!!");
						}
							%>
							
						</form>
					</table>
				</td>
			</tr>
		</table>
	
	</body>

</html>