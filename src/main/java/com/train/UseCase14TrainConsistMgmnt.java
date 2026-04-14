package com.train;

import java.util.ArrayList;
import java.util.List;

public class UseCase14TrainConsistMgmnt {

    // ---- CUSTOM EXCEPTION ----
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // Passenger Bogie model with validation
    static class PassengerBogie {
        String type;
        int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type     = type;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println(" UC14 - Handle Invalid Bogie Capacity ");
        System.out.println("===============================================\n");

        List<PassengerBogie> bogies = new ArrayList<>();

        // Attempt 1: valid capacity
        try {
            PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
            bogies.add(b1);
            System.out.println("Created Bogie: " + b1.type + " -> " + b1.capacity);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Attempt 2: invalid capacity (zero)
        try {
            PassengerBogie b2 = new PassengerBogie("AC Chair", 0);
            bogies.add(b2);
            System.out.println("Created Bogie: " + b2.type + " -> " + b2.capacity);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nUC14 exception handling completed...");
    }
}