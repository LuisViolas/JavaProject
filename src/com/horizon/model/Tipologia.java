package com.horizon.model;

public class Tipologia {
	private int codigo;
	private String nome;

	//CONSTRUCTOR
	public Tipologia(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	
	//GETTERS e SETTERS
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
}
