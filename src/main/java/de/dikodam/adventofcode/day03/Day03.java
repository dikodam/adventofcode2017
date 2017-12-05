package de.dikodam.adventofcode.day03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {

    private static final int input = 361527;
    private List<Position> graph;

    public static void main(String[] args) {
        if (args.length == 0) {
            new Day03(input);
        } else {
            new Day03(Integer.parseInt(args[0]));
        }
    }

    public Day03(int input) {
        task1(input);
    }

    public Day03() {
    }

    private void task1(int input) {
        graph = buildGraph(input);
        Position position = graph.get(graph.size() - 1);
        int manhattanDistance = Math.abs(position.getX()) + Math.abs(position.getY());
        System.out.println("Task 1: " + manhattanDistance);
    }

    private List<Position> buildGraph(int maxNumber) {
        return Stream.iterate(new Position(0, 0), this::nextPosition)
            .limit(maxNumber)
            .collect(Collectors.toList());
    }

    public Position nextPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        int absX = Math.abs(x);
        int absY = Math.abs(y);

        boolean moveLeft = y > 0 && x <= y && -x < y;
        boolean moveDown = x < 0 && -x >= y && x < y;
        boolean moveUp = x > 0 && -y <= x && y < x && absX != absY;
        boolean moveRight = (x == 0 && y == 0) || (y < 0 && x <= -y);

        if (moveDown) {
            return new Position(x, y - 1);
        }
        if (moveRight) {
            return new Position(x + 1, y);
        }
        if (moveLeft) {
            return new Position(x - 1, y);
        }
        if (moveUp) {
            return new Position(x, y + 1);
        }
        throw new IllegalStateException("something went horribly wrong!");
    }
}