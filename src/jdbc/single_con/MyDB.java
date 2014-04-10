package jdbc.single_con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.Conta;

public class MyDB{

	private static final String URL = null;
	private static final String USER = null;
	private static final String PASSWORD = null;
	
	private static Connection con = MyDB.getInstance();
	
	private MyDB() {
	}
	
	private static Connection getInstance() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	// funkcjonalnoœæ nietestowalna!!!
	public static List<Conta> fetchAll(String table) throws SQLException {
		ArrayList<Conta> lista = new ArrayList<>();
		String sql = "select numero, cliente, saldo from ";
		sql = sql + table;
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					lista.add(new Conta(rs.getInt(1), rs.getString(2), rs
							.getDouble(3)));
				}
			}
		}
		return lista;
	}
	
}
