package geekStore;

import java.util.Scanner;

import conexaoBD.Conexao;
import dados.ListaActionFigures;
import dados.ListaCamisas;
import dados.ListaClientes;
import dados.NotaFiscal;

public class Compra {
	private Clientes cliente;
	private Produtos produto;
	private char formaPagamento;
	private double valorTotal;
	Scanner input = new Scanner(System.in);
	
	RepositorioProdutos repCamisas = new ListaCamisas();
	RepositorioProdutos repActionFigures = new ListaActionFigures();
	RepositorioProdutos repNotaFiscal = new NotaFiscal();
	RepositorioClientes repClientes = new ListaClientes();
	
	Produtos p = new Produtos();
	//Constructor
	public Compra() {
		
	}
	/*----------*/
	public Compra(Clientes cliente) {
		this.cliente = cliente;
	}
	/*----------*/
	public Compra(Clientes cliente, Produtos produto) {
		this.cliente = cliente;
		this.produto = produto;
	}
	
	//Getters and Setters
	public char getPagamento() {
		return formaPagamento;
	}
	public void setPagamento(char pagamento) {
		this.formaPagamento = pagamento;
	}
	/*----------*/
	public Clientes getCliente() {
		return cliente;
	}
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	/*----------*/
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal ) {
		this.valorTotal = valorTotal;
	}
	/*----------*/
	public Produtos getProdutos() {
		return produto;
	}
	public void setProdutos(Produtos produto) {
		this.produto = produto;
	}
	/*----------*/
	
	public void comprar() {	
		Conexao conexao = new Conexao();
		Produtos produtoLocal = new Produtos();
		
		int quantidade;
		double preco;
		double precoTotal = 0;
		double valorFinal = 0;
		char resp;
		boolean parar = false;
		int respInt = 0;
		int inputId = 0;
		String tabela = null;
			
			do {
				preco = 0;
				precoTotal = 0;
				
				System.out.println("Qual tipo de produto você deseja comprar?");
				do {
					
					System.out.println("[1] Camisas\n[2] Action Figures");
					respInt = input.nextInt();
					
					if (respInt == 1 || respInt == 2) {
						parar = true;
					} else {
						System.out.println("Escolha uma opção válida: ");
						parar = false;
					}
					
				} while (parar == false) ;	
				
				switch (respInt) {
				case 1:
					produtoLocal = null;
					tabela = "camisasstorage";
					conexao.buscarProdutos(tabela);
					break;
				case 2:
					produtoLocal = null;
					tabela = "actionfiguresstorage";
					conexao.buscarProdutos(tabela);
					
					break;
				}
				
				do {	
					System.out.println("Digite o código do produto desejado: ");
					inputId = input.nextInt();
					produtoLocal = conexao.selecionarProdutos(inputId, tabela);
					
					if (conexao.selecionarProdutos(inputId, tabela) == null) {
						System.out.println("\nNão encontramos este produto.\nPor favor, selecione um item em estoque.\n");
						parar = false;
					} else {
						parar = true;	
					}	
					
				} while (parar == false);

			preco = produtoLocal.getPreco();
			String concat = produtoLocal.toString();
			conexao.guardarHistorico(concat, cliente.getId_cliente());
			System.out.println("Preço do produto: R$" + preco);
			
			System.out.println("\nQuantidade de produtos: ");
			do {
				quantidade = input.nextInt();
				if (quantidade < 1 || quantidade > 10) {
					System.out.println("Não podemos fornecer a quantidade de produtos desejada.\nPor favor, selecione no mínimo 1 produto: ");
					parar = false;
				} else {
					parar = true;
				}
				
			} while (parar == false);
			
			//Não pode ser igual ou menor a 0
			
			for (int i = 1; i <= quantidade; i++) {
				precoTotal += preco;
			}
			
			System.out.println("Valor final R$: " + precoTotal + "\n");
			
			for (int i = 0; i < quantidade; i++) {
				repNotaFiscal.inserir(produtoLocal);
				repClientes.buscar(cliente.getCpf());
				cliente.setHistorico(produtoLocal);
				cliente.inserirHistorico();
			}
			
			do {
				System.out.println("Continuar comprando?[S]/[N]");
				resp = input.next().charAt(0);
								
				if (resp == 'n' || resp == 'N') {
					valorFinal += precoTotal;
					this.setValorTotal(valorFinal);
					parar = true;
				} else if (resp == 's' || resp == 'S') {
					System.out.println("Fique a vontade para adquirir mais produtos na GeekStore!\n");
					valorFinal += precoTotal;
					parar = true;
				} else {
					System.out.println("Digite [S] para continuar\nDigite [N] para sair");
					parar = false;
				}
				
				
			} while (parar == false) ;			
			
			
			} while (resp == 's' || resp == 'S');
			
			this.pagamento();
			this.imprimirNota();
	}
	
		private void pagamento() {
		boolean parar = false;
		System.out.println("Forma de pagamento: ");
		System.out.println("Crédito: C\nDébito:\t D\nBoleto:\t B");
		
		
		do {
			
			this.formaPagamento = input.next().charAt(0);
			
			if (this.formaPagamento == 'c' || this.formaPagamento == 'C') {
				System.out.println("Você quer pagar em quantas vezes?");
				int vezes = input.nextInt();
				parar = true;
				
			} else if (this.formaPagamento == 'd' || this.formaPagamento == 'D') {
				parar = true;
				
			} else if (this.formaPagamento == 'b' || this.formaPagamento == 'B') {
				this.valorTotal = this.valorTotal * 0.9;
				System.out.println("10% de desconto");
				System.out.printf("O valor total da compra é: R$ %.2f", this.valorTotal);
				this.setValorTotal(this.valorTotal);
				parar = true;
			} else {
				System.out.println("Escolha uma opção de pagamento válida");
				parar = false;
			}
			
		} while (parar == false);
	}
	
		private String imprimirNota() {
		String historico;
		Conexao conexao = new Conexao();
		System.out.println("\n");
		System.out.println("############## Nota Fiscal ##############\n");
		System.out.println("NOME DO CLIENTE: " + cliente.getNome());
		System.out.println("CPF DO CLIENTE: " + cliente.getCpf());
		System.out.println(" ");
		repNotaFiscal.buscarEstoque();
		System.out.printf("Valor total da compra: R$%.2f", this.getValorTotal());
		System.out.println("\n");
		System.out.println("GeekStore");
		System.out.println("##########################################");
		historico = repNotaFiscal.guardarHistorico();
		
		return historico;
	}		
						
	@Override
	public String toString() {
		return "Compra [pagamento=" + formaPagamento + ", cliente=" + cliente + " valorTotal=" + valorTotal + ", input="
				+ input + "]";
	}
}
