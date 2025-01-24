package com.example.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.truco.model.Carta;
import com.example.truco.model.Jogador;

public class JogadaMaquina {
	
	
	
	public JogadaMaquina() {
		super();
	}

	public int escolheJogada(List<Carta> cartasMaquina, Carta cartaAdversario, Boolean empaxou, Boolean podeTrucar) {
		Random random = new Random();
		int cartaRetorno = 0;
		
		if(calculaForcaMao(cartasMaquina) > 28 && podeTrucar) {
			return 4;
		}
		
		if(random.nextInt(100) < 19 && calculaForcaMao(cartasMaquina) < 14 ) {
			return 4;
		}
		
		cartaRetorno = escolheCartaRetorno(cartasMaquina, cartaAdversario, empaxou);
		
		return cartaRetorno;
	}
	
	public int escolheCartaRetorno(List<Carta> cartasMaquina, Carta cartaAdversario, Boolean empaxou) {
		int cartaRetorno = 0;
		
		if(empaxou) {
			if(cartasMaquina.size() > 1) {
				for(int i = 1; i < cartasMaquina.size() -1; i++) {
					if(	cartasMaquina.get(cartaRetorno).getForca() <
						cartasMaquina.get(i).getForca()) {
						cartaRetorno = i;
					}
				}
			}
		} else {
			if(cartasMaquina.size() > 1) {
				for(int i = 1; i < cartasMaquina.size(); i++) {
					if(	cartasMaquina.get(cartaRetorno).getForca() <
						cartasMaquina.get(i).getForca()) {
						cartaRetorno = i;
					}
				}
			}
		}
		return cartaRetorno;
	}
	
	private int calculaForcaMao(List<Carta> cartasMaquina) {
		int forcaMao = 0;
		
		for(Carta carta: cartasMaquina) {
			forcaMao += carta.getForca();
		}
		return forcaMao;
	}

	public Boolean aceitaTruco(List<Carta> cartasMaquina, Integer valorRodada, int turno, Integer turnoVencidoJogador,
			Integer turnoVencidoMaquina, Jogador vencedorTurno1) {
		Random random = new Random();
		
		if(turnoVencidoMaquina == 1 && turnoVencidoJogador == 1) {
			
			if(calculaForcaMao(cartasMaquina) >= 9) {
				return true;
			} else if(random.nextInt(100) < 22 && 
						calculaForcaMao(cartasMaquina) < 4 ) {
				return true;
			} else {
				return false;
			}
			
		} else if(turnoVencidoMaquina == 1) {
			
			if(calculaForcaMao(cartasMaquina) > 14) {
				return true;
			} else if(random.nextInt(100) < 35 && 
					calculaForcaMao(cartasMaquina) < 9 ) {
			return true;
			} else {
				return false;
			}
			
		} else if(turnoVencidoJogador == 1) {
			
			if(calculaForcaMao(cartasMaquina) > 17) {
				return true;
			} else if(random.nextInt(100) < 31 && 
					calculaForcaMao(cartasMaquina) < 11 ) {
			return true;
			} else {
				return false;
			}
			
		} else {
			if(calculaForcaMao(cartasMaquina) > 26) {
				return true;
			} else if(random.nextInt(100) < 31 && 
					calculaForcaMao(cartasMaquina) < 11 ) {
			return true;
			} else {
				return false;
			}
		}
	}
}
