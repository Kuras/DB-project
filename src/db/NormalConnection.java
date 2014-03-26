package db;

import java.sql.SQLException;

public class NormalConnection {

	public static void main(String[] args) {
		DbConnect dbConnect = new DbConnect();
		
		dbConnect.getData();
		
		try {
			
			dbConnect.close();
			
		} catch (SQLException e) {
			System.out.println("Problem with close DB");;
		}

	}

}
