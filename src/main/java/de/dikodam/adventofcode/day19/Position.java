package de.dikodam.adventofcode.day19;

import de.dikodam.adventofcode.tools.Tuple;

public class Position extends Tuple<Integer, Integer> {

    public Position(Integer row, Integer col) {
        super(row, col);
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                x--;
                break;
            case DOWN:
                x++;
                break;
            case LEFT:
                y--;
                break;
            case RIGHT:
                y++;
                break;
        }
    }

}
