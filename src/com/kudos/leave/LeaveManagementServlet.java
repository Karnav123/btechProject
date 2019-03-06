package com.kudos.leave;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.sql.*;

import com.kudos.leave.LeaveManagementHelper;
import com.kudos.leave.LeaveRequest;

public class LeaveManagementServlet extends HttpServlet{
	String dbuser  = "";
	String dbpswd  = "";
	String dburl   = "";
	/**Initialize global variables*/
	public void init(ServletConfig config) throws ServletException{
		System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
		dbuser = "root";
		dbpswd = "password";		
		dburl  = "jdbc:mysql://localhost:3307/hrm";
		super.init(config);
	}
	/**Process the HTTP Get request*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**Process the HTTP Post request*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("lErrorMsg",null);
		String target = "";
		String action = request.getParameter("action");
		String dBOperation = "";
		dBOperation = (String)request.getParameter("dBOperation");
		session.setAttribute("dBOperation",dBOperation);
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("leaveRequest")) ){
			target = "/jsp/leave/leaveRequest.jsp";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("leaveApprove")) ){
			action = "selectLeaveRequest";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("approve")) ){
			action = "leaveRequestApprove";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("approvedLeave")) ){
			action = "approvedLeaveRequest";
		}
		String action_submit = request.getParameter("actionSubmit");
		System.out.println("actionSubmit=="+action_submit);
		if ( action_submit != null ){
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( action_submit.equals("employeeLeaveReqSubmit") ){
					System.out.println("in the employeeLeaveReqSubmit ");
					action = "employeeLeaveReqSubmit";
				}
			}
			else 
			if ( request.getParameter("submit").equals("Edit") ){
				if ( action_submit.equals("employeeLeaveReqEditSubmit") )
					action = "employeeLeaveReqEditSubmit";
			}
		}
		if (action!=null){
			System.out.println("in the  "+action);
			if (action.equals("leaveRequestApprove")){
			String reqId = "";
			reqId = (String)request.getParameter("reqId");
			String empId = "";
			empId = (String)request.getParameter("empId");
			LeaveManagementHelper leaveMgmtBeanMethods = new LeaveManagementHelper(dbuser,dbpswd,dburl);
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest = (LeaveRequest)leaveMgmtBeanMethods.getRecordByPrimaryKey(reqId,empId);
			session.setAttribute("leaveRequest",leaveRequest);
			target = "/jsp/leave/leaveRequestEdit.jsp";
		}   
		else
		if (action.equals("selectLeaveRequest") || action.equals("approvedLeaveRequest")){
			LeaveManagementHelper leaveMgmtBeanMethods  = new LeaveManagementHelper(dbuser,dbpswd,dburl);
			ArrayList leaveRequestList = new ArrayList();
			String criteria = "";
			if( action.equals("selectLeaveRequest") )
				criteria = " where leave_status='Req' ";
			else
				criteria = " where leave_status='Aprv' ";
			
			leaveRequestList = ( ArrayList)leaveMgmtBeanMethods.selectLeaveRequestByCriteria(criteria);
			session.setAttribute("leaveRequestList",leaveRequestList);
			System.out.println("leaveRequestList.size()"+leaveRequestList.size());
			target = "/jsp/leave/leaveRequestList.jsp";
		}   
		else
		if (action.equals("employeeLeaveReqSubmit")){
			System.out.println("LeaveManagementServlet.doPost()");
			LeaveRequest popLeaveRequest = new LeaveRequest(); 
			LeaveManagementHelper leaveMgmtBeanMethods = new LeaveManagementHelper(dbuser,dbpswd,dburl);
			popLeaveRequest = (LeaveRequest)leaveMgmtBeanMethods.populateLeaveRequestFromReq(request);
			popLeaveRequest.leaveStatus = "Req";
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest = (LeaveRequest)leaveMgmtBeanMethods.getRecordByPrimaryKey(popLeaveRequest.reqId,popLeaveRequest.empId);
			if ( (popLeaveRequest.empId).equals(leaveRequest.empId) && (popLeaveRequest.reqId).equals(leaveRequest.reqId) ){
				String lErrorMsg = "Employee Already Exist"; 
				session.setAttribute("lErrorMsg",lErrorMsg);
				target = "/jsp/leave/leaveRequest.jsp";
			}
			else{
				int rval =  leaveMgmtBeanMethods.insertLeaveRequest(popLeaveRequest);
				LeaveRequest sLeaveRequest = new LeaveRequest();
				sLeaveRequest = (LeaveRequest)leaveMgmtBeanMethods.getRecordByPrimaryKey(popLeaveRequest.reqId,popLeaveRequest.empId);
				session.setAttribute("leaveRequest",sLeaveRequest);
				target = "/jsp/leave/leaveRequestEdit.jsp";
			}
		}   
		else
		if (action.equals("employeeLeaveReqEditSubmit")){ 
			LeaveRequest popLeaveRequest = new LeaveRequest();
			LeaveManagementHelper leaveMgmtBeanMethods = new  LeaveManagementHelper(dbuser,dbpswd,dburl);
			String ldBOperation = (String)session.getAttribute("dBOperation");
			System.out.println("dBOperation????????????????//"+ldBOperation);
			popLeaveRequest = (LeaveRequest)leaveMgmtBeanMethods.populateLeaveRequestFromReq(request); 
			if(ldBOperation != null && ldBOperation.equals("approve"))
				popLeaveRequest.leaveStatus = "Aprv"; 
			else
				popLeaveRequest.leaveStatus = "Req"; 
			System.out.println("dBOperation????????????????//"+popLeaveRequest.leaveStatus);
			int rval = leaveMgmtBeanMethods .updateLeaveRequestByPrimaryKey(popLeaveRequest);
			if ( rval > 0 ){   
				LeaveRequest LeaveRequest = new LeaveRequest();
				LeaveRequest = (LeaveRequest)leaveMgmtBeanMethods.getRecordByPrimaryKey(popLeaveRequest.reqId,popLeaveRequest.empId);
				session.setAttribute("LeaveRequest",LeaveRequest);
				ArrayList leaveRequestList = new ArrayList();
				String criteria = "";
				criteria = " where leave_status='Req' ";
				leaveRequestList = ( ArrayList)leaveMgmtBeanMethods.selectLeaveRequestByCriteria(criteria);
				session.setAttribute("leaveRequestList",leaveRequestList);
				System.out.println("leaveRequestList.size()"+leaveRequestList.size());
				target = "/jsp/leave/leaveRequestList.jsp";
			}
			else{
				target = "/jsp/profile/employeeEdit.jsp";
			}
		}
		} // (action== null )
		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	} // doPost closed
}// class closed