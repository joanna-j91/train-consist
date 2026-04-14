package com.train;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrainConsistMgmnt {

    // ---- BOGIE MODEL ----
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name     = name;
            this.capacity = capacity;
        }
    }

    // ---- GOODS BOGIE MODEL ----
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type  = type;
            this.cargo = cargo;
        }
    }

    // ---- UC14: CUSTOM CHECKED EXCEPTION ----
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) { super(message); }
    }

    // ---- UC14: PASSENGER BOGIE WITH VALIDATION ----
    static class PassengerBogie {
        String type;
        int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0)
                throw new InvalidCapacityException("Capacity must be greater than zero");
            this.type     = type;
            this.capacity = capacity;
        }
    }

    // ---- UC15: CUSTOM RUNTIME EXCEPTION ----
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) { super(message); }
    }

    // ---- UC15: GOODS BOGIE WITH CARGO ASSIGNMENT ----
    static class SafeGoodsBogie {
        String shape;
        String cargo;
        boolean finallyExecuted = false;

        SafeGoodsBogie(String shape) { this.shape = shape; }

        // Returns true if assignment succeeded, false if exception was caught
        boolean assignCargo(String cargo) {
            try {
                // Rule: Rectangular bogie cannot carry Petroleum
                if (this.shape.equals("Rectangular") && cargo.equals("Petroleum")) {
                    throw new CargoSafetyException("Unsafe cargo assignment!");
                }
                this.cargo = cargo;
                System.out.println("Cargo assigned successfully -> " + cargo);
                return true;
            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            } finally {
                finallyExecuted = true;
                System.out.println("Cargo validation completed for " + this.shape + " bogie");
            }
        }
    }

    // ---- UC8: Filter Bogies Using Streams ----
    public static List<Bogie> filterBogies(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    // ---- UC9: Group Bogies by Type ----
    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));
    }

    // ---- UC10: Count Total Seats (reduce) ----
    public static int countTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    // ---- UC11: Validate Train ID & Cargo Code (Regex) ----
    static final String TRAIN_ID_PATTERN   = "TRN-\\d{4}";
    static final String CARGO_CODE_PATTERN = "PET-[A-Z]{2}";

    public static boolean validateTrainId(String trainId) {
        return Pattern.compile(TRAIN_ID_PATTERN).matcher(trainId).matches();
    }

    public static boolean validateCargoCode(String cargoCode) {
        return Pattern.compile(CARGO_CODE_PATTERN).matcher(cargoCode).matches();
    }

    // ---- UC12: Safety Compliance Check ----
    public static boolean isSafetyCompliant(List<GoodsBogie> goodsBogies) {
        return goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));
    }

    // ---- UC13: Performance Comparison (Loops vs Streams) ----
    public static List<Bogie> filterByLoop(List<Bogie> bogies, int threshold) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) { if (b.capacity > threshold) result.add(b); }
        return result;
    }

    public static List<Bogie> filterByStream(List<Bogie> bogies, int threshold) {
        return bogies.stream().filter(b -> b.capacity > threshold).collect(Collectors.toList());
    }

    public static long measureLoopTime(List<Bogie> bogies, int threshold) {
        long start = System.nanoTime(); filterByLoop(bogies, threshold); return System.nanoTime() - start;
    }

    public static long measureStreamTime(List<Bogie> bogies, int threshold) {
        long start = System.nanoTime(); filterByStream(bogies, threshold); return System.nanoTime() - start;
    }

    public static void main(String[] args) {

        // UC8
        System.out.println("===============================================");
        System.out.println(" UC8 - Filter Passenger Bogies Using Streams ");
        System.out.println("===============================================\n");
        List<Bogie> bogies8 = new ArrayList<>();
        bogies8.add(new Bogie("Sleeper", 72)); bogies8.add(new Bogie("AC Chair", 56));
        bogies8.add(new Bogie("First Class", 24)); bogies8.add(new Bogie("General", 90));
        for (Bogie b : bogies8) System.out.println(b.name + " -> " + b.capacity);
        List<Bogie> filtered = filterBogies(bogies8, 60);
        System.out.println("\nFiltered Bogies (Capacity > 60):");
        for (Bogie b : filtered) System.out.println(b.name + " -> " + b.capacity);
        System.out.println("\nUC8 filtering completed...");

        // UC9
        System.out.println("\n===============================================");
        System.out.println(" UC9 - Group Bogies by Type ");
        System.out.println("===============================================\n");
        List<Bogie> bogies9 = new ArrayList<>();
        bogies9.add(new Bogie("Sleeper", 72)); bogies9.add(new Bogie("AC Chair", 56));
        bogies9.add(new Bogie("First Class", 24)); bogies9.add(new Bogie("Sleeper", 70));
        bogies9.add(new Bogie("AC Chair", 60));
        Map<String, List<Bogie>> grouped = groupBogiesByType(bogies9);
        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println("Bogie Type: " + entry.getKey());
            for (Bogie b : entry.getValue()) System.out.println("  Capacity -> " + b.capacity);
        }
        System.out.println("\nUC9 grouping completed...");

        // UC10
        System.out.println("\n===============================================");
        System.out.println(" UC10 - Count Total Seats in Train ");
        System.out.println("===============================================\n");
        List<Bogie> bogies10 = new ArrayList<>();
        bogies10.add(new Bogie("Sleeper", 72)); bogies10.add(new Bogie("AC Chair", 56));
        bogies10.add(new Bogie("First Class", 24)); bogies10.add(new Bogie("Sleeper", 70));
        for (Bogie b : bogies10) System.out.println(b.name + " -> " + b.capacity);
        System.out.println("\nTotal Seating Capacity of Train: " + countTotalSeats(bogies10));
        System.out.println("\nUC10 aggregation completed...");

        // UC11
        System.out.println("\n===============================================");
        System.out.println(" UC11 - Validate Train ID and Cargo Code ");
        System.out.println("===============================================\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();
        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();
        System.out.println("Train ID Valid: " + validateTrainId(trainId));
        System.out.println("Cargo Code Valid: " + validateCargoCode(cargoCode));
        System.out.println("\nUC11 validation completed...");

        // UC12
        System.out.println("\n===============================================");
        System.out.println(" UC12 - Safety Compliance Check for Goods Bogies ");
        System.out.println("===============================================\n");
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));
        for (GoodsBogie b : goodsBogies) System.out.println(b.type + " -> " + b.cargo);
        boolean compliant = isSafetyCompliant(goodsBogies);
        System.out.println("\nSafety Compliance Status: " + compliant);
        System.out.println(compliant ? "Train formation is SAFE." : "Train formation is NOT SAFE.");
        System.out.println("\nUC12 safety validation completed...");

        // UC13
        System.out.println("\n===============================================");
        System.out.println(" UC13 - Performance Comparison (Loops vs Streams) ");
        System.out.println("===============================================\n");
        List<Bogie> bigDataset = new ArrayList<>();
        for (int i = 0; i < 100000; i++) bigDataset.add(new Bogie("Sleeper", 50 + (i % 50)));
        System.out.println("Loop Execution Time (ns): "   + measureLoopTime(bigDataset, 60));
        System.out.println("Stream Execution Time (ns): " + measureStreamTime(bigDataset, 60));
        System.out.println("\nUC13 performance benchmarking completed...");

        // UC14
        System.out.println("\n===============================================");
        System.out.println(" UC14 - Handle Invalid Bogie Capacity ");
        System.out.println("===============================================\n");
        try {
            PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
            System.out.println("Created Bogie: " + b1.type + " -> " + b1.capacity);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            PassengerBogie b2 = new PassengerBogie("AC Chair", 0);
            System.out.println("Created Bogie: " + b2.type + " -> " + b2.capacity);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nUC14 exception handling completed...");

        // UC15
        System.out.println("\n===============================================");
        System.out.println(" UC15 - Safe Cargo Assignment ");
        System.out.println("===============================================\n");
        SafeGoodsBogie cylindrical = new SafeGoodsBogie("Cylindrical");
        cylindrical.assignCargo("Petroleum");

        SafeGoodsBogie rectangular = new SafeGoodsBogie("Rectangular");
        rectangular.assignCargo("Petroleum");

        System.out.println("\nUC15 runtime handling completed...");
        scanner.close();
    }
}