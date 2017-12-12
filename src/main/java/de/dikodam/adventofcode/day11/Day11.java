package de.dikodam.adventofcode.day11;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 extends AbstractDay {

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        day11.task1();
        day11.task2();
    }

    String[] input;

    public Day11() {
        input = getInput(getClass().getSimpleName()).get(0).split(",");
    }

    @Override
    public void task1() {
        Movement totalMovement = Arrays.stream(input)
            .map(Direction::getByValue)
            .map(Direction::getMovement)
            .reduce(new Movement(0d, 0d), Movement::add);

        int distance = getManhattanDistanceOf(totalMovement);

        System.out.println("Task 1: total movement is " + totalMovement.getX() + "|" + totalMovement.getY());
        System.out.println("Task 1: distance is " + distance);
    }

    private int getManhattanDistanceOf(Movement movement) {
        double x = Math.abs(movement.getX());
        double y = Math.abs(movement.getY());
        double sum = x;
        double yResidue = y - x / 2;
        return yResidue > 0 ? (int) (sum + yResidue) : (int) sum;

    }

    @Override
    public void task2() {
        List<Movement> movements = Arrays.stream(input)
            .map(dir -> Direction.getByValue(dir).getMovement())
            .collect(Collectors.toList());

        Movement iterator = new Movement(0d, 0d);
        List<Movement> positions = new ArrayList<>();

        for (Movement movement : movements) {
            iterator = iterator.add(movement);
            positions.add(iterator);
        }

        positions.stream()
            .mapToInt(this::getManhattanDistanceOf)
            .max()
            .ifPresent(maxStepCount -> System.out.println("Task 2: max step count ever: " + maxStepCount));
    }
}
