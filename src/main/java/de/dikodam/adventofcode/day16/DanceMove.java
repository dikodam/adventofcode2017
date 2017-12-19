package de.dikodam.adventofcode.day16;

import java.util.Arrays;
import java.util.function.Function;

public class DanceMove {

    private char operation;
    private String arguments;
    private int firstArg;
    private int secondArg;

    public DanceMove(char operation, String arguments) {
        if (operation != 'p' && operation != 's' && operation != 'x') {
            throw new IllegalArgumentException("" + operation);
        }
        this.operation = operation;
        parseArgs(arguments);
    }

    private void parseArgs(String arguments) {
        if (operation == 's') {
            firstArg = Integer.parseInt(arguments);
            secondArg = 0;
        } else if (operation == 'x') {
            String[] args = arguments.split("/");
            firstArg = Integer.parseInt(args[0]);
            secondArg = Integer.parseInt(args[1]);
        } else {
            this.arguments = arguments;
        }
    }

    public Function<char[], char[]> getDanceOperation() {
        switch (operation) {
            case 's':
                return spin();
            case 'x':
                return exchange();
            default:
                return partner();
        }
    }

    private Function<char[], char[]> spin() {
        return (state) -> {
            char[] newState = new char[state.length];
            for (int i = 0; i < state.length; i++) {
                newState[(i + firstArg) % state.length] = state[i];
            }
            return newState;
        };
    }

    private Function<char[], char[]> exchange() {
        return (state) -> swap(state, firstArg, secondArg);
    }

    private Function<char[], char[]> partner() {
        return (state) -> {
            char firstChar = arguments.charAt(0);
            char secondChar = arguments.charAt(2);

            int firstIndex = findIndexOf(firstChar, state);
            int secondIndex = findIndexOf(secondChar, state);

            return swap(state, firstIndex, secondIndex);
        };
    }

    private int findIndexOf(char c, char[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == c) {
                return i;
            }
        }
        throw new IllegalArgumentException("Char " + c + " not contained in array " + Arrays.toString(state));
    }

    private char[] swap(char[] state, int firstIndex, int secondIndex) {
        char buffer = state[secondIndex];
        state[secondIndex] = state[firstIndex];
        state[firstIndex] = buffer;
        return state;
    }

}
