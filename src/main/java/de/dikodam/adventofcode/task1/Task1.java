package de.dikodam.adventofcode.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Task1 {

    public static void main(String[] args) {
        Task1 task1 = new Task1(args[0]);
    }

    public Task1() {
    }

    public Task1(String inputString) {
        List<Integer> ints = parseStringToInts(inputString);
        subtask1(ints);
        subtask2(ints);
    }

    private void subtask2(List<Integer> ints) {
        List<Integer> summands = new ArrayList<>();
        int listSize = ints.size();

        for (int i = 0; i < listSize; i++) {
            Integer n0 = ints.get(i);
            Integer n1 = ints.get((i + listSize / 2) % listSize);
            if (Objects.equals(n0, n1)) {
                summands.add(n0);
            }
        }

        System.out.println("Sum of subtask2: " + sumInts(summands));
    }

    private void subtask1(List<Integer> ints) {
        List<Integer> summands = new ArrayList<>();
        int listSize = ints.size();
        for (int i = 0; i < listSize; i++) {
            Integer n0 = ints.get(i);
            Integer n1 = ints.get((i + 1) % listSize);
            if (Objects.equals(n0, n1)) {
                summands.add(n0);
            }
        }
        System.out.println("Sum of subtask1: " + sumInts(summands));
    }

    private int sumInts(List<Integer> summands) {
        return summands.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public List<Integer> parseStringToInts(String inputString) {
        return Arrays.stream(inputString.split(""))
            .map(Integer::parseInt)
            .collect(toList());
    }

}
