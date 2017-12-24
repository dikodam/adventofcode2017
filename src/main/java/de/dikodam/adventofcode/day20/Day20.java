package de.dikodam.adventofcode.day20;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Day20 extends AbstractDay {

    private List<PointInfo> input;

    public static void main(String[] args) {
        doTheMagic(Day20.class);
    }

    public Day20() {
        List<String> rawInput = getInput();
        input = IntStream.range(0, rawInput.size())
            .mapToObj(i -> parseInput(rawInput.get(i), i))
            .collect(toList());
    }

    @Override
    public void task1() {
        // filter points with lowest acceleration-"score"
        // out of these, determine the one which stays closest to 0,0,0

        //noinspection unchecked
        PointInfo minPoint = Collections.min(input);

        int minPointLineIndex = minPoint.getLineIndex();
        System.out.println("Task 1: closest point is: " + minPointLineIndex);
    }

    private PointInfo parseInput(String line, int lineIndex) {
        // example line: p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>
        // p: position
        // v: velocity
        // a: acceleration
        String[] splitInputLine = line.split(", ");
        Vector3D position = parseSplitLineToCoordinates(splitInputLine[0]);
        Vector3D velocity = parseSplitLineToCoordinates(splitInputLine[1]);
        Vector3D acceleration = parseSplitLineToCoordinates(splitInputLine[2]);
        return new PointInfo(position, velocity, acceleration, lineIndex);
    }

    private Vector3D parseSplitLineToCoordinates(String splitInputLine) {
        // splitInputLine in format x=<i,j,k>, extract integers i, j , k
        String trimmedInput = splitInputLine.substring(3, splitInputLine.length() - 1);
        String[] integers = trimmedInput.split(",");
        int x = Integer.parseInt(integers[0]);
        int y = Integer.parseInt(integers[1]);
        int z = Integer.parseInt(integers[2]);
        return new Vector3D(x, y, z);
    }

    @Override
    public void task2() {
        // while(collisionsPossible)
        // removeCollisions
        // tick

        // probabilistic-y algo (if the output value stops changing, you MIGHT have the right one)

        while (true) {
            input.removeAll(findCollisions(input));
            tick(input);
            System.out.println(input.size());
        }
    }

    private void tick(List<PointInfo> input) {
        input.forEach(PointInfo::tick);
    }

    private List<PointInfo> findCollisions(List<PointInfo> input) {
        Map<Vector3D, List<PointInfo>> positionToPointMap = input.stream()
            .collect(groupingBy(PointInfo::getPosition));

        return positionToPointMap.values().stream()
            .filter(listOfPoints -> listOfPoints.size() > 1)
            .flatMap(Collection::stream)
            .collect(toList());
    }

}
