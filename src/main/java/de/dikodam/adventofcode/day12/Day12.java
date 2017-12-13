package de.dikodam.adventofcode.day12;

import de.dikodam.adventofcode.tools.AbstractDay;
import de.dikodam.adventofcode.tools.Tuple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 extends AbstractDay {

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        day12.task1();
        day12.task2();
    }

    Map<Integer, Set<Integer>> dependencies;

    public Day12() {
        List<String> input = getInput(getClass().getSimpleName());
        dependencies = input
            .stream()
            .map(this::parseDependency)
            .collect(Collectors.toMap(Tuple::getX, Tuple::getY));
    }

    @Override
    public void task1() {
        Set<Integer> equivalenceClassFor0 = getEquivalenceClassFor(0);
        System.out.println("Task 1: The size of the 0 group is " + equivalenceClassFor0.size());
    }

    private Set<Integer> getEquivalenceClassFor(int root) {
        Set<Integer> equivalenceClass = Set.of(root);
        int oldSize = 0;
        int newSize = 1;
        while (newSize > oldSize) {
            oldSize = newSize;
            equivalenceClass = resolveDependencies(equivalenceClass);
            newSize = equivalenceClass.size();
        }
        return equivalenceClass;
    }

    private Set<Integer> resolveDependencies(Set<Integer> indizes) {
        return Stream.concat(indizes.stream(),
                             indizes.stream()
                                 .map(dependencies::get)
                                 .flatMap(Set::stream)
        ).collect(Collectors.toSet());
    }

    private Tuple<Integer, Set<Integer>> parseDependency(String inputLine) {
        String[] splitInput = inputLine.split(" <-> ");
        Integer index = Integer.parseInt(splitInput[0]);
        Set<Integer> dependencies = Arrays.stream(splitInput[1].split(", "))
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
        return new Tuple<>(index, dependencies);
    }

    @Override
    public void task2() {
        Set<Set<Integer>> equivalenceClasses = new HashSet<>();
        for (Integer value : dependencies.keySet()) {
            if (!equivalenceClassExistsFor(value, equivalenceClasses)) {
                equivalenceClasses.add(getEquivalenceClassFor(value));
            }
        }
        System.out.println("Task 2: There are " + equivalenceClasses.size() + " equivalence classes");
    }

    private boolean equivalenceClassExistsFor(Integer value, Set<Set<Integer>> equivalenceClasses) {
        return equivalenceClasses.stream()
            .flatMap(Set::stream)
            .anyMatch(value::equals);
    }
}
