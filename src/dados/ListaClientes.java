package dados;

import java.util.ArrayList;
import java.util.List;

import geekStore.Clientes;
import geekStore.Produtos;
import geekStore.RepositorioClientes;

public class ListaClientes implements RepositorioClientes {
	private List<Clientes> DadosClientes;
	private List<Produtos> DadosHistorico;
	
	public ListaClientes() {
		DadosClientes = new ArrayList<Clientes>();
	}
	
	@Override
	public void inserir(Clientes c) {
		DadosClientes.add(c);
	}

	@Override
	public void remover(String cpf) {
		Clientes resultado = null;
		for(Clientes removerClientes : DadosClientes) {
			if (removerClientes.getCpf().equals(cpf)) {
				resultado = removerClientes;
				break;
			}
		}
		if (resultado != null) {
			DadosClientes.remove(resultado);
		} else {
			System.out.println("CPF não encontrado");
		}
			
	}

	@Override
	public Clientes buscar(String cpf) {
		Clientes resultado = null;
		for(Clientes procurarClientes : DadosClientes) {
			if(procurarClientes.getCpf().equals(cpf)) {
				resultado = procurarClientes;
			}	
		}
		return resultado;
	}
	

	@Override
	public void inserirHistorico(Clientes c, Produtos p) {
		for(Clientes procurarClientes : DadosClientes) {
			if(procurarClientes.getCpf().equals(c.getCpf())) {
				DadosHistorico.add(p);
			}
		}
	}

	@Override
	public void mostrarHistorico(Clientes c) {
		String textoFinal = null;
		for (Clientes procurarClientes : DadosClientes) {
			if(procurarClientes.getCpf().equals(c.getCpf())) {
				for(Produtos procurarProdutos : DadosHistorico) {
					textoFinal = procurarProdutos.toString();
					System.out.println(textoFinal);
			}
			}	
			
		}
	}
	
	public List<Clientes> listarClientes() {
		return this.DadosClientes;
	}
	
}
