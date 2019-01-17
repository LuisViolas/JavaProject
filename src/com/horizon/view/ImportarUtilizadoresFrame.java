package com.horizon.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ImportarUtilizadoresFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	

	

	/**
	 * Create the frame.
	 */
	public ImportarUtilizadoresFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		
		JLabel label_1 = new JLabel("horizon");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		label_1.setBounds(65, 11, 155, 49);
		contentPane.add(label_1);
		
		JButton button = new JButton("Voltar");
		button.addActionListener(new ActionListener() {
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
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		button.setBounds(10, 71, 80, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Sair");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.RED);
		button_1.setBounds(100, 71, 89, 23);
		contentPane.add(button_1);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setBounds(0, 0, 711, 118);
		Image imag1 = imagem1.getImage().getScaledInstance(lblFundo.getWidth(), lblFundo.getHeight(), Image.SCALE_DEFAULT);
		
		lblFundo.setIcon(new ImageIcon(imag1));
		
		contentPane.add(lblFundo);
		
		JButton btnCarregar = new JButton("Carregar Ficheiro");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				String path = fc.showOpenDialog(contentPane).;
			}
		});
		btnCarregar.setBounds(10, 175, 179, 23);
		contentPane.add(btnCarregar);
		
		JButton btnAdicionarUsers = new JButton("Adicionar Users");
		btnAdicionarUsers.setEnabled(false);
		btnAdicionarUsers.setBounds(10, 209, 179, 23);
		contentPane.add(btnAdicionarUsers);
		
		JList<String> list = new JList<String>();
		list.setForeground(Color.WHITE);
		list.setBackground(Color.GRAY);
		list.setBounds(224, 159, 252, 189);
		contentPane.add(list);
	}
}
