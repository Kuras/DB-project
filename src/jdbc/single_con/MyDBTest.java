package jdbc.single_con;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import jdbc.Conta;
import org.junit.Test;

public class MyDBTest {

	@Test
	public void test() {
		// Test fecht all contas from Data Base
		List<Conta> array;
		// how to mocking to testing, because we now dont have access to DB!
		try {
			array = MyDB.fetchAll("Student");
			assertNotNull(array);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// I cant Testing this resolve!!!
		
	}

}
