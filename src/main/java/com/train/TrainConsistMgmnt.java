package com.train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ========================================================
 * MAIN CLASS - TrainConsistMgmnt
 * ========================================================
 *
 * Train Consist Management Application
 *
 * Use Case 8:  Filter Passenger Bogies Using Streams
 * Use Case 9:  Group Bogies by Type (Collectors.groupingBy)
 * Use Case 10: Count Total Seats in Train (reduce)
 * Use Case 11: Validate Train ID & Cargo Codes (Regex)
 * Use Case 12: Safety Compliance Check for Goods Bogies
 * Use Case 13: Performance Comparison (Loops vs Streams)
 * Use Case 14: Handle Invalid Bogie Capacity (Custom Exception)
 * Use Case 15: Safe Cargo Assignment Using try-catch-finally
 * Use Case 16: Sort Passenger Bogies by Capacity (Bubble Sort)
 * Use Case 17: Sort Bogie Names Using Arrays.sort()
 *
 * @author Developer
 * @version 17.0
 */
public class TrainConsistMgmnt {

    static class Bogie {
        String name; int capacity;
        Bogie(String name, int capacity) { this.name = name; this.capacity = capacity; }
    }

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) { super(message); }
    }

    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) { super(message); }
    }

    // uc 17
    public static String[] sortBogieNames(String[] names) {
        String[] copy = Arrays.copyOf(names, names.length);
        Arrays.sort(copy);
        return copy;
    }

    public static void main(String[] args) {
        System.out.println("\n===============================================");
        System.out.println(" UC17 - Sort Bogie Names Using Arrays.sort() ");
        System.out.println("===============================================\n");
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        System.out.println("Original Bogie Names:");
        System.out.println(Arrays.toString(bogieNames));
        String[] sortedNames = sortBogieNames(bogieNames);
        System.out.println("\nSorted Bogie Names (Alphabetical):");
        System.out.println(Arrays.toString(sortedNames));
        System.out.println("\nUC17 sorting completed...");

        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}