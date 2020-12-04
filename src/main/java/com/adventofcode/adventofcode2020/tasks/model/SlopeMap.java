package com.adventofcode.adventofcode2020.tasks.model;

import java.util.ArrayList;

public class SlopeMap {
    ArrayList<SlopeTier> slopeTiers;

    public SlopeMap() {
        this.slopeTiers = new ArrayList<>();
    }

    public ArrayList<SlopeTier> getSlopeTiers() {
        return slopeTiers;
    }
}
