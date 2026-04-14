package com.train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrainConsistMgmnt {

    static class Bogie {
        String name; int capacity;
        Bogie(String name, int capacity) { this.name = name; this.capacity = capacity; }
    }

    public static boolean binarySearch(String[] bogieIds, String searchId) {
        String[] sorted = Arrays.copyOf(bogieIds, bogieIds.length);
        Arrays.sort(sorted); // ensure sorted precondition
        int low = 0, high = sorted.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = sorted[mid].compareTo(searchId);
            if      (cmp == 0) return true;
            else if (cmp < 0)  low  = mid + 1;
            else               high = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("\n===============================================");
        System.out.println(" UC19 - Binary Search for Bogie ID ");
        System.out.println("===============================================\n");
        String[] bogieIds19 = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        Arrays.sort(bogieIds19);
        String key = "BG309";
        System.out.println("Sorted Bogie IDs:");
        for (String id : bogieIds19) System.out.println(id);
        boolean found19 = binarySearch(bogieIds19, key);
        System.out.println(found19 ? "\nBogie " + key + " found using Binary Search." : "\nBogie " + key + " not found.");
        System.out.println("\nUC19 search completed...");

    }
}