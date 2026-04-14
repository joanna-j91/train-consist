package com.train;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UseCase11TrainConsistMgmnt {

    static final String TRAIN_ID_PATTERN  = "TRN-\\d{4}";
    static final String CARGO_CODE_PATTERN = "PET-[A-Z]{2}";

    public static boolean validateTrainId(String trainId) {
        Pattern pattern = Pattern.compile(TRAIN_ID_PATTERN);
        Matcher matcher = pattern.matcher(trainId);
        return matcher.matches();
    }

    public static boolean validateCargoCode(String cargoCode) {
        Pattern pattern = Pattern.compile(CARGO_CODE_PATTERN);
        Matcher matcher = pattern.matcher(cargoCode);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println(" UC11 - Validate Train ID and Cargo Code ");
        System.out.println("===============================================\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();

        // Validate
        boolean trainIdValid  = validateTrainId(trainId);
        boolean cargoCodeValid = validateCargoCode(cargoCode);

        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: " + trainIdValid);
        System.out.println("Cargo Code Valid: " + cargoCodeValid);

        System.out.println("\nUC11 validation completed...");
        scanner.close();
    }
}