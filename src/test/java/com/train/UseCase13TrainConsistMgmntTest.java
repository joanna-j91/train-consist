package com.train;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase13TrainConsistMgmntTest {

    private List<UseCase13TrainConsistMgmnt.Bogie> bogies;

    @BeforeEach
    void setUp() {
        bogies = new ArrayList<>();
        bogies.add(new UseCase13TrainConsistMgmnt.Bogie("Sleeper",     72));
        bogies.add(new UseCase13TrainConsistMgmnt.Bogie("AC Chair",    56));
        bogies.add(new UseCase13TrainConsistMgmnt.Bogie("First Class", 24));
        bogies.add(new UseCase13TrainConsistMgmnt.Bogie("General",     90));
        bogies.add(new UseCase13TrainConsistMgmnt.Bogie("Sleeper",     65));
    }

    @Test
    void testLoopFilteringLogic() {
        List<UseCase13TrainConsistMgmnt.Bogie> result =
                UseCase13TrainConsistMgmnt.filterByLoop(bogies, 60);
        assertTrue(result.stream().allMatch(b -> b.capacity > 60));
        assertTrue(result.stream().noneMatch(b -> b.capacity <= 60));
    }

    @Test
    void testStreamFilteringLogic() {
        List<UseCase13TrainConsistMgmnt.Bogie> result =
                UseCase13TrainConsistMgmnt.filterByStream(bogies, 60);
        assertTrue(result.stream().allMatch(b -> b.capacity > 60));
        assertTrue(result.stream().noneMatch(b -> b.capacity <= 60));
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<UseCase13TrainConsistMgmnt.Bogie> loopResult =
                UseCase13TrainConsistMgmnt.filterByLoop(bogies, 60);
        List<UseCase13TrainConsistMgmnt.Bogie> streamResult =
                UseCase13TrainConsistMgmnt.filterByStream(bogies, 60);
        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        long elapsed = UseCase13TrainConsistMgmnt.measureLoopTime(bogies, 60);
        assertTrue(elapsed > 0);

        long streamElapsed = UseCase13TrainConsistMgmnt.measureStreamTime(bogies, 60);
        assertTrue(streamElapsed > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<UseCase13TrainConsistMgmnt.Bogie> large = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            large.add(new UseCase13TrainConsistMgmnt.Bogie("Type", 50 + (i % 50)));
        }
        List<UseCase13TrainConsistMgmnt.Bogie> loopResult =
                UseCase13TrainConsistMgmnt.filterByLoop(large, 60);
        List<UseCase13TrainConsistMgmnt.Bogie> streamResult =
                UseCase13TrainConsistMgmnt.filterByStream(large, 60);
        assertEquals(loopResult.size(), streamResult.size());
        assertFalse(loopResult.isEmpty());
    }
}