package main.tracker;

import java.util.LinkedList;
import main.game.map.Point;

public class PathTracker {
    private final LinkedList<Point> path; // Caminho que o robô já percorreu
    private final LinkedList<Point> obstacles; // Obstáculos encontrados pelo robô

    public PathTracker() {
        this.path = new LinkedList<>();
        this.obstacles = new LinkedList<>();
    }

    // Adiciona um ponto ao caminho
    public void addPathPoint(Point point) {
        path.add(point);
    }

    // Adiciona um ponto de obstáculo
    public void addObstacle(Point obstacle) {
        obstacles.add(obstacle);
    }

    // Retorna o caminho
    public LinkedList<Point> getPath() {
        return path;
    }

    // Retorna os obstáculos
    public LinkedList<Point> getObstacles() {
        return obstacles;
    }
}