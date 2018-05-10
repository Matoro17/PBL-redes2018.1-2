import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
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
	private final Action action = new SwingAction();
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
	private JPanel panel_3;
	private JLabel lblId;
	private JLabel lblNome;
	private JLabel lblIp;
	private JLabel lblPorta;
	private JButton btnConectar;
	private JLabel lblPlayers;
	private final Action action_1 = new SwingAction();
	private final Action action_2 = new Changescreen();
	public String[] listadesalas;
	private JTextField textFieldmen;
	private final Action Sendmensage = new Sendmensage();
	
	private JPanel peinalmen;
	private JLabel mensagem;
	private JButton btnSend;
	private JLabel coming;
	private JLabel titulo;
	private JButton btnEntrar;
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

		btnEntrar = new JButton("Entrar");
		btnEntrar.setAction(action);
		btnEntrar.setBounds(113, 359, 97, 25);
		contentPane.add(btnEntrar);

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
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 5, 0, 0));
		
		lblId = new JLabel("id");
		panel_3.add(lblId);
		
		lblNome = new JLabel("Nome");
		panel_3.add(lblNome);
		
		lblIp = new JLabel("ip");
		panel_3.add(lblIp);
		
		lblPorta = new JLabel("porta");
		panel_3.add(lblPorta);
		
		lblPlayers = new JLabel("players");
		panel_3.add(lblPlayers);

		
		btnConectar = new JButton("Conectar");
		btnConectar.setAction(action_2);
		panel_3.add(btnConectar);
		panel_3.setVisible(false);
		
		peinalmen = new JPanel();
		peinalmen.setBounds(10, 11, 794, 544);
		contentPane.add(peinalmen);
		peinalmen.setLayout(null);
		
		mensagem = new JLabel("text");
		mensagem.setBounds(30, 27, 46, 14);
		peinalmen.add(mensagem);
		
		textFieldmen = new JTextField();
		textFieldmen.setBounds(31, 46, 86, 20);
		peinalmen.add(textFieldmen);
		textFieldmen.setColumns(10);
		

		
		btnConectar = new JButton("Conectar");
		btnConectar.setAction(action_2);
		panel_3.add(btnConectar);
		panel_3.setVisible(false);
		
		peinalmen = new JPanel();
		peinalmen.setBounds(10, 11, 794, 544);
		contentPane.add(peinalmen);
		peinalmen.setLayout(null);
		
		mensagem = new JLabel("text");
		mensagem.setBounds(30, 27, 46, 14);
		peinalmen.add(mensagem);
		
		textFieldmen = new JTextField();
		textFieldmen.setBounds(31, 46, 86, 20);
		peinalmen.add(textFieldmen);
		textFieldmen.setColumns(10);
		

		btnSend = new JButton("send");
		btnSend.setAction(Sendmensage);
		btnSend.setBounds(34, 77, 89, 23);
		peinalmen.add(btnSend);
		
		coming = new JLabel("New label");
		coming.setBounds(161, 31, 46, 14);
		peinalmen.add(coming);
		peinalmen.setVisible(false);
		
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Enviado");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				
				socket = new Socket(txtIpServidor.getText(), Integer.parseInt(txtPorta.getText()));

				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());

				setNome(txtNome.getText());
				String lista;
				lista = input.readUTF();
				listadesalas = lista.split("/");
				for(int i=0;i<listadesalas.length;i++){
					String[] part = listadesalas[0].split(",");
					lblId.setText(part[0]);
					lblIp.setText(part[1]);
					lblNome.setText(part[2]);
					lblPlayers.setText(part[3] + "/" + part[4]);
					lblPorta.setText(part[5]);
					panel_3.setVisible(true);
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

	private class Sendmensage extends AbstractAction {
		public Sendmensage() {
			putValue(NAME, "send");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			
			try {
			 String msg = textFieldmen.getText();
			 InetAddress group = InetAddress.getByName(lblIp.getText());
			 MulticastSocket s = new MulticastSocket(Integer.parseInt(lblPorta.getText()));
			 s.joinGroup(group);
			 DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),group, Integer.parseInt(lblPorta.getText()));
			 s.send(hi);
			 // get their responses!
			 byte[] buf = new byte[1000];
			 DatagramPacket recv = new DatagramPacket(buf, buf.length);
			 s.receive(recv);
			
			 // OK, I'm done talking - leave the group...
			 
				s.leaveGroup(group);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class Changescreen extends AbstractAction {
		public Changescreen() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			lblId.setVisible(false);
			panel.setVisible(false);
			txtNome.setVisible(false);
			txtPorta.setVisible(false);
			txtIpServidor.setVisible(false);
			titulo.setVisible(false);
			btnEntrar.setVisible(false);
			
			peinalmen.setVisible(true);
			mensagem.setVisible(true);
			textFieldmen.setVisible(true);
			btnSend.setVisible(true);
			coming.setVisible(true);
			
			
			
		}
	}
}
