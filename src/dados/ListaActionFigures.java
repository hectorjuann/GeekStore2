package dados;

import java.util.ArrayList;
import java.util.List;

import geekStore.ActionFigures;
import geekStore.Produtos;
import geekStore.RepositorioProdutos;

public class ListaActionFigures implements RepositorioProdutos {

	private List<ActionFigures> DadosActionFigures;
	
	public ListaActionFigures() {
	DadosActionFigures = new ArrayList<ActionFigures>();
	}
	
	@Override
	public void inserir(Produtos p) {
		DadosActionFigures.add((ActionFigures) p);
	}

	@Override
	public void remover(int id) {
		Produtos resultado = null;
		for(Produtos removerProdutos : DadosActionFigures) {
			if (removerProdutos.getId() == id) {
				resultado = removerProdutos;
				break;
			}
		}
		if (resultado != null) {
			DadosActionFigures.remove(resultado);
		} else {
			System.out.println("Produto não encontrado");
		}
		
	}

	@Override
	public Produtos buscar(int id) {
		Produtos resultado = null;
		for(Produtos procurarProdutos : DadosActionFigures) {
			if(procurarProdutos.getId() == id) {
				resultado = procurarProdutos;
			}	
		}
		return resultado;
	}

	@Override
	public void buscarEstoque() {
		String textoFinal = null;
		for(Produtos procurarProdutos : DadosActionFigures) {
			textoFinal = procurarProdutos.toString();
			System.out.println(textoFinal);
		}
		
	}

	@Override
	public String guardarHistorico() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
