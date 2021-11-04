package geekStore;

import java.util.Scanner;

public class Endereco {
	private int id;
	private String cep;
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	Scanner input = new Scanner(System.in);
	
	//Constructor
	public Endereco() {
		
	}
	
	public Endereco(String cep, String estado, String cidade, String bairro, String rua, int numero) {
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	
	//Getters and Setters
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	/*----------*/
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/*----------*/
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	/*----------*/
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/*----------*/
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	/*----------*/
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/*----------*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/*----------*/
	
	public void cadastro() {
		String respString;
		int respInt;
		
		System.out.println("-------------- Cadastro do Endereço --------------");
		
		System.out.println("Informe o seu CEP:");
		respString = input.nextLine();
		this.setCep(respString);
		
		System.out.println("Informe o seu Estado:");
		respString = input.nextLine();
		this.setEstado(respString);
		
		System.out.println("Informe a sua Cidade:");
		respString = input.nextLine();
		this.setCidade(respString);
		
		System.out.println("Informe o seu Bairro:");
		respString = input.nextLine();
		this.setBairro(respString);
		
		System.out.println("Informe a sua Rua:");
		respString = input.nextLine();
		this.setRua(respString);
		
		System.out.println("Informe o número:");
		respInt = input.nextInt();
		this.setNumero(respInt);
	}
	
	
	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro + ", rua="
				+ rua + ", numero=" + numero + "]";
	}
	
}
