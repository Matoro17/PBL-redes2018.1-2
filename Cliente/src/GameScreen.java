import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;

public class GameScreen extends JFrame {
	private JButton[] btnNewButton;
	private static HashMap<Integer, String> dicionario;
	private JPanel contentPane;
	private static int pontuacao;
	private static ArrayList<String> escolidas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		pontuacao = 0;
		dicionario = new HashMap<>();
		escolidas = new ArrayList<>();
		carregardicionario();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen frame = new GameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameScreen() {
		
		Dimension buto = new Dimension(50, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel Dices = new JPanel();
		contentPane.add(Dices, BorderLayout.CENTER);
		
		JLabel palavra = new JLabel("");
		
		
		JPanel panel_2 = new JPanel();
		Dices.add(panel_2);
		panel_2.setLayout(new GridLayout(4, 4, 0, 0));

		btnNewButton = new JButton[16];
		String[] alea = new Dices().randomize();
		for (int j = 0; j < alea.length; j++) {
			btnNewButton[j] = new JButton();
			btnNewButton[j].setText(alea[j]);
			btnNewButton[j].setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton[j].setPreferredSize(buto);
			JButton temp = btnNewButton[j];
			btnNewButton[j].addActionListener(l -> {
				palavra.setText(palavra.getText()+temp.getText());
				temp.setEnabled(false);
			});;
			panel_2.add(btnNewButton[j]);
		}

		JPanel Checkpanel = new JPanel();
		contentPane.add(Checkpanel, BorderLayout.SOUTH);
		Checkpanel.add(palavra);
		
		JPanel Players = new JPanel();
		contentPane.add(Players, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 1));
		
		JLabel player = new JLabel("Player: ");
		panel_1.add(player);
		
		JLabel potnuacao = new JLabel("Pontua\u00E7\u00E3o: ");
		panel_1.add(potnuacao);
		
		JLabel points = new JLabel("");
		panel_1.add(points);
		
		JPanel Words = new JPanel();
		contentPane.add(Words, BorderLayout.EAST);
		Words.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextPane wordspoints = new JTextPane();
		Words.add(wordspoints);
		wordspoints.setText("Words");
		
		JButton send = new JButton("Enviar");
		send.addActionListener(l -> {
			System.out.println(palavra.getText());
			if(checkword(palavra.getText().toLowerCase())) {
				System.out.println("palavra existe");
				if(checkrepete(palavra.getText())) {
					System.out.println("ja inserida");
					palavra.setText("ja inserida");
					
					palavra.setText("");
				}else {
					escolidas.add(palavra.getText());
					wordspoints.setText(wordspoints.getText()+"\n"+palavra.getText());
					pontuacao = pontuacao + palavra.getText().length();
					points.setText(pontuacao +"pontos");
					palavra.setText("");
				}
			}
			else {
				
				palavra.setText("Não existe");
				
				palavra.setText("");
			}
			
			resetbtns();
		});;
		Checkpanel.add(send);
		
		
	}
	
	void resetbtns() {
		for (int i = 0; i < btnNewButton.length; i++) {
			btnNewButton[i].setEnabled(true);
		}
	}
	
	public boolean checkrepete(String palavra) {
		return escolidas.contains(palavra);
	}
	
	public boolean checkword(String palavra) {
		return dicionario.containsValue(palavra);
	}
	
	public static void carregardicionario() {
	    String nome = "dictionary.txt";
	    
	    try {
	      FileReader arq = new FileReader(nome);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); // lê a primeira linha
	// a variável "linha" recebe o valor "null" quando o processo
	// de repetição atingir o final do arquivo texto
	      Integer count = 0;
	      while (linha != null) {
	        dicionario.put(count, linha);
	        count++;
	 
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      }
	 
	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	}
}
