package com.kudos.payroll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

import com.kudos.time.TimeManagementHelper;
import com.kudos.leave.LeaveManagementHelper;
import com.kudos.leave.LeaveRequest;



public class HrmsPayrollServlet extends HttpServlet{
	String dBUser = "";
	String dBPswd = "";
	String dBUrl = "";
	
	/**Initialize global variables*/
	public void init(ServletConfig config) throws ServletException{
		System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
		dBUser = "root";
		dBPswd = "password";
		dBUrl = "jdbc:mysql://localhost:3307/hrm";
		super.init(config);
	}
	
	/**Process the HTTP Get request*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("HrmsPayrollServlet.doGet()");
		doPost(request, response);
	}
	
	/**Process the HTTP Post request*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("HrmsPayrollServlet.doPost()");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("lErrorMsg",null);
		String target = "";
		String action = request.getParameter("action");
		String dBOperation = "";
		dBOperation = (String)request.getParameter("dBOperation");
		session.setAttribute("dBOperation",dBOperation);
	
		System.out.println("HrmsPayrollServlet.doPost() dBOperation  :"+dBOperation);
		System.out.println("HrmsPayrollServlet.doPost() action :"+action);
		
		
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("employeeAgreement")) ){
			target = "/jsp/profile/employeeSearch.jsp";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("editHead")) ){
			action = "editSalaryHead";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("deleteHead")) ){
			action = "employeeSalaryHeadDelete";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("calculateEmployeeSalary")) ){
			target= "/jsp/payroll/salarySearch.jsp";
		}
		String actionSubmit = request.getParameter("actionSubmit");
		System.out.println("actionSubmit=="+actionSubmit);
		if ( actionSubmit != null ){
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( actionSubmit.equals("hrmsEmployeeSearchSubmit") ){
					System.out.println("in the hrmsEmployeeSearchSubmit ");
					action = "hrmsEmployeeSearchSubmit";
				}
			}
			else 
			if ( request.getParameter("submit").equals("Edit") ){
				if ( actionSubmit.equals("employeeSalaryHeadEditSubmit") )
					action = "employeeSalaryHeadEditSubmit";
			}  
			else 
			if ( request.getParameter("submit").equals("Submit Detail") ){
				if ( actionSubmit.equals("employeeAgreementDetailSubmit") )
					System.out.println("HrmsPayrollServlet.doPost()=====employeeAgreementDetailSubmit");
					action = "employeeAgreementDetailSubmit";
			}  
			else 
			if ( request.getParameter("submit").equals("Calculate") ){
				if ( actionSubmit.equals("salaryCalculateSubmit") )
					action = "salaryCalculateSubmit";
			}  
		}
		
		
		System.out.println("HrmsPayrollServlet.doPost() ACTION --->"+action);
		///  Real Stuff happens here..... based on action calculated above
		
		
		if (action!=null){
			System.out.println("in the  "+action);
	     		if (action.equals("hrmsEmployeeSearchSubmit")){
				String lEmpId = "";
				String lEmpFName = "";
				lEmpId = (String)request.getParameter("empId");
				lEmpFName = (String)request.getParameter("empFirstName");
				HrmsEmployee hrmsEmployee = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods= new HrmsEmployeeHelper();
				hrmsEmployee = (HrmsEmployee)employeeDBMethods.getRecordByPrimaryKey(lEmpId,lEmpFName);
				System.out.println("Emp Id="+hrmsEmployee.empId+"First Name="+hrmsEmployee.empFirstName);
				if ( (hrmsEmployee.empId != null && hrmsEmployee.empFirstName != null) ){
					session.setAttribute("hrmsEmployee",hrmsEmployee);
					HrmsPayrollHelper payrollBeanMethods = new HrmsPayrollHelper(dBUser,dBPswd,dBUrl);
					ArrayList employeeAgreementList = new ArrayList();
					String criteria = "";
					criteria = "where emp_id='"+hrmsEmployee.empId+"'";
					employeeAgreementList = ( ArrayList)payrollBeanMethods.selectEmployeeAgreementByCriteria(criteria);
					session.setAttribute("employeeAgreementList",employeeAgreementList);
					target = "/jsp/payroll/employeeAgreement.jsp";
				}
				else{
					String lErrorMsg = "Employee doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					target = "/jsp/hrmsDefault.jsp";
				}
			}
			else
			if (action.equals("editSalaryHead")){
				String empId = "";
				String allowanceName = "";
				empId = (String)request.getParameter("empId");
				allowanceName = (String)request.getParameter("allowanceName");
				HrmsPayrollHelper payrollBeanMethods  = new HrmsPayrollHelper(dBUser,dBPswd,dBUrl);
				EmployeeAgreement employeeAgreement = new EmployeeAgreement();
				employeeAgreement = (EmployeeAgreement)payrollBeanMethods.getEmployeeAgreementRecord(empId,allowanceName);
				session.setAttribute("employeeAgreement",employeeAgreement);
				target = "/jsp/payroll/employeeAgreementEdit.jsp";
			}
			else
			if (action.equals("salaryCalculateSubmit")){
				HrmsPayrollHelper payrollBeanMethods  = new HrmsPayrollHelper(dBUser,dBPswd,dBUrl);
				String empId = (String)request.getParameter("empId");
				int year = Integer.parseInt((String)request.getParameter("year"));
				int month = Integer.parseInt((String)request.getParameter("month"));
				System.out.println("HrmsPayrollServlet.salaryCalculateSubmit===="+empId+"   ");
				String lEmpFName = "";
				HrmsEmployee hrmsEmployee = new HrmsEmployee();
				HrmsEmployeeHelper employeeDBMethods = new HrmsEmployeeHelper();
				hrmsEmployee = (HrmsEmployee)employeeDBMethods.getRecordByPrimaryKey(empId,lEmpFName);
				
				int totalAttendance = 0;
				totalAttendance = getTotalAttendance(empId,year,month);
				System.out.println("totalAttendance======"+totalAttendance);
				session.setAttribute("totalAttendance",Integer.toString(totalAttendance)); 
				int totalLeave = 0;
				totalLeave = getTotalLeave(empId,year,month);
				System.out.println("totalLeave======>>>>>>>>>>"+totalLeave);
				session.setAttribute("totalLeave",Integer.toString(totalLeave)); 
				session.setAttribute("hrmsEmployee",hrmsEmployee);
				session.setAttribute("year",Integer.toString(year));
				session.setAttribute("month",Integer.toString(month));	
				
				ArrayList empSalExist = new ArrayList();
				String criteria = "";
				criteria = " where emp_id='"+empId+"' and year='"+year+"' and month='"+month+"' ";
				empSalExist = ( ArrayList)payrollBeanMethods.selectEmployeeSalaryByCriteria(criteria);
				
				if ( empSalExist != null && empSalExist.size() > 0 ){
					ArrayList empSalList = new ArrayList();
					criteria = "";
					criteria = " where emp_id='"+empId+"' and year='"+year+"' and month='"+month+"' ";
					empSalList = ( ArrayList)payrollBeanMethods.selectEmployeeSalaryByCriteria(criteria);
					session.setAttribute("empSalList",empSalList);
					System.out.println("empSalList.size()======"+empSalList.size());
				}
				else
				if( totalAttendance > 0 ){
					ArrayList employeeAgreementList = new ArrayList();
					criteria = "";
					criteria = " where emp_id='"+empId+"'";
					employeeAgreementList = ( ArrayList)payrollBeanMethods.selectEmployeeAgreementByCriteria(criteria);
					for (int rec =1; rec <= employeeAgreementList.size() ; rec++ ){
						EmployeeAgreement employeeAgreement = new EmployeeAgreement();
						EmployeeSalary empSal =  new EmployeeSalary();
						employeeAgreement = (EmployeeAgreement)employeeAgreementList.get(rec-1);
						empSal.empId =  employeeAgreement.empId;
						empSal.year = year ;
						empSal.month = month;
						empSal.allowanceName =  employeeAgreement.allowanceName;
						empSal.allowanceType =  employeeAgreement.allowanceType;
						empSal.amount =  employeeAgreement.amount;
						empSal.taxable =  employeeAgreement.taxable;
						empSal.percentage =  employeeAgreement.percentage;
						payrollBeanMethods.insertEmployeeSalary(empSal);
					} 
					ArrayList empSalList = new ArrayList();
					criteria = "";
					criteria = " where emp_id='"+empId+"' and year='"+year+"' and month='"+month+"' ";
					empSalList = ( ArrayList)payrollBeanMethods.selectEmployeeSalaryByCriteria(criteria);
					session.setAttribute("empSalList",empSalList);
					System.out.println("empSalList.size()======"+empSalList.size());
				}
				else{
					String lErrorMsg = "your criteria is not correct .. salary is not prepared!!!"; 
					session.setAttribute("lErrorMsg",lErrorMsg);

					if(session.getAttribute("empSalList")!=null)
						session.removeAttribute("empSalList");
					
				}
				
				target = "/jsp/payroll/salarySlip.jsp";
			}
			else
			if (action.equals("employeeAgreementDetailSubmit")){
				EmployeeAgreement popEmployeeAgreement = new EmployeeAgreement(); 
				HrmsPayrollHelper payrollBeanMethods = new HrmsPayrollHelper(dBUser,dBPswd,dBUrl);
				popEmployeeAgreement = (EmployeeAgreement)payrollBeanMethods.populateEmployeeAgreementFromReq(request);
				EmployeeAgreement employeeAgreement = new EmployeeAgreement();
				employeeAgreement = (EmployeeAgreement)payrollBeanMethods.getEmployeeAgreementRecord(popEmployeeAgreement.empId,popEmployeeAgreement.allowanceName);
				if ( (popEmployeeAgreement.empId).equals(employeeAgreement.empId) && (popEmployeeAgreement.allowanceName).equals(employeeAgreement.allowanceName) ){
					String lErrorMsg = "Allowance Already Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					target = "/jsp/payroll/employeeAgreement.jsp";
				}
				else{
					int rval =  payrollBeanMethods.insertEmployeeAgreement(popEmployeeAgreement);
					EmployeeAgreement sEmployeeAgreement = new EmployeeAgreement();
					sEmployeeAgreement = (EmployeeAgreement)payrollBeanMethods.getEmployeeAgreementRecord(popEmployeeAgreement.empId,popEmployeeAgreement.allowanceName);
					ArrayList employeeAgreementList = new ArrayList();
					String criteria = "";
					criteria = " where emp_id='"+popEmployeeAgreement.empId+"'";
					employeeAgreementList = ( ArrayList)payrollBeanMethods.selectEmployeeAgreementByCriteria(criteria);
					session.setAttribute("employeeAgreementList",employeeAgreementList);
					session.setAttribute("employeeAgreement",sEmployeeAgreement);
					target = "/jsp/payroll/employeeAgreement.jsp";
				}
			} 
			else
			if (action.equals("employeeSalaryHeadEditSubmit")){ 
				System.out.println("HrmsPayrollServlet.doPost()    employeeSalaryHeadEditSubmit");
				EmployeeAgreement popEmployeeAgreement = new EmployeeAgreement();
				HrmsPayrollHelper payrollBeanMethods = new  HrmsPayrollHelper(dBUser,dBPswd,dBUrl);
				popEmployeeAgreement = (EmployeeAgreement)payrollBeanMethods.populateEmployeeAgreementFromReq(request); 
				int rval = payrollBeanMethods.updateEmployeeAgreementByPrimaryKey(popEmployeeAgreement);
				
				if ( rval > 0 ){   
					ArrayList employeeAgreementList = new ArrayList();
					String criteria = "";
					criteria = " where emp_id='"+popEmployeeAgreement.empId+"' ";
					employeeAgreementList = ( ArrayList)payrollBeanMethods.selectEmployeeAgreementByCriteria(criteria);
					session.setAttribute("employeeAgreementList",employeeAgreementList);
					System.out.println("employeeAgreementList.size()"+employeeAgreementList.size());
					target = "/jsp/payroll/employeeAgreementEdit.jsp";
				}
				else{
					target = "/jsp/payroll/employeeAgreementEdit.jsp";
				}
			}
			else
			if (action.equals("employeeSalaryHeadDelete")){ 
				String empId = "";
				String allowanceName = "";
				empId = (String)request.getParameter("empId");
				allowanceName = (String)request.getParameter("allowanceName");
				HrmsPayrollHelper payrollBeanMethods = new  HrmsPayrollHelper(dBUser,dBPswd,dBUrl);
				payrollBeanMethods.deleteEmployeeAgreement(empId,allowanceName); 
				ArrayList employeeAgreementList = new ArrayList();
				String criteria = "";
				criteria = " where emp_id='"+empId+"' ";
				employeeAgreementList = ( ArrayList)payrollBeanMethods.selectEmployeeAgreementByCriteria(criteria);
				session.setAttribute("employeeAgreementList",employeeAgreementList);
				System.out.println("employeeAgreementList.size()"+employeeAgreementList.size());
				target = "/jsp/payroll/employeeAgreementEdit.jsp";
			}
		} // (action== null )
		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	} // doPost closed

	public int getTotalAttendance( String inEmpId, int inYear,int inMonth ){
		TimeManagementHelper timeManagementHelper = new  TimeManagementHelper(dBUser,dBPswd,dBUrl);
		int totalAttendance = 0;
		ArrayList attendanceList = new ArrayList();
		//String criteria = "where emp_id ='"+inEmpId+"' and year='"+inYear+"' and month='"+timeManagementHelper.getMonth(inMonth-1)+"' "; 
		String criteria = "where emp_id ='"+inEmpId+"' and year='"+inYear+"' and month='"+inMonth+"' ";
		attendanceList = (ArrayList)timeManagementHelper.selectEmpDailyAttendanceByCriteria(criteria);
		if( attendanceList != null && attendanceList.size() > 0)
			totalAttendance = attendanceList.size();
		return totalAttendance;
	}

	public int getTotalLeave( String inEmpId, int inYear,int inMonth ){
		
		System.out.println("HrmsPayrollServlet.getTotalLeave() EMP_ID: "+inEmpId+" yyyy: "+ inYear+" MM: "+ inMonth);
		LeaveManagementHelper leaveManagementBeanMethods = new  LeaveManagementHelper(dBUser,dBPswd,dBUrl);

		int totalLeave = 0;
		ArrayList leaveList = new ArrayList();
//		String strtdate = "";
//		String enddate = "";
		LeaveRequest  leaveRequest = new LeaveRequest();
//		strtdate = "01"+"-"+getMonth(inMonth-1)+"-"+inYear;
//		enddate  = "28"+"-"+getMonth(inMonth-1)+"-"+inYear;
		
//		strtdate = "01"+"-"+getMonth(inMonth-1)+"-"+inYear;
//		enddate  = "28"+"-"+getMonth(inMonth-1)+"-"+inYear;
		
		String criteria = "where emp_id ='"+inEmpId+"' and leave_status='Aprv' and from_date='"+leaveRequest.fromDate+"' and to_date='"+leaveRequest.toDate+"'  ";
		leaveList = (ArrayList)leaveManagementBeanMethods.selectLeaveRequestByCriteria(criteria);
		if( leaveList != null && leaveList.size() > 0){
			for (int i = 1;i<=leaveList.size() ;i++ ){
				//LeaveRequest leaveRequest  = new LeaveRequest();
				leaveRequest = (LeaveRequest)leaveList.get(i-1);
				totalLeave = totalLeave + leaveRequest.days;
			}
		}
		return totalLeave;
	}
	public String getMonth( int month ){
		String strMonth = "";
		if(month == 0) strMonth = "JAN";
		else if(month == 1) strMonth = "FEB";
		else if(month == 2 ) strMonth = "MAR";
		else if(month == 3) strMonth = "APR";
		else if(month == 4) strMonth = "MAY";
		else if(month == 5) strMonth = "JUN";
		else if(month == 6) strMonth = "JUL";
		else if(month == 7) strMonth = "AUG";
		else if(month == 8) strMonth = "SEP"; 
		else if(month == 9) strMonth = "OCT";
		else if(month == 10) strMonth = "NOV";
		else if(month == 11) strMonth = "DEC";
		return strMonth;
	}
}// class closed