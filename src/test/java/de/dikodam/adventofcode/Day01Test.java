package de.dikodam.adventofcode;

import de.dikodam.adventofcode.day01.Day01;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class Day01Test {

    Day01 tested;

    @Before
    public void setUp() {
        tested = new Day01();
    }

    @Test
    public void parseStringToInts() throws Exception {
        List<Integer> result = tested.parseStringToInts("123");
        // result.forEach(System.out::print);
        assertThat(result, contains(1, 2, 3));
    }

}