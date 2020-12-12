package com.adventofcode.adventofcode2020.tasks.model;

public enum Action {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    String action;

    Action(String action) {
        this.action = action;
    }

    public static Action fromString(String text) {
        for (Action b : Action.values()) {
            if (b.action.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
