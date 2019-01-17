package com.horizon.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;





public class Gestao {
	
	private final static String FICHEIRO_USERS = "Users.txt";
	private final static String FICHEIRO_TIPOS = "Tipologias.txt";
	private final static String FICHEIRO_DEPAR = "Departamentos.txt";
	private final static String FICHEIRO_EVENT = "Eventos.txt";
	private final static String FICHEIRO_INSCR = "Inscritos.txt";
	
	private static ArrayList<Tipologia> tipologias = new ArrayList<Tipologia>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
	private static ArrayList<Evento> eventos = new ArrayList<Evento>();
	private static ArrayList<Inscrito> inscritos = new ArrayList<Inscrito>();
	
	
	//GETTERS dos array lists
	//util para fazer tabelas e cenas assim
	public static ArrayList<Tipologia> getTipologias(){
		return tipologias;
	}
	public static ArrayList<User> getUsers(){
		return users;
	}
	public static ArrayList<Departamento> getDepartamentos(){
		return departamentos;
	}
	public static ArrayList<Evento> getEventos(){
		return eventos;
	}
	public static ArrayList<Inscrito> getInscritos(){
		return inscritos;
	}
	
	
	
	//CENAS PARA CADA UM----------------------------------
	//novo item
	//eliminar item
	//carregar items
	//gravar items
	//alterar items (varia para cada um)
	
	//GERAL-----------------------------------------------
	public static void carregarTudo(){
		try{
			carregarUsers();
			carregarDepartamentos();
			carregarTipos();
			carregarEventos();
			carregarInscritos();
			System.out.println("Tudo carregado com sucesso.");
		}
		
		catch (FileNotFoundException e){
			System.out.println("fnfe");
		}
		
		
	}
	
	//----------------------------------------------------
	
	
	//EVENTOS---------------------------------------------
	//TODO
	
	/**
	 * Método para guardar eventos no ficheiro.
	 * @throws FileNotFoundException
	 */
	public static void gravarEventos () throws FileNotFoundException{
		File file = new File(FICHEIRO_EVENT);
		PrintWriter out = new PrintWriter(file);
		
		for (Evento evento : eventos) {
			out.println(evento.getCodigo() + "#" +
						evento.getTipoCodigo() + "#" +
						evento.getDepartCodigo() + "#" +
						evento.getValor() + "#" +
						evento.getNome() + "#" +
						evento.getData() + "#" +
						evento.getHora() + "#" +
						evento.getSala() + "#" +
						evento.getDataLimite() + "#" +
						evento.isPago());
		}
		out.close();
		System.out.println("Eventos gravados");
	}
	
	/**
	 * Método para carregar eventos do ficheiro para o array list.
	 * @throws FileNotFoundException
	 */
	public static void carregarEventos() throws FileNotFoundException{
		File file = new File(FICHEIRO_EVENT);
		Scanner in = new Scanner(file);
		String[] linha;
		Evento evento = null;
		Tipologia tipo = null;
		Departamento depart = null;
		boolean pago = false;
		
		while (in.hasNextLine()){
			linha = in.nextLine().split("#");
			
			for (Tipologia tipologia : tipologias) {
				if (tipologia.getCodigo()==Integer.parseInt(linha[1]))
					tipo = tipologia;
			}
			
			for (Departamento departamento : departamentos) {
				if (departamento.getCodigo()==Integer.parseInt(linha[2]))
					depart = departamento;
			}
			
			if (linha[9].equals("true"))
				pago=true;
			else 
				pago = false;
			
			
			evento = new Evento(Integer.parseInt(linha[0]),
								tipo,
								depart,
								Integer.parseInt(linha[3]),
								linha[4],
								linha[5],
								linha[6],
								linha[7],
								linha[8],
								pago);
			eventos.add(evento);
			System.out.println("Evento " + linha[4] + ": " + pago );
			
		}
		System.out.println("Eventos carregados.");
		in.close();
	}
	

	/**
	 * Método para carregar eventos do ficheiro para o array list que também carrega tipologias e departamentos.
	 * @throws FileNotFoundException
	 */
	public static void carregarEventosComDependencias() throws FileNotFoundException{
		carregarDepartamentos();
		carregarTipos();
		File file = new File(FICHEIRO_EVENT);
		Scanner in = new Scanner(file);
		String[] linha;
		Evento evento = null;
		Tipologia tipo = null;
		Departamento depart = null;
		boolean pago = false; 
		
		while (in.hasNextLine()){
			linha = in.nextLine().split("#");
			
			for (Tipologia tipologia : tipologias) {
				if (tipologia.getCodigo()==Integer.parseInt(linha[1]))
					tipo = tipologia;
			}
			
			for (Departamento departamento : departamentos) {
				if (departamento.getCodigo()==Integer.parseInt(linha[2]))
					depart = departamento;
			}
			
			if (linha[9].equals("true"))
				pago=true;
			else 
				pago = false;
			
			evento = new Evento(Integer.parseInt(linha[0]),
								tipo,
								depart,
								Integer.parseInt(linha[3]),
								linha[4],
								linha[5],
								linha[6],
								linha[7],
								linha[8],
								pago);
			eventos.add(evento);
			
		}
		System.out.println("Eventos carregados.");
		in.close();
	}
	
	
	/**
	 * Método para adicionar um evento. 
	 * @param codigo Código do evento.
	 * @param tipologia Tipologia do evento.
	 * @param depart Departamento do evento.
	 * @param valor Valor monetário do evento. 0 se não pago.
	 * @param nome Nome do evento. 
	 * @param data Data do evento.
	 * @param hora Hora do evento.
	 * @param sala Sala do evento.
	 * @param dataLimite Data limite de inscrições no evento.
	 * @param pago Se o evento é pago ou não.
	 */
	public static void adicionarEvento (int codigo, Tipologia tipologia, Departamento depart, int valor, String nome, String data, String hora, String sala, String dataLimite, boolean pago) {
		Evento event = new Evento(codigo, tipologia, depart, valor, nome, data, hora, sala, dataLimite, pago);
		eventos.add(event);
		
		System.out.println("Evento " + nome + " adicionado.");
	}
	
	
	/**
	 * Método para adicionar um evento que aceita tipologia e departamento como STRING.
	 * @param codigo Código do evento.
	 * @param tipologia Tipologia do evento (STRING).
	 * @param depart Departamento do evento (STRING).
	 * @param valor Valor monetário do evento. 0 se não pago.
	 * @param nome Nome do evento. 
	 * @param data Data do evento.
	 * @param hora Hora do evento.
	 * @param sala Sala do evento.
	 * @param dataLimite Data limite de inscrições no evento.
	 * @param pago Se o evento é pago ou não.
	 */
	public static void adicionarEvento (String tipo, String depart, int valor, String nome, String data, String hora, String sala, String dataLimite, boolean pago) {
		Tipologia tipo2 = null;
		Departamento depart2 = null;
		Evento event = null;
		
		for (Departamento departamento : departamentos) {
			if (departamento.getNome().equals(depart))
				depart2 = departamento;
		}
		
		for (Tipologia tipologia : tipologias) {
			if (tipologia.getNome().equals(tipo))
				tipo2 = tipologia;
		}
		try {
			event = new Evento(eventos.get(eventos.size()-1).getCodigo()+1, tipo2, depart2, valor, nome, data, hora, sala, dataLimite, pago);
		}
		
		catch (IndexOutOfBoundsException e) {
			event = new Evento(0, tipo2, depart2, valor, nome, data, hora, sala, dataLimite, pago);
		}
		eventos.add(event);
		
		System.out.println("Evento " + nome + " adicionado.");
	}
	
	/**
	 * Método para remover um evento pelo nome.
	 * @param nome Nome do evento a remover.
	 */
	public static void eliminarEvento(String nome){
		Evento eventoRemover = null;
		for (Evento evento : eventos) {
			if (evento.getNome().equals(nome))
				eventoRemover = evento;
				
		}	

		eventos.remove(eventoRemover);
	}
	
	/**
	 * Método para alterar o conteúdo de um evento. Guarda tudo de uma vez. Identifica o evento a alterar pelo seu código (que nunca deve ser alterado). Aceita tipologia e departamento como string.
	 * @param codigo Código do evento.
	 * @param tipologia Tipologia do evento (STRING).
	 * @param depart Departamento do evento (STRING).
	 * @param valor Valor monetário do evento. 0 se não pago.
	 * @param nome Nome do evento. 
	 * @param data Data do evento.
	 * @param hora Hora do evento.
	 * @param sala Sala do evento.
	 * @param dataLimite Data limite de inscrições no evento.
	 * @param pago Se o evento é pago ou não.
	 */
	public void alterarEvento(int codigo, String tipo, String depart, int valor, String nome, String data, String hora, String sala, String dataLimite, boolean pago){
		Tipologia tipo2 = null;
		Departamento depart2 = null;
		
		for (Departamento departamento : departamentos) {
			if (departamento.getNome().equals(depart))
				depart2 = departamento;
		}
		
		for (Tipologia tipologia : tipologias) {
			if (tipologia.getNome().equals(tipo))
				tipo2 = tipologia;
		}
		
		for (Evento evento : eventos) {
			if (evento.getCodigo()==codigo){
				evento.setNome(nome);
				evento.setTipologia(tipo2);
				evento.setDepart(depart2);
				evento.setValor(valor);
				evento.setData(data);
				evento.setDataLimite(dataLimite);
				evento.setHora(hora);
				evento.setSala(sala);
				evento.setPago(pago);
			}
			
		}
	}
	
	public static Evento getEventoPorNome(String nome){
		for (Evento evento : eventos) {
			if (evento.getNome().equals(nome))
				return evento;
		}
		
		return null;
	}
	
	
	//----------------------------------------------------
	
	
	
	
	//INSCRITOS-------------------------------------------
	//TODO
	
	
	/**
	 * Método para gravar inscritos em ficheiro.
	 * @throws FileNotFoundException
	 */
	public static void gravarInscritos() throws FileNotFoundException{
		File file = new File(FICHEIRO_INSCR);
		PrintWriter out = new PrintWriter(file);
		
		for (Inscrito inscrito : inscritos) {
			out.println(inscrito.getCodigo() + "#" +  inscrito.getNome() + "#" + inscrito.getEventoCodigo() +"#" + inscrito.getPago() + "#" + inscrito.getValor() + "#" + inscrito.getDataPago() + "#" + inscrito.getEmail());
		}
		
		out.close();
		System.out.println("Inscritos gravados");
	}
	
	
	/**
	 * Método para carregar Inscritos do ficheiro.
	 * @throws FileNotFoundException
	 */
	public static void carregarInscritos() throws FileNotFoundException{
		File file = new File(FICHEIRO_INSCR);
		Scanner in = new Scanner(file);
		String[] linha;
		Inscrito novoInscrito = null;
		Evento eventoRelated = null;
		
		while (in.hasNextLine()){
			linha  = in.nextLine().split("#");
			
			//ir buscar o evento ao qual este inscrito tá associado
			for (Evento evento : eventos) {
				if (evento.getCodigo() == Integer.parseInt(linha[2]))
				eventoRelated = evento;
			}
			
			novoInscrito = new Inscrito(Integer.parseInt(linha[0]), linha[1], eventoRelated, linha[3], Integer.parseInt(linha[4]), linha[5], linha[6]);
			inscritos.add(novoInscrito);
		}
		
		in.close();
		System.out.println("Inscritos carregados");
	}
	
	
	/**
	 * Método para criar novo inscrito. 
	 * @param nome Nome do inscrito.
	 * @param evento Objecto Evento em que o inscrito está inscrito
	 * @param pago Se o inscrito pagou, não, ou se não se aplica.
	 */
	public static void adicionarInscrito(String nome, Evento evento, String pago, int valor, String data, String email){
		Inscrito novoInscrito = null;
		
		try{
			novoInscrito = new Inscrito(inscritos.get(inscritos.size()-1).getCodigo() + 1, nome, evento, pago, valor, data, email);
		}
		catch (IndexOutOfBoundsException e) {
			novoInscrito = new Inscrito(0, nome, evento, pago, valor, data, email);
			
		}
		inscritos.add(novoInscrito);
		System.out.println("Inscrito " + nome + " adicionado");
	}
	
	
	/**
	 * Método para criar novo inscrito que aceita nome do evento em vez de um objecto Evento.
	 * @param nome Nome do inscrito.
	 * @param evento Nome do evento em que o inscrito está inscrito.
	 * @param pago Se o inscrito pagou, não, ou se não se aplica.
	 */
	public static void adicionarInscrito(String nome, String evento, String pago, int valor, String data, String email){
		Inscrito novoInscrito = null;
		
		//encontrar evento com o mesmo nome do recebido e atribuir-lo
		Evento ev = null;
		for (Evento evento2 : eventos ) {
			if (evento2.getNome().equals(nome))
				ev = evento2;
		}
		
		try{
			novoInscrito = new Inscrito(inscritos.get(inscritos.size()-1).getCodigo() + 1, nome, ev, pago, valor, data, email);
		}
		catch (IndexOutOfBoundsException e) {
			novoInscrito = new Inscrito(0, nome, ev, pago, valor, data, email);
		}
		
		inscritos.add(novoInscrito);
		System.out.println("Inscrito " + nome + " adicionado");
	}
	
	/**
	 * Método para eliminar um específico inscrito.
	 * @param nome Nome do inscrito a eliminar.
	 */
	public static void eliminarInscrito(String nome) {
		Inscrito inscritoRemover = null;
		for (Inscrito inscrito : inscritos) {
			if (inscrito.getNome().equals(nome))
				inscritoRemover = inscrito;
		}
		
		inscritos.remove(inscritoRemover);
	}
	
	
	/**
	 * Método para eliminar todos os inscritos num dado evento.
	 * @param nome Nome do evento cujos inscritos se quer eliminar.
	 */
	public static void eliminarInscritosPorEvento(String nome){
		ArrayList<Inscrito> inscritoRemover= new ArrayList<Inscrito>();
		
		for (Inscrito inscrito : inscritos) {
			if (inscrito.GetEventoNome().equals(nome))
				inscritoRemover.add(inscrito);
			
		}
		
		inscritos.removeAll(inscritoRemover);
	}
	
	
	
	/**
	 * Método para alterar o estado de pago de um dado inscrito.
	 * @param nome Nome do inscrito a alterar.
	 * @param pago Estado de pago com que o inscrito dever ficar.
	 */
	public static void alterarPago(String nome, String pago){
		for (Inscrito inscrito : inscritos) {
			if (inscrito.getNome().equals(nome))
				inscrito.setPago(pago);
		}
	}
	
	/**
	 * Método para alterar o evento de um dado inscrito.
	 * @param nome	Nome do inscrito a alterar.
	 * @param evento Evento com o qual o inscrito deve ficar.
	 */
	public static void alterarEvento(String nome, Evento evento){
		for (Inscrito inscrito : inscritos) {
			if (inscrito.getNome().equals(nome))
				inscrito.setEvento(evento);
		}
	}
	
	/**
	 * Método para alterar o evento de um dado incrito que aceita evento como STRING. 
	 * @param nome Nome do inscrito a alterar.
	 * @param evento Nome do evento com o qual o inscrito deve ficar (STRING).
	 */
	public static void alterarEvento(String nome, String evento){ //Deve haver melhor maneira de fazer isto...
		Evento ev = null;
		
		for (Evento evento2 : eventos) {
			if (evento2.getNome().equals(evento))
				ev=evento2;
		}
		
		for (Inscrito inscrito : inscritos) {
			if (inscrito.getNome().equals(nome))
				inscrito.setEvento(ev);
		}
		
	}
	
	
	public static Inscrito getInscritoPorNome(String nome){
		Inscrito novoInscrito = null;
		
		for (Inscrito inscrito : inscritos) {
			if(inscrito.getNome().equals(nome))
				novoInscrito = inscrito;
		}
		
		return novoInscrito;
	}
	
	
	public static Inscrito getInscritoPorID(int codigo){
		Inscrito novoInscrito = null;
		
		for (Inscrito inscrito : inscritos) {
			if(inscrito.getCodigo()==codigo){
				novoInscrito=inscrito;
			}	
		}
		
		return novoInscrito;
	}
	//----------------------------------------------------
	
	
	
	
	//TIPOLOGIAS------------------------------------------
	//TODO
	
	
	/**
	 * Método para carregar todas as tipologias para memória.
	 * @throws FileNotFoundException
	 */
	public static void carregarTipos() throws FileNotFoundException{
		File file = new File(FICHEIRO_TIPOS);
		Scanner in = new Scanner(file);
		String[] linha;
		Tipologia tipo = null;
		while (in.hasNextLine()){
			linha = in.nextLine().split("#");
			tipo = new Tipologia(Integer.parseInt(linha[0]), linha[1]);
			tipologias.add(tipo);

			System.out.println("Tipologia "+ linha[1] + " carregada.");
		}
		
		in.close();
		System.out.println("Tipologias carregadas");
	}
	
	/**
	 * Método para gravar tipologias em ficheiro.
	 * @throws FileNotFoundException
	 */
	public static void gravarTipologias() throws FileNotFoundException {
		File file = new File(FICHEIRO_TIPOS);
		PrintWriter out = new PrintWriter(file);
		
		for (Tipologia tipologia : tipologias) {
			out.println(tipologia.getCodigo() + "#" + tipologia.getNome());
		}
		out.close();
		System.out.println("Tipologias gravadas");
	}
	
	/**
	 * Método para adicionar uma tipologia. 
	 * @param nome Nome da nova tipologia.
	 */
	public static void adicionarTipologia(String nome){
		Tipologia novaTipologia = null;
		
		try {
			novaTipologia = new Tipologia (tipologias.get(tipologias.size()-1).getCodigo()+1, nome);
		}
		catch (IndexOutOfBoundsException e){
			novaTipologia = new Tipologia (0, nome);
		}
		tipologias.add(novaTipologia);
		System.out.println("Tipologia adicionada");
	}
	
	
	/**
	 * Método para alterar o nome de uma tipologia.
	 * @param nome	Nome da tipologia a alterar.
	 * @param nome2 Nome com o qual a tipologia dever ficar.
	 */
	public static void alterarTipologia(String nome, String nome2){
		for (Tipologia tipologia : tipologias) {
			if (tipologia.getNome().equals(nome))
				tipologia.setNome(nome2);
		}
	System.out.println("Nome de " + nome + " alterado para " + nome2);
	}
	
	
	/**
	 * Método para remover uma dada Tipologia.
	 * @param nome Nome da tipologia a eliminar.
	 */
	public static void eliminarTipologia(String nome){
		Tipologia tipoRemover = null;
		for (Tipologia tipologia : tipologias) {
			if (tipologia.getNome().equals(nome))
				tipoRemover = tipologia;
				
		}
		tipologias.remove(tipoRemover);
	System.out.println("Tipologia " + nome + " removida");
	}
	
	/**
	 * Método para eliminar todas as tipologias, NÃO USAR!!
	 */
	public static void eliminarTodasTipologias(){
		tipologias.clear();
		
		System.out.println("Todas tipologias removidas.");
		
	}
	
	
	/**
	 * Método para eliminar todas as tipologias que NÃO ESTÃO ASSOCIADAS a nenhum evento!!
	 * @return Um array list com todas as tipologias que foram eliminadas.
	 */
	public static ArrayList<Tipologia> eliminarTodasTipologiasSeguro(){
		ArrayList<Tipologia> tipologiasRemover = new ArrayList<Tipologia>();
		Boolean encontrou = false;
		
		for (Tipologia tipologia : tipologias) {
			for (Evento evento : eventos) {
				if (evento.getTipologia().equals(tipologia))
					encontrou = true;
			}
			
			if (!encontrou)
				tipologiasRemover.add(tipologia);
			else
				encontrou = false;
		}
		
		tipologias.removeAll(tipologiasRemover);
		return tipologiasRemover;
	}
	
	
	
	/**
	 * Método para devolver uma tipologia por nome.
	 * @param nome Nome da tipologia a devolver
	 * @return Objecto to tipo Tipologia cujo nome é "nome";
	 */
	public static Tipologia getTipologiaPorNome(String nome){
		for (Tipologia tipologia : tipologias) {
			if (tipologia.getNome().equals(nome)){
				return tipologia;
			}
		}
		return null;
	}
	
	/**
	 * Método que descobre se uma dada tipologia já está associada a um evento.
	 * @param nome Nome da tipologia a procurar.
	 * @return Boolean se a tipologia está associada ou não.
	 */
	public static boolean isTipologiaEmEvento(String nome){
		boolean encontrou = false;
		
		Tipologia tipologia=getTipologiaPorNome(nome);
		
		for (Evento evento : eventos) {
			if (evento.getTipologia().equals(tipologia))
				encontrou = true;
		}
		
		
		return encontrou;
	}
	
	
	//----------------------------------------------------
	
	
	
	
	//DEPARTAMENTOS---------------------------------------
	//TODO
	
	/**
	 * Método para graver departamentos.
	 * @throws FileNotFoundException
	 */
	public static void gravarDepartamentos() throws FileNotFoundException{
		File file = new File(FICHEIRO_DEPAR);
		PrintWriter out = new PrintWriter(file);
		
		for (Departamento departamento : departamentos) {
			out.println(departamento.getCodigo() + "#" + departamento.getNome());
		}
		
		out.close();
		System.out.println("Departamentos gravados");
	}
	
	/**
	 * Método para carregar departamentos.
	 * @throws FileNotFoundException
	 */
	public static void carregarDepartamentos() throws FileNotFoundException{
		File file = new File(FICHEIRO_DEPAR);
		Scanner in = new Scanner(file);
		String[] linha;
		Departamento depart = null;
		
		while (in.hasNextLine()){
			linha = in.nextLine().split("#");
			depart = new Departamento(Integer.parseInt(linha[0]), linha[1]);
			departamentos.add(depart);
			System.out.println("Departamento "+ linha[1] + " carregado.");
		}
		
		in.close();
		System.out.println("Departamentos carregados");
	}
	
	/**
	 * Método para adicionar um novo departamento.
	 * @param nome Nome do departamento a adicionar.
	 */
	public static void adicionarDepartamento(String nome){
		Departamento depart = null;
		
		try {
			depart = new Departamento(departamentos.get(departamentos.size()-1).getCodigo()+1, nome);
		}
		catch (IndexOutOfBoundsException e) {
			depart = new Departamento(0, nome);
		}
		departamentos.add(depart);
		System.out.println("Departamento " + nome + " adicionado");
	}
	
	
	/**
	 * Método para remover um departamento.
	 * @param nome Nome do departamento a eliminar.
	 */
	public static void eliminarDepartamento(String nome){
		Departamento departRemover = null;
		
		for (Departamento depart : departamentos) {
			if(depart.getNome().equals(nome))
				departRemover = depart;
		}
		departamentos.remove(departRemover);
	System.out.println("Departamento " + nome + " removido");
	}
	
	/**
	 * Método para alterar o nome de um departamento
	 * @param nome Nome do departamento a alterar
	 * @param nome2 Nome com o qual o departamento deve ficar
	 */
	public static void alterarDepartamento(String nome, String nome2){
		for (Departamento depart : departamentos) {
			if(depart.getNome().equals(nome))
				depart.setNome(nome2);
		}
	}
	
	/**
	 * Método que elimina todos os departamentos não associados a um evento
	 * @return array list com os departamentos eliminados.
	 */
	public static ArrayList<Departamento> eliminarTodosDepartamentosSeguro(){
		ArrayList<Departamento> departamentosRemover = new ArrayList<Departamento>();
		Boolean encontrou = false;
		
		for (Departamento departamento : departamentos) {
			for (Evento evento : eventos) {
				if (evento.getDepart().equals(departamento))
					encontrou = true;
			}
			

			if (!encontrou)
				departamentosRemover.add(departamento);
			else
				encontrou = false;
		}
		
		departamentos.removeAll(departamentosRemover);
		return departamentosRemover;
	}
	
	/**
	 * Método que devolve se um departamento já está associado a um evento.
	 * @param nome Nome do departamento a procurar.
	 * @return Boolean com se o evento já está associado ou não.
	 */
	public static Boolean isDepartamentoEmEvento(String nome){
		Boolean encontrou = false;
		
		Departamento depart = getDepartamentoPorNome(nome);
		
		for (Evento evento : eventos) {
			if (evento.getDepart().equals(depart))
				encontrou = true;						
		}
		
		return encontrou;
	}
	
	public static Departamento getDepartamentoPorNome(String nome){
		for (Departamento departamento : departamentos) {
			if (departamento.getNome().equals(nome))
					return departamento;
		}
		return null;
	}
	
	//----------------------------------------------------
	
	
	
	
	//USERS-----------------------------------------------
	//TODO
	
	/**
	 * Método para gravar todos os users num ArrayList do tipo User para o ficheiro Users.txt
	 * @param users	ArrayList do tipo User a gravar
	 * @throws FileNotFoundException
	 */
	public static void gravarUsers() throws FileNotFoundException{
		File file = new File(FICHEIRO_USERS);
		PrintWriter out = new PrintWriter(file);
		
		for (User user: users) {
			out.println(user.getId() + "#" + user.getNivel() + "#" + user.getNome() + "#" + user.getPassword());			
		}
		System.out.println("Users gravados");
		out.close();
	}
	
	
	
	/**
	 * Método para carregar todos os users em Users.txt para uma colecção ArrayList
	 * @return ArrayList<User> com todos os users em User.txt
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<User> carregarUsers() throws FileNotFoundException{
		File file = new File(FICHEIRO_USERS);
		Scanner in = new Scanner(file);
		String[] linha;
		User novoUser = null; 
		
		while (in.hasNextLine()) {
			linha = in.nextLine().split("#");
			novoUser = new User(Integer.parseInt(linha[0]), Integer.parseInt(linha[1]), linha[2], linha[3]);
			users.add(novoUser);			
		}
		in.close();
		System.out.println("Users carregados");
		return users;
	}
	
	
	/**
	 * Método para criar um novo user, separado para conveniência e porque não tenho melhor que fazer..
	 * @param nivel Nível de permissões do user.
	 * @param nome Nome do user.
	 * @param password Pasword do user.
	 * @return Um User construído com os parâmetros recebidos.
	 */
	public static User adicionarUser(int nivel, String nome, String password){
		User novoUser = null;
		
		try{
			novoUser = new User(users.get(users.size()-1).getId()+1, nivel, nome, password);
		}
		catch (IndexOutOfBoundsException e) {
			novoUser = new User(0, nivel, nome, password);
		}
		
		users.add(novoUser);
		System.out.println("User " + nome + " adicionado");
		return novoUser;
	}
	
	
	/**
	 * Método para eliminar apenas um User.
	 * @param nome Nome do user a eliminar.
	 */
	public static void eliminarUser(String nome){
		User userRemover= null;
		
		for (User user : users) {
			if (user.getNome().equals(nome))
				userRemover = user;
		}
		users.remove(userRemover);
		System.out.println("User " + nome + " removido");
	}
	
	
	/**
	 * método para mudar o nivel de um dado User.
	 * @param nome Nome do User a mudar.
	 * @param nivel Nível com o qual o User deve ficar
	 * 				// 1-ADMIN 2-GESTOR 3-SECRETARIADO
	 */
	public static void alterarNivel(String nome, int nivel){
		for (User user : users) {
			if (user.getNome().equals(nome))
				user.setNivel(nivel);
		}
	System.out.println("Nível de " + nome + " alterado para " + nivel);
	}
	
	
	/**Método para alterar a password de um dado User.
	 * @param nome Nome do User a alterar.
	 * @param password Password com a qual o user deve ficar.
	 */
	public static void alterarPassword(String nome, String password){
		for (User user : users) {
			if (user.getPassword().equals(nome))
				user.setPassord(password);
		}
	System.out.println("Password de " + nome + " alterada");
	}
	
	
	
	
	public static void alterarNomeComNomeAntigo(String nome, String nome2){
		for (User user : users) {
			if (user.getNome().equals(nome))
				user.setNome(nome2);
		}
	}
	
	
	/**
	 * Método para alterar a password de um dado user que pede a password antiga.
	 * @param nome Nome do User a alterar.
	 * @param password Password antiga do user a alterar.
	 * @param novaPassword Password com a qual o user deve ficar.
	 */
	public static void alterarPasswordComPasswordAntiga(String nome, String password, String novaPassword){
		for (User user : users) {
			if ((user.getNome().equals(nome))&&(user.getPassword().equals(password))){
				user.setPassord(novaPassword);
			}

			System.out.println(user.getNome() + " " + user.getPassword() );
				
		}
	}
	
	/**
	 * Método para devolver um User pelo nome. Devolve null se não encontrar nada.
	 * @param nome Nome do user a devolver.
	 * @return
	 */
	public static User getUser(String nome){
		User login = null;		
		for (User user : users) {
			if (user.getNome().equals(nome))
				login = user;
		}
		
		return login;
	}
	
	/**
	 * Método para devolver um user pelo nome e password. Devolve null se não encontrar nada.
	 * @param nome Nome do user a devolver.
	 * @param password Password do user a devolver.
	 * @return
	 */
	public static User getUser(String nome, String password){
		User login = null;
		for (User user : users) {
			if ((user.getNome().equals(nome))&&(user.getPassword().equals(password)))
				login = user;
		}
		
		return login;
	}
	


	//----------------------------------------------------
	
	
	
	//VALIDAÇÃO-------------------------------------------
	//TODO
	
	/**
	 * Método para validar strings para que só contenham números, usa NumberFormatEception.
	 * @param string String a validar
	 * @return True se string for parsable para int, false se não for.
	 */
	@SuppressWarnings("unused")
	public static boolean validaNumerico(String string){
		
		try{
			int i = Integer.parseInt(string);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	
	/**
	 * Método para validar strings para datas do formato dd/MM/yyyy, usa DateTimeParseException.
	 * @param string String a validar.
	 * @return True se string for parsable para este formato de data, false se não.
	 */
	@SuppressWarnings("unused")
	public static boolean validaData(String string){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		try{
			localDate = LocalDate.parse(string, dtf);
		}
		
		catch (DateTimeParseException e){
			return false;
		}
		
		return true;
	}
	
	//----------------------------------------------------
	
	
}
