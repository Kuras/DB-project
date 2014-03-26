package jdbc.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conta;
import jdbc.view.DBInterface;

import org.junit.Test;

public class MockDBTest {

	@Test
	public void testMyDB() {	
		String password = "mypass";
		String user = "baraka";
		String url = "jdbc:mysql://localhost:3306/hibernate";

		DBInterface db = new MockDB(url,user,password);
		assertFalse(db.isConnected());
		ArrayList<Conta> contas;
		try {
			contas = db.fetchAll("conta");
			assertNull(contas);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			db.setUpConnection();
			assertTrue(db.isConnected());
			
			contas = db.fetchAll("conta");
			for (Conta conta : contas) {
				System.out.println(conta);
			}
			
			Conta baraka = new Conta(1, "Baraka", 23.13);
			double expected = 23.13 + 10;
			baraka.setSaldo(baraka.getSaldo() + 10);
			db.alterar(baraka);
			contas = db.fetchAll("conta");
			double actual = contas.get(0).getSaldo();
			assertEquals( new Double(expected), new Double(actual));
//			assertEquals( (expected), (actual), 2014);
			
			db.transfer(baraka, contas.get(1), 3);
			contas = db.fetchAll("conta");
			assertEquals(30.13, contas.get(0).getSaldo(), 2);
			assertEquals(10003.03, contas.get(1).getSaldo(), 2);
			
			db.closeConnection();
			assertFalse(db.isConnected());

		} catch (SQLException e) {
			System.out.println("Error accurd");
		}
	}
	

}
