package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.tasks.model.Bag;
import com.adventofcode.adventofcode2020.tasks.model.BagRule;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LuggageChecker {

    public static int countBagsThatCanContainGoldBag(String resource){
        List<String> rules = TxtFileReader.read(resource);
        List<Bag> bags = convertStringRulesToBags(rules);
        int bagsWithGoldBag = 0;
        for(Bag bag : bags){
            if(canContainShinyGold(bag, bags)) bagsWithGoldBag++;
        }
        return bagsWithGoldBag;
    }

    public static int countHowManyBagsInGoldBag(String resource){
        List<String> rules = TxtFileReader.read(resource);
        List<Bag> bags = convertStringRulesToBags(rules);
        Bag bag = findBagByColor("shiny gold", bags).get();
        return howManyBagsBagContains(bag,bags);
    }

    private static List<Bag> convertStringRulesToBags(List<String> rules) {
        List<Bag> bags = new ArrayList<>();
        for( String rule : rules){
            String[] partsOfLine = rule.split("bags contain|,");
            Bag bag = new Bag(partsOfLine[0].trim());
            for(int i=1; i < partsOfLine.length; i++) {
                String[] partsOfRule = partsOfLine[i].trim().split(" ");
                if("no".equals(partsOfRule[0])) bag.addRule(new BagRule(-1, "no other bags"));
                else bag.addRule(new BagRule(Integer.parseInt(partsOfRule[0]),partsOfRule[1] + " " + partsOfRule[2]));
            }
            bags.add(bag);
        }
        return bags;
    }

    private static boolean canContainShinyGold(Bag bag, List<Bag> bags) {
        List<String> allColorsThatBagCanContain = new ArrayList<>();
        allThatBagCanContain(bag,bags, allColorsThatBagCanContain);
        return allColorsThatBagCanContain.contains("shiny gold");
    }

    private static void allThatBagCanContain(Bag bag, List<Bag> bags, List<String> allColorsThatBagCanContain) {
        for (BagRule bagRule : bag.getBagRules()) {
            Optional<Bag> bagFromRule = findBagByColor(bagRule.getColor(), bags);
            if(bagFromRule.isPresent()){
                allColorsThatBagCanContain.add(bagFromRule.get().getColor());
                allThatBagCanContain(bagFromRule.get(), bags, allColorsThatBagCanContain);
            }
        }
    }

    private static Optional<Bag> findBagByColor(String color, List<Bag> bags){
        for(Bag bag: bags){
            if(bag.getColor().equals(color)) return Optional.of(bag);
        }
        if("shiny gold".equals(color)) return Optional.of(new Bag("shiny gold"));
        return Optional.empty();
    }

    private static int howManyBagsBagContains(Bag bag, List<Bag> bags) {
        List<Integer> allQuantitiesThatBagContains = new ArrayList<>();
        allQuantitiesThatBagContains(bag,bags, allQuantitiesThatBagContains, 1);
        return allQuantitiesThatBagContains.stream().reduce(0, (a, b) -> a + b);
    }

    private static void allQuantitiesThatBagContains(Bag bag, List<Bag> bags, List<Integer> allQuantitiesThatBagContains, int quantity) {
        for (BagRule bagRule : bag.getBagRules()) {
            Optional<Bag> bagFromRule = findBagByColor(bagRule.getColor(), bags);
            if(bagFromRule.isPresent()){
                allQuantitiesThatBagContains.add(bagRule.getQuantity() * quantity);
                allQuantitiesThatBagContains(bagFromRule.get(), bags, allQuantitiesThatBagContains, quantity * bagRule.getQuantity());
            }
        }
    }
}
