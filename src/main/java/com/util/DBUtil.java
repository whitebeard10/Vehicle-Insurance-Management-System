package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class DBUtil {
	private static String DRIVERNAME= "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/StarProtectDB";
    private static final String USER = "root";
    private static final String PASSWORD = "";
	
	
	public static Connection createConnection() {
		Connection connection = null;
		
		try {
			Class.forName(DRIVERNAME);
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//Avinash123
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		//System.out.println("Connection ki value h : "+connection);
		return connection;
	}	
	public static void closeAllConnection(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}