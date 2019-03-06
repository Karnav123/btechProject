package com.kudos.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import com.kudos.jdbc.DatabseHelper;

public class UserLoginHelper{
	public String dbUser;
	public String dBPswd;
	public String dBUrl ;
	
	public UserLoginHelper(){ 
		
	}
	
	public UserLoginHelper(String indbUser, String indBPswd, String indBUrl ){
		dbUser = indbUser ;
		dBPswd = indBPswd;
		dBUrl  = indBUrl;
	}
	public void initializeUserLoginDBObj(UserLogin inUserLoginDBObj ){
		inUserLoginDBObj.userId =  "";
		inUserLoginDBObj.userName = "";
		inUserLoginDBObj.oldPassword = "";
		inUserLoginDBObj.newPassword = "";
	  	inUserLoginDBObj.passwordEffDate = "";
	  	inUserLoginDBObj.passwordExpDate = "";
	}
	public UserLogin getRecordByPrimaryKey(String inUserId, String inUserName, String inUserPswd){
		UserLogin  userLoginDBObj = new UserLogin();
		try{
			System.out.println("dbUser=="+dbUser+",dBPswd=="+dBPswd+",dBUrl=="+dBUrl);
			
			
			Connection conn = DatabseHelper.getConnection();
			
			
			//DriverManager.getConnection(dBUrl,dbUser,dBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from HRMS_USER_LOGIN  ";
			lSqlString = lSqlString + "where user_Id='"+inUserId+"' ";
			lSqlString = lSqlString + "and user_Name='"+inUserName+"' ";
			lSqlString = lSqlString + "and new_Pswd='"+inUserPswd+"' ";
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			System.out.println("lSqlString====trtrt==within getRecordByPrimaryKey== "+lSqlString);
			if( rs.next()){
				System.out.println("fffff==="+rs.getString("user_Id"));
				userLoginDBObj.userId =  rs.getString("user_Id");
				userLoginDBObj.userName = rs.getString("user_Name");
				userLoginDBObj.oldPassword = rs.getString("old_Pswd");
				userLoginDBObj.newPassword = rs.getString("new_Pswd");
				userLoginDBObj.passwordEffDate = rs.getString("pswd_Eff_Date");
				userLoginDBObj.passwordExpDate = rs.getString("pswd_Exp_Date");
				System.out.println("result set==="+rs.getString("user_Id"));
			}
			else {
				System.out.println(".............else part");
				
				initializeUserLoginDBObj(userLoginDBObj);
			}
				System.out.println("else rskk====="+userLoginDBObj.userId);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return userLoginDBObj;
	}
	
	public int updateUserLoginByPrimaryKey(UserLogin inUserLoginDBObj){
		int recupd = 0; 
		String lQuery = "";
		lQuery = lQuery +"update HRMS_USER_LOGIN set old_pswd='"+inUserLoginDBObj.oldPassword+"'  "; 
		lQuery = lQuery +" , new_pswd='"+inUserLoginDBObj.newPassword+"' ";
		lQuery = lQuery + "where user_Id='"+inUserLoginDBObj.userId+"' ";
		lQuery = lQuery + "and user_Name='"+inUserLoginDBObj.userName+"' ";
		lQuery = lQuery + "and new_Pswd='"+inUserLoginDBObj.oldPassword+"' "; 
		System.out.println("lSqlString===:"+lQuery);
		try{
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Connection conn= DriverManager.getConnection(dBUrl,dbUser,dBPswd);
			
			Connection conn = DatabseHelper.getConnection();
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	
	public UserLogin populateUserLoginDBObjFromReq(HttpServletRequest inReq){
		UserLogin  userLoginDBObj = new UserLogin();
		userLoginDBObj.userId   =  (String)inReq.getParameter("userId");
		userLoginDBObj.userName = (String)inReq.getParameter("userName"); 
		userLoginDBObj.oldPassword  = (String)inReq.getParameter("oldPassword");
     	userLoginDBObj.newPassword  = (String)inReq.getParameter("newPassword");
     	userLoginDBObj.passwordEffDate = (String)inReq.getParameter("passwordEffDate");
	    userLoginDBObj.passwordExpDate = (String)inReq.getParameter("passwordExpDate");
		return userLoginDBObj;
	}
}
