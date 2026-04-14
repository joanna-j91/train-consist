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
    void testSort_BasicSorting() {
        int[] result = TrainConsistMgmnt.bubbleSort(new int[]{72, 56, 24, 70, 60});
        assertArrayEquals(new int[]{24, 56, 60, 70, 72}, result);
    }

    @Test
    void testSort_AlreadySortedArray() {
        int[] result = TrainConsistMgmnt.bubbleSort(new int[]{24, 56, 60, 70, 72});
        assertArrayEquals(new int[]{24, 56, 60, 70, 72}, result);
    }

    @Test
    void testSort_DuplicateValues() {
        int[] result = TrainConsistMgmnt.bubbleSort(new int[]{72, 56, 56, 24});
        assertArrayEquals(new int[]{24, 56, 56, 72}, result);
    }

    @Test
    void testSort_SingleElementArray() {
        int[] result = TrainConsistMgmnt.bubbleSort(new int[]{50});
        assertArrayEquals(new int[]{50}, result);
    }

    @Test
    void testSort_AllEqualValues() {
        int[] result = TrainConsistMgmnt.bubbleSort(new int[]{40, 40, 40});
        assertArrayEquals(new int[]{40, 40, 40}, result);
    }
}