package de.dikodam.adventofcode.tools;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class KnotHash {

    public int[] sparseHash(int arraysSize, String hashKey) {
        int currentPos = 0;
        int skipSize = 0;
        int[] buffer = IntStream.range(0, arraysSize).toArray();
        List<Integer> lenghts = parseHashKey(hashKey);

        for (int i = 0; i < 64; i++) {
            for (int length : lenghts) {
                int[] sublist = getSubList(buffer, currentPos, length);
                sublist = reverse(sublist);
                buffer = write(sublist, buffer, currentPos);
                currentPos += (length + skipSize);
                skipSize++;
            }
        }
        return buffer;
    }

    public String denseHash(int[] sparseHash) {
        return buildDenseHashStream(sparseHash)
            .mapToObj(i -> String.format("%02x", i))
            .collect(joining());
    }

    public List<Integer> parseHashKey(String hashKey) {
        Stream<Integer> hashKeyStream = hashKey.chars().boxed();
        Stream<Integer> suffixStream = Stream.of(17, 31, 73, 47, 23);
        return Stream.concat(hashKeyStream, suffixStream).collect(toList());
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

    private IntStream buildDenseHashStream(int[] sparseHash) {
        return IntStream.range(0, sparseHash.length / 16)
            .map(groupIndex ->
                     Arrays
                         .stream(sparseHash, groupIndex * 16, sparseHash.length)
                         .reduce(0, (a, b) -> a ^ b)
            );
    }

}
