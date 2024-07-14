package br.ufpr.banco;

import java.util.Date;

/**
 * Classe que representa uma conta poupança.
 */
public class ContaPoupanca extends Conta implements Remunerada {
    /**
     * Construtor da classe ContaPoupanca.
     * 
     * @param numeroConta o número da conta
     * @param nomeCorrentista o nome do correntista
     * @param cpfCorrentista o CPF do correntista
     */
    public ContaPoupanca(String numeroConta, String nomeCorrentista, String cpfCorrentista) {
        super(numeroConta, nomeCorrentista, cpfCorrentista);
    }

    @Override
    public void depositar(double valor) {
        Operacao operacao = new Operacao(new Date(), valor, "Deposito");
        addOperacao(operacao);
    }

    @Override
    public void sacar(double valor) {
        Operacao operacao = new Operacao(new Date(), valor, "Saque");
        addOperacao(operacao);
    }

    @Override
    public void aplicarCorrecao(double taxa) {
        double saldoAtual = calcularSaldo();
        double valorCorrecao = saldoAtual * taxa / 100;
        Operacao operacao = new Operacao(new Date(), valorCorrecao, "Correcao");
        addOperacao(operacao);
    }
}
