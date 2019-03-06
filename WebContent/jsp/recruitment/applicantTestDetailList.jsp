<%@ page language="java" %>
<%@ page session="true" %>
<%@ page  import="com.kudos.recruitment.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="java.util.*" %>
<html>
	<head>
		<title>www.hrsolutions.com/Applicant Test Detail List</title>
		<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
	</head>
	
	<body>
		<table width="900" border="0" align="center"> 
			<tr>
				<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
			</tr>
			<tr>
				<td width="150">
					<%@ include file="/jsp/hrmsMainMenu.jsp" %>
				</td>  
				<td width ="750" valign="top">
					<table  border="0"  width=100% >
					<% 
						String dBOperation = "";
						dBOperation = (String)session.getAttribute("dBOperation");
						System.out.println("vivek......dbOperation-"+dBOperation);
					%>
						<tr>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Test Id</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Test Name</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Applicant Id</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' colspan='2' >Applicant Name</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Test Date</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Test Time</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Present Status</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Total Marks</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Marks Gained</th>
							<td class=whitetext bgcolor ='#843F8B' align='center' >Pass Fail</th>
						    <td class=whitetext bgcolor ='#843F8B' align='center' >Edit</th>    
						</tr>
					<%
						ArrayList applicantTestDetailList = new ArrayList();
						applicantTestDetailList = (ArrayList)session.getAttribute("ApplicantTestDetailList");
						
						ArrayList selectApplicantTechnicalList = new ArrayList();
						if(applicantTestDetailList!=null){
						System.out.println("size of applicantTestDetailList-.................==="+applicantTestDetailList);
						}
						selectApplicantTechnicalList = (ArrayList)session.getAttribute("selectApplicantTechnicalList");
						//System.out.println("applicantTestDetailList====>>>++++>>>>"+applicantTestDetailList +"," + applicantTestDetailList.size());
						
						if ( applicantTestDetailList != null && applicantTestDetailList.size() > 0 ) {
							
							for ( int size = 1; size <= applicantTestDetailList.size() ; size++ ) {
															
								HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
								applicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestDetailList.get(size-1);
					%>
					<form name="form1" method="post">		  
					<tr bgcolor ='#843f8b'>
					
						<td align='center'  ><%=applicantTestDetailDBObj.testId%>
							<input type='hidden' name='testId' id='testId' size ='20' value='<%=applicantTestDetailDBObj.testId%>'/>
						</td>
						
						<td align='left'  ><%=applicantTestDetailDBObj.testName%>
							<input type='hidden' name='testName' id='testName' size ='20' value='<%=applicantTestDetailDBObj.testName%>'/>
						</td>
						
						<td align='center'  ><%=applicantTestDetailDBObj.applicantId%>
							<input type='hidden' name='aplicantId' id='aplicantId' size ='20' value='<%=applicantTestDetailDBObj.applicantId%>'/>
						</td>
						
						<td align='left' colspan='2' ><%=applicantTestDetailDBObj.applicantName%>
							<input type='hidden' name='applicantName' id='applicantName' size ='20' value='<%=applicantTestDetailDBObj.applicantName%>'/>
						</td>
						
						<td align='center' ><%=applicantTestDetailDBObj.testDate%>
							<input type='hidden' name='testDate' id='testDate' size ='20' value='<%=applicantTestDetailDBObj.testDate%>'/>
						</td>
						
						<td align='center' ><%=applicantTestDetailDBObj.testTime%>
							<input type='hidden' name='testTime' id='testTime' size ='20' value='<%=applicantTestDetailDBObj.testTime%>'/>
						</td>
							<%
								if( applicantTestDetailDBObj.presentStatus != null ) 
									 out.println("<td align='center'><input type='text' disabled='disabled' name='presentStatus' id='presentStatus 'size ='5' value='"+applicantTestDetailDBObj.presentStatus+"' /> </td>");
									         else{
									 out.println("<td align='center'  ><input type='text' disabled='disabled' name='presentStatus' id='presentStatus 'size ='5' value='' /></td>");
									 }
							%>
						<td align='center'><%=applicantTestDetailDBObj.totalMarks%>
							<input type='hidden' name='totalMarks' id='totalMarks' size ='20' value='<%=applicantTestDetailDBObj.totalMarks%>'/>
						</td>
						<td align='center'>
							<input type='text' disabled='disabled' name='marksGained' id='marksGained' size= '5' value = '<%=applicantTestDetailDBObj.marksGained%>' />
						</td>
						
						<%
							if( applicantTestDetailDBObj.passFail != null )
							out.println("<td align='center'><input type='text' disabled='disabled' name='passFail' id='passFail 'size ='5' value='"+applicantTestDetailDBObj.passFail+"' /> </td>");
							else{
							out.println("<td align='center'><input type='text' disabled='disabled' name='passFail' id='passFail 'size ='5' value='' /> </td>");
							}
							//out.println("<td align='center'><a href='/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?editForm=editDetail&applicantId="+applicantTestDetailDBObj.applicantId+"'class=yellowlink>edit </a> </td>");
					
							if( dBOperation != null && ( dBOperation.equals("shortListAfterWritten") || dBOperation.equals("applicantCallForTechnical") || dBOperation.equals("delete")) ){
							  out.println("<td align='center' bgcolor ='#843F8B'>");
							  out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=applicantCallForTechnical&&applicantId="+applicantTestDetailDBObj.applicantId+"' class=yellowlink>Select For Technical </a>");
							  out.println("</td >");
							  
							}
							else if( dBOperation != null && ( dBOperation.equals("shortListAfterTechnical") || dBOperation.equals("applicantCallForHr") || dBOperation.equals("discard")) ){
								  out.println("<td align='center' bgcolor ='#843F8B'>");
								  out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=applicantCallForHr&&applicantId="+applicantTestDetailDBObj.applicantId+"' class=yellowlink>Select For HR </a>");
								  out.println("</td >");
							}								
							
							else
							if( dBOperation != null && ( dBOperation.equals("shortListAfterHr") || dBOperation.equals("applicantCallForFinal") || dBOperation.equals("discardForFinal")) )
							{
							  out.println("<td align='center' bgcolor ='#843F8B'>");
							  out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=applicantCallForFinal&&applicantId="+applicantTestDetailDBObj.applicantId+"' class=yellowlink>Select For FInal </a>");
							  out.println("</td >");
							  
							}
							else  
							if( dBOperation != null && ( dBOperation.equals("updateWrittenPerformance") || dBOperation.equals("updateWrittenRecord")) )
							{
							  out.println("<td align='center' bgcolor ='#843F8B'>");
							  out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=updateWrittenRecord&&applicantId="+applicantTestDetailDBObj.applicantId+" ' class=yellowlink>Edit </a>");
							  out.println("</td >");
							}
							else  
							if( dBOperation != null && ( dBOperation.equals("updateTechnicalPerformance") || dBOperation.equals("updateTechnicalRecord")) )
							{
							  out.println("<td align='center' bgcolor ='#843F8B'>");
							  out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=updateTechnicalRecord&&applicantId="+applicantTestDetailDBObj.applicantId+" ' class=yellowlink>Edit </a>");
							  out.println("</td >");
							}
							else  
							if( dBOperation != null && ( dBOperation.equals("updateHrPerformance") || dBOperation.equals("updateHrRecord")) )
							{
							  out.println("<td align='center' bgcolor ='#843F8B'>");
							  out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=updateHrRecord&&applicantId="+applicantTestDetailDBObj.applicantId+" ' class=yellowlink>Edit </a>");
							  out.println("</td >");
							}	     
								  }
								} 
								else{
							out.println("Applicant does not exist!!!");
								}
								     
								if( selectApplicantTechnicalList != null && selectApplicantTechnicalList.size() > 0)
								{
								out.println("<tr>");
								out.println("<td class=whitetext bgcolor ='#843F8B' align='center' colspan='13'>The Selected Applicant</th>");
								out.println("</tr>");
								for ( int size = 1; size <= selectApplicantTechnicalList.size() ; size++ ){
					
							HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
							applicantTestDetailDBObj = (HrmsApplicantTestDetail)selectApplicantTechnicalList.get(size-1);
							  
							out.println("<tr bgcolor ='dfdfd'>");
							out.println("<td align='center'  >"+applicantTestDetailDBObj.testId+"</td>");
							out.println("<td align='center'  >"+applicantTestDetailDBObj.testName+" </td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.applicantId+"</td>");	  
							out.println("<td align='center' colspan='2' >"+applicantTestDetailDBObj.applicantName+"</td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.testDate+"</td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.testTime+"</td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.presentStatus+"</td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.totalMarks+"</td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.marksGained+"</td>");
							out.println("<td align='center' >"+applicantTestDetailDBObj.passFail+"</td>");
					
								if( dBOperation != null && ( dBOperation.equals("shortListAfterWritten") || dBOperation.equals("shortListAfterTechnical") || dBOperation.equals("applicantCallForTechnical") || dBOperation.equals("delete") )){
							out.println("<td align='center' colspan='2' bgcolor ='#843F8B'>");
							out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=delete&&applicantId="+applicantTestDetailDBObj.applicantId+" ' class=yellowlink>Delete</a>");
							out.println("</td >");
								}
								else
								if( dBOperation != null && ( dBOperation.equals("shortListAfterTechnical") || dBOperation.equals("applicantCallForHr") || dBOperation.equals("discard") )){
							out.println("<td align='center' colspan='2' bgcolor ='#843F8B'>");
							out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=discard&&applicantId="+applicantTestDetailDBObj.applicantId+" ' class=yellowlink>Discard</a>");
							out.println("</td >");
								}
								else
								if( dBOperation != null && ( dBOperation.equals("shortListAfterHr") || dBOperation.equals("discardForFinal") || dBOperation.equals("applicantCallForFinal")  )){
							out.println("<td align='center' colspan='2' bgcolor ='#843F8B'>");
							out.println("<a href='http://localhost:8080/EnterprizeResourceSystem/hrmsApplicantTestDetailServlet?dBOperation=discardForFinal&&applicantId="+applicantTestDetailDBObj.applicantId+" ' class=yellowlink>Remove</a>");
							out.println("</td >");
							}
							out.println("</tr>");
								}
					
								if( dBOperation != null && (  dBOperation.equals("delete") || dBOperation.equals("applicantCallForTechnical") )){
							out.println("<tr>");
							out.println("<td align='center' colspan='13'><input type='submit' name='submit' id='submit' size ='10' value='Call For Technical'/> </td>");
							out.println("<input type='hidden' name='actionSubmit' id='actionSubmit' size ='10' value='applicantCallTechnicalDetailSubmit'/> ");
							out.println("</tr>");
								}
								else
								if( dBOperation != null && (  dBOperation.equals("discard") || dBOperation.equals("applicantCallForHr") )){
							out.println("<tr>");
							out.println("<td align='center' colspan='13'><input type='submit' name='submit' id='submit' size ='10' value='Call For HR'/> </td>");
							out.println("<input type='hidden' name='actionSubmit' id='actionSubmit' size ='10' value='applicantCallHrDetailSubmit'/> ");
							out.println("</tr>");
								}
								else
								if( dBOperation != null && (  dBOperation.equals("discardForFinal") || dBOperation.equals("applicantCallForFinal") )){
							out.println("<tr>");
							out.println("<td align='center' colspan='13'><input type='submit' name='submit' id='submit' size ='10' value='Select Final'/> </td>");
							out.println("<input type='hidden' name='actionSubmit' id='actionSubmit' size ='10' value='applicantSelectForFinalSubmit'/> ");
							out.println("</tr>");
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

