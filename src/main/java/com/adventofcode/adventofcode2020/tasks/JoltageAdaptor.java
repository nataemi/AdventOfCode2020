package com.adventofcode.adventofcode2020.tasks;

import com.adventofcode.adventofcode2020.utils.Converter;
import com.adventofcode.adventofcode2020.utils.TxtFileReader;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class JoltageAdaptor {


    public static int calculateJoltageDiff(String resource) {
        List<String> adaptersStrings = TxtFileReader.read(resource);
        List<Integer> adapters = Converter.convert(adaptersStrings);
        Collections.sort(adapters);
        Map<Integer, AtomicLong> countAmountOfDiffs = countAmountOfAdapterDiffs(adapters, 3);
        return countAmountOfDiffs.get(1).intValue() * countAmountOfDiffs.get(3).intValue();
    }

    private static Map<Integer, AtomicLong> countAmountOfAdapterDiffs(List<Integer> adapters, int maxDiffBetweenAdapters) {
        Map<Integer, AtomicLong> countAmountOfDiffs = new HashMap<>();
        addBuiltInAdapter(adapters, maxDiffBetweenAdapters);
        int currentAdapter = 0;
        for (int index = 0; index < adapters.size(); index++) {
            boolean nextAdapterFound = false;
            while (!nextAdapterFound) {
                int checkAdapter = adapters.get(index);
                if (checkAdapter - currentAdapter <= maxDiffBetweenAdapters) {
                    nextAdapterFound = true;
                    countAmountOfDiffs.putIfAbsent(checkAdapter - currentAdapter, new AtomicLong(0));
                    countAmountOfDiffs.get(checkAdapter - currentAdapter).incrementAndGet();
                    currentAdapter = checkAdapter;
                } else {
                    index++;
                }
            }
        }
        return countAmountOfDiffs;
    }

    private static void addBuiltInAdapter(List<Integer> adapters, int maxDiffBetweenAdapters) {
        adapters.add(adapters.get(adapters.size() - 1) + maxDiffBetweenAdapters);
    }


    public static long waysToConnect(String resource){
        List<String> adaptersStrings = TxtFileReader.read(resource);
        List<Integer> adapters = Converter.convert(adaptersStrings);
        adapters.add(0);
        Collections.sort(adapters);
        int maxDiffBetweenAdapters = 3;
        addBuiltInAdapter(adapters, maxDiffBetweenAdapters);

        Map<Integer, List<Integer>> possibleConnections = findPossibleConnectionsForEveryAdapter(adapters, maxDiffBetweenAdapters);

        Map<Integer, Long> waysToGetConnect = new HashMap<>();
        waysToGetConnect.put(0, 1L);
        long count = 0;
        for (int index = 1; index < adapters.size(); index++) {
            count = 0;
            for (int possibleConnection : possibleConnections.get(adapters.get(index))) {
                count += waysToGetConnect.get(possibleConnection);
            }
            waysToGetConnect.put(adapters.get(index), count);
        }

        return count;
    }

    private static Map<Integer, List<Integer>> findPossibleConnectionsForEveryAdapter(List<Integer> adapters, int maxDiffBetweenAdapters) {
        Map<Integer, List<Integer>> possibleConnections = new HashMap<>();
        for (int index = adapters.size() - 1; index >= 0; index--) {
            int previousAdapterIndex = index - 1;
            List<Integer> children = new ArrayList<>();
            while (previousAdapterIndex >= 0 && adapters.get(index) - adapters.get(previousAdapterIndex)<= maxDiffBetweenAdapters) {
                children.add(adapters.get(previousAdapterIndex));
                previousAdapterIndex--;
            }
            possibleConnections.put(adapters.get(index), children);
        }
        return possibleConnections;
    }
}
