

public class Conta {

	private int numero;
	private String cliente;
	private double saldo;

	public Conta(int numero, String cliente, double saldo) {
		this.numero = numero;
		this.cliente= cliente;
		this.saldo = saldo;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return numero + ", " + cliente + ", " + saldo;
	}

}
