package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Conta> contas= new ArrayList<>();
		Conta pp,cc, aux2, aux3;
		Clientes aux;
		int senha=0,sin, sin2;
		double valor;
		String nome;
		int op, op2;
		// a interface do usu�rio para cada conta e suas opera��es
		do {
			Menu();
			op = in.nextInt();			
			switch (op){
				case 1:
					//cria cliente com senha com as informa��es informadas pelo usu�rio
					aux = new Clientes();
					System.out.print("Digite o nome do Titular: ");
					nome = in.next();
					aux.setNome(nome);
					do {
					System.out.println("Deseja criar conta corrente, poupan�a?");
					System.out.println("(1-conta corrente/2-poupan�a)");
					op2 = in.nextInt();
					//verifica o desejo do cliente em rela��o qual tipo da conta
					switch(op2) {
					case 1:
						cc = new Cc(aux);
						contas.add(cc);
						System.out.println("Senha da conta: "+senha);
						senha++;
						break;
					case 2: 						
						pp =  new Poupanca(aux);
						contas.add(pp);
						System.out.println("Senha da conta: "+senha);
						senha++;
						break;
					default:
						System.out.println("op��o inexistente");
						break;
					}
					}while(op2>2&&op2<1);					
				break;
				case 2:			
					//Op��o depositar a partir da senha e valor
					System.out.println("Digite a senha da conta: ");
					sin = in.nextInt();
					System.out.println("Digite o valor: ");
					valor=in.nextDouble();
					if(verifyAccount(sin,contas)) {
					aux2 = contas.get(sin);
					aux2.depositar(valor);
					contas.set(sin, aux2);
					}else {
						System.out.println("Conta inexistente");
					}
				break;
				case 3:
					//Op��o Transferira partir da senha do titular e de que recebe, al�m do valor
					System.out.println("Digite o senha da conta do titular: ");
					sin = in.nextInt();
					System.out.println("Digite o senha da conta de quem recebe: ");
					sin2 = in.nextInt();
					System.out.println("Digite o valor: ");
					valor=in.nextDouble();
					if(verifyAccount(sin,contas)&&verifyAccount(sin2,contas)) {
					aux2 = contas.get(sin);
					aux3 = contas.get(sin2);
					aux2.transferir(valor, aux3);
					contas.set(sin2, aux3);
					contas.set(sin, aux2);
					}else {
						System.out.println("Conta inexistente");
					}
					break;
				case 4:
					//Op��o Sacar a partir da senha e valor
					System.out.println("Digite a senha da conta: ");
					sin = in.nextInt();
					System.out.println("Digite o valor: ");
					valor=in.nextDouble();
					if(verifyAccount(sin,contas)) {
					aux2 = contas.get(sin);
					aux2.sacar(valor);
					contas.set(sin, aux2);
					}else {
						System.out.println("Conta inexistente");
					}
					break;
				case 5:
					//Op��o extrato a partir da senha
					System.out.println("Digite a senha da conta: ");
					sin = in.nextInt();
					if(verifyAccount(sin,contas)) {
						aux2 = contas.get(sin);
						aux2.imprimirExtrato();
					}else {
						System.out.println("Conta inexistente");
					}
					break;		
				default:
					System.out.println("op��o inexistente");
			}
		}while(true);
	}
	//Menu principal
	public static void Menu() {
		System.out.println("Menu principal do Banco ");
		System.out.println("Escolha a op��o: ");
		System.out.println("1 - cadastrar: ");
		System.out.println("2 - depositos: ");
		System.out.println("3 - transferencia: ");
		System.out.println("4 - saques");
		System.out.println("5 - tirar extrato: ");
	}
	//tratamento de exce��o na confirma��o da senha
	public static boolean verifyAccount(int sin, List<Conta> contas) {
		boolean ver = true;
		try {
		contas.get(sin);
		}catch(IndexOutOfBoundsException e) {
			ver=false;
		}
		return ver;
	}

}
