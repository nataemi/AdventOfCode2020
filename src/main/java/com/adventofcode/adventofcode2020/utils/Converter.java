package com.adventofcode.adventofcode2020.utils;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter {

    public static List<Integer> convert(List<String> stringList){
        return convertList(stringList, s -> Integer.parseInt(s));
    }

    public static List<Long> converttoLong(List<String> stringList){
        return convertList(stringList, s -> Long.parseLong(s));
    }

    static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

    public static List<String> convertStringToLetterStringList(String string){
        return string.chars().mapToObj(Character::toString).collect(Collectors.toList());
    }

    public static List<BigInteger> convertToBigInteger(List<String> numbersAsString) {
        return convertList(numbersAsString, s -> BigInteger.valueOf(Long.parseLong(s)));
    }
}



