package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.tasks.model.SlopeMap;
import com.adventofcode.adventofcode2020.tasks.model.SlopeTier;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.awt.*;
import java.math.BigInteger;
import java.util.List;

public class ToboganMapTraverser {

    static SlopeMap slopeMap;
    static int mapRepetitionWidth;
    static int mapRepetitionLength;


    public static BigInteger traverseMap(String fileName){
        List<String> stringSlopeMap = TxtFileReader.read(fileName);
        slopeMap = mapStringListToSlopeMap(stringSlopeMap);
        mapRepetitionWidth = stringSlopeMap.get(0).length();
        mapRepetitionLength = stringSlopeMap.size();

        return  countTreesEncounteredInTraverse(1,1).multiply(
                countTreesEncounteredInTraverse(3,1)).multiply(
                countTreesEncounteredInTraverse(5,1)).multiply(
                countTreesEncounteredInTraverse(7,1)).multiply(
                countTreesEncounteredInTraverse(1,2));
    }

     static BigInteger countTreesEncounteredInTraverse(int right, int down){
        Point position = new Point(0,0);
        long trees = 0;
        while (position.y< mapRepetitionLength){
            if (slopeMap.getSlopeTiers().get(position.y).checkIfTreeAtPosition(position.x%mapRepetitionWidth)) trees ++;
            position.setLocation(position.x+right, position.y+down);
        }
        return BigInteger.valueOf(trees);
    }

     static SlopeMap mapStringListToSlopeMap(List<String> stringSlopeMap){
        SlopeMap slopeMap = new SlopeMap();
        for(String stringTier: stringSlopeMap){
            SlopeTier slopeTier = new SlopeTier();
            slopeMap.getSlopeTiers().add(slopeTier);
            char[] tier = stringTier.toCharArray();
            char TREE = '#';
            for (int i=0; i < tier.length; i++){
                if(TREE ==(tier[i])) slopeTier.getTrees().add(i);
            }
        }
        return slopeMap;
    }
}
