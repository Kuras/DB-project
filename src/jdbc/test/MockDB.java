package jdbc.test;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conta;
import jdbc.view.DBInterface;

public class MockDB implements DBInterface {

	private boolean connected = false;
	private ArrayList<Conta> contas = new ArrayList<>();

	public MockDB(String url, String user, String password) {
		
		contas.add(new Conta(1, "Baraka", 23.13));
		contas.add(new Conta(2, "Ricardo", 10000.03));
		contas.add(new Conta(3, "Beatriz", 111.13));

	}

	@Override
	public void setUpConnection() throws SQLException {
		this.connected  = true;
//		int x = 1/0;
	}

	@Override
	public boolean isConnected() {
		return this.connected;
	}

	@Override
	public void closeConnection() throws SQLException {
		this.connected = false;
	}

	@Override
	public ArrayList<Conta> fetchAll(String table) throws SQLException {
		if (isConnected()){
			return contas;
		} else {
			return null;
		}
		
	}

	@Override
	public void transfer(Conta origem, Conta destino, double value)
			throws SQLException {
		if (origem.getSaldo() >= value){
			/*Saque*/
			origem.setSaldo(origem.getSaldo() - value);
			/*Deposito*/
			destino.setSaldo(destino.getSaldo() + value);
			contas.set(origem.getNumero() - 1, origem);
			contas.set(destino.getNumero() - 1, destino);
		}

	}

	@Override
	public void alterar(Conta conta) throws SQLException {
		contas.set(conta.getNumero() - 1, conta);
	}

}
