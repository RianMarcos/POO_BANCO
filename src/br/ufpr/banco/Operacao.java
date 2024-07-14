package br.ufpr.banco;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que representa uma operação bancária.
 */
public class Operacao {
    private Date data;
    private double valor;
    private String tipo;

    /**
     * Construtor da classe Operacao.
     * 
     * @param data a data da operação
     * @param valor o valor da operação
     * @param tipo o tipo da operação (ex.: "Deposito", "Saque", "PIX In")
     */
    public Operacao(Date data, double valor, String tipo) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    /**
     * Retorna a data da operação.
     * 
     * @return a data da operação
     */
    public Date getData() {
        return data;
    }

    /**
     * Retorna o valor da operação.
     * 
     * @return o valor da operação
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna o tipo da operação.
     * 
     * @return o tipo da operação
     */
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(data) + " - " + tipo + ": " + valor;
    }
}
