import controller.Requisicao;
import model.TCP;

public class Main {
	/*
	 * A main do server, da hora n�?
	 */
    public static void main(String[] args) {
        Requisicao.iniciarSessoes();
        new TCP().start();
    }

}
