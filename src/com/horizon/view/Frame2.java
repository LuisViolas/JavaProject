package com.horizon.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.Gestao;
import com.horizon.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Frame2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textnome;
	//private DefaultComboBoxModel<String> dcmTiposUtilizadores= new DefaultComboBoxModel<String>();
	Gestao gestor= new Gestao();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	
	public Frame2() throws FileNotFoundException {
		Gestao.carregarUsers();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 446, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(29, 164, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("Password:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(29, 233, 69, 14);
		contentPane.add(lblEmail);
		
		textnome = new JTextField();
		textnome.setBounds(29, 190, 152, 20);
		contentPane.add(textnome);
		textnome.setColumns(10);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar password:");
		lblConfirmarPassword.setForeground(Color.WHITE);
		lblConfirmarPassword.setBounds(242, 169, 120, 14);
		contentPane.add(lblConfirmarPassword);
		//
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Adiministrador", "Secretario", "Gestor"}));
		comboBox.setBounds(242, 258, 152, 20);
		contentPane.add(comboBox);
		
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setForeground(Color.WHITE);
		lblTipoDeUsuario.setBounds(242, 235, 97, 14);
		contentPane.add(lblTipoDeUsuario);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// novo utilizador
				String nome = textnome.getText();
				String password = new String(passwordField.getPassword());
				String confirmarPass= new String(passwordField_1.getPassword());
				String tipoUtilizador= String.valueOf(comboBox.getSelectedItem());
				int x = 0;
				// devolve um numero de 1 a 3 de acordo com o nivel de utilizador selecionado
				if (tipoUtilizador.equals("Adiministrador")) {
					x=1;
				}else if (tipoUtilizador.equals("Secretario")) {
					x=3;
				} else {
					x=2;
				}
				// varefica se as duas passwords são iguais
				if (password.equals(confirmarPass)) {
					Gestao.adicionarUser(x, nome, password);
					try {
						Gestao.gravarUsers();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Conta criado com sucesso!");
					User user2 = Gestao.adicionarUser(x, nome, password);
					Frame3 f = null;
					try {
						f = new Frame3(user2);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					f.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(null, "SEJA BEM VINDO!");
				} else {
					JOptionPane.showMessageDialog(null, "Passwords diferentes");
				}
				
			}
		});
		btnConfirmar.setBackground(new Color(50, 205, 50));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBounds(154, 326, 120, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(128, 128, 128));
		btnVoltar.setForeground(new Color(255, 255, 255));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				Frame1 f= new Frame1();
				f.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(29, 11, 77, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblFfundo = new JLabel("");
		lblFfundo.setBounds(0, -15, 451, 408);
		//
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/fundo.PNG"));
		Image imag1 = imagem1.getImage().getScaledInstance(lblFfundo.getWidth(), lblFfundo.getHeight(), Image.SCALE_DEFAULT);
		
		JLabel lblNovoUtilizador = new JLabel("Novo utilizador");
		lblNovoUtilizador.setForeground(new Color(102, 204, 0));
		lblNovoUtilizador.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblNovoUtilizador.setBackground(new Color(102, 204, 0));
		lblNovoUtilizador.setBounds(29, 79, 192, 49);
		contentPane.add(lblNovoUtilizador);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(29, 258, 152, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(242, 190, 152, 20);
		contentPane.add(passwordField_1);
		
		lblFfundo.setIcon(new ImageIcon(imag1));

		contentPane.add(lblFfundo);
	}
}
