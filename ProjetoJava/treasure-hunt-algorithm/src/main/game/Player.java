package main.game;

import java.util.*;

import main.game.map.Map;
import main.game.map.Point;
import main.game.map.Rock; // Importa a classe Rock
import main.game.map.Monster; // Importa a classe Monster
import main.strategies.Strategy;

public class Player {
	public static final String CHARACTER = "W"; // Exemplo de símbolo do jogador
	private Strategy strategy;
	private Set<Point> visitedPoints; // Conjunto para armazenar pontos visitados

	public Player(Strategy strategy) {
		this.strategy = strategy;
		this.visitedPoints = new HashSet<>(); // Inicializa o conjunto de pontos visitados
	}

	public Point evaluatePossbileNextStep(Map map) {
		Point robotLocation = map.getRobotLocation();
		List<Point> possibleNextSteps = new ArrayList<>();

		// Adiciona as direções possíveis
		possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() + 1)); // Cima
		possibleNextSteps.add(new Point(robotLocation.getPositionX() + 1, robotLocation.getPositionY())); // Direita
		possibleNextSteps.add(new Point(robotLocation.getPositionX() - 1, robotLocation.getPositionY())); // Esquerda
		possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() - 1)); // Baixo

		List<Point> validSteps = new LinkedList<>();

		// Filtra os passos possíveis
		for (Point p : possibleNextSteps) {
			int[] scenarioSize = map.getScenarioSize();
			// Verifica se o ponto está dentro dos limites do mapa
			if (p.getPositionX() >= 0 && p.getPositionY() >= 0 &&
					p.getPositionX() < scenarioSize[0] && p.getPositionY() < scenarioSize[1] &&
					!visitedPoints.contains(p)) { // Verifica se o ponto não foi visitado

				String space = map.get(p); // Verifica o que há no próximo ponto

				// Verifica se não há pedras
				if (space != null && space.equals(Rock.CHARACTER)) {
					continue; // Ignora este ponto com pedra
				}

				// Adiciona pontos válidos (sem pedras e sem monstros)
				if (space == null || !space.equals(Monster.CHARACTER)) {
					validSteps.add(p);
				}
			}
		}

		// Marca o ponto atual como visitado
		visitedPoints.add(robotLocation);

		// Retorna o próximo passo baseado na estratégia, se houver passos válidos
		return validSteps.isEmpty() ? null : this.strategy.evaluatePossbileNextStep(validSteps, map);
	}
}
