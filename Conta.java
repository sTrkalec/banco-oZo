 
import java.util.Scanner;  // 
public class Conta {
 private       
	float Valor; 
 protected    
    String Nome; 
    String Conta;
	
 public int ExibirMenu() { 
   Scanner ler = new Scanner(System.in);
   System.out.println(" \n\n\n");
   System.out.println(" ******* Conta Bancaria ********");
   System.out.println(" 1) Consultar o Saldo");
   System.out.println(" 2) Depositar");
   System.out.println(" 3) Sacar");
   System.out.println(" 4) Sair");
   System.out.print(" Digite a opcao desejada: ");
   return ler.nextInt();
 }

 public void ConsultarSaldo() {  // Implementacao do metodo ConsultarSaldo() 
   System.out.println(" \n\n\n");
   System.out.println(" **** SALDO DA CONTA ****");
   System.out.println(" Nome do correntista: " + Nome);
   System.out.println(" Numero da conta: " + Conta);
   System.out.println(" Valor do Saldo: " + Valor);
   System.out.println(" ************************ \n\n\n");
 }

 public boolean Depositar(float Quanto) {
   if (Quanto <= 0) // Estamos validando NOVAMENTE se valor informado e positivo
     return(false);   // A funcao retorna falso caso receba um valor invalido
   else {
     Valor = Valor + Quanto;  // Incrementamos o valor do saldo
     return(true);            // Retornamos verdadeiro para quem nos chamou
   }
 }

 public boolean Sacar(float Quanto) {
   if (Quanto <= 0) // Estamos validando NOVAMENTE se valor informado e positivo
     return(false); // A funcao retorna falso caso receba um valor invalido
   else if (Quanto > Valor) {  // Validamos se ha saldo suficiente na conta
	System.out.println("\n Saldo insuficiente!");
	return(false);
   }
   else {
     Valor = Valor - Quanto;  // Decrementamos o valor do saldo
     return(true);            // Retornamos verdadeiro para quem nos chamou
   }
 }

 public boolean AbrirConta(String Cliente, String numConta, float Quanto) {
   if (Quanto < 0) {
   	  System.out.println(" O saldo inicial da conta nao pode ser negativo!");
	  return false;
   }
   else {
       Nome = Cliente;
	  Valor = Quanto;
	  Conta = numConta;
	  return true;
   }
 }
// Na linha a seguir estamos criando o metodo principal da classe Contas  
public static void main(String[] args) {
 // Na linha abaixo declaramos o objeto "ler" da classe Scanner para entrada de dados
 Scanner ler = new Scanner(System.in);
 int resposta;
 float valor;
 System.out.print(" Informe o nome do correntista: ");
 String NomeCorrentista = ler.nextLine();
 System.out.print(" Informe o numero da conta: ");
 String NumeroConta = ler.nextLine();
 System.out.print(" Informe o valor de abertura: ");
 float vlrSaldo = ler.nextFloat();
 
 
   System.out.println(" ******* Escolha o Tipo da Conta ********");
   System.out.println(" 1) Corrente");
   System.out.println(" 2) Poupanca");
   System.out.println(" 3) Salario");
   System.out.println(" 4) Sair");
   System.out.print(" Digite a opcao desejada: ");
   resposta = ler.nextInt(); 
   
 Conta minhaConta = null; 
 if (resposta == 1)
   minhaConta = new Conta();
 else if (resposta == 2)
   minhaConta = new Poupanca();
 else if(resposta == 3 ) {
	 minhaConta = new Salario();
 }

 minhaConta.AbrirConta(NomeCorrentista, NumeroConta, vlrSaldo);
 do { 
   resposta = minhaConta.ExibirMenu();
   if (resposta == 1) {
     minhaConta.ConsultarSaldo();
   }  
   else if (resposta == 2) {
     System.out.printf(" Informe o valor a ser depositado: ");
     valor = ler.nextFloat();
     if (minhaConta.Depositar(valor))
       System.out.printf("\n Deposito de " + valor + " realizado com sucesso!");
     else
       System.out.printf("\n Erro ao fazer o deposito!");
   }  
   else if (resposta == 3) {
     System.out.printf(" Informe o valor do saque: ");
     valor = ler.nextFloat();
     if (minhaConta.Sacar(valor)) {
       System.out.printf("\n Saque de " + valor + " realizado com sucesso!");
       //Valor = 1000;
     }
     else
       System.out.println(" O Saque nao foi realizado!");
   }  
   else {
     System.out.println("\n\n Obrigado por usar o nosso banco!!! \n\n");
   }
 }  // Fechando o comando de repeticao "do"
while (resposta < 4);

}  

}


class Poupanca extends Conta {
private
 float vlrLimiteDia = 300;
 float vlrTotalSaqueDia = 0;
 
	protected boolean aindaPodeSacar(float vlrSaque) {
	 return  (vlrSaque + vlrTotalSaqueDia <= vlrLimiteDia);
	}

	 public boolean Sacar(float Quanto) {
	   if (aindaPodeSacar(Quanto)) {
	     super.Sacar(Quanto);
	     vlrTotalSaqueDia = vlrTotalSaqueDia + Quanto;
	     return true;
	   }
	   else {
	     System.out.println(" Limite de saque por dia excedido!");
	     return false;
	   }
	 }
}

class Salario extends Poupanca {
	
	private 
		int limiteDepositoMes = 1;
		int totalDepositosRealizados = 0;
	
 
 
	protected boolean aindaPodeDepositar() {
	 return  (totalDepositosRealizados < limiteDepositoMes);
	}

	 public boolean Depositar(float Quanto) {
	   if (aindaPodeDepositar()) {
	     super.Depositar(Quanto);
	     totalDepositosRealizados += 1;
	     return true;
	   }
	   else {
	     System.out.println(" Limite de Depositos por mes excedido!");
	     return false;
	   }
	 }
}
;

