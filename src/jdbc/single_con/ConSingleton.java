package jdbc.single_con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConSingleton {
	
	private static final String URL = null;
	private static final String USER = null;
	private static final String PASSWORD = null;
	
	private static Connection con;
	
	public synchronized static Connection getInstance() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return con;
	}
	
	private ConSingleton() {
	}
	
}
