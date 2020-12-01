package com.adventofcode.adventofcode2020.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader {

    public static List<String> read(String fileName){
        List<String> allLines = new ArrayList<>();
        Path path = Paths.get(fileName);
        try {
            allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLines;
    }
}
