import java.util.ArrayList;

public class Sala {
	private int Id;
	private String ip;
	private int porta;
	private String nome;
	private ArrayList<String> jogadores;
	private int limite;
	
	
	public Sala(int id,String ip, String nome, int limite, int port){
		jogadores = new ArrayList<>();
		this.Id = id;
		this.ip = ip;
		this.nome = nome;
		this.limite = limite;
		this.porta = port;
	}
	
	public ArrayList<String> setJogadores(String player){
		for(int i=0; i<jogadores.size();i++) {
			String temp = jogadores.get(i);
			if (temp.equals(player)) {
				break;
			}
		}	
		jogadores.add(player);
		return jogadores;
	}
	
	public String getTudo(){
		return Id+","+ip+","+nome+","+jogadores.size()+","+limite+","+porta;
	}

	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getQuantidade() {
		return jogadores.size();
	}


	public int getLimite() {
		return limite;
	}

	public int getPorta(){
		return porta;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	
}
