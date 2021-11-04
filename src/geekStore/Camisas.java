package geekStore;

import dados.NotaFiscal;

public class Camisas extends Produtos{
	private String tamanho;
	private String cor;
	private String genero;
	
	
	//Constructor
	public Camisas() {
		
	}
	
	public Camisas(String nome, int id, double preco, String tamanho, String cor, String genero) {
		super(nome, id, preco);
		this.tamanho = tamanho;
		this.cor = cor;
		this.genero = genero;
	}
	
	//Getters and Setters
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	/*----------*/
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	/*----------*/
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	/*----------*/

	@Override
	public String toString() {
		return  super.toString() +
				"\nCor: \t\t" + cor + 
				"\nTamanho: \t" + tamanho + " " + 
				"\nGênero: \t" + genero + "\n";
	}
	
}
