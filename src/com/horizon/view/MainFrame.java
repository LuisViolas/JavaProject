package com.horizon.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.horizon.model.User;
import com.horizon.model.Gestao;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;

public class MainFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPassword;
	
	private JTable table;
	
	private static String[] colunas = {"Código", "Nome", "Nível"};
	private static DefaultTableModel dtmUsers = new DefaultTableModel(colunas, 0);	
	
	
	private static void preencherTable(){
		ArrayList<User> users = new ArrayList<User>();
		users.addAll(Gestao.getUsers());
		for (User user : users) {
			String id = Integer.toString(user.getId());
			String nome = user.getNome();
			String nivel = Integer.toString(user.getNivel());
			
			Object[] usersTable = {id, nome, nivel};
			dtmUsers.addRow(usersTable);
			System.out.println(usersTable);
			System.out.println(nome);
		}
		
		
	}
	
	

	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					Gestao.carregarUsers();
					if (Gestao.getUser("pedro") == null){
					System.out.println("falhado");}
					else {
						System.out.println("encontrou");
					}
					preencherTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 159);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(76, 8, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 33, 62, 14);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(76, 30, 86, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JSpinner spnNivel = new JSpinner();
		spnNivel.setModel(new SpinnerNumberModel(1, null, 3, 1));
		spnNivel.setBounds(76, 61, 86, 20);
		contentPane.add(spnNivel);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestao.adicionarUser(Integer.parseInt(spnNivel.getValue().toString()), txtNome.getText(), txtPassword.getText());
				try {
					Gestao.gravarUsers();
				} catch (FileNotFoundException e) {
					System.out.println("fnfe");
				}
				
				
			}
		});
		btnAdicionar.setBounds(10, 92, 142, 23);
		contentPane.add(btnAdicionar);
		
		
		JLabel lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(10, 58, 46, 14);
		contentPane.add(lblNvel);
		
		JScrollPane scrollPane= new JScrollPane();
		table = new JTable(dtmUsers);
		table.setBounds(172, 11, 193, 98);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
