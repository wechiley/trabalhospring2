package com.academify.util;



import java.util.Random;

public class CNPJGenerator {

    private static final Random random = new Random();

    public static String generateCNPJ() {
        int[] cnpj = new int[14];

        // Gerar os 12 primeiros dígitos
        for (int i = 0; i < 12; i++) {
            cnpj[i] = random.nextInt(10);
        }

        // Calcular o primeiro dígito verificador
        cnpj[12] = calculateDigit(cnpj, 12);

        // Calcular o segundo dígito verificador
        cnpj[13] = calculateDigit(cnpj, 13);

        // Montar o CNPJ no formato 00.000.000/0000-00
        StringBuilder cnpjFormatted = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            if (i == 2 || i == 5) {
                cnpjFormatted.append('.');
            }
            if (i == 8) {
                cnpjFormatted.append('/');
            }
            if (i == 12) {
                cnpjFormatted.append('-');
            }
            cnpjFormatted.append(cnpj[i]);
        }

        return cnpjFormatted.toString();
    }

    private static int calculateDigit(int[] cnpj, int position) {
        int sum = 0;
        int weight = (position == 12) ? 2 : 3;
        for (int i = position - 1; i >= 0; i--) {
            sum += cnpj[i] * weight;
            weight = (weight == 9) ? 2 : weight + 1;
        }
        int result = sum % 11;
        return (result < 2) ? 0 : 11 - result;
    }

    public static void main(String[] args) {
        System.out.println("CNPJ Gerado: " + generateCNPJ());
    }
}
