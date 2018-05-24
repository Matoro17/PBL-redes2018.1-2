package model;

import controller.Requisicao;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP extends Thread {

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(9799)) {
            while(true) {
                Conexao conexao = new Conexao(server.accept());
                conexao.setDaemon(true);
                conexao.start();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public class Conexao extends Thread {

        private Socket socket;

        public Conexao(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            try (Scanner entrada = new Scanner(socket.getInputStream());
                 PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)){

                while(entrada.hasNextLine())
                    saida.println(Requisicao.tratarRequisicao(entrada.nextLine()));

                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        }

    }

}
