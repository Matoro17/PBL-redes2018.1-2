
public class Sala {
	private int Id;
	private String ip;
	private int porta;
	private String nome;
	private int quantidade, limite;
	
	
	public Sala(int id,String ip, String nome, int limite){
		this.Id = id;
		this.ip = ip;
		this.nome = nome;
		this.limite = limite;
	}
	
	public String getTudo(){
		return Id+","+ip+","+nome+","+quantidade+","+limite+","+porta;
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
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public int getLimite() {
		return limite;
	}


	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	
}
