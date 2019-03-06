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
import com.kudos.util.DateUtil;





public class HrmsApplicantHelper {
	public String dBUser ;
	public String dBPswd ;
	public String dBUrl ;
	
	public HrmsApplicantHelper () {
		
	}
	
	public HrmsApplicantHelper (String inDBUser, String inDBPswd, String inDBUrl) {
		dBUser = inDBUser;
		dBPswd = inDBPswd;
		dBUrl = inDBUrl;
	}
	
	public void initializeHrmsApplicantHelper(HrmsApplicant inHrmsApplicantHelper) {
		System.out.println("HrmsApplicantHelper.initializeHrmsApplicantHelper()");
		System.out.println("HrmsApplicantHelper*************()");
		inHrmsApplicantHelper.applicantId = "";
		inHrmsApplicantHelper.applicantName = "";
		inHrmsApplicantHelper.address1 = "";
		inHrmsApplicantHelper.address2 = "";
		inHrmsApplicantHelper.currentLocation = "";
		inHrmsApplicantHelper.email = "";
		inHrmsApplicantHelper.phone = 0;
		inHrmsApplicantHelper.mobile = 0;
		inHrmsApplicantHelper.dateOfBirth = null;
		inHrmsApplicantHelper.gender = "";
		inHrmsApplicantHelper.nationality = "";
		inHrmsApplicantHelper.workExp = 0;
		inHrmsApplicantHelper.skill = "";
		inHrmsApplicantHelper.industry = "";
		inHrmsApplicantHelper.category = "";
		inHrmsApplicantHelper.roles = "";
		inHrmsApplicantHelper.currentEmployer = "";
		inHrmsApplicantHelper.currentSalary = 0;
		inHrmsApplicantHelper.highestDegree = "";
		inHrmsApplicantHelper.secondHighestDegree = "";
		inHrmsApplicantHelper.domain = "";
	}
	
	public HrmsApplicant getRecordByPrimaryKey(String inApplicantId) {
        System.out.println("HrmsApplicantHelper.getRecordByPrimaryKey()");
		HrmsApplicant applicantDBObj = new HrmsApplicant();
		Date date = null;
		try {
			System.out.println("dBUser == " + dBUser + ",dBPswd ==" + dBPswd + ",dBUrl" + dBUrl);
//			DriverManager.registerDriver(null);
//			Connection connection = DriverManager.getConnection(dBUrl, dBUser, dBPswd);
			
			Connection connection = DatabseHelper.getConnection();			
			Statement statement = connection.createStatement();
			
			String sqlString = "select * from HRMS_APPLICANT";
			sqlString = sqlString +" "+"where applicant_id = '"+inApplicantId+"' ";
			ResultSet resultSet =null;
			resultSet = statement.executeQuery(sqlString);
			System.out.println("sqlString ==== trtrt == within getRecordByPrimaryKey ==" + sqlString );
			if (resultSet.next()) {
				System.out.println("ffff == " + resultSet.getString("applicant_id") );
				applicantDBObj.applicantId = resultSet.getString("applicant_id");
				applicantDBObj.applicantName = resultSet.getString("applicant_name");
				applicantDBObj.address1 = resultSet.getString("address_1");
				applicantDBObj.address2 = resultSet.getString("address_2");
				//applicantDBObj.currentLocation = resultSet.getString("current_location");
				applicantDBObj.email = resultSet.getString("email");
				applicantDBObj.phone = resultSet.getLong("phone");
				applicantDBObj.mobile = resultSet.getLong("mobile");
				
//				date = resultSet.getDate("dateOfBirth");
//				applicantDBObj.dateOfBirth = date.toString();
				//applicantDBObj.dateOfBirth = resultSet.getString("dob");
				if(resultSet.getString("DOB") !=null )
				{
					date = resultSet.getDate("DOB");
					applicantDBObj.dateOfBirth = date;
				}
				
				applicantDBObj.gender = resultSet.getString("gender");
				applicantDBObj.nationality = resultSet.getString("nationality");
				applicantDBObj.workExp = resultSet.getLong("work_exp");
				applicantDBObj.skill = resultSet.getString("skill");
				applicantDBObj.industry = resultSet.getString("industry");
				applicantDBObj.category = resultSet.getString("category");
				applicantDBObj.roles = resultSet.getString("roles");
				applicantDBObj.currentEmployer = resultSet.getString("current_employer");
				applicantDBObj.currentSalary = resultSet.getDouble("current_sal");
				applicantDBObj.highestDegree = resultSet.getString("highest_degree");
				applicantDBObj.secondHighestDegree = resultSet.getString("second_highest_degree");
				applicantDBObj.domain = resultSet.getString("domain");
				applicantDBObj.currentLocation = resultSet.getString("current_location");
				System.out.println("fffff* === " + resultSet.getString("applicant_id") );
				System.out.println("applicantDBObj === "+applicantDBObj.applicantId);
			} else {
				initializeHrmsApplicantHelper (applicantDBObj);
			}
			System.out.println("ffff*** == " + applicantDBObj.applicantId );
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return applicantDBObj;
	}
	
	public ArrayList selectApplicantByCriteria(String inCriteria) {
		ArrayList applicantList = new  ArrayList();
		Date date;
		try {
//			DriverManager.registerDriver(null);
//			Connection connection = DriverManager.getConnection(dBUrl, dBUser, dBPswd);
			System.out.println("HrmsApplicantHelper.selectApplicantByCriteria()");
			Connection connection = DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			String sqlString = "select * from HRMS_APPLICANT";
			if(inCriteria != null && inCriteria.length() > 0) {
				sqlString = sqlString + " " + inCriteria + "";
			}
			System.out.println("Criteria ==== " + inCriteria +" and query = " + sqlString );
			ResultSet resultSet =null;
			resultSet = statement.executeQuery(sqlString);
			while(resultSet.next()) {
				
				HrmsApplicant applicantDBObj = new HrmsApplicant();
				applicantDBObj.applicantId = resultSet.getString("applicant_id");
				applicantDBObj.applicantName = resultSet.getString("applicant_name");
				applicantDBObj.address1 = resultSet.getString("address_1");
				applicantDBObj.address2 = resultSet.getString("address_2");
				
				//applicantDBObj.currentLocation = resultSet.getString("current_location");
				
				applicantDBObj.email = resultSet.getString("email");
				applicantDBObj.phone = resultSet.getLong("phone");
				applicantDBObj.mobile = resultSet.getLong("mobile");
				
//				date = resultSet.getDate("dateOfBirth");
//				applicantDBObj.dateOfBirth = date.toString();				
				applicantDBObj.dateOfBirth = resultSet.getDate("dob");
				
				applicantDBObj.gender = resultSet.getString("gender");
				applicantDBObj.nationality = resultSet.getString("nationality");
				applicantDBObj.workExp = resultSet.getLong("work_exp");
				applicantDBObj.skill = resultSet.getString("skill");
				applicantDBObj.industry = resultSet.getString("industry");
				applicantDBObj.category = resultSet.getString("category");
				applicantDBObj.roles = resultSet.getString("roles");
				applicantDBObj.currentEmployer = resultSet.getString("current_employer");
				applicantDBObj.currentSalary = resultSet.getDouble("current_sal");
				applicantDBObj.highestDegree = resultSet.getString("highest_degree");
				applicantDBObj.secondHighestDegree = resultSet.getString("second_highest_degree");
				applicantDBObj.domain = resultSet.getString("domain");
				applicantDBObj.currentLocation = resultSet.getString("current_location");
				System.out.println("HrmsApplicantHelper.selectApplicantByCriteria()   ApplicantId=="+resultSet.getString("applicant_id"));
				applicantList.add(applicantDBObj);
				
			}
		} catch(SQLException sqlEx) {
			System.out
					.println("HrmsApplicantHelper.selectApplicantByCriteria()");
			sqlEx.printStackTrace();
		}		
		return applicantList;
	}
	
	
	public int updateApplicant(HrmsApplicant inApplicantDBObj) {
		int recupd = 0;
		//Date date = new Date();
		String query = "";
		query = query + "update HRMS_APPLICANT set email ='" + inApplicantDBObj.email +"' ";
		query = query + ",applicant_name  ='" + inApplicantDBObj.applicantName +"' ";
		query = query + ",address_1  ='" + inApplicantDBObj.address1 +"' ";
		query = query + ",address_2  ='" + inApplicantDBObj.address2 +"' ";
		//query = query + ",currentLocation  ='" + inApplicantDBObj.currentLocation +"'";
		query = query + ",phone  =" + inApplicantDBObj.phone +" ";
		query = query + ",mobile  =" + inApplicantDBObj.mobile +" ";
		//query = query + ",dateOfBirth toDate('" + inApplicantDBObj.dateOfBirth +"','yyyy-mm-dd')";
		if(inApplicantDBObj.dateOfBirth !=null )
		{
			query = query + ",'" + inApplicantDBObj.dateOfBirth  + "'";
		} else
		{
			query = query + ", '1900-01-01'  ";
		}
		//query = query + ",dob='" + inApplicantDBObj.dateOfBirth +"' ";
		query = query + ",gender  ='" + inApplicantDBObj.gender +"' ";
		query = query + ",nationality  ='" + inApplicantDBObj.nationality +"' ";
		query = query + ",work_exp  =" + inApplicantDBObj.workExp +" ";
		query = query + ",skill  ='" + inApplicantDBObj.skill +"' ";
		query = query + ",industry  ='" + inApplicantDBObj.industry +"' ";
		query = query + ",category  ='" + inApplicantDBObj.category +"' ";
		query = query + ",roles  ='" + inApplicantDBObj.roles +"'";
		query = query + ",current_employer  ='" + inApplicantDBObj.currentEmployer +"' ";
		query = query + ",current_sal  =" + inApplicantDBObj.currentSalary +" ";
		query = query + ",highest_degree  ='" + inApplicantDBObj.highestDegree +"' ";
		query = query + ",second_highest_degree  ='" + inApplicantDBObj.secondHighestDegree +"' ";
		query = query + ",domain  ='" + inApplicantDBObj.domain +"' ";
		query = query + ",current Location = '" + inApplicantDBObj.currentLocation + "'";
		query = query +" where applicant_id='"+inApplicantDBObj.applicantId+"' ";

		System.out.println("sqlString ===: " + query );
		try {
			
			Connection connection = DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//date = format.parse(dateText.getText());
			//preparedStatement.setDate(2, (java.sql.Date) date);
			recupd = statement.executeUpdate(query);
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		
		return recupd;
	}
	
	public HrmsApplicant populateApplicantDBObjFromReq(HttpServletRequest inReq) {
		System.out.println("HrmsApplicantHelper.populateApplicantDBObjFromReq()");
		HrmsApplicant applicantDBObj = new HrmsApplicant();
		applicantDBObj.applicantId = (String)inReq.getParameter("applicantId");
		applicantDBObj.applicantName = (String)inReq.getParameter("applicantName");
		applicantDBObj.address1 = (String)inReq.getParameter("address1");
		applicantDBObj.address2 = (String)inReq.getParameter("address2");
		applicantDBObj.currentLocation = (String)inReq.getParameter("currentLocation");
		applicantDBObj.email = (String)inReq.getParameter("email");
		applicantDBObj.phone = Long.parseLong((String)inReq.getParameter("phone"));
		applicantDBObj.mobile = Long.parseLong((String)inReq.getParameter("mobile"));
		 java.util.Date d = null; 
		 if (inReq.getParameter("dateOfBirth") !=null)
		 {
			 d = DateUtil.getDate(inReq.getParameter("dateOfBirth"), DateUtil._YYYY_MM_DD);
		 }
		applicantDBObj.dateOfBirth = d;
		
		applicantDBObj.gender = (String)inReq.getParameter("gender");
		applicantDBObj.nationality = (String)inReq.getParameter("nationality");
		applicantDBObj.workExp = Double.parseDouble((String)inReq.getParameter("workExp"));
		applicantDBObj.skill = (String)inReq.getParameter("skill");
		applicantDBObj.industry = (String)inReq.getParameter("industry");
		applicantDBObj.category = (String)inReq.getParameter("category");
		applicantDBObj.roles = (String)inReq.getParameter("roles");
		applicantDBObj.currentEmployer = (String)inReq.getParameter("currentEmployer");
		applicantDBObj.currentSalary = Double.parseDouble((String)inReq.getParameter("currentSalary"));
		applicantDBObj.highestDegree = (String)inReq.getParameter("highestDegree");
		applicantDBObj.secondHighestDegree = (String)inReq.getParameter("secondHighestDegree");
		applicantDBObj.domain = (String)inReq.getParameter("domain");
		
		return applicantDBObj;
		
	}
	
	public int insertApplicant(HrmsApplicant inApplicantDBObj) {
		System.out.println("HrmsApplicantHelper.insertApplicant()");
		int recupd = 0;
		/*String query = "";
		query = query + "insert into HRMS_APPLICANT values(";
		query = query + " '" + inApplicantDBObj.applicantId + "'";
		query = query + ", '" + inApplicantDBObj.applicantName + "'";
		query = query + ", '" + inApplicantDBObj.address1 + "'";
		query = query + ", '" + inApplicantDBObj.address2 + "'";
		query = query + ", '" + inApplicantDBObj.email + "'";
		query = query + "," + inApplicantDBObj.phone + "";
		query = query + "," + inApplicantDBObj.mobile + "";
		//query = query + ", toDate(  '" + inApplicantDBObj.dateOfBirth +"','yyyy-mm-dd'";
		//query = query + ", '" + inApplicantDBObj.dateOfBirth +"' ";
		if(inApplicantDBObj.dateOfBirth !=null )
		{
			query = query + ",'" + inApplicantDBObj.dateOfBirth  + "'";
		} else
		{
			query = query + ", '1900-01-01'  ";
		}
		query = query + ", '" + inApplicantDBObj.gender + "'";
		query = query + ", '" + inApplicantDBObj.nationality + "'";
		query = query + "," + inApplicantDBObj.workExp + "";
		query = query + ", '" + inApplicantDBObj.skill + "'";
		query = query + ", '" + inApplicantDBObj.industry + "'";
		query = query + ", '" + inApplicantDBObj.category + "'";
		query = query + ", '" + inApplicantDBObj.roles + "'";
		query = query + ", '" + inApplicantDBObj.currentEmployer + "'";
		query = query + ", " + inApplicantDBObj.currentSalary + "";
		query = query + ", '" + inApplicantDBObj.highestDegree + "'";
		query = query + ", '" + inApplicantDBObj.secondHighestDegree + "'";
		query = query + ", '" + inApplicantDBObj.domain + "'";
		query = query + ", '" + inApplicantDBObj.currentLocation + "'";
		query = query +  ")";*/
		
		
		//System.out.println("sqlString ===: " + query );
		try {
//			DriverManager.registerDriver(null);
//			Connection connection = DriverManager.getConnection(dBUrl, dBUser, dBPswd);
			Connection connection = DatabseHelper.getConnection();
			//Statement statement = connection.createStatement();
			System.out.println("start insert.................");
			PreparedStatement pdst=connection.prepareStatement("insert into hrms_applicant values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pdst.setString(1, inApplicantDBObj.applicantId);
			pdst.setString(2, inApplicantDBObj.applicantName);
			pdst.setString(3, inApplicantDBObj.address1);
			System.out.println("address2--------"+inApplicantDBObj.address2);
			pdst.setString(4, inApplicantDBObj.address2);
			
			pdst.setString(5, inApplicantDBObj.email);
			pdst.setDouble(6, inApplicantDBObj.phone);
			pdst.setDouble(7, inApplicantDBObj.mobile);
			pdst.setString(8, DateUtil.getDateText(inApplicantDBObj.dateOfBirth, DateUtil._YYYY_MM_DD));
			pdst.setString(9, inApplicantDBObj.gender);
			pdst.setString(10, inApplicantDBObj.nationality);
			pdst.setDouble(11, inApplicantDBObj.workExp);
			pdst.setString(12, inApplicantDBObj.skill);
			pdst.setString(13, inApplicantDBObj.industry);
			pdst.setString(14, inApplicantDBObj.category);
			pdst.setString(15, inApplicantDBObj.roles);
			pdst.setString(16, inApplicantDBObj.currentEmployer);
			pdst.setDouble(17, inApplicantDBObj.currentSalary);
			pdst.setString(18, inApplicantDBObj.highestDegree);
			pdst.setString(19, inApplicantDBObj.secondHighestDegree);
			pdst.setString(20, inApplicantDBObj.domain);
			pdst.setString(21, inApplicantDBObj.currentLocation);
			recupd=pdst.executeUpdate();
			System.out.println("inserted data in database-"+recupd);
			//System.out.println("HrmsApplicantHelper.insertApplicant()"+query);
//			recupd = statement.executeUpdate("insert into hrms_applicant values('"+inApplicantDBObj.applicantId+"','"+inApplicantDBObj.applicantName+"','"+inApplicantDBObj.address1+"','"+inApplicantDBObj.address2+"','"+inApplicantDBObj.email+"','"+inApplicantDBObj.phone+"','"+inApplicantDBObj.mobile+"','"+inApplicantDBObj.dateOfBirth+"','"+inApplicantDBObj.gender+"','"+inApplicantDBObj.nationality+"','"+inApplicantDBObj.workExp+"','"+inApplicantDBObj.skill+"','"+inApplicantDBObj.industry+"','"+inApplicantDBObj.category+"','"+inApplicantDBObj.roles+"','"+inApplicantDBObj.currentEmployer+"','"+inApplicantDBObj.currentSalary+"','"+inApplicantDBObj.highestDegree+"','"+inApplicantDBObj.secondHighestDegree+"','"+inApplicantDBObj.domain+"','"+inApplicantDBObj.currentLocation+"','"+inApplicantDBObj+"')");
			System.out.println("recup"+recupd);
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return recupd;
	}
	
	public void deleteApplicant(String inApplicantId) {
		try {
//			
			Connection connection = DatabseHelper.getConnection();

			Statement statement = connection.createStatement();
			String query = "";
			query = query + "delete from HRMS_APPLICANT";
			query = query + "  where applicant_id = '" + inApplicantId + "' ";
			System.out.println("sqlString ===: " + query );
			statement.executeUpdate(query);
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

}
