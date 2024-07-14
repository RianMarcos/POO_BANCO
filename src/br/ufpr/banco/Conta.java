package br.ufpr.banco;

import java.util.ArrayList;

/**
 * Classe abstrata que representa uma conta bancária.
 */
public abstract class Conta {
    protected String numeroConta;
    protected String nomeCorrentista;
    protected String cpfCorrentista;
    protected ArrayList<Operacao> operacoes;

    /**
     * Construtor da classe Conta.
     * 
     * @param numeroConta o número da conta
     * @param nomeCorrentista o nome do correntista
     * @param cpfCorrentista o CPF do correntista
     */
    public Conta(String numeroConta, String nomeCorrentista, String cpfCorrentista) {
        this.numeroConta = numeroConta;
        this.nomeCorrentista = nomeCorrentista;
        this.cpfCorrentista = cpfCorrentista;
        this.operacoes = new ArrayList<>();
    }

    /**
     * Método abstrato para depositar um valor na conta.
     * 
     * @param valor o valor a ser depositado
     */
    public abstract void depositar(double valor);

    /**
     * Método abstrato para sacar um valor da conta.
     * 
     * @param valor o valor a ser sacado
     */
    public abstract void sacar(double valor);

    /**
     * Adiciona uma operação à lista de operações da conta.
     * 
     * @param operacao a operação a ser adicionada
     */
    public void addOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }

    /**
     * Calcula o saldo atual da conta.
     * 
     * @return o saldo atual da conta
     */
    public double calcularSaldo() {
        double saldo = 0.0;
        for (Operacao operacao : operacoes) {
            saldo += operacao.getTipo().equals("Deposito") || operacao.getTipo().equals("PIX In") || operacao.getTipo().equals("Correcao") ? operacao.getValor() : -operacao.getValor();
        }
        return saldo;
    }

    /**
     * Imprime o extrato da conta.
     */
    public void extrato() {
        double saldo = 0.0;
        System.out.println("Extrato da Conta: " + numeroConta);
        System.out.println("Nome do Correntista: " + nomeCorrentista);
        System.out.println("CPF do Correntista: " + cpfCorrentista);
        System.out.println("-------------------------------------------------");
        for (Operacao operacao : operacoes) {
            System.out.println(operacao);
            saldo += operacao.getTipo().equals("Deposito") || operacao.getTipo().equals("PIX In") || operacao.getTipo().equals("Correcao") ? operacao.getValor() : -operacao.getValor();
            System.out.println("Saldo após operação: " + saldo);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Saldo Atual: " + saldo);
    }

    /**
     * Retorna o número da conta.
     * 
     * @return o número da conta
     */
    public String getNumeroConta() {
        return numeroConta;
    }
}
