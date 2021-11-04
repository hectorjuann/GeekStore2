package conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import geekStore.ActionFigures;
import geekStore.Camisas;
import geekStore.Clientes;
import geekStore.Produtos;

public class Conexao {
	Scanner input = new Scanner(System.in);
	Connection conexao;
	Integer id_cliente = 0;
	int id_endereco = 0;

	public Conexao() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/geekstorage", "root", "root");

			System.out.println("Conectado!");
		} catch (SQLException e) {
			System.out.println("Não foi possível estabelecer conexão com o banco de dados.");
		}
	}

	public void buscarProdutos(String tabela) {
		PreparedStatement stmt;
		ResultSet rs;

		try {
			stmt = conexao.prepareStatement("SELECT * FROM " + tabela);
			rs = stmt.executeQuery();

			switch (tabela) {
			case "camisasstorage":
				while(rs.next()) {
					System.out.println("Produto: \n" + "Id: \t\t" + rs.getInt("Id") +
							"\nNome: \t\t" + rs.getString("nome") + 
							"\nPreço Unidade: \t" + rs.getDouble("preco") +
							"\nCor: \t\t" + rs.getString("cor") +
							"\nTamanho: \t" + rs.getString("tamanho") +
							"\nGênero: \t" + rs.getString("genero") + "\n");
				}
				break;

			case "actionfiguresstorage":
				while(rs.next()) {
					System.out.println("Produto: \n" + "Id: \t\t" + rs.getInt("Id") +
							"\nNome: \t\t" + rs.getString("nome") + 
							"\nPreço Unidade: \t" + rs.getDouble("preco") +
							"\nArticulado: \t" + rs.getBoolean("articulado") +
							"\nCategoria: \t" + rs.getString("categoria") +
							"\nTamanho: \t" + rs.getInt("tamanho") + "\n");
				}break;
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível executar o comando.");
		}

	}

	public Produtos selecionarProdutos(int id, String tabela) {
		Camisas camisa = new Camisas();
		ActionFigures boneco = new ActionFigures();
		Produtos produto = new Produtos();
		PreparedStatement stmt;
		ResultSet rs;

		try {
			stmt = conexao.prepareStatement("SELECT * FROM " + tabela + " WHERE id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (tabela == "camisasstorage") {

				if (rs.next()) {
					camisa.setCor(rs.getString("cor"));
					camisa.setId(rs.getInt("Id"));
					camisa.setNome(rs.getString("nome"));
					camisa.setPreco(rs.getDouble("preco"));
					camisa.setTamanho(rs.getString("tamanho"));
					camisa.setGenero(rs.getString("genero"));

					produto = camisa;
				} else {
					return produto = null;
				}

			} else if (tabela == "actionfiguresstorage") {
				if (rs.next()) {
					boneco.setArticulado(rs.getBoolean("articulado"));
					boneco.setCategoria(rs.getString("categoria"));
					boneco.setId(rs.getInt("Id"));
					boneco.setNome(rs.getString("nome"));
					boneco.setPreco(rs.getDouble("preco"));
					boneco.setTamanho(rs.getInt("tamanho"));
					produto = boneco;
				} else {
					return produto = null;
				}
			}


		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return produto;
	}

	public void guardarHistorico(String desc_novo, int id) {
		String desc = null;
		try {
			PreparedStatement st = conexao.prepareStatement("SELECT descricao FROM historico JOIN clientes WHERE id_clientes_fk = id_clientes and id_clientes = " + id);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				desc = rs.getString("descricao");	
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement st2 = conexao.prepareStatement("UPDATE historico SET descricao = '"+ desc + "\n" + desc_novo +"'  WHERE id_clientes_fk = " + id);
			st2.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possivel inserir na tabela de historico");
			e.printStackTrace();
		}
		
	}

	public String mostrarHistorico(int id) {
		String desc = null;

		try {
			PreparedStatement st = conexao.prepareStatement("SELECT descricao FROM historico JOIN clientes WHERE id_clientes_fk = id_clientes and id_clientes = " + id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				desc = rs.getString("descricao");
			}
System.out.println(desc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return desc;

	}


	public int cadastroClientes() {
		String nome = null, cpf = null, email = null, senha = null;
		int idade = 0;

		PreparedStatement st;

		try {
			st = conexao.prepareStatement("INSERT INTO clientes (nome, cpf, email, idade, senha) values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);


			System.out.println("Digite o seu nome:");
			nome = input.nextLine();
			st.setString(1, nome);

			System.out.println("Digite o seu CPF:");
			cpf = input.nextLine();
			st.setString(2, cpf);

			System.out.println("Digite o seu email:");
			email = input.nextLine();
			st.setString(3, email);

			System.out.println("Digite a sua idade:");
			idade = input.nextInt();
			st.setInt(4, idade);
			input.nextLine();

			System.out.println("Digite a sua senha:");
			senha = input.nextLine();
			st.setString(5, senha);

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				id_cliente = rs.getInt(1);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_cliente;
	}

	public int cadastroEndereco(int id_cliente) {
		String cep = null, estado = null, cidade = null, bairro = null, rua = null;
		int numero = 0;

		PreparedStatement st;

		try {
			st = conexao.prepareStatement("INSERT INTO enderecos (cep, estado, cidade, bairro, rua, numero, id_clientes_fk) VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			System.out.println("Informe o seu CEP:");
			cep = input.nextLine();
			st.setString(1, cep);

			System.out.println("Informe o seu Estado:");
			estado = input.nextLine();
			st.setString(2, estado);

			System.out.println("Informe a sua Cidade:");
			cidade = input.nextLine();
			st.setString(3, cidade);

			System.out.println("Informe o seu Bairro:");
			bairro = input.nextLine();
			st.setString(4, bairro);

			System.out.println("Informe a sua Rua:");
			rua = input.nextLine();
			st.setString(5, rua);

			System.out.println("Informe o número:");
			numero = input.nextInt();
			st.setInt(6, numero);

			st.setInt(7, id_cliente);

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				id_endereco = rs.getInt(1);
				System.out.println("===============");
				System.out.println(id_endereco);
				System.out.println("===============");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_endereco;
	}

	public void cadastroHistorico(int id_clientes) {
		try {
			PreparedStatement st = conexao.prepareStatement("INSERT INTO historico (id_clientes_fk) VALUES (?)");
			st.setInt(1, id_clientes);	
			st.executeUpdate();
			
				st.setInt(1, id_clientes);	
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int login(String inputCpf, String inputSenha) {
		int respInt;
		int logado = 0;

		try {
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM clientes WHERE cpf = " + inputCpf);
			ResultSet rs = st.executeQuery();
			if (rs != null && rs.next()) {
				String s = rs.getString("senha");
				if ( s.equals(inputSenha) ) {
					System.out.println("Senha correta");
					logado = 1;
				}
				else {
					System.out.println("CPF ou SENHA incorretos");
					System.out.println("-------------- MENU --------------");
					System.out.println("[1] Tentar Novamente\n[2] Voltar");
					System.out.println("----------------------------------");
					respInt = input.nextInt();

					switch (respInt) {
					case 1:
						logado = 3;
						break;
					case 2: 
						logado = 2;
						break;
					}
				}
			} else {
				System.out.println("Não achamos seu CPF na base de dados");
				System.out.println("\n");
				System.out.println("-------------- MENU --------------");
				System.out.println("[1] Tentar Novamente\n[2] Voltar");
				System.out.println("----------------------------------");
				respInt = input.nextInt();
				switch (respInt) {
				case 1:
					logado = 3;
					break;
				case 2: 
					logado = 2;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return logado;
	}

	public void deleteConta(int id_cliente) {
		try {
			PreparedStatement st = conexao.prepareStatement("DELETE FROM enderecos WHERE id_clientes_fk = ?");
			st.setInt(1, id_cliente);
			st.executeUpdate();
			
			PreparedStatement st2 = conexao.prepareStatement("DELETE FROM historico WHERE id_clientes_fk = ?");
			st2.setInt(1, id_cliente);
			st2.executeUpdate();
			
			PreparedStatement st3 = conexao.prepareStatement("DELETE FROM clientes WHERE id_clientes = ?");
			st3.setInt(1, id_cliente);
			st3.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar a conta");
			e.printStackTrace();
		}
	}

	public Clientes selectCliente(String cpf_cliente) {
		Clientes cliente = new Clientes();
		try {
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM clientes WHERE cpf = ?");
			st.setString(1, cpf_cliente);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setId_cliente(rs.getInt("id_clientes"));
				cliente.setIdade(rs.getInt("idade"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSenha(rs.getString("senha"));	
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;

	}
}
