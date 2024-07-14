package br.ufpr.banco;

/**
 * Interface que define os métodos para operações com PIX.
 */
public interface Pix {
    /**
     * Cadastra uma chave PIX.
     * 
     * @param cpf o CPF do correntista
     */
    void cadastrarPix(String cpf);

    /**
     * Efetua uma transação PIX.
     * 
     * @param chavePixDestino a chave PIX de destino
     * @param valor o valor a ser transferido
     */
    void efetuarPix(String chavePixDestino, double valor);

    /**
     * Recebe uma transação PIX.
     * 
     * @param cpfOrigem o CPF de origem
     * @param valor o valor recebido
     */
    void receberPix(String cpfOrigem, double valor);
}
