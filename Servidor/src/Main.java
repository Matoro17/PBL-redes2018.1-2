import controller.Requisicao;
import model.TCP;

public class Main {
	/*
	 * A main do server, da hora né?
	 */
    public static void main(String[] args) {
        Requisicao.iniciarSessoes();
        new TCP().start();
    }

}
