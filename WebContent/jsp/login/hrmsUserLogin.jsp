<html>
<head><title> [] User Login</title>
	<link rel="stylesheet" href="../css/mystyle.css" type="text/css" />
	<script type="text/javascript" language="javascript" src="scripts/validation.js"></script>
</head>
<body>
	<form name =login method="post" ACTION="/EnterprizeResourceSystem/hrmsUserLoginServlet" onSubmit="return validateLogin(this);">
	<table width="900" align=center>
	<tr>
		<td colspan="2"><%@ include file="/jsp/ersHeader.jsp" %></td>
    </tr>
	<tr >
		<td colspan="2">
			<table border ="0"  align="center" >
				<tr>
				<!-- **** -->
				
					<td>User Id</td>
					<td align="center" ><input type ="text" name="userId" id="userId" value="" /></td>
				</tr>
				<tr>
					<td>User Name</td>
					<td align="center" ><input type ="text" name="userName" id="userName" value="" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td align="center" ><input type ="password" name="userPassword" id="userPassword" value="" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center" ><input type ="submit" name="submit" id="submit" value="Log-In." />
		
						<input type="hidden" name="actionSubmit" id="actionSubmit" value="hrmsUserLoginSubmit"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type ="submit" name="submit" id="submit" value="Change Password" />
						<input type="hidden" name="actionChangePassword" id="actionChangePassword" value="hrmsChangePasswordSubmit"/>
					</td>
				</tr>
			<%
				String msg  = (String)session.getAttribute("lErrorMsg");
				if (msg== null)
				{ // if session is invalidated.. the fetching from request
					msg = (String) request.getAttribute("lErrorMsg"); 
				}
				if ( msg != null && msg.length() > 0 ){
			%>
    			<tr>
					<td colspan="2" align="center"> 
				<%	out.println("<div class=boldred >"+msg+"</div>"); %>
					</td>
				</tr>
				<%	}	%>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2"><%@include file="../hrmsFooter.jsp"%></td>
	</tr>
	<tr><td>
	<%
	System.out.println("Hello World");
	%>
	</td>
	</tr>
	</table>
	</form>
</body>
</html> 