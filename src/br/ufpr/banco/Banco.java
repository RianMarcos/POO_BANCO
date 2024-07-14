package br.ufpr.banco;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal que gerencia as operações do banco.
 */
public class Banco {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<String> cpfsPix = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Criar contas e operações pré-cadastradas para demonstração
        ContaCorrente cc1 = new ContaCorrente("123", "Joao", "922.683.310-91");
        ContaPoupanca cp1 = new ContaPoupanca("456", "Maria", "361.961.800-37");
        ContaCorrente cc2 = new ContaCorrente("789", "Pedro", "724.078.490-86");
        ContaPoupanca cp2 = new ContaPoupanca("012", "Ana", "884.904.790-89");
        ContaCorrente cc3 = new ContaCorrente("345", "Lucas", "828.621.730-61");

        contas.add(cc1);
        contas.add(cp1);
        contas.add(cc2);
        contas.add(cp2);
        contas.add(cc3);

        // Realizar operações iniciais
        cc1.depositar(1000);
        cp1.depositar(2000);
        cc2.depositar(1500);
        cp2.depositar(2500);
        cc3.depositar(3000);

        cc1.sacar(200);
        cp1.sacar(500);
        cc2.sacar(300);
        cp2.sacar(700);
        cc3.sacar(1000);

        cc1.cadastrarPix(cc1.cpfCorrentista);
        cp1.aplicarCorrecao(1.5);
        cc2.cadastrarPix(cc2.cpfCorrentista);
        cp2.aplicarCorrecao(2.0);
        cc3.cadastrarPix(cc3.cpfCorrentista);
        System.out.println("Bem-vindo ao Sistema Bancário");
        
        menu();
    }

    /**
     * Exibe o menu de opções e executa as operações escolhidas pelo usuário.
     */
    public static void menu() {
        while (true) {
        	System.out.println("");
        	System.out.println("--------------------------------------");
        	System.out.println("Selecione a opção desejada:");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Efetuar Depósito");
            System.out.println("4. Efetuar Saque");
            System.out.println("5. Aplicar Correção");
            System.out.println("6. Cadastrar PIX");
            System.out.println("7. Efetuar PIX");
            System.out.println("8. Consultar Extrato");
            System.out.println("9. Consultar Saldo");
            System.out.println("10. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarContaCorrente();
                    break;
                case 2:
                    criarContaPoupanca();
                    break;
                case 3:
                    efetuarDeposito();
                    break;
                case 4:
                    efetuarSaque();
                    break;
                case 5:
                    aplicarCorrecao();
                    break;
                case 6:
                    cadastrarPix();
                    break;
                case 7:
                    efetuarPix();
                    break;
                case 8:
                    consultarExtrato();
                    break;
                case 9:
                    consultarSaldo();
                    break;
                case 10:
                	System.out.println("Desconectado");
                    System.exit(0);
                    
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Cria uma nova conta corrente.
     */
    public static void criarContaCorrente() {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Nome do correntista: ");
        String nomeCorrentista = scanner.nextLine();
        System.out.print("CPF do correntista: ");
        String cpfCorrentista = scanner.nextLine();

        if (!ValidadorCPF.validarCPF(cpfCorrentista)) {
            System.out.println("CPF inválido. Conta não foi criada.");
            return;
        }

        ContaCorrente conta = new ContaCorrente(numeroConta, nomeCorrentista, cpfCorrentista);
        contas.add(conta);
        System.out.println("Conta Corrente criada com sucesso!");
    }

    /**
     * Cria uma nova conta poupança.
     */
    public static void criarContaPoupanca() {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Nome do correntista: ");
        String nomeCorrentista = scanner.nextLine();
        System.out.print("CPF do correntista: ");
        String cpfCorrentista = scanner.nextLine();

        if (!ValidadorCPF.validarCPF(cpfCorrentista)) {
            System.out.println("CPF inválido. Conta não foi criada.");
            return;
        }

        ContaPoupanca conta = new ContaPoupanca(numeroConta, nomeCorrentista, cpfCorrentista);
        contas.add(conta);
        System.out.println("Conta Poupança criada com sucesso!");
    }

    /**
     * Efetua um depósito em uma conta.
     */
    public static void efetuarDeposito() {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                conta.depositar(valor);
                System.out.println("Depósito efetuado com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Efetua um saque de uma conta.
     */
    public static void efetuarSaque() {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                conta.sacar(valor);
                System.out.println("Saque efetuado com sucesso!");
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Aplica uma correção em todas as contas poupança.
     */
    public static void aplicarCorrecao() {
        System.out.print("Taxa de correção (%): ");
        double taxa = scanner.nextDouble();
        scanner.nextLine();

        for (Conta conta : contas) {
            if (conta instanceof ContaPoupanca) {
                ((ContaPoupanca) conta).aplicarCorrecao(taxa);
            }
        }
        System.out.println("Correção aplicada com sucesso!");
    }

    /**
     * Cadastra uma chave PIX para uma conta corrente.
     */
    public static void cadastrarPix() {
        System.out.print("CPF do correntista: ");
        String cpf = scanner.nextLine();

        if (!ValidadorCPF.validarCPF(cpf)) {
            System.out.println("CPF inválido. PIX não pode ser cadastrado.");
            return;
        }

        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente && conta.cpfCorrentista.equals(cpf)) {
                ((ContaCorrente) conta).cadastrarPix(cpf);
                cpfsPix.add(cpf);
                System.out.println("PIX cadastrado com sucesso!");
                return;
            }
        }
        System.out.println("Correntista não encontrado.");
    }

    /**
     * Efetua uma transação PIX.
     */
    public static void efetuarPix() {
        System.out.print("Chave PIX de origem: ");
        String chavePixOrigem = scanner.nextLine();
        System.out.print("Chave PIX de destino: ");
        String chavePixDestino = scanner.nextLine();
        System.out.print("Valor do PIX: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta contaOrigem = getContaByChavePix(chavePixOrigem);
        if (contaOrigem == null || !(contaOrigem instanceof ContaCorrente)) {
            System.out.println("Chave PIX de origem não cadastrada no sistema.");
            return;
        }

        ((ContaCorrente) contaOrigem).efetuarPix(chavePixDestino, valor);
    }

    /**
     * Consulta o extrato de uma conta.
     */
    public static void consultarExtrato() {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();

        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                conta.extrato();
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Consulta o saldo de uma conta.
     */
    public static void consultarSaldo() {
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();

        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                System.out.println("Saldo atual: " + conta.calcularSaldo());
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    /**
     * Retorna a conta associada a uma chave PIX.
     * 
     * @param chavePix a chave PIX
     * @return a conta associada à chave PIX
     */
    public static Conta getContaByChavePix(String chavePix) {
        for (Conta conta : contas) {
            if (conta instanceof ContaCorrente) {
                ContaCorrente contaCorrente = (ContaCorrente) conta;
                if (chavePix.equals(contaCorrente.getChavePix())) {
                    return contaCorrente;
                }
            }
        }
        return null;
    }

    /**
     * Retorna a conta associada a um CPF.
     * 
     * @param cpf o CPF
     * @return a conta associada ao CPF
     */
    public static Conta getContaByCpf(String cpf) {
        for (Conta conta : contas) {
            if (conta.cpfCorrentista.equals(cpf)) {
                return conta;
            }
        }
        return null;
    }
}
