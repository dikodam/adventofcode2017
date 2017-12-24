package de.dikodam.adventofcode.day19;

import de.dikodam.adventofcode.tools.AbstractDay;

public class Day19 extends AbstractDay {

    public static final char VERTICAL = '|';
    public static final char HORIZONTAL = '-';
    public static final char CROSSROAD = '+';
    public static final char SPACE = ' ';
    private final char[][] map;

    public static void main(String[] args) {
        doTheMagic(Day19.class);
    }

    public Day19() {
        map = getInput().stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    @Override
    public void task1() {
        Position position = findStart();
        Direction dir = Direction.DOWN;

    }

    private Position findStart() {
        for (int i = 0; i < map[0].length; i++) {
            if (map[0][i] == VERTICAL) {
                return new Position(0, i);
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public void task2() {

    }
}
