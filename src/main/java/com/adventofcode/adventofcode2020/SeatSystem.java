package com.adventofcode.adventofcode2020;

import com.adventofcode.adventofcode2020.tasks.model.SeatStatus;
import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SeatSystem {

    public static long countEmptySeats(String resource){
        List<String> rowStrings = TxtFileReader.read(resource);
        Map<Point, SeatStatus> seatsModel = convertRowStringsToOccupiedSeats(rowStrings);
        long countOccupiedSeats = 0;
        long countOccupiedSeatsAfterMix = -1l;
        while(countOccupiedSeats != countOccupiedSeatsAfterMix) {
            countOccupiedSeats = countOccupiedSeatsAfterMix;
            seatsModel = mixSeats(seatsModel);
            countOccupiedSeatsAfterMix = getCountOccupied(seatsModel.values());
        }
        return countOccupiedSeats;
    }

    private static Map<Point, SeatStatus> mixSeats(Map<Point, SeatStatus> seatsModel) {
        Map<Point, SeatStatus> seatsAfterMix = new HashMap<>();
        for(Point point: seatsModel.keySet()){
                List<SeatStatus> nearbySeats = new ArrayList<>();
                getNearbySeats(seatsModel, point, nearbySeats);
                long countOccupied = getCountOccupied(nearbySeats);
                if(seatsModel.get(point).equals(SeatStatus.OCCUPIED) && countOccupied >= 5) seatsAfterMix.put(point, SeatStatus.EMPTY);
                else if(seatsModel.get(point).equals(SeatStatus.EMPTY) &&countOccupied == 0) seatsAfterMix.put(point, SeatStatus.OCCUPIED);
                else seatsAfterMix.put(point,seatsModel.get(point));
        }
        return seatsAfterMix;
    }

    private static long getCountOccupied(Collection<SeatStatus> seats) {
        return seats.stream().filter(x -> x.equals(SeatStatus.OCCUPIED)).count();
    }

    private static void getNearbySeats(Map<Point, SeatStatus> seatsModel, Point point, List<SeatStatus> nearbySeats) {
        addNearbySeat(seatsModel, point, nearbySeats,0,1,0,0);
        addNearbySeat(seatsModel, point, nearbySeats,0,0,1,0);
        addNearbySeat(seatsModel, point, nearbySeats,0,1,0,1);
        addNearbySeat(seatsModel, point, nearbySeats,1,1,0,0);
        addNearbySeat(seatsModel, point, nearbySeats,0,0,1,1);
        addNearbySeat(seatsModel, point, nearbySeats,1,0,1,0);
        addNearbySeat(seatsModel, point, nearbySeats,1,0,0,0);
        addNearbySeat(seatsModel, point, nearbySeats,0,0,0,1);
    }

    private static void addNearbySeat(Map<Point, SeatStatus> seatsModel, Point point, List<SeatStatus> nearbySeats, int up, int left, int right, int down) {
        point = new Point(point.x-left+right, point.y-down+up);
        if(seatsModel.containsKey(point)) {
            if (seatsModel.get(point).equals(SeatStatus.FLOOR)) { ;
                addNearbySeat(seatsModel, point, nearbySeats, up, left, right, down);
            }
            else {
                nearbySeats.add(seatsModel.get(point));
            }
        }
    }

    private static Map<Point, SeatStatus> convertRowStringsToOccupiedSeats(List<String> rowStrings) {
        Map<Point, SeatStatus> seatsModel = new HashMap<>();
        for(int rowNb=0 ; rowNb< rowStrings.size(); rowNb++){
            List<String> seatStrings = Converter.convertStringToLetterStringList(rowStrings.get(rowNb));
            for(int colNb=0; colNb<seatStrings.size(); colNb++){
                if(SeatStatus.FLOOR.getSign().equals(seatStrings.get(colNb)))
                    seatsModel.put(new Point(rowNb,colNb), SeatStatus.FLOOR);
                else
                    seatsModel.put(new Point(rowNb,colNb), SeatStatus.OCCUPIED);
            }
        }
        return seatsModel;
    }
}
