package de.dikodam.adventofcode.day10;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day10 extends AbstractDay {

    public static void main(String[] args) {
        Day10 day10 = new Day10();
        day10.task1();
        day10.task2();
    }

    List<Integer> input;

    public Day10() {
        String[] splitInputString = getInput(this.getClass().getSimpleName())
            .get(0)
            .split(",");
        input = Arrays.stream(splitInputString)
            .map(Integer::parseInt)
            .collect(toList());
    }

    @Override
    public void task1() {
        int currentPosition = 0;
        int skipSize = 0;
        int[] list = IntStream.range(0, 256).toArray();

        for (int length : input) {
            int[] sublist = getSubList(list, currentPosition, length);
            sublist = reverse(sublist);
            write(sublist, list, currentPosition);
            currentPosition += length + skipSize;
            skipSize++;
        }

        // TODO print smth

        System.out.println("Task 1: ");
    }

    private void write(int[] sublist, int[] targetList, int currentPosition) {
        for (int i = 0; i < sublist.length; i++) {
            int targetPosition = (i + currentPosition) % targetList.length;
            targetList[targetPosition] = sublist[i];
        }
    }

    private int[] reverse(int[] sublist) {
        int[] newSublist = new int[sublist.length];
        // TODO reverse
        return newSublist;
    }

    private int[] getSubList(int[] list, int fromInclusive, int lenght) {
        int[] sublist = new int[lenght];

        for (int i = fromInclusive; i < fromInclusive + lenght; i++) {
            // TODO iterate wrapping modulo
        }
        return sublist;
    }

    @Override
    public void task2() {
        System.out.println("Task 2: ");
    }
}
