package com.adventofcode.adventofcode2020.tasks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instruction {
     Operation opertation;
     int amount;
     boolean done = false;

    public Instruction(Operation opertation, int amount) {
        this.opertation = opertation;
        this.amount = amount;
    }

    @Override
    public Instruction clone(){
        return new Instruction(opertation,amount);
    }

}
