package main.java.model;

public class Cliente {

	private String cnpj;
	private String nome;
	private String businessArea;
	
	public Cliente(String cnpj, String nome, String area) {
		setCnpj(cnpj);
		setNome(nome);
		setBusinessArea(area);
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	

}
