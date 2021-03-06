package com.kudos.login;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HrmsUserLoginServlet extends HttpServlet{
	String dBUser  = "";
	String dBPswd  = "";
	String dBUrl   = "";
    /**Initialize global variables*/
	public void init(ServletConfig config) throws ServletException {
	    System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
		dBUser = "root";
		dBPswd = "root123";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dBUrl  = "jdbc:mysql://localhost:3306/hrm";
		
		super.init(config);
	}
	
	/**Process the HTTP Get request*/
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HrmsUserLoginServlet.doGet()");
		doPost(request, response);
		System.out.println("HrmsUserLoginServlet.doGet()");
	}
	
	/**Process the HTTP Post request*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("=========HrmsUserLoginServlet.doPost()");
		
	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("Error Message",null);
		String target = "/jsp/login/hrmsUserLogin.jsp";
		
		String action = request.getParameter("action");
		String actionSubmit = request.getParameter("actionSubmit");
		String actionChangePassword = request.getParameter("actionChangePassword");//.getParameter("actionChangePassword");
		System.out.println("actionSubmit ---->"+actionSubmit+"<---");
		System.out.println("actionChangePassword ---->"+actionChangePassword+"<---");
		System.out.println("request.getParameter(submit) ---->"+request.getParameter("submit")+"<---");
		
		
		
		
		if ( actionSubmit != null || actionChangePassword != null ){
			
			
			if ( request.getParameter("submit").equals("Log-In.") ){
				System.out.println("in the  Submit [Log-In.]>>" +actionSubmit);
				if ( actionSubmit.equals("hrmsUserLoginSubmit") ){
					
					
					System.out.println("in the hrmsUserLoginSubmit ");
					action = "hrmsUserLoginSubmit";
					
				} else if (actionSubmit.equals("loginPasswordChangeSubmit")) {
						
					System.out.println("in the  loginPasswordChangeSubmit>>>" +actionSubmit);
					action = "loginPasswordChangeSubmit";
				}
					
			} else if ( request.getParameter("submit").equals("Change Password") ){
					
				if ( actionChangePassword.equals("hrmsChangePasswordSubmit") ){
					
					action = "hrmsChangePasswordSubmit";
				}				
			} else if ( request.getParameter("submit").equals("Submit") ){
				if ( actionSubmit.equals("loginPasswordChangeSubmit") ){
					System.out.println("in the  loginPasswordChangeSubmit>>>" +actionSubmit);
					action = "loginPasswordChangeSubmit";
				}
			}
				 
		}
		
		// Action is performed
		
		System.out.println("action  ---->>>>"+action+"<---");
		if (action != null ) {
			System.out.println("in the  "+action);
			if (action.equals("hrmsUserLoginSubmit")){
				System.out.println("HrmsUserLoginServlet.doPost() ACTION : hrmsUserLoginSubmit");
				String userId = "";
				String userName = "";
				String userPassword = "";
				userId = (String)request.getParameter("userId");
				userName = (String)request.getParameter("userName");
				userPassword = (String)request.getParameter("userPassword");				
				UserLogin userLoginObject = new UserLogin();	
				UserLoginHelper userLoginDBMethods = new UserLoginHelper(dBUser,dBPswd,dBUrl);
				userLoginObject = (UserLogin)userLoginDBMethods.getRecordByPrimaryKey(userId,userName,userPassword);
				System.out.println("HrmsUserLoginServlet.doPost() userLoginObject --->" +userLoginObject);				
				
				if ( userLoginObject != null && ( userLoginObject.userId != null && (userLoginObject.userId).length() > 0) ) {
					target = "/jsp/hrmsDefault.jsp";
				} else {
					String errorMessage = "User Does Not Exist!!"; 
					session.setAttribute("errorMessage",errorMessage);
					target = "/jsp/login/hrmsUserLogin.jsp";
				}	
			} else if (action.equals("hrmsChangePasswordSubmit")) {
					target = "/jsp/login/hrmsUserLoginPasswordChange.jsp";
			} else if (action.equals("loginPasswordChangeSubmit")) {
				UserLogin popUserLoginDBObj = new UserLogin();
				UserLoginHelper userLoginDBMethods = new UserLoginHelper(dBUser,dBPswd,dBUrl);
				String userId = "";
				String userName = "";
				String currentPassword = "";
				String newPassword = "";
				String reTypePassword = "";
					
				popUserLoginDBObj = (UserLogin)userLoginDBMethods.populateUserLoginDBObjFromReq(request);  
				reTypePassword = (String)request.getParameter("reTypePassword");
				System.out.println("reTypePassword" +reTypePassword);
					
				if ( (popUserLoginDBObj.newPassword).equals(reTypePassword) ) {
					UserLogin userLoginObject = new UserLogin();
					userLoginObject = (UserLogin)userLoginDBMethods.getRecordByPrimaryKey(popUserLoginDBObj.userId,popUserLoginDBObj.userName,popUserLoginDBObj.oldPassword);
					System.out.println("userLoginObject>>>>" +userLoginObject);
						
					if ( userLoginObject != null && ( userLoginObject.userId != null && (userLoginObject.userId).length() > 0) ){
						
						int rval = userLoginDBMethods.updateUserLoginByPrimaryKey(popUserLoginDBObj);
						System.out.println("rval>>>>" +rval);
						if ( rval > 0 ){
							target = "/jsp/login/hrmsUserLogin.jsp";
						}
						else {
							target = "/jsp/login/hrmsUserLoginPasswordChange.jsp";
						}
					} else {
						
						String errorMessage = "User Does Not Exist!!"; 
						session.setAttribute("errorMessage",errorMessage);
						target = "/jsp/login/hrmsUserLoginPasswordChange.jsp";
					}	
				}
				else {
					String errorMessage = "Retype Correct Password!!"; 
					session.setAttribute("errorMessage",errorMessage);
					target = "/jsp/login/hrmsUserLoginPasswordChange.jsp";
				}
			}
		} else	{ 
			
			/// Action == null
			session.invalidate();
			target = "/jsp/login/hrmsUserLogin.jsp";
			String errorMessage = "You have sucessfully logged out."; 
			request.setAttribute("errorMessage",errorMessage);
		}
		
		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}
	 // doPost closed
	/* write the methods that are used in class  */
}
// class closed


