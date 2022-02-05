package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range refRangeDecimal;
    private Range refRangeNoDecimal;
    private Range UBDecimal;
    private Range UBNoDecimal;
    
    // NOTE FROM BROOKE: I am currently researching as to whether Range is an 
    // inclusive range, only partially inclusive, or non-inclusive, as I am recieving
    // conflicting results from both these tests, and my own research. 
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	refRangeNoDecimal = new Range (-8.0, 16.0);
    	refRangeDecimal = new Range (-8.0, 16.000000001d);
    	UBDecimal = new Range(16.000000000d, 16.5);
    	UBNoDecimal = new Range(16.0, 16.5);
    	
    }


//    @Test
//    public void centralValueShouldBeZero() {
//        assertEquals("The central value of -1 and 1 should be 0",
//        0, negToPosRange.getCentralValue(), .000000001d);
//    }
    
    // BROOKE TESTS
    @Test public void brookeIntersectsDecimalUBDecimal() {
    	assertTrue("The range (16.000000000d, 16.5) intersects the range (-8.0, 16.000000001d)", refRangeDecimal.intersects(UBDecimal));
    }
    @Test public void brookeIntersectsDecimalUBNoDecimal() {
    	assertTrue("The range (16.0, 16.5) intersects the range (-8.0, 16.000000001d)", refRangeDecimal.intersects(UBNoDecimal));
    }
    @Test public void brookeIntersectsNoDecimalUBDecimal() {
    	assertTrue("The range (16.000000000d, 16.5) intersects the range from (-8.0, 16.0)", refRangeNoDecimal.intersects(UBDecimal));
    }
    @Test public void brookeIntersectsNoDecimalUBNoDecimal() {
    	assertTrue("The range (16.0, 16.5) intersects the range from (-8.0, 16.0)", refRangeNoDecimal.intersects(UBNoDecimal));
    }
    
    

//    @Test 
//    public void intersectsWithinBoundaryNotTouchingBoundary() {
//    	assertTrue("The range from -4.0 to 8.0 intersects the range from -8 to 16.", refRange.intersects(-4.0, 8.0));
//    }
//    
//    // Thinking about this. 
//    @Test
//    public void intersectsWithinBoundaryTouchingBoundary() {
//    	assertTrue("The range from -8.0 to 16.0 intersects the range from -8.0 to 16.0", refRange.intersects(-8.0, 16.0));
//    }
//    
//    @Test
//    public void intersectsAboveUpperBoundaryAndBelowLowerBoundaryNotTouchingBoundary() {
//    	assertTrue("The range from -28.0 to 36.0 intersects the range from -8.0 to 16.0", refRange.intersects(-8.0, 16.0));
//    }
//    
//    @Test
//    public void intersectsBelowLowerBoundaryAndWithinBoundary() {
//    	assertTrue("The range from -14.0 to 2.0 intersects the range from -8.0 to 16.0", refRange.intersects(-14.0, 2.0));
//    }
//    
//    // Thinking about this
//    @Test
//    public void intersectsOnlyTouchingLowerBoundary() {
//    	assertFalse("The range from -14 to -8 intersects the range from -8.0 to 16.0", refRange.intersects(-14.0, -8.0));
//    }
//    
//    @Test
//    public void intersectsFullyBelowLowerBoundaryNotTouchingBoundary() {
//    	assertFalse("The range from -43.0 to -16.0 does not intersect the range from -8.0 to 16.0", refRange.intersects(-43.0, -16.0));
//    }
//    
//    @Test
//    public void intersectsAboveUpperBoundaryAndWithinBoundary() {
//    	assertTrue("The range from 3.0 to 28.0 intersects the range from -8.0 to 16.0", refRange.intersects(3.0, 28.0));
//    }
//    
//    @Test
//    public void intersectsOnlyTouchingUpperBoundary() {
//    	assertFalse("The range from 16.0 to 32.0 intersects the range from -8.0 to 16.0", refRange.intersects(16.0, 32.0));
//    }
//    
//    @Test
//    public void intersectsFullyAboveUpperBoundaryNotTouchingBoundary() {
//    	assertFalse("The range from 17.0 to 19.0 does not intersect the range from -8.0 to 16.0", refRange.intersects(17.0, 19.0));
//    }
//    
//    @Test
//    public void containsBelowLowerBoundary() {
//    	assertFalse("The value -22. is not contained within the range from -8.0 to 16.0", refRange.contains(-22.0));
//    }
//    
//    @Test
//    public void containsOnLowerBoundary() {
//    	assertTrue("The value -8.0 is contained within the range from -8.0 to 16.0", refRange.contains(-8.0));
//    }
//
//    @Test
//    public void containsBetweenUpperAndLowerBoundary(){
//	    assertTrue("The value 0.0 is contained within the range from -8.0 to 16.0", refRange.contains(0.0));
//    }
//
//    @Test
//    public void containsOnUpperBoundary(){
//	    assertTrue("The value 16.0 is contained within the range from -8.0 to 16.0", refRange.contains(16.0));
//    }
//
//    @Test 
//    public void containsAboveUpperBoundary(){
//    	    assertFalse("The value 99.0 is not contained within the range from -8.0 to 16.0", refRange.contains(99.0));
//    }
  
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
