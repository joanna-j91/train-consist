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

    void testSearch_ThrowsExceptionWhenEmpty() {
        assertThrows(IllegalStateException.class,
                () -> TrainConsistMgmnt.searchWithValidation(new String[]{}, "BG101"));
    }

    @Test
    void testSearch_AllowsSearchWhenDataExists() {
        assertDoesNotThrow(() ->
                TrainConsistMgmnt.searchWithValidation(new String[]{"BG101", "BG205"}, "BG101"));
    }

    @Test
    void testSearch_BogieFoundAfterValidation() {
        assertTrue(TrainConsistMgmnt.searchWithValidation(
                new String[]{"BG101", "BG205", "BG309"}, "BG205"));
    }

    @Test
    void testSearch_BogieNotFoundAfterValidation() {
        assertFalse(TrainConsistMgmnt.searchWithValidation(
                new String[]{"BG101", "BG205", "BG309"}, "BG999"));
    }

    @Test
    void testSearch_SingleElementValidCase() {
        assertTrue(TrainConsistMgmnt.searchWithValidation(new String[]{"BG101"}, "BG101"));
    }
}