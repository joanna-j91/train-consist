package com.train;

import java.util.ArrayList;
import java.util.List;

public class UseCase12TrainConsistMgmnt {

    // Goods Bogie model
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type  = type;
            this.cargo = cargo;
        }
    }

    // Safety rule: Cylindrical bogies must carry only Petroleum
    public static boolean isSafetyCompliant(List<GoodsBogie> goodsBogies) {
        return goodsBogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));
    }

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println(" UC12 - Safety Compliance Check for Goods Bogies ");
        System.out.println("===============================================\n");

        // Create goods bogie list
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open",        "Coal"));
        goodsBogies.add(new GoodsBogie("Box",         "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal")); // violates rule

        System.out.println("Goods Bogies in Train:");
        for (GoodsBogie b : goodsBogies) {
            System.out.println(b.type + " -> " + b.cargo);
        }

        boolean compliant = isSafetyCompliant(goodsBogies);

        System.out.println("\nSafety Compliance Status: " + compliant);
        System.out.println(compliant ? "Train formation is SAFE." : "Train formation is NOT SAFE.");

        System.out.println("\nUC12 safety validation completed...");
    }
}