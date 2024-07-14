package br.ufpr.banco;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa uma conta corrente.
 */
public class ContaCorrente extends Conta implements Pix {
    private ArrayList<String> pixCadastrados;
    private Map<String, String> chavesPix; // Mapa para armazenar chaves PIX associadas aos CPFs

    /**
     * Construtor da classe ContaCorrente.
     * 
     * @param numeroConta o número da conta
     * @param nomeCorrentista o nome do correntista
     * @param cpfCorrentista o CPF do correntista
     */
    public ContaCorrente(String numeroConta, String nomeCorrentista, String cpfCorrentista) {
        super(numeroConta, nomeCorrentista, cpfCorrentista);
        this.pixCadastrados = new ArrayList<>();
        this.chavesPix = new HashMap<>();
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
    public void cadastrarPix(String cpf) {
        if (!pixCadastrados.contains(cpf)) {
            pixCadastrados.add(cpf);
            chavesPix.put(cpf, cpf); // Associa a chave PIX ao CPF automaticamente
        }
    }

    @Override
    public void efetuarPix(String chavePixDestino, double valor) {
        // Verificar se a conta possui saldo suficiente
        double saldo = calcularSaldo();
        if (saldo < valor) {
            System.out.println("Saldo insuficiente para realizar o PIX.");
            return;
        }

        // Verificar se a chave PIX de destino está cadastrada no sistema
        Conta contaDestino = Banco.getContaByChavePix(chavePixDestino);
        if (contaDestino == null || !(contaDestino instanceof ContaCorrente)) {
            System.out.println("Chave PIX de destino não cadastrada no sistema.");
            return;
        }

        // Realizar a operação de PIX
        this.sacar(valor);
        ((ContaCorrente) contaDestino).receberPix(this.cpfCorrentista, valor);
        System.out.println("PIX efetuado com sucesso!");
    }

    @Override
    public void receberPix(String cpfOrigem, double valor) {
        Operacao operacao = new Operacao(new Date(), valor, "PIX In");
        addOperacao(operacao);
    }

    /**
     * Retorna a chave PIX associada ao CPF do correntista.
     * 
     * @return a chave PIX associada ao CPF do correntista
     */
    public String getChavePix() {
        return chavesPix.get(cpfCorrentista);
    }
}
