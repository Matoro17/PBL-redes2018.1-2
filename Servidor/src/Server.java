import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
	private static int portaserver = 12345;
	static public LinkedList<Sala> salas;

	


	public static void main(String[] args) {
		salas  = new LinkedList<>();
		salas.add(new Sala(0,"Default","235.0.0.1",10,4453));
		try {
			ServerSocket serversocket = new ServerSocket(portaserver);
			 System.out.println("Servidor ativo");
			 System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());

			while(true){
				Socket socket = serversocket.accept();
				new Thread(new Client(socket,salas)).start();
				for(int i=0; i<salas.size();i++) {
					Sala temp = salas.get(i);
					new Thread(new Listener(temp.getIp(),temp.getPorta(),temp)).start();
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}



class Listener implements Runnable {
	protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private String ip;
    private int port;
    private Sala sala;
    
	public Listener(String ip2, int port2, Sala sala) {
		this.ip = ip2;
    	this.port = port2;
    	this.sala = sala;
	}
	
	public void multicast(String multicastMessage) throws IOException {
		DatagramSocket socket;
	    InetAddress group;
	    byte[] buf;
		        socket = new DatagramSocket();
		        group = InetAddress.getByName(ip);
		        buf = multicastMessage.getBytes();
		 
		        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
		        socket.send(packet);
		        socket.close();
	}
	
	@Override
	public void run() {
		try{
			
	        socket = new MulticastSocket(this.port);
	        InetAddress group = InetAddress.getByName(this.ip);
	        socket.joinGroup(group);
	        while (true) {
	        	if(sala.getQuantidade()>1){
	        		multicast("dices/"+ new Dices().randomize().toString());
	        	}
	        	
	            DatagramPacket packet = new DatagramPacket(buf, buf.length);
	            socket.receive(packet);
	            String received = new String(packet.getData(), 0, packet.getLength());
	            
	            String[] recebido = received.split("/");
	            if ("end".equals(recebido[0])) {
	            	break;
	            }
	            else if("player".equals(recebido[0])){
	            	sala.setJogadores(recebido[1]);
	            }
	            
	        }
	        socket.leaveGroup(group);
	        socket.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

/*
 * 
 *///Padrão de solicitação do cliente: tipo da solicitação, string da soliciação
// 1 - Escolher sala, 2 - Criar sala
class Client implements Runnable{
	private Socket socket;
	private LinkedList<Sala> salas;
	public Client(Socket sock, LinkedList<Sala> salas){
		this.socket = sock;
		this.salas = salas;
	}
	
	@Override
	public void run() {
		try{
			DataInputStream input = new DataInputStream(this.socket.getInputStream());
			DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
			String total = "";
			for(int i=0; i<salas.size();i++) {
				Sala temp = salas.get(i);
				total += temp.getTudo()+"/";
			}
			output.writeUTF(total);
			
			String recebido = input.readUTF();
			String[] receb = recebido.split(",");
			
			if (receb[0].equals("1")) {
				int into = (Integer.parseInt(receb[1]));
				
				for(int i=0; i<salas.size();i++) {
					Sala temp = salas.get(i);
					if (temp.getId() == into) {
						output.writeUTF(temp.getTudo());
						break;
					}
				}	
			}
			
		}catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}

	
}