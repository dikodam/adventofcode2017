package de.dikodam.adventofcode.day13;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Tuple;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Day13 extends AbstractDay {
    public static void main(String[] args) {
        Day13 day13 = new Day13();
        day13.task1();
        day13.task2();
    }

    Map<Integer, Integer> firewall;

    public Day13() {
        List<String> input = getInput();
        firewall = input.stream()
            .map(this::parseInputLine)
            .collect(toMap(Tuple::getX, Tuple::getY));
    }

    Tuple<Integer, Integer> parseInputLine(String inputLine) {
        String[] splitInputLine = inputLine.split(": ");
        return new Tuple<>(Integer.parseInt(splitInputLine[0]), Integer.parseInt(splitInputLine[1]));
    }

    @Override
    public void task1() {
        int severityStreamed = firewall.entrySet()
            .stream()
            .filter(entry -> isDetectedByScanner(entry.getKey(), entry.getValue()))
            .mapToInt(entry -> computeSeverity(entry.getKey(), entry.getValue()))
            .sum();

        System.out.println("Task 1: Severity (stream) is: " + severityStreamed);
    }

    public int computeSeverity(int layer, int depth) {
        return layer * depth;
    }

    public boolean isDetectedByScanner(int pictoseconds, int depth) {
        // stepping onto first scanner
        if (pictoseconds == 0) {
            return true;
        }

        if (depth <= 2) {
            return pictoseconds % depth == 0;
        }
        return pictoseconds % (2 * (depth - 1)) == 0;
    }

    @Override
    public void task2() {
        int delay = 1;
        while (isDetectedByScannerAfterDelayOf(delay)) {
            delay++;
        }

        System.out.println("Task 2: delay needed is " + delay);
    }

    private boolean isDetectedByScannerAfterDelayOf(int i) {
        return firewall.entrySet()
            .stream()
            .anyMatch(entry -> isDetectedByScanner(entry.getKey() + i, entry.getValue()));
    }
}
