package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range higherRange;
    private Range extraneousMaxRange;
    private Range extraneousMinRange;
    private Range refRange;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
        higherRange = new Range(2,4);
        extraneousMaxRange = new Range(1, Double.MAX_VALUE);
        extraneousMinRange = new Range(-Double.MIN_VALUE, 1);
    	refRange = new Range (-8.0, 16.0);
  
    }

    @Test
    public void expandToIncludeValidRangeAboveRange() {
        Range testRange = Range.expandToInclude(exampleRange, 2);
        assertEquals("The upper bound should be 2", 2, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void expandToIncludeValidRangeBelowRange() {
        Range testRange = Range.expandToInclude(exampleRange, -2);
        assertEquals("The upper bound should be 1", 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -2", -2, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void expandToIncludeValidRangeInRange() {
        Range testRange = Range.expandToInclude(exampleRange, 0);
        assertEquals("The upper bound should be 1", 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void expandToIncludeValidRangeUpperBound() {
        Range testRange = Range.expandToInclude(exampleRange, 1);
        assertEquals("The upper bound should be 1", 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void expandToIncludeValidRangeLowerBound() {
        Range testRange = Range.expandToInclude(exampleRange, -1);
        assertEquals("The upper bound should be 1", 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void expandToIncludeNullRangeAboveRange() {
        Range testRange = Range.expandToInclude(null, 2);
        assertEquals("The upper bound should be 2", 2, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be 2", 2, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void expandToIncludeValidRangeLargeExtraneousValue() {
        Range testRange = Range.expandToInclude(exampleRange, Double.MAX_VALUE);
        assertEquals("The upper bound of the range should be " + Double.toString(Double.MAX_VALUE), Double.MAX_VALUE, testRange.getUpperBound(), .000000001d);
    }

    @Test
    public void shiftValidRangeGreaterThanZero() {
        Range testRange = Range.shift(higherRange, 2);
        assertEquals("The upper bound should be 6", 6, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be 4", 4, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void shiftValidRangeEqualsZero() {
        Range testRange = Range.shift(exampleRange, 0);
        assertEquals("The upper bound should be 1", 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void shiftValidRangeLessThanZero() {
        Range testRange = Range.shift(higherRange, -1);
        assertEquals("The upper bound should be 3", 3, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be 1", 1, testRange.getLowerBound(), .000000001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shiftNullRangeLessThanZero() {
        Range testRange = Range.shift(null, -2);
    }

//    @Test
//    public void centralValueShouldBeZero() {
//        assertEquals("The central value of -1 and 1 should be 0",
//        0, negToPosRange.getCentralValue(), .000000001d);
//    }

    @Test 
    public void intersectsWithinBoundaryNotTouchingBoundary() {
    	assertTrue("The range from -4.0 to 8.0 intersects the range from -8 to 16.", refRange.intersects(-4.0, 8.0));
    }
    
    @Test
    public void shiftValidRangeMinNegativeDoubleValue() {
        Range testRange = Range.shift(exampleRange, -1 * Double.MIN_VALUE);
        assertEquals("The upper bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void shiftValidRangeMaxDoubleValue() {
        Range testRange = Range.shift(exampleRange, Double.MAX_VALUE);
        assertEquals("The upper bound should be " + Double.toString(Double.MAX_VALUE + 1), Double.MAX_VALUE + 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be 0", 0, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void shiftValidRangeCrossesZero() {
        Range testRange = Range.shift(exampleRange, 2);
        assertEquals("The upper bound should be 3", 3, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be 0", 0, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void scaleValidRangeGreaterThanOne() {
        Range testRange = Range.scale(exampleRange, 2);
        assertEquals("The upper bound should be 2", 2, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -2", -2, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void scaleValidRangeEqualsOne() {
        Range testRange = Range.scale(exampleRange, 1);
        assertEquals("The upper bound should be 1", 1, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -1", -1, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void scaleValidRangeLessThanOne() {
        Range testRange = Range.scale(exampleRange, 0.5);
        assertEquals("The upper bound should be 0.5", 0.5, testRange.getUpperBound(), .000000001d);
        assertEquals("The lower bound should be -0.5", -0.5, testRange.getLowerBound(), .000000001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void scaleValidRangeLessThanZero() {
        Range testRange = Range.scale(exampleRange, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void scaleNullRangeLessThanOne() {
        Range testRange = Range.scale(null, 2);
    }

    @Test
    public void scaleExtraneousMaxRangeGreaterThanOne() {
        Range testRange = Range.scale(extraneousMaxRange, 2);
        assertTrue("The upper bound of the range should be infinity", Double.isInfinite(testRange.getUpperBound()));
    }

    @Test
    public void scaleExtraneousMinRangeLessThanOne() {
        Range testRange = Range.scale(extraneousMinRange, 0.5);
        assertEquals("The lower bound of the range should be " + Double.toString(Double.MIN_VALUE / 2), Double.MIN_VALUE / 2, testRange.getLowerBound(), .000000001d);
    }

    @Test
    public void scaleValidRangeByMaxDouble() {
        Range testRange = Range.scale(exampleRange, Double.MAX_VALUE);
        assertEquals("The upper bound of the range should be " + Double.toString(Double.MAX_VALUE), Double.MAX_VALUE, testRange.getUpperBound(), .000000001d);
    }

    @Test
    public void scaleValidRangeByMinDouble() {
        Range testRange = Range.scale(exampleRange, Double.MIN_VALUE);
        assertEquals("The upper bound of the range should be " + Double.toString(Double.MIN_VALUE), Double.MIN_VALUE, testRange.getUpperBound(), .000000001d);
    }
    
    public void intersectsWithinBoundaryTouchingBoundary() {
    	assertTrue("The range from -8.0 to 16.0 intersects the range from -8.0 to 16.0", refRange.intersects(-8.0, 16.0));
    }
    
    @Test
    public void intersectsAboveUpperBoundaryAndBelowLowerBoundaryNotTouchingBoundary() {
    	assertTrue("The range from -28.0 to 36.0 intersects the range from -8.0 to 16.0", refRange.intersects(-8.0, 16.0));
    }
    
    @Test
    public void intersectsBelowLowerBoundaryAndWithinBoundary() {
    	assertTrue("The range from -14.0 to 2.0 intersects the range from -8.0 to 16.0", refRange.intersects(-14.0, 2.0));
    }
    
    
    // Test fails, despite overlapping with refRange at value -8.0
    @Test
    public void intersectsOnlyTouchingLowerBoundary() {
    	assertTrue("The range from -14 to -8 intersects the range from -8.0 to 16.0", refRange.intersects(-14.0, -8.0));
    }
    
    @Test
    public void intersectsFullyBelowLowerBoundaryNotTouchingBoundary() {
    	assertFalse("The range from -43.0 to -16.0 does not intersect the range from -8.0 to 16.0", refRange.intersects(-43.0, -16.0));
    }
    
    @Test
    public void intersectsAboveUpperBoundaryAndWithinBoundary() {
    	assertTrue("The range from 3.0 to 28.0 intersects the range from -8.0 to 16.0", refRange.intersects(3.0, 28.0));
    }
    
    // Test fails, despite overlapping with refRange at value 16.0
    @Test
    public void intersectsOnlyTouchingUpperBoundary() {
    	assertTrue("The range from 16.0 to 32.0 intersects the range from -8.0 to 16.0", refRange.intersects(16.0, 32.0));
    }
    
    @Test
    public void intersectsFullyAboveUpperBoundaryNotTouchingBoundary() {
    	assertFalse("The range from 17.0 to 19.0 does not intersect the range from -8.0 to 16.0", refRange.intersects(17.0, 19.0));
    }
    
    @Test
    public void containsBelowLowerBoundary() {
    	assertFalse("The value -22. is not contained within the range from -8.0 to 16.0", refRange.contains(-22.0));
    }
    
    @Test
    public void containsOnLowerBoundary() {
    	assertTrue("The value -8.0 is contained within the range from -8.0 to 16.0", refRange.contains(-8.0));
    }

    @Test
    public void containsBetweenUpperAndLowerBoundary(){
	    assertTrue("The value 0.0 is contained within the range from -8.0 to 16.0", refRange.contains(0.0));
    }

    @Test
    public void containsOnUpperBoundary(){
	    assertTrue("The value 16.0 is contained within the range from -8.0 to 16.0", refRange.contains(16.0));
    }

    @Test 
    public void containsAboveUpperBoundary(){
    	    assertFalse("The value 99.0 is not contained within the range from -8.0 to 16.0", refRange.contains(99.0));
    }
  
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
