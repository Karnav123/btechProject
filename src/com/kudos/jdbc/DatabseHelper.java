package com.kudos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabseHelper {

	static {
		System.out
				.println("DatabseHelper.static block Loading com.mysql.jdbc.Driver");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		Connection connection = null;
		try {
			String dbUrl = "jdbc:mysql://localhost:3306/hrm";
			connection = DriverManager.getConnection(dbUrl, "root", "kumar1993");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return connection;

	}

}
