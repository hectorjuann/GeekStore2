package geekStore;

import java.util.Scanner;

import conexaoBD.Conexao;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean parar = false;
		int menu1 = 0;
		int menu2 = 0;
		int respInt = 0;
		int id_cliente = 0;
		int id_endereco = 0;
		int logado = 0;
		Clientes auxiliar = new Clientes();
		Conexao conexao = new Conexao();

		System.out.println("############### BEM VINDO A GEEKSTORE ###############");
		// MENU

		do {

			System.out.println("\n");
			System.out.println("-------------- MENU --------------");
			System.out.println("[1] Login\n[2] Cadastrar\n[3] Sair");
			System.out.println("----------------------------------");
			menu1 = input.nextInt();

			switch (menu1) {
			case 1:
				
				do {
					System.out.println("----------------------------");
					System.out.println("Digite seu CPF:");
					String inputCpf = input.next();

					System.out.println("Digite sua SENHA:");
					String inputSenha = input.next();
					logado = conexao.login(inputCpf, inputSenha);	
					auxiliar = conexao.selectCliente(inputCpf);
					if (logado == 1) {
						parar = true;
						respInt = 1;
					} else if (logado == 2) {
						parar = true;
						break;
					} else if (logado == 3) {
						parar = false;
					}
					
				}while (parar == false);

				if (logado == 2) {
					break;
				}
				
				switch (respInt) {
				case 1:
					do {
						System.out.println("\n");
						System.out.println("-------------- MENU --------------");
						System.out.println("[1] Comprar\n[2] Histórico de Compras\n[3] Deletar Conta\n[4] Sair");
						System.out.println("----------------------------------");
						menu2 = input.nextInt();

						switch(menu2) {
						case 1:
							do {
								Compra compra = new Compra(auxiliar);
								compra.comprar();
								System.out.println("\n");
								System.out.println("-------------- MENU --------------");
								System.out.println("[1] Continuar Comprando\n[2] Sair");
								System.out.println("----------------------------------");
								respInt = input.nextInt();
								if (respInt == 1) {
									parar = false;
								} else if (respInt == 2) {
									parar = true;
								}
							} while(parar == false);
							
						case 2:
							System.out.println("Aqui está o seu histórico:");
							conexao.mostrarHistorico(auxiliar.getId_cliente());
							//auxiliar.mostrarHistorico();
							parar = false;
							break;
						case 3:
							System.out.println("Tem certeza que deseja excluir a conta?");
							System.out.println("1 - SIM\n2 - NÃO");
							int resp = input.nextInt();
							
							if (resp == 1) {
								conexao.deleteConta(auxiliar.getId_cliente());	
								System.out.println("Conta deletada com sucesso");
								parar = true;
								break;
							} else {
								System.out.println("Conta não foi excluida");
							}
							break;
						case 4:
							parar = true;
							break;
						default:
							System.out.println("Escolha uma opção válida");
							parar = false;
							break;
						}
					} while (parar == false);
				} while (parar == false);
				break;
				
			case 2:
				id_cliente = conexao.cadastroClientes();
				id_endereco = conexao.cadastroEndereco(id_cliente);
				conexao.cadastroHistorico(id_cliente);
				System.out.println("Cadastro realizado com sucesso!");
				break;
			case 3:
				System.out.print("Saindo ");
				for (int i = 0; i <= 100; i =i+10) {
					System.out.print(". ");
				}
				System.out.println("Volte sempre!");
				break;
			default:
				System.out.println("Escolha uma opção válida");
			}
		} while (menu1 != 3);
	}
}