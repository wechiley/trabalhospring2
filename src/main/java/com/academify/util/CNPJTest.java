package com.academify.util;

public class CNPJTest {
    public static void main(String[] args) {
        // Gerar um novo CNPJ
        String novoCNPJ = CNPJGenerator.generateCNPJ();
        System.out.println("Novo CNPJ Gerado: " + novoCNPJ);

        // Validar o CNPJ gerado
        boolean isValid = CNPJValidator.isValidCNPJ(novoCNPJ);
        System.out.println("O CNPJ gerado é válido? " + isValid);
    }
}
