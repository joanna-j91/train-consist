package com.train;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase14TrainConsistMgmntTest {

    @Test
    void testException_ValidCapacityCreation() throws UseCase14TrainConsistMgmnt.InvalidCapacityException {
        UseCase14TrainConsistMgmnt.PassengerBogie bogie =
                new UseCase14TrainConsistMgmnt.PassengerBogie("Sleeper", 72);
        assertNotNull(bogie);
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        assertThrows(
                UseCase14TrainConsistMgmnt.InvalidCapacityException.class,
                () -> new UseCase14TrainConsistMgmnt.PassengerBogie("AC Chair", -10)
        );
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(
                UseCase14TrainConsistMgmnt.InvalidCapacityException.class,
                () -> new UseCase14TrainConsistMgmnt.PassengerBogie("Sleeper", 0)
        );
    }

    @Test
    void testException_ExceptionMessageValidation() {
        UseCase14TrainConsistMgmnt.InvalidCapacityException ex =
                assertThrows(
                        UseCase14TrainConsistMgmnt.InvalidCapacityException.class,
                        () -> new UseCase14TrainConsistMgmnt.PassengerBogie("Sleeper", -5)
                );
        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws UseCase14TrainConsistMgmnt.InvalidCapacityException {
        UseCase14TrainConsistMgmnt.PassengerBogie bogie =
                new UseCase14TrainConsistMgmnt.PassengerBogie("First Class", 24);
        assertEquals("First Class", bogie.type);
        assertEquals(24, bogie.capacity);
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws UseCase14TrainConsistMgmnt.InvalidCapacityException {
        UseCase14TrainConsistMgmnt.PassengerBogie b1 =
                new UseCase14TrainConsistMgmnt.PassengerBogie("Sleeper", 72);
        UseCase14TrainConsistMgmnt.PassengerBogie b2 =
                new UseCase14TrainConsistMgmnt.PassengerBogie("AC Chair", 56);
        UseCase14TrainConsistMgmnt.PassengerBogie b3 =
                new UseCase14TrainConsistMgmnt.PassengerBogie("First Class", 24);
        assertNotNull(b1);
        assertNotNull(b2);
        assertNotNull(b3);
    }
}