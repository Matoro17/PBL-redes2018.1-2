package model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class Dicionario {

    private static HashSet<String> dicionario = null;

    public static void carregarPalavras() {
        if (dicionario == null) {
            dicionario = new HashSet<>();

            try {
                Files.lines(Paths.get("palavras.dic")).forEach(palavra -> dicionario.add(palavra));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean validarPalavra(String palavra) {
    	String temp = palavra.toLowerCase();
        return dicionario.contains(temp);
    }

}
