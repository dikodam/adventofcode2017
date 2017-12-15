package de.dikodam.adventofcode.day13;

import de.dikodam.adventofcode.tools.Tuple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static java.util.stream.Collectors.toMap;

public class Day13Test {
    private static String input = "0: 3\n" +
        "1: 2\n" +
        "4: 4\n" +
        "6: 4";

    private Day13 tested;

    @Before
    public void setUp() {
        tested = new Day13();
    }

    @Test
    public void a() {
        tested.firewall = Arrays.stream(input.split("\n"))
            .map(tested::parseInputLine)
            .collect(toMap(Tuple::getX, Tuple::getY));
        tested.firewall.entrySet().forEach(System.out::println);
        tested.task2();
    }
}