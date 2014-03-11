package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoBanco {

	private static final String USER = "baraka";
	private static final String PASS = "mypass";

	public static void main(String[] args) throws SQLException {
		// protokol komunikacji z BD, podprotokol komunikacji
		String sql = "select codigo, nome, sexo, email from pessoa";
		String url = "jdbc:mysql://localhost:3306/hibernate";
		
		
		try (
			Connection con = DriverManager.getConnection(url, USER, PASS);
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery()
					){

			System.err.println("Toda elemento de banco de dado hibernete");
			while (rs.next()) {
				String nome = rs.getString("nome");
				String codigo = rs.getString("codigo");
				String sexo = rs.getString("sexo");
				String email = rs.getString("email");
				System.out.println(codigo+"\t"+nome+"\t"+sexo+"\t"+email+"\t" );
			}
		} 		
//		are in try!!!!!!!!
//		rs.close();
//		stm.close();
//		con.close();

	}

}

