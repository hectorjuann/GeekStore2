package geekStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clientes {
	private int id_cliente;
	private String nome;
	private String cpf;
	private String email;
	private int idade;
	private String senha;
	private Endereco endereco;
	private Produtos historico;
	Scanner input = new Scanner(System.in);
	
	//Constructor
	public Clientes() {
		this.nome = null;
		this.cpf = null;
		this.email = null;
		this.idade = 0;
		this.senha = null;
		this.endereco = null;
		this.senha = null;
	}
	
	public Clientes(String nome, String cpf, int idade, String email, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.email = email;
		this.senha = senha;
	}
	
	//Getters and Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/*----------*/
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/*----------*/
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	/*----------*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*----------*/
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	/*----------*/
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	/*----------*/

	public Produtos getHistorico() {
		return historico;
	}

	public void setHistorico(Produtos historico) {
		this.historico = historico;
	}
	/*----------*/
	
	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	List<Produtos> AddProdutos = new ArrayList<Produtos>();
	
	public void cadastro() {
		String respString;
		int respInt;
		
		System.out.println("-------------- Cadastro do Cliente --------------");
		
		System.out.println("Digite o seu nome:");
		respString = input.nextLine();
		this.setNome(respString);
		
		System.out.println("Digite o seu CPF:");
		respString = input.nextLine();
		this.setCpf(respString);
		
		System.out.println("Digite o seu email:");
		respString = input.nextLine();
		this.setEmail(respString);
		
		System.out.println("Digite a sua idade:");
		respInt = input.nextInt();
		this.setIdade(respInt);
		
		input.nextLine();
		
		System.out.println("Digite a sua senha:");
		respString = input.nextLine();
		this.setSenha(respString);
		
	}
	
	public void inserirHistorico() {
		AddProdutos.add(this.getHistorico());
	}
	
	public void mostrarHistorico() {
		for(Produtos procurarProdutos : AddProdutos) {
			System.out.println(procurarProdutos);
				
			}
		}

	@Override
	public String toString() {
		return "Clientes [nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", email=" + email + ", endereco="
				+ endereco + "]";
	}
}
