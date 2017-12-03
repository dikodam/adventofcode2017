package de.dikodam.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day02 {

    static String input = "493\t458\t321\t120\t49\t432\t433\t92\t54\t452\t41\t461\t388\t409\t263\t58\n" +
        "961\t98\t518\t188\t958\t114\t1044\t881\t948\t590\t972\t398\t115\t116\t451\t492\n" +
        "76\t783\t709\t489\t617\t72\t824\t452\t748\t737\t691\t90\t94\t77\t84\t756\n" +
        "204\t217\t90\t335\t220\t127\t302\t205\t242\t202\t259\t110\t118\t111\t200\t112\n" +
        "249\t679\t4015\t106\t3358\t1642\t228\t4559\t307\t193\t4407\t3984\t3546\t2635\t3858\t924\n" +
        "1151\t1060\t2002\t168\t3635\t3515\t3158\t141\t4009\t3725\t996\t142\t3672\t153\t134\t1438\n" +
        "95\t600\t1171\t1896\t174\t1852\t1616\t928\t79\t1308\t2016\t88\t80\t1559\t1183\t107\n" +
        "187\t567\t432\t553\t69\t38\t131\t166\t93\t132\t498\t153\t441\t451\t172\t575\n" +
        "216\t599\t480\t208\t224\t240\t349\t593\t516\t450\t385\t188\t482\t461\t635\t220\n" +
        "788\t1263\t1119\t1391\t1464\t179\t1200\t621\t1304\t55\t700\t1275\t226\t57\t43\t51\n" +
        "1571\t58\t1331\t1253\t60\t1496\t1261\t1298\t1500\t1303\t201\t73\t1023\t582\t69\t339\n" +
        "80\t438\t467\t512\t381\t74\t259\t73\t88\t448\t386\t509\t346\t61\t447\t435\n" +
        "215\t679\t117\t645\t137\t426\t195\t619\t268\t223\t792\t200\t720\t260\t303\t603\n" +
        "631\t481\t185\t135\t665\t641\t492\t408\t164\t132\t478\t188\t444\t378\t633\t516\n" +
        "1165\t1119\t194\t280\t223\t1181\t267\t898\t1108\t124\t618\t1135\t817\t997\t129\t227\n" +
        "404\t1757\t358\t2293\t2626\t87\t613\t95\t1658\t147\t75\t930\t2394\t2349\t86\t385";

    public static void main(String[] args) {
        if (args.length == 0) {
            new Day02(input);
        } else {
            new Day02(args[0]);
        }
    }

    public Day02(String input) {
        List<List<Integer>> lines = parseLines(input);
        task1(lines);
        task2(lines);
    }

    private void task2(List<List<Integer>> lines) {
        int checksum = lines.stream()
            .map(this::findDivisionTuple)
            .mapToInt(divTuple -> divTuple[0] / divTuple[1])
            .sum();
        System.out.println("The checksum for task 2 is: " + checksum);
    }

    private int[] findDivisionTuple(List<Integer> line) {
        for (int i = 0; i < line.size(); i++) {
            for (int j = i + 1; j < line.size(); j++) {
                int a = line.get(i);
                int b = line.get(j);
                if (a % b == 0 || b % a == 0) {
                    return new int[]{Math.max(a, b), Math.min(a, b)};
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private void task1(List<List<Integer>> lines) {
        int[] minimums = lines.stream().mapToInt(this::getMin).toArray();
        int[] maximums = lines.stream().mapToInt(this::getMax).toArray();
        int checksum = IntStream.range(0, maximums.length)
            .map(index -> maximums[index] - minimums[index])
            .sum();
        System.out.println("Checksum for task 1 is: " + checksum);
    }

    private int getMin(List<Integer> intLine) {
        return intLine.stream()
            .mapToInt(Integer::valueOf)
            .min()
            .orElseThrow(IllegalArgumentException::new);
    }

    private int getMax(List<Integer> intLine) {
        return intLine.stream()
            .mapToInt(Integer::valueOf)
            .max()
            .orElseThrow(IllegalArgumentException::new);
    }

    private List<List<Integer>> parseLines(String input) {
        return Arrays.stream(input.split("\n"))
            .map(stringLine -> stringLine.split("\t"))
            .map(this::stringLineArrayToIntList)
            .collect(toList());
    }

    private List<Integer> stringLineArrayToIntList(String[] stringLineArray) {
        return Arrays.stream(stringLineArray)
            .map(Integer::parseInt)
            .collect(toList());
    }
}
