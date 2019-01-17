package com.horizon.model;

public class Inscrito {
	private Evento evento;
	private int codigo;
	private String nome;
	private String pago; //Se pagou = SIM /Se não pagou = NAO / Se não se aplica = NA
	private int valor;
	
	private String dataPago;
	private String email;
	
	
	
	//CONSTRUCTOR
	public Inscrito(int codigo, String nome, Evento evento, String pago, int valor, String data, String email) {
		super();
		this.evento = evento;
		this.codigo = codigo;
		this.nome = nome;
		this.pago = pago;
		this.valor = valor;
		this.dataPago = data;
		this.email = email;
		
	}
	
	
	//GETTERS e SETTERS
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDataPago() {
		return dataPago;
	}
	public void setDataPago(String dataPago) {
		this.dataPago = dataPago;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEventoCodigo(){
		return evento.getCodigo(); 
	}
	public String GetEventoNome(){
		return evento.getNome();
	}
	
	
}
