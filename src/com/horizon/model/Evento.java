package com.horizon.model;

public class Evento {
	private int codigo;
	private Tipologia tipologia;
	private Departamento depart;
	private int valor;
	private String nome;
	private String data;
	private String hora;
	private String sala;
	private String dataLimite;
	private boolean pago;
	
	
	//CONSTRUCTOR
	public Evento(int codigo, Tipologia tipologia, Departamento depart, int valor, String nome, String data, String hora, String sala, String dataLimite, boolean pago) {
		super();
		this.codigo = codigo;
		this.tipologia = tipologia;
		this.depart = depart;
		this.valor = valor;
		this.nome = nome;
		this.data = data;
		this.hora = hora;
		this.sala = sala;
		this.dataLimite = dataLimite;
		this.pago = pago;
	}
	
	
	
	//GETTERS e SETTERS
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Tipologia getTipologia() {
		return tipologia;
	}
	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}
	public Departamento getDepart() {
		return depart;
	}
	public void setDepart(Departamento depart) {
		this.depart = depart;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getDataLimite() {
		return dataLimite;
	}
	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public String getTipoNome(){
		return tipologia.getNome();
	}
	public int getTipoCodigo(){
		return tipologia.getCodigo();
	}
	public String getDepartNome(){
		return depart.getNome();
	}
	public int getDepartCodigo(){
		return depart.getCodigo();
	}
	
	
}
