package de.dikodam.adventofcode.day10;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.KnotHash;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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


        list = hashRound(list, lengths);

        int hash = list[0] * list[1];
        System.out.println("Task 1: hashRound is " + hash);
    }

    public int[] hashRound(int[] list, List<Integer> lenghts) {
        for (int length : lenghts) {
            int[] sublist = getSubList(list, currentPosition, length);
            sublist = reverse(sublist);
            list = write(sublist, list, currentPosition);
            currentPosition += (length + skipSize);
            skipSize++;
        }
        return list;
    }

    private int[] getSubList(int[] list, int fromInclusive, int lenght) {
        int[] sublist = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            sublist[i] = list[(fromInclusive + i) % list.length];
        }
        return sublist;
    }

    private int[] reverse(int[] sublist) {
        int length = sublist.length;
        int[] newSublist = new int[length];
        for (int i = 0; i < length; i++) {
            newSublist[i] = sublist[length - i - 1];
        }
        return newSublist;
    }

    private int[] write(int[] sublist, int[] targetList, int currentPosition) {
        for (int i = 0; i < sublist.length; i++) {
            int targetPosition = (i + currentPosition) % targetList.length;
            targetList[targetPosition] = sublist[i];
        }
        return targetList;
    }

    @Override
    public void task2() {
        String input = getInput(this.getClass().getSimpleName())
            .get(0).trim();

        KnotHash knotHash = new KnotHash();
        String denseHash = knotHash.denseHash(knotHash.sparseHash(256,input));

        // 10. should be 32 digit long, is the dense hashRound and the answer
        System.out.println("Task 2: dense hashRound is: " + denseHash);
    }

}
