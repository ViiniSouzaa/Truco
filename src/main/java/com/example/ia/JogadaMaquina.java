package com.example.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.truco.model.Carta;

public class JogadaMaquina {
	
	
	
	public JogadaMaquina() {
		super();
	}

	public int escolheJogada(List<Carta> cartasMaquina, Carta cartaAdversario, Boolean empaxou) {
		Random random = new Random();
		int cartaRetorno = 0;
		
		if(calculaForcaMao(cartasMaquina) > 117) {
			return 4;
		}
		
		if(random.nextInt(100) < 4) {
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
}
