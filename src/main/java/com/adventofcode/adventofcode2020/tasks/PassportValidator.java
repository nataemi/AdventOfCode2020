package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.tasks.model.Passport;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassportValidator {

    public static long countValid(String resource){
        List<String> input = TxtFileReader.read(resource);
        List<Passport> passports = mapInputToPassports(input);
        return passports.stream()
                .filter(Passport::validate)
                .count();
        }

    private static List<Passport> mapInputToPassports(List<String> input) {
        List<String> passportStrings = mapInputToPassportStrings(input);
        List<Passport> passports = passportStrings.stream().map(Passport::new).collect(Collectors.toList());
        return passports;
    }


    private static List<String> mapInputToPassportStrings(List<String> input) {
        List<String> passports = new ArrayList<>();
        String passport = "";
        for(String line : input){
            if(line.isBlank()){
                passports.add(passport);
                passport = "";
            }
            else{
                passport += line + " ";
            }
        }
        passports.add(passport);
        return passports;
    }

}
