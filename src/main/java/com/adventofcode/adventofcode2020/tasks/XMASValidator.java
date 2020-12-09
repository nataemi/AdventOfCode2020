package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMASValidator {

    public static long validate(String resource){
        List<String> numbersAsString = TxtFileReader.read(resource);
        List<Long> numbers = Converter.converttoLong(numbersAsString);
        int preamble = 5;
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

    public static BigInteger findEncryptionWeakness(String resource){
        List<String> numbersAsString = TxtFileReader.read(resource);
        List<BigInteger> numbers = Converter.convertToBigInteger(numbersAsString);
        BigInteger invalidNumber = BigInteger.valueOf(validate(resource));
        List<BigInteger> rangeThatSumsToInvalidNumber =  findRange(invalidNumber, numbers);
        Collections.sort(rangeThatSumsToInvalidNumber);
        return rangeThatSumsToInvalidNumber.get(0).add(rangeThatSumsToInvalidNumber.get(rangeThatSumsToInvalidNumber.size()-1));
    }

    private static List<BigInteger> findRange(BigInteger invalidNumber, List<BigInteger> numbers) {
        List<BigInteger> range = new ArrayList<>();
        int rangeSize = 2;
        boolean rangeFound = false;
        while(!rangeFound) {
            for (int index = 0; index < numbers.size() - rangeSize; index++) {
                range = numbers.subList(index, index + rangeSize);
                if(range.stream().reduce(BigInteger.ZERO, (a,b) -> a.add(b)).equals(invalidNumber)){
                    rangeFound = true;
                    break;
                }
            }
            rangeSize++;
        }
        return range;
    }
}
