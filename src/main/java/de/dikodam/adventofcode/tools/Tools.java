package de.dikodam.adventofcode.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Tools {

    public static List<String> getInput(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(
            new File(Tools.class.getResource("/" + fileName).toURI())))) {
            return br.lines().collect(toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
