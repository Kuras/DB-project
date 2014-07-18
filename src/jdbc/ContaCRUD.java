package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

//import com.mysql.jdbc.Connection;

public class ContaCRUD {

	public void transferir(Connection con, Conta origem, Conta destino, double valor) throws SQLException {
		if (origem.getSaldo() >= valor){
			
			try {
				con.setAutoCommit(false);
				
				/* SAQUE */
				origem.setSaldo(origem.getSaldo() - valor);
				alterar(con, origem);
				
//				throw new Exception(); eqwiwalent ->
//				int x = 1 / 0;
				
				/* DEPOSITO */
				destino.setSaldo(destino.getSaldo() + valor);
				alterar(con, destino);
				
				con.commit();
			} catch (Exception e) {
				con.rollback();
			}
			
		}
		
	}
	
	public void criar(Connection con, Conta conta) throws SQLException {
		String sql = "insert into conta values(?,?,?)";
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			stm.setInt(1, conta.getNumero());
			stm.setString(2, conta.getCliente());
			stm.setDouble(3, conta.getSaldo());
		
			stm.executeUpdate();
		}
	}

	public ArrayList<Conta> ler(Connection con) throws SQLException {
		ArrayList<Conta> lista = new ArrayList<>();
		String sql = "select numero, cliente, saldo from conta";

		try (PreparedStatement stm = con.prepareStatement(sql);
				ResultSet rs = stm.executeQuery()) {
			while (rs.next()) {
				lista.add(new Conta(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
			}
		}
		return lista;
	}
	
	public void excluir(Connection con, Conta conta) throws SQLException {
		String sql = "delete from conta where numero=?";
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			stm.setInt(1, conta.getNumero());			
			stm.executeUpdate();
		}
	}
	
	public void alterar(Connection con, Conta conta) throws SQLException {
		String sql = "update conta set cliente=?, saldo=? where numero=?";
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			stm.setString(1, conta.getCliente());
			stm.setDouble(2, conta.getSaldo());
			stm.setInt(3, conta.getNumero());
			
			stm.executeUpdate();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		String password = "mypass";
		String user = "baraka";
		String url = "jdbc:mysql://localhost:3306/hibernate";
		try (Connection con = DriverManager.getConnection(url , user , password )){
			ContaCRUD crud = new ContaCRUD();
			
//			Conta conta1 = new Conta(1, "Baraka", 23.13);
//			Conta conta2 = new Conta(2, "Ricardo", 10000.03);
//			Conta conta3 = new Conta(3, "Beatriz", 111.13);
//			crud.criar(con, conta1);
//			crud.criar(con, conta2);
//			crud.criar(con, conta3);
//			Conta conta1 = new Conta(1, "Baraka", 23.13);
//			conta1.setSaldo(1000000.00);
//			crud.alterar(con, conta1);
//			
//			Conta conta3 = new Conta(3, "Beatriz", 111.13);
//			crud.criar(con, conta3);
	
			ArrayList<Conta> contas = crud.ler(con);
			for (Conta conta : contas) {
				System.out.println(conta);
			}
			
			Conta origem = contas.get(0);
			Conta destino = contas.get(1);
			crud.transferir(con, origem, destino, 500);
			
			contas = crud.ler(con);
			for (Conta conta : contas) {
				System.out.println(conta);
			}
			
		}// close connection
	}
}
