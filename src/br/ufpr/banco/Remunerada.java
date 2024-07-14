package br.ufpr.banco;

/**
 * Interface que define os métodos para contas remuneradas.
 */
public interface Remunerada {
    /**
     * Aplica correção monetária na conta.
     * 
     * @param taxa a taxa de correção a ser aplicada
     */
    void aplicarCorrecao(double taxa);
}
