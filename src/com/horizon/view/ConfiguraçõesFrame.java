package com.horizon.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.horizon.model.Departamento;
import com.horizon.model.Gestao;
import com.horizon.model.Tipologia;
import com.horizon.model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ConfiguraçõesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> dlmTipologias = new DefaultListModel<String>();
	private DefaultListModel<String> dlmDepartamentos= new DefaultListModel<String>();
	private DefaultListModel<String> dlmUsers= new DefaultListModel<String>();
	
	Gestao gestor = new Gestao();
	
	
	private void preencherDLM(){
		ArrayList<Tipologia> tipologias = Gestao.getTipologias();
		ArrayList<Departamento> departamentos = Gestao.getDepartamentos();
		ArrayList<User> users = Gestao.getUsers();
		
		for (Departamento departamento : departamentos) {
			dlmDepartamentos.addElement(departamento.getNome());
		}
		
		for (Tipologia tipologia : tipologias) {
			dlmTipologias.addElement(tipologia.getNome());
		}
		
		for (User user : users) {
			String string = "";
			
			switch (user.getNivel()) {
				case 1:
					string += "A - ";
					break;
				case 2:
					string += "G - ";
					break;
				case 3:
					string += "S - ";
			}
			
			dlmUsers.addElement(string + user.getNome());
			
			//ordernar dlm
			//????????????
					
			
			
			
		}
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public ConfiguraçõesFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblImagenfundo = new JLabel("");
		lblImagenfundo.setBounds(0, 0, 762, 118);
		ImageIcon imagem1= new ImageIcon(Frame1.class.getResource("/com/horizon/imagens/f.PNG"));
		Image imag1 = imagem1.getImage().getScaledInstance(lblImagenfundo.getWidth(), lblImagenfundo.getHeight(), Image.SCALE_DEFAULT);
		
		lblImagenfundo.setIcon(new ImageIcon(imag1));

		contentPane.add(lblImagenfundo);
		
		JList<String> lstTipologias = new JList<String>(dlmTipologias);
		lstTipologias.setBackground(Color.GRAY);
		lstTipologias.setForeground(Color.WHITE);
		lstTipologias.setBounds(10, 162, 232, 266);
		contentPane.add(lstTipologias);
		
		JList<String> lstDepartamentos = new JList<String>(dlmDepartamentos);
		lstDepartamentos.setForeground(Color.WHITE);
		lstDepartamentos.setBackground(Color.GRAY);
		lstDepartamentos.setBounds(522, 162, 220, 266);
		contentPane.add(lstDepartamentos);
		
		JButton btnAdicionarTipologia = new JButton("Adicionar");
		btnAdicionarTipologia.setForeground(new Color(255, 255, 255));
		btnAdicionarTipologia.setBackground(new Color(50, 205, 50));
		btnAdicionarTipologia.addActionListener(new ActionListener() {
			//como adicionar novas tipologias na lista
			public void actionPerformed(ActionEvent arg0) {
				
				//verificar se algum item na lista está selectionado usando isSelectionEmpty
				if (lstTipologias.isSelectionEmpty()){ //se estiver vazia...
					//pedir o nome da nova tipologia e adicionar-la ao arraylist de tipologias no gestor e à defaultlistmodel
					String novoNome= JOptionPane.showInputDialog("Insira o nome da nova tipologia");
					if (novoNome!=null){
					Gestao.adicionarTipologia(novoNome);
					dlmTipologias.addElement(novoNome);
					}
					else
						System.out.println("Cancelado");
					
					
				}
				else { //se alguma tipologia estiver seleccionada
					//pedir o novo nome da tipologia selecctionada e alterar-lhe o nome no array list e na dlm 
					String novoNome = JOptionPane.showInputDialog("Insira o novo nome da tipologia " + lstTipologias.getSelectedValue());
					if (novoNome!=null){
					Gestao.getTipologiaPorNome(lstTipologias.getSelectedValue()).setNome(novoNome);
					dlmTipologias.setElementAt(novoNome, lstTipologias.getSelectedIndex());
					}
					else
						System.out.println("Cancelado");
					
					
				}
				
				
				//no final, gravar todas as alterações feitas quaisquer que sejam
				try {
					Gestao.gravarTipologias();
				} catch (FileNotFoundException e) {
					System.out.println("fnfe");
				}
			}
		});
		btnAdicionarTipologia.setBounds(32, 439, 89, 23);
		contentPane.add(btnAdicionarTipologia);
		
		JButton btnRemoverTipologia = new JButton("Remover");
		btnRemoverTipologia.setForeground(new Color(255, 255, 255));
		btnRemoverTipologia.setBackground(new Color(255, 51, 51));
		btnRemoverTipologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//eliminar tipologias
				
				if (lstTipologias.isSelectionEmpty()){ //se não selecionou nenhuma tipologia...
					//perguntar se quer eliminar todas as tipologias
					if(JOptionPane.showConfirmDialog(contentPane, "Não selecionou nenhuma tipologia! Deseja eliminar todas as tipologias não associadas a um evento?")==0){
						ArrayList<Tipologia> tipologiasEliminadas = Gestao.eliminarTodasTipologiasSeguro(); //método que apenas elimina tipologias não associadas e devolve-as num array list
						
						for (Tipologia tipologia : tipologiasEliminadas) {
							if (dlmTipologias.contains(tipologia.getNome()))
								dlmTipologias.removeElement(tipologia.getNome());
						}
						
						JOptionPane.showMessageDialog(contentPane, tipologiasEliminadas.size() + " tipologias eliminadas.");
						
					}
					
				}
				
				else { //se selecionou uma tipologia
					
					//se a tipologia já está associada a um evento não deixar eliminar
					if (Gestao.isTipologiaEmEvento(lstTipologias.getSelectedValue())) {
						JOptionPane.showMessageDialog(contentPane, "Esta tipologia já está associada a um evento!");
					}
					
					//se não estiver, simplesmente eliminar a tipologia
					else {
					Gestao.eliminarTipologia(lstTipologias.getSelectedValue());
					dlmTipologias.remove(lstTipologias.getSelectedIndex());
					}
				}
				
				//gravar quaisquer aalterações feitas
				try {
					Gestao.gravarTipologias();
				}
				
				catch (FileNotFoundException e){
					System.out.println("fnfe");
				}
			}
		});
		btnRemoverTipologia.setBounds(131, 439, 89, 23);
		contentPane.add(btnRemoverTipologia);
		
		JButton btnRemoverDepartamento = new JButton("Remover");
		btnRemoverDepartamento.setForeground(new Color(255, 255, 255));
		btnRemoverDepartamento.setBackground(new Color(255, 51, 51));
		btnRemoverDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//eliminar tipologias
				
				if (lstDepartamentos.isSelectionEmpty()){ //se não selecionou nenhum departamento...
					//perguntar se quer eliminar todos os departamentos
					if(JOptionPane.showConfirmDialog(contentPane, "Não selecionou nenhum departamento! Deseja eliminar todos os departamentos não associadas a um evento?")==0){
						ArrayList<Departamento> departamentosEliminados = Gestao.eliminarTodosDepartamentosSeguro(); //método que apenas elimina departamenso não associadas e devolve-as num array list
						
						for (Departamento departamento : departamentosEliminados) {
							if (dlmDepartamentos.contains(departamento.getNome()))
								dlmDepartamentos.removeElement(departamento.getNome());
						}
						
						JOptionPane.showMessageDialog(contentPane, departamentosEliminados.size() + " departamentos eliminados.");
						
					}
					
				}
				
				else { //se selecionou um  departamento
					
					//se o departamento já está associada a um evento não deixar eliminar
					if (Gestao.isDepartamentoEmEvento(lstTipologias.getSelectedValue())) {
						JOptionPane.showMessageDialog(contentPane, "Este departamento já está associado a um evento!");
					}
					
					//se não estiver, simplesmente eliminar o departamento
					else {
						Gestao.eliminarDepartamento(lstDepartamentos.getSelectedValue());
						dlmDepartamentos.remove(lstDepartamentos.getSelectedIndex());
					}
				}
				
				//gravar quaisquer alterações feitas
				try {
					Gestao.gravarDepartamentos();
				}
				
				catch (FileNotFoundException e){
					System.out.println("fnfe");
				}
				
				
				
				
				
				
				
			}
		});
		btnRemoverDepartamento.setBounds(644, 439, 89, 23);
		contentPane.add(btnRemoverDepartamento);
		
		JButton btnAdicionarDepartamento = new JButton("Adicionar");
		btnAdicionarDepartamento.setForeground(new Color(255, 255, 255));
		btnAdicionarDepartamento.setBackground(new Color(50, 205, 50));
		btnAdicionarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//verificar se algum item na lista está selectionado usando isSelectionEmpty
				if (lstDepartamentos.isSelectionEmpty()){ //se estiver vazia...
					//pedir o nome do novo departamento e adicionar-lo ao arraylist de departamentos no gestor e à defaultlistmodel
					String novoNome= JOptionPane.showInputDialog("Insira o nome do novo departamento");
					if (novoNome!=null){
					Gestao.adicionarDepartamento(novoNome);
					dlmDepartamentos.addElement(novoNome);
						
					}
					else
						System.out.println("Cancelado");
					
					
				}
				else { //se alguma departamento estiver seleccionado
					//pedir o novo nome do departamento selecctionada«o e alterar-lhe o nome no array list e na dlm 
					String novoNome = JOptionPane.showInputDialog("Insira o novo nome da tipologia " + lstTipologias.getSelectedValue());
					if (novoNome!=null){
						Gestao.getDepartamentoPorNome(lstDepartamentos.getSelectedValue()).setNome(novoNome);
						dlmDepartamentos.setElementAt(novoNome, lstDepartamentos.getSelectedIndex());
					}
					else
						System.out.println("Cancelado");
					
					
				}
				
				
				//no final, gravar todas as alterações feitas quaisquer que sejam
				try {
					Gestao.gravarDepartamentos();
				} catch (FileNotFoundException e) {
					System.out.println("fnfe");
				}
			}
		});
		btnAdicionarDepartamento.setBounds(545, 439, 89, 23);
		contentPane.add(btnAdicionarDepartamento);
		
		JLabel lblTipologias = new JLabel("Tipologias");
		lblTipologias.setForeground(new Color(153, 204, 0));
		lblTipologias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipologias.setBounds(10, 129, 120, 30);
		contentPane.add(lblTipologias);
		
		JLabel lblDepartamentos = new JLabel("Departamentos");
		lblDepartamentos.setForeground(new Color(153, 204, 0));
		lblDepartamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDepartamentos.setBounds(522, 129, 144, 30);
		contentPane.add(lblDepartamentos);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 443, 46, 14);
		contentPane.add(label);
		
		JLabel lblUtilizadores = new JLabel("Utilizadores");
		lblUtilizadores.setForeground(new Color(153, 204, 0));
		lblUtilizadores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUtilizadores.setBounds(251, 129, 120, 30);
		contentPane.add(lblUtilizadores);
		
		JList<String> lstUsers = new JList<String>(dlmUsers);
		lstUsers.setBackground(Color.GRAY);
		lstUsers.setForeground(Color.WHITE);
		lstUsers.setBounds(252, 162, 260, 266);
		contentPane.add(lstUsers);
		
		JButton alterarUser = new JButton("Alterar");
		alterarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lstUsers.isSelectionEmpty())
					JOptionPane.showMessageDialog(contentPane, "Não selecionou nenhum User!");
				else {
					String nomeUser = lstUsers.getSelectedValue().substring(4);
					System.out.println(Gestao.getUser(nomeUser));
					System.out.println(user);
					if(nomeUser.equals(user.getNome())){
						JOptionPane.showMessageDialog(contentPane, "Não pode alterar o seu próprio nível!");
					}
					else{
						int option = JOptionPane.showOptionDialog(contentPane, "Qual nível com que o user dever ficar?",
						 		"Alterar Nível",
						 		JOptionPane.OK_CANCEL_OPTION,
						 		JOptionPane.INFORMATION_MESSAGE,
								null,
								new String[]{"Aministrador", "Gestor", "Secretariado"},
								"default");
						System.out.println(option);
						if (option>=0){
						Gestao.getUser(nomeUser).setNivel(option+1);
						
						switch (option){
						
						case 0:
							dlmUsers.set(lstUsers.getSelectedIndex(), "A - " + nomeUser);
							break;
							
						case 1:
							dlmUsers.set(lstUsers.getSelectedIndex(), "G - " + nomeUser);
							break;
						
						case 2:
							dlmUsers.set(lstUsers.getSelectedIndex(), "S - " + nomeUser);
							break;
						
						
						}
						
						}
					}
					
				}
				
				//gravar alterações
				try{
				Gestao.gravarUsers();
				}
				catch(FileNotFoundException fnfe) {
					System.out.println("fnfe");
				}
									
			}
		});
		alterarUser.setForeground(Color.WHITE);
		alterarUser.setBackground(new Color(50, 205, 50));
		alterarUser.setBounds(294, 439, 89, 23);
		contentPane.add(alterarUser);
		
		JButton removerUser = new JButton("Remover");
		removerUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (lstUsers.isSelectionEmpty()){
					JOptionPane.showMessageDialog(contentPane, "Não seleciounou nenhum user!");
				}
				
				else {
					String nomeUser = lstUsers.getSelectedValue().substring(4);
					if(Gestao.getUser(nomeUser).seAdmin())
					{
						JOptionPane.showMessageDialog(contentPane, "Não pode eliminar um administrador!");
					}
					
					else
					{
						Gestao.eliminarUser(nomeUser);
						dlmUsers.remove(lstUsers.getSelectedIndex());
					}
				}
			
				//gravar alterações
				try {
					Gestao.gravarUsers();
				} catch (FileNotFoundException e1) {
					System.out.println("fnfe");
				}
			
			
			}
		});
		removerUser.setForeground(Color.WHITE);
		removerUser.setBackground(new Color(255, 51, 51));
		removerUser.setBounds(393, 439, 89, 23);
		contentPane.add(removerUser);
		
		preencherDLM();
		
		lstTipologias.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (lstTipologias.isSelectionEmpty())
					btnAdicionarTipologia.setText("Adicionar");
				else
					btnAdicionarTipologia.setText("Alterar");
			}
		});
		
		
		lstDepartamentos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (lstDepartamentos.isSelectionEmpty())
					btnAdicionarDepartamento .setText("Adicionar");
				else
					btnAdicionarDepartamento .setText("Alterar");
			}
			
			
			
		});
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent ev) {
				System.out.println("released");
				System.out.println(ev.getPoint());
				
				if(!lstTipologias.getBounds().contains(ev.getPoint())){
					lstTipologias.clearSelection();
					System.out.println("não está em tipologias");
				}

				if(!lstDepartamentos.getBounds().contains(ev.getPoint())) {
					lstDepartamentos.clearSelection();
					System.out.println("não está em departamentos");
				}
				
				if(!lstUsers.getBounds().contains(ev.getPoint())){
					lstUsers.clearSelection();
					System.out.println("não está em users");
				}
				
				
			}
		});
	}
}
