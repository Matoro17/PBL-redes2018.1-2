package model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDP {

    private static String jogador = null;
    private static String endereco = null;
    private static Thread receberPalavras = null;
    private static Thread enviarNome = null;
    private static StringBuilder dados = new StringBuilder();

    public static synchronized void denifirJogador() {
        try {
            jogador = "Jogador" + InetAddress.getLocalHost().toString().hashCode()/10000;
            jogador = jogador.replace("-", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized boolean existeJogador() {
        return jogador != null;
    }

    public static synchronized String nomeJogador() {
        return jogador;
    }

    public static synchronized void enviarNome() {
        if (enviarNome == null) {
            enviarNome = new Thread(() -> {
                try {
                    while (true) {
                        if (existeJogador() && existeEndereco())
                            enviarMensagem(("jogador," + nomeJogador()).getBytes());

                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            enviarNome.setDaemon(true);
            enviarNome.start();
        }
    }

    public static synchronized boolean existeEndereco() {
        return endereco != null;
    }

    public static synchronized void inserirEndereco(String end) {
        endereco = end;
    }

    public static synchronized void enviarMensagem(byte[] msg) {
        if (existeEndereco()) {
            try (DatagramSocket socket = new DatagramSocket()) {
                DatagramPacket packet = new DatagramPacket(msg, msg.length, InetAddress.getByName(endereco), 9798);
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * Metodo principal do multicast, onde pela criação de Thread é escutado a porta 9798, e a chacagem se os endereços existem em multicast
     */
    public synchronized static void iniciarMulticast() {
        if (receberPalavras == null) {
            receberPalavras = new Thread(() -> {
                try (MulticastSocket socket = new MulticastSocket(9798)){
                    InetAddress grupo = null;
                    byte[] buffer = new byte[512];

                    while (true) {
                        if (existeEndereco()) {
                            if (grupo == null) {
                                try {
                                	System.out.println(endereco);
                                    grupo = InetAddress.getByName(endereco);
                                    socket.joinGroup(grupo);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                                socket.receive(packet);
                                dados.append(new String(packet.getData(), 0, packet.getLength())).append(";");

                                System.out.println(dados);
                            }
                        } else if (grupo != null) {
                            try {
                                socket.leaveGroup(grupo);
                                grupo = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                       e.printStackTrace();
                }
            });

            receberPalavras.setDaemon(true);
            receberPalavras.start();
        }
    }

    public synchronized static boolean existeDados() {
        return dados.length() > 0;
    }
    /*
     * Leitura de dados do Multicast, trabalhando com buffer para evitar redundância de informações
     */
    public synchronized static String lerDados() {
        if (existeDados()) {
            String temp = dados.toString();
            dados.delete(0, dados.length());
            return temp;
        }

        return null;
    }

}
