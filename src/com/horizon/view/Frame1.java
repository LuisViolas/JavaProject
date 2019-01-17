package com.horizon.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.Gestao;
import com.horizon.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Frame1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	//
	
	ArrayList<User> userss=  Gestao.getUsers();
	ArrayList<String> nomePassword = new ArrayList<String>(); 
	private JPasswordField passwordField;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Gestao.carregarTudo();
					Frame1 frame = new Frame1();
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
	public Frame1() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame1.class.getResource("/com/horizon/imagens/horizon_crop2_transp.png")));
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/fundo.PNG"));
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Login");
		label.setForeground(new Color(102, 204, 0));
		label.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		label.setBackground(new Color(102, 204, 0));
		label.setBounds(10, 127, 117, 49);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		label_1.setBounds(12, 202, 206, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Password:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		label_2.setBounds(13, 260, 205, 23);
		contentPane.add(label_2);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(12, 226, 192, 23);
		contentPane.add(txtNome);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			// fazer login

				
				String password = new String(passwordField.getPassword());
				if (Gestao.getUser(txtNome.getText(), password)!= null) {
					Frame3 n = null;
					try {
						User user2 = Gestao.getUser(txtNome.getText(), password);
						n = new Frame3(user2);
						n.setVisible(true);
						dispose();
					} catch (FileNotFoundException e) {
						System.out.println("fnfe");
					}
					
					
				}
				else
					JOptionPane.showMessageDialog(null, "Esta conta não existe!");
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBackground(new Color(102, 204, 0));
		button.setBounds(12, 424, 192, 28);
		contentPane.add(button);
		
		JLabel lblImagenFundo = new JLabel("New label");
		lblImagenFundo.setBounds(-3, -3, 775, 504);
		Image imag1 = imagem1.getImage().getScaledInstance(lblImagenFundo.getWidth(), lblImagenFundo.getHeight(), Image.SCALE_DEFAULT);
		
		JLabel lblNewLabel = new JLabel("horizon");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bauhaus 93", Font.PLAIN, 50));
		lblNewLabel.setBounds(91, 34, 205, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblEsqueciAPassword = new JLabel("Esqueci a password");
		lblEsqueciAPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// caso o tulizador esqueça a password 
				String email= JOptionPane.showInputDialog("Insira o seu email e receberá a sua password");
				if (email!=null){
					// enviar uma menssagem com a password para o email insserido
					}
					else{
						System.out.println("Cancelado");
					}
						
			}
		});
		lblEsqueciAPassword.setForeground(new Color(51, 204, 0));
		lblEsqueciAPassword.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblEsqueciAPassword.setBounds(12, 373, 135, 23);
		contentPane.add(lblEsqueciAPassword);
		
		JLabel lblNovoUtilizador = new JLabel("Novo utilizador");
		lblNovoUtilizador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Frame2 novaFrame = null;
				try {
					novaFrame = new Frame2();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				novaFrame.setVisible(true);
				dispose();
				
			}
		});
		lblNovoUtilizador.setForeground(new Color(51, 204, 0));
		lblNovoUtilizador.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		lblNovoUtilizador.setBounds(12, 341, 117, 23);
		contentPane.add(lblNovoUtilizador);
		
		JLabel lblLogotipo = new JLabel("New label");
		lblLogotipo.setBounds(10, 11, 71, 67);
		
		// adicionar imagen
				ImageIcon imagem= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/horizon_crop2_transp.png"));
				Image imag = imagem.getImage().getScaledInstance(lblLogotipo.getWidth(), lblLogotipo.getHeight(), Image.SCALE_DEFAULT);
				contentPane.setLayout(null);
				lblLogotipo.setIcon(new ImageIcon(imag));
				
		contentPane.add(lblLogotipo);
		
		JLabel lblPorto = new JLabel("New label");
		lblPorto.setBounds(621, 447, 121, 30);
		//
		ImageIcon imagem2= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/Pporto.png"));
		Image imag2 = imagem2.getImage().getScaledInstance(lblPorto.getWidth(), lblPorto.getHeight(), Image.SCALE_DEFAULT);
		contentPane.setLayout(null);
		lblPorto.setIcon(new ImageIcon(imag2));
		
		contentPane.add(lblPorto);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(12, 288, 192, 23);
		contentPane.add(passwordField);
		
		lblImagenFundo.setIcon(new ImageIcon(imag1));
		
		contentPane.add(lblImagenFundo);
	}
}
