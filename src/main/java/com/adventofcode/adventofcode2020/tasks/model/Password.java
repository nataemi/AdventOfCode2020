package com.adventofcode.adventofcode2020.tasks.model;

import org.springframework.util.StringUtils;

public class Password {
    String password;
    Policy policy;

    public Password(String password, Policy policy) {
        this.password = password;
        this.policy = policy;
    }

    public boolean validate(){
        int occurences = StringUtils.countOccurrencesOf(password, policy.getLetter());
        return  occurences <= policy.getMaxOccurence() && occurences >= policy.getMinOccurence();
    }

    public boolean validateByNewRules(){
        int passwordLength = password.length();
        boolean letterInMin = passwordLength >= policy.getMinOccurence() && checkIfLetterInPasswordAtIndex(policy.getMinOccurence());
        boolean letterAtMax = passwordLength >= policy. getMaxOccurence() && checkIfLetterInPasswordAtIndex(policy.getMaxOccurence());
        return  letterInMin ^ letterAtMax;
    }

    boolean checkIfLetterInPasswordAtIndex(int index){
       return policy.getLetter().equals(Character.toString(password.charAt(index-1)));
    }
}
