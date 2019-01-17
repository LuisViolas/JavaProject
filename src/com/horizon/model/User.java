package com.horizon.model;

public class User {
	public final int ADMIN = 1;
	public final int GESTOR = 2;
	public final int SECRETARIADO = 1; 
	
	/*  
	 *N�VEIS :
	 *1: ADMIN
	 *2. GESTOR DE EVENTOS
	 *3. SECRETARIADO
	 */		
			
	private int codigo;
	private int nivel;
	private String nome;
	private String password;
	
	private String image;
	
	
	
	 
	//CONSTRUCTOR
	public User(int codigo, int nivel, String nome, String password) {
		super();
		this.codigo = codigo;
		this.nivel = nivel;
		this.nome = nome;
		this.password = password;
		
	}
	@Override
	public String toString() {
		return "User [codigo=" + codigo + ", nivel=" + nivel + ", nome=" + nome + ", password=" + password + "]";
	}
	//GETTERS e SETTERS
	public int getId() {
		return codigo;
	}
	public void setId(int id) {
		this.codigo = id;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassord(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * M�todo para verificar se um determinado usr � de um determinado n�vel. 1 = ADMIN, 2 = GESTOR 3 = SESCRETARIADO
	 * @param nivel N�vel que se pretende verificar
	 * @return True se o user for desse n�vel, False se n�o.
	 */
	public boolean verificarNivel(int nivel){
		if (this.nivel == nivel)
			return true;
		else
			return false;
	}
	
	/**
	 * M�doto que apenas verifica se um dado user � admin. 
	 * @return True se ADMIN, false se n�o. 
	 */
	public boolean seAdmin(){
		if (this.nivel== ADMIN)
			return true;
		else
			return false;
	}
}
