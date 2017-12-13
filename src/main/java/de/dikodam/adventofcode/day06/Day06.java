package de.dikodam.adventofcode.day06;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.*;

public class Day06 extends AbstractDay{
    private int[] memorybank;

    public static void main(String[] args) {
        Day06 day06 = new Day06();
        day06.task1();
        day06.task2();
    }

    public void task1() {
        int stepCounter = doALoop(parseInput());
        System.out.println("Task 1: Took " + stepCounter + " to finish reallocation of memory");
    }

    private int doALoop(int[] input) {
        memorybank = input;
        Set<int[]> alreadyExistantMemConfigs = new HashSet<>();
        int stepCounter = 0;
        do {
            alreadyExistantMemConfigs.add(memorybank);
            memorybank = reallocateMemory(memorybank);
            stepCounter++;
        } while (!containsMemConfig(alreadyExistantMemConfigs, memorybank));
        return stepCounter;
    }

    private boolean containsMemConfig(Set<int[]> memConfigs, int[] b) {
        return memConfigs.stream().anyMatch(m -> Arrays.equals(m, b));
    }

    private int[] reallocateMemory(int[] memorybank) {
        int[] newMemoryBank = new int[memorybank.length];
        System.arraycopy(memorybank, 0, newMemoryBank, 0, memorybank.length);

        int index = findIndexOfModus(newMemoryBank);
        int memLoad = newMemoryBank[index];
        newMemoryBank[index] = 0;
        index = (index + 1) % newMemoryBank.length;
        while (memLoad > 0) {
            newMemoryBank[index] = newMemoryBank[index] + 1;
            memLoad--;
            index = (index + 1) % newMemoryBank.length;
        }
        return newMemoryBank;
    }

    private int findIndexOfModus(int[] memorybank) {
        int index = 0;
        int highestMemLoad = 0;
        for (int i = 0; i < memorybank.length; i++) {
            int currentMemLoad = memorybank[i];
            if (currentMemLoad > highestMemLoad) {
                highestMemLoad = currentMemLoad;
                index = i;
            }
        }
        return index;
    }

    private int[] parseInput() {
        String[] input = getInput(this.getClass().getSimpleName())
            .get(0)
            .split("\t");
        return Arrays.stream(input)
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    public void task2() {
        int steps = doALoop(memorybank);
        System.out.println("Task 2: " + steps + " had to be taken to see the same config a second time");
    }

}
