package com.horizon.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.horizon.model.Departamento;
import com.horizon.model.Evento;
import com.horizon.model.Gestao;
import com.horizon.model.Inscrito;
import com.horizon.model.Tipologia;
import com.horizon.model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class ConsultasFrame extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private final ButtonGroup group = new ButtonGroup();
	
	private final String ID = "ID//NAO_MOSTRAR";
	private String[] colunasEvento = {"Nome", "Tipo", "Responsável", "Data", "Hora", "Sala", "Pago", "Valor", "Data Limite"};
	private String[] colunasInscrito = {"Nome", "Evento", "Pago", "Valor", "Email", ID};
	
	private final String ALL_DEP = "TODOS DEPARTAMENTOS";
	private final String ALL_TIP = "TODOS TIPOS";
	private final String ALL_EVE = "TODOS";
	
	
 	
	private DefaultTableModel dtmEventos = new DefaultTableModel(colunasEvento, 0);
	private DefaultComboBoxModel<String> dcbmEventos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcbmTipologias = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> dcbmDepartamentos = new DefaultComboBoxModel<String>();
	
	public void preencherTipos(){
		ArrayList<Tipologia> tipologias = Gestao.getTipologias();
		dcbmTipologias.addElement(ALL_TIP);
		for (Tipologia tipologia : tipologias) {
			dcbmTipologias.addElement(tipologia.getNome());
		}
		
	}
	
	public void preencherDepartamentos(){
		ArrayList<Departamento> departamentos = Gestao.getDepartamentos();
		dcbmDepartamentos.addElement(ALL_DEP);
		for (Departamento departamento : departamentos) {
			dcbmDepartamentos.addElement(departamento.getNome());
		}
		
		
	}
	
	
	public void preencherEventos(){
		ArrayList<Evento> eventos = Gestao.getEventos();
		dcbmEventos.addElement(ALL_EVE);
		
		for (Evento evento : eventos) {
			dcbmEventos.addElement(evento.getNome());
		}
	}
	
	public void limparDTM() {
		if (dtmEventos.getRowCount()>0){
			for (int i = dtmEventos.getRowCount()-1; i > -1; i--) {
				dtmEventos.removeRow(i);
			}
		}
			
	}
	
	
	public void preencherTabelaComInscritos(){
		limparDTM();
		dtmEventos.setColumnIdentifiers(colunasInscrito);
		
		ArrayList<Inscrito> inscritos = Gestao.getInscritos();
		
		for (Inscrito inscrito : inscritos) {
			String nome = inscrito.getNome();
			String evento = inscrito.GetEventoNome();
			String pago = inscrito.getPago();
			String valor = Integer.toString(inscrito.getValor()) + "€";
			String email = inscrito.getEmail();
			int id = inscrito.getCodigo();
			
			Object[] obj = {nome, evento, pago, valor, email, id};
			dtmEventos.addRow(obj);
		}
		
	}
	
	
	
	public void preencherTabelaComInscritosPorEvento(Evento ev){
		limparDTM();
		
		dtmEventos.setColumnIdentifiers(colunasInscrito);
		
		ArrayList<Inscrito> inscritos = Gestao.getInscritos();
		
		System.out.println(inscritos.size());
		
		for (Inscrito inscrito : inscritos) {
			if(inscrito.getEvento().equals(ev)){
				String nome = inscrito.getNome();
				String evento = inscrito.GetEventoNome();
				String pago = inscrito.getPago();
				String valor = Integer.toString(inscrito.getValor()) + "€";
				String email = inscrito.getEmail();
				
				Object[] obj = {nome, evento, pago, valor, email};
				dtmEventos.addRow(obj);
				System.out.println("Incrito " + nome + " adicionado");
			}
				
		}
		
	}
	
	
	public void preencherTabelaComEventoPorTipo(Tipologia tipologia){
		limparDTM();
		
		dtmEventos.setColumnIdentifiers(colunasEvento);
		
		ArrayList<Evento> eventos = Gestao.getEventos();
		
		for (Evento evento : eventos) {
			
			if(evento.getTipologia().equals(tipologia)){
				String nome = evento.getNome();
				String tipo = evento.getTipoNome();
				String responsavel = evento.getDepartNome();
				String data = evento.getData();
				String hora = evento.getHora();
				String sala = evento.getSala();
				String pago = null;
				String valor = Integer.toString(evento.getValor());
				String dataLimite = evento.getDataLimite();
				
				if (evento.isPago())
					pago = "Sim";
				
				else
					pago = "Não";
						
				Object[] obj = {nome, tipo, responsavel, data, hora, sala, pago, valor, dataLimite};
				
				dtmEventos.addRow(obj);
				System.out.println("Evento " + nome + " adicionado");
			}
			
		}
		
	}
	
	public void preencherTabelaComEventoPorDepart(Departamento departamento){
		limparDTM();
		
		dtmEventos.setColumnIdentifiers(colunasEvento);
		
		ArrayList<Evento> eventos = Gestao.getEventos();
		
		for (Evento evento : eventos) {
			if(evento.getDepart().equals(departamento)){
				String nome = evento.getNome();
				String tipo = evento.getTipoNome();
				String responsavel = evento.getDepartNome();
				String data = evento.getData();
				String hora = evento.getHora();
				String sala = evento.getSala();
				String pago = null;
				String valor = Integer.toString(evento.getValor());
				String dataLimite = evento.getDataLimite();
				
				if (evento.isPago())
					pago = "Sim";
				
				else
					pago = "Não";
						
				Object[] obj = {nome, tipo, responsavel, data, hora, sala, pago, valor, dataLimite};
				
				dtmEventos.addRow(obj);
				System.out.println("Evento " + nome + " adicionado");
			}
		}
	}
	
	public void preencherTabelaComEventoPorDepartPorTipo(Tipologia tipologia, Departamento departamento){
		limparDTM();
		
		dtmEventos.setColumnIdentifiers(colunasEvento);
		
		ArrayList<Evento> eventos = Gestao.getEventos();
		
		for (Evento evento : eventos) {
			if((evento.getTipologia().equals(tipologia))&&(evento.getDepart().equals(departamento))){
				String nome = evento.getNome();
				String tipo = evento.getTipoNome();
				String responsavel = evento.getDepartNome();
				String data = evento.getData();
				String hora = evento.getHora();
				String sala = evento.getSala();
				String pago = null;
				String valor = Integer.toString(evento.getValor());
				String dataLimite = evento.getDataLimite();
				
				if (evento.isPago())
					pago = "Sim";
				
				else
					pago = "Não";
						
				Object[] obj = {nome, tipo, responsavel, data, hora, sala, pago, valor, dataLimite};
				
				dtmEventos.addRow(obj);
				System.out.println("Evento " + nome + " adicionado");
			}
			
		}
	}
	
	public void preencherTabelaComEventos(){
		limparDTM();

		dtmEventos.setColumnIdentifiers(colunasEvento);
		
		ArrayList<Evento> eventos = Gestao.getEventos();
		
		
		
		for (Evento evento : eventos) {
			String nome = evento.getNome();
			String tipo = evento.getTipoNome();
			String responsavel = evento.getDepartNome();
			String data = evento.getData();
			String hora = evento.getHora();
			String sala = evento.getSala();
			String pago = null;
			String valor = Integer.toString(evento.getValor());
			String dataLimite = evento.getDataLimite();
			
			if (evento.isPago())
				pago = "Sim";
			
			else
				pago = "Não";
					
			Object[] obj = {nome, tipo, responsavel, data, hora, sala, pago, valor, dataLimite};
			
			dtmEventos.addRow(obj);
			System.out.println("Evento " + nome + " adicionado");
			
		}
		
		System.out.println("dtm tem " + dtmEventos.getRowCount() + "rows");
	}
	
	
	/**
	 * Create the frame.
	 */
	public ConsultasFrame(User user) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 527);
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
				} 
				catch (FileNotFoundException e1) {
					System.out.println("deu erro");
				}
				f.setVisible(true);
				dispose();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(10, 71, 80, 23);
		contentPane.add(button_1);
		
		JLabel lblImagenFundo = new JLabel("");
		lblImagenFundo.setBounds(0, 0, 762, 118);
		Image imag1 = imagem1.getImage().getScaledInstance(lblImagenFundo.getWidth(), lblImagenFundo.getHeight(), Image.SCALE_DEFAULT);
		
		lblImagenFundo.setIcon(new ImageIcon(imag1));
		
		contentPane.add(lblImagenFundo);
		
		JLabel lblFiltro = new JLabel("Filtros:");
		lblFiltro.setForeground(Color.DARK_GRAY);
		lblFiltro.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFiltro.setBounds(10, 129, 80, 23);
		contentPane.add(lblFiltro);
		
		
		
		
		
		JRadioButton rdbtnEventos = new JRadioButton("Eventos");
		rdbtnEventos.setSelected(true);
		rdbtnEventos.setBounds(10, 169, 192, 23);
		contentPane.add(rdbtnEventos);
		
		JRadioButton rdbtnEstudantes = new JRadioButton("Estudantes");
		rdbtnEstudantes.setBounds(10, 257, 192, 23);
		contentPane.add(rdbtnEstudantes);
		
		JComboBox<String> cbbEstudantes = new JComboBox<String>(dcbmEventos);
		cbbEstudantes.setBounds(10, 287, 192, 20);
		contentPane.add(cbbEstudantes);
		preencherEventos();
		preencherTabelaComEventos();
		
		
		
		group.add(rdbtnEstudantes);
		group.add(rdbtnEventos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 169, 497, 308);
		contentPane.add(scrollPane);
		
		table_1 = new JTable(dtmEventos);
		scrollPane.setViewportView(table_1);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setBackground(new Color(128, 128, 128));
		
		JComboBox<String> cbbTipos = new JComboBox<String>(dcbmTipologias);
		cbbTipos.setBounds(10, 199, 192, 20);
		contentPane.add(cbbTipos);
		
		JComboBox<String> cbbDepart = new JComboBox<String>(dcbmDepartamentos);
		cbbDepart.setBounds(10, 230, 192, 20);
		contentPane.add(cbbDepart);
		
		preencherTipos();
		preencherDepartamentos();
		
		
		JButton btnAlterarPago = new JButton("Alterar Pago");
		btnAlterarPago.setEnabled(false);
		btnAlterarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(dtmEventos.getValueAt(table_1.getSelectedRow(), 0).toString());
				System.out.println(Gestao.getInscritoPorNome(dtmEventos.getValueAt(table_1.getSelectedRow(), 0).toString()).getNome());
				
				try {
				int escolha =  Integer.parseInt(JOptionPane.showInputDialog("Qual o valor do pagamento de " + dtmEventos.getValueAt(table_1.getSelectedRow(), 0).toString()))  ;
				if (escolha>=0){
					if(escolha==0){
						Gestao.getInscritoPorID( (int) dtmEventos.getValueAt(table_1.getSelectedRow(), 5)).setPago("Não");
						Gestao.getInscritoPorID( (int) dtmEventos.getValueAt(table_1.getSelectedRow(), 5)).setValor(escolha);
					}
					
					else {
						Gestao.getInscritoPorID( (int)dtmEventos.getValueAt(table_1.getSelectedRow(), 5)).setPago("Sim");
						Gestao.getInscritoPorID( (int) dtmEventos.getValueAt(table_1.getSelectedRow(), 5)).setValor(escolha);
						System.out.println("pago de " + dtmEventos.getValueAt(table_1.getSelectedRow(), 5).toString() +  " alterado para valor");
					
						
					}
				}
				}
				
				catch(NullPointerException nulle){
					System.out.println("escolha deu null");
				}
				
				try {
					Gestao.gravarInscritos();
				} catch (FileNotFoundException e) {
					System.out.println("deu erro");
				}
				
			}
		});
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// consultar os eventos de acordo com os filtros
				
				System.out.println(rdbtnEventos.isSelected());
				if (rdbtnEventos.isSelected()){
					
					if ((cbbDepart.getSelectedItem().toString().equals(ALL_DEP))&&(cbbTipos.getSelectedItem().toString().equals(ALL_TIP)))
					preencherTabelaComEventos();
					
					else if(cbbDepart.getSelectedItem().toString().equals(ALL_DEP))
						preencherTabelaComEventoPorTipo(Gestao.getTipologiaPorNome(cbbTipos.getSelectedItem().toString()));
					
					else if(cbbTipos.getSelectedItem().toString().equals(ALL_TIP))
						preencherTabelaComEventoPorDepart(Gestao.getDepartamentoPorNome(cbbDepart.getSelectedItem().toString()));
					
					else
						preencherTabelaComEventoPorDepartPorTipo(Gestao.getTipologiaPorNome(cbbTipos.getSelectedItem().toString()), Gestao.getDepartamentoPorNome(cbbDepart.getSelectedItem().toString()));
					
					
				}
				
				
					
				else if (rdbtnEstudantes.isSelected()&&(!cbbEstudantes.getSelectedItem().toString().equals("TODOS"))){
					btnAlterarPago.setEnabled(true);
					preencherTabelaComInscritosPorEvento(Gestao.getEventoPorNome(cbbEstudantes.getSelectedItem().toString()));

					TableColumn coluna = table_1.getColumnModel().getColumn(5);
					table_1.removeColumn(coluna);
					System.out.println("sucesso 2");
				}
				else if (rdbtnEstudantes.isSelected()){
					btnAlterarPago.setEnabled(true);
					preencherTabelaComInscritos();

					TableColumn coluna = table_1.getColumnModel().getColumn(5);
					table_1.removeColumn(coluna);
				}
			}
				
		});
		btnConsultar.setBackground(new Color(50, 205, 50));
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConsultar.setBounds(10, 400, 192, 30);
		contentPane.add(btnConsultar);
		
		
		btnAlterarPago.setForeground(Color.WHITE);
		btnAlterarPago.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAlterarPago.setBackground(new Color(50, 205, 50));
		btnAlterarPago.setBounds(10, 318, 130, 30);
		contentPane.add(btnAlterarPago);
		
	}
}
