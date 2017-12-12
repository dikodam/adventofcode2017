package de.dikodam.adventofcode.day11;

import de.dikodam.adventofcode.tools.Tuple;

public class Movement extends Tuple<Double, Double> {
    public Movement(Double x, Double y) {
        super(x, y);
    }

    public Movement add(Movement movement) {
        return new Movement(getX() + movement.getX(), getY() + movement.getY());
    }
}
