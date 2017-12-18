package de.dikodam.adventofcode.day14;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.KnotHash;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day14 extends AbstractDay {

    List<String> input;
    KnotHash knotHash;

    public static void main(String[] args) {
        execute(Day14.class);
    }

    public Day14() {
        String inputString = getInput().get(0);
        input = IntStream.range(0, 128)
            .mapToObj(i -> appendIndex(inputString, i))
            .collect(toList());
        knotHash = new KnotHash();
    }

    private String appendIndex(String inputString, int i) {
        return String.format("%s-%d", inputString, i);
    }

    @Override
    public void task1() {
        long usedSquares = input.stream()
            .map(this::hashStringAsHex)
            .mapToLong(this::hexStringToCountOfBinaryOnes)
            .count();

        long sum = 0;
        for (String s : input) {
            String hashStringAsHex = hashStringAsHex(s);
            sum += hexStringToCountOfBinaryOnes(hashStringAsHex);
        }

        System.out.println("Task 1: Used Squares: " + sum);
    }

    private long hexStringToCountOfBinaryOnes(String hexString) {
        return (int) IntStream.range(0, hexString.length())
            .mapToObj(hexString::charAt)
            .mapToInt(this::countBinaryOnesInHexChar)
            .count();
    }

    private int countBinaryOnesInHexChar(Character character) {
        switch (character) {
            case '0': // 0000
                return 0;
            case '1': // 0001
                return 1;
            case '2': // 0010
                return 1;
            case '3': // 0011
                return 2;
            case '4': // 0100
                return 1;
            case '5': // 0101
                return 2;
            case '6': // 0110
                return 2;
            case '7': // 0111
                return 3;
            case '8': // 1000
                return 1;
            case '9': // 1001
                return 2;
            case 'a': // 1010
            case 'A':
                return 2;
            case 'b': // 1011
            case 'B':
                return 3;
            case 'c': // 1100
            case 'C':
                return 2;
            case 'd': // 1101
            case 'D':
                return 3;
            case 'e': // 1110
            case 'E':
                return 3;
            case 'f': // 1111
            case 'F':
                return 4;
            default:
                throw new IllegalArgumentException("invalid char: " + character);
        }
    }

    private String hashStringAsHex(String key) {
        int[] sparseHash = knotHash.sparseHash(128, key);
        return knotHash.denseHash(sparseHash);
    }

    @Override
    public void task2() {
        System.out.println("Task 2: ");

    }
}
