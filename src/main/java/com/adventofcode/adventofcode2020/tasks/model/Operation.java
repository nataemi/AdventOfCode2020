package com.adventofcode.adventofcode2020.tasks.model;

public enum Operation {
    ACC("acc"),
    JMP("jmp"),
    NOP("nop");

    private String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
