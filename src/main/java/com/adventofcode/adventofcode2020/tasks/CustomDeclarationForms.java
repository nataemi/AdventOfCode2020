package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomDeclarationForms {

    public static int sumQuestionsWithYesAnswersInGroups(String resource){
        List<String> allAnswers = TxtFileReader.read(resource);
        List<Set<String>> answersInGroups = new ArrayList<>();
        divideInputAnswersIntoGroups(allAnswers, answersInGroups);
        int sum = 0;
        for (Set<String> answers : answersInGroups){
            sum+= questionsWithYesAnswersInGroup(answers).size();
        }
        return sum;
    }

    public static int sumQuestionsWithOnlyYesAnswersInGroups(String resource){
        List<String> allAnswers = TxtFileReader.read(resource);
        List<Set<String>> answersInGroups = new ArrayList<>();
        divideInputAnswersIntoGroups(allAnswers, answersInGroups);
        return countQuestionsWithOnlyYesAnswersInGroup(answersInGroups);
    }

    private static Set<String> questionsWithYesAnswersInGroup(Set<String> answersInGroups) {
        Set<String> questionsWithYesAnswers = new HashSet<>();
        for(String answer: answersInGroups){
            List<String> questions = Converter.convertStringToLetterStringList(answer);
            questionsWithYesAnswers.addAll(questions);
        }
        return questionsWithYesAnswers;
    }

    private static int countQuestionsWithOnlyYesAnswersInGroup(List<Set<String>> answersInGroups) {
        int sum = 0;
        List<Set<String>> questionsWithYesAnswersInGroups = new ArrayList<>();
        for (Set<String> answers : answersInGroups){
            questionsWithYesAnswersInGroups.add(questionsWithYesAnswersInGroup(answers));
        }
        for(int i=0 ; i< questionsWithYesAnswersInGroups.size(); i++){
            for (String question : questionsWithYesAnswersInGroups.get(i)){
                boolean yesInEveryQuestion = true;
                for(String answer: answersInGroups.get(i)){
                    if(!answer.contains(question)) yesInEveryQuestion = false;
                }
                if(yesInEveryQuestion) sum++;
            }
        }
        return sum;
    }

    private static void divideInputAnswersIntoGroups(List<String> allAnswers, List<Set<String>> answersInGroups) {
        Set<String> groupAnswers = new HashSet<>();
        for(String line : allAnswers){
            if(line.isBlank()){
                answersInGroups.add(groupAnswers);
                groupAnswers = new HashSet<>();
            }
            else{
                groupAnswers.add(line);
            }
        }
        answersInGroups.add(groupAnswers);
    }
}
