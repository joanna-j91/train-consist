package com.train;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistMgmntTest {

    private List<TrainConsistMgmnt.Bogie> bogies;

    @BeforeEach
    void setUp() {
        bogies = new ArrayList<>();
        bogies.add(new TrainConsistMgmnt.Bogie("Sleeper",     72));
        bogies.add(new TrainConsistMgmnt.Bogie("AC Chair",    56));
        bogies.add(new TrainConsistMgmnt.Bogie("First Class", 24));
        bogies.add(new TrainConsistMgmnt.Bogie("General",     90));
    }

    @Test
    void testSort_BasicAlphabeticalSorting() {
        String[] result = TrainConsistMgmnt.sortBogieNames(new String[]{"Sleeper", "AC Chair", "First Class", "General", "Luxury"});
        assertArrayEquals(new String[]{"AC Chair", "First Class", "General", "Luxury", "Sleeper"}, result);
    }

    @Test
    void testSort_UnsortedInput() {
        String[] result = TrainConsistMgmnt.sortBogieNames(new String[]{"Luxury", "General", "Sleeper", "AC Chair"});
        assertArrayEquals(new String[]{"AC Chair", "General", "Luxury", "Sleeper"}, result);
    }

    @Test
    void testSort_AlreadySortedArray() {
        String[] result = TrainConsistMgmnt.sortBogieNames(new String[]{"AC Chair", "First Class", "General"});
        assertArrayEquals(new String[]{"AC Chair", "First Class", "General"}, result);
    }

    @Test
    void testSort_DuplicateBogieNames() {
        String[] result = TrainConsistMgmnt.sortBogieNames(new String[]{"Sleeper", "AC Chair", "Sleeper", "General"});
        assertArrayEquals(new String[]{"AC Chair", "General", "Sleeper", "Sleeper"}, result);
    }

    @Test
    void testSort_SingleElementArray() {
        String[] result = TrainConsistMgmnt.sortBogieNames(new String[]{"Sleeper"});
        assertArrayEquals(new String[]{"Sleeper"}, result);
    }
}