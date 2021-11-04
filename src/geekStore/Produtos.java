package geekStore;

public class Produtos {
	protected int id;
	protected String nome;
	protected double preco;
	
	//Constructor
	public Produtos() {
		
	}
	
	public Produtos(String nome, int id, double preco) {
		this.nome = nome;
		this.id = id;
		this.preco = preco;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*----------*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/*----------*/
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	/*----------*/

	@Override
	public String toString() {
		return "\nProduto: " + 
				"\nId: \t\t" + id + 
				"\nNome: \t\t" + nome + 
				"\nPreço Unidade: \t" + preco;
	}
		
	
}
