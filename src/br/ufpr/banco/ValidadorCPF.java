package br.ufpr.banco;

/**
 * Classe utilitária para validar CPF.
 */
public class ValidadorCPF {

    /**
     * Valida um CPF.
     * 
     * @param cpf o CPF a ser validado
     * @return true se o CPF for válido, false caso contrário
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * pesos[i];
            }
            int primeiroDigitoVerificador = 11 - (soma % 11);
            primeiroDigitoVerificador = (primeiroDigitoVerificador > 9) ? 0 : primeiroDigitoVerificador;

            soma = 0;
            int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * pesos2[i];
            }
            int segundoDigitoVerificador = 11 - (soma % 11);
            segundoDigitoVerificador = (segundoDigitoVerificador > 9) ? 0 : segundoDigitoVerificador;

            return cpf.charAt(9) == Character.forDigit(primeiroDigitoVerificador, 10)
                    && cpf.charAt(10) == Character.forDigit(segundoDigitoVerificador, 10);
        } catch (Exception e) {
            return false;
        }
    }
}
