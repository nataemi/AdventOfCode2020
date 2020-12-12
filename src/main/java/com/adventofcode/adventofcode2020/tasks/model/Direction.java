package com.adventofcode.adventofcode2020.tasks.model;

public enum Direction {
    N(0, 1, 0),
    S(0, -1, 180),
    E(1, 0, 90),
    W(-1, 0, 270);

    int movementOnAxisx;
    int movementOnAxisy;
    int degrees;

    Direction(int movementOnAxisx, int movementOnAxisy, int degrees) {
        this.movementOnAxisx = movementOnAxisx;
        this.movementOnAxisy = movementOnAxisy;
        this.degrees = degrees;
    }

    public Direction getEnumFromDegrees(int degrees) {
        if(degrees == 0)
        return Direction.N;
        else if(degrees == 90)
            return Direction.E;
        else if(degrees == 180)
            return Direction.S;
        else
            return Direction.W;
    }

}
