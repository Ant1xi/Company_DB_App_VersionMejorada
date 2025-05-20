package com.daw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClaseConectoraBBDD {
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static final String User = "C##COMPANY2";
	private static final String Password = "company2";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, User, Password);
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
