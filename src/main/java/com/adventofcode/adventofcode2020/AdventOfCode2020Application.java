package com.adventofcode.adventofcode2020;

import com.adventofcode.adventofcode2020.tasks.ExpenseReportCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCode2020Application {

    public static void main(String[] args) {
        SpringApplication.run(AdventOfCode2020Application.class, args);
        //System.out.println(ExpenseReportCalculator.findThreeEntriesThatSumTo2020AndMultiplyThen().get());
    }

}
