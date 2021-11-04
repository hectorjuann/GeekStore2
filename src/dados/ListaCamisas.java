package dados;

import java.util.ArrayList;
import java.util.List;

import geekStore.Camisas;
import geekStore.Produtos;
import geekStore.RepositorioProdutos;

public class ListaCamisas implements RepositorioProdutos{
	private List<Camisas> DadosCamisas;
	
		public ListaCamisas() {
		DadosCamisas = new ArrayList<Camisas>();
		}
		
	@Override
	public void inserir(Produtos p) {
		DadosCamisas.add((Camisas) p);
	}

	@Override
	public void remover(int id) {
		Produtos resultado = null;
		for(Produtos removerProdutos : DadosCamisas) {
			if (removerProdutos.getId() == id) {
				resultado = removerProdutos;
				break;
			}
		}
		if (resultado != null) {
			DadosCamisas.remove(resultado);
		} else {
			System.out.println("Produto não encontrado");
		}
		
	}

	
	public Produtos buscar(int id) {
		Produtos resultado = null;
		for(Produtos procurarProdutos : DadosCamisas) {
			if(procurarProdutos.getId() == id) {
				resultado = procurarProdutos;
			}	
		}
		return resultado;
	}

	@Override
	public void buscarEstoque() {
		String textoFinal = null;
		for(Produtos procurarProdutos : DadosCamisas) {
			textoFinal = procurarProdutos.toString();
			System.out.println(textoFinal);
		}
		
	}

	public List<Camisas> listarProdutos() {
		return this.DadosCamisas;
	}

	@Override
	public String guardarHistorico() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
