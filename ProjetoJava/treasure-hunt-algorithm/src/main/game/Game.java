package main.game;

import main.game.map.Map;
import main.game.map.Point;
import main.game.map.TreasureChest;
import main.game.map.Monster;
import main.game.map.Rock;
import main.strategies.FewerObstacles;
import main.strategies.FewerObstaclesAndShorterDistance;
import main.strategies.ShortestDistance;
import main.strategies.Sort;

public class Game {
	private Map map;
	private Player player;

	public Game() {
		this.map = new Map(8, 8);
		this.player = new Player(new FewerObstaclesAndShorterDistance());
	}

	public void run() {
		this.map.print();
		System.out.println();
		int moveCount = 0; // Contador de movimentos

		while (moveCount < 100) { // Limita o número de movimentos a 100
			Point nextPoint = this.player.evaluatePossbileNextStep(map);
			if (nextPoint == null) {
				System.out.println("Não há mais movimentos possíveis. O jogo terminou.");
				break; // Termina o jogo se não houver mais movimentos possíveis
			} else {
				String space = this.map.get(nextPoint);
				if (space != null) {
					// Verifica se o jogador encontrou um tesouro
					if (space.equals(TreasureChest.CHARACTER)) {
						this.map.openTreasureChest(nextPoint);
						this.map.print();
						System.out.println("Tesouro encontrado! O jogo terminou.");
						break; // Termina o jogo se um tesouro for encontrado
					}
					// Verifica se o jogador encontrou uma pedra
					else if (space.equals(Rock.CHARACTER)) {
						System.out.println("Você encontrou uma pedra e mudou de rota. Tente outra direção.");
						continue; // Muda de rota e continua buscando
					}
				}
				// Move o jogador para o próximo ponto
				this.map.moveRobot(nextPoint);
			}
			this.map.print();
			System.out.println();
			moveCount++; // Incrementa o contador de movimentos
		}

		if (moveCount >= 100) {
			System.out.println("O número máximo de movimentos foi atingido. O jogo terminou.");
		}
	}
}