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

public class DivulgaçãoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public DivulgaçãoFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImagemFundo = new JLabel("horizon");
		lblImagemFundo.setForeground(Color.WHITE);
		lblImagemFundo.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		lblImagemFundo.setBounds(65, 11, 155, 49);
		
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
		
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setBounds(0, 0, 711, 118);
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		Image imag1 = imagem1.getImage().getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_DEFAULT);
		
		lblImagem.setIcon(new ImageIcon(imag1));

		contentPane.add(lblImagem);
		contentPane.add(lblImagem);
		
		JLabel lblEventosDesponveisPara = new JLabel("Eventos despon\u00EDveis para a divulga\u00E7\u00E3o:");
		lblEventosDesponveisPara.setBounds(241, 188, 248, 23);
		contentPane.add(lblEventosDesponveisPara);
		
		
		JButton btnNewButton = new JButton("Divulgar");
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(241, 340, 200, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblFace = new JLabel("");
		lblFace.setBounds(241, 287, 27, 29);
		ImageIcon imagem= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/face.png"));
		Image imag = imagem.getImage().getScaledInstance(lblFace.getWidth(), lblFace.getHeight(), Image.SCALE_DEFAULT);
		
		lblFace.setIcon(new ImageIcon(imag));

		contentPane.add(lblFace);
		
		JLabel lblInsta = new JLabel("");
		lblInsta.setBounds(300, 288, 27, 29);
		ImageIcon imagem3= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/insta.jpg"));
		Image imag3 = imagem3.getImage().getScaledInstance(lblInsta.getWidth(), lblInsta.getHeight(), Image.SCALE_DEFAULT);
		
		lblInsta.setIcon(new ImageIcon(imag3));

		contentPane.add(lblInsta);
		
		JLabel lblLink = new JLabel("");
		lblLink.setBounds(353, 287, 27, 30);
		ImageIcon imagem4= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/linke.png"));
		Image imag4 = imagem4.getImage().getScaledInstance(lblLink.getWidth(), lblLink.getHeight(), Image.SCALE_DEFAULT);
		
		lblLink.setIcon(new ImageIcon(imag4));

		contentPane.add(lblLink);
		
		JLabel lblTwi = new JLabel("");
		lblTwi.setBounds(414, 287, 27, 30);
		ImageIcon imagem5= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/twitter.png"));
		Image imag5 = imagem5.getImage().getScaledInstance(lblTwi.getWidth(), lblTwi.getHeight(), Image.SCALE_DEFAULT);
		
		lblTwi.setIcon(new ImageIcon(imag5));

		contentPane.add(lblTwi);
	}

}
