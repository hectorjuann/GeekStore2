package geekStore;

public interface RepositorioClientes {
	
	public abstract void inserir(Clientes c);
	public abstract void remover(String cpf);
	public abstract Clientes buscar(String cpf);
	public abstract void inserirHistorico(Clientes c, Produtos p);
	public abstract void mostrarHistorico(Clientes c); 
	
}
