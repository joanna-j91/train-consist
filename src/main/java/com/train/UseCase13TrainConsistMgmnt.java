package com.train;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UseCase13TrainConsistMgmnt {

    // Bogie model
    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type     = type;
            this.capacity = capacity;
        }
    }

    // Loop-based filtering
    public static List<Bogie> filterByLoop(List<Bogie> bogies, int threshold) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > threshold) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream-based filtering
    public static List<Bogie> filterByStream(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    public static long measureLoopTime(List<Bogie> bogies, int threshold) {
        long start = System.nanoTime();
        filterByLoop(bogies, threshold);
        return System.nanoTime() - start;
    }

    public static long measureStreamTime(List<Bogie> bogies, int threshold) {
        long start = System.nanoTime();
        filterByStream(bogies, threshold);
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println(" UC13 - Performance Comparison (Loops vs Streams) ");
        System.out.println("===============================================\n");

        // Create large test dataset
        List<Bogie> bogies = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            bogies.add(new Bogie("Sleeper", 50 + (i % 50)));
        }

        long loopTime   = measureLoopTime(bogies, 60);
        long streamTime = measureStreamTime(bogies, 60);

        System.out.println("Loop Execution Time (ns): "   + loopTime);
        System.out.println("Stream Execution Time (ns): " + streamTime);

        System.out.println("\nUC13 performance benchmarking completed...");
    }
}