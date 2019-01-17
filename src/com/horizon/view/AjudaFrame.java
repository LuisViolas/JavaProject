package com.horizon.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractListModel;

public class AjudaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public AjudaFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		btnVoltar.setBounds(10, 71, 87, 22);
		contentPane.add(btnVoltar);
		
		JButton button_3 = new JButton("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.RED);
		button_3.setBounds(107, 71, 89, 23);
		contentPane.add(button_3);
		
		JLabel lblfundo = new JLabel("");
		lblfundo.setBounds(0, 0, 762, 118);
		//imagen
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		Image imag1 = imagem1.getImage().getScaledInstance(lblfundo.getWidth(), lblfundo.getHeight(), Image.SCALE_DEFAULT);
		lblfundo.setIcon(new ImageIcon(imag1));
		contentPane.add(lblfundo);
		
		JLabel lblAjuda = new JLabel("Ajuda");
		lblAjuda.setForeground(new Color(153, 204, 0));
		lblAjuda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAjuda.setBounds(311, 129, 70, 30);
		contentPane.add(lblAjuda);
		
		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"Bem vindo ao gestor de eventos Horizon.", "Funcionalidades de Horizon: ", "Registo eventos;", "Incri\u00E7\u00F5es e pagamentos;", "Importa\u00E7\u00E3o de utilizadores;", "Divulga\u00E7\u00E3o de eventos;", "Consultas; ", "Alertas;\t\t", "Configura\u00E7\u00F5es;\t\t"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBackground(Color.WHITE);
		list.setBounds(38, 170, 685, 295);
		contentPane.add(list);
	}

}
