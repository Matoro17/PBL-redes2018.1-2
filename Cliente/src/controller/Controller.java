package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import model.TCP;
import model.UDP;
import view.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static String info;

    private Timeline atualizar;
    @FXML
    private ListView<String> sessoes;
    @FXML
    private Button entrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entrar.setOnAction(event -> {
            atualizar.stop();
            TCP.enviarMensagem("info," + sessoes.getSelectionModel().getSelectedItem());
            while (!TCP.existeResposta());
            String[] resposta = TCP.lerResposta().split(";");
            UDP.inserirEndereco(resposta[1]);
            info = resposta[0];
            System.out.println(info);

            try {
                Main.tela.setScene(new Scene(FXMLLoader.load(Main.class.getResource("Jogo.fxml"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        atualizar = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            if (!TCP.estaConectado()) {
                TCP.iniciarConexao();
            } else {
                TCP.enviarMensagem("listar");
                while (!TCP.existeResposta());
                sessoes.setItems(FXCollections.observableArrayList(TCP.lerResposta().split(";")));
            }
        }));

        atualizar.setCycleCount(Timeline.INDEFINITE);
        atualizar.play();
    }

}
