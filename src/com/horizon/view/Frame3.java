package com.horizon.view;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import com.horizon.model.Departamento;
import com.horizon.model.Evento;
import com.horizon.model.Gestao;
import com.horizon.model.Tipologia;
import com.horizon.model.User;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame3 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textSala;
	private JTextField textData;
	private JTextField textdataFecho;
	private JTextField texthora;
	private JTextField textValorPagar;
	
	
	
	ArrayList<Evento> eventos = Gestao.getEventos();
	
	//para departamentos
	private DefaultComboBoxModel<String> dcbmm = new DefaultComboBoxModel<String>();
	//para tipologia
	private DefaultComboBoxModel<String> dcbmTipologia = new DefaultComboBoxModel<String>();
	private ArrayList<Departamento> departamentos= Gestao.getDepartamentos();
	private ArrayList<Tipologia> tipologias= Gestao.getTipologias();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtNomeEvento;
	
	
	
	//carregar eventos
	
	
	/**
	 * metodo para adicionar os responsaveis na dcbm
	 * @throws FileNotFoundException 
	 */
	public void responsaveis() throws FileNotFoundException{
		for (Departamento departamento : departamentos) {
			dcbmm.addElement(departamento.getNome());
		}
	}
	
	public void mostrarTipologia() throws FileNotFoundException{
		for (Tipologia tipologia : tipologias) {
			dcbmTipologia.addElement(tipologia.getNome());
		}
	}
	

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public Frame3(User user) throws FileNotFoundException {
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Fa\u00E7a a gest\u00E3o dos seus eventos com horizon");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame3.class.getResource("/com/horizon/imagens/horizon_crop2_transp.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 527);
		getContentPane().setLayout(null);
		// adicionar imagen
		ImageIcon imagem= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		
		JLabel label = new JLabel("horizon");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		label.setBounds(65, 11, 155, 49);
		getContentPane().add(label);
		
		JComboBox<String> comboBox = new JComboBox<String>(dcbmTipologia);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(379, 223, 186, 20);
		getContentPane().add(comboBox);
		// mostrar os tipos de eventos na combobox
		mostrarTipologia(); 
		
		
		JRadioButton rdbtnSim = new JRadioButton("sim");
		rdbtnSim.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnSim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//ao carregar no sim faz a text box ficar enabled 
				if (textValorPagar.isEnabled()== false)
					textValorPagar.setEnabled(true);
				
			}
		});
		buttonGroup.add(rdbtnSim);
		rdbtnSim.setBounds(379, 396, 52, 20);
		getContentPane().add(rdbtnSim);
		
		
		JRadioButton rdbtnNo = new JRadioButton("N\u00E3o");
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//ao carregar no não por a text box a nao enabled e o seu texto a zero
				if (textValorPagar.isEnabled()== true){
					textValorPagar.setEnabled(false);
					textValorPagar.setText("0");
				}
			}
		});
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setSelected(true);
		rdbtnNo.setBounds(447, 396, 52, 20);
		getContentPane().add(rdbtnNo);
		
		JComboBox<String> cbBResponsavel = new JComboBox<String>(dcbmm);
		cbBResponsavel.setBackground(new Color(255, 255, 255));
		cbBResponsavel.setBounds(379, 286, 186, 20);
		getContentPane().add(cbBResponsavel);
		responsaveis();
		
		textValorPagar = new JTextField();
		textValorPagar.setText("0");
		textValorPagar.setEnabled(false);
		textValorPagar.setBounds(505, 396, 60, 21);
		getContentPane().add(textValorPagar);
		textValorPagar.setColumns(10);
		
		
		
		JLabel lblNomeDoEvento = new JLabel("Nome do evento:");
		lblNomeDoEvento.setBounds(165, 198, 103, 14);
		getContentPane().add(lblNomeDoEvento);
		
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(165, 261, 46, 14);
		getContentPane().add(lblSala);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBackground(new Color(50, 205, 50));
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setFont(new Font("Myriad Pro", Font.PLAIN, 14));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// adicionar evento ao clik do botão
				
				if((!txtNomeEvento.getText().equals(""))
				 &&(!textData.getText().equals(""))
				 &&(!textdataFecho.getText().equals(""))
				 &&(!texthora.getText().equals(""))
				 &&(!textSala.getText().equals(""))
				 &&(!textValorPagar.getText().equals("")))
				 {
					if(!Gestao.validaNumerico(textValorPagar.getText())){
						JOptionPane.showMessageDialog(getContentPane(), "O valor de pagamento tem de ser um número!");
						return;
					}
						
				 }
				 
				else{
					JOptionPane.showMessageDialog(getContentPane(), "Todos os campos têm de estar preenchidos!");
					return;
				}
				
				Gestao.adicionarEvento(String.valueOf(comboBox.getSelectedItem()),
						String.valueOf(cbBResponsavel.getSelectedItem()),
						Integer.parseInt(textValorPagar.getText()),
						txtNomeEvento.getText(),
						textData.getText(),
						texthora.getText(),
						textSala.getText(),
						textdataFecho.getText(),
						rdbtnSim.isSelected());
				try {
					Gestao.gravarEventos();
					JOptionPane.showMessageDialog(null, "Evento guardado com sucesso");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					System.out.println(Gestao.getDepartamentos());
				}
				
				
				
			}
		});
		btnAdicionar.setBounds(270, 445, 186, 30);
		getContentPane().add(btnAdicionar);
		
		JButton btnSair = new JButton("Terminar sec\u00E7\u00E3o");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 
				Frame1 f= new Frame1();
				f.setVisible(true);
				dispose();
				
				
			}
		});
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setBounds(530, 71, 135, 23);
		getContentPane().add(btnSair);
		
		
		textSala = new JTextField();
		textSala.setBounds(165, 286, 171, 20);
		getContentPane().add(textSala);
		textSala.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(165, 317, 103, 14);
		getContentPane().add(lblData);
		
		
		textData = new JTextField();
		textData.setBounds(165, 342, 171, 20);
		getContentPane().add(textData);
		textData.setColumns(10);
		
		
		JLabel lblTipoDeEvento = new JLabel("Tipo de evento:");
		lblTipoDeEvento.setBounds(379, 198, 94, 14);
		getContentPane().add(lblTipoDeEvento);
		
		
		JLabel lblResponsavel = new JLabel("Responsavel:");
		lblResponsavel.setBounds(379, 261, 83, 14);
		getContentPane().add(lblResponsavel);
		
		
		JLabel lblDataDeFecho = new JLabel("Data de fecho:");
		lblDataDeFecho.setBounds(379, 317, 83, 14);
		getContentPane().add(lblDataDeFecho);
		
		
		textdataFecho = new JTextField();
		textdataFecho.setBounds(379, 342, 186, 20);
		getContentPane().add(textdataFecho);
		textdataFecho.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(165, 373, 46, 14);
		getContentPane().add(lblHora);
		
		texthora = new JTextField();
		texthora.setBounds(165, 393, 171, 23);
		getContentPane().add(texthora);
		texthora.setColumns(10);
		//
		
		
		JLabel lblAInscrio = new JLabel("A inscri\u00E7\u00E3o \u00E9 paga?");
		lblAInscrio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAInscrio.setBounds(379, 373, 120, 14);
		getContentPane().add(lblAInscrio);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// esconder os botões jtxt etc
				/*lblNomeDoEvento.setVisible(false);
				textNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				PerfilFrame perfil= new PerfilFrame(user);
				perfil.setVisible(true);
				dispose();
				
				
				
			}
		});
		
		btnPerfil.setBackground(new Color(128, 128, 128));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBounds(140, 71, 120, 23);
		getContentPane().add(btnPerfil);
		
		JButton btnSobreHorizon = new JButton("Sobre horizon");
		btnSobreHorizon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder os botões jtxt etc
				/*lblNomeDoEvento.setVisible(false);
				textNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				//
				HorizonFrame horizon = new HorizonFrame(user);
				horizon.setVisible(true);
				dispose();
			}
		});
		btnSobreHorizon.setBackground(new Color(128, 128, 128));
		btnSobreHorizon.setForeground(Color.WHITE);
		btnSobreHorizon.setBounds(270, 72, 120, 21);
		getContentPane().add(btnSobreHorizon);
		
		JButton btnNewButton = new JButton("Ajuda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder os botões jtxt etc
				/*lblNomeDoEvento.setVisible(false);
				textNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				AjudaFrame ajuda= new AjudaFrame(user);
				ajuda.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(400, 71, 120, 22);
		getContentPane().add(btnNewButton);
		
		txtNomeEvento = new JTextField();
		txtNomeEvento.setBounds(165, 223, 171, 20);
		getContentPane().add(txtNomeEvento);
		txtNomeEvento.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(128, 128, 128));
		menuBar.setBounds(10, 71, 120, 21);
		getContentPane().add(menuBar);
		
		JMenu mnGestoDeEventos = new JMenu("Gest\u00E3o de eventos");
		mnGestoDeEventos.setBackground(new Color(128, 128, 128));
		mnGestoDeEventos.setForeground(new Color(255, 255, 255));
		menuBar.add(mnGestoDeEventos);
		
		JMenuItem mntmRegistoDeEventos = new JMenuItem("Registo de eventos");
		mntmRegistoDeEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// mostrar os botões jtxt etc
				lblNomeDoEvento.setVisible(true);
				txtNomeEvento.setVisible(true);
				lblSala.setVisible(true);
				textSala.setVisible(true);
				lblData.setVisible(true);
				textData.setVisible(true);
				lblTipoDeEvento.setVisible(true);
				comboBox.setVisible(true);
				lblResponsavel.setVisible(true);
				lblDataDeFecho.setVisible(true);
				textdataFecho.setVisible(true);
				btnAdicionar.setVisible(true);
				lblHora.setVisible(true);
				texthora.setVisible(true);
				cbBResponsavel.setVisible(true);
				textValorPagar.setVisible(true);
				lblAInscrio.setVisible(true);
				rdbtnSim.setVisible(true);
				rdbtnNo.setVisible(true);
				
				
				
			}
		});
		mnGestoDeEventos.add(mntmRegistoDeEventos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Inscri\u00E7\u00F5es e pagamentos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder as cenas
				/*lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);
				txtNomeEvento.setVisible(false);*/
				//
				IncriçoesFrame in = null;
				try {
					in = new IncriçoesFrame(user);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				in.setVisible(true);
				dispose();
			
			}
		});
		mnGestoDeEventos.add(mntmNewMenuItem);
		
		JMenuItem mntmImportaoDeUtilizadores = new JMenuItem("Importa\u00E7\u00E3o de utilizadores");
		mntmImportaoDeUtilizadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder as cenas
				/*lblNomeDoEvento.setVisible(false);
				txtNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				ImportarUtilizadoresFrame im= new ImportarUtilizadoresFrame(user);
				im.setVisible(true);
				dispose();
			}
		});
		mnGestoDeEventos.add(mntmImportaoDeUtilizadores);
		
		JMenuItem mntmDivulgaoDeEvento = new JMenuItem("Divulga\u00E7\u00E3o de evento");
		mntmDivulgaoDeEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder as cenas
				/*lblNomeDoEvento.setVisible(false);
				txtNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				DivulgaçãoFrame d= new DivulgaçãoFrame(user);
				d.setVisible(true);
				dispose();
			}
		});
		mnGestoDeEventos.add(mntmDivulgaoDeEvento);
		
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder as cenas
				/*lblNomeDoEvento.setVisible(false);
				txtNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				ConsultasFrame consulta= new ConsultasFrame(user);
				consulta.setVisible(true);
				dispose();
			}
		});
		mnGestoDeEventos.add(mntmConsultas);
		
		JMenuItem mntmAlertas = new JMenuItem("Alertas");
		mntmAlertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// esconder as cenas
				/*lblNomeDoEvento.setVisible(false);
				txtNomeEvento.setVisible(false);
				lblSala.setVisible(false);
				textSala.setVisible(false);
				lblData.setVisible(false);
				textData.setVisible(false);
				lblTipoDeEvento.setVisible(false);
				comboBox.setVisible(false);
				lblResponsavel.setVisible(false);
				lblDataDeFecho.setVisible(false);
				textdataFecho.setVisible(false);
				btnAdicionar.setVisible(false);
				lblHora.setVisible(false);
				texthora.setVisible(false);
				cbBResponsavel.setVisible(false);
				textValorPagar.setVisible(false);
				lblAInscrio.setVisible(false);
				rdbtnSim.setVisible(false);
				rdbtnNo.setVisible(false);*/
				
				AlertasFrame alertas= new AlertasFrame(user);
				alertas.setVisible(true);
				dispose();			
			}
		});
		mnGestoDeEventos.add(mntmAlertas);
		
		JMenuItem mntmC = new JMenuItem("Configura\u00E7\u00F5es");
		mntmC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConfiguraçõesFrame config= new ConfiguraçõesFrame(user);
				config.setVisible(true);
				dispose();
			}
		});
		mnGestoDeEventos.add(mntmC);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(0, 0, 762, 118);
		Image imag = imagem.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT);
		lblImagen.setIcon(new ImageIcon(imag));
		getContentPane().add(lblImagen);
		
		JLabel lblRegistoDeEventos = new JLabel("Registo de eventos");
		lblRegistoDeEventos.setForeground(new Color(153, 204, 0));
		lblRegistoDeEventos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRegistoDeEventos.setBounds(269, 139, 186, 30);
		getContentPane().add(lblRegistoDeEventos);
		
		// verificar a nivel do utilizador
		
		if (user.verificarNivel(2)) {
			mntmImportaoDeUtilizadores.setVisible(false);
			mntmC.setVisible(false);
		} if(user.verificarNivel(3)) {
			mntmC.setVisible(false);
			mntmImportaoDeUtilizadores.setVisible(false);
			mntmRegistoDeEventos.setVisible(false);
			//esconder as cenas
			lblNomeDoEvento.setVisible(false);
			txtNomeEvento.setVisible(false);
			lblSala.setVisible(false);
			textSala.setVisible(false);
			lblData.setVisible(false);
			textData.setVisible(false);
			lblTipoDeEvento.setVisible(false);
			comboBox.setVisible(false);
			lblResponsavel.setVisible(false);
			lblDataDeFecho.setVisible(false);
			textdataFecho.setVisible(false);
			btnAdicionar.setVisible(false);
			lblHora.setVisible(false);
			texthora.setVisible(false);
			cbBResponsavel.setVisible(false);
			textValorPagar.setVisible(false);
			lblAInscrio.setVisible(false);
			rdbtnSim.setVisible(false);
			rdbtnNo.setVisible(false);
			lblRegistoDeEventos.setVisible(false);
			
		}
		
			
		
		
	}
	}

