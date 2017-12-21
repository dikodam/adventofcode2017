package de.dikodam.adventofcode.day16;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class Day16 extends AbstractDay {

    public static void main(String[] args) {
        doTheMagic(Day16.class);
    }

    List<Function<char[], char[]>> danceMoves;
    List<Function<String, String>> stringDanceMoves;

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
        state = oneIterationRound(state);
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
        int firstDuplicateIndex = findOutFirstDuplicateStateIndex(initState());
        int offset = 1000000000 % firstDuplicateIndex;

        char[] state = initState();
        for (int i = 0; i < offset; i++) {
            state = oneIterationRound(state);
        }

        String finalState = stateToString(state);

        System.out.println("Task 2: state after 1.000.000.000 iterations: " + finalState);
    }

    public int findOutFirstDuplicateStateIndex(char[] initialState) {
        List<char[]> states = new ArrayList<>();
        states.add(initialState);
        char[] iterState = initialState;
        for (int i = 1; i <= 1000000000; i++) {
            iterState = oneIterationRound(iterState);
            int firstDoubleIndex = indexOf(iterState, states);
            if (firstDoubleIndex > -1) {
                return firstDoubleIndex;
            }
            if (i % 10000 == 0) {
                System.out.println(i);
            }

        }
        return -1;
    }

    private char[] oneIterationRound(char[] iterState) {
        for (Function<char[], char[]> danceMove : danceMoves) {
            iterState = danceMove.apply(iterState);
        }
        return iterState;
    }

    private int indexOf(char[] state, List<char[]> states) {
        for (int i = 0; i < states.size(); i++) {
            if (Arrays.equals(states.get(i), state)) {
                return i;
            }
        }
        return -1;
    }

}
