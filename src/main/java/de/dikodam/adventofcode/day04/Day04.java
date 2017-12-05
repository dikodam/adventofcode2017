package de.dikodam.adventofcode.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Day04 {
    public static void main(String[] args) {
        new Day04();
    }

    public Day04() {
        task1();
        task2();
    }

    private void task2() {
        long validPassphrasesCount = getUnvalidatedPassphrases()
            .stream()
            .filter(this::isPassphraseValid2)
            .count();
        System.out.println("Task 2: " + validPassphrasesCount + " valid passphrases in input file");
    }

    private boolean isPassphraseValid2(String s) {
        List<Map<Integer, Long>> charSignaturesForEachWord = Arrays.stream(s.split(" "))
            .map(this::charSignature)
            .collect(toList());

        List<Map<Integer, Long>> distinctCharSignatures = charSignaturesForEachWord.stream()
            .distinct().collect(toList());

        return charSignaturesForEachWord.size() == distinctCharSignatures.size();
    }

    public Map<Integer, Long> charSignature(String word) {
        return word.chars()
            .boxed()
            .collect(groupingBy(Function.identity(), Collectors.counting()));
    }

    private void task1() {
        int validPassphrasesCount = (int) getUnvalidatedPassphrases()
            .stream()
            .filter(this::isPassphraseValid1)
            .count();
        System.out.println("Task 1: " + validPassphrasesCount + " valid passphrases in input file");
    }

    private boolean isPassphraseValid1(String s) {
        String[] words = s.split(" ");
        return Arrays.stream(words).distinct().count() == words.length;
    }

    private List<String> getUnvalidatedPassphrases() {
        try (BufferedReader br = new BufferedReader(new FileReader(
            new File(this.getClass().getResource("/day04input").toURI())))) {
            return br.lines().collect(toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}