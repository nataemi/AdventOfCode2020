package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.List;

public class XMASValidator {

    public static long validate(String resource){
        List<String> numbersAsString = TxtFileReader.read(resource);
        List<Long> numbers = Converter.converttoLong(numbersAsString);
        int preamble = 25;
        List<Long> invalidNumbers = new ArrayList<>();
        for(int index=preamble;index < numbers.size(); index++){
            if(!validateNumber(preamble, index, numbers)) invalidNumbers.add(numbers.get(index));
        }
        return invalidNumbers.get(0);
    }

    private static boolean validateNumber(int preamble, int index, List<Long> numbers) {
        List<Long> numbersForValidation = numbers.subList(index-preamble, index);
        boolean valid = false;
        for(long add1: numbersForValidation){
            for(long add2: numbersForValidation){
                if(add1 != add2 && add1 + add2 == numbers.get(index)){
                    valid = true;
                }
            }
        }
        return valid;
    }
}
