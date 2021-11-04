package geekStore;

public interface RepositorioProdutos {
	
	public abstract void inserir(Produtos p);
	public abstract void remover(int id);
	public abstract Produtos buscar(int id);
	public abstract void buscarEstoque();
	public abstract String guardarHistorico();
	
}
