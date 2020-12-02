package com.adventofcode.adventofcode2020.tasks.model;


public class Policy {
    String letter;
    int minOccurence;
    int maxOccurence;

    public Policy(String letter, int minOccurence, int maxOccurence) {
        this.letter = letter;
        this.minOccurence = minOccurence;
        this.maxOccurence = maxOccurence;
    }

    public String getLetter() {
        return letter;
    }

    public int getMinOccurence() {
        return minOccurence;
    }

    public int getMaxOccurence() {
        return maxOccurence;
    }


}
