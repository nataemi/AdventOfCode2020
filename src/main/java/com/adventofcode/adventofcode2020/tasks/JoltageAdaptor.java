package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class JoltageAdaptor {


    public static int calculateJoltageDiff(String resource){
        List<String> adaptersStrings = TxtFileReader.read(resource);
        List<Integer> adapters = Converter.convert(adaptersStrings);
        Collections.sort(adapters);
        Map<Integer, AtomicLong> countAmountOfDiffs = countAmountOfAdapterDiffs(adapters, 3);
        return countAmountOfDiffs.get(1).intValue() * countAmountOfDiffs.get(3).intValue();
    }

    private static Map<Integer, AtomicLong>  countAmountOfAdapterDiffs(List<Integer> adapters, int maxDiffBetweenAdapters) {
        Map<Integer, AtomicLong> countAmountOfDiffs = new HashMap<>();
        addBuiltInAdapter(adapters, maxDiffBetweenAdapters);
        int currentAdapter = 0;
        for(int index=0;index<adapters.size();index++){
            boolean nextAdapterFound = false;
            while(!nextAdapterFound){
                int checkAdapter = adapters.get(index);
                if(checkAdapter - currentAdapter <= maxDiffBetweenAdapters){
                    nextAdapterFound = true;
                    countAmountOfDiffs.putIfAbsent(checkAdapter - currentAdapter, new AtomicLong(0));
                    countAmountOfDiffs.get(checkAdapter-currentAdapter).incrementAndGet();
                    currentAdapter = checkAdapter;
                }
                else {
                    index++;
                }
            }
        }
        return countAmountOfDiffs;
    }

    private static void addBuiltInAdapter(List<Integer> adapters, int maxDiffBetweenAdapters) {
        adapters.add(adapters.get(adapters.size()-1) + maxDiffBetweenAdapters);
    }

}
