package com.example.truco.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Rodada {
	
	private Baralho baralho;
	
	private Carta coringa;
	
	private Jogador jogador;
	
	private Jogador maquina;
	
	private Integer valorRodada;

	public Rodada(Baralho baralho, Jogador jogador, Jogador maquina) {
		super();
		this.baralho = baralho;
		this.jogador = jogador;
		this.maquina = maquina;
		this.valorRodada = 1;
	}
	
	public Vencedor iniciaRodada() {
		Scanner scanner = new Scanner(System.in);
		Integer turnoVencidoJogador = 0;
		Integer turnoVencidoMaquina = 0;
		List<Carta> cartasJogador = new ArrayList<Carta>();
		List<Carta> cartasMaquina = new ArrayList<Carta>();
		
		distribuirCartas(cartasJogador,cartasMaquina);
		
		System.out.println("O CORINGA DA RODADA É: " + coringa.getValor() + " " + coringa.getNaipe());
		
		Boolean empaxou = false;
		
		int turno = 0;
		
		Jogador vencedorTurno1 = null;
		
		while((turnoVencidoJogador != 2 && turnoVencidoMaquina != 2) && (cartasJogador.size() > 0 && cartasMaquina.size() > 0)) {
			turno++;
			System.out.println("Suas cartas: ");
			int i = 1;
			for(Carta carta : cartasJogador) {
				System.out.println("Carta " + i + " " + carta.getValor() + " " + carta.getNaipe());
				i++;
			}
			
			String escolha = "1";

			do {
				if(!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3") && !escolha.equals("4")) {
					System.out.println("Escolha invalida!");
				}
				if(escolha.equals("4")) {
					if(this.valorRodada == 3) {
						System.out.println("Escolha invalida!");
					}
					else {
						System.out.println("TRUCO LADRAO!");
						System.out.println("DESCE VAGABUNDO!");
						this.valorRodada = 3;
					}
					
				}
				
				System.out.println("Selecione qual ação quer realizar: ");
				int j = 1;
				for(Carta carta : cartasJogador) {
					System.out.println(j + ": Jogar carta " + j);
					j++;
				}
				if(this.valorRodada < 3) {
					System.out.println(4 + ": Trucar");
				}
				
				escolha = scanner.nextLine();
			}while(!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3"));
			
			Carta cartaJogador = null;
			Carta cartaMaquina = null;
			
			switch (escolha) {
				case "1": {
					System.out.println("Voce jogou a carta : " + cartasJogador.get(0).getValor() + " " + cartasJogador.get(0).getNaipe());
					cartaJogador = cartasJogador.get(0);
					cartasJogador.remove(0);
					break;
				}
				case "2": {
					System.out.println("Voce jogou a carta : " + cartasJogador.get(1).getValor() + " " + cartasJogador.get(1).getNaipe());
					cartaJogador = cartasJogador.get(1);
					cartasJogador.remove(1);
					break;
				}
				case "3": {
					System.out.println("Voce jogou a carta : " + cartasJogador.get(2).getValor() + " " + cartasJogador.get(2).getNaipe());
					cartaJogador = cartasJogador.get(2);
					cartasJogador.remove(2);
					break;
				}
				
			}
			
			System.out.println("Seu adversario jogou : " + cartasMaquina.get(0).getValor() + " " + cartasMaquina.get(0).getNaipe());
			cartaMaquina = cartasMaquina.get(0);
			cartasMaquina.remove(0);
			
			
			if(empaxou) {
				if(turnoVencidoJogador == 1) {
					turnoVencidoJogador = 2;
				}else if(turnoVencidoMaquina == 1){
					turnoVencidoMaquina = 2;
				} else if(turnoVencidoJogador == 1 && turnoVencidoMaquina == 1) {
					if(vencedorTurno1 == jogador) {
						turnoVencidoJogador = 2;
					} else {
						turnoVencidoMaquina = 2;
					}
				}
				if(cartaJogador.getForca() > cartaMaquina.getForca()) {
					System.out.println("Voce venceu o turno!");
					turnoVencidoJogador = 2;
				} else if(cartaJogador.getForca() < cartaMaquina.getForca()){
					System.out.println("A Maquina venceu o turno!");
					turnoVencidoMaquina = 2;
				} else {
					System.out.println("Empaxou, mostre sua carta mais forte!");
					empaxou = true;
				}
			} else {
				if(cartaJogador.getForca() > cartaMaquina.getForca()) {
					System.out.println("Voce venceu o turno!");
					if(turno == 1) {
						vencedorTurno1 = jogador;
					}
					turnoVencidoJogador++;
				} else if(cartaJogador.getForca() < cartaMaquina.getForca()){
					System.out.println("A Maquina venceu o turno!");
					if(turno == 1) {
						vencedorTurno1 = maquina;
					}
					turnoVencidoMaquina++;
				} else {
					System.out.println("Empaxou, mostre sua carta mais forte!");
					empaxou = true;
				}
			}
			
		}
		
		if(turnoVencidoJogador == 2) {
			return new Vencedor(jogador, valorRodada);
		} else if(turnoVencidoMaquina == 2) {
			return new Vencedor(maquina, valorRodada);
		}
		
		return null;
		
	}
	
	private void distribuirCartas(List<Carta> cartasJogador, List<Carta> cartasMaquina) {
		Random random = new Random();
		List<Carta> cartasEmJogo = new ArrayList<Carta>();
		
		for(int i = 0; i < 7; i++) {
			Carta carta = baralho.getCartas().get(random.nextInt(40));
			if(cartasEmJogo.contains(carta)) {
				carta = baralho.getCartas().get(random.nextInt(40));
			}
			if(cartasJogador.size() < 3) {
				cartasJogador.add(carta);
				cartasEmJogo.add(carta);
			}else if(cartasMaquina.size() < 3) {
				cartasMaquina.add(carta);
				cartasEmJogo.add(carta);
			}
			coringa = carta;
		}


		for(Carta carta : cartasJogador) {
			ajustarForcaCarta(carta, coringa);
		}
		
		for(Carta carta : cartasMaquina) {
			ajustarForcaCarta(carta, coringa);
		}
		
	}
	
	private void ajustarForcaCarta(Carta carta, Carta coringa) {
	    if ((coringa.getForca() == 10 && carta.getForca() == 1) || coringa.getForca() + 1 == carta.getForca()) {
	        int forca = 0;
	       
	        switch (carta.getNaipe()) {
	            case "Ouro":
	                forca = 100;
	                break;
	            case "Espada":
	                forca = 200;
	                break;
	            case "Copas":
	                forca = 300;
	                break;
	            case "Paus":
	                forca = 400;
	                break;
	        }

	        carta.setForca(forca);
	    }
	}

	public class Vencedor {
		private Jogador vencedor;
		private Integer pontos;
		
		public Vencedor(Jogador vencedor, Integer pontos) {
			super();
			this.vencedor = vencedor;
			this.pontos = pontos;
		}

		public Jogador getVencedor() {
			return vencedor;
		}

		public void setVencedor(Jogador vencedor) {
			this.vencedor = vencedor;
		}

		public Integer getPontos() {
			return pontos;
		}

		public void setPontos(Integer pontos) {
			this.pontos = pontos;
		}
		
		
	}
}
