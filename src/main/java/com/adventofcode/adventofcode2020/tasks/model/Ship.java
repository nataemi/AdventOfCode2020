package com.adventofcode.adventofcode2020.tasks.model;

import lombok.Getter;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class Ship {
    Point position = new Point(0,0);
    Point wayPoint = new Point(10,1);
    int wayPointQuarter = 1; // 1 - 0-90 2 - 90-180 3 - 180-270 4- 270-460
    Direction directionThatItsFacing = Direction.E;
    List<Action> moveActions = Arrays.asList(Action.EAST, Action.WEST, Action.SOUTH, Action.NORTH);
    List<Action> turnActions = Arrays.asList(Action.LEFT, Action.RIGHT);

    public void move(Action action, int steps){
        if(moveActions.contains(action)){
            Direction direction = Direction.valueOf(action.action);
            position =
                    new Point(position.x + direction.movementOnAxisx * steps,
                            position.y + direction.movementOnAxisy * steps);
        }
        if(action.equals(Action.FORWARD)){
            position =
                    new Point(position.x + directionThatItsFacing.movementOnAxisx * steps,
                            position.y + directionThatItsFacing.movementOnAxisy * steps);
        }
        if(turnActions.contains(action)){
            int newDegrees = directionThatItsFacing.degrees;
            if(action.equals(Action.LEFT)) newDegrees -= steps;
            if(action.equals(Action.RIGHT)) newDegrees += steps;
            if(newDegrees >= 360) newDegrees -= 360;
            if(newDegrees < 0) newDegrees += 360;
            directionThatItsFacing = Direction.E.getEnumFromDegrees(newDegrees);
        }
    }

    public void moveDependingOnWayPoint(Action action, int steps){
        if(moveActions.contains(action)){
            Direction direction = Direction.valueOf(action.action);
            wayPoint =
                    new Point(wayPoint.x + direction.movementOnAxisx * steps,
                            wayPoint.y + direction.movementOnAxisy * steps);
        }
        if(action.equals(Action.FORWARD)){
            int onXmovement = steps * wayPoint.x;
            int onYmovement = steps * wayPoint.y;
            position =
                    new Point(position.x + onXmovement,
                            position.y + onYmovement);
            wayPoint =
                    new Point(wayPoint.x + onXmovement,
                            wayPoint.y + onYmovement);
        }
        if(turnActions.contains(action)){
            int turnQuartes = steps / 90;
            if(action.equals(Action.LEFT)) wayPointQuarter -= turnQuartes;
            if(action.equals(Action.RIGHT)) wayPointQuarter += turnQuartes;
            if(wayPointQuarter < 1) wayPointQuarter += 4;
            if(wayPointQuarter > 4) wayPointQuarter -= 4;
            if(wayPointQuarter == 1) wayPoint = new Point(Math.abs(wayPoint.x), Math.abs(wayPoint.y));
            if(wayPointQuarter == 2) wayPoint = new Point(Math.abs(wayPoint.x), -Math.abs(wayPoint.y));
            if(wayPointQuarter == 3) wayPoint = new Point(-Math.abs(wayPoint.x), -Math.abs(wayPoint.y));
            if(wayPointQuarter == 4) wayPoint = new Point(-Math.abs(wayPoint.x), Math.abs(wayPoint.y));
        }
    }
}
