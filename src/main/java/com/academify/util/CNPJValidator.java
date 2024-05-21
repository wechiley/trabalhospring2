package com.academify.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPJValidator {

    public static boolean isValidCNPJ(String cnpj) {
        // Valida CNPJ no formato 00.000.000/0000-00
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
        Matcher matcher = pattern.matcher(cnpj);
        if (!matcher.matches()) {
            return false;
        }

        // Validação de dígitos do CNPJ
        cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1)) dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1)) dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) return true;
            else return false;
        } catch (Exception erro) {
            return false;
        }
    }
}
