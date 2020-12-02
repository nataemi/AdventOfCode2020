package com.adventofcode.adventofcode2020;

import com.adventofcode.adventofcode2020.tasks.ExpenseReportCalculator;
import com.adventofcode.adventofcode2020.tasks.PasswordCorporatePolicyValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCode2020Application {

    public static void main(String[] args) {
        final String RESOURCE = "C:\\Users\\ngalda\\Documents\\advent-of-code-2020\\src\\main\\resources\\day2input.txt";

        SpringApplication.run(AdventOfCode2020Application.class, args);
        //System.out.println(ExpenseReportCalculator.findThreeEntriesThatSumTo2020AndMultiplyThen().get());
        System.out.println(PasswordCorporatePolicyValidator.countValidPasswords(RESOURCE));
    }

}
