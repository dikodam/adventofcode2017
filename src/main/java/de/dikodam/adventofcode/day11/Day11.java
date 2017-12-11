package de.dikodam.adventofcode.day11;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Arrays;
import java.util.List;

public class Day11 extends AbstractDay {

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        day11.task1();
        day11.task2();
    }

    @Override
    public void task1() {
        List<String> input = getInput(getClass().getSimpleName());
        Arrays.stream(input.get(0).split(","))
        ;
            // .map() TODO map direction
        // .map(Direction to coordinate)
        // reduce(adding)
        // manhattan distance of reduction
    }

    @Override
    public void task2() {

    }
}
