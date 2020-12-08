package com.adventofcode.adventofcode2020.tasks.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Bag {
    String color;
    Set<BagRule> bagRules;

    public Bag(String color) {
        this.color = color;
        this.bagRules = new HashSet<>();
    }

    public void addRule(BagRule bagRule){
        bagRules.add(bagRule);
    }

    public boolean canContainShinyGold(){
        Set<String> colorsOfBagsThatCanContain = bagRules.stream().map(BagRule::getColor).collect(Collectors.toSet());
        return colorsOfBagsThatCanContain.contains("shiny gold");
    }

    public boolean cantContainAnything(){
        Set<String> colorsOfBagsThatCanContain = bagRules.stream().map(BagRule::getColor).collect(Collectors.toSet());
        return colorsOfBagsThatCanContain.contains("no other bags");
    }
}
