package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Point;
import main.tracker.PathTracker;

public class FewerObstaclesAndShorterDistance implements Strategy {

	private final PathTracker pathTracker;

	public FewerObstaclesAndShorterDistance() {
		this.pathTracker = new PathTracker();
	}

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		Point bestStep = null;
		double bestScore = Double.MAX_VALUE;
		Point target = map.getTreasureLocation();

		for (Point step : possibleNextSteps) {
			int obstacleCount = countObstaclesAround(step, map);
			double distance = calculateDistance(step, target);
			double score = distance + obstacleCount * 2; // Ajustar o peso conforme necessário

			if (score < bestScore) {
				bestScore = score;
				bestStep = step;
			}
		}

		if (bestStep != null) {
			pathTracker.addPathPoint(bestStep); // Adiciona o melhor passo ao caminho
			// Verifica e adiciona obstáculos encontrados ao redor
			if (countObstaclesAround(bestStep, map) > 0) {
				pathTracker.addObstacle(bestStep); // Adiciona o ponto como obstáculo se aplicável
			}
		}

		return bestStep;
	}

	private int countObstaclesAround(Point point, Map map) {
		int obstacles = 0;
		// Lógica para contar obstáculos nos pontos ao redor
		// Atualize esta lógica para checar obstáculos e, se necessário, adicionar os pontos ao PathTracker
		return obstacles;
	}

	private double calculateDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.getPositionX() - b.getPositionX(), 2) +
				Math.pow(a.getPositionY() - b.getPositionY(), 2));
	}

	public PathTracker getPathTracker() {
		return pathTracker;
	}
}