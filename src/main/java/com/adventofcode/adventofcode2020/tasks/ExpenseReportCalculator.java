package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExpenseReportCalculator {

    public static Optional<Integer> findTwoEntriesThatSumTo2020AndMultiplyThen(){
        final int SUM = 2020;
        List<String> allLinesFromFile =
                TxtFileReader.read("C:\\Users\\ngalda\\Documents\\advent-of-code-2020\\src\\main\\resources\\day1input.txt");
        List<Integer> reportValues = Converter.convert(allLinesFromFile);
        for (int value: reportValues){
            for (int value2 : reportValues){
                if(checkSum(Arrays.asList(value, value2),SUM)){
                    return  Optional.of(value * value2);
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<Integer> findThreeEntriesThatSumTo2020AndMultiplyThen() {
        final int SUM = 2020;
        List<String> allLinesFromFile =
                TxtFileReader.read("C:\\Users\\ngalda\\Documents\\advent-of-code-2020\\src\\main\\resources\\day1input.txt");
        List<Integer> reportValues = Converter.convert(allLinesFromFile);
        for (int value : reportValues) {
            for (int value2 : reportValues) {
                for (int value3 : reportValues) {
                    if(checkSum(Arrays.asList(value, value2, value3),SUM)){
                        return Optional.of(value * value2 * value3);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static boolean checkSum(List<Integer> values, int checkSum){
        int sum = values.stream().reduce(0, Integer::sum);
        return sum == checkSum;
    }

}
