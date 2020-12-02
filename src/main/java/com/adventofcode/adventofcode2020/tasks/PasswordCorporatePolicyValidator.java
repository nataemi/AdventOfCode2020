package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.tasks.model.Password;
import com.adventofcode.adventofcode2020.tasks.model.PolicyBuilder;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;


import java.util.ArrayList;
import java.util.List;


public class PasswordCorporatePolicyValidator {

    public static long countValidPasswords(String resource){
        List<String> passwordsWithPolicies = TxtFileReader.read(resource);
        List<Password> passwords = mapLinesToPasswords(passwordsWithPolicies);
        return passwords.stream().filter(password -> password.validateByNewRules()).count();
    }

    private static List<Password> mapLinesToPasswords(List<String> passwordsWithPolicies) {
        List<Password> passwords = new ArrayList<>();
        for (String record : passwordsWithPolicies) {
            String[] policyStringAndPassword = record.split("\\W");
            passwords.add(
                    new Password(policyStringAndPassword[4],
                            PolicyBuilder.aPolicy()
                            .withLetter(policyStringAndPassword[2])
                            .withMinOccurence(Integer.parseInt(policyStringAndPassword[0]))
                            .withMaxOccurence(Integer.parseInt(policyStringAndPassword[1]))
                            .build()));
                            }
        return passwords;
    }

}
