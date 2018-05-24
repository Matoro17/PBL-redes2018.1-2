package controller;

import model.Letras;

import java.util.Arrays;
import java.util.Hashtable;

public class Requisicao {

    private static Hashtable<String, String[]> sessoes = new Hashtable<>();
    private static int endereco = 0;

    public synchronized static void iniciarSessoes() {
        for (int i = 0; i < 9; i++)
            sessoes.put("Sessão " + (i+1), new String[]{Letras.getLetras(), "230.0.1." + i});
    }

    public synchronized static String tratarRequisicao(String mensagem) {
        if (mensagem.equals("listar")) {
            StringBuilder resposta = new StringBuilder();
            sessoes.keySet().stream().sorted().forEach(sessao -> resposta.append(sessao).append(";"));
            return resposta.toString();
        }

        if (mensagem.startsWith("info")) {
            String[] info = mensagem.split(",");

            if (info.length > 0 && sessoes.containsKey(info[1])) {
                String[] temp = sessoes.get(info[1]);
                return temp[0] + ";" + temp[1];
            }
        }

        return " ";
    }

}
