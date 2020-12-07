package com.adventofcode.adventofcode2020.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter {

    public static List<Integer> convert(List<String> stringList){
        return convertList(stringList, s -> Integer.parseInt(s));
    }

    static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

    public static List<String> convertStringToLetterStringList(String string){
        return string.chars().mapToObj(Character::toString).collect(Collectors.toList());
    }

}



