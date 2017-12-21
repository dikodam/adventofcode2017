package de.dikodam.adventofcode.day17;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.ArrayList;
import java.util.List;

public class Day17 extends AbstractDay {

    private int stepCount;

    public Day17() {
        stepCount = Integer.parseInt(getInput().get(0));
    }

    public static void main(String[] args) {
        doTheMagic(Day17.class);
    }

    @Override
    public void task1() {
        List<Integer> buffer = spinBufferXTimes(2017, stepCount);

        int shortcutValue = getValueAfter(2017, buffer);

        System.out.println("Task 1: shortcut value " + shortcutValue);
    }

    private int getValueAfter(int valueBefore, List<Integer> buffer) {
        return buffer.get(buffer.indexOf(valueBefore) + 1);
    }

    private List<Integer> spinBufferXTimes(int spinCount, int stepCount) {
        List<Integer> buffer = new ArrayList<>();
        buffer.add(0);
        int currentPos = 0;
        for (int i = 1; i < spinCount + 1; i++) {
            currentPos = ((currentPos + stepCount) % buffer.size()) + 1;
            buffer.add(currentPos, i);
        }
        return buffer;
    }

    @Override
    public void task2() {
        int valueAfter0 = spinBufferGetValueAfter0(50000000, stepCount);
        System.out.println("Task 2: new shortcut value: " + valueAfter0);

    }

    private int spinBufferGetValueAfter0(int spinCount, int stepCount) {
        int bufferSize = 0;
        bufferSize++;
        int currentPos = 0;
        int valueAfter0 = -1;
        for (int i = 1; i < spinCount + 1; i++) {
            currentPos = ((currentPos + stepCount) % bufferSize) + 1;
            if (currentPos == 1) {
                valueAfter0 = i;
            }
            bufferSize++;
        }
        return valueAfter0;
    }
}
