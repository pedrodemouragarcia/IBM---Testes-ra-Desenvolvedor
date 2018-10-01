package main.java.model;

public class Vendedor {

	private String cpf;
	private String nome;
	private String salario;
	private double valorVendas;

	public Vendedor(String cpf, String nome, String salario) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}

	public Vendedor(String nome, double valorVendas) {
		super();
		this.nome = nome;
		this.valorVendas = valorVendas;
	}

	public double getValorVendas() {
		return valorVendas;
	}

	public void setValorVendas(double valorVendas) {
		this.valorVendas = valorVendas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

}
