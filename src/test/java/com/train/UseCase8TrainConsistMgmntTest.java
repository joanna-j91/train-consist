package com.train;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase8TrainConsistMgmntTest {

    private List<UseCase8TrainConsistMgmnt.Bogie> bogies;

    @BeforeEach
    void setUp() {
        bogies = new ArrayList<>();
        bogies.add(new UseCase8TrainConsistMgmnt.Bogie("Sleeper",     72));
        bogies.add(new UseCase8TrainConsistMgmnt.Bogie("AC Chair",    56));
        bogies.add(new UseCase8TrainConsistMgmnt.Bogie("First Class", 24));
        bogies.add(new UseCase8TrainConsistMgmnt.Bogie("General",     90));
    }

    @Test
    public void testFilter_CapacityGreaterThanThreshold() {
        List<UseCase8TrainConsistMgmnt.Bogie> result = UseCase8TrainConsistMgmnt.filterBogies(bogies, 70);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(b -> b.capacity > 70));
    }

    @Test
    public void testFilter_CapacityEqualToThreshold() {
        // Equal to threshold should NOT be included (strictly greater than)
        List<UseCase8TrainConsistMgmnt.Bogie> result = UseCase8TrainConsistMgmnt.filterBogies(bogies, 72);
        assertTrue(result.stream().noneMatch(b -> b.capacity == 72));
    }

    @Test
    public void testFilter_CapacityLessThanThreshold() {
        List<UseCase8TrainConsistMgmnt.Bogie> result = UseCase8TrainConsistMgmnt.filterBogies(bogies, 70);
        assertTrue(result.stream().noneMatch(b -> b.capacity < 70));
    }

    @Test
    public void testFilter_MultipleBogiesMatching() {
        List<UseCase8TrainConsistMgmnt.Bogie> result = UseCase8TrainConsistMgmnt.filterBogies(bogies, 50);
        assertTrue(result.size() > 1);
    }

    @Test
    public void testFilter_NoBogiesMatching() {
        List<UseCase8TrainConsistMgmnt.Bogie> result = UseCase8TrainConsistMgmnt.filterBogies(bogies, 100);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFilter_AllBogiesMatching() {
        List<UseCase8TrainConsistMgmnt.Bogie> result = UseCase8TrainConsistMgmnt.filterBogies(bogies, 0);
        assertEquals(bogies.size(), result.size());
    }

    @Test
    public void testFilter_EmptyBogieList() {
        List<UseCase8TrainConsistMgmnt.Bogie> result =
                UseCase8TrainConsistMgmnt.filterBogies(new ArrayList<>(), 60);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFilter_OriginalListUnchanged() {
        int originalSize = bogies.size();
        UseCase8TrainConsistMgmnt.filterBogies(bogies, 60);
        assertEquals(originalSize, bogies.size());
    }
}