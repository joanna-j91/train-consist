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

    public static boolean linearSearch(String[] bogieIds, String searchId) {
        for (String id : bogieIds) {
            if (id.equals(searchId)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("\n===============================================");
        System.out.println(" UC18 - Linear Search for Bogie ID ");
        System.out.println("===============================================\n");
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        String searchId = "BG309";
        System.out.println("Available Bogie IDs:");
        for (String id : bogieIds) System.out.println(id);
        boolean found = linearSearch(bogieIds, searchId);
        System.out.println(found ? "\nBogie " + searchId + " found in train consist." : "\nBogie " + searchId + " not found.");
        System.out.println("\nUC18 search completed...");

        Scanner scanner = new Scanner(System.in);
        scanner.close();
    }
}