package com.kudos.leave;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import com.kudos.jdbc.DatabseHelper;
import com.kudos.leave.LeaveRequest;
import com.kudos.util.DateUtil;
public class LeaveManagementHelper{
	public String DBUser;
	public String DBPswd;
	public String DBUrl ;
	public LeaveManagementHelper(){ }
	public LeaveManagementHelper(String inDBUser, String inDBPswd, String inDBUrl ){
		DBUser = inDBUser ;
		DBPswd = inDBPswd;
		DBUrl  = inDBUrl;
	}
	public void initializeLeaveRequest(LeaveRequest inLeaveRequest ){
		inLeaveRequest.reqId =""; 
		inLeaveRequest.empId ="";         
		inLeaveRequest.empName ="";       
		inLeaveRequest.todayDate=new Date();   
		inLeaveRequest.levelId="";
		inLeaveRequest.deptId="";
		inLeaveRequest.fromDate=new Date();   
		inLeaveRequest.toDate=new Date();   
		inLeaveRequest.days= 0;   
		inLeaveRequest.reason="";  
		inLeaveRequest.leaveType=""; 
		inLeaveRequest.activity1="";
		inLeaveRequest.activity2="";
		inLeaveRequest.activity3="";
		inLeaveRequest.person1="";
		inLeaveRequest.person2="";
		inLeaveRequest.person3="";
		inLeaveRequest.detail1="";
		inLeaveRequest.detail2="";
		inLeaveRequest.detail3="";
		inLeaveRequest.leaveStatus="";
		inLeaveRequest.remark="";
		inLeaveRequest.address="";
	}
	public LeaveRequest getRecordByPrimaryKey(String inReqId, String inEmpId){
		LeaveRequest  leaveRequest = new LeaveRequest();
		java.sql.Date date;
		try{
			System.out.println("DBUser=="+DBUser+",DBPswd=="+DBPswd+",DBUrl=="+DBUrl);
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from LEAVE_REQUEST  ";
			lSqlString = lSqlString + "where req_id='"+inReqId+"' ";
			lSqlString = lSqlString + "and emp_id='"+inEmpId+"' ";
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			System.out.println("lSqlString====trtrt==within getRecordByPrimaryKey== "+lSqlString);
			if( rs.next()){
				System.out.println("fffff==="+rs.getString("emp_id"));
				leaveRequest.reqId   =  (String)rs.getString("req_id");
				leaveRequest.empId   =  (String)rs.getString("emp_id");
				leaveRequest.empName = (String)rs.getString("emp_name"); 
				
				
				if(rs.getDate("today_date")!=null)
				{
					date=rs.getDate("today_date");
				    leaveRequest.todayDate  = date;
				}
				if(rs.getDate("from_date")!=null)
				{
					date=rs.getDate("from_date");
				    leaveRequest.fromDate  = date;
				}
				if(rs.getDate("to_date")!=null)
				{
					date=rs.getDate("today_date");
				    leaveRequest.toDate  = date;
				}
								
				leaveRequest.levelId = (String)rs.getString("level_id");
				leaveRequest.deptId = (String)rs.getString("dept_id");
				leaveRequest.days =    rs.getInt("days");
				leaveRequest.reason = (String)rs.getString("reason");
				leaveRequest.leaveType = (String)rs.getString("leave_type");
				leaveRequest.activity1 = (String)rs.getString("activity_1");
				leaveRequest.activity2 = (String)rs.getString("activity_2");
				leaveRequest.activity3 = (String)rs.getString("activity_3");
				leaveRequest.person1 = (String)rs.getString("person_1");
				leaveRequest.person2 = (String)rs.getString("person_2");
				leaveRequest.person3 = (String)rs.getString("person_3");
				leaveRequest.detail1 = (String)rs.getString("detail_1");
				leaveRequest.detail2 = (String)rs.getString("detail_2");
				leaveRequest.detail3 = (String)rs.getString("detail_3");
				leaveRequest.leaveStatus = (String)rs.getString("leave_status");
				leaveRequest.address = (String)rs.getString("address");
				leaveRequest.remark = (String)rs.getString("remark");
				System.out.println("fffff==="+rs.getString("emp_id"));
			}
			else{
				initializeLeaveRequest(leaveRequest);
			}
			System.out.println("fffff====="+leaveRequest.empId);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return leaveRequest;
	}
	public ArrayList selectLeaveRequestByCriteria(String inCriteria) {
		ArrayList LeaveRequestList = new ArrayList();
		java.sql.Date date;
		try{
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from LEAVE_REQUEST";
			if( inCriteria != null && inCriteria.length() > 0 ){
				lSqlString = lSqlString +" "+inCriteria+"" ;
			}
			System.out.println("Criteria===== "+inCriteria+" and query="+lSqlString);
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			while( rs.next()){
				LeaveRequest  leaveRequest = new LeaveRequest();
				leaveRequest.reqId   =  (String)rs.getString("req_id");
				leaveRequest.empId   =  (String)rs.getString("emp_id");
				leaveRequest.empName = (String)rs.getString("emp_name"); 

				if(rs.getDate("today_date")!=null)
				{
					date=rs.getDate("today_date");
				    leaveRequest.todayDate  = date;
				}
				if(rs.getDate("from_date")!=null)
				{
					date=rs.getDate("from_date");
				    leaveRequest.fromDate  = date;
				}
				if(rs.getDate("to_date")!=null)
				{
					date=rs.getDate("today_date");
				    leaveRequest.toDate  = date;
				}
				
//				leaveRequest.todayDate  = (String)rs.getString("today_date");
//				
//				leaveRequest.fromDate  = (String)rs.getString("from_date");
//				
//				leaveRequest.toDate = (String)rs.getString("to_date");

				
				leaveRequest.levelId = (String)rs.getString("level_id");
				leaveRequest.deptId = (String)rs.getString("dept_id");
				leaveRequest.days =    rs.getInt("days");
				leaveRequest.reason = (String)rs.getString("reason");
				leaveRequest.leaveType = (String)rs.getString("leave_type");
				leaveRequest.activity1 = (String)rs.getString("activity_1");
				leaveRequest.activity2 = (String)rs.getString("activity_2");
				leaveRequest.activity3 = (String)rs.getString("activity_3");
				leaveRequest.person1 = (String)rs.getString("person_1");
				leaveRequest.person2 = (String)rs.getString("person_2");
				leaveRequest.person3 = (String)rs.getString("person_3");
				leaveRequest.detail1 = (String)rs.getString("detail_1");
				leaveRequest.detail2 = (String)rs.getString("detail_2");
				leaveRequest.detail3 = (String)rs.getString("detail_3");
				leaveRequest.leaveStatus = (String)rs.getString("leave_status");
				leaveRequest.address = (String)rs.getString("address");
				leaveRequest.remark = (String)rs.getString("remark");
				LeaveRequestList.add(leaveRequest);
			}
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return LeaveRequestList;
	}
	public int updateLeaveRequestByPrimaryKey(LeaveRequest inLeaveRequest){
		int recupd = 0; 
		String lQuery = "";
		lQuery = lQuery +"update LEAVE_REQUEST set emp_name='"+inLeaveRequest.empName+"'  "; 

		//lQuery = lQuery +" , today_date=to_date('"+inLeaveRequest.todayDate+"','yyyy-mm-dd') ";
		//lQuery = lQuery +" , today_date='"+inLeaveRequest.todayDate+"' ";

		String todayDateStr = DateUtil.getDateText(inLeaveRequest.todayDate, DateUtil._YYYY_MM_DD);
		lQuery = lQuery +" , today_date='"+todayDateStr+"' ";
		
		
		lQuery = lQuery +" , level_id='"+inLeaveRequest.levelId+"' ";
		lQuery = lQuery +" , dept_id='"+inLeaveRequest.deptId+"' ";

//		lQuery = lQuery +" , from_date=to_date('"+inLeaveRequest.fromDate+"', 'yyyy-mm-dd') ";
//		lQuery = lQuery +" , to_date=to_date('"+inLeaveRequest.toDate+"', 'yyyy-mm-dd') ";
		String fromDateStr = DateUtil.getDateText(inLeaveRequest.fromDate, DateUtil._YYYY_MM_DD);
		lQuery = lQuery +" , from_date='"+fromDateStr+"' ";
		String toDateStr = DateUtil.getDateText(inLeaveRequest.toDate, DateUtil._YYYY_MM_DD);
		lQuery = lQuery +" , to_date='"+toDateStr+"' ";
		
		
		
		lQuery = lQuery +" , days='"+inLeaveRequest.days+"' ";
		lQuery = lQuery +" , reason='"+inLeaveRequest.reason+"' ";
		lQuery = lQuery +" , leave_type='"+inLeaveRequest.leaveType+"' ";
		lQuery = lQuery +" , activity_1='"+inLeaveRequest.activity1+"' ";
		lQuery = lQuery +" , activity_2='"+inLeaveRequest.activity2+"' ";
		lQuery = lQuery +" , activity_3='"+inLeaveRequest.activity3+"' ";
		lQuery = lQuery +" , person_1='"+inLeaveRequest.person1+"' ";
		lQuery = lQuery +" , person_2='"+inLeaveRequest.person2+"' ";
		lQuery = lQuery +" , person_3='"+inLeaveRequest.person3+"' ";
		lQuery = lQuery +" , detail_1='"+inLeaveRequest.detail1+"' ";
		lQuery = lQuery +" , detail_2='"+inLeaveRequest.detail2+"' ";
		lQuery = lQuery +" , detail_3='"+inLeaveRequest.detail3+"' ";
		lQuery = lQuery +" , address='"+inLeaveRequest.address+"' ";
		lQuery = lQuery +" , remark='"+inLeaveRequest.remark+"' ";
		lQuery = lQuery +" , leave_status='"+inLeaveRequest.leaveStatus+"' ";
		lQuery = lQuery + "where req_id='"+inLeaveRequest.reqId+"' ";
		lQuery = lQuery + "and emp_id='"+inLeaveRequest.empId+"' ";
		System.out.println("lSqlString===:"+lQuery);
		try{
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	public LeaveRequest populateLeaveRequestFromReq(HttpServletRequest inReq){
		LeaveRequest  leaveRequest = new LeaveRequest();
		leaveRequest.reqId   =  (String)inReq.getParameter("reqId");
		leaveRequest.empId   =  (String)inReq.getParameter("empId");
		leaveRequest.empName = (String)inReq.getParameter("empName"); 
		String  todayDateStr = (String)inReq.getParameter("todayDate");
		 
		 if(todayDateStr !=null )
		 {
			 Date date = DateUtil.getDate(todayDateStr, DateUtil._YYYY_MM_DD);
			 leaveRequest.todayDate = date;
		 }
		 String  fromDateStr = (String)inReq.getParameter("fromDate");
		 
		 if(fromDateStr !=null )
		 {
			 Date date = DateUtil.getDate(fromDateStr, DateUtil._YYYY_MM_DD);
			 leaveRequest.fromDate = date;
		 }
		 String  toDateStr = (String)inReq.getParameter("toDate");
		 
		 if(toDateStr !=null )
		 {
			 Date date = DateUtil.getDate(toDateStr, DateUtil._YYYY_MM_DD);
			 leaveRequest.toDate = date;
		 }
		 
//		leaveRequest.todayDate  = (String)inReq.getParameter("todayDate");
//		leaveRequest.fromDate  = (String)inReq.getParameter("fromDate");
//		leaveRequest.toDate = (String)inReq.getParameter("toDate");
		
		 leaveRequest.levelId = (String)inReq.getParameter("levelId");
		leaveRequest.deptId = (String)inReq.getParameter("deptId");
		leaveRequest.days = Integer.parseInt((String)inReq.getParameter("days"));
		leaveRequest.reason = (String)inReq.getParameter("reason");
		leaveRequest.leaveType = (String)inReq.getParameter("leaveType");
		leaveRequest.activity1 = (String)inReq.getParameter("activity1");
		leaveRequest.activity2 = (String)inReq.getParameter("activity2");
		leaveRequest.activity3 = (String)inReq.getParameter("activity3");
		leaveRequest.person1 = (String)inReq.getParameter("person1");
		leaveRequest.person2 = (String)inReq.getParameter("person2");
		leaveRequest.person3 = (String)inReq.getParameter("person3");
		leaveRequest.detail1 = (String)inReq.getParameter("detail1");
		leaveRequest.detail2 = (String)inReq.getParameter("detail2");
		leaveRequest.detail3 = (String)inReq.getParameter("detail3");
		leaveRequest.leaveStatus = (String)inReq.getParameter("leaveStatus");
		leaveRequest.address = (String)inReq.getParameter("address");
		leaveRequest.remark = (String)inReq.getParameter("remark");
		return leaveRequest;
	}
	public int insertLeaveRequest(LeaveRequest inLeaveRequest){
		int recupd = 0; 
		String lQuery = "";
		lQuery = lQuery +"insert into LEAVE_REQUEST  values ( ";
		lQuery = lQuery +" '"+inLeaveRequest.reqId+"'  ";
		lQuery = lQuery +" , '"+inLeaveRequest.empId+"'  ";
		lQuery = lQuery +" , '"+inLeaveRequest.empName+"'  ";

//		lQuery = lQuery +" , to_date('"+inLeaveRequest.todayDate+"','yyyy-mm-dd')  ";
		String todayDateStr = DateUtil.getDateText(inLeaveRequest.todayDate, DateUtil._YYYY_MM_DD);
		lQuery = lQuery +" , '"+todayDateStr+"' ";

		//lQuery = lQuery +" , '"+inLeaveRequest.todayDate+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.levelId+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.deptId+"' ";
		
//		lQuery = lQuery +" , to_date('"+inLeaveRequest.fromDate+"', 'yyyy-mm-dd') ";
//		lQuery = lQuery +" , to_date('"+inLeaveRequest.toDate+"','yyyy-mm-dd') ";
		
		String fromDateStr = DateUtil.getDateText(inLeaveRequest.fromDate, DateUtil._YYYY_MM_DD);
		lQuery = lQuery +" , '"+fromDateStr+"' ";

		String toDateStr = DateUtil.getDateText(inLeaveRequest.toDate, DateUtil._YYYY_MM_DD);
		lQuery = lQuery +" , '"+toDateStr+"' ";
		
//		lQuery = lQuery +" , '"+inLeaveRequest.fromDate+"' ";
//    	lQuery = lQuery +" , '"+inLeaveRequest.toDate+"' ";
//		
		lQuery = lQuery +" , "+inLeaveRequest.days+" ";
		lQuery = lQuery +" , '"+inLeaveRequest.reason+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.leaveType+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.activity1+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.activity2+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.activity3+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.person1+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.person2+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.person3+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.detail1+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.detail2+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.detail3+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.address+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.remark+"' ";
		lQuery = lQuery +" , '"+inLeaveRequest.leaveStatus+"' ";
		lQuery = lQuery + " )";
		System.out.println("lSqlString===:"+lQuery);
		try{
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
}