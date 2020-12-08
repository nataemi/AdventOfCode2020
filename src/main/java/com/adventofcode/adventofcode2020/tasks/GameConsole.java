package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.tasks.model.Instruction;
import com.adventofcode.adventofcode2020.tasks.model.Operation;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameConsole {

    public static int runInstructions(String resource){
        List<String> instructionStrings = TxtFileReader.read(resource);
        List<Instruction> instructions = mapInstructionStringsToInstructions(instructionStrings);
        int acc = 0;
        boolean repeatedInstruction = false;
        int index = 0;
        Instruction instruction;
        while(!repeatedInstruction){
            instruction = instructions.get(index);
            if(instruction.isDone()) repeatedInstruction = true;
            else {
                instructions.get(index).setDone(true);
                if (checkIfOperation(instruction, Operation.ACC)) {
                    acc += instruction.getAmount();
                    index++;
                } else if (checkIfOperation(instruction, Operation.JMP)) {
                    index += instruction.getAmount();
                } else {
                    index += 1;
                }
            }
        }
        return acc;
    }

    private static List<Instruction> mapInstructionStringsToInstructions(List<String> instructionStrings) {
        List<Instruction> instructions = new ArrayList<>();
        for(String instructionString: instructionStrings){
            String[] partsOfInstruction = instructionString.split(" ");
            instructions.add(new Instruction(Operation.valueOf(partsOfInstruction[0].toUpperCase()), Integer.parseInt(partsOfInstruction[1])));
        }
        return instructions;
    }

    public static void repairInstructions(String resource){
        List<String> instructionStrings = TxtFileReader.read(resource);
        List<Instruction> instructions = mapInstructionStringsToInstructions(instructionStrings);
        List<List<Instruction>> instructionsWithChangedJmpNop = new ArrayList<>();
        for(int i=0; i < instructions.size(); i++){
            List<Instruction> instructionsCopy = instructions.stream().map(Instruction::clone).collect(Collectors.toList());
            if(checkIfOperation(instructions.get(i), Operation.JMP)){
                instructionsCopy.get(i).setOpertation(Operation.NOP);
            }
            if(checkIfOperation(instructions.get(i), Operation.NOP)){
                instructionsCopy.get(i).setOpertation(Operation.JMP);
            }
            instructionsWithChangedJmpNop.add(instructionsCopy);
        }
        instructionsWithChangedJmpNop.forEach(GameConsole::searchForAccOfCorrectInstructionList);
    }

    private static void searchForAccOfCorrectInstructionList(List<Instruction> instructions) {
        int acc = 0;
        boolean repeatedInstruction = false;
        int index = 0;
        Instruction instruction;
        boolean lastInstruction = false;
        while(!repeatedInstruction && !lastInstruction){
            instruction = instructions.get(index);
            if(instruction.isDone()) repeatedInstruction = true;
            else {
                instructions.get(index).setDone(true);
                if (checkIfOperation(instruction, Operation.ACC)) {
                    acc += instruction.getAmount();
                    index++;
                } else if (checkIfOperation(instruction, Operation.JMP)) {
                    index += instruction.getAmount();
                } else {
                    index += 1;
                }
            }
            if(index >= instructions.size()){
                System.out.println(acc);
                lastInstruction = true;
            }
        }
    }

    private static boolean checkIfOperation(Instruction instruction, Operation acc) {
        return acc.getOperation().equals(instruction.getOpertation().getOperation());
    }


}
