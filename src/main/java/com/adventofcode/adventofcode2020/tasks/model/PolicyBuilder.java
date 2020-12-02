package com.adventofcode.adventofcode2020.tasks.model;

public final class PolicyBuilder {
    String letter;
    int minOccurence;
    int maxOccurence;

    private PolicyBuilder() {
    }

    public static PolicyBuilder aPolicy() {
        return new PolicyBuilder();
    }

    public PolicyBuilder withLetter(String letter) {
        this.letter = letter;
        return this;
    }

    public PolicyBuilder withMinOccurence(int minOccurence) {
        this.minOccurence = minOccurence;
        return this;
    }

    public PolicyBuilder withMaxOccurence(int maxOccurence) {
        this.maxOccurence = maxOccurence;
        return this;
    }

    public Policy build() {
        return new Policy(letter, minOccurence, maxOccurence);
    }
}
