package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
	private Range refRange;
	private Range testRange;
	private Range smallestRange;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		refRange = new Range(-8.0, 16.0);
		smallestRange = new Range(0.0, Double.MIN_VALUE);

	}

	@Test
	public void intersectsWithinBoundaryNotTouchingBoundaryDoubles() {
		assertTrue("The range from -4.0 to 8.0 intersects the range from -8 to 16.", refRange.intersects(-4.0, 8.0));
	}

	@Test
	public void intersectsWithinBoundaryTouchingBoundaryDoubles() {
		assertTrue("The range from -8.0 to 16.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(-8.0, 16.0));
	}

	@Test
	public void intersectsAboveUpperBoundaryAndBelowLowerBoundaryNotTouchingBoundaryDoubles() {
		assertTrue("The range from -28.0 to 36.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(-8.0, 16.0));
	}

	@Test
	public void intersectsBelowLowerBoundaryAndWithinBoundaryDoubles() {
		assertTrue("The range from -14.0 to 2.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(-14.0, 2.0));
	}

	// BUG: Should pass, because the test range overlaps with refRange at -8.0, but
	// the test fails instead
	@Test
	public void intersectsOnlyTouchingLowerBoundaryDoubles() {
		assertTrue("The range from -14 to -8 intersects the range from -8.0 to 16.0", refRange.intersects(-14.0, -8.0));
	}

	@Test
	public void intersectsFullyBelowLowerBoundaryNotTouchingBoundaryDoubles() {
		assertFalse("The range from -43.0 to -16.0 does not intersect the range from -8.0 to 16.0",
				refRange.intersects(-43.0, -16.0));
	}

	@Test
	public void intersectsAboveUpperBoundaryAndWithinBoundaryDoubles() {
		assertTrue("The range from 3.0 to 28.0 intersects the range from -8.0 to 16.0", refRange.intersects(3.0, 28.0));
	}

	// BUG: Should pass, because the test range overlaps with refRange at 16.0, but
	// the test fails instead
	@Test
	public void intersectsOnlyTouchingUpperBoundaryDoubles() {
		assertTrue("The range from 16.0 to 32.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(16.0, 32.0));
	}

	@Test
	public void intersectsFullyAboveUpperBoundaryNotTouchingBoundaryDoubles() {
		assertFalse("The range from 17.0 to 19.0 does not intersect the range from -8.0 to 16.0",
				refRange.intersects(17.0, 19.0));
	}

	@Test
	public void interesectsMinDoubleValToMaxDoubleValDoubles() {
		assertTrue(
				"The range from MIN_VALUE for double to MAX_VALUE for double intersects with the range from -8.0 to 16.0",
				refRange.intersects(Double.MIN_VALUE, Double.MAX_VALUE));
	}

	@Test
	public void intersectSmallestPossibleRangeDoubles() {
		assertTrue("The range from 0.0 to MIN_VALUE for double intersects with the range from -8.0 to 16.0",
				refRange.intersects(0.0, Double.MIN_VALUE));
	}

	@Test
	public void intersectsWithinBoundaryNotTouchingBoundaryRangeArg() {
		testRange = new Range(2.0, 5.0);
		assertTrue("The range from 2.0 to 5.0 intersects the range from -8 to 16.", refRange.intersects(testRange));
	}

	@Test
	public void intersectsWithinBoundaryTouchingBoundaryRangeArg() {
		testRange = new Range(-8.0, 16.0);
		assertTrue("The range from -8.0 to 16.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void intersectsAboveUpperBoundaryAndBelowLowerBoundaryNotTouchingBoundaryRangeArg() {
		testRange = new Range(-9.5, 17.5);
		assertTrue("The range from -9.5 to 17.5 intersects the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void intersectsBelowLowerBoundaryAndWithinBoundaryRangeArg() {
		testRange = new Range(-10.0, 14.2);
		assertTrue("The range from -10.0 to 14.2 intersects the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	// BUG: Should pass, because the test range overlaps with refRange at -8.0, but
	// the test fails instead
	@Test
	public void intersectsOnlyTouchingLowerBoundaryRangeArg() {
		testRange = new Range(-45.0, -8.0);
		assertTrue("The range from -45.0 to -8.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void intersectsFullyBelowLowerBoundaryNotTouchingBoundaryRangeArg() {
		testRange = new Range(-1004.0, -16.2);
		assertFalse("The range from -1004.0 to -16.2 does not intersect the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void intersectsAboveUpperBoundaryAndWithinBoundaryRangeArg() {
		testRange = new Range(0.03, 17.7);
		assertTrue("The range from 0.03 to 17.7 intersects the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	// BUG: Should pass, because the test range overlaps with refRange at 16.0, but
	// the test fails instead
	@Test
	public void intersectsOnlyTouchingUpperBoundaryRangeArg() {
		testRange = new Range(16.0, 23.0);
		assertTrue("The range from 16.0 to 23.0 intersects the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void intersectsFullyAboveUpperBoundaryNotTouchingBoundaryRangeArg() {
		testRange = new Range(200.75, 302.0);
		assertFalse("The range from 200.75 to 302.0 does not intersect the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void interesectsMinDoubleValToMaxDoubleValRangeArg() {
		testRange = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
		assertTrue(
				"The range from MIN_VALUE for double to MAX_VALUE for double intersects with the range from -8.0 to 16.0",
				refRange.intersects(testRange));
	}

	@Test
	public void intersectSmallestPossibleRangeArgs() {
		assertTrue("The range from 0.0 to MIN_VALUE for double intersects with the range from -8.0 to 16.0",
				refRange.intersects(smallestRange));
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
	public void containsBetweenUpperAndLowerBoundary() {
		assertTrue("The value 0.0 is contained within the range from -8.0 to 16.0", refRange.contains(0.0));
	}

	@Test
	public void containsOnUpperBoundary() {
		assertTrue("The value 16.0 is contained within the range from -8.0 to 16.0", refRange.contains(16.0));
	}

	@Test
	public void containsAboveUpperBoundary() {
		assertFalse("The value 99.0 is not contained within the range from -8.0 to 16.0", refRange.contains(99.0));
	}

	@Test
	public void containsMaxDoubleValue() {
		assertFalse("The maximum value of a double is not contained within the range from -8.0 to 16.0",
				refRange.contains(Double.MAX_VALUE));
	}

	@Test
	public void containsMinDoubleValue() {
		assertTrue("The minimum value of a double is contained within the range from -8.0 to 16.0",
				refRange.contains(Double.MIN_VALUE));
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
