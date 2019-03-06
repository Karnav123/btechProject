package com.kudos.profile;

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

public class HrmsEmployeeServlet extends HttpServlet {
	String dBUser = "";
	String dBPswd = "";
	String dBUrl = "";
	
	/* initialize the global variable*/
	public void init(ServletConfig config) throws ServletException {
		System.out.println("HrmsEmployeeServlet.init()");
		System.out.println("initialize controller servlet");
		ServletContext context = config.getServletContext();
		dBUser = "root";
		dBPswd = "password";
		dBUrl = "jdbc:mysql://localhost:3307/hrm";
		super.init(config);
	}
	
	/* process the http Get request */
	public void doGet(HttpServletRequest request ,HttpServletResponse response)throws IOException, ServletException {
		System.out.println("HrmsEmployeeServlet.doGet()");
		doPost (request, response);
	}
	
	/* process the http Post request */
	public void doPost(HttpServletRequest request ,HttpServletResponse response)throws IOException, ServletException {
		System.out.println("HrmsEmployeeServlet.doPost() ");
		System.out.println("HrmsEmployeeServlet.doPost()");
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		
		HttpSession session = request.getSession();
		session.setAttribute("Error Message", null);
		
		String target = "";
		String dBOperation = "";
		
		String action = request.getParameter("action");
		dBOperation = (String)request.getParameter("dBOperation");
		
		System.out.println("HrmsEmployeeServlet.doPost() dBOperation ::::"+dBOperation);
		
		if ((dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("create"))) {
			target = "/jsp/profile/employeeInsert.jsp";
		} else {
			target = "/jsp/profile/employeeSearch.jsp";
		}
		String actionSubmit = request.getParameter("actionSubmit");
		String actionEdit = request.getParameter("actionEdit");
		System.out.println("actionSubmit == " + actionSubmit);
		System.out.println("actionEdit == " + actionEdit);

		if (actionSubmit != null || actionEdit != null) {
			if (request.getParameter("submit").equals("Submit")) {
				System.out.println(" in the Submit  ");
				if (actionSubmit.equals("HrmsEmployeeInsertSubmit")) {
					System.out.println(" in the HrmsEmployeeInsertSubmit  ");
					action = "HrmsEmployeeInsertSubmit";
				}else if (actionSubmit.equals("LoginPswdChangeSubmit")){
					action = "LoginPswdChangeSubmit";
				} else if (actionSubmit.equals("hrmsEmployeeSearchSubmit")) {
					action = "hrmsEmployeeSearchSubmit";
				}
			} else if (request.getParameter("submit").equals("Edit")) {
				if (actionEdit.equals("hrmsEmployeeEditSubmit")) {
					System.out.println(" in the ***HrmsEmployeeEditSubmit*** ");
					action = "hrmsEmployeeEditSubmit";
				}
			}
		}
		
		
		
		
		
		///OK I have the default target and actiion now....
		
		
		
		
		
		if (action != null) {
			System.out.println(" in the========<>>>> " + action);
			
			
			if (action.equals("hrmsEmployeeSearchSubmit")) {
				System.out.println(" in hrmsEmployeeSearchSubmit the " + action);
				String empId = "";
				String empFirstName = "";
				
				empId = (String)request.getParameter("empId");
				System.out.println(" empId " +empId);
				empFirstName = (String)request.getParameter("empFirstName" );
				System.out.println(" empFirstName " +empFirstName);
				
				
				HrmsEmployee employeeDBObj = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods = new HrmsEmployeeHelper();
				employeeDBObj = (HrmsEmployee) employeeDBMethods.getRecordByPrimaryKey(empId, empFirstName);
				System.out.println (" EmployeeDBObj " + employeeDBObj.empId + "ffff = " + employeeDBObj.empFirstName);
				
				
				if ((employeeDBObj.empId != null && employeeDBObj.empFirstName != null)) {
					
					session.setAttribute("employeeDBObj", employeeDBObj);
					target = "/jsp/profile/employeeEdit.jsp";
				} else {
					String errorMessage = "Employee does not exit";
					session.setAttribute("ErrorMessage", errorMessage);
					target = "/jsp/profile/employeeInsert.jsp";
				}
			} else if (action.equals("hrmsChangePswdSubmit")) {
				target = "/jsp/login/hrmsUserLoginPswdChange.jsp";
			} else if (action.equals("HrmsEmployeeInsertSubmit")) {
				
				
				HrmsEmployee popEmployeeDBObj = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods = new HrmsEmployeeHelper();
				popEmployeeDBObj = (HrmsEmployee)employeeDBMethods.populateEmployeeDBObjFromReq(request);
				HrmsEmployee employeeDBObj = new HrmsEmployee();
				employeeDBObj = (HrmsEmployee)employeeDBMethods.getRecordByPrimaryKey(popEmployeeDBObj.empId, popEmployeeDBObj.empFirstName);
				if ((popEmployeeDBObj.empId).equals(employeeDBObj.empId) && (popEmployeeDBObj.empFirstName).equals(employeeDBObj.empFirstName)) {
					String errorMessage = "Employee already exit";
					session.setAttribute("ErrorMessage", errorMessage);
					target = "/jsp/profile/employeeInsert.jsp";
				} else {
					
					
					int rval = employeeDBMethods.insertEmployee(popEmployeeDBObj);
					
					
					HrmsEmployee sEmployeeDBObj = new HrmsEmployee();
					sEmployeeDBObj = (HrmsEmployee) employeeDBMethods.getRecordByPrimaryKey(popEmployeeDBObj.empId, popEmployeeDBObj.empFirstName);
					
					
					session.setAttribute("EmployeeDBObj", sEmployeeDBObj);
					target = "/jsp/profile/employeeSearch.jsp";
				}
			} else if (action.equals("hrmsEmployeeEditSubmit")) {
				System.out.println(" in hrmsEmployeeEditSubmit 34 the " );
				
				HrmsEmployee popEmployeeDBObj = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods = new HrmsEmployeeHelper();
				popEmployeeDBObj = (HrmsEmployee)employeeDBMethods.populateEmployeeDBObjFromReq(request);
				int rval = employeeDBMethods.updateEmployeeByPrimaryKey(popEmployeeDBObj);
				if ( rval > 0) {
					HrmsEmployee employeeDBObj = new HrmsEmployee();
					employeeDBObj = (HrmsEmployee) employeeDBMethods.getRecordByPrimaryKey(popEmployeeDBObj.empId, popEmployeeDBObj.empFirstName);
					session.setAttribute("EmployeeDBObj", employeeDBObj);
					target = "/jsp/profile/employeeEdit.jsp";
				} else {
					target = "/jsp/profile/employeeEdit.jsp";
				}
			}
		} // (action == null)
		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}

}
