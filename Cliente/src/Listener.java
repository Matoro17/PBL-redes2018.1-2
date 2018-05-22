import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class Listener implements Runnable {
	protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    private String ip;
    private int port;
    private boolean ready = false;
    public ArrayList<Players> jogadores;
    public String[] dices;
    
	public Listener(String ip2, int port2) {
		this.ip = ip2;
    	this.port = port2;
	}


	@Override
	public void run() {
		try{
		
	        socket = new MulticastSocket(this.port);
	        InetAddress group = InetAddress.getByName(this.ip);
	        socket.joinGroup(group);
	        while (true) {
	            DatagramPacket packet = new DatagramPacket(buf, buf.length);
	            socket.receive(packet);
	            String received = new String(packet.getData(), 0, packet.getLength());
	            
	            String[] recebido = received.split("/");
	            if ("end".equals(recebido[0])) {
	            	break;
	            }
	            else if("player".equals(recebido[0])){
	            	if(!recebido[3].equals("ready")){
	            		jogadores.add(new Players(recebido[1],recebido[2].split(",")));
	            	}
	            }
	            else if("dices".equals(recebido[0])){
	            	ready = true;
	            	dices = recebido[1].split(",");
	            }
	        }
	        socket.leaveGroup(group);
	        socket.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean getReady(){
		return ready;
	}
	
	public String[] getDados(){
		return dices;
	}


	
}
