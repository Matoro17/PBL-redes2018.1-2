import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Clientframe extends JFrame {

	private JPanel contentPane;
	private JTextField txtIpServidor;
	private JTextField txtPorta;
	private JTextField txtNome;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private String nome;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblServers;
	private JLabel lblIpUdp;
	private JPanel panel_2;
	private JLabel lblNewLabel;

	private JLabel lblPlayers;
	private final Action ServerLists = new Serverlists();
	private final Action Enterroom = new EnterRoom();
	public String[] listadesalas;
	
	private JLabel titulo;
	private JButton[] btnEntrar;
	private JPanel[] Roomlist;
	private JButton[] btnConectar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientframe frame = new Clientframe();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public void setGamelist(){
		
	}
	
	public void setGameplay(){
		panel.setVisible(false);
		txtNome.setVisible(false);
		txtPorta.setVisible(false);
		txtIpServidor.setVisible(false);
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public Clientframe() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIpServidor = new JTextField();
		txtIpServidor.setText("Ip Servidor");
		txtIpServidor.setBounds(48, 259, 227, 37);
		contentPane.add(txtIpServidor);
		txtIpServidor.setColumns(10);
		
		txtPorta = new JTextField();
		txtPorta.setText("Porta");
		txtPorta.setBounds(48, 309, 227, 37);
		contentPane.add(txtPorta);
		txtPorta.setColumns(10);
		
		titulo = new JLabel("Login");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(48, 152, 227, 37);
		contentPane.add(titulo);
		
		txtNome = new JTextField();
		txtNome.setText("Nome");
		txtNome.setBounds(48, 209, 227, 37);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		
		panel = new JPanel();
		panel.setBounds(340, 93, 447, 421);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		lblServers = new JLabel("Servers");
		lblServers.setBounds(12, 5, 106, 16);
		panel_1.add(lblServers);
		
		lblIpUdp = new JLabel("Ip UDP");
		lblIpUdp.setBounds(130, 5, 137, 16);
		panel_1.add(lblIpUdp);
		
		lblNewLabel = new JLabel("porta");
		lblNewLabel.setBounds(243, 5, 56, 16);
		panel_1.add(lblNewLabel);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(12, 34, 423, 374);
		panel_1.add(panel_2);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setAction(ServerLists);
		btnLogin.setBounds(112, 359, 97, 25);
		contentPane.add(btnLogin);
		
		
		
		
		
		
	}
	


	private class Serverlists extends AbstractAction {
		public Serverlists() {
			putValue(NAME, "Login");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				socket = new Socket(txtIpServidor.getText(), Integer.parseInt(txtPorta.getText()));
				
				input = new DataInputStream(socket.getInputStream());
				output =  new DataOutputStream(socket.getOutputStream());
				
				setNome(txtNome.getText());
				String lista;
				lista = input.readUTF();
				listadesalas = lista.split("/");
				Roomlist = new JPanel[listadesalas.length];
				btnConectar = new JButton[listadesalas.length];
				for(int i=0;i<listadesalas.length;i++){
					Roomlist[i] = new JPanel();
					Roomlist[i].setLayout(new GridLayout(0, 5, 0, 0));

					Roomlist[i].setVisible(true);
					JLabel lblId;
					JLabel lblNome;
					JLabel lblIp;
					JLabel lblPorta;
					
					String[] part = listadesalas[i].split(",");
					lblId = new JLabel("id");
					Roomlist[i].add(lblId);
					
					lblNome = new JLabel("Nome");
					Roomlist[i].add(lblNome);
					
					lblIp = new JLabel("ip");
					Roomlist[i].add(lblIp);
					
					lblPorta = new JLabel("porta");
					Roomlist[i].add(lblPorta);
					
					lblPlayers = new JLabel("players");
					Roomlist[i].add(lblPlayers);
					
					btnConectar[i] = new JButton("Conectar");
					btnConectar[i].setAction(Enterroom);
					Roomlist[i].add(btnConectar[i]);
					lblId.setText(part[0]);
					lblIp.setText(part[1]);
					lblNome.setText(part[2]);
					lblPlayers.setText(part[3]+"/"+part[4]);
					lblPorta.setText(part[5]);
					panel_2.add(Roomlist[i]);
				}
				
				
				
				
				
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	

	
	
	private class EnterRoom extends AbstractAction {
		public EnterRoom() {
			putValue(NAME, "Enter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String ip = null;
			int port = 0;
			for(int i=0;i<Roomlist.length;i++){
				
				for (Component componente : Roomlist[i].getComponents()) {
			        if (componente instanceof JLabel) {
			           JLabel tempo = (JLabel) componente;
			           String ipcode[] = tempo.getText().split(".");
			           if(ipcode[0].equals("235")){
			        	   ip=tempo.getText();
			           }
			           if(tempo.getText().length() == 5){
			        	   port = Integer.parseInt(tempo.getText());
			           }
			        }
			    }
			}
			setGameplay();
			GameScreen frame;
			try {
				frame = new GameScreen(ip,port);
				contentPane.add(frame);
				frame.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	}
}
