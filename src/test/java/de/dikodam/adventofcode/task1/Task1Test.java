package de.dikodam.adventofcode.task1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class Task1Test {

    Task1 tested;

    @Before
    public void setUp() {
        tested = new Task1();
    }

    @Test
    public void parseStringToInts() throws Exception {
        List<Integer> result = tested.parseStringToInts("123");
        // result.forEach(System.out::print);
        assertThat(result, contains(1, 2, 3));
    }

}