package com.kudos.recruitment;

//import the java 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// import java packages
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.Array;


public class HrmsApplicantServlet extends HttpServlet {
	String dBUser = "";
	String dBPswd = "";
	String dBUrl = "";
	
	/* initialize the global variable*/
	public void init(ServletConfig config) throws ServletException {
		System.out.println("initialize controller servlet");
		System.out.println("initialize controller servlet");
		ServletContext context = config.getServletContext();
		dBUser = "root";
		dBPswd = "password";
		dBUrl = "jdbc:mysql://localhost:3306/hrm";
		super.init(config);
	}
	
	/* process the http Get request */
	public void doGet(HttpServletRequest request ,HttpServletResponse response)throws IOException, ServletException {
		System.out.println("HrmsApplicantServlet.doGet()");
		doPost (request, response);
	}
	
	/* process the http Post request */
	public void doPost(HttpServletRequest request ,HttpServletResponse response)throws IOException, ServletException {
		System.out.println("HrmsApplicantServlet.doPost()");
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		
		HttpSession session = request.getSession();
		session.setAttribute("Error Message", null);
		
		String target = "";
		String action = request.getParameter("action");
		
		String dBOperation = "";
		dBOperation = (String)request.getParameter("dBOperation");
		session.setAttribute("dBOperation", dBOperation);
		System.out.println("HrmsEmployeeServlet.doPost() dBOperation ::::"+dBOperation);
		
		if ((dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("register"))) {
			target = "/jsp/recruitment/applicantRegister.jsp";
		}  else if ((dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("edit"))) {
			action = "hrmsApplicantEdit";
		} else if ((dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("delete"))) {
			action = "hrmsApplicantDelete";
		} else if ((dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("detail"))) {
			action = "hrmsApplicantDetail";
		} else {
			action = "hrmsApplicantSelect";
		} 
		String actionSubmit = request.getParameter("actionSubmit");
		String actionEdit = request.getParameter("actionEdit");
		System.out.println("actionSubmit == " + actionSubmit);
		System.out.println("actionEdit == " + actionEdit);
		if (actionSubmit != null || actionEdit != null) {
			//System.out.println("");
			if (request.getParameter("submit").equals("Submit")) {
				System.out.println(" in the Submit  ");
				if (actionSubmit.equals("hrmsApplicantRegisterSubmit")) {
					System.out.println(" in the hrmsApplicantRegisterSubmit  ");
					action = "hrmsApplicantRegisterSubmit";
				} else if (actionSubmit.equals("HrmsApplicantSearchSubmit")){
					action = "HrmsApplicantSearchSubmit";
				}
			} else if (request.getParameter("submit").equals("Update")) {
				if (actionEdit.equals("hrmsApplicantEditSubmit")) 
					action = "hrmsApplicantEditSubmit";
			}
		}
		
		if (action != null) {
			System.out.println(" in the " + action);
			if (action.equals("hrmsChangePswdSubmit")) {
				target = "/jsp/login/hrmsUserLoginPswdChange.jsp";
			} else if (action.equals("hrmsApplicantRegisterSubmit")) {
				System.out.println("**********hrmsApplicantRegisterSubmit***********");
				HrmsApplicant popApplicantDBObj = new HrmsApplicant();
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				popApplicantDBObj = (HrmsApplicant)applicantDBMethods.populateApplicantDBObjFromReq(request);
			System.out.println("popApplicationDBObj----"+popApplicantDBObj.applicantId);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj = (HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(popApplicantDBObj.applicantId);
				if ((popApplicantDBObj.applicantId).equals(applicantDBObj.applicantId)) {
					System.out.println("if block in hrmsApplicantRegisterSubmit");
					String errorMessage = "Applicant already exit";
					session.setAttribute("ErrorMessage", errorMessage);
					target = "/jsp/recruitment/applicantRegister.jsp";
				} else {
					System.out.println("if block in hrmsApplicantRegisterSubmit");
					int rval = applicantDBMethods.insertApplicant(popApplicantDBObj);
					System.out.println("insert applicant in db----"+rval);
					ArrayList applicantList = new  ArrayList();
					String criteria = " ";
					//String criteria = " where applicant_id= '"+popApplicantDBObj.applicantId+"' ";
					applicantList = (ArrayList)applicantDBMethods.selectApplicantByCriteria(criteria);
					session.setAttribute("applicantList", applicantList);
					//System.out.println("ApplicantList++++++++"+applicantList.size());
					target = "/jsp/recruitment/applicantList.jsp";
				}
			} else if (action.equals("hrmsApplicantSelect")) {
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				ArrayList applicantList = new  ArrayList();
				String criteria = "";
				applicantList = (ArrayList)applicantDBMethods.selectApplicantByCriteria(criteria);
				session.setAttribute("applicantList", applicantList);
				target = "/jsp/recruitment/applicantList.jsp";
			} else if (action.equals("hrmsApplicantEdit")) {
				System.out.println("HrmsApplicantServlet.doPost()hrmsApplicantEdit");
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				System.out.println("applicantId==="+applicantId);
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj =(HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(applicantId);
				if (applicantDBObj != null && (applicantDBObj.applicantId != null && applicantDBObj.applicantId.length() > 0)) {
					session.setAttribute("applicantDBObj", applicantDBObj);
					target = "/jsp/recruitment/applicantEdit.jsp";
				} else {
					target = "/jsp/recruitment/applicantList.jsp";
				}
			} else if (action.equals("hrmsApplicantEditSubmit")) {
				System.out.println("*******hrmsApplicantEditSubmit*********");
				HrmsApplicant popApplicantDBObj = new HrmsApplicant();
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				popApplicantDBObj = (HrmsApplicant)applicantDBMethods.populateApplicantDBObjFromReq(request);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				int rval = applicantDBMethods.updateApplicant(popApplicantDBObj);
				applicantDBObj =(HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(popApplicantDBObj.applicantId);
				session.setAttribute("applicantDBObj", applicantDBObj);
				target = "/jsp/recruitment/applicantEdit.jsp";
			} else if (action.equals("hrmsApplicantDelete")) {
				System.out.println("HrmsApplicantServlet.doPost()hrmsApplicantDelete");
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				applicantDBMethods.deleteApplicant(applicantId);
				ArrayList applicantList = new  ArrayList();
				String criteria = "";
				applicantList = (ArrayList)applicantDBMethods.selectApplicantByCriteria(criteria);
				session.setAttribute("applicantList", applicantList);
				target = "/jsp/recruitment/applicantList.jsp";
			}  else if (action.equals("hrmsApplicantDetail")) {
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj =(HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(applicantId);
				session.setAttribute("applicantDBObj", applicantDBObj);
				target = "/jsp/recruitment/applicantEdit.jsp";
			}
		
		}
		
		// (action == null)
				/* forwarding the request/response to the targeted view */
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
				requestDispatcher.forward(request, response);
	}

}
