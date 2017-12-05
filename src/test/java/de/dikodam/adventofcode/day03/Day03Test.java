package de.dikodam.adventofcode.day03;

import de.dikodam.adventofcode.Tuple;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Day03Test {

    private Day03 tested;

    @Before
    public void setUp() {
        tested = new Day03();
    }

    @Test
    public void nextPosition() {
        List<Tuple> expected = List.of(new Tuple<>(0, 0)
            , new Tuple<>(1, 0)
            , new Tuple<>(1, 1)
            , new Tuple<>(0, 1)
            , new Tuple<>(-1, 1)
            , new Tuple<>(-1, 0)
            , new Tuple<>(-1, -1)
            , new Tuple<>(0, -1)
            , new Tuple<>(1, -1)
            , new Tuple<>(2, -1)
            , new Tuple<>(2, 0)
            , new Tuple<>(2, 1)
            , new Tuple<>(2, 2)
            , new Tuple<>(1, 2)
            , new Tuple<>(0, 2)
            , new Tuple<>(-1, 2)
            , new Tuple<>(-2, 2)
            , new Tuple<>(-2, 1)
            , new Tuple<>(-2, 0)
            , new Tuple<>(-2, -1)
            , new Tuple<>(-2, -2)
            , new Tuple<>(-1, -2)
            , new Tuple<>(0, -2)
            , new Tuple<>(1, -2)
            , new Tuple<>(2, -2)
            , new Tuple<>(3, -2)
        );

        Object[] result = Stream
            .iterate(new Tuple<>(0, 0), tested::nextPosition)
            .limit(expected.size()).toArray();

        assertThat(result, is(expected.toArray()));

    }

}