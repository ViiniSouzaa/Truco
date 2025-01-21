package com.example.truco;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.truco.model.Baralho;
import com.example.truco.model.Jogador;
import com.example.truco.model.Rodada;
import com.example.truco.model.Rodada.Vencedor;

@SpringBootApplication
public class TrucoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrucoApplication.class, args);
		
		Scanner scanner = new Scanner(System.in);
		
		Boolean reinicia = true;
		
		System.out.println("Digite o nome do jogador: ");
		
		String nome = scanner.nextLine();
		
		Jogador jogador = new Jogador(nome);
		
		while(reinicia) {
			try {
				
				reinicia = iniciaJogo(jogador);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static Boolean iniciaJogo(Jogador jogador) {
		Scanner scanner = new Scanner(System.in);
		
		Baralho baralho = new Baralho();
		
		Jogador maquina = new Jogador("Maquina");
		
		Integer pontuacaoJogador = 0; 
		
		Integer pontuacaoMaquina = 0; 
		
		while(pontuacaoJogador < 12 && pontuacaoMaquina < 12) {
			Rodada rodada = new Rodada(baralho, jogador, maquina);
			
			Vencedor vencedor =  rodada.iniciaRodada();
			
			if(vencedor.getVencedor().equals(jogador)) {
				pontuacaoJogador += vencedor.getPontos();
			} else if(vencedor.getVencedor().equals(maquina)) {
				pontuacaoMaquina += vencedor.getPontos();
			}
			
			System.out.println("PONTUACAO : ");
			System.out.println(jogador.getNome() + ": " + pontuacaoJogador);
			System.out.println("Maquina : " + pontuacaoMaquina);
		}
		
		
		if(pontuacaoJogador >= 12) {
			System.out.println("Voce venceu a partida!");
		}
		
		if(pontuacaoMaquina >= 12) {
			System.out.println("Maquina venceu a partida!");
		}
			
			
		System.out.println("Digite 1 se quiser continuar jogando!");
		
		String retorno = scanner.nextLine();
		
		if(retorno.equals("1")) {
			return true;
		}
		return false;
		
	}

}
