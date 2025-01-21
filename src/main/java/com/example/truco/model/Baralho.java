package com.example.truco.model;

import java.util.ArrayList;
import java.util.List;

public class Baralho {

	private List<Carta> cartas;

	public Baralho() {
		super();
		this.cartas = new ArrayList<Carta>();
		generateBaralho();
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public void generateBaralho() {
		String[] naipes = {"Ouro", "Espada", "Copas", "Paus"};
	    String[] valores = {"4", "5", "6", "7", "Q", "J", "K", "A", "2", "3"};

	    for (int i = 0; i < valores.length; i++) {
	        for (int j = 0; j < naipes.length; j++) {
	            Carta carta = new Carta(naipes[j], valores[i], i + 1);
	            this.cartas.add(carta);
	        }
	    }
	}
}
