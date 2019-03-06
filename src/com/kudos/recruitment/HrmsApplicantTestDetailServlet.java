package com.kudos.recruitment;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.sql.*;
import com.kudos.recruitment.*;

public class HrmsApplicantTestDetailServlet extends HttpServlet{
	String dBUser  = "";
	String dBPswd  = "";
	String dBUrl   = "";
	
	/**Initialize global variables*/
	public void init(ServletConfig config) throws ServletException {
		System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
		dBUser = "root";
		dBPswd = "password";
		dBUrl  = "jdbc:mysql://localhost:3306/hrm";
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
		session.setAttribute("ErrorMsg",null);
		String target = "";
		String action = request.getParameter("action");
		String dBOperation = "";
		dBOperation = (String)request.getParameter("dBOperation");
		String editForm=null;
		editForm=(String)request.getParameter("editForm");
		System.out.println("editForm------"+editForm);
		session.setAttribute("dBOperation",dBOperation);
		System.out.println("<<<HrmsApplicantTestDetailServlet.dBOperation()>>>");
		
		System.out.println("<<<HrmsApplicantTestDetailServlet.dBOperation() for diffrent condition>>>" +dBOperation);
		
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("callForWritten")) ) {
			System.out.println("HrmsApplicantTestDetailServlet.dBOperation==callForWritten()");
			action = "hrmsApplicantSelect";
			System.out.println("HrmsApplicantTestDetailServlet.action==callForWritten()=" +action);
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("call")) ){
			action = "hrmsApplicantCall";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("remove")) ){
			action = "hrmsApplicantRemove";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("detail")) ){
			System.out.println("HrmsApplicantTestDetailServlet.detail()");
			action = "hrmsApplicantDetail";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("updateWrittenPerformance"))){
			action = "updateApplicantWrittenPerformance";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("updateTechnicalPerformance"))){
			action = "updateApplicantTechnicalPerformance";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("updateHrPerformance"))){
			action = "updateApplicantHrPerformance";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("updateWrittenRecord"))){
			action = "updateWrittenRecord";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("updateTechnicalRecord"))){
			action = "updateTechnicalRecord";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("updateHrRecord"))){
			action = "updateHrRecord";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("shortListAfterWritten"))){
			action = "shortListAfterWritten";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("shortListAfterTechnical"))){ // chnge
			action = "shortListAfterTechnical";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("shortListAfterHr"))){ // chnge
			action = "shortListAfterHr";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("applicantCallForTechnical"))){
			action = "applicantCallForTechnical";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("applicantCallForHr"))){
			action = "applicantCallForHr";
		}
		else
		if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("applicantCallForFinal"))){
			action = "applicantCallForFinal";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("delete")) ){
			action = "applicantRemoveForTechnical";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("discard")) ){
			action = "applicantRemoveForHR";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("discardForFinal")) ){
			action = "applicantRemoveForFinal";
		}
		else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("finalSelected")) ){
			action = "applicantFinalSelected";
		} else if( (dBOperation != null && dBOperation.length() > 0) && (dBOperation.equals("issueOfferLetter")) ){
			action = "applicantOfferLetter";
		}
		else if( (editForm != null && editForm.length() > 0) && (editForm.equals("editDetail"))){
			
			String applicantId=request.getParameter("applicantId");
			action="editFormforFilledInTR";
			System.out.println("applicantId for edit -"+applicantId);
		}
		
		String actionSubmit = request.getParameter("actionSubmit");
		String actionWrittenDetailSubmit = request.getParameter("actionWrittenDetailSubmit");
		String actionEdit = request.getParameter("actionEdit");
		String actionSelect = request.getParameter("actionSelect");
		
		System.out.println("actionSubmit=="+actionSubmit+"; actionWrittenDetailSubmit=="+actionWrittenDetailSubmit);
		System.out.println("actionEdit=="+actionEdit+"; actionSelect=="+actionSelect);
		
		if ( actionSubmit != null || actionEdit != null || actionSelect != null || actionWrittenDetailSubmit != null  ){
			
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( actionSubmit != null && actionSubmit.equals("hrmsApplicantRegisterSubmit") ){
					System.out.println("in the hrmsApplicantRegisterSubmit ");
					action = "hrmsApplicantRegisterSubmit";
				}else{
					action="hrmsTestUpdateDetails";
				}
			}
			else if ( request.getParameter("submit").equals("Update") ){
				if ( actionEdit.equals("hrmsApplicantEditSubmit") )
					action = "hrmsApplicantEditSubmit";
			}  
			else if ( request.getParameter("submit").equals("Enter The Test detail") ){
				if (actionSelect.equals("applicantCallForWrittenTestSubmit")){
					action = "applicantCallForWrittenTestSubmit";
				}
			}  
			else if ( request.getParameter("submit").equals("Submit Detail") ){
				if ( actionSubmit.equals("hrmsApplicantWrittenTestDetailSubmit") ){
					System.out.println("in the hrmsApplicantWrittenTestDetailSubmit ");
					action = "hrmsApplicantWrittenTestDetailSubmit";
				}
			}
			else if ( request.getParameter("submit").equals("Update Detail") ){
				if ( actionSubmit.equals("hrmsApplicantWrittenTestDetailUpdateSubmit") ){
					System.out.println("in the hrmsApplicantWrittenTestDetailUpdateSubmit ");
					action = "hrmsApplicantWrittenTestDetailUpdateSubmit";
				}
			}
			else if ( request.getParameter("submit").equals("Call For Technical") ){
				if ( actionSubmit.equals("applicantCallTechnicalDetailSubmit") ){
					System.out.println("in the applicantCallTechnicalDetailSubmit ");
					action = "applicantCallTechnicalDetailSubmit";
				}
			}
			else  if ( request.getParameter("submit").equals("Call For HR") ){
				if ( actionSubmit.equals("applicantCallHrDetailSubmit") ){
					System.out.println("in the applicantCallHrDetailSubmit ");
					action = "applicantCallHrDetailSubmit";
				}
			}
			else if ( request.getParameter("submit").equals("Select Final") ){
				if ( actionSubmit.equals("applicantSelectForFinalSubmit") ){
					System.out.println("in the applicantSelectForFinalSubmit ");
					action = "applicantSelectForFinalSubmit";
				}
			}
		}
		
		// if action is no null
		if (action!=null) {
			System.out.println("in the action = "+action);
			
			if (action.equals("updateWrittenRecord") || action.equals("updateTechnicalRecord") || action.equals("updateHrRecord")){
				
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				System.out.println("applicantId======******="+applicantId);
				HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				
				// selecting particular record  for HR interview
				if (action.equals("updateWrittenRecord"))	{
					
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'WR' and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0); // it has only one record always
					System.out.println("applicant id---by kk chandan....--+"+applicantTestDetailDBObj.applicantId);
				}
				
				// selecting particular record  for HR interview
				if (action.equals("updateTechnicalRecord"))	{
					
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'TR' and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0);// it has only one record always
					System.out.println("updateTechnicalRecord for ApplicantId by kk chandan---"+applicantTestDetailDBObj.applicantId);
				}
			

				// selecting particular record  for HR interview
				if (action.equals("updateHrRecord")) {
					
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'HR' and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0);// it has only one record always
				}
				
			
				//applicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestDetailDBMethods.getRecordByPrimaryKey(applicantId);
				int rval = 0; 
				if ( (applicantTestDetailDBObj.applicantId != null ) ) { 
					
					session.setAttribute("applicantTestDetailDBObj",applicantTestDetailDBObj);
					
					target = "/jsp/recruitment/applicantTestDetailUpdate.jsp";
					System.out.println("target by kk chandan-"+target);
					
				} else {
					
					String lErrorMsg = "Applicant doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					target = "/jsp/hrmsDefault.jsp";
				}
				
			} else if (action.equals("hrmsApplicantDetail")) {
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser, dBPswd, dBUrl);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj =(HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(applicantId);
				session.setAttribute("applicantDBObj", applicantDBObj);
				target = "/jsp/recruitment/applicantEdit.jsp";
			}
			
			else if (action.equals("hrmsApplicantWrittenTestDetailUpdateSubmit")) {
				
				dBOperation = " ";
				dBOperation = (String)session.getAttribute("dBOperation");
				System.out.println("dBOperation>>>>>>>>>>>>>>>>>>>>>>>>>"+dBOperation);
				ArrayList testRecords = new ArrayList();
				String applicantId = "";
				String testId="";
				String presentStatus = "";
				long marksGained = 0;
				String passFail = "";
				String nextRound = "";
				System.out.println("****************************************");
				applicantId = (String)request.getParameter("applicantId");
				testId = (String)request.getParameter("testId");
				System.out.println("applicantId======******="+applicantId);
				presentStatus = (String)request.getParameter("presentStatus");
				System.out.println("presentStatus"+applicantId+"======******="+presentStatus);
				marksGained  = Long.parseLong((String)request.getParameter("marksGained"));
				passFail   = (String)request.getParameter("passFail");
				nextRound    = (String)request.getParameter("nextRound");
				HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				String criteria="";
				criteria = "where test_id='"+testId+"' and applicant_id='"+applicantId+"'";
				testRecords = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
				applicantTestDetailDBObj = (HrmsApplicantTestDetail)testRecords.get(0);
				int rval = 0; 
				
				if ( (applicantTestDetailDBObj.applicantId != null ) ) { 
					
					System.out.println("applicantTestDetailDBObj.test_id"+applicantTestDetailDBObj.testId);
					System.out.println("applicantTestDetailDBObj.test_date"+applicantTestDetailDBObj.testDate);
					System.out.println("applicantTestDetailDBObj.test_time"+applicantTestDetailDBObj.testTime);

					HrmsApplicantTestDetail updateApplicantTestDetailDBObj = new HrmsApplicantTestDetail();
					updateApplicantTestDetailDBObj.testId = applicantTestDetailDBObj.testId;
					updateApplicantTestDetailDBObj.testName = applicantTestDetailDBObj.testName;
					updateApplicantTestDetailDBObj.applicantId = applicantTestDetailDBObj.applicantId;
					updateApplicantTestDetailDBObj.applicantName = applicantTestDetailDBObj.applicantName;
					updateApplicantTestDetailDBObj.testDate = applicantTestDetailDBObj.testDate;
					updateApplicantTestDetailDBObj.testTime = applicantTestDetailDBObj.testTime;
					updateApplicantTestDetailDBObj.totalMarks = applicantTestDetailDBObj.totalMarks ;
					updateApplicantTestDetailDBObj.testStatus = applicantTestDetailDBObj.testStatus;

					updateApplicantTestDetailDBObj.presentStatus = presentStatus;
					updateApplicantTestDetailDBObj.marksGained = marksGained;
					updateApplicantTestDetailDBObj.passFail = passFail;
					updateApplicantTestDetailDBObj.nextRound = nextRound;

					rval = applicantTestDetailDBMethods.updateApplicantTestDetail(updateApplicantTestDetailDBObj);
					ArrayList applicantTestDetailList = new ArrayList();
					criteria = "";
					if(dBOperation != null && dBOperation.equals("updateWrittenRecord")) {
						
						criteria = "where test_status='WR'";
						
					} else if(dBOperation != null && dBOperation.equals("updateTechnicalRecord")) {
						criteria = "where test_status='TR' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('HR','confirm'))";
						
					} else if(dBOperation != null && dBOperation.equals("updateHrRecord")) {
						criteria = "where test_status='HR' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('confirm'))";
						
					}
					
					applicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					session.setAttribute("applicantTestDetailList",applicantTestDetailList);
					target = "/jsp/recruitment/applicantTestDetailList.jsp";
				}
				else {
					
					String lErrorMsg = "Applicant doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					target = "/jsp/hrmsDefault.jsp";
				}
			}
			else if (action.equals("hrmsApplicantWrittenTestDetailSubmit")) { // for all written,technical and HR detail
		
				ArrayList applicantTestList = new ArrayList();
				//String dBOperation = "";
				dBOperation = (String)session.getAttribute("dBOperation");
				System.out.println("i m in>>>>>>>>>> "+dBOperation); 
				
				if( dBOperation != null && ( dBOperation.equals("applicantCallForTechnical") || dBOperation.equals("applicantCallForHr") )) {
					
					System.out.println("i m in>>>>>>>>>> "+dBOperation); 
					applicantTestList = (ArrayList)session.getAttribute("selectApplicantTechnicalList");
					System.out.println("i m in>><applicantTestList>>>>>>>>> "+applicantTestList);
				}
				else {
					applicantTestList = (ArrayList)session.getAttribute("applicantTestList");
					System.out.println("i m in>applicantTestList>>>>>>>>> "+applicantTestList);
				}
				
				
             
				HrmsApplicantTestDetail popApplicantTestDetailDBObj = new HrmsApplicantTestDetail(); 
				System.out.println("HrmsApplicantTestDetailServlet.popApplicantTestDetailDBObj()" +popApplicantTestDetailDBObj);
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				System.out.println("HrmsApplicantTestDetailServlet.applicantTestDetailDBMethods()" +applicantTestDetailDBMethods);
				popApplicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestDetailDBMethods.populateApplicantTestDetailDBObjFromReq(request);
				System.out.println("HrmsApplicantTestDetailServlet.popApplicantTestDetailDBObj()" +popApplicantTestDetailDBObj);
				if ( applicantTestList != null && applicantTestList.size() > 0 ) {
					
					for (int size =1; size <= applicantTestList.size() ;size++ ) {
						
						HrmsApplicantTestDetail listApplicantTestDetailDBObj  = new HrmsApplicantTestDetail();
						HrmsApplicant applicantDBObj  = new HrmsApplicant();
						if( dBOperation != null && ( dBOperation.equals("applicantCallForTechnical") || dBOperation.equals("applicantCallForHr") )) {
							
							listApplicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestList.get(size-1); 
						}
						else {
							System.out
									.println("HrmsApplicantTestDetailServlet.applicantTestList()" +size);
							
							applicantDBObj = (HrmsApplicant)applicantTestList.get(size-1);
						}
						
					
						HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
						applicantTestDetailDBObj.testId   =  popApplicantTestDetailDBObj.testId;
						applicantTestDetailDBObj.testName = popApplicantTestDetailDBObj.testName;
						if( dBOperation != null && ( dBOperation.equals("applicantCallForTechnical") || dBOperation.equals("applicantCallForHr") ) ) {
							
							applicantTestDetailDBObj.applicantId = listApplicantTestDetailDBObj.applicantId;
							applicantTestDetailDBObj.applicantName = listApplicantTestDetailDBObj.applicantName;
						}
						else {
							
							applicantTestDetailDBObj.applicantId = applicantDBObj.applicantId;
							applicantTestDetailDBObj.applicantName = applicantDBObj.applicantName;
						}
						
						applicantTestDetailDBObj.testDate = popApplicantTestDetailDBObj.testDate;
						applicantTestDetailDBObj.testTime = popApplicantTestDetailDBObj.testTime;
						applicantTestDetailDBObj.presentStatus = "";
						applicantTestDetailDBObj.totalMarks = popApplicantTestDetailDBObj.totalMarks;
						applicantTestDetailDBObj.marksGained = 0;
						if( dBOperation != null && dBOperation.equals("applicantCallForTechnical")){
							
							applicantTestDetailDBObj.testStatus= "TR";
						}
						
						else if( dBOperation != null && dBOperation.equals("applicantCallForHr")){
							applicantTestDetailDBObj.testStatus= "HR";
						
						} else if( dBOperation != null && dBOperation.equals("shortListAfterHr")) {
							applicantTestDetailDBObj.testStatus= "confirm"; 
						
						} else {
							applicantTestDetailDBObj.testStatus= "WR";
							System.out
									.println("applicantTestDetailDBObj.testStatus= WR.doPost()");
						}
						
					
						applicantTestDetailDBObj.passFail= "";
						applicantTestDetailDBObj.nextRound = "";
						int rval =  applicantTestDetailDBMethods.insertApplicantTestDetail(applicantTestDetailDBObj);
						System.out.println("............vivek..............-"+rval);
					}//end of for loop
				} //end of if before for loop 
				
				
				ArrayList ApplicantTestDetailList = new ArrayList();
				String criteria = "";
				if( dBOperation != null && dBOperation.equals("applicantCallForHr")) {
					criteria = " where test_status='TR' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('HR','confirm'))";
				}
				else
					criteria = " where test_status not in ('TR','HR','confirm') and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('TR','HR','confirm'))";
				
				ApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
				session.setAttribute("ApplicantTestDetailList",ApplicantTestDetailList);
				System.out.println("ApplicantTestDetailList.size=====>>>>>>>>>>"+ApplicantTestDetailList.size());
				
				// setting prev selected list to null
				session.setAttribute("selectApplicantTechnicalList",null);
				System.out.println("*****&&&&&&&&&&&&&&&&");
				target = "/jsp/recruitment/applicantTestDetailList.jsp";
				System.out.println("applicantTestDetailList^^^^^^^^^^^^^^^^^^^^^");
			}
			else if( action.equals("applicantSelectForFinalSubmit")) {   
				
				ArrayList applicantTestList = new ArrayList();
				applicantTestList = (ArrayList)session.getAttribute("selectApplicantTechnicalList");
				HrmsApplicantTestDetail popApplicantTestDetailDBObj = new HrmsApplicantTestDetail(); 
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				popApplicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestDetailDBMethods.populateApplicantTestDetailDBObjFromReq(request);
				if ( applicantTestList != null && applicantTestList.size() > 0 ) {
					
					for (int size =1; size <= applicantTestList.size() ;size++ ) {
						
						HrmsApplicantTestDetail listApplicantTestDetailDBObj  = new HrmsApplicantTestDetail();
						HrmsApplicant applicantDBObj  = new HrmsApplicant();
						listApplicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestList.get(size-1);  
						HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
						applicantTestDetailDBObj.testId   = "final";
						applicantTestDetailDBObj.testName = "final";
						applicantTestDetailDBObj.applicantId = listApplicantTestDetailDBObj.applicantId;
						applicantTestDetailDBObj.applicantName = listApplicantTestDetailDBObj.applicantName;
						applicantTestDetailDBObj.testDate = "";
						applicantTestDetailDBObj.testTime = "";
						applicantTestDetailDBObj.presentStatus = "";
						applicantTestDetailDBObj.totalMarks = 0;
						applicantTestDetailDBObj.marksGained = 0;
						applicantTestDetailDBObj.testStatus= "confirm"; 
						applicantTestDetailDBObj.passFail= "";
						applicantTestDetailDBObj.nextRound = "";
						int rval =  applicantTestDetailDBMethods.insertApplicantTestDetail(applicantTestDetailDBObj);
					}
				}
				
				/* select those applicant who doesn.t selected for final*/
				ArrayList ApplicantTestDetailList = new ArrayList();
				String criteria = "";
				criteria = " where test_status='confirm'"; 
				ApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
				session.setAttribute("ApplicantTestDetailList",ApplicantTestDetailList);
				System.out.println("ApplicantTestDetailList.size=====>>>>>>>>>>"+ApplicantTestDetailList.size());
				
				// setting prev selected list to null
				session.setAttribute("selectApplicantTechnicalList",null);
				target = "/jsp/recruitment/applicantFinalSelectedList.jsp";
			}
			else if (action.equals("hrmsApplicantSelect")) {    
				
				System.out.println("HrmsApplicantTestDetailServlet.doPost()hrmsApplicantSelect"); 
			
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser,dBPswd,dBUrl);
				ArrayList applicantList = new ArrayList();
				String criteria = "";
				criteria = " where applicant_id not in ( select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('WR','TR','HR','confirm'))";
				System.out.println("HrmsApplicantTestDetailServlet.criteria()" +criteria);
				applicantList = (ArrayList)applicantDBMethods.selectApplicantByCriteria(criteria);
				session.setAttribute("applicantList",applicantList);
				target = "/jsp/recruitment/applicantList.jsp";
			}
			else if (action.equals("updateApplicantWrittenPerformance") || action.equals("updateApplicantTechnicalPerformance") || action.equals("updateApplicantHrPerformance")){  
				
				System.out.println("HrmsApplicantTestDetailServlet.doPost()updateApplicantWrittenPerformance");
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				ArrayList applicantTestDetailList = new ArrayList();
				String  criteria = "";
				if (action.equals("updateApplicantTechnicalPerformance")) {
					
					criteria = "where test_status='TR' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('HR','confirm'))";
					
				}
				else if (action.equals("updateApplicantHrPerformance")) { 
					
					criteria = "where test_status='HR' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('confirm'))";
				}
				else
					criteria = "where test_status='WR' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('TR','HR','confirm'))";
				
				applicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
				session.setAttribute("ApplicantTestDetailList",applicantTestDetailList);
				target = "/jsp/recruitment/applicantTestDetailList.jsp";
			}
			else if (action.equals("hrmsApplicantCall")) {
				
				String applicantId = "";
				String str="vivek";
				applicantId = (String)request.getParameter("applicantId");
				 session.setAttribute("str1", str);
				System.out.println("HrmsApplicantTestDetailServlet.applicantId()" +applicantId);
				ArrayList previousApplicantTestList = new ArrayList();
				String str2=(String)session.getAttribute("str1");
				str2=str2.concat("kumar");
				String str1=(String)session.getAttribute("str1");
				//System.out.println("String..................."+str1+".............."+str2);
				previousApplicantTestList = (ArrayList)session.getAttribute("applicantTestList");
				if(previousApplicantTestList!=null){
					System.out.println("size of previousApplicantTestList-"+previousApplicantTestList.size());
				}
				System.out.println("HrmsApplicantTestDetailServlet.previousApplicantTestList()" +previousApplicantTestList);
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser,dBPswd,dBUrl);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj = (HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(applicantId); 
				System.out.println("HrmsApplicantTestDetailServlet.applicantDBObj()" +applicantDBObj);
				int notAddedFlag=0;
				int addedFlag = 0;
				if(previousApplicantTestList != null && previousApplicantTestList.size() > 0 ){
					
					System.out.println("HrmsApplicantTestDetailServlet.previousApplicantTestList != null && previousApplicantTestList.size()()" );
					for( int listSize = 1; listSize <= previousApplicantTestList.size(); listSize++ ) {
						System.out.println("**********i m in for loop");
						HrmsApplicant inApplicantDBObj = new HrmsApplicant();
						inApplicantDBObj = (HrmsApplicant)previousApplicantTestList.get(listSize-1);
						if( !((inApplicantDBObj.applicantId).equals((applicantDBObj.applicantId))) ) {
							notAddedFlag = 1;
							System.out.println("HrmsApplicantTestDetailServlet.notAddedFlag()" +notAddedFlag);
						}
						else {
						addedFlag = 1;
						System.out.println("HrmsApplicantTestDetailServlet.addedFlag()" +addedFlag);
						}
					}//end of for loop
					if ( addedFlag == 0) {
						
						System.out.println("**********i m in add   addedFlag=="+addedFlag); 
						previousApplicantTestList.add(applicantDBObj);
						System.out.println("**********i m in  previousApplicantTestList");
					}
					System.out.println("**********i m out of for previousApplicantTestList.size()-"+previousApplicantTestList.size());
//					previousApplicantTestList.remove(previousApplicantTestList.size()-1);
//					System.out.println("remove................................then");
					ArrayList applicantTestList =new ArrayList();
					applicantTestList=(ArrayList)session.getAttribute("applicantTestList");
					System.out.println("size of applicantTestList--------"+applicantTestList.size() );
				}//end of first if
				else {
					
					ArrayList applicantTestList = new ArrayList();
					applicantTestList.add(applicantDBObj);
					session.setAttribute("applicantTestList",applicantTestList);
					System.out.println("HrmsApplicantTestDetailServlet.applicantTestList()" +applicantTestList);
				}
				target = "/jsp/recruitment/applicantList.jsp";
			}   
			else if (action.equals("hrmsApplicantRemove")){ 
				
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				ArrayList previousApplicantTestList = new ArrayList();
				previousApplicantTestList = (ArrayList)session.getAttribute("applicantTestList");
				HrmsApplicantHelper applicantDBMethods = new HrmsApplicantHelper(dBUser,dBPswd,dBUrl);
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj = (HrmsApplicant)applicantDBMethods.getRecordByPrimaryKey(applicantId);
				
				for( int listSize = 1; listSize <= previousApplicantTestList.size(); listSize++ ){
					HrmsApplicant inApplicantDBObj = new HrmsApplicant();
					inApplicantDBObj = (HrmsApplicant)previousApplicantTestList.get(listSize-1);
					if((inApplicantDBObj.applicantId).equals(applicantId) ) {
						int lIndex = previousApplicantTestList.indexOf(inApplicantDBObj);
						previousApplicantTestList.remove(lIndex);
					}
				}	
				session.setAttribute("applicantTestList",previousApplicantTestList);
				target = "/jsp/recruitment/applicantList.jsp";
			}   
			else if (action.equals("shortListAfterWritten") || action.equals("shortListAfterTechnical") || action.equals("shortListAfterHr") ){
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				session.setAttribute("selectApplicantTechnicalList",null);
				ArrayList applicantTestDetailList = new ArrayList();
				String criteria = "";
				if ( action.equals("shortListAfterWritten")) {
					criteria = " where test_status = 'WR' and pass_fail='Pass' and applicant_id not in (select applicant_id from APPLICANT_TEST_DETAIL where test_status in ('TR','HR','confirm'))";
				}
				else if ( action.equals("shortListAfterTechnical")) {
					criteria = " where test_status = 'TR' and pass_fail='Pass'" ;
				}
				else if ( action.equals("shortListAfterHr")) {
					criteria = " where test_status = 'HR' and pass_fail='Pass'" ;
				}
				applicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
				session.setAttribute("applicantTestDetailList",applicantTestDetailList);
				target = "/jsp/recruitment/applicantTestDetailList.jsp";
			}
			else if ( action.equals("applicantFinalSelected") ) {     	   
				System.out.println(" i m in======applicant_final_selected");
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				session.setAttribute("selectApplicantTechnicalList",null);
				ArrayList ApplicantTestDetailList = new ArrayList();
				String criteria = "";
				criteria = " where test_status ='confirm'";
				ApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
				session.setAttribute("ApplicantTestDetailList",ApplicantTestDetailList);
				System.out.println("ApplicantTestDetailList===="+ApplicantTestDetailList.size());
				target = "/jsp/recruitment/applicantFinalSelectedList.jsp";
			}
			else if (action.equals("applicantCallForTechnical") || action.equals("applicantCallForHr") || action.equals("applicantCallForFinal") ) { // for both tech select as wel as Hr select
			        
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				ArrayList prevApplicantTechnicalList = new ArrayList();
				prevApplicantTechnicalList = (ArrayList)session.getAttribute("selectApplicantTechnicalList");
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
				
				if (action.equals("applicantCallForHr")) {// selecting particular record  for HR interview
				
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'TR' and pass_fail='Pass'and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0);// it has only one record always
				}
				else if (action.equals("applicantCallForFinal")) {// selecting particular record  for HR interview
				
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'HR' and pass_fail='Pass'and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0);// it has only one record always
				} 
				else
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestDetailDBMethods.getRecordByPrimaryKey(applicantId);
				
				int notAddedFlag=0;
				int addedFlag = 0;
				
				if(prevApplicantTechnicalList != null && prevApplicantTechnicalList.size() > 0 ) {
					for( int listSize = 1; listSize <= prevApplicantTechnicalList.size(); listSize++ ) {
						System.out.println("**********i m in for loop");
						HrmsApplicantTestDetail inApplicantTestDetailHelper = new HrmsApplicantTestDetail();
						inApplicantTestDetailHelper = (HrmsApplicantTestDetail)prevApplicantTechnicalList.get(listSize-1);
						
						if( !((inApplicantTestDetailHelper.applicantId).equals((applicantTestDetailDBObj.applicantId))) ) {
							notAddedFlag = 1;
						}
						else{
							addedFlag = 1;
						}
					}	
					if ( addedFlag == 0){
						System.out.println("**********i m in add   addedFlag=="+addedFlag); 
						prevApplicantTechnicalList.add(applicantTestDetailDBObj);
					}
					System.out.println("**********i m out of for previousApplicantTestList.size()"+prevApplicantTechnicalList.size());
				}
				else {
					ArrayList selectApplicantTechnicalList = new ArrayList();
					selectApplicantTechnicalList.add(applicantTestDetailDBObj);
					session.setAttribute("selectApplicantTechnicalList",selectApplicantTechnicalList);
					System.out.println(" selectApplicantTestList.size()$$$$$$$="+selectApplicantTechnicalList.size());
				}
				target = "/jsp/recruitment/applicantTestDetailList.jsp";
			}
			else if (action.equals("applicantRemoveForTechnical") || action.equals("applicantRemoveForHr") || action.equals("applicantRemoveForFinal")) {        
				String applicantId = "";
				applicantId = (String)request.getParameter("applicantId");
				ArrayList prevApplicantTechnicalList = new ArrayList();
				prevApplicantTechnicalList = (ArrayList)session.getAttribute("selectApplicantTechnicalList");
				HrmsApplicantTestDetailHelper applicantTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				HrmsApplicantTestDetail applicantTestDetailDBObj = new HrmsApplicantTestDetail();
				
				if (action.equals("applicantRemoveForHr")) {// selecting particular record  for HR interview
				
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'TR' and pass_fail='Pass'and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0);// it has only one record always
				}
				if (action.equals("applicantRemoveForFinal")) {// selecting particular record  for HR interview
				
					ArrayList dummyApplicantTestDetailList = new ArrayList();
					String criteria = "";
					criteria = " where test_status = 'HR' and pass_fail='Pass'and applicant_id='"+applicantId+"' " ;
					dummyApplicantTestDetailList = (ArrayList)applicantTestDetailDBMethods.selectApplicantTestDetailByCriteria(criteria);
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)dummyApplicantTestDetailList.get(0);// it has only one record always
				}
				else
					applicantTestDetailDBObj = (HrmsApplicantTestDetail)applicantTestDetailDBMethods.getRecordByPrimaryKey(applicantId); 
				
				for( int listSize = 1; listSize <= prevApplicantTechnicalList.size(); listSize++ ) {
					
					HrmsApplicantTestDetail inApplicantTestDetailHelper = new HrmsApplicantTestDetail();
					inApplicantTestDetailHelper = (HrmsApplicantTestDetail)prevApplicantTechnicalList.get(listSize-1);
					if((inApplicantTestDetailHelper.applicantId).equals(applicantId) ) {
						int lIndex = prevApplicantTechnicalList.indexOf(inApplicantTestDetailHelper);
						prevApplicantTechnicalList.remove(lIndex);
					}
				}	
				System.out.println("prevApplicantTechnicalList.size()===" +prevApplicantTechnicalList.size()); 
				session.setAttribute("selectApplicantTechnicalList",prevApplicantTechnicalList);
				target = "/jsp/recruitment/applicantTestDetailList.jsp";
			}   
			else if ( action.equals("applicantCallForWrittenTestSubmit") || action.equals("applicantCallTechnicalDetailSubmit") || action.equals("applicantCallHrDetailSubmit") ) {
				System.out.println("<<-->>HrmsApplicantTestDetailServlet.doPost()applicantCallForWrittenTestSubmit");
				target = "/jsp/recruitment/applicantTestDetailCreate.jsp";
			} 
			else if (action.equals("applicantOfferLetter")) {
				target = "/jsp/recruitment/applicantOfferLetter.jsp";
			}
			else if(action.equals("editFormforFilledInTR")){
				HrmsApplicantTestDetail appId = new HrmsApplicantTestDetail();
				String applId="";
				applId = (String)request.getParameter("applicantId");
				HrmsApplicantTestDetailHelper appTestDetailDBMethods = new HrmsApplicantTestDetailHelper(dBUser,dBPswd,dBUrl);
				appId =appTestDetailDBMethods.getRecordByPrimaryKey(applId);
				session.setAttribute("appId", appId);
				target = "/jsp/recruitment/applicantTestDetailUpdate.jsp";
			}
			else if(action.equals("hrmsTestUpdateDetails")){
				HrmsApplicantTestDetail hrmsApplicantTestDetail = new HrmsApplicantTestDetail();
				HrmsApplicantTestDetailHelper hrmsApplicantTestDetailHelper = new HrmsApplicantTestDetailHelper();
				hrmsApplicantTestDetail=(HrmsApplicantTestDetail)hrmsApplicantTestDetailHelper.populateApplicantTestDetailDBObjFromReq(request);
				
				
			}
		}
		// (action== null )

		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}
 // doPost closed
}
// class closed


