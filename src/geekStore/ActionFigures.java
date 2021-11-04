package geekStore;

public class ActionFigures extends Produtos{
	private int tamanho;
	private String categoria;
	private boolean articulado;
	
	//Constructor
	public ActionFigures() {
		
	}
	
	public ActionFigures(String nome, int id, double preco, int tamanho, String categoria, boolean articulado) {
		super(nome, id, preco);
		this.tamanho = tamanho;
		this.categoria = categoria;
		this.articulado = articulado;
	}

	// Getters and Setters
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	/*----------*/
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/*----------*/
	public boolean getArticulado() {
		return articulado;
	}

	public void setArticulado(boolean articulado) {
		this.articulado = articulado;
	}
	/*----------*/
	
	@Override
	public String toString() {
		return super.toString() + 
				"\nTamanho: \t" + tamanho + " cm" + 
				"\nArticulado: \t" + articulado + 
				"\nCategoria: \t" + categoria;
	}

		
	
}
