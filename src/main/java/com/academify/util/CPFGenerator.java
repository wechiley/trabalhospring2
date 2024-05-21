package com.academify.util;

import java.util.Random;

public class CPFGenerator {

    private static final Random random = new Random();

    public static String generateCPF() {
        int[] cpf = new int[11];

        // Gerar os 9 primeiros dígitos
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcular o primeiro dígito verificador
        int sm = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            sm += cpf[i] * peso--;
        }
        int r = 11 - (sm % 11);
        cpf[9] = (r == 10 || r == 11) ? 0 : r;

        // Calcular o segundo dígito verificador
        sm = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            sm += cpf[i] * peso--;
        }
        r = 11 - (sm % 11);
        cpf[10] = (r == 10 || r == 11) ? 0 : r;

        // Montar o CPF no formato 000.000.000-00
        StringBuilder cpfFormatted = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (i == 3 || i == 6) {
                cpfFormatted.append('.');
            }
            if (i == 9) {
                cpfFormatted.append('-');
            }
            cpfFormatted.append(cpf[i]);
        }

        return cpfFormatted.toString();
    }

    public static void main(String[] args) {
        System.out.println("CPF Gerado: " + generateCPF());
    }
}
