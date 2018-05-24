package model;

import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TCP {

    private static String endereco = null;
    private static Socket socket = null;
    private static Scanner entrada = null;
    private static PrintWriter saida = null;
    private static Thread enviarMensagem = null;
    private static StringBuilder mensagem = new StringBuilder();
    private static StringBuilder resposta = new StringBuilder();

    public synchronized static boolean estaConectado() {
        return socket != null && entrada != null && saida != null;
    }

    public synchronized static void iniciarConexao() {
        if (endereco == null) {
            try {
                endereco = Files.lines(Paths.get("endereco.txt")).findFirst().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (enviarMensagem == null)
            iniciarServico();

        if (!estaConectado()) {
            try {
                socket = new Socket(endereco, 9799);
                entrada = new Scanner(socket.getInputStream());
                saida = new PrintWriter(socket.getOutputStream(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void encerrarConecao() {
        if(estaConectado()) {
            try {
                entrada.close(); entrada = null;
                saida.close(); saida = null;
                socket.close(); socket = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void iniciarServico() {
        if (enviarMensagem == null) {
            enviarMensagem = new Thread(() -> {
                try {
                    while (true) {
                        if (estaConectado() && existeMensagem() && !existeResposta()) {
                            saida.println(mensagem);
                            while (!entrada.hasNextLine());
                            resposta.append(entrada.nextLine());
                            mensagem.delete(0, mensagem.length());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            enviarMensagem.setDaemon(true);
            enviarMensagem.start();
        }
    }

    private synchronized static boolean existeMensagem() {
        return mensagem.length() > 0;
    }

    public synchronized static void enviarMensagem(String msg) {
        if (!existeMensagem())
            mensagem.append(msg);
    }

    public synchronized static boolean existeResposta() {
        return resposta.length() > 0;
    }

    public synchronized static String lerResposta() {
        if (existeResposta()) {
            String temp = resposta.toString();
            resposta.delete(0, resposta.length());
            return temp;
        }

        return null;
    }

}
