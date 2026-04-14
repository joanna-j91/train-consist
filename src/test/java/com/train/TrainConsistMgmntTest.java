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

    // ---- UC8 Tests ----
    @Test void testFilter_CapacityGreaterThanThreshold() { assertEquals(2, TrainConsistMgmnt.filterBogies(bogies, 70).size()); }
    @Test void testFilter_CapacityEqualToThreshold()     { assertTrue(TrainConsistMgmnt.filterBogies(bogies, 72).stream().noneMatch(b -> b.capacity == 72)); }
    @Test void testFilter_CapacityLessThanThreshold()    { assertTrue(TrainConsistMgmnt.filterBogies(bogies, 70).stream().noneMatch(b -> b.capacity < 70)); }
    @Test void testFilter_MultipleBogiesMatching()       { assertTrue(TrainConsistMgmnt.filterBogies(bogies, 50).size() > 1); }
    @Test void testFilter_NoBogiesMatching()             { assertTrue(TrainConsistMgmnt.filterBogies(bogies, 100).isEmpty()); }
    @Test void testFilter_AllBogiesMatching()            { assertEquals(bogies.size(), TrainConsistMgmnt.filterBogies(bogies, 0).size()); }
    @Test void testFilter_EmptyBogieList()               { assertTrue(TrainConsistMgmnt.filterBogies(new ArrayList<>(), 60).isEmpty()); }
    @Test void testFilter_OriginalListUnchanged()        { int sz = bogies.size(); TrainConsistMgmnt.filterBogies(bogies, 60); assertEquals(sz, bogies.size()); }

    // ---- UC9 Tests ----
    @Test void testGrouping_BogiesGroupedByType() {
        List<TrainConsistMgmnt.Bogie> list = new ArrayList<>();
        list.add(new TrainConsistMgmnt.Bogie("Sleeper", 72)); list.add(new TrainConsistMgmnt.Bogie("AC Chair", 56));
        list.add(new TrainConsistMgmnt.Bogie("First Class", 24)); list.add(new TrainConsistMgmnt.Bogie("Sleeper", 70));
        assertTrue(TrainConsistMgmnt.groupBogiesByType(list).containsKey("Sleeper"));
    }
    @Test void testGrouping_MultipleBogiesInSameGroup() {
        List<TrainConsistMgmnt.Bogie> list = new ArrayList<>();
        list.add(new TrainConsistMgmnt.Bogie("Sleeper", 72)); list.add(new TrainConsistMgmnt.Bogie("Sleeper", 70));
        assertEquals(2, TrainConsistMgmnt.groupBogiesByType(list).get("Sleeper").size());
    }
    @Test void testGrouping_EmptyBogieList()        { assertTrue(TrainConsistMgmnt.groupBogiesByType(new ArrayList<>()).isEmpty()); }
    @Test void testGrouping_OriginalListUnchanged() { int sz = bogies.size(); TrainConsistMgmnt.groupBogiesByType(bogies); assertEquals(sz, bogies.size()); }

    // ---- UC10 Tests ----
    @Test void testReduce_TotalSeatCalculation() {
        List<TrainConsistMgmnt.Bogie> list = new ArrayList<>();
        list.add(new TrainConsistMgmnt.Bogie("Sleeper", 72)); list.add(new TrainConsistMgmnt.Bogie("AC Chair", 56));
        list.add(new TrainConsistMgmnt.Bogie("First Class", 24)); list.add(new TrainConsistMgmnt.Bogie("Sleeper", 70));
        assertEquals(222, TrainConsistMgmnt.countTotalSeats(list));
    }
    @Test void testReduce_EmptyBogieList()      { assertEquals(0, TrainConsistMgmnt.countTotalSeats(new ArrayList<>())); }
    @Test void testReduce_SingleBogieCapacity() {
        List<TrainConsistMgmnt.Bogie> list = new ArrayList<>(); list.add(new TrainConsistMgmnt.Bogie("Sleeper", 72));
        assertEquals(72, TrainConsistMgmnt.countTotalSeats(list));
    }

    // ---- UC11 Tests ----
    @Test void testRegex_ValidTrainID()                { assertTrue(TrainConsistMgmnt.validateTrainId("TRN-1234")); }
    @Test void testRegex_InvalidTrainIDFormat()        { assertFalse(TrainConsistMgmnt.validateTrainId("TRAIN12")); }
    @Test void testRegex_ValidCargoCode()              { assertTrue(TrainConsistMgmnt.validateCargoCode("PET-AB")); }
    @Test void testRegex_InvalidCargoCodeFormat()      { assertFalse(TrainConsistMgmnt.validateCargoCode("PET-ab")); }
    @Test void testRegex_TrainIDDigitLengthValidation(){ assertFalse(TrainConsistMgmnt.validateTrainId("TRN-123")); assertFalse(TrainConsistMgmnt.validateTrainId("TRN-12345")); }
    @Test void testRegex_EmptyInputHandling()          { assertFalse(TrainConsistMgmnt.validateTrainId("")); assertFalse(TrainConsistMgmnt.validateCargoCode("")); }

    // ---- UC12 Tests ----
    @Test void testSafety_AllBogiesValid() {
        List<TrainConsistMgmnt.GoodsBogie> list = new ArrayList<>();
        list.add(new TrainConsistMgmnt.GoodsBogie("Cylindrical", "Petroleum")); list.add(new TrainConsistMgmnt.GoodsBogie("Open", "Coal"));
        assertTrue(TrainConsistMgmnt.isSafetyCompliant(list));
    }
    @Test void testSafety_CylindricalWithInvalidCargo() {
        List<TrainConsistMgmnt.GoodsBogie> list = new ArrayList<>();
        list.add(new TrainConsistMgmnt.GoodsBogie("Cylindrical", "Coal"));
        assertFalse(TrainConsistMgmnt.isSafetyCompliant(list));
    }
    @Test void testSafety_EmptyBogieList() { assertTrue(TrainConsistMgmnt.isSafetyCompliant(new ArrayList<>())); }

    // ---- UC13 Tests ----
    @Test void testLoopFilteringLogic()      { assertTrue(TrainConsistMgmnt.filterByLoop(bogies, 60).stream().allMatch(b -> b.capacity > 60)); }
    @Test void testStreamFilteringLogic()    { assertTrue(TrainConsistMgmnt.filterByStream(bogies, 60).stream().allMatch(b -> b.capacity > 60)); }
    @Test void testLoopAndStreamResultsMatch() { assertEquals(TrainConsistMgmnt.filterByLoop(bogies, 60).size(), TrainConsistMgmnt.filterByStream(bogies, 60).size()); }
    @Test void testExecutionTimeMeasurement() { assertTrue(TrainConsistMgmnt.measureLoopTime(bogies, 60) > 0); assertTrue(TrainConsistMgmnt.measureStreamTime(bogies, 60) > 0); }

    // ---- UC14 Tests ----
    @Test void testException_ValidCapacityCreation() throws TrainConsistMgmnt.InvalidCapacityException {
        assertNotNull(new TrainConsistMgmnt.PassengerBogie("Sleeper", 72));
    }
    @Test void testException_NegativeCapacityThrowsException() {
        assertThrows(TrainConsistMgmnt.InvalidCapacityException.class, () -> new TrainConsistMgmnt.PassengerBogie("AC Chair", -10));
    }
    @Test void testException_ZeroCapacityThrowsException() {
        assertThrows(TrainConsistMgmnt.InvalidCapacityException.class, () -> new TrainConsistMgmnt.PassengerBogie("Sleeper", 0));
    }
    @Test void testException_ExceptionMessageValidation() {
        TrainConsistMgmnt.InvalidCapacityException ex = assertThrows(
                TrainConsistMgmnt.InvalidCapacityException.class,
                () -> new TrainConsistMgmnt.PassengerBogie("Sleeper", -5));
        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }
    @Test void testException_ObjectIntegrityAfterCreation() throws TrainConsistMgmnt.InvalidCapacityException {
        TrainConsistMgmnt.PassengerBogie bogie = new TrainConsistMgmnt.PassengerBogie("First Class", 24);
        assertEquals("First Class", bogie.type);
        assertEquals(24, bogie.capacity);
    }

    // ---- UC15 Tests ----
    @Test
    void testCargo_SafeAssignment() {
        TrainConsistMgmnt.SafeGoodsBogie bogie = new TrainConsistMgmnt.SafeGoodsBogie("Cylindrical");
        boolean result = bogie.assignCargo("Petroleum");
        assertTrue(result);
        assertEquals("Petroleum", bogie.cargo);
    }

    @Test
    void testCargo_UnsafeAssignmentHandled() {
        TrainConsistMgmnt.SafeGoodsBogie bogie = new TrainConsistMgmnt.SafeGoodsBogie("Rectangular");
        // Exception is caught inside assignCargo — should not propagate
        assertDoesNotThrow(() -> bogie.assignCargo("Petroleum"));
    }

    @Test
    void testCargo_CargoNotAssignedAfterFailure() {
        TrainConsistMgmnt.SafeGoodsBogie bogie = new TrainConsistMgmnt.SafeGoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");
        assertNull(bogie.cargo); // cargo must not have been stored
    }

    @Test
    void testCargo_ProgramContinuesAfterException() {
        TrainConsistMgmnt.SafeGoodsBogie b1 = new TrainConsistMgmnt.SafeGoodsBogie("Rectangular");
        TrainConsistMgmnt.SafeGoodsBogie b2 = new TrainConsistMgmnt.SafeGoodsBogie("Cylindrical");
        // Both calls must complete without crashing
        assertDoesNotThrow(() -> {
            b1.assignCargo("Petroleum");
            b2.assignCargo("Petroleum");
        });
        assertEquals("Petroleum", b2.cargo);
    }

    @Test
    void testCargo_FinallyBlockExecution() {
        TrainConsistMgmnt.SafeGoodsBogie bogie = new TrainConsistMgmnt.SafeGoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");
        assertTrue(bogie.finallyExecuted); // finally must always run
    }
}