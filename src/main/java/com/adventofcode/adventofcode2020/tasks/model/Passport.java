package com.adventofcode.adventofcode2020.tasks.model;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Passport {
    String byr;
    String iyr;
    String eyr;
    String hgt;
    String hcl;
    String ecl;
    String pid;
    String cid;

    //byr:1953 hgt:190cm pid:156cm hcl:#7d3b0c eyr:2022 ecl:#1b0b35 iyr:2015
    //hgt:159cm ecl:blu pid:5642951907 iyr:2029 byr:1952 hcl:#6b5442
    public Passport(String passportString) {
        List<String> passportInfo = Arrays.asList(passportString.split(" "));
        Class aClass = this.getClass();
        for(String info : passportInfo){
            String[] infoAndVal = info.split(":");
            try {
                aClass.getDeclaredField(infoAndVal[0]).set(this,infoAndVal[1]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean validate(){
        return validateByr() && validateEcl() && validateEyr() && validateHcl() && validateIyr() && validateHgt() && validatePid();
    }

    boolean validateHgt(){
        if(hgt == null) return false;
        if (hgt.contains("cm")) return stringBetween(hgt.substring(0, hgt.length()-2), 193, 150);
        if (hgt.contains("in")) return stringBetween(hgt.substring(0, hgt.length()-2), 76, 59);
        return false;
    }

    boolean validatePid(){
        if(pid == null) return false;
        return Pattern.matches("[0-9]{9}", pid);
    }

    boolean validateHcl(){
        if(hcl == null) return false;
        return Pattern.matches("#[a-f0-9]{6}", hcl);
    }

    boolean validateEcl(){
        if(ecl == null) return false;
        return ecl.length() == 3 && Pattern.matches("amb|blu|brn|gry|grn|hzl|oth", ecl);
    }

    boolean validateEyr(){
        if(eyr == null) return false;
        return eyr.length() == 4 && stringBetween(eyr, 2030, 2020);
    }

    boolean validateIyr(){
        if(iyr == null) return false;
        return iyr.length() == 4 && stringBetween(iyr, 2020, 2010);
    }


    boolean validateByr(){
        if(byr == null) return false;
        return byr.length() == 4 && stringBetween(byr, 2002, 1920);
    }

    private boolean stringBetween(String value, int max, int min) {
        return Integer.parseInt(value) >= min && Integer.parseInt(value) <= max;
    }
}
