package privPack;

import jdbc.Conta;


public class Mycalass {

	public static void main(String[] args) {
		Conta conta = new Conta(2, "", 23);
		conta.getSaldo();
	}

}
