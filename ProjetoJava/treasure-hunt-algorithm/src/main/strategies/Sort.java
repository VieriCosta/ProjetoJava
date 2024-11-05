package main.strategies;

import java.util.List;
import java.util.Random;
import main.game.map.Map;
import main.game.map.Monster;
import main.game.map.Point;
import main.game.map.Rock;

public class Sort implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		Random random = new Random();
		return possibleNextSteps.get(random.nextInt(possibleNextSteps.size()));
	}
}
