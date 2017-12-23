package de.dikodam.adventofcode.day20;

import de.dikodam.adventofcode.tools.Triple;

public class Transformation extends Triple<Coordinates, Coordinates, Coordinates> {
    public Transformation(Coordinates coordinates, Coordinates coordinates2, Coordinates coordinates3) {
        super(coordinates, coordinates2, coordinates3);
    }
}
