package main.strategies;

import java.util.List;
import main.game.map.Map;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;

public class ShortestDistance implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		Point bestStep = null;
		double shortestDistance = Double.MAX_VALUE;
		Point target = map.getTreasureLocation(); // Supondo que exista um método para encontrar a posição do tesouro

		for (Point step : possibleNextSteps) {
			double distance = calculateDistance(step, target);
			if (distance < shortestDistance) {
				shortestDistance = distance;
				bestStep = step;
			}
		}
		return bestStep;
	}

	private double calculateDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.getPositionX() - b.getPositionX(), 2) +
				Math.pow(a.getPositionY() - b.getPositionY(), 2));
	}
}
