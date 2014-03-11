package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	
	private Connection con;
	private Statement st;
	private ResultSet resultSet;

	// connect to our MySql
	public DbConnect() {
		try {
			// load driver class - check if we have drivers/lib connection
			Class.forName("com.mysql.jdbc.Driver");
			
			// url!!!!! to database 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate", "baraka","mypass");
			
			st = con.createStatement();
			
			System.out.println("You huhhhh we connect to MySQQL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("not found connection to MySQL --- pass wrong url? Or there is no instant of MySQL\n" +e );
		}
	}

	public void getData() {
		try {

			String query = "select * from student_information";
			resultSet = st.executeQuery(query);
			
			System.out.println("Records from Database");
			while (resultSet.next()) {
				//every record
				String name = resultSet.getString("nAME");
				String age = resultSet.getString("rollNo");
				
				System.out.println("Name: " + name + "\tId: " + age );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() throws SQLException {
		con.close();
	}
	
	
}
