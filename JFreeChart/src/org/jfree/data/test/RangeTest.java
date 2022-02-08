package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range higherRange;
    private Range extraneousMaxRange;
    private Range extraneousMinRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
        higherRange = new Range(2,4);
        extraneousMaxRange = new Range(1, Double.MAX_VALUE);
        extraneousMinRange = new Range(-Double.MIN_VALUE, 1);
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
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
