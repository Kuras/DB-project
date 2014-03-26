package jdbc.view;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conta;

public interface DBInterface {

	void setUpConnection() throws SQLException;

	boolean isConnected();

	void closeConnection() throws SQLException;

	ArrayList<Conta> fetchAll(String table) throws SQLException;

	void transfer(Conta origem, Conta destino, double value) throws SQLException;
	
	public void alterar(Conta conta) throws SQLException;
}
