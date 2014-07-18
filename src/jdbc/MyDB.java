package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.view.DBInterface;

public class MyDB implements DBInterface {

	private String password;
	private String user;
	private String url;
	private Connection con;

	public MyDB(String url, String user, String password) {
		this.password = password;
		this.user = user;
		this.url = url;
	}

	@Override
	public void setUpConnection() throws SQLException {
		con = DriverManager.getConnection(this.url, this.user, this.password);
	}

	@Override
	public boolean isConnected() {
		return con != null;
	}

	@Override
	public void closeConnection() throws SQLException {
		con.close();
	}

	@Override
	public ArrayList<Conta> fetchAll(String table) throws SQLException {
		ArrayList<Conta> lista = new ArrayList<>();
		String sql = "select numero, cliente, saldo from ";
		sql = sql + table;
		try (PreparedStatement stm = con.prepareStatement(sql)) {
//			stm.setString(1, table);
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					lista.add(new Conta(rs.getInt(1), rs.getString(2), rs
							.getDouble(3)));
				}
			}
		}
		return lista;
	}

	@Override
	public void transfer(Conta origem, Conta destino, double value) throws SQLException {
		if (origem.getSaldo() >= value){
			
			try {
				con.setAutoCommit(false);
				
				/*Saque*/
				origem.setSaldo(origem.getSaldo() - value);
				alterar(origem);
				
				/*Deposito*/
				destino.setSaldo(destino.getSaldo() + value);
				alterar(destino);

				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				con.rollback();
			}
			
		}
	}

	public void alterar(Conta conta) throws SQLException {
		String sql = "update conta set cliente=?, saldo=? where numero=?";
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			
			stm.setString(1, conta.getCliente());
			stm.setDouble(2, conta.getSaldo());
			stm.setInt(3, conta.getNumero());
			
			stm.executeUpdate();
		}
	}

}
