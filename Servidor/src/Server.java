import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
	private static int portaserver = 12345;
	static public LinkedList<Sala> salas = new LinkedList<>();




	public static void main(String[] args) {
		salas.add(new Sala(0,"Default","235.0.0.1",10,4453));
		try {
			ServerSocket serversocket = new ServerSocket(portaserver);
			 System.out.println("Servidor ativo");
			 System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());

			while(true){
				Socket socket = serversocket.accept();
				new Thread(new Client(socket)).start();
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}


/*
 * 
 *///Padrão de solicitação do cliente: tipo da solicitação, string da soliciação
// 1 - Escolher sala, 2 - Criar sala
class Client implements Runnable{
	private Socket socket;
	
	public Client(Socket sock){
		this.socket = sock;
	}
	
	@Override
	public void run() {
		try{
			DataInputStream input = new DataInputStream(this.socket.getInputStream());
			DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
			
			String total = "";
			for(int i=0; i<Server.salas.size();i++) {
				Sala temp = Server.salas.get(i);
				total += temp.getTudo()+"/";
			}
			output.writeUTF(total);
			
			String recebido = input.readUTF();
			String[] receb = recebido.split(",");
			
			if (receb[0].equals("1")) {
				int into = (Integer.parseInt(receb[1]));
				
				for(int i=0; i<Server.salas.size();i++) {
					Sala temp = Server.salas.get(i);
					if (temp.getId() == into) {
						output.writeUTF(temp.getTudo());
						temp.setQuantidade(temp.getQuantidade() + 1);
						break;
					}
				}	
			}
			
		}catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
	
}