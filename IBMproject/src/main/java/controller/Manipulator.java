package main.java.controller;

import java.util.ArrayList;

import main.java.dao.Writer;
import main.java.model.Cliente;
import main.java.model.Venda;
import main.java.model.Vendedor;

public class Manipulator {
	private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Venda> vendas = new ArrayList<Venda>();
	private ArrayList<Venda> vendasCalculadas = new ArrayList<Venda>();
	private ArrayList<Vendedor> vendasVendedores = new ArrayList<Vendedor>();

	/*
	 * Checks the line from the data file and filter it by the id. Splits the string
	 * where a ç is found. Creates the objects according the id and add it to
	 * arrays.
	 */
	public void checkLine(String line) {
		String idLine = line.substring(0, Math.min(line.length(), 3));
		if (idLine.matches("001")) {
			String[] parts = line.split("ç");
			String vendedorCPF = parts[1];
			String vendedorName = parts[2];
			String vendedorSalary = parts[3];
			Vendedor vendedor = new Vendedor(vendedorCPF, vendedorName, vendedorSalary);
			vendedores.add(vendedor);
		} else if (idLine.matches("002")) {
			String[] parts = line.split("ç");
			String clienteCNPJ = parts[1];
			String clienteName = parts[2];
			String clienteBusinessArea = parts[3];
			Cliente cliente = new Cliente(clienteCNPJ, clienteName, clienteBusinessArea);
			clientes.add(cliente);
		} else if (idLine.matches("003")) {
			String[] parts = line.split("ç");
			String saleID = parts[1];
			String saleItens = parts[2];
			saleItens = saleItens.substring(1);
			saleItens = saleItens.substring(0, saleItens.length() - 1);
			String[] itens = saleItens.split(",");
			String saleSalesmanName = parts[3];
			Venda venda = new Venda(saleID, itens, saleSalesmanName);
			vendas.add(venda);
		}
	}

	/*
	 * Starts the analysis report
	 */
	public void beginReport(String fileName) {
		parseAndCalculateSalesItens();
		String question1 = "Quantidade de clientes no arquivo de entrada = " + vendedores.size();
		String question2 = "Quantidade de vendedores no arquivo de entrada = " + clientes.size();
		String question3 = "ID da venda mais cara = " + calculateBiggestSale();
		String question4 = "O pior vendedor = " + calculateWorstSalesman();
		String fileNoExtension = removeExtenstion(fileName);
		Writer writer = new Writer();
		writer.createAndWriteReport(fileNoExtension, question1, question2, question3, question4);
		clearArrays();
	}

	/*
	 * Removes the extension from the input file name and returns only the file name
	 */
	public String removeExtenstion(String fileName) {
		int length = fileName.length() - 4;
		fileName = fileName.substring(0, length);
		return fileName;
	}

	/*
	 * Parse the sale itens string and calculates the total value of the sale
	 */
	public void parseAndCalculateSalesItens() {
		for (int x = 0; x < vendas.size(); x++) {
			String itens[] = vendas.get(x).getItens();
			double valorTotal = 0;
			for (int y = 0; y < itens.length; y++) {
				String[] parts = itens[y].split("-");
				String quantidadeTemp = parts[1];
				String valorTemp = parts[2];
				double quantidade = Double.parseDouble(quantidadeTemp);
				double valor = Double.parseDouble(valorTemp);
				valorTotal = valorTotal + (quantidade * valor);
			}
			Venda vc = new Venda(vendas.get(x).getIdVenda(), valorTotal, vendas.get(x).getVendedor());
			vendasCalculadas.add(vc);
			addSaleToVendedor(valorTotal, vendas.get(x).getVendedor());
		}
	}

	/*
	 * Add a new sale for a salesman
	 */
	public void addSaleToVendedor(double valor, String vendedor) {
		int result = checkIndexOfVendedor(vendedor);
		if (result == 0) {
			Vendedor v = new Vendedor(vendedor, valor);
			vendasVendedores.add(v);
		} else {
			double soma = valor + vendasVendedores.get(result).getValorVendas();
			vendasVendedores.get(result).setValorVendas(soma);
		}
	}
	
	/*
	 * Checks if a salesman already have a sale,
	 *  if it has return his index from the
	 * vendasVedores ArrayList if not return 0
	 */
	public int checkIndexOfVendedor(String vendedor) {
		for (int x = 0; x < vendasVendedores.size(); x++) {
			if (vendasVendedores.get(x).getNome().matches(vendedor)) {
				return x;
			}
		}
		return 0;
	}

	/*
	 * Calculates and return the biggest sale from the ArrayList vendasCalculadas
	 */
	public String calculateBiggestSale() {
		String idSale = "";
		double totalVenda = 0;
		for (int x = 0; x < vendasCalculadas.size(); x++) {
			if (vendasCalculadas.get(x).getValorTotal() > totalVenda) {
				totalVenda = vendasCalculadas.get(x).getValorTotal();
				idSale = vendasCalculadas.get(x).getIdVenda();
			}
		}
		return idSale;
	}

	/*
	 * Calculates and return the worst salesman from the ArrayList vendasVendedores
	 */
	public String calculateWorstSalesman() {
		String vendedor = "";
		if (vendasVendedores.size() > 0) {
			double totalVendas = vendasVendedores.get(0).getValorVendas();
			vendedor = vendasVendedores.get(0).getNome();
			for (int x = 0; x < vendasVendedores.size(); x++) {
				if (vendasVendedores.get(x).getValorVendas() < totalVendas) {
					totalVendas = vendasVendedores.get(x).getValorVendas();
					vendedor = vendasVendedores.get(x).getNome();
				}
			}
		}
		return vendedor;
	}

	/*
	 * Clear all the ArrayLists
	 */
	public void clearArrays() {
		vendedores.clear();
		clientes.clear();
		vendas.clear();
		vendasCalculadas.clear();
		vendasVendedores.clear();
	}
}