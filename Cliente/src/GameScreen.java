import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class GameScreen extends JFrame {
	private JButton[] btnNewButton;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		
		JLabel palavra = new JLabel("");
		
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
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

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.add(palavra);
		
		
		JButton send = new JButton("Enviar");
		send.addActionListener(l -> {
			System.out.println(palavra.getText());
			palavra.setText("");
			resetbtns();
		});;
		panel_1.add(send);
	}
	
	void resetbtns() {
		for (int i = 0; i < btnNewButton.length; i++) {
			btnNewButton[i].setEnabled(true);
		}
	}

}
