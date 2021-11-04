package dados;

import java.util.ArrayList;
import java.util.List;

import geekStore.Produtos;
import geekStore.RepositorioProdutos;

public class NotaFiscal implements RepositorioProdutos{
	private List<Produtos> DadosNotaFiscal;
	
	public NotaFiscal() {
	DadosNotaFiscal = new ArrayList<Produtos>();
	}
	
	@Override
	public void inserir(Produtos p) {
		DadosNotaFiscal.add(p);
		
	}

	@Override
	public void remover(int id) {
		Produtos resultado = null;
		for(Produtos removerProdutos : DadosNotaFiscal) {
			if (removerProdutos.getId() == id) {
				resultado = removerProdutos;
				break;
			}
		}
		if (resultado != null) {
			DadosNotaFiscal.remove(resultado);
		} else {
			System.out.println("Produto não encontrado");
		}
		
	}

	@Override
	public Produtos buscar(int id) {
		Produtos resultado = null;
		for(Produtos procurarProdutos : DadosNotaFiscal) {
			if(procurarProdutos.getId() == id) {
				resultado = procurarProdutos;
			}	
		}
		return resultado;
	}

	@Override
	public void buscarEstoque() {
		String textoFinal = null;
		for(Produtos procurarProdutos : DadosNotaFiscal) {
			textoFinal = procurarProdutos.toString();
			System.out.println(textoFinal);
		}
		
	}
	
	@Override
	public String guardarHistorico() {
		String textoFinal = null;
		
		for(Produtos procurarProdutos : DadosNotaFiscal) {
			textoFinal = procurarProdutos.toString();
			
	} return textoFinal;
	}

}
