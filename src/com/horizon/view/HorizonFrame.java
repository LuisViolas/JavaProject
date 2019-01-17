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
import javax.swing.AbstractListModel;
import javax.swing.border.EtchedBorder;

public class HorizonFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HorizonFrame(User user) {
		setResizable(false);
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
		btnVoltar.setBounds(10, 72, 78, 21);
		contentPane.add(btnVoltar);
		
		JButton button_3 = new JButton("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.RED);
		button_3.setBounds(98, 71, 89, 23);
		contentPane.add(button_3);
		
		JLabel lblfundo = new JLabel("");
		lblfundo.setBounds(0, 0, 774, 118);
		//imagen
				ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
				Image imag1 = imagem1.getImage().getScaledInstance(lblfundo.getWidth(), lblfundo.getHeight(), Image.SCALE_DEFAULT);
				lblfundo.setIcon(new ImageIcon(imag1));
		contentPane.add(lblfundo);
		
		JList<String> list = new JList<String>();
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setForeground(Color.BLACK);
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"", "", "", "", "", "Horizon \u00E9 um gestor de eventos desenvolvido por alunos da Escola Superior de Media, Artes e Design ", "(ESMAD),  com o intu\u00EDto  de gerir eventos da mesma escola.", "O principal objetivo desse software \u00E9 permitir que a gest\u00E3o dos eventos que ocorrem na ESMAD passa a", "ser suportada por uma aplica\u00E7\u00E3o desktop, de forma r\u00E1pida e simples.", "\"Horizon\" foi intutulado a este aplicativo, por aludir a um esp\u00EDrito de descoberta e viagem, que se adequa ", "\u00E0 necessidade de gerir e promover eventos que pretendem alargar os horizontes dos seus inscritos.", "", "", "", "", "", "", "", "", "", "", "", "", "", "     Desenvolvedores:", "          Samuel Monteiro  9160378", "          Jo\u00E3o Cardoso Silva 9160370"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setBackground(Color.WHITE);
		list.setBounds(34, 222, 685, 226);
		contentPane.add(list);
		
		JLabel lblSobreHorizon = new JLabel("Sobre Horizon");
		lblSobreHorizon.setForeground(new Color(153, 204, 0));
		lblSobreHorizon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSobreHorizon.setBounds(301, 151, 140, 30);
		contentPane.add(lblSobreHorizon);
	}
}
