package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dicionario;
import model.TCP;
import model.UDP;

public class Main extends Application {

    public static Stage tela;

    @Override
    public void start(Stage primaryStage) throws Exception{
        tela = primaryStage;
        Dicionario.carregarPalavras();
        TCP.iniciarConexao();
        UDP.denifirJogador();
        UDP.enviarNome();
        UDP.iniciarMulticast();

        Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        primaryStage.setTitle("Boggle");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
