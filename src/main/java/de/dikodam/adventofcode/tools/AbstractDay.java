package de.dikodam.adventofcode.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractDay {

    public abstract void task1();

    public abstract void task2();

    public static <Day extends AbstractDay> void doTheMagic(Class<Day> clazz) {
        try {
            Day day = clazz.getConstructor().newInstance();
            day.task1();
            day.task2();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public List<String> getInput(String fileName) {
        try (FileReader reader = new FileReader(new File(getClass().getResource("/" + fileName).toURI()));
             BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getInput() {
        return getInput(getClass().getSimpleName());
    }
}
