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

    public static boolean linearSearch(String[] bogieIds, String searchId) {
        for (String id : bogieIds) { if (id.equals(searchId)) return true; }
        return false;
    }

    public static boolean searchWithValidation(String[] bogieIds, String searchId) {
        if (bogieIds.length == 0)
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        return linearSearch(bogieIds, searchId);
    }

    public static void main(String[] args) {
        System.out.println("\n===============================================");
        System.out.println(" UC20 - Exception Handling During Search ");
        System.out.println("===============================================\n");
        String[] emptyIds = {};
        String searchId = "BG101";
        searchWithValidation(emptyIds, searchId);

        System.out.println("\nUC20 execution completed...");

    }
}