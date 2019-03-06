package com.kudos.profile;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.kudos.jdbc.DatabseHelper;
import com.kudos.util.DateUtil;

public class HrmsEmployeeHelper {
	
	public HrmsEmployeeHelper () {
		
	}
	
//	public HrmsEmployeeHelper (String inDBUser, String inDBPswd, String inDBUrl) {
//		dBUser = inDBUser;
//		dBPswd = inDBPswd;
//		dBUrl = inDBUrl;
//	}
	
	public void initializeHrmsEmployeeHelper(HrmsEmployee inEmployeeDBObj) {
		System.out.println("HrmsEmployeeHelper.initializeHrmsEmployeeHelper()");
		inEmployeeDBObj.empId = "";
		inEmployeeDBObj.empFirstName = "";
		inEmployeeDBObj.empMiddleName = "";
		inEmployeeDBObj.empLastName = "";
		inEmployeeDBObj.orgId = "";
		inEmployeeDBObj.levelId = "";
		inEmployeeDBObj.deptId = "";
		inEmployeeDBObj.dateOfBirth = new Date();
		inEmployeeDBObj.dateOfJoin = new Date();
		inEmployeeDBObj.address1 = "";
		inEmployeeDBObj.address2 = "";
		inEmployeeDBObj.city = "";
		inEmployeeDBObj.state = "";
		inEmployeeDBObj.nationality = "";
	}

	public HrmsEmployee getRecordByPrimaryKey(String inEmpId, String inEmpFirstName) {
		System.out.println("HrmsEmployeeHelper.getRecordByPrimaryKey()");
		HrmsEmployee employeeDBObj = new HrmsEmployee();
		java.sql.Date date;
		try {
			//System.out.println("dBUser == " + dBUser + ",dBPswd ==" + dBPswd + ",dBUrl" + dBUrl);
//			DriverManager.registerDriver(null);
//			Connection connection = DriverManager.getConnection(dBUrl, dBUser, dBPswd);
			
			Connection connection = DatabseHelper.getConnection();
			//System.out.println("Connection" + connection );
			
			Statement statement = connection.createStatement();
			//System.out.println("Statement" + statement );
			String sqlString = "select * from HRMS_EMPLOYEE ";
			sqlString = sqlString + " WHERE EMP_ID = '" + inEmpId + "' ";
			if(inEmpFirstName != null && inEmpFirstName.length() > 0)
			{
				sqlString = sqlString + " and EMP_F_NAME ='" + inEmpFirstName + "' ";
			}
			ResultSet resultSet =null;
			System.out.println("sqlString ==== trtrt == within getRecordByPrimaryKey ==" + sqlString );
			resultSet = statement.executeQuery(sqlString);
			System.out.println("sqlString ==== trtrt == within getRecordByPrimaryKey ==" + sqlString );
			if (resultSet.next()) {
				System.out.println("ffff == " + resultSet.getString("emp_id") );
				employeeDBObj.empId = resultSet.getString("emp_id");
				employeeDBObj.empFirstName = resultSet.getString("emp_F_Name");
				employeeDBObj.empMiddleName = resultSet.getString("emp_M_Name");
				employeeDBObj.empLastName = resultSet.getString("emp_L_Name");
				employeeDBObj.orgId = resultSet.getString("org_Id");
				employeeDBObj.levelId = resultSet.getString("level_Id");
				employeeDBObj.deptId = resultSet.getString("dept_Id");
				//date=rs.getDate("agreement_date");
				
				if(resultSet.getDate("DOB") !=null )
				{
					date = resultSet.getDate("DOB");
					employeeDBObj.dateOfBirth = date;
				}
				
				
				
				if(resultSet.getDate("DOJOIN") !=null )
				{
					date = resultSet.getDate("DOJOIN");
					employeeDBObj.dateOfJoin = date;
				}
				
				
				employeeDBObj.address1 = resultSet.getString("address_1");
				employeeDBObj.address2 = resultSet.getString("address_2");
				employeeDBObj.city = resultSet.getString("city");
				employeeDBObj.state = resultSet.getString("state");
				employeeDBObj.nationality = resultSet.getString("nationality");
				System.out.println("ffff == " + resultSet.getString("emp_id") );
			} else {
				initializeHrmsEmployeeHelper (employeeDBObj);
			}
			System.out.println("HrmsEmployeeHelper.getRecordByPrimaryKey()employeeDBObj.dateOfJoin===>"+employeeDBObj.dateOfJoin+""+employeeDBObj.dateOfBirth);
			System.out.println("ffff == " + employeeDBObj.empId );
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return employeeDBObj;
	}
	
	public int updateEmployeeByPrimaryKey (HrmsEmployee inEmployeeDBObj) {
		System.out.println("HrmsEmployeeHelper.updateEmployeeByPrimaryKey()");
		int recupd = 0;
		String query = "";
		query =query + "update HRMS_EMPLOYEE set emp_M_Name ='"+inEmployeeDBObj.empMiddleName+"' ";
		query =query + " ,emp_L_Name ='"+inEmployeeDBObj.empLastName+"' ";
		query =query + " ,org_Id ='"+inEmployeeDBObj.orgId +"' ";	
		query =query + " ,level_Id ='"+inEmployeeDBObj.levelId+"' ";
		query =query + " ,dept_Id  ='"+inEmployeeDBObj.deptId+"' ";	
		String dateOfBirthDateStr = DateUtil.getDateText(inEmployeeDBObj.dateOfBirth, DateUtil._YYYY_MM_DD);
		query =query + " ,DOB = '"+dateOfBirthDateStr+"' ";	
		String dateOfJoinDateStr = DateUtil.getDateText(inEmployeeDBObj.dateOfJoin, DateUtil._YYYY_MM_DD);
		query =query + " ,DOJOIN = '"+dateOfJoinDateStr+"' ";	
		query =query + " ,address_1 ='"+inEmployeeDBObj.address1 +"' ";	
		query =query + " ,address_2 ='"+inEmployeeDBObj.address2 +"' ";	
		query =query + " ,city ='"+inEmployeeDBObj.city+"' ";	
		query =query + " ,state ='"+inEmployeeDBObj.state+"' ";	
		query =query + " ,nationality ='"+inEmployeeDBObj.nationality +"' ";	
		query =query + " where emp_Id ='"+inEmployeeDBObj.empId+"' ";	
		query =query + " and EMP_F_NAME ='"+inEmployeeDBObj.empFirstName+"' ";	
		System.out.println("sqlString ===: "+query );
		try {
			Connection connection = DatabseHelper.getConnection();
			Statement statement = connection.createStatement();
			
			System.out.println("HrmsEmployeeHelper.updateEmployeeByPrimaryKey() QUERY -->"+query);
			recupd = statement.executeUpdate(query);
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		
		return recupd;
	}
	
	public HrmsEmployee populateEmployeeDBObjFromReq(HttpServletRequest inReq) {
		
		System.out.println("HrmsEmployeeHelper.populateEmployeeDBObjFromReq()");
		HrmsEmployee employeeDBObj = new HrmsEmployee();
		
		employeeDBObj.empId = (String)inReq.getParameter("empId");
		employeeDBObj.empFirstName = (String)inReq.getParameter("empFirstName");
		employeeDBObj.empMiddleName = (String)inReq.getParameter("empMiddleName");
		employeeDBObj.empLastName = (String)inReq.getParameter("empLastName");
		employeeDBObj.orgId = (String)inReq.getParameter("orgId");
		employeeDBObj.levelId = (String)inReq.getParameter("levelId");
		employeeDBObj.deptId = (String)inReq.getParameter("deptId");
		String  dateOfBirthDateStr = (String)inReq.getParameter("dateOfBirth");
		 if(dateOfBirthDateStr !=null )
		 {
			 Date date = DateUtil.getDate(dateOfBirthDateStr, DateUtil._YYYY_MM_DD);
			 employeeDBObj.dateOfBirth = date;
		 }
		
		String  dateOfJoinDateStr = (String)inReq.getParameter("dateOfJoin");
		 if(dateOfJoinDateStr !=null )
		 {
			 Date date = DateUtil.getDate(dateOfJoinDateStr, DateUtil._YYYY_MM_DD);
			 employeeDBObj.dateOfJoin = date;
		 }
		
		employeeDBObj.address1 = (String)inReq.getParameter("address1");
		employeeDBObj.address2 = (String)inReq.getParameter("address2");
		employeeDBObj.city = (String)inReq.getParameter("city");
		employeeDBObj.state = (String)inReq.getParameter("state");
		employeeDBObj.nationality = (String)inReq.getParameter("nationality");
		
		
		return employeeDBObj;
	}
	
	public int insertEmployee(HrmsEmployee inEmployeeDBObj) {
		
		System.out.println("HrmsEmployeeHelper.insertEmployee()");
		
		int recupd = 0;
		String query = "";
		query = query + "INSERT INTO HRMS_EMPLOYEE( EMP_ID, EMP_F_NAME, EMP_M_NAME, EMP_L_NAME, ORG_ID, LEVEL_ID, DEPT_ID, DOB, DOJOIN, ADDRESS_1, ADDRESS_2, CITY, STATE, NATIONALITY) values(";
		query = query + " '" + inEmployeeDBObj.empId + "'";
		query = query + " , '" + inEmployeeDBObj.empFirstName + "'";
		query = query + " , '" + inEmployeeDBObj.empMiddleName + "'";
		query = query + " , '" + inEmployeeDBObj.empLastName + "'";
		query = query + " , '" + inEmployeeDBObj.orgId + "'";
		query = query + " , '" + inEmployeeDBObj.levelId + "'";
		query = query + " , '" + inEmployeeDBObj.deptId + "'";
		String dateOfBirthStr = DateUtil.getDateText(inEmployeeDBObj.dateOfBirth, DateUtil._YYYY_MM_DD);
		query = query +" , '"+dateOfBirthStr+"' ";
		String dateOfJoinStr = DateUtil.getDateText(inEmployeeDBObj.dateOfJoin, DateUtil._YYYY_MM_DD);
		query = query +" , '"+dateOfJoinStr+"' ";
		query = query + " , '" + inEmployeeDBObj.address1 + "'";
		query = query + " , '" + inEmployeeDBObj.address2 + "'";
		query = query + " , '" + inEmployeeDBObj.city + "'";
		query = query + " , '" + inEmployeeDBObj.state + "'";
		query = query + " , '" + inEmployeeDBObj.nationality + "'";
		query = query +  "); ";
		System.out.println("sqlString ===: " + query );
		try {
			Connection connection = DatabseHelper.getConnection();
			
			Statement statement = connection.createStatement();
			System.out.println("HrmsEmployeeHelper.insertEmployee() query "+ query);
			recupd = statement.executeUpdate(query);
		} catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		
		return recupd;
	}

}
