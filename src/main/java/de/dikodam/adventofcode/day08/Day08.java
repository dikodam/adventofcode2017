package de.dikodam.adventofcode.day08;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Tools;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08 extends AbstractDay {

    public static void main(String[] args) {
        Day08 day08 = new Day08();
        day08.task01();
        day08.task02();
    }

    private List<String> input;
    private Map<String, Integer> registers;

    public Day08() {
        input = Tools.getInput(this.getClass().getSimpleName());
    }

    @Override
    public void task01() {
        registers = input.stream()
            .map(this::extractRegister)
            .distinct()
            .collect(Collectors.toMap(Function.identity(), (s) -> 0));

        input.stream()
            .map(line -> line.split("if "))
            .filter(splitInputLine -> evaluateCondition(splitInputLine[1]))
            .forEach(splitInputLine -> runOperation(splitInputLine[0]));

        int maxValue = registers.entrySet()
            .stream()
            .mapToInt(Map.Entry::getValue)
            .max().orElseThrow(IllegalStateException::new);

        System.out.println("Task 1: max value is " + maxValue);

    }

    private void runOperation(String operationLine) {
        String[] splitOpLine = operationLine.split(" ");
        String registerAdress = splitOpLine[0];
        BiFunction<Integer, Integer, Integer> operation = getOperation(splitOpLine[1]);
        Integer operand = Integer.parseInt(splitOpLine[2]);

        Integer result = operation.apply(registers.get(registerAdress), operand);
        registers.put(registerAdress, result);
    }

    private BiFunction<Integer, Integer, Integer> getOperation(String operator) {
        switch (operator) {
            case "inc":
                return (a, b) -> a + b;
            case "dec":
                return (a, b) -> a - b;
            default:
                throw new IllegalArgumentException();
        }
    }

    private String extractRegister(String line) {
        return line.split(" ")[0];
    }

    private boolean evaluateCondition(String conditionLine) {
        String[] splitCondition = conditionLine.split(" ");
        Integer registerValue = registers.get(splitCondition[0]);
        Integer integer = Integer.parseInt(splitCondition[2]);
        BiPredicate<Integer, Integer> comparison = getComparison(splitCondition[1]);
        return comparison.test(registerValue, integer);
    }

    private BiPredicate<Integer, Integer> getComparison(String conditionOperator) {
        switch (conditionOperator) {
            case ">":
                return (firstInt, comparisonInt) -> firstInt > comparisonInt;

            case ">=":
                return (firstInt, comparisonInt) -> firstInt >= comparisonInt;

            case "<":
                return (firstInt, comparisonInt) -> firstInt < comparisonInt;

            case "<=":
                return (firstInt, comparisonInt) -> firstInt <= comparisonInt;

            case "==":
                return (firstInt, comparisonInt) -> comparisonInt.equals(firstInt);

            case "!=":
                return (firstInt, comparisonInt) -> !comparisonInt.equals(firstInt);
            default:
                throw new IllegalArgumentException(conditionOperator);
        }
    }

    @Override
    public void task02() {

    }
}
