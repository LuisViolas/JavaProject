package com.horizon.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.Gestao;
import com.horizon.model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PerfilFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Gestao gestor= new Gestao();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textNomeAntigo;
	private JTextField textNovoNome;
	private JTextField txtConfirmarNome;

	

	/**
	 * Create the frame.
	 */
	public PerfilFrame(User user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("horizon");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		label.setBounds(65, 11, 155, 49);
		contentPane.add(label);
		//imagen
				ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame3 f = null;
				try {
					f = new Frame3(user);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setBackground(Color.GRAY);
		btnVoltar.setBounds(10, 71, 80, 23);
		contentPane.add(btnVoltar);
		
		JButton button_3 = new JButton("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.RED);
		button_3.setBounds(100, 71, 89, 23);
		contentPane.add(button_3);
		
		JLabel lblusuario = new JLabel("");
		lblusuario.setBounds(10, 145, 227, 208);
		//
		//imagen
				ImageIcon imagem2= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/usuarioa.png"));
				Image imag2 = imagem2.getImage().getScaledInstance(lblusuario.getWidth(), lblusuario.getHeight(), Image.SCALE_DEFAULT);
				lblusuario.setIcon(new ImageIcon(imag2));
		contentPane.add(lblusuario);
		
		JLabel lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(24, 382, 144, 21);
		//
		lblNome.setText(user.getNome());
		
		
		contentPane.add(lblNome);
		
		JLabel lblPassword = new JLabel("Nova password:");
		lblPassword.setBounds(517, 314, 143, 21);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar password:");
		lblConfirmarPassword.setBounds(516, 367, 130, 21);
		contentPane.add(lblConfirmarPassword);
		
		JButton btnNewButton = new JButton("Alterar password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// alterar password
				// erro verificar depois
				String passAntiga = new String(passwordField_2.getPassword());
				String passNova = new String(passwordField.getPassword());
				String confirmarPass = new String(passwordField_1.getPassword());
				
				
				
				if(passAntiga.equals(user.getPassword())){
					if (passNova.equals(confirmarPass)) {
						Gestao.alterarPasswordComPasswordAntiga(user.getNome(), passAntiga, passNova);
						
						JOptionPane.showMessageDialog(null, "Password alterada com sucesso");
						try {
							Gestao.gravarUsers();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// falta gravar a pass nova no ficheiro
						System.out.println(user.getPassword());
					} else {
						JOptionPane.showMessageDialog(null, "Password diferente! Tente outra vez.");

					}
				}
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(517, 437, 139, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblfundo = new JLabel("");
		lblfundo.setBounds(0, 0, 762, 118);
		Image imag1 = imagem1.getImage().getScaledInstance(lblfundo.getWidth(), lblfundo.getHeight(), Image.SCALE_DEFAULT);
		lblfundo.setIcon(new ImageIcon(imag1));
		contentPane.add(lblfundo);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(516, 337, 144, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(516, 389, 144, 20);
		contentPane.add(passwordField_1);
		
		JLabel lblPasswordAntiga = new JLabel("Password antiga:");
		lblPasswordAntiga.setBounds(516, 264, 139, 18);
		contentPane.add(lblPasswordAntiga);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(515, 283, 144, 20);
		contentPane.add(passwordField_2);
		
		JLabel lblPerfilDoUsuario = new JLabel("Perfil do utilizador");
		lblPerfilDoUsuario.setForeground(new Color(0, 51, 102));
		lblPerfilDoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerfilDoUsuario.setBounds(290, 129, 186, 30);
		contentPane.add(lblPerfilDoUsuario);
		
		JLabel lblNomeAntigo = new JLabel("Nome antigo:");
		lblNomeAntigo.setBounds(291, 266, 89, 14);
		contentPane.add(lblNomeAntigo);
		
		textNomeAntigo = new JTextField();
		textNomeAntigo.setBounds(291, 283, 143, 20);
		contentPane.add(textNomeAntigo);
		textNomeAntigo.setColumns(10);
		
		JLabel lblNovoNome = new JLabel("Novo nome:");
		lblNovoNome.setBounds(291, 317, 89, 14);
		contentPane.add(lblNovoNome);
		
		textNovoNome = new JTextField();
		textNovoNome.setBounds(291, 337, 144, 20);
		contentPane.add(textNovoNome);
		textNovoNome.setColumns(10);
		
		JLabel lblConfirmarNome = new JLabel("Confirmar nome:");
		lblConfirmarNome.setBounds(291, 370, 110, 14);
		contentPane.add(lblConfirmarNome);
		
		txtConfirmarNome = new JTextField();
		txtConfirmarNome.setBounds(291, 389, 144, 20);
		contentPane.add(txtConfirmarNome);
		txtConfirmarNome.setColumns(10);
		
		JButton btnAlterarNome = new JButton("Alterar nome");
		btnAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// alterar o nome
				if(textNomeAntigo.getText().equals(user.getNome())){
					if (txtConfirmarNome.getText().equals(textNovoNome.getText())) {
						user.setNome(textNovoNome.getText());
						Gestao.alterarNomeComNomeAntigo(textNomeAntigo.getText(), textNovoNome.getText());
						JOptionPane.showMessageDialog(null, "Nome alterado com sucesso");
						//  gravar o nome novo no ficheiro
						try {
							Gestao.gravarUsers();
						} 
						catch (FileNotFoundException e) {
							System.out.println("deu erro");
						}
		
						System.out.println(user.getPassword());
					} else {
						JOptionPane.showMessageDialog(null, "Nome diferente! Tente outra vez.");

					}
				}

			}
		});
		btnAlterarNome.setForeground(Color.WHITE);
		btnAlterarNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterarNome.setBackground(new Color(50, 205, 50));
		btnAlterarNome.setBounds(291, 437, 139, 29);
		contentPane.add(btnAlterarNome);
		
		JLabel lblAlterarNome = new JLabel("Alterar nome");
		lblAlterarNome.setForeground(new Color(153, 204, 0));
		lblAlterarNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlterarNome.setBounds(290, 211, 123, 30);
		contentPane.add(lblAlterarNome);
		
		JLabel lblAlterarPassword = new JLabel("Alterar password");
		lblAlterarPassword.setForeground(new Color(153, 204, 0));
		lblAlterarPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlterarPassword.setBounds(517, 213, 155, 30);
		contentPane.add(lblAlterarPassword);
	}
}
