package de.dikodam.adventofcode.day05;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Day05 extends AbstractDay {
    public static void main(String[] args) {
        Day05 day05 = new Day05();
        day05.task1();
        day05.task2();
    }

    public void task2() {
        Map<Integer, Integer> data = getDay05input();
        int stepCounter = 0;
        int index = 0;
        while (data.containsKey(index)) {
            int offset = data.get(index);
            if (offset >= 3) {
                data.put(index, offset - 1);
            } else {
                data.put(index, offset + 1);
            }
            index += offset;
            stepCounter++;
        }
        System.out.println("Task 2 took " + stepCounter + " to get outside the list!");

    }

    public void task1() {
        Map<Integer, Integer> data = getDay05input();
        int stepCounter = 0;
        int index = 0;
        while (data.containsKey(index)) {
            int offset = data.get(index);
            data.put(index, offset + 1);
            index += offset;
            stepCounter++;
        }
        System.out.println("Task 1 took " + stepCounter + " to get outside the list!");
    }

    private Map<Integer, Integer> getDay05input() {
        List<Integer> input = getInput(this.getClass().getSimpleName())
            .stream()
            .map(Integer::parseInt)
            .collect(toList());

        return IntStream.range(0, input.size())
            .boxed()
            .collect(toMap(Function.identity(), input::get));
    }
}
