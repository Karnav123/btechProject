package com.kudos.payroll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.kudos.jdbc.DatabseHelper;
import com.kudos.util.DateUtil;

public class HrmsPayrollHelper{
	public String dBUser ;
	public String dBPswd ;
	public String dBUrl ;
	public HrmsPayrollHelper(){ }
	
	public HrmsPayrollHelper(String inDBUser, String inDBPswd, String inDBUrl ){
		dBUser = inDBUser;
		dBPswd = inDBPswd;
		dBUrl = inDBUrl;
	}
	
	
	    public void initializeEmployeeAgreement(EmployeeAgreement inEmployeeAgreement){
		inEmployeeAgreement.empId = "";        
		inEmployeeAgreement.empName = "";      
		inEmployeeAgreement.levelId = "";      
		inEmployeeAgreement.allowanceType ="" ;
		inEmployeeAgreement.allowanceName ="";
		inEmployeeAgreement.amount  = 0;          
		inEmployeeAgreement.taxable  ="";      
		inEmployeeAgreement.percentage =  0;  
		inEmployeeAgreement.agreementDate = new Date();
	    }
	public EmployeeAgreement getEmployeeAgreementRecord(String inEmpId, String inAllowanceName){
		EmployeeAgreement  employeeAgreement = new EmployeeAgreement();
		java.sql.Date date;
	try
	{
	//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
		Connection conn = DatabseHelper.getConnection();
	Statement stmt = conn.createStatement();
	String lSqlString =	"select * from EMPLOYEE_AGREEMENT  ";
	lSqlString = lSqlString + "where emp_id='"+inEmpId+"' ";
	lSqlString = lSqlString + "and allowance_name='"+inAllowanceName+"' ";
	ResultSet rs  = null;
	rs  = stmt.executeQuery(lSqlString);
			System.out.println("lSqlString====trtrt==within getRecordByPrimaryKey== "+lSqlString);
			if( rs.next()){
				
				System.out.println("fffff==="+rs.getString("emp_id"));
				employeeAgreement.empId   =  (String)rs.getString("emp_id");
				employeeAgreement.empName = (String)rs.getString("emp_name"); 
				employeeAgreement.levelId = (String)rs.getString("level_id");
				employeeAgreement.allowanceType = (String)rs.getString("allowance_type");
				employeeAgreement.allowanceName =    rs.getString("allowance_name");
				employeeAgreement.amount = rs.getDouble("amt");
				employeeAgreement.taxable = (String)rs.getString("taxable");
				employeeAgreement.percentage = rs.getDouble("percentage");
				date=rs.getDate("agreement_date");
				
				if(date!=null)
				//employeeAgreement.agreementDate = date.toString();
				employeeAgreement.agreementDate = date;
				System.out.println("fffff==="+rs.getString("emp_id"));
			
			}
			else{
				initializeEmployeeAgreement(employeeAgreement);
			}
			
			System.out.println("fffff====="+employeeAgreement.empId);
			if(rs != null)
				rs.close();
			if( conn != null)
				conn.close();
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return employeeAgreement;
	}
	
	
	public ArrayList selectEmployeeAgreementByCriteria(String inCriteria) {
		ArrayList EmployeeAgreementList = new ArrayList();
		java.sql.Date date;
    try{
	//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
	
    	Connection conn = DatabseHelper.getConnection();
    	Statement stmt = conn.createStatement();
	String lSqlString =	"select * from EMPLOYEE_AGREEMENT";
	if( inCriteria != null && inCriteria.length() > 0 ){
	lSqlString = lSqlString +" "+inCriteria+"" ;
	}
			System.out.println("Criteria===== "+inCriteria+" and query="+lSqlString);
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			while( rs.next()){
	       		EmployeeAgreement  employeeAgreement = new EmployeeAgreement();
				employeeAgreement.empId   =  (String)rs.getString("emp_id");
				employeeAgreement.empName = (String)rs.getString("emp_name"); 
				employeeAgreement.levelId = (String)rs.getString("level_id");
				employeeAgreement.allowanceType = (String)rs.getString("allowance_type");
				employeeAgreement.allowanceName =    rs.getString("allowance_name");
				employeeAgreement.amount = rs.getDouble("amt");
				employeeAgreement.taxable = (String)rs.getString("taxable");
				employeeAgreement.percentage = rs.getDouble("percentage");
				date=rs.getDate("agreement_date");
			
				if(date!=null)
				employeeAgreement.agreementDate = date;
				EmployeeAgreementList.add(employeeAgreement);
			}
			
			if(rs != null)
				rs.close();
			if( conn != null)
				conn.close();
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return EmployeeAgreementList;
	}

	
	
	public int updateEmployeeAgreementByPrimaryKey(EmployeeAgreement inEmployeeAgreement){
		int recupd = 0; 
		String lQuery = "";
			lQuery = lQuery +"update EMPLOYEE_AGREEMENT set emp_name='"+inEmployeeAgreement.empName+"'  "; 
			lQuery = lQuery +" , level_id='"+inEmployeeAgreement.levelId+"' ";
			lQuery = lQuery +" , allowance_type='"+inEmployeeAgreement.allowanceType+"' ";
			lQuery = lQuery +" , amt="+inEmployeeAgreement.amount+" ";
			lQuery = lQuery +" , taxable='"+inEmployeeAgreement.taxable+"' ";
			lQuery = lQuery +" , percentage="+inEmployeeAgreement.percentage+" ";
			//lQuery = lQuery +" , agreementDate=to_date('"+inEmployeeAgreement.agreementDate+"','yyyy-mm-dd') ";
			
			String agreementDateStr = DateUtil.getDateText(inEmployeeAgreement.agreementDate, DateUtil._YYYY_MM_DD);
			lQuery = lQuery +" , agreement_date='"+agreementDateStr+"' ";
			
			
			
			//lQuery = lQuery +" , agreement_date=str_to_date('"+inEmployeeAgreement.agreementDate+"','%m/%d/%y') ";
			lQuery = lQuery + "where emp_id='"+inEmployeeAgreement.empId+"' ";
			lQuery = lQuery + "and allowance_name='"+inEmployeeAgreement.allowanceName+"' ";
		System.out.println("lSqlString===:"+lQuery);
		try{
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
			if( conn != null)
				conn.close();
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	
	
	public EmployeeAgreement populateEmployeeAgreementFromReq(HttpServletRequest inReq){
		
		EmployeeAgreement  employeeAgreement = new EmployeeAgreement();
		employeeAgreement.empId   =  (String)inReq.getParameter("empId");
		employeeAgreement.empName = (String)inReq.getParameter("empName"); 
		employeeAgreement.levelId = (String)inReq.getParameter("levelId");
		employeeAgreement.allowanceType = (String)inReq.getParameter("allowanceType");
		employeeAgreement.allowanceName =    inReq.getParameter("allowanceName");
		if( (String)inReq.getParameter("amount") != null && ((String)inReq.getParameter("amount")).length() > 0 )
			employeeAgreement.amount = Double.parseDouble((String)inReq.getParameter("amount")) ;
		else
			employeeAgreement.amount = 0;
		employeeAgreement.taxable = (String)inReq.getParameter("taxable");
		if( (String)inReq.getParameter("percentage") != null && ((String)inReq.getParameter("percentage")).length() > 0)
			employeeAgreement.percentage = Double.parseDouble((String)inReq.getParameter("percentage"));
		else
			employeeAgreement.percentage = 0;
		
		String  agreementDateStr = (String)inReq.getParameter("agreementDate");
		 
		 if(agreementDateStr !=null )
		 {
			 Date date = DateUtil.getDate(agreementDateStr, DateUtil._YYYY_MM_DD);
			 employeeAgreement.agreementDate = date;
		 }
		 
		return employeeAgreement;
	}
	
	
	public int insertEmployeeAgreement(EmployeeAgreement inEmployeeAgreement){
		int recupd = 0; 
		String lQuery = "";
				lQuery = lQuery +"insert into EMPLOYEE_AGREEMENT  values ( ";
				lQuery = lQuery +" '"+inEmployeeAgreement.empId+"'  ";
				lQuery = lQuery +" , '"+inEmployeeAgreement.empName+"'  ";
				lQuery = lQuery +" , '"+inEmployeeAgreement.levelId+"' ";
				lQuery = lQuery +" , '"+inEmployeeAgreement.allowanceType+"' ";
				lQuery = lQuery +" , '"+inEmployeeAgreement.allowanceName+"' ";
				lQuery = lQuery +" , "+inEmployeeAgreement.amount+" ";
				lQuery = lQuery +" , '"+inEmployeeAgreement.taxable+"' ";
				lQuery = lQuery +" , "+inEmployeeAgreement.percentage+" ";
				//lQuery = lQuery +" , Date('"+inEmployeeAgreement.agreementDate+"') ";
				String agreementDateStr = DateUtil.getDateText(inEmployeeAgreement.agreementDate, DateUtil._YYYY_MM_DD);
				lQuery = lQuery +" , '"+agreementDateStr+"' ";
				lQuery = lQuery + " )";
		System.out.println("lSqlString===:"+lQuery);
		try{
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
			if( conn != null)
				conn.close();
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	
	
	
	
	
	
	public int insertEmployeeSalary(EmployeeSalary inEmployeeSalary){
	 
		int recupd = 0; 
		String lQuery = "";
	        	lQuery = lQuery +"insert into EMP_SAL  values ( ";
		        lQuery = lQuery +" '"+inEmployeeSalary.empId+"'  ";
		        lQuery = lQuery +" , "+inEmployeeSalary.year+"  ";
		        lQuery = lQuery +" , "+inEmployeeSalary.month+" ";
		        lQuery = lQuery +" , '"+inEmployeeSalary.allowanceType+"' ";
		        lQuery = lQuery +" , '"+inEmployeeSalary.allowanceName+"' ";
		        lQuery = lQuery +" , "+inEmployeeSalary.amount+" ";
		        lQuery = lQuery +" , '"+inEmployeeSalary.taxable+"' ";
		        lQuery = lQuery +" , "+inEmployeeSalary.percentage+" ";
		        lQuery = lQuery + " )";
		System.out.println("lSqlString===:"+lQuery);
		try{
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Connection conn = DatabseHelper.getConnection();
			
			Statement stmt = conn.createStatement();
		recupd  = stmt.executeUpdate(lQuery);
			if( conn != null)
				conn.close();
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	public ArrayList selectEmployeeSalaryByCriteria(String inCriteria){
		ArrayList EmployeeSalaryList = new ArrayList();
	try{
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
		Connection conn = DatabseHelper.getConnection();	
		
		Statement stmt = conn.createStatement();
			String lSqlString =	"select * from EMP_SAL";
			if( inCriteria != null && inCriteria.length() > 0 ){
				lSqlString = lSqlString +" "+inCriteria+"" ;
	}
			System.out.println("Criteria===== "+inCriteria+" and query="+lSqlString);
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			while( rs.next()){
				EmployeeSalary  empSal = new EmployeeSalary();
				empSal.empId   =  (String)rs.getString("emp_id");
				empSal.year = rs.getInt("year"); 
				empSal.month = rs.getInt("month");
				empSal.allowanceType = (String)rs.getString("allowance_type");
				empSal.allowanceName =    rs.getString("allowance_name");
				empSal.amount = rs.getDouble("amt");
				empSal.taxable = (String)rs.getString("taxable");
				empSal.percentage = rs.getDouble("percentage");
				EmployeeSalaryList.add(empSal);
			}
			if(rs != null)
				rs.close();
			if( conn != null)
				conn.close();
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return EmployeeSalaryList;
	}
	
	public void deleteEmployeeAgreement(String inEmpId , String inAllowanceName){
	try{
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
		Connection conn = DatabseHelper.getConnection();	
		
		Statement stmt = conn.createStatement();
			String lQuery = "";
			lQuery = lQuery +"delete from EMPLOYEE_AGREEMENT "; 
			lQuery = lQuery +" where emp_id='"+inEmpId+"' and allowance_name='"+inAllowanceName+"' ";
			System.out.println("lSqlString===:"+lQuery);
			stmt.executeUpdate(lQuery);
			if( conn != null)
				conn.close();
	}
	catch(SQLException  ex){
			ex.printStackTrace();
		}
	}
}

