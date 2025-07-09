package com.seadesafio.clienteservice.utils;

public class MascaraUtils {

    public static String formatarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String formatarCep(String cep) {
        if (cep == null || cep.length() != 8) return cep;
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

    public static String formatarTelefone(String telefone) {
        if (telefone == null) return null;
        if (telefone.length() == 10) {
            return telefone.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else if (telefone.length() == 11) {
            return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        }
        return telefone;
    }
}
