package com.horizon.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.Evento;
import com.horizon.model.Gestao;
import com.horizon.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class IncriçoesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeAluno;
	private JTextField txtEmail;
	private JTextField txtValor;
	private JTextField txtdataPagamento;
	//
	private DefaultComboBoxModel<String> dcmEventos= new DefaultComboBoxModel<String>();
	
	
	/**
	 * metodo para prencher o dcbmEventos
	 * @throws FileNotFoundException
	 */
	
	public void prencherCombobox() throws FileNotFoundException{
		
		//este método usa um método para carregar eventos diferente
		//o método gestor.carregarEventosComDependencias() carrega também as tipologias e os departamentos
		//isto não é muito bonito e é só para esta faze inicial
		//temos de alterara as alturas em que as coisas são carregadas para não haver situações destas
		//gestor.carregarEventosComDependencias();
		
		
		ArrayList<Evento> eve= Gestao.getEventos();
		System.out.println(Gestao.getEventos());
		for ( Evento evento:eve) {
			dcmEventos.addElement(evento.getNome());
			System.out.println(evento.getNome());
		}
	}

	public void verificarPago(String nome){
		boolean isPago = Gestao.getEventoPorNome(nome).isPago();
		if (!isPago){
			try{
				txtValor.setEnabled(false);
				txtValor.setText("O evento não é pago.");
				txtdataPagamento.setEnabled(false);
				txtdataPagamento.setText("O evento não é pago.");
				
			}
		    catch (NullPointerException npe)
			{
		    	System.out.println("Ainda não foi incializado");
			}
		
			
		}
		
		else {
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.now();
				
				
				txtValor.setEnabled(true);
				txtValor.setText("");
				
				txtdataPagamento.setEnabled(true);
				txtdataPagamento.setText(dtf.format(localDate).toString());
				
				
				
			}
		    catch (NullPointerException npe)
			{
		    	System.out.println("Ainda não foi incializado");
			}
		}
		
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public IncriçoesFrame(User user) throws FileNotFoundException {
		
		System.out.println(user.getNome());
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		
		JButton button = new JButton("Sair");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.RED);
		button.setBounds(100, 71, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Voltar");
		button_1.addActionListener(new ActionListener() {
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
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(10, 71, 80, 23);
		contentPane.add(button_1);
		
		JLabel label_1 = new JLabel("horizon");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		label_1.setBounds(65, 11, 155, 49);
		contentPane.add(label_1);
		
		JLabel lblImagenfundo = new JLabel("");
		lblImagenfundo.setBounds(0, 0, 762, 118);
		Image imag1 = imagem1.getImage().getScaledInstance(lblImagenfundo.getWidth(), lblImagenfundo.getHeight(), Image.SCALE_DEFAULT);
		
		lblImagenfundo.setIcon(new ImageIcon(imag1));
		
				contentPane.add(lblImagenfundo);
				
				JLabel lblEvento = new JLabel("Evento:");
				lblEvento.setBounds(114, 220, 80, 23);
				contentPane.add(lblEvento);
				
				JComboBox<String> cmbEvento = new JComboBox<String>(dcmEventos);
				
				
				
				cmbEvento.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					verificarPago(cmbEvento.getSelectedItem().toString());					
					}
				});
				cmbEvento.setBounds(114, 249, 204, 21);
				contentPane.add(cmbEvento);
				prencherCombobox();
				//
				
				
				JLabel lblNomeDoAluno = new JLabel("Nome do aluno:");
				lblNomeDoAluno.setBounds(114, 290, 89, 14);
				contentPane.add(lblNomeDoAluno);
				
				txtNomeAluno = new JTextField();
				txtNomeAluno.setBounds(114, 315, 204, 20);
				contentPane.add(txtNomeAluno);
				txtNomeAluno.setColumns(10);
				
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setBounds(114, 346, 106, 23);
				contentPane.add(lblEmail);
				
				txtEmail = new JTextField();
				txtEmail.setBounds(114, 376, 204, 20);
				contentPane.add(txtEmail);
				txtEmail.setColumns(10);
				
				JLabel lblValor = new JLabel("Valor da incri\u00E7\u00E3o:");
				lblValor.setBounds(410, 224, 134, 14);
				contentPane.add(lblValor);
				
				txtValor = new JTextField();
				txtValor.setBounds(410, 249, 204, 20);
				contentPane.add(txtValor);
				txtValor.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("Data de pagamento:");
				lblNewLabel_1.setBounds(410, 300, 121, 17);
				contentPane.add(lblNewLabel_1);
				
				txtdataPagamento = new JTextField();
				txtdataPagamento.setBounds(410, 315, 204, 20);
				contentPane.add(txtdataPagamento);
				txtdataPagamento.setColumns(10);
				
				
				
				
				JButton btnNewButton = new JButton("Inscrever-se");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if((!txtNomeAluno.getText().equals(""))
						 &&(!txtdataPagamento.getText().equals(""))
						 &&(!txtEmail.getText().equals(""))
						 &&(!txtValor.getText().equals("")))
						{
							if(!Gestao.validaNumerico(txtValor.getText())){
								
								JOptionPane.showMessageDialog(contentPane, "O valor de pagamento tem de ser um número!");
								return;
							}
						}
							
						else
						{
							JOptionPane.showMessageDialog(contentPane, "Todos os campos têm de estao preenchidos!");
							return;
						}
						
						
						int valor=0;
						String data=txtdataPagamento.getText();
						String nomeAluno = txtNomeAluno.getText();
						String email= txtEmail.getText();
						String pago;
						
						if (!txtValor.isEnabled()) {
							valor=0;
							data="Sem data";
							pago="N/A";
						} else {
							if (valor!=0) {
								pago="Sim";
							}else{
								pago="Não";
							}
						}
						Gestao.adicionarInscrito(nomeAluno,  Gestao.getEventoPorNome(String.valueOf(cmbEvento.getSelectedItem())), pago, valor, data, email);

				
						 JOptionPane.showMessageDialog(null, "Inscrição realizada com sucesso.");
						 try {
							Gestao.gravarInscritos();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton.setBackground(new Color(50, 205, 50));
				btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnNewButton.setBounds(410, 370, 204, 30);
				contentPane.add(btnNewButton);
				
				JLabel lblInscriesEPagamentos = new JLabel("Inscri\u00E7\u00F5es e pagamentos");
				lblInscriesEPagamentos.setForeground(new Color(153, 204, 0));
				lblInscriesEPagamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblInscriesEPagamentos.setBounds(240, 156, 240, 30);
				contentPane.add(lblInscriesEPagamentos);
				verificarPago(cmbEvento.getSelectedItem().toString());
	}

}
