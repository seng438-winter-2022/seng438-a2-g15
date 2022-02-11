package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Range;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

public class DataUtilitiesTest extends DataUtilities {

	@Test(expected = IllegalArgumentException.class)
	public void createNumberArray2d_NullValue() {
		double[][] testArray = null;
		
		DataUtilities.createNumberArray2D(testArray);
	}
	
    @Test
    public void createNumberArray2D_EmptyArray() {
        double[][] testArray = new double[][] {};
        
        assertArrayEquals("The created Number Array is empty",testArray, DataUtilities.createNumberArray2D(testArray));
    }

    @Test
    public void createNumberArray2D_ValidArray() {
        double[][] testArray = new double[][] {{1.1, 12, 6.77}, {25, 65.4, 22.22}};
        
        assertArrayEquals("The created Number Array contains {{1.1, 12, 6.77}, {25, 65.4, 22.22}}",testArray, DataUtilities.createNumberArray2D(testArray));
    }

    @Test
    public void createNumberArray2D_ExtremeValue() {
        double[][] testArray = new double [][] {{Double.MAX_VALUE, Double.MAX_VALUE}, {Double.MAX_VALUE, Double.MAX_VALUE}};
        
        assertArrayEquals("The created Number Array contains extreme values"
        		,testArray, DataUtilities.createNumberArray2D(testArray));
    }
    
    @Test
    public void createNumberArray2D_SmallNegativeValue() {
        double[][] testArray = new double [][] {{Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE}, 
        	{Double.MIN_VALUE, Double.MIN_VALUE}};
        
        assertArrayEquals("The created Number Array contains very small negative values"
        		,testArray, DataUtilities.createNumberArray2D(testArray));
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------//
    
    @Test
    public void calculateRowTotal_NullData() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(null));
                one(values).getValue(0, 1);
                will(returnValue(null));
                one(values).getValue(0, 2);
                will(returnValue(null));
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated total is 0.0",result, 0.0, .000000001d);
    }
    
    @Test
    public void calculateRowTotal_ValidDataValidRow() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(5.67));
                one(values).getValue(0, 1);
                will(returnValue(5.2));
                one(values).getValue(0, 2);
                will(returnValue(1.2));
                one(values).getValue(1, 0);
                will(returnValue(1.7));
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The calculated total is 12.07",result, 12.07, .000000001d);
    }
    
    @Test
    public void calculateRowTotal_ValidDataExtremeValuesValidRow() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(2));
                one(values).getValue(2, 0);
                will(returnValue(Double.MAX_VALUE));
                one(values).getValue(2, 1);
                will(returnValue(Double.MAX_VALUE));
                one(values).getValue(2, 2);
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 2);
        assertEquals("The calculated total is Double.MAX_VALUE+Double.MAX_VALUE",result, (Double.MAX_VALUE+Double.MAX_VALUE), .000000001d);
    }

    @Test
    public void calculateRowTotal_ValidDataNegativeValueValidRow() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(3));
                one(values).getValue(1, 0);
                will(returnValue(Double.MIN_VALUE));
                one(values).getValue(1, 1);
                will(returnValue(Double.MIN_VALUE));
                one(values).getValue(1, 2);
                will(returnValue(Double.MIN_VALUE));
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 1);
        assertEquals("The calculated total is Double.MIN_VALUE+Double.MIN_VALUE+Double.MIN_VALUE"
        		,result, (Double.MIN_VALUE+Double.MIN_VALUE+Double.MIN_VALUE), .000000001d);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateRowTotal_ValidDataInvalidRowAboveUpperBoundary() {
    	DefaultKeyedValues2D test = new DefaultKeyedValues2D();
    	test.addValue(1, 0, 0);
        Values2D values = test;
        
        DataUtilities.calculateRowTotal(values, 5);
    }
    

    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateRowTotal_ValidDataInvalidRowBelowLowerBoundary() {
    	DefaultKeyedValues2D test = new DefaultKeyedValues2D();
    	test.addValue(1, 0, 0);
        Values2D values = test;
        
        DataUtilities.calculateRowTotal(values, -2);
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------//
    
    @Test
    public void getCumulativePercentages_ValidValues() {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
            	allowing(values).getItemCount();
                will(returnValue(2));
                allowing(values).getKey(0);
                will(returnValue(0));
                allowing(values).getValue(0);
                will(returnValue(6.0));
                allowing(values).getKey(1);
                will(returnValue(1));
                allowing(values).getValue(1);
                will(returnValue(4.0));
            }
        });
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        
        Number[] expectedResults = {(6.0/10.0), 1.0};
        Number [] actualResults = new Number[2];
        for(int i = 0; i < result.getItemCount(); i++) {
        	actualResults[i] = result.getValue(i);
        }
        
        assertArrayEquals("The KeyedValues object contains cumulative percentages 0.6 and 1.0",expectedResults, actualResults);
    }
    
    @Test
    public void getCumulativePercentages_ExtremeValues() {
    	Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
            	allowing(values).getItemCount();
                will(returnValue(2));
                allowing(values).getKey(0);
                will(returnValue(0));
                allowing(values).getValue(0);
                will(returnValue(Double.MAX_VALUE));
                allowing(values).getKey(1);
                will(returnValue(1));
                allowing(values).getValue(1);
                will(returnValue(Double.MAX_VALUE));
            }
        });
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        
        Number[] expectedResults = {(Double.MAX_VALUE/(Double.MAX_VALUE+Double.MAX_VALUE)), 
        		((Double.MAX_VALUE+Double.MAX_VALUE)/(Double.MAX_VALUE+Double.MAX_VALUE))};
        Number [] actualResults = new Number[2];
        for(int i = 0; i < result.getItemCount(); i++) {
        	actualResults[i] = result.getValue(i);
        }
        
        assertArrayEquals("The KeyedValues object contains cumulative percentages (Double.MAX_VALUE/(Double.MAX_VALUE+Double.MAX_VALUE) and ((Double.MAX_VALUE+Double.MAX_VALUE)/(Double.MAX_VALUE+Double.MAX_VALUE))"
        		,expectedResults, actualResults);
    }
    
    @Test
    public void getCumulativePercentages_ZeroValues() {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
            	allowing(values).getItemCount();
                will(returnValue(2));
                allowing(values).getKey(0);
                will(returnValue(0));
                allowing(values).getValue(0);
                will(returnValue(0));
                allowing(values).getKey(1);
                will(returnValue(1));
                allowing(values).getValue(1);
                will(returnValue(0));
            }
        });
        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        Number[] expectedResults = {Double.NaN, Double.NaN};
        Number [] actualResults = {0.0, 0.0};
        for(int i = 0; i < result.getItemCount(); i++) {
        	actualResults[i] = result.getValue(i);
        }
        
        assertArrayEquals("The KeyedValues object contains cumulative percentages Double.NaN and Double.NaN",expectedResults, actualResults);
    }
    
    @Test
    public void getCumulativePercentages_WithNullValues() {
    	Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
            	allowing(values).getItemCount();
                will(returnValue(4));
                allowing(values).getKey(0);
                will(returnValue(0));
                allowing(values).getValue(0);
                will(returnValue(null));
                allowing(values).getKey(1);
                will(returnValue(1));
                allowing(values).getValue(1);
                will(returnValue(4.5));
                allowing(values).getKey(2);
                will(returnValue(2));
                allowing(values).getValue(2);
                will(returnValue(null));
                allowing(values).getKey(3);
                will(returnValue(3));
                allowing(values).getValue(3);
                will(returnValue(3.5));
            }
        });
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        
        Number[] expectedResults = {0.0, (4.5/8.0), (4.5/8.0), 1.0};
        Number [] actualResults = new Number[4];
        for(int i = 0; i < result.getItemCount(); i++) {
        	actualResults[i] = result.getValue(i);
        }
        
        assertArrayEquals("The KeyedValues object contains cumulative percentages 0.5625 and 0.5625",expectedResults, actualResults);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void getCumulativePercentages_NullData() {
    	KeyedValues values = null;
    	DataUtilities.getCumulativePercentages(values);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
