package main.java.model;

public class Venda {
	public String idVenda;
	public String[] itens;
	public String vendedor;
	private double valorTotal;

	public Venda(String idVenda, String[] itens, String vendedor) {
		super();
		this.idVenda = idVenda;
		this.itens = itens;
		this.vendedor = vendedor;
	}

	public Venda(String idVenda, double valorTotal, String vendedor) {
		super();
		this.idVenda = idVenda;
		this.valorTotal = valorTotal;
		this.vendedor = vendedor;
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public String[] getItens() {
		return itens;
	}

	public void setItens(String[] itens) {
		this.itens = itens;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
