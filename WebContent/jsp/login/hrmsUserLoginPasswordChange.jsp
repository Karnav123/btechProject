<html>
	<head><title>www.hrsolutions.com/Change Password</title>
		<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
		<script type="text/javascript" language="javascript" src="scripts/validation.js"></script>
	</head>
	<body>
		<form name = "changePassword" method="post" onSubmit="return validateChangePassword(this);">
			<table width="100%" >
				<tr>
					<td colspan="2">
					<%@ include file="/jsp/ersHeader.jsp" %>
					</td>
				</tr>
				<tr >
					<td colspan="2">
						<table border ="0"  align="center" >
							<tr>
								<td>User Id</td>
    							<td align="center" ><input type ="text" name="userId" id="userId" value="" /></td>
    						</tr>
							<tr>
								<td>User Name</td>
								<td align="center" ><input type ="text" name="userName" id="userName" value="" /></td>
							</tr>
							<tr>
								<td>Current Password</td>
								<td align="center" ><input type ="password" name="oldPassword" id="oldPassword" value=""/></td>
							</tr>
							<tr>
								<td>New Password</td>
								<td align="center" >
									<input type ="password" name="newPassword" id="newPassword" value="" />
								</td>
							</tr>
							<tr>
								<td>Retype Password</td>
								<td align="center" >
									<input type ="password" name="reTypePassword" id="reTypePassword" value="" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<input type ="submit" name="submit" id="submit" value="Submit" />
									<input type="hidden" name="actionSubmit" id="actionSubmit" value="loginPasswordChangeSubmit"/>
								</td>
							</tr>
							<%	String msg  = (String)session.getAttribute("lErrorMsg");
								if ( msg != null && msg.length() > 0 ){	
							%>
							<tr>
								<td colspan="2" align="right">
							<%	out.println("<div class=boldred align=center>"+msg+"</div>"); %>
								</td>
							</tr>
							<% } %>
						</table>
					</td>
				</tr>
			<tr><td colspan="2"><%@include file="../hrmsFooter.jsp"%></td></tr>
		</table>
	</form>
</body>
</html>
 