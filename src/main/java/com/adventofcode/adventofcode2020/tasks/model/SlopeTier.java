package com.adventofcode.adventofcode2020.tasks.model;

import java.util.HashSet;
import java.util.Set;

public class SlopeTier {
    Set<Integer> trees;

    public SlopeTier() {
        trees = new HashSet<>();
    }

    public boolean checkIfTreeAtPosition(int position){
        return trees.contains(position);
    }

    public Set<Integer> getTrees() {
        return trees;
    }
}
