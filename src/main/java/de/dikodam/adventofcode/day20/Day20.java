package de.dikodam.adventofcode.day20;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day20 extends AbstractDay {

    private List<Transformation> input;

    public static void main(String[] args) {
        doTheMagic(Day20.class);
    }

    public Day20() {
        input = getInput()
            .stream()
            .map(this::parseInput)
            .collect(toList());
    }

    @Override
    public void task1() {
        // in each tick:
        // for each particle
        // increase v by a
        // increase p by v
        // particles correspond to line in input
        // => line x is a transformation of particle x

        // TODO which particle will stay closest to [0|0|0] in the long run
        // TODO distance = manhanntan distance, sum of absolute coords
    }

    private Transformation parseInput(String line) {
        // example line: p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>
        // p: position
        // v: velocity
        // a: acceleration
        String[] splitInputLine = line.split(", ");
        Coordinates position = parseSplitLineToCoordinates(splitInputLine[0]);
        Coordinates velocity = parseSplitLineToCoordinates(splitInputLine[1]);
        Coordinates acceleration = parseSplitLineToCoordinates(splitInputLine[2]);
        return new Transformation(position, velocity, acceleration);
    }

    private Coordinates parseSplitLineToCoordinates(String splitInputLine) {
        // splitInputLine in format x=<i,j,k>, extract integers i, j , k
        String trimmedInput = splitInputLine.substring(3, splitInputLine.length() - 1);
        String[] integers = trimmedInput.split(",");
        int x = Integer.parseInt(integers[0]);
        int y = Integer.parseInt(integers[1]);
        int z = Integer.parseInt(integers[2]);
        return new Coordinates(x, y, z);
    }

    @Override
    public void task2() {

    }
}
