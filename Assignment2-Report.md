# Assignment 2

**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

# **1 Introduction**

Black Box testing was utilized to conduct the tests for each method and various cases within each method. The two classes examined were `Range` and `DataUtilities`, with five methods selected from each to be tested in each of these methods. In each method, various boundary cases were discovered. We split each partition in the equivalence testing to either “Legal Inputs” or “Unexpected Inputs.” Inputs that were anticipated to be provided under normal use the method were considered legal, while unexpected inputs, the remaining tests not considered legal, tested the system’s ability to respond to extreme and out-of-boundary cases.

The use of mocking within this lab allowed us to test methods that referenced other methods that were not being evaluated in our planned test cases. This was primarily prevalent in the `DataUtitlities` class, where class methods frequently referenced other classes and methods outside the domain of this test suite. As a result, mocking was used to mimic the functionality of those methods and classes not being examined in this lab, but were required for the functionality of the methods that were being tested. Specifically, for `calculateColumnTotal` and `calculateRowTotal` mocking was necessary as `Value2d` is an interface class and needed to be used as in input to adequately test the methods. Since an instantiation of this class is not possible, mocking was required to ensure the test can be conducted and only the function of the methods in the test suite were being evaluated, not that of `Value2d`.

Mocking however, does have its limitation. For a number of test cases (`calculateColumnTotal_TooLargeColumn(), calculateColumnTotal_RowsBelowZero(), calculateColumnTotal_TooLargeColumn()`) mocking did not work as expected as those cases were looking for exceptions. As a result, we could not create expectations for the mocks to find necessary exceptions needed to ensure proper operation of the methods. Mocking in this case was not effective and instead, testing without the assistance of mocking was used.

# **2 Detailed description of unit test strategy**

_Below are the list of methods with their respective partitions. Partitions have been organized into legal inputs and unexpected inputs to test as many boundary cases as possible._

## `**Range.expandToInclude(Range r, double d)**`

**Legal Inputs (E):**

1. A valid range, and a value less than 0
2. A valid range, and a value equal to 0
3. A valid range, and a value greater than 0

**Unexpected Inputs (U):**

1. A null range, and a value less than 0
2. A null range, and a value equal to 0
3. A null range, and a value greater than 0
4. A valid range and a value that is the largest positive double
5. A valid range and a value that is the largest negative double
6. A valid range and a value that is the smallest positive double
7. A valid range and a value that is the smallest negative double

## `Range.shift(Range r, double d)`

**Legal Inputs (E):**

1. A valid range, and a value less than 1
2. A valid range, and a value equal to 1
3. A valid range, and a value greater than 1
4. A valid range, and a value less than 0
5. A valid range and a value that is the largest positive double
6. A valid range and a value that is the smallest positive double

**Unexpected Inputs (U):**

1. A range with an extraneous maximum value, and a value less than 1
2. A range with an extraneous maximum value, and a value equal to 1
3. A range with an extraneous maximum value, and a value greater than 1
4. A range with an extraneous maximum value, and a value less than 0
5. A range with an extraneous minimum value, and a value less than 1
6. A range with an extraneous minimum value, and a value equal to 1
7. A range with an extraneous minimum value, and a value greater than 1
8. A range with an extraneous minimum value, and a value less than 0
9. A null range, and a value less than 1
10. A null range, and a value equal to 1
11. A null range, and a value greater than 1

## `Range.scale(Range r, double d)`

**Legal Inputs (E):**

1. A valid range, and a value less than 0
2. A valid range, and a value equal to 0
3. A valid range, and a value greater than 0

**Unexpected Inputs (U):**

1. A null range, and a value less than 0
2. A null range, and a value equal to 0
3. A null range, and a value greater than 0
4. A valid range and a value that is the largest positive double
5. A valid range and a value that is the largest negative double
6. A valid range and a value that is the smallest positive double
7. A valid range and a value that is the smallest negative double

## `Range.intersects(double a, double b)` `Range.intersects(Range r)`

**Legal Inputs (E):**

1. Intersects the upper boundary
2. Intersects the lower boundary
3. Intersects between the upper and lower boundaries

**Unexpected Inputs (U):**

1. Intersects above the upper boundary
2. Intersects below the lower boundary

## `Range.contains(double d)`

**Legal Inputs (E):**

1. Below lower boundary
2. On lower boundary

**Unexpected Inputs (U):**

1. Between lower and upper boundary
2. On upper boundary
3. Above upper boundary

## `DataUtilities.createNumberArray2D(double[][] data)`

**Legal Inputs (E):**

1. A valid 2D double array

**Unexpected Inputs (U):**

1. An invalid 2D array

## `DataUtilities.calculateRowTotal(Values2D data, int row)`

**Legal Inputs (E):**

1. The given row value is greater than zero and less than the number of rows in the data Table (between upper and lower boundary).
2. The given row value is equal to zero (lower boundary).
3. The given row value is equal to the number of rows in the data table (upper boundary).

**Unexpected Inputs (U):**

1. An invalid data table. (empty table)
2. A valid data table with given row values less than zero.
3. A valid data table with a given row value greater than the number of rows in the data table.

## `DataUtilities.getCumulativePercentages(KeyedValues data)`

**Legal Inputs (E):**

1. An instance of KeyedValues that contains data values greater than zero.
2. An instance of KeyedValues that contain data values that are very large values (16-bit and 32-bit integers).

**Unexpected Inputs (U):**

1. An instance of KeyedValues that contains data values that are only zeros.
2. An instance of KeyedValues that contains data values that are null.

## `DataUtilities.createNumberArray(double[] data)`

**Legal Inputs (E):**

1. Non-Null Data Array

**Unexpected Outputs (U):**

1. Null Data Array

## `DataUtilities.calculateColumnTotal(Values2D data, int column, int[] validRows)`

**Legal Inputs (E):**

1. A given column number > 0 (lower boundary)
2. A given column number = 0 (on boundary)
3. A given column number = total columns in the the valid table - 1 (on boundary)
4. A given column number < the total columns in the valid table (upper boundary)
5. A ValidRows list with all values < the total rows in the valid table (upper boundary)
6. A ValidRows list with all values = the total rows in the valid table (on boundary)
7. A ValidRows list with all values > 0 (lower boundary)

**Unexpected Inputs(U):**

1. An invalid table
2. A given column number < 0
3. A given column number > the total columns in the valid table
4. A ValidRows list with a value < 0
5. A ValidRows list with a value > the total rows in the valid table

# **3 Test cases developed**

## `**Range.expandToInclude(Range r, double d)**`

The `expandToInclude()` function expects both a range and a valid double to be included in the range. In this case a null range is accepted since the single double value would just become the range. The partitions for the range are either a valid range, a range with an extraneous maximum value, a range with an extraneous minimum value, and a null range. Since the Range is it’s own class, testing for an invalid range would be implemented in the constructor. The partitions for the double include values greater than 1, less than 1, equal to 1, less than 0, and values with the largest and smallest possible doubles.

**Test Cases:**

1. `expandToIncludeValidRangeAboveRange()`
   - Expand to a value within the upper boundary
2. `expandToIncludeValidRangeBelowRange()`
   - Expand to a value within the lower boundary
3. `expandToIncludeValidRangeInRange()`
   - Expand to a value within the current range
4. `expandToIncludeValidRangeUpperBound()`
   - Expand to include a value on the current upper boundary (transition)
5. `expandToIncludeValidRangeLowerBound()`
   - Expand to include a value on the current lower boundary (transition)
6. `expandToIncludeNullRangeAboveRange()`
   - Expand a null range to include a valid value (create new rande)
7. `expandToIncludeValidRangeLargeExtraneousValue()`
   - Expand to include an extraneous maximum double value

## `Range.shift(Range r, double d)`

The `shift()` function expects both a valid range and a valid double to be included in the range. The partitions for the range are either a valid range, a range with an extraneous maximum value, a range with an extraneous minimum value, and a null range. Since the Range is it’s own class, testing for an invalid range would be implemented in the constructor. The partitions for the double include values greater than 1, less than 1, equal to 1, less than 0, and values with the largest and smallest possible doubles.

**Test Cases:**

1. `shiftValidRangeGreaterThanZero()`
   - Shift right an amount within the upper and lower bound
2. `shiftValidRangeEqualsZero()`
   - Don’t shift the range (keep constant)
3. `shiftValidRangeLessThanZero()`
   - Shift left an amount within the upper and lower bound
4. `shiftNullRangeLessThanZero()`
   - Invalid range argument
5. `shiftValidRangeMinNegativeDoubleValue()`
   - Shift left extreme minimum double value
6. `shiftValidRangeMaxDoubleValue()`
   - Shift right extreme maximum double value
7. `shiftValidRangeCrossesZero()`
   - Check that a shift doesn’t cross the zero bound

## `Range.scale(Range r, double d)`

The `scale()` function ex

**Test Cases:**

1. `scaleValidRangeGreaterThanOne()`
   - Increase the scaling of the range, within upper and lower bound
2. `scaleValidRangeEqualsOne()`
3. Keep the scaling of the range the same
4. `scaleValidRangeLessThanOne()`
   - Reduce the scaling of the range, within upper and lower bound
5. `scaleValidRangeLessThanZero()`
   - Invalid scaling factor argument
6. `scaleNullRangeLessThanOne()`
   - Invalid range argument
7. `scaleExtraneousMaxRangeGreaterThanOne()`
   - Scale range with extreme maximum bound
8. `scaleExtraneousMinRangeLessThanOne()`
   - Scale range with extreme minimum bound
9. `scaleValidRangeByMaxDouble()`
   - Extreme maximum double
10. `scaleValidRangeByMinDouble()`
    - Extreme minimum double value

## `Range.intersects(double b0, double b1)`

The method `intersects()` is intended to evaluate whether two ranges intersect. If the two ranges being tested, both the range in scope of the method and the range provided as an argument, overlap at any point `intersects()` will return `true`. Otherwise, it will return `false`.

**Test Cases**

1. `intersectsWithinBoundaryNotTouchingBoundaryDoubles()`
   1. Between lower and upper boundary
2. `intersectsWithinBoundaryTouchingBoundaryDoubles()`
   1. On boundary
3. `intersectsAboveUpperBoundaryAndBelowLowerBoundaryNotTouchingBoundaryDoubles()`
   1. Above boundary and below boundary
4. `intersectsBelowLowerBoundaryAndWithinBoundaryDoubles()`
   1. Intersect with lower boundary, within the boundary
5. `intersectsOnlyTouchingLowerBoundaryDoubles()`
   1. Intersects with Lower boundary
   2. Bug was caught with this test case, where despite there being a point at the boundary where an intersection between the targeted instance of a range object and the provided arguments, the method returned `false` instead of the expected output, `true`.
6. `intersectsFullyBelowLowerBoundaryNotTouchingBoundaryDoubles()`
   1. Below lower boundary, not touching boundary
7. `intersectsAboveUpperBoundaryAndWithinBoundaryDoubles()`
   1. Intersect with above boundary
8. `intersectsOnlyTouchingUpperBoundaryDoubles()`
   1. Above boundary while intersecting
   2. Bug was caught with this test case, where despite there being a point at the boundary where an intersection between the targeted instance of a range object and the provided arguments, the method returned `false` instead of the expected output, `true`.
9. `intersectsFullyAboveUpperBoundaryNotTouchingBoundaryDoubles()`
   1. Above boundary without intersecting boundary
10. `interesectsMinDoubleValToMaxDoubleValDoubles()`
    1. Extreme Values with Double
11. `intersectSmallestPossibleRangeDoubles()`
    1. Small Range
12. `intersectsWithinBoundaryNotTouchingBoundaryRangeArg()`
    1. Inside Boundary, not touching boundary. Overloaded Method.
13. `intersectsWithinBoundaryTouchingBoundaryRangeArg()`
    1. Inside Boundary, touching boundary. Overloaded Method.
14. `intersectsAboveUpperBoundaryAndBelowLowerBoundaryNotTouchingBoundaryRangeArg()`
    1. Above boundary and below boundary, not touching. Overloaded Method.
15. `intersectsBelowLowerBoundaryAndWithinBoundaryRangeArg()`
    1. Below Lower boundary, inside boundary. Overloaded Method.
16. `intersectsOnlyTouchingLowerBoundaryRangeArg()`
    1. Intersects with lower boundary. Overloaded Method.
    2. Bug was caught with this test case, where despite there being a point at the boundary where an intersection between the targeted instance of a range object and the provided argument(s), the method returned `false` instead of the expected output, `true`.
17. `intersectsFullyBelowLowerBoundaryNotTouchingBoundaryRangeArg()`
    1. Below lower boundary. Overloaded Method.
18. `intersectsAboveUpperBoundaryAndWithinBoundaryRangeArg()`
    1. Near above boundary. Overloaded Method.
19. `intersectsOnlyTouchingUpperBoundaryRangeArg()`
    1. Touching upper boundary. Overloaded Method.
    2. Bug was caught with this test case, where despite there being a point at the boundary where an intersection between the targeted instance of a range object and the provided argument, the method returned `false` instead of the expected output, `true`.
20. `intersectsFullyAboveUpperBoundaryNotTouchingBoundaryRangeArg()`
    1. Above boundary. Overloaded Method.
21. `interesectsMinDoubleValToMaxDoubleValRangeArg()`
    1. Extreme Values. Overloaded Method.
22. `intersectSmallestPossibleRangeArgs()`
    1. Small Range. Overloaded Method.

## `Range.contains(double d)`

The method `contains()` determines whether the provided double value is contained within the range it is being test against, and returns a boolean value reflecting such. If the provided double `d` is contained within the range, the method will return `true`. Otherwise, it will return `false`.

**Test Cases**

1. `containsBelowLowerBoundary()`
   1. Below Lower Boundary
2. `containsOnLowerBoundary()`
   1. On Boundary
3. `containsBetweenUpperAndLowerBoundary()`
   1. Within Upper and Lower Boundary
4. `containsOnUpperBoundary()`
   1. On Above Boundary
5. `containsAboveUpperBoundary()`
   1. Above the Upper Boundary
6. `containsMaxDoubleValue()`
   1. Upper Extreme Value
7. `containsMinDoubleValue()`
   1. Lower Extreme Value

## `DataUtilities.createNumberArray2D(double[][] data)`

The `createNumberArray2D()` function expects a double two-dimensional array. It returns a copy of the given array into a `Number` two-dimensional array. Due to this, there isn’t many possible test cases, therefore, illegal and legal inputs are tested.

**Test Cases**

1. `createNumberArray2d_NullValue()`
   1. A null double array
   2. Expects an IllegalArgumentException to be thrown
2. `createNumberArray2D_EmptyArray()`
   1. An empty double two-dimensional array
   2. Expects a `Number` empty array
3. `createNumberArray2D_ValidArray()`
   1. A double two-dimensional array with valid data
4. `createNumberArray2D_ExtremeValue()`
   1. A double two-dimensional array with extreme data
5. `createNumberArray2D_SmallNegativeValue()`
   1. A double two-dimensional array with very small negative values

## `**DataUtilities.calculateRowTotal(Values2D data, int row)**`

The `DataUtilities()` function expects a Values2D object, and a column. It is important to note that Values2D is an interface, and during the testing of this method, both mocking and regular testing was conducted. Boundary values, extreme values, illegal values, and null values were tested to see how the method responds to each.

**Test Cases**

1. `calculateRowTotal_NullData()`
   1. A null table and valid row
2. `calculateRowTotal_ValidDataValidRow()`
   1. A table with valid data and valid row
3. `calculateRowTotal_ValidDataExtremeValuesValidRow()`
   1. A table with extreme data and valid row
4. `calculateRowTotal_ValidDataNegativeValueValidRow()`
   1. A table with very small negative data and a valid row
5. `calculateRowTotal_ValidDataInvalidRowAboveUpperBoundary()`
   1. A table with valid data and an invalid row which is above the upper boundary for rows
6. `calculateRowTotal_ValidDataInvalidRowBelowLowerBoundary()`
   1. A table with valid data and an invalid row which is below the lower boundary for rows

## `DataUtilities.getCumulativePercentages(KeyedValues data)`

The `DataUtilities()` function expects a KeyedValues object. It is important to note that KeyedValues is an interface, and during the testing of this method, mocking was used. It returns an instance of KeyedValues that contains cumulative percentages of the given data. As only `Number`data was used there weren’t many possible test cases therefore illegal and legal inputs were tested

**Test Cases**

1. `getCumulativePercentages_ValidValues()`
   1. A KeyedValues object containing valid values
2. `getCumulativePercentages_ExtremeValues()`
   1. A KeyedValues object containing extreme values
3. `getCumulativePercentages_ZeroValues()`
   1. A KeyedValues object containing only zero values
4. `getCumulativePercentages_WithNullValues()`
   1. A KeyedValues object containing null values and valid values
5. `getCumulativePercentages_NullData()`
   1. The KeyedValues object is null

## `DataUtilities.createNumberArray(double[] data)`

The `createNumberArray()` function expects a double array. It simply converts it to a `Number` type. Due to this, not a lot of test cases exist, and as a result, we test illegal and legal conditions.

**Test Cases**

1. `createNumberArray_NonNullDataInput`
   1. A non-null data array
2. `createNumberArray_ExtremeDataInput()`
   1. A non-null data array with extreme double values
3. `createNumberArray_NullDataArray()`
   1. A null data array

## `**DataUtilities.calculateColumnTotal(Values2D data, int column, int []validRows)**`

The `DataUtilities()` function expects a Values2D object, a column and a list of validRows. It is important to note that Values2D is an interface, and during the testing of this method, both mocking and regular testing was conducted. Boundary values, extreme values and illegal values were tested to see how the method responds to each. This includes utilizing `try catch` blocks to check for exceptions. Null values are also tested for Values2D and invalid Rows.

**Test Cases**

1. `calculateColumnTotal_NonNullAndValidColumn()`
   1. A non-null table and a column count > 0 but column count < the table’s column length and a ValidRows list with all values < 0 and values < the total rows in the valid table.
2. `calculateColumnTotal_TooSmallColumn()`
   1. 1. A non-null table with a column count < 0 and Allowed ValidRows
3. `calculateColumnTotal_TooLargeColumn()`
   1. A non-null table with a column count > the table’s total columns and Allowed ValidRows
4. `calculateColumnTotal_ValidRowsExceeded()`
   1. A non-null table with valid columns but a row in the ValidRows table > the total rows in table
   2. This test case fails. The method is unable to throw an exception and effectively parse an row which exceeds what is allowed. Our test cases shows this as an error and can be found at the assert statement.
5. `calculateColumnTotal_RowsBelowZero()`
   1. A non-null table with valid columns but a row in the ValidRows table < 0

# **4. How the team work/effort was divided and managed**

Setup of the lab environment including creating the JUnit class responsible for holding the necessary test cases, writing of the laboratory report, and internal review of proposed testing was done on a voice call with screen sharing between all team members, to ensure equal participation and opportunity to vocalize ideas or concerns.

All other work was divided between team members along Java class separates, with two pairs of two people managing 5 methods between them. One pair, Team A, was responsible for testing methods within `org.free.data.DataUtilites`. The other, Team B, was responsible for testing methods from `org.jfree.data.Range`. Both teams created their test plans in a document shared among the whole group, allowing team members to critique and suggest improvements for another pair’s tests, even if the individual doing such review was not testing those methods themselves.

Within each pair, team members collaborated to come up with test cases, following the steps outlined in slides from lectures regarding black-box testing protocol. Equivalent classes, test case partitions, boundary value test cases, and worst case test cases were created and evaluated within each pair, for their respective classes. The document containing the testing plan was available to both members of each pair throughout the test process, allowing for regular referencing of work and to ensure that any concerns or challenges confronted during the testing process could be compared with the testing plan to see if they had already been managed by the other partner in the pair.

Implementation of test cases in the required JUnit test class was done individually, with each pair assigning an equal, or as equal as was feasible considering test case size and complexity, number of test cases to write and execute on a separate branch. By allocating a given number of test cases to each team member it was easier to see if certain cases overlapped, allowed for one team member’s technical challenges to not impeded on the function of those of other team members’ test cases, and provide version control throughout the test-writing process.

Upon each team member’s completion of writing effective and functional test cases, the branches containing their work were carefully rebased on to the main branch, where they could be added to the JUnit class intended to contain all used test cases, where they were asynchronously reviewed by the group and any necessary final fixes of logic or formatting could be applied.

# **5 Difficulties encountered, challenges overcome, and lessons learned**

Throughout the testing process followed in the laboratory, multiple challenges were encountered. These challenges included difficulty in differentiating between a failed test due to a bug in the SUT or a failure due to erroneous test writing, isolating methods so it is only the functionality of a given method that is being tested, not the object it is a member of, and managing overlap in some testing categories such as ECT, boundary, and worst case testing.

Issues caused by the possible ambiguity of failed tests caused some confusion and delays in the test-writing process, as the source of the error had to be resolved before it could be determined if the fault causing the failure was one that the lab was intended to catch, or if it was a problem that originated from the testing class and methods themselves. The solution found to work best at resolving this uncertainty was reading through all included documentation for classes and methods, followed by having fellow team members not writing that particular test case run it on their own machine to check for issues in environment variables, and finally, reviewing other test cases for the same method to see if any other test cases are behaving in a similar way or indicate any other unexpected behaviour. Following this protocol, it could be confidently determined which errors were caused by bugs in the provided classes and which were the result of incorrectly written tests.

There were also challenges encountered regarding the isolation of methods being tested, particularly when it came to ensuring that only the methods in question were being tested and not the operation of their parent classes. In some situations, this could be resolved through the use of JMock to imitate the functions of a non-tested classes. This, however, was not possible with the parent classes of which the methods being tested and was especially apparent with member functions of the Range class. Certain inputs for tests, such as those for the method `intersects(Range a)`, an invalid input other than a `null` Range object could not be provided to test, as this would only test the ability for the Range object constructor to handle illegal arguments, not the functionality of the `intersects` method. In these situations, test cases had to be designed carefully to avoid testing the parent class, and test cases which could violate this had to be excluded from testing.

When determining partitions for test cases, it occasionally became difficult to decide whether a test partition qualified as an ECT, boundary testing, worst case testing, or more than one of these categories. Certain cases, such as boundary cases that required extreme values such as `Double.MAX_VAL` but in circumstances where a particular boundary was the maximum value of a double would qualify as both boundary testing, due to being on the boundary, as well
as robustness testing, because the value was so substantial that it would not be expected under normal use. Handling this conflict involved using qualitative evaluation of the situation, as well as considering the extensiveness to which the method had been evaluated by other test cases written for it.

The challenges and difficulties in this lab primarily involved the holistic evaluation of the testing process, where it needed to be ensured that all possible methods could be tested without writing extraneous or repetitive tests. Group collaboration and discussion proved particularly helpful when resolving these conflicts, where individual evaluation of the situation could be provided to the group and a thoughtful decision could be made to resolve the issue.

# **6 Comments/feedback on the lab itself**

Great Lab!
