package com.kudos.recruitment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.kudos.jdbc.DatabseHelper;




public class HrmsApplicantTestDetailHelper{
	public String dBUser ;
	public String dBPswd ;
	public String dBUrl ;
	public HrmsApplicantTestDetailHelper(){ }
	
	public HrmsApplicantTestDetailHelper(String inDBUser, String inDBPswd, String inDBUrl ){
		dBUser = inDBUser;
		dBPswd = inDBPswd;
		dBUrl = inDBUrl;
	}
	
	public void initializeHrmsApplicantTestDetailHelper(HrmsApplicantTestDetail inApplicantTestDetailHelper ){
		inApplicantTestDetailHelper.testId =  "";
		inApplicantTestDetailHelper.testName = "";
		inApplicantTestDetailHelper.applicantId= "";
		inApplicantTestDetailHelper.applicantName= "";
		inApplicantTestDetailHelper.testDate = "";
		inApplicantTestDetailHelper.testTime  = "";
		inApplicantTestDetailHelper.presentStatus = "";
		inApplicantTestDetailHelper.totalMarks= 0;
		inApplicantTestDetailHelper.marksGained = 0;
		inApplicantTestDetailHelper.testStatus= "";
		inApplicantTestDetailHelper.passFail= "";
		inApplicantTestDetailHelper.nextRound = "";
	}
	
	public HrmsApplicantTestDetail getRecordByPrimaryKey(String inApplicantId) {
		System.out.println("HrmsApplicantTestDetail.getRecordByPrimaryKey()");
		HrmsApplicantTestDetail  applicantTestDetailDBObj = new HrmsApplicantTestDetail();
		String date = null;
		try{
			System.out.println("dBUser == " + dBUser + ",dBPswd ==" + dBPswd + ",dBUrl" + dBUrl);
		
			
			Connection connection = DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			String sqlString =	"select * from APPLICANT_TEST_DETAIL  ";
			sqlString = sqlString + "where applicant_id='"+inApplicantId+"' ";
			ResultSet resultSet  = null;
			resultSet  = statement.executeQuery(sqlString);
			System.out.println("sqlString====trtrt==within getRecordByPrimaryKey== "+sqlString);
			if( resultSet.next()){
				System.out.println("test id==="+resultSet.getString("test_id"));
				applicantTestDetailDBObj.testId =  resultSet.getString("test_id");
				applicantTestDetailDBObj.testName = resultSet.getString("test_name");
				applicantTestDetailDBObj.applicantId = resultSet.getString("applicant_id");
				applicantTestDetailDBObj.applicantName = resultSet.getString("applicant_name");

				//date=resultSet.getDate("test_date");
				//if(date!=null)
				//applicantTestDetailDBObj.testDate = date.toString();
				if(resultSet.getString("TEST_DATE") !=null )
				{
					date = resultSet.getString("TEST_DATE");
					applicantTestDetailDBObj.testDate = date.toString();
				}

				String time=resultSet.getString("test_time");
				
				if(resultSet.getString("test_time") !=null ){
					time = resultSet.getString("test_time");
					applicantTestDetailDBObj.testTime = time.toString();
				}
				
				//String time=resultSet.getString("test_time");
				//if(time!=null)
					//applicantTestDetailDBObj.testTime  =time.substring(11,16);

				applicantTestDetailDBObj.presentStatus = resultSet.getString("present_status");
				applicantTestDetailDBObj.totalMarks = resultSet.getLong("total_marks");
				applicantTestDetailDBObj.marksGained  = resultSet.getLong("marks_gained");
				applicantTestDetailDBObj.testStatus = resultSet.getString("test_status");
				applicantTestDetailDBObj.passFail = resultSet.getString("pass_fail");
				applicantTestDetailDBObj.nextRound  = resultSet.getString("next_round");
				System.out.println("fffff==="+resultSet.getString("test_id"));
			}
			else{
				initializeHrmsApplicantTestDetailHelper(applicantTestDetailDBObj);
			}
			System.out.println("fffff====="+applicantTestDetailDBObj.testId);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return applicantTestDetailDBObj;
	}
	
	// CODE FOR  selectApplicantTestDetailByCriteria
	public ArrayList selectApplicantTestDetailByCriteria(String inCriteria){
		ArrayList applicantList = new ArrayList();
		String date = null;
		try{
			
			Connection connection= DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			String sqlString =	"select * from APPLICANT_TEST_DETAIL";
			if( inCriteria != null && inCriteria.length() > 0 ){
				sqlString = sqlString +" "+inCriteria+"" ;
			}
			System.out.println("Criteria===== "+inCriteria+" and query="+sqlString);
			ResultSet resultSet  = null;
			resultSet  = statement.executeQuery(sqlString);
			while( resultSet.next()){
				HrmsApplicantTestDetail  applicantTestDetailDBObj = new HrmsApplicantTestDetail();
				applicantTestDetailDBObj.testId =  resultSet.getString("test_id");
				applicantTestDetailDBObj.testName = resultSet.getString("test_name");
				applicantTestDetailDBObj.applicantId = resultSet.getString("applicant_id");
				applicantTestDetailDBObj.applicantName = resultSet.getString("applicant_name");
				
//				date=resultSet.getDate("test_date");
//				if(date!=null)
//				applicantTestDetailDBObj.testDate = date.toString();
				if(resultSet.getString("TEST_DATE") !=null )
				{
					date = resultSet.getString("TEST_DATE");
					applicantTestDetailDBObj.testDate = date.toString();
				}


				String time=resultSet.getString("test_time");
				//if(time!=null)
					//applicantTestDetailDBObj.testTime  = time.substring(11,16);
				if(resultSet.getString("test_time") !=null )  {
					time = resultSet.getString("test_time");
					applicantTestDetailDBObj.testTime = time.toString();
				}
				
				applicantTestDetailDBObj.testDate = resultSet.getString("test_date");
				applicantTestDetailDBObj.testTime = resultSet.getString("test_time");
				
				applicantTestDetailDBObj.presentStatus = resultSet.getString("present_status");
				applicantTestDetailDBObj.totalMarks = resultSet.getLong("total_marks");
				applicantTestDetailDBObj.marksGained  = resultSet.getLong("marks_gained");
				applicantTestDetailDBObj.testStatus = resultSet.getString("test_status");
				applicantTestDetailDBObj.passFail = resultSet.getString("pass_fail");
				applicantTestDetailDBObj.nextRound  = resultSet.getString("next_round");
				applicantList.add(applicantTestDetailDBObj);
			}
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return applicantList;
	}
	public int updateApplicantTestDetail(HrmsApplicantTestDetail inApplicantTestDetailHelper){
		int recupd = 0; 
		String query = "";
		//query = query +"update APPLICANT_TEST_DETAIL set test_date=to_date('"+inApplicantTestDetailHelper.testDate+"','yyyy-mm-dd')  "; 
		query = query +"update APPLICANT_TEST_DETAIL set test_date='"+inApplicantTestDetailHelper.testDate+"' "; 

		query = query +" , applicant_name='"+inApplicantTestDetailHelper.applicantName+"' ";

		query = query +" , test_time='"+inApplicantTestDetailHelper.testTime+"' ";
		//query = query +" , test_time=date('"+inApplicantTestDetailHelper.testTime+"', 'HH24:MI') ";
		
		
		if( inApplicantTestDetailHelper.presentStatus != null )
			query = query +" , present_status='"+inApplicantTestDetailHelper.presentStatus+"' ";
		else 
			query = query +" , present_status='' ";
		query = query +" , total_marks="+inApplicantTestDetailHelper.totalMarks+" ";
		query = query +" , marks_gained ="+inApplicantTestDetailHelper.marksGained+" ";
		query = query +" , test_status='"+inApplicantTestDetailHelper.testStatus+"' ";
		if (inApplicantTestDetailHelper.passFail != null)
			query = query +" , pass_fail='"+inApplicantTestDetailHelper.passFail+"' ";
		else
			query = query +" , pass_fail='' ";
		if(inApplicantTestDetailHelper.nextRound != null)
		    query = query +" , next_round='"+inApplicantTestDetailHelper.nextRound+"' ";
		else
			query = query +" , next_round='' ";
		query = query +" where test_id='"+inApplicantTestDetailHelper.testId+"' ";
		query = query +" and test_name='"+inApplicantTestDetailHelper.testName+"' ";
		query = query +" and applicant_id='"+inApplicantTestDetailHelper.applicantId+"' ";
		System.out.println("sqlString===:"+query);
		try{
		
			Connection connection= DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			recupd  = statement.executeUpdate(query);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	
	//populate applicant test detail 
	public HrmsApplicantTestDetail populateApplicantTestDetailDBObjFromReq(HttpServletRequest inReq){
		HrmsApplicantTestDetail  applicantTestDetailDBObj = new HrmsApplicantTestDetail();
		applicantTestDetailDBObj.testId   =  (String)inReq.getParameter("testId");
		applicantTestDetailDBObj.testName = (String)inReq.getParameter("testName"); 
		applicantTestDetailDBObj.applicantId = (String)inReq.getParameter("applicantId");
		applicantTestDetailDBObj.applicantName = (String)inReq.getParameter("applicantName");

		applicantTestDetailDBObj.testDate  = (String)inReq.getParameter("testDate");
		applicantTestDetailDBObj.testTime   = (String)inReq.getParameter("testTime");
		
		applicantTestDetailDBObj.presentStatus = (String)inReq.getParameter("presentStatus");
		if(inReq.getParameter("totalMarks") == null){
			applicantTestDetailDBObj.totalMarks = 0;
		}
		else
			applicantTestDetailDBObj.totalMarks = Long.parseLong((String)inReq.getParameter("totalMarks"));
		if(inReq.getParameter("marksGained") == null){
			applicantTestDetailDBObj.marksGained = 0;
		}
		else
			applicantTestDetailDBObj.marksGained = Long.parseLong((String)inReq.getParameter("marksGained"));
		applicantTestDetailDBObj.testStatus = (String)inReq.getParameter("testStatus");
		applicantTestDetailDBObj.passFail = (String)inReq.getParameter("passFail");
		applicantTestDetailDBObj.nextRound  = (String)inReq.getParameter("nextRound");
		return applicantTestDetailDBObj;
	}
	
	public int insertApplicantTestDetail(HrmsApplicantTestDetail inApplicantTestDetailHelper){
		int recupd = 0; 
		System.out.println("Start method of  insertApplicantTestDetail in HrmsApplicantTestDetailHelper");
		String query = "";
		query = query +"insert into APPLICANT_TEST_DETAIL  values ( ";
		query = query +" '"+inApplicantTestDetailHelper.testId+"'  ";
		query = query +" , '"+inApplicantTestDetailHelper.testName+"'  ";
		query = query +" , '"+inApplicantTestDetailHelper.applicantId+"' ";
		query = query +" , '"+inApplicantTestDetailHelper.applicantName+"' ";

		//query = query +" , to_date('"+inApplicantTestDetailHelper.testDate+"','yyyy-mm-dd')  ";
		if(inApplicantTestDetailHelper.testDate !=null && inApplicantTestDetailHelper.testDate.trim().length()>4)
		{
			query = query + ",'" + inApplicantTestDetailHelper.testDate  + "'";
		} else
		{
			query = query + ", '1900-01-01'  ";
		}
		
		//query = query +" , date('"+inApplicantTestDetailHelper.testTime+"','HH24:MI') ";
		System.out
				.println("HrmsApplicantTestDetailHelper.inApplicantTestDetailHelper()" +inApplicantTestDetailHelper.testTime.trim().length());
		if(inApplicantTestDetailHelper.testTime !=null && inApplicantTestDetailHelper.testTime.trim().length()>2) {
			query = query + ",'" + inApplicantTestDetailHelper.testTime  + "'";
		} else {
			query = query + ", '12:00-PM'  ";
		}
		
		query = query +" , '"+inApplicantTestDetailHelper.presentStatus+"' ";
		query = query +" , "+inApplicantTestDetailHelper.totalMarks+" ";
		query = query +" , "+inApplicantTestDetailHelper.marksGained+" ";
		query = query +" , '"+inApplicantTestDetailHelper.testStatus+"' ";
		query = query +" , '"+inApplicantTestDetailHelper.passFail+"' ";
		query = query +" , '"+inApplicantTestDetailHelper.nextRound +"' ";
		query = query + " )";
		System.out.println("sqlString===:"+query);
		try{
			
			Connection connection= DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement pdst=connection.prepareStatement("insert into APPLICANT_TEST_DETAIL values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pdst.setString(1, inApplicantTestDetailHelper.testId);
			pdst.setString(2, inApplicantTestDetailHelper.testName);
			pdst.setString(3, inApplicantTestDetailHelper.applicantId);
			pdst.setString(4, inApplicantTestDetailHelper.applicantName);
			pdst.setString(5, inApplicantTestDetailHelper.testDate);
			pdst.setString(6, inApplicantTestDetailHelper.testTime);
			pdst.setString(7, inApplicantTestDetailHelper.presentStatus);
			pdst.setDouble(8, inApplicantTestDetailHelper.totalMarks);
			pdst.setDouble(9, inApplicantTestDetailHelper.marksGained);
			pdst.setString(10, inApplicantTestDetailHelper.testStatus);
			pdst.setString(11, inApplicantTestDetailHelper.passFail);
			pdst.setString(12, inApplicantTestDetailHelper.nextRound);
			recupd=pdst.executeUpdate();
			System.out.println("inserted into testDetail dataBase----"+recupd);
			//recupd  = statement.executeUpdate(query);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	
	public void deleteApplicant(String inApplicantId){
		try{

			Connection connection= DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			String query = "";
			query = query +"delete from APPLICANT_TEST_DETAIL "; 
			query = query +" where test_id='"+inApplicantId+"' ";
			System.out.println("sqlString===:"+query);
			statement.executeQuery(query);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
	}
}