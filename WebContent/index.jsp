<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kudos .. .. ..  JSP </title>
</head>
<body>

<H2>Example links index.jsp</H2>

<HR>
<CENTER>
<BR>
<a HREF="techSupport.do?forward=techSupportExample">   Tech Support Example </a> <BR>
<BR>
<a HREF="sessionLifecycle.jsp">   Sesssion Lifecycle Example </a><BR>
<BR>
<a HREF="invalidSession.jsp">   Invalid Session Example </a><BR>
<BR>



<BR>
<a HREF="techSupportWithRegistration.html">   Tech Support Example with registration </a> <BR>
<BR>


<BR> Total Requests in this : <%= (Integer)application.getAttribute("COUNTER") %>
 <BR>
<BR>



</CENTER>



</body>
</html>