package com.adventofcode.adventofcode2020.tasks.model;

import lombok.Getter;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class Ship {
    Point position = new Point(0,0);
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
}
