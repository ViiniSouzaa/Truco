package com.example.truco.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.ia.JogadaMaquina;

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
		JogadaMaquina jogadaMaquina =  new JogadaMaquina();
		Integer turnoVencidoJogador = 0;
		Integer turnoVencidoMaquina = 0;
		List<Carta> cartasJogador = new ArrayList<Carta>();
		List<Carta> cartasMaquina = new ArrayList<Carta>();
		
		distribuirCartas(cartasJogador,cartasMaquina);
		
		System.out.println("O CORINGA DA RODADA É: " + coringa.getValor() + " " + coringa.getNaipe());
		
		Boolean empaxou = false;
		
		int turno = 0;
		
		Jogador vencedorTurno1 = null;
		
		while((turnoVencidoJogador != 2 && turnoVencidoMaquina != 2) 
				&& (cartasJogador.size() > 0 && cartasMaquina.size() > 0)) {
			turno++;
			
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
			}else {
				System.out.println("Suas cartas: ");
				int i = 1;
				for(Carta carta : cartasJogador) {
					System.out.println("Carta " + i + " " + carta.getValor() + " " + carta.getNaipe());
					i++;
				}
				
				Integer escolha = 0;

				do {
					if(escolha != 0 && validaEscolha(escolha, turno) == -1) {
						System.out.println("Escolha Invalida!");
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
					if(this.valorRodada >= 3) {
						System.out.println(5 + ": Aumentar");
					}
					
					escolha = scanner.nextInt();
				}while(escolha == -1 || escolha == -2);
				
				Carta cartaJogador = null;
				Carta cartaMaquina = null;
				
				switch (escolha) {
					case 1: {
						System.out.println("Voce jogou a carta : " + cartasJogador.get(0).getValor() + " " + cartasJogador.get(0).getNaipe());
						cartaJogador = cartasJogador.get(0);
						cartasJogador.remove(0);
						break;
					}
					case 2: {
						System.out.println("Voce jogou a carta : " + cartasJogador.get(1).getValor() + " " + cartasJogador.get(1).getNaipe());
						cartaJogador = cartasJogador.get(1);
						cartasJogador.remove(1);
						break;
					}
					case 3: {
						System.out.println("Voce jogou a carta : " + cartasJogador.get(2).getValor() + " " + cartasJogador.get(2).getNaipe());
						cartaJogador = cartasJogador.get(2);
						cartasJogador.remove(2);
						break;
					}
					
				}
				
				//Jogada da maquina
				int indexCartaMaquina = jogadaMaquina.escolheJogada(cartasMaquina, cartaJogador, empaxou);
				if(indexCartaMaquina != 4) {
					cartaMaquina = cartasMaquina.get(indexCartaMaquina);
					cartasMaquina.remove(indexCartaMaquina);
					System.out.println("Seu adversario jogou : " + cartaMaquina.getValor() + " " + cartaMaquina.getNaipe());
				} else { 
					boolean respostaTrucoJogador = respostaTrucoJogador();
					if(respostaTrucoJogador) {
						this.valorRodada = 3;
						indexCartaMaquina = jogadaMaquina.escolheCartaRetorno(cartasMaquina, cartaJogador, empaxou);
						cartaMaquina = cartasMaquina.get(indexCartaMaquina);
						cartasMaquina.remove(indexCartaMaquina);
						System.out.println("Seu adversario jogou : " + cartaMaquina.getValor() + " " + cartaMaquina.getNaipe());
					} else {
						System.err.println("Voce correu!");
						turnoVencidoMaquina = 2;
					}
				}
				
				if(empaxou) {
					
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
						if(turnoVencidoMaquina == 0 && turnoVencidoJogador == 0) {
							System.out.println("Empaxou, mostre sua carta mais forte!");
						}
						empaxou = true;
					}
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
	
	private boolean respostaTrucoJogador() {
		Scanner scanner = new Scanner(System.in);
		
		int escolha = 0;
		
		System.out.println("Seu adversario trucou!");
		while(escolha != 1 && escolha != 2) {
			System.out.println("Para aceitar digite 1, correr digite 2!");
			escolha = scanner.nextInt();
			
			if(escolha != 1 && escolha != 2) {
				System.out.println("Escolha invalida!");
			}
		}
		if(escolha == 1) {
			return true;
		}
		return false;
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
	
	public int validaEscolha(int escolha, int turno) {
		if(turno == 1) {
			if(escolha != 1 && escolha != 2 && escolha != 3 
					&& escolha != 4 && escolha != 5) {
				return -1;
			}
		} else if(turno == 2) {
			if(escolha != 1 && escolha != 2 && escolha != 4 && escolha != 5) {
				return -1;
			}
		} else if(turno == 3) {
			if(escolha != 1 && escolha != 4 && escolha != 5) {
				return -1;
			}
		}
		
		if(escolha == 4) {
			if(this.valorRodada <= 3) {
				return -1;
			}
			else {
				System.out.println("TRUCO LADRAO!");
				System.out.println("DESCE VAGABUNDO!");
				this.valorRodada = 3;
			}
			return -2;
		}
		
		if(escolha == 5) {
			if(this.valorRodada == 3) {
				System.out.println("SEEEEEEIS");
				System.out.println("DESCE VAGABUNDO!");
				this.valorRodada = 6;
			} else if(this.valorRodada == 6) {
				System.out.println("NOVEEEEE");
				System.out.println("DESCE VAGABUNDO!");
				this.valorRodada = 9;
			} else if(this.valorRodada == 9) {
				System.out.println("DOZEEE");
				System.out.println("DESCE VAGABUNDO!");
				this.valorRodada = 12;
			}
			
		}
		
		return escolha;
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
