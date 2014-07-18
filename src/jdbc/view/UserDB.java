package jdbc.view;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conta;
import jdbc.MyDB;

public class UserDB {

	public static void main(String[] args) throws SQLException {
		String password = "mypass";
		String user = "baraka";
		String url = "jdbc:mysql://localhost:3306/hibernate";
		String table = "conta";
		
		//in this place should be injection! and it is
		//conta table
		DBInterface db = new MyDB(url,user,password);
//		db = new MockDB(url, user, password);
		
		try {
			db.setUpConnection();
			ArrayList<Conta> contas = db.fetchAll(table);
			for (Conta conta : contas) {
				System.out.println(conta);
			}
			
			Conta origem = contas.get(0);
			Conta destino = contas.get(1);
			db.transfer(origem, destino, 500);

			contas = db.fetchAll(table);
			for (Conta conta : contas) {
				System.out.println(conta);
			}
			
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		} finally {
			if (db.isConnected()){
				db.closeConnection();
			}
		}
	}

}
