package com.kudos.time;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kudos.profile.HrmsEmployee;
import com.kudos.profile.HrmsEmployeeHelper;
import com.kudos.util.DateUtil;

public class TimeManagementServlet extends HttpServlet{
	String lDBUser  = "";
	String lDBPswd  = "";
	String lDBUrl   = "";
	
	/**Initialize global variables*/
	public void init(ServletConfig config) throws ServletException{
		System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
		lDBUser = "root";
		lDBPswd = "root123";
		lDBUrl  = "jdbc:mysql://localhost:3306/hrm";
		super.init(config);
	}

	/**Process the HTTP Get request*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	/**Process the HTTP Post request*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("lErrorMsg",null);
		String target = "";
		String action = request.getParameter("action");
		String dBOperation = "";
		dBOperation = (String)request.getParameter("dBOperation");
		session.setAttribute("dBOperation",dBOperation);
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("dailyAttendanceEntry")) ){
			target = "/jsp/profile/employeeSearch.jsp";
		}
		else
			if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("dailyAttendanceSummary")) ){
				action = "dailyAttendanceSummary";
			}
			else
				if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("edit")) ){
					action = "dailyAttendanceSummaryEdit";
				}
		String actionSubmit = request.getParameter("actionSubmit");
		String actionEdit = request.getParameter("actionEdit");
		System.out.println("actionSubmit=="+actionSubmit);
		if ( actionSubmit != null || actionEdit != null ){
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( actionSubmit.equals("hrmsEmployeeSearchSubmit") ){
					System.out.println("in the hrmsEmployeeInsertSubmit ");
					action = "hrmsEmployeeSearchSubmit";
				}
			}
			else 
				if ( request.getParameter("submit").equals("submit Detail") ){
					if ( actionSubmit.equals("employeeDailyAttendanceSubmit") )
						System.out.println("TimeManagementServlet.doPost(99999)  employeeDailyAttendanceSubmit");
						action = "employeeDailyAttendanceSubmit";
				}  
		}
		if (action!=null){
			System.out.println("in the  "+action);
			if (action.equals("hrmsEmployeeSearchSubmit")){
				String lEmpId = "";
				String lEmpFName = "";
				lEmpId = (String)request.getParameter("empId");
				lEmpFName = (String)request.getParameter("empFirstName");
				TimeManagementHelper timeManagementDBMethods  = new TimeManagementHelper(lDBUser,lDBPswd,lDBUrl);
				DayObject dateYearMonthDayDBObj = new DayObject();
				dateYearMonthDayDBObj = (DayObject)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				System.out.println("Date+++++++kk++++++++"+dateYearMonthDayDBObj.toString());
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				HrmsEmployee employeeDBObj = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods = new HrmsEmployeeHelper();
				employeeDBObj = (HrmsEmployee)employeeDBMethods.getRecordByPrimaryKey(lEmpId,lEmpFName);
				EmployeeDailyAttendance empDailyAttendanceDBObj = new EmployeeDailyAttendance();
				String todaydate=DateUtil.getDateText(dateYearMonthDayDBObj.todayDate, DateUtil._YYYY_MM_DD);
				String toDaydate=(String)timeManagementDBMethods.dayDateMonth();
				session.setAttribute("toDaydate", toDaydate);

				//session.setAttribute("todaydate", todaydate);
				System.out.println("TodayDate__kk_________"+todaydate);
				empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(lEmpId,todaydate);
				//empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(lEmpId,dateYearMonthDayDBObj.todayDate);
				System.out.println("iiiii="+employeeDBObj.empId+"ffff="+employeeDBObj.empFirstName);
				if ( (employeeDBObj.empId != null && employeeDBObj.empFirstName.equals(lEmpFName)  ) ){
					System.out.println("-------------");
					session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
					session.setAttribute("employeeDBObj",employeeDBObj);
					target = "/jsp/time/employeeDailyAttendance.jsp";
				}
				else{
					String lErrorMsg = "Employee doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					target = "/jsp/hrmsDefault.jsp";
				}
			}   
			else
			if (action.equals("dailyAttendanceSummaryEdit")){
				String lEmpId = "";
				String lTodayDate = "";
				String lEmpFName = "";
				lEmpId = (String)request.getParameter("empId");
				lTodayDate = (String)request.getParameter("today_date");//kk
				System.out.println("lToday++++++++++++kkkkkkk++++++"+lTodayDate);
				TimeManagementHelper timeManagementDBMethods  = new TimeManagementHelper(lDBUser,lDBPswd,lDBUrl);
				DayObject dateYearMonthDayDBObj = new DayObject();
				dateYearMonthDayDBObj = (DayObject)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				HrmsEmployee employeeDBObj = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods = new HrmsEmployeeHelper();
				employeeDBObj = (HrmsEmployee)employeeDBMethods.getRecordByPrimaryKey(lEmpId,lEmpFName);
				EmployeeDailyAttendance empDailyAttendanceDBObj = new EmployeeDailyAttendance();
				empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(lEmpId,lTodayDate);
				session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
				session.setAttribute("employeeDBObj",employeeDBObj);
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				target = "/jsp/time/employeeDailyAttendance.jsp";
			}   
			else
			if (action.equals("dailyAttendanceSummary")){
				TimeManagementHelper timeManagementDBMethods  = new TimeManagementHelper(lDBUser,lDBPswd,lDBUrl);
				DayObject dateYearMonthDayDBObj = new DayObject();
				dateYearMonthDayDBObj = (DayObject)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				System.out.println("vivek......."+dateYearMonthDayDBObj.todayDate.toString());
				String todaydate=DateUtil.getDateText(dateYearMonthDayDBObj.todayDate, DateUtil._YYYY_MM_DD);//kk
				System.out.println("vivek...todaydate..."+todaydate);
				Date todayDate=DateUtil.getDate(todaydate, DateUtil._YYYY_MM_DD);
                 session.setAttribute("todaydate", todaydate);
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				ArrayList empDailyAttendanceList = new ArrayList();
				String criteria = "";
				//criteria = " where today_date='"+dateYearMonthDayDBObj.todayDate+"' ";
				criteria = " where today_date='"+todaydate+"' ";

				empDailyAttendanceList = ( ArrayList)timeManagementDBMethods.selectEmpDailyAttendanceByCriteria(criteria);
				if(empDailyAttendanceList!=null){
					System.out.println(".....................vivek ........size-"+empDailyAttendanceList.size());
				}
				session.setAttribute("empDailyAttendanceList",empDailyAttendanceList);
				System.out.println("empDailyAttendanceList.size()"+empDailyAttendanceList.size());
				target = "/jsp/time/employeeDailyAttendanceSummary.jsp";
			}   
			else
			if (action.equals("employeeDailyAttendanceSubmit")){
				System.out.println("TimeManagementServlet.doPost(employeeDailyAttendanceSubmit  Block)");
				EmployeeDailyAttendance  popEmpDailyAttendanceDBObj = new EmployeeDailyAttendance();
				TimeManagementHelper timeManagementDBMethods  = new TimeManagementHelper(lDBUser,lDBPswd,lDBUrl);
				popEmpDailyAttendanceDBObj = ( EmployeeDailyAttendance)timeManagementDBMethods.populateEmpDailyAttendanceDBObjFromReq(request);
				System.out.println("popEmpDailyAttendanceDBObj.empId"+popEmpDailyAttendanceDBObj.empId);
				EmployeeDailyAttendance  empDailyAttendanceDBObj = new EmployeeDailyAttendance();
				String todaydate=DateUtil.getDateText(popEmpDailyAttendanceDBObj.todayDate, DateUtil._YYYY_MM_DD);
				empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.empId,todaydate);
				//empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.empId,popEmpDailyAttendanceDBObj.todayDate);
				System.out.println("empDailyAttendanceDBObj.empId"+empDailyAttendanceDBObj.empId);
				if ( ( empDailyAttendanceDBObj.empId != null && (popEmpDailyAttendanceDBObj.empId).equals(empDailyAttendanceDBObj.empId)) && (popEmpDailyAttendanceDBObj.todayDate).equals(empDailyAttendanceDBObj.todayDate) ){
					System.out.println("TimeManagementServlet.doPost(IF BLOCK empDailyAttendanceDBObj )");
					int rval = timeManagementDBMethods.updateEmpDailyAttendanceDBObjByPrimaryKey(popEmpDailyAttendanceDBObj);
					String todaydate1=DateUtil.getDateText(popEmpDailyAttendanceDBObj.todayDate, DateUtil._YYYY_MM_DD);
					empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.empId,todaydate1);
					//empDailyAttendanceDBObj = (EmployeeDailyAttendance)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.empId,popEmpDailyAttendanceDBObj.todayDate);
					session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
					target = "/jsp/time/employeeDailyAttendance.jsp";
				}
				else{
					int rval =  timeManagementDBMethods.insertEmpDailyAttendanceDBObj(popEmpDailyAttendanceDBObj);
					target = "/jsp/time/employeeDailyAttendance.jsp";
				}
			}
						
    	} // (action== null )
		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}// doPost closed
}// class closed