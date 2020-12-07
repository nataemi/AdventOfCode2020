package com.adventofcode.adventofcode2020;

import com.adventofcode.adventofcode2020.tasks.ExpenseReportCalculator;
import com.adventofcode.adventofcode2020.tasks.PasswordCorporatePolicyValidator;
import com.adventofcode.adventofcode2020.tasks.ToboganMapTraverser;
import com.adventofcode.adventofcode2020.tasks.CustomDeclarationForms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCode2020Application {

    public static void main(String[] args) {
        final String RESOURCE = "/Users/maciek/IdeaProjects/AdventOfCode2020/src/main/resources/day6input.txt";

        SpringApplication.run(AdventOfCode2020Application.class, args);
        //System.out.println(ExpenseReportCalculator.findThreeEntriesThatSumTo2020AndMultiplyThen().get());
//        System.out.println(PasswordCorporatePolicyValidator.countValidPasswords(RESOURCE));
//        System.out.println(BinaryBoarding.findYourSeatId(RESOURCE));
        System.out.println(CustomDeclarationForms.sumQuestionsWithOnlyYesAnswersInGroups(RESOURCE));
//        System.out.println(PasswordCorporatePolicyValidator.countValidPasswords(RESOURCE));
        System.out.println(ToboganMapTraverser.traverseMap(RESOURCE));
    }

}
