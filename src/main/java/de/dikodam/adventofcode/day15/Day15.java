package de.dikodam.adventofcode.day15;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Tuple;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Day15 extends AbstractDay {

    public static void main(String[] args) {
        execute(Day15.class);
    }

    private static long FACTOR_A = 16807;
    private static long FACTOR_B = 48271;
    private static long MOD = 2147483647;

    List<String> input;
    long seedA;
    long seedB;

    public Day15() {
        input = getInput();
        seedA = Long.parseLong(input.get(0).split(" ")[4]);
        seedB = Long.parseLong(input.get(1).split(" ")[4]);
    }

    @Override
    public void task1() {
        Function<Long, Long> generatorA = createRemainderComputer(FACTOR_A, MOD);
        Function<Long, Long> generatorB = createRemainderComputer(FACTOR_B, MOD);
        UnaryOperator<Tuple<Long, Long>> tupleGenerator = createTupleGenerator(generatorA, generatorB);

        long generationCount = 40000000;

        long count = Stream.iterate(new Tuple<>(seedA, seedB), tupleGenerator)
            .limit(generationCount)
            .filter(this::areLowest16BitsEqual)
            .count();

        System.out.println("Task 1: Valid pairs: " + count);
    }

    @Override
    public void task2() {
        Function<Long, Long> remainderComputerA = createRemainderComputer(FACTOR_A, MOD);
        Function<Long, Long> remainderComputerB = createRemainderComputer(FACTOR_B, MOD);

        Function<Long, Long> generatorA = createGenerator(remainderComputerA, 4);
        Function<Long, Long> generatorB = createGenerator(remainderComputerB, 8);

        UnaryOperator<Tuple<Long, Long>> tupleGenerator = createTupleGenerator(generatorA, generatorB);

        long count = Stream.iterate(new Tuple<>(seedA, seedB), tupleGenerator)
            .limit(5000000)
            .filter(this::areLowest16BitsEqual)
            .count();

        System.out.println("Task 2: Count is: " + count);
    }

    private Function<Long, Long> createGenerator(Function<Long, Long> remainderComputer, int multipleOf) {
        return (input) -> {
            long result = remainderComputer.apply(input);
            while (result % multipleOf != 0) {
                result = remainderComputer.apply(result);
            }
            return result;
        };
    }

    private UnaryOperator<Tuple<Long, Long>> createTupleGenerator(Function<Long, Long> generatorA, Function<Long, Long> generatorB) {
        return (tuple) -> new Tuple<>(generatorA.apply(tuple.getX()),
                                      generatorB.apply(tuple.getY()));
    }

    private boolean areLowest16BitsEqual(Tuple<Long, Long> tuple) {
        long first = tuple.getX() & 0xFFFF;
        long second = tuple.getY() & 0xFFFF;
        return (first ^ second) == 0;
    }

    private Function<Long, Long> createRemainderComputer(long factor, long modul) {
        return (previousValue) -> (previousValue * factor) % modul;
    }

}
