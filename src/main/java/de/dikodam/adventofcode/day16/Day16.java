package de.dikodam.adventofcode.day16;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class Day16 extends AbstractDay {

    public static void main(String[] args) {
        doTheMagic(Day16.class);
    }

    List<Function<char[], char[]>> danceMoves;

    public Day16() {
        String input = getInput().get(0);
        danceMoves = Arrays.stream(input.split(","))
            .map(this::parseDanceMove)
            .map(DanceMove::getDanceOperation)
            .collect(toList());
    }

    @Override
    public void task1() {
        char[] state = initState();
        for (Function<char[], char[]> danceMove : danceMoves) {
            state = danceMove.apply(state);
        }
        String finalState = stateToString(state);
        System.out.println("Task 1: final state is " + finalState);
    }

    private String stateToString(char[] state) {
        StringBuilder sb = new StringBuilder();
        for (char c : state) {
            sb.append(c);
        }
        return sb.toString();
    }

    private char[] initState() {
        char[] state = new char[16];
        for (int i = 0; i < state.length; i++) {
            state[i] = (char) (i + 'a');
        }
        return state;
    }

    private DanceMove parseDanceMove(String danceMove) {
        char operation = danceMove.charAt(0);
        String arguments = danceMove.substring(1, danceMove.length());
        return new DanceMove(operation, arguments);
    }

    @Override
    public void task2() {
        char[] state = initState();
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 1000000000; i++) {
            for (Function<char[], char[]> danceMove : danceMoves) {
                state = danceMove.apply(state);
            }
        }

        String finalState = stateToString(state);

        System.out.println("Task 2: state after 1.000.000.000 iterations: " + finalState);

    }
}
