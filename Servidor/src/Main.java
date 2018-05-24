import controller.Requisicao;
import model.TCP;

public class Main {

    public static void main(String[] args) {
        Requisicao.iniciarSessoes();
        new TCP().start();
    }

}
