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

    public Function<String, String> getDanceOperationForStrings() {
        switch (operation) {
            case 's':
                return spinString();
            case 'x':
                return exchangeString();
            default:
                return partnerString();
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

    private Function<String, String> spinString() {
        return (string) -> {
            int offset = firstArg;
            String rightShift = string.substring(0, string.length() - offset - 1);
            String carryOver = string.substring(string.length() - offset);
            return carryOver + rightShift;
        };
    }

    private Function<char[], char[]> exchange() {
        return (state) -> swap(state, firstArg, secondArg);
    }

    private Function<String, String> exchangeString() {
        return (state) -> swapString(state, firstArg, secondArg);
    }

    private String swapString(String state, int firstArg, int secondArg) {
        char firstChar = state.charAt(firstArg);
        char secondChar = state.charAt(secondArg);
        StringBuilder sb = new StringBuilder(state);
        sb.setCharAt(firstArg, secondChar);
        sb.setCharAt(secondArg, firstChar);
        return sb.toString();
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

    private Function<String, String> partnerString() {
        return (string) -> {
            char firstChar = arguments.charAt(0);
            char secondChar = arguments.charAt(2);

            int firstIndex = string.indexOf(firstChar);
            int secondIndex = string.indexOf(secondChar);

            return swapString(string, firstIndex, secondIndex);
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
