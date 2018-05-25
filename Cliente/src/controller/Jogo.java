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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Dicionario;
import model.TCP;
import model.UDP;
import view.Main;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

public class Jogo implements Initializable {

    @FXML
    private ListView<String> jogadores;
    @FXML
    private ListView<String> palavras;
    @FXML
    private Button voltar;
    @FXML
    private Button letra1;
    @FXML
    private Button letra2;
    @FXML
    private Button letra3;
    @FXML
    private Button letra4;
    @FXML
    private Button letra5;
    @FXML
    private Button letra6;
    @FXML
    private Button letra7;
    @FXML
    private Button letra8;
    @FXML
    private Button letra9;
    @FXML
    private Button letra10;
    @FXML
    private Button letra11;
    @FXML
    private Button letra12;
    @FXML
    private Button letra13;
    @FXML
    private Button letra14;
    @FXML
    private Button letra15;
    @FXML
    private Button letra16;
    @FXML
    private TextField palavra;
    @FXML
    private TextField tempo;
    @FXML
    private Button confirmar;
    @FXML
    private Button limpar;

    private Timeline atualizar;
    
    private HashMap<String , HashSet<String>> players;

    private HashMap<String, Integer> pontuacao;
    
    private int timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	players = new HashMap<>();
        timer = 180;
        String[] letras = Controller.info.split(",");

        letra1.setText(letras[0]); letra5.setText(letras[4]); letra9.setText(letras[8]); letra13.setText(letras[12]);
        letra2.setText(letras[1]); letra6.setText(letras[5]); letra10.setText(letras[9]); letra14.setText(letras[13]);
        letra3.setText(letras[2]); letra7.setText(letras[6]); letra11.setText(letras[10]); letra15.setText(letras[14]);
        letra4.setText(letras[3]); letra8.setText(letras[7]); letra12.setText(letras[11]); letra16.setText(letras[15]);


        letra1.setOnAction(event -> {
            palavra.appendText(letra1.getText());
            letra1.setDisable(true);
        });

        letra2.setOnAction(event -> {
            palavra.appendText(letra2.getText());
            letra2.setDisable(true);
        });

        letra3.setOnAction(event -> {
            palavra.appendText(letra3.getText());
            letra3.setDisable(true);
        });

        letra4.setOnAction(event -> {
            palavra.appendText(letra4.getText());
            letra4.setDisable(true);
        });

        letra5.setOnAction(event -> {
            palavra.appendText(letra5.getText());
            letra5.setDisable(true);
        });

        letra6.setOnAction(event -> {
            palavra.appendText(letra6.getText());
            letra6.setDisable(true);
        });

        letra7.setOnAction(event -> {
            palavra.appendText(letra7.getText());
            letra7.setDisable(true);
        });

        letra8.setOnAction(event -> {
            palavra.appendText(letra8.getText());
            letra8.setDisable(true);
        });

        letra9.setOnAction(event -> {
            palavra.appendText(letra9.getText());
            letra9.setDisable(true);
        });

        letra10.setOnAction(event -> {
            palavra.appendText(letra10.getText());
            letra10.setDisable(true);
        });

        letra11.setOnAction(event -> {
            palavra.appendText(letra11.getText());
            letra11.setDisable(true);
        });

        letra12.setOnAction(event -> {
            palavra.appendText(letra12.getText());
            letra12.setDisable(true);
        });

        letra13.setOnAction(event -> {
            palavra.appendText(letra13.getText());
            letra13.setDisable(true);
        });

        letra14.setOnAction(event -> {
            palavra.appendText(letra14.getText());
            letra14.setDisable(true);
        });

        letra15.setOnAction(event -> {
            palavra.appendText(letra15.getText());
            letra15.setDisable(true);
        });

        letra16.setOnAction(event -> {
            palavra.appendText(letra16.getText());
            letra16.setDisable(true);
        });

        limpar.setOnAction(event -> {
            palavra.setText("");
            letra1.setDisable(false); letra5.setDisable(false); letra9.setDisable(false); letra13.setDisable(false);
            letra2.setDisable(false); letra6.setDisable(false); letra10.setDisable(false); letra14.setDisable(false);
            letra3.setDisable(false); letra7.setDisable(false); letra11.setDisable(false); letra15.setDisable(false);
            letra4.setDisable(false); letra8.setDisable(false); letra12.setDisable(false); letra16.setDisable(false);
        });

        atualizar = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (timer >= 0) {
                tempo.setText(String.format("%2d:%02d", timer / 60, timer % 60));
                timer--;
                //UDP.enviarMensagem(String.format("time,%s,%d;",UDP.nomeJogador(), timer).getBytes());
                
                if(timer % 5 == 0) {
	                checkPlayers();
                }
            } else {
                atualizar.stop();
                checkPlayers();
                String winner = calcularVencedor();
                if(winner.equals(UDP.nomeJogador())) {
                	 palavra.setText("Você Venceu"+pontuacao.get(winner)+"_Pontos");
                }else {
                	palavra.setText(winner+pontuacao.get(winner)+"_Pontos");
                }
                limpar.setDisable(true);
                confirmar.setDisable(true);
            }
        }));

        atualizar.setCycleCount(Timeline.INDEFINITE);
        atualizar.play();

        /*
         * Ação do botão de envio de palavra e checagem da mesma
         */
        confirmar.setOnAction(event -> {
        	if(!checarpalavrasDoJogo(palavra.getText())) {
        		
	            if (Dicionario.validarPalavra(palavra.getText())) {
	            	if(!players.containsKey(UDP.nomeJogador())) {
	            		players.put(UDP.nomeJogador(), new HashSet<>());
	            		players.get(UDP.nomeJogador()).add(palavra.getText());
	            	}else {
	            		if(!players.get(UDP.nomeJogador()).contains(palavra.getText())) {
	            			players.get(UDP.nomeJogador()).add(palavra.getText());

	    	                palavras.getItems().add(palavra.getText());
	            		}
	            	}
	            	
	                limpar.getOnAction();

	               
	                UDP.enviarMensagem(String.format("palavra,%s,%s;",UDP.nomeJogador(), palavra.getText()).getBytes());
	                
	            }
        	}
        });
        
        /*
         * Botão para voltar para a tela de salas do servidor
         */
        voltar.setOnAction(event -> {
            try {
                Main.tela.setScene(new Scene(FXMLLoader.load(Main.class.getResource("Inicio.fxml"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /*
     * Metodo para tratar os dados lidos pelo endereço de multicast
     */
    private void checkPlayers(){
    	String[] recebido = UDP.lerDados().split(";");
        for(int i=0;i<recebido.length;i++) {
        	String[] line = recebido[i].split(",");
        	if (line[0].equals("jogador")) {
        		if(!players.containsKey(line[1])) {
        			players.put(line[1], new HashSet<>());
        			jogadores.getItems().add(line[1]);
        		}							
			}else if(line[0].equals("palavra")){
				if(players.containsKey(line[1])) {
					if(!players.get(line[1]).contains(line[2])) {
						players.get(line[1]).add(line[2]);
					}
				}
			}
			else if(line[0].equals("time")) {
				if(Integer.parseInt(line[2]) < this.timer) {
					this.timer = Integer.parseInt(line[2]);
				}
			}
        }
    }
    /*
     * Metodo para checar todos os jogadores e suas palvras salvas no cliente e calcular quem deve ser o vencedor
     */
    private String calcularVencedor() {
    	pontuacao = new HashMap<>();
    	
    	for (String entry: players.keySet()) {
			int point = 0;
			
			for (String word :players.get(entry)) {
				point += word.length();
			}
			pontuacao.put(entry, point);
		}
    	String winner = null;
    	int maior = 0;
    	for (String ponto : pontuacao.keySet()) {
			if(pontuacao.get(ponto)>maior) {
				maior = pontuacao.get(ponto);
				winner = ponto;
			}
		}
    	return winner;
    }
    
    /*
     * Metodo para checar se alguma palavra já foi dita no jogo
     */
	private boolean checarpalavrasDoJogo(String word) {
		for (String entry: players.keySet()) {
			if (entry.contains(word)) {
				System.out.println("palavra ja existe no hash");
				return true;
			}
		}
		return false;
	}

}
