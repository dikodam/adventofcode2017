package de.dikodam.adventofcode.day09;

import de.dikodam.adventofcode.tools.AbstractDay;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day09 extends AbstractDay {

    public static void main(String[] args) {
        Day09 day09 = new Day09();
        day09.task1();
        day09.task2();
    }

    private char[] input;

    public Day09() {
        input = getInput(getClass().getSimpleName()).get(0).toCharArray();
    }

    @Override
    public void task1() {
        boolean garbageState = false;
        int score = 0;
        int nonCancelledGarbageCount = 0;
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length; i++) {
            char inputChar = input[i];
            if (garbageState) {
                if (inputChar == '!') {
                    i++;
                } else if (inputChar == '>') {
                    garbageState = false;
                } else {
                    nonCancelledGarbageCount++;
                }
            } else {
                if (inputChar == '{') {
                    stack.push(inputChar);
                } else if (inputChar == '}') {
                    score += stack.size();
                    stack.pop();
                } else if (inputChar == '<') {
                    garbageState = true;
                }
            }
        }

        System.out.println("Task 1: Score is " + score);
        System.out.println("Task 2: Count of non-cancelled garbage is " + nonCancelledGarbageCount);
    }

    @Override
    public void task2() {
        // implemented in task1
    }
}
