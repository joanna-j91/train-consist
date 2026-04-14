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
    void testSearch_BogieFound() {
        assertTrue(TrainConsistMgmnt.linearSearch(BOGIE_IDS, "BG309"));
    }

    @Test
    void testSearch_BogieNotFound() {
        assertFalse(TrainConsistMgmnt.linearSearch(BOGIE_IDS, "BG999"));
    }

    @Test
    void testSearch_FirstElementMatch() {
        assertTrue(TrainConsistMgmnt.linearSearch(BOGIE_IDS, "BG101"));
    }

    @Test
    void testSearch_LastElementMatch() {
        assertTrue(TrainConsistMgmnt.linearSearch(BOGIE_IDS, "BG550"));
    }

    @Test
    void testSearch_SingleElementArray() {
        assertTrue(TrainConsistMgmnt.linearSearch(new String[]{"BG101"}, "BG101"));
    }
}