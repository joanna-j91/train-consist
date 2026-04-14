package com.train;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrainConsistMgmnt {


    static class Bogie {
        String name;
        int capacity;
        Bogie(String name, int capacity) { this.name = name; this.capacity = capacity; }
    }

    static class GoodsBogie {
        String type;
        String cargo;
        GoodsBogie(String type, String cargo) { this.type = type; this.cargo = cargo; }
    }

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) { super(message); }
    }

    static class PassengerBogie {
        String type;
        int capacity;
        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) throw new InvalidCapacityException("Capacity must be greater than zero");
            this.type = type; this.capacity = capacity;
        }
    }

    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) { super(message); }
    }

    static class SafeGoodsBogie {
        String shape;
        String cargo;
        boolean finallyExecuted = false;

        SafeGoodsBogie(String shape) { this.shape = shape; }

        boolean assignCargo(String cargo) {
            try {
                if (this.shape.equals("Rectangular") && cargo.equals("Petroleum"))
                    throw new CargoSafetyException("Unsafe cargo assignment!");
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

    public static List<Bogie> filterBogies(List<Bogie> bogies, int threshold) {
        return bogies.stream().filter(b -> b.capacity > threshold).collect(Collectors.toList());
    }

    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream().collect(Collectors.groupingBy(b -> b.name));
    }

    public static int countTotalSeats(List<Bogie> bogies) {
        return bogies.stream().map(b -> b.capacity).reduce(0, Integer::sum);
    }

    static final String TRAIN_ID_PATTERN   = "TRN-\\d{4}";
    static final String CARGO_CODE_PATTERN = "PET-[A-Z]{2}";

    public static boolean validateTrainId(String trainId) {
        return Pattern.compile(TRAIN_ID_PATTERN).matcher(trainId).matches();
    }

    public static boolean validateCargoCode(String cargoCode) {
        return Pattern.compile(CARGO_CODE_PATTERN).matcher(cargoCode).matches();
    }

    public static boolean isSafetyCompliant(List<GoodsBogie> goodsBogies) {
        return goodsBogies.stream().allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));
    }

    public static List<Bogie> filterByLoop(List<Bogie> bogies, int threshold) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) { if (b.capacity > threshold) result.add(b); }
        return result;
    }
    public static List<Bogie> filterByStream(List<Bogie> bogies, int threshold) {
        return bogies.stream().filter(b -> b.capacity > threshold).collect(Collectors.toList());
    }
    public static long measureLoopTime(List<Bogie> bogies, int threshold) {
        long s = System.nanoTime(); filterByLoop(bogies, threshold); return System.nanoTime() - s;
    }
    public static long measureStreamTime(List<Bogie> bogies, int threshold) {
        long s = System.nanoTime(); filterByStream(bogies, threshold); return System.nanoTime() - s;
    }

    //uc 16 Bubble Sort
    public static int[] bubbleSort(int[] capacities) {
        int[] arr = Arrays.copyOf(capacities, capacities.length);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp  = arr[j];
                    arr[j]    = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {

        System.out.println("\n===============================================");
        System.out.println(" UC16 - Manual Sorting using Bubble Sort ");
        System.out.println("===============================================\n");
        int[] capacities = {72, 56, 24, 70, 60};
        System.out.println("Original Capacities:");
        for (int c : capacities) System.out.print(c + " ");
        int[] sorted = bubbleSort(capacities);
        System.out.println("\n\nSorted Capacities (Ascending):");
        for (int c : sorted) System.out.print(c + " ");
        System.out.println("\n\nUC16 sorting completed...");


    }
}