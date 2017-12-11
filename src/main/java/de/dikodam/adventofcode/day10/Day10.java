package de.dikodam.adventofcode.day10;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Day10 extends AbstractDay {

    int currentPosition;
    int skipSize;

    public static void main(String[] args) {
        Day10 day10 = new Day10();
        day10.task1();
        day10.task2();
    }

    @Override
    public void task1() {
        String[] splitInputString = getInput(this.getClass().getSimpleName())
            .get(0)
            .split(",");
        List<Integer> lengths = Arrays.stream(splitInputString)
            .map(Integer::parseInt)
            .collect(toList());
        currentPosition = 0;
        skipSize = 0;
        int[] list = IntStream.range(0, 256)
            .toArray();

        list = hash(list, lengths);

        int hash = list[0] * list[1];
        System.out.println("Task 1: hash is " + hash);
    }

    private int[] hash(int[] list, List<Integer> lenghts) {
        for (int length : lenghts) {
            int[] sublist = getSubList(list, currentPosition, length);
            sublist = reverse(sublist);
            list = write(sublist, list, currentPosition);
            currentPosition += (length + skipSize);
            skipSize++;
        }
        return list;
    }

    private int[] write(int[] sublist, int[] targetList, int currentPosition) {
        for (int i = 0; i < sublist.length; i++) {
            int targetPosition = (i + currentPosition) % targetList.length;
            targetList[targetPosition] = sublist[i];
        }
        return targetList;
    }

    private int[] reverse(int[] sublist) {
        int length = sublist.length;
        int[] newSublist = new int[length];
        for (int i = 0; i < length; i++) {
            newSublist[i] = sublist[length - i - 1];
        }
        return newSublist;
    }

    private int[] getSubList(int[] list, int fromInclusive, int lenght) {
        int[] sublist = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            sublist[i] = list[(fromInclusive + i) % list.length];
        }
        return sublist;
    }

    @Override
    public void task2() {
        String input = getInput(this.getClass().getSimpleName())
            .get(0).trim();

        // 1. treat each input character as an ASCII character
        // 2. translate each char to its numerical representation ("byte"), separate by commas
        // 3. append 17, 31, 73, 47, 23
        Stream<Integer> inputStream = input.chars().boxed();
        Stream<Integer> suffixStream = Stream.of(17, 31, 73, 47, 23);
        List<Integer> lengths = Stream.concat(inputStream, suffixStream)
            .collect(toList());

        currentPosition = 0;
        skipSize = 0;
        // 4. run 64 rounds of hashing algorithm (each time with the same length sequence)
        // 5. preserve cP and skip between rounds
        int[] list = IntStream.range(0, 256).toArray();
        for (int i = 0; i < 64; i++) {
            list = hash(list, lengths);
        }

        // 6. result is sparse hash, 16 times 16 numbers
        // 7. consecutively XOR every 16 element groups of sparse hash
        // 8. result should be 16 numbers
        // 9. take 2-digit hexadecimal respresentation of those 16 numbers
        String denseHash = buildDenseHashStream(list)
            .mapToObj(Integer::toHexString)
            .collect(joining());

        // 10. should be 32 digit long, is the dense hash and the answer
        System.out.println("Task 2: dense hash is: " + denseHash);
    }

    private IntStream buildDenseHashStream(int[] sparseHash) {
        return IntStream.range(0, 16)
            .map(groupIndex ->
                     Arrays
                         .stream(sparseHash, groupIndex * 16, groupIndex * 16 + 16)
                         .reduce(0, this::xor)
            );
    }

    private int xor(int first, int second) {
        return first ^ second;
    }

}
