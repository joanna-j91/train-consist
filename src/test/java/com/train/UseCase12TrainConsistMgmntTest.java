package com.train;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase12TrainConsistMgmntTest {

    @Test
    void testSafety_AllBogiesValid() {
        List<UseCase12TrainConsistMgmnt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Open",        "Coal"));
        assertTrue(UseCase12TrainConsistMgmnt.isSafetyCompliant(bogies));
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<UseCase12TrainConsistMgmnt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Cylindrical", "Coal"));
        assertFalse(UseCase12TrainConsistMgmnt.isSafetyCompliant(bogies));
    }

    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        List<UseCase12TrainConsistMgmnt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Open", "Coal"));
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Box",  "Grain"));
        assertTrue(UseCase12TrainConsistMgmnt.isSafetyCompliant(bogies));
    }

    @Test
    void testSafety_MixedBogiesWithViolation() {
        List<UseCase12TrainConsistMgmnt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Open",        "Coal"));
        bogies.add(new UseCase12TrainConsistMgmnt.GoodsBogie("Cylindrical", "Coal")); // violation
        assertFalse(UseCase12TrainConsistMgmnt.isSafetyCompliant(bogies));
    }

    @Test
    void testSafety_EmptyBogieList() {
        // allMatch on empty stream returns true (vacuous truth)
        assertTrue(UseCase12TrainConsistMgmnt.isSafetyCompliant(new ArrayList<>()));
    }
}