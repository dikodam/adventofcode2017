package de.dikodam.adventofcode.day03;

import de.dikodam.adventofcode.tools.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Day03 {

    private static final int input = 361527;
    private List<Tuple<Integer, Integer>> positions;
    private Map<Tuple<Integer, Integer>, Integer> positionSumGraph;

    public static void main(String[] args) {
        if (args.length == 0) {
            new Day03(input);
        } else {
            new Day03(Integer.parseInt(args[0]));
        }
    }

    public Day03(int input) {
        task1(input);
        task2(input);
    }

    private void task2(int input) {
        positionSumGraph = new HashMap<>();
        // position : sum of existing neighbours
        // find 1st position of which the sum is greater than input
        Tuple<Integer, Integer> position = new Tuple<>(0, 0);
        int currentSum = 1;
        while (currentSum < input) {
            positionSumGraph.put(position, currentSum);
            position = nextPosition(position);
            currentSum = sumOfPosition(position);
        }
        System.out.println("Task 2: value of " + currentSum + " at position " + position);
    }

    private int sumOfPosition(Tuple<Integer, Integer> position) {
        int sum = 0;
        for (Tuple<Integer, Integer> neighbour : getNeighbourPositions(position)) {
            sum += Optional.ofNullable(positionSumGraph.get(neighbour)).orElse(0);
        }
        return sum;
    }

    private List<Tuple<Integer, Integer>> getNeighbourPositions(Tuple<Integer, Integer> position) {
        int x = position.getX();
        int y = position.getY();
        return List.of(new Tuple<>(x + 1, y),
                       new Tuple<>(x + 1, y + 1),
                       new Tuple<>(x, y + 1),
                       new Tuple<>(x - 1, y + 1),
                       new Tuple<>(x - 1, y),
                       new Tuple<>(x - 1, y - 1),
                       new Tuple<>(x, y - 1),
                       new Tuple<>(x + 1, y - 1));
    }

    public Day03() {

    }

    private void task1(int input) {
        positions = buildGraph(input);
        Tuple<Integer, Integer> position = positions.get(positions.size() - 1);
        int manhattanDistance = Math.abs(position.getX()) + Math.abs(position.getY());
        System.out.println("Task 1: " + manhattanDistance);
    }

    private List<Tuple<Integer, Integer>> buildGraph(int maxNumber) {
        return Stream.iterate(new Tuple<>(0, 0), this::nextPosition)
            .limit(maxNumber)
            .collect(toList());
    }

    public Tuple<Integer, Integer> nextPosition(Tuple<Integer, Integer> previousPosition) {
        int x = previousPosition.getX();
        int y = previousPosition.getY();
        int absX = Math.abs(x);
        int absY = Math.abs(y);

        boolean moveLeft = y > 0 && x <= y && -x < y;
        boolean moveDown = x < 0 && -x >= y && x < y;
        boolean moveUp = x > 0 && -y <= x && y < x && absX != absY;
        boolean moveRight = (x == 0 && y == 0) || (y < 0 && x <= -y);

        if (moveDown) {
            return new Tuple<>(x, y - 1);
        }
        if (moveRight) {
            return new Tuple<>(x + 1, y);
        }
        if (moveLeft) {
            return new Tuple<>(x - 1, y);
        }
        if (moveUp) {
            return new Tuple<>(x, y + 1);
        }
        throw new IllegalStateException("something went horribly wrong!");
    }
}