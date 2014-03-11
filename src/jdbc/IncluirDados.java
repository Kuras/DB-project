package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncluirDados {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/hibernate";
		// String sql =
		// "insert into pessoa values(3,'Caio','M','caio@gmial.com')";
		//
		// try (Connection con = DriverManager.getConnection(url, "baraka",
		// "mypass");
		// PreparedStatement stm = con.prepareStatement(sql)
//				){
		//
		// stm.executeUpdate();
		//
		// }

		// String sql = "insert into pessoa values(?,?,?,?)";
		// String[] pessoas = {"Sandra", "Betriz", "Juliana", "Fatima",
		// "Veranda", "Tereza", "Lelia"};
		// try (Connection con = DriverManager.getConnection(url, "baraka",
		// "mypass");
		// PreparedStatement stm = con.prepareStatement(sql)) {
		//
		// for (int i = 0; i < pessoas.length; i++) {
		// stm.setInt(1, i+4);
		// stm.setString(2, pessoas[i]);
		// stm.setString(3, "F");
		// stm.setString(4, pessoas[i].toLowerCase()+"@gmail.com");
		// stm.addBatch();
		// }
		// stm.executeBatch();
		//
		// }

		String sql = "insert into pessoa values(?,?,?,?)";
		String[] pessoas = { "Sandra", "Betriz", "Juliana", "Fatima",
				"Veranda", "Tereza", "Lelia" };
		try (Connection con = DriverManager.getConnection(url, "baraka",
				"mypass")) {

			try (PreparedStatement stm = con.prepareStatement(sql)) {

				for (int i = 0; i < pessoas.length; i++) {
					stm.setInt(1, i + 4);
					stm.setString(2, pessoas[i]);
					stm.setString(3, "F");
					stm.setString(4, pessoas[i].toLowerCase() + "@gmail.com");
					stm.addBatch();
				}
				stm.executeBatch();
			} catch (SQLException e) {
			}

			sql = "select nome, email from pessoa";
			try (PreparedStatement stm2 = con.prepareStatement(sql);
					ResultSet rs = stm2.executeQuery()) {
				
				while (rs.next()) {
					 String nome = rs.getString(1);
					 String email = rs.getString(2);
					 System.out.println("Nome e :" + nome + "\temail :" + email);
				}
			}

		}

	}

}
