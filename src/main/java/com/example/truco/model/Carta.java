package com.example.truco.model;

public class Carta {

	private String naipe;
	private String valor;
	private int forca;
	
	public Carta(String naipe, String valor, int forca) {
		super();
		this.naipe = naipe;
		this.valor = valor;
		this.forca = forca;
	}

	public String getNaipe() {
		return naipe;
	}

	public void setNaipe(String naipe) {
		this.naipe = naipe;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	} 
	
	
}
