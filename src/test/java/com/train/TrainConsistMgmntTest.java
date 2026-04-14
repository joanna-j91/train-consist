package com.train;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistMgmntTest {

    private List<TrainConsistMgmnt.Bogie> bogies;
    private static final String[] BOGIE_IDS = {"BG101", "BG205", "BG309", "BG412", "BG550"};

    @BeforeEach
    void setUp() {
        bogies = new ArrayList<>();
        bogies.add(new TrainConsistMgmnt.Bogie("Sleeper",     72));
        bogies.add(new TrainConsistMgmnt.Bogie("AC Chair",    56));
        bogies.add(new TrainConsistMgmnt.Bogie("First Class", 24));
        bogies.add(new TrainConsistMgmnt.Bogie("General",     90));
    }
    @Test
    void testBinarySearch_BogieFound() {
        assertTrue(TrainConsistMgmnt.binarySearch(BOGIE_IDS, "BG309"));
    }

    @Test
    void testBinarySearch_BogieNotFound() {
        assertFalse(TrainConsistMgmnt.binarySearch(BOGIE_IDS, "BG999"));
    }

    @Test
    void testBinarySearch_FirstElementMatch() {
        assertTrue(TrainConsistMgmnt.binarySearch(BOGIE_IDS, "BG101"));
    }

    @Test
    void testBinarySearch_LastElementMatch() {
        assertTrue(TrainConsistMgmnt.binarySearch(BOGIE_IDS, "BG550"));
    }

    @Test
    void testBinarySearch_SingleElementArray() {
        assertTrue(TrainConsistMgmnt.binarySearch(new String[]{"BG101"}, "BG101"));
    }

    @Test
    void testBinarySearch_EmptyArray() {
        assertFalse(TrainConsistMgmnt.binarySearch(new String[]{}, "BG101"));
    }

    @Test
    void testBinarySearch_UnsortedInputHandled() {
        assertTrue(TrainConsistMgmnt.binarySearch(
                new String[]{"BG309", "BG101", "BG550", "BG205", "BG412"}, "BG205"));
    }
}