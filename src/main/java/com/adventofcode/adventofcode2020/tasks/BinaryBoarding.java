package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding {

    final static int MAXROWNB = 127;
    final static int MAXCOLNB = 7;

    public static int findHighestSeatId(String resource){
        List<String> seats = TxtFileReader.read(resource);
        int maxSeatId = -1;
        for(String seat: seats){
            int seatId = calculateSeatId(seat);
            if (maxSeatId < seatId ) maxSeatId = seatId;
        }
        return maxSeatId;
    }

    public static int findYourSeatId(String resource){
        List<String> seats = TxtFileReader.read(resource);
        List<Integer> seatIds = new ArrayList<>();
        for(String seat: seats){
            int seatId = calculateSeatId(seat);
            seatIds.add(seatId);
        }
        Collections.sort(seatIds);
        for(int i =1 ; i<seatIds.size(); i ++){
            if(seatIds.get(i) != seatIds.get(i-1) + 1)
                return (seatIds.get(i) + seatIds.get(i-1))/2;
        }
        return -1;
    }

    static int calculateSeatId(String seat){
        List<String> partitions = seat.chars().mapToObj(Character::toString).collect(Collectors.toList());
        List<String> rowPartitions = partitions.subList(0,7);
        List<String> columnPartitions = partitions.subList(7,10);
        int minRowNb = getNbWithBinarySearch(rowPartitions, MAXROWNB, "F", "B");
        int minColNb = getNbWithBinarySearch(columnPartitions, MAXCOLNB, "L", "R");
        return calculateSeatId(minRowNb, minColNb);
    }

    private static int getNbWithBinarySearch(List<String> rowPartitions, int i, String f, String b) {
        int minRowNb = 0;
        int maxRowNb = i;
        for (String rowPartition : rowPartitions) {
            if (f.equals(rowPartition)) {
                maxRowNb -= ((maxRowNb + 1) - minRowNb) / 2;
            }
            if (b.equals(rowPartition)) {
                minRowNb += ((maxRowNb + 1) - minRowNb) / 2;
            }
        }
        return minRowNb;
    }

    static int calculateSeatId(int row, int column){
        return row * 8 + column;
    }
}
