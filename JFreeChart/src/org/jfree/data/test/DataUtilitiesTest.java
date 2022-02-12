package org.jfree.data.test;

import com.orsoncharts.data.Values2D;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValue;
import org.jfree.data.DefaultKeyedValues2D;
import org.jmock.Expectations;
import org.jmock.*;
import static org.junit.Assert.*;
import org.junit.*;


public class DataUtilitiesTest {
    private DataUtilities exampleDataUtilities;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }
   

    //Normal Input with non-null values
    @Test
    public void createNumberArray_NonNullDataInput() {
        //Sample Input with Non Null Data Input
        double [] data = { 13.4, 12.00004, 0.001, 1235.0, 234};
        Number [] result = DataUtilities.createNumberArray(data);
        Number [] expected = { 13.4, 12.00004, 0.001, 1235.0, 234.0};
        
        assertArrayEquals("The data input should include non-null double values", expected, result);
    }

    //Too Large value or Too Small Value. Checked for Conversion. 
    @Test
    public void createNumberArray_ExtremeDataInput() {
        //Sample Input with Extreme (large/small) doubles
        double [] data = { Double.MAX_VALUE, Double.MIN_VALUE};
        Number [] result = DataUtilities.createNumberArray(data);
        Number [] expected = { Double.MAX_VALUE, Double.MIN_VALUE};
        
        assertArrayEquals("Extreme values should be allowed (large doubles and small doubles)", expected, result);
    }

    //Invalid Array is used as an Argument. 
    @Test
     public void createNumberArray_NullDataArray() {
         //Sample Input with Null. Expect a null return
         double [] data = {};
         Number [] result = DataUtilities.createNumberArray(data);
         assertNotNull("Checking that createNumberArray can throw exception when a null array is sent",  result);
     }
 
    
    Mockery context;

    //Checking whether calculateColumnTotal works as expected with non-null values
    @Before
    public void setUp_NonNullAndValidColumn() throws Exception { 
        context = new Mockery();
    }
    
    @Test
    public void calculateColumnTotal_NonNullAndValidColumn(){
        final org.jfree.data.Values2D values = context.mock(org.jfree.data.Values2D.class);

        int dataRowCount = 3;
        int column = 2;

        context.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(dataRowCount));
            one(values).getValue(0,column);
            will(returnValue(2.4));
            one(values).getValue(1,column);
            will(returnValue(7.2));
            one(values).getValue(2,column);
            will(returnValue(9.4));
        }});
        
        int [] validRows = {0,1,2};

        double result = DataUtilities.calculateColumnTotal(values, column, validRows);
        assertEquals("Method cannot support a valid Values2D object, column and valid rows", result, 19.0, .000000001d);
    }

    @After
    public void tearDown_NonNullAndValidColumn() throws Exception {
        context = null;
    }


    //Test a column value not allowed by Value 2D Array. ie < 0
    @Before
     public void setUp_TooSmallColumn() throws Exception { 
         context = new Mockery();
     }
     
     @Test
     public void calculateColumnTotal_TooSmallColumn(){
         DefaultKeyedValues2D test = new DefaultKeyedValues2D();
         test.addValue(1, 0, 0);
         org.jfree.data.Values2D values = (org.jfree.data.Values2D) test;
 
         int [] validRows = {0};
 
 
         try{
            double value = DataUtilities.calculateColumnTotal((org.jfree.data.Values2D) values, -1, validRows);
            assertNull("Calculation should throw exception to out of bounds value (<0)",value);
         } catch (Exception e){

        }         
     }

     @After
     public void tearDown_TooSmallColumn() throws Exception {
         context = null;
     }
 
    //Testing column that is too large. Exception should be thrown. 
    @Test
    public void calculateColumnTotal_TooLargeColumn(){
        DefaultKeyedValues2D test = new DefaultKeyedValues2D();
        test.addValue(1, 0, 0);
        org.jfree.data.Values2D values = (org.jfree.data.Values2D) test;

        int [] validRows = {0};


        try{
           double value = DataUtilities.calculateColumnTotal((org.jfree.data.Values2D) values, 5, validRows);
           assertNull("Calculation should throw exception to out of bounds value (>Values Length)",value);
        } catch (Exception e){

       }         
    }

    //New Test
    @Before
    public void setUp_ValidRowsExceeded() throws Exception { 
        context = new Mockery();
    }
    
    @Test
    public void calculateColumnTotal_ValidRowsExceeded(){
        final org.jfree.data.Values2D values = context.mock(org.jfree.data.Values2D.class);

        int dataRowCount = 3;
        int column = 2;

        context.checking(new Expectations() {{
            one(values).getRowCount();
            will(returnValue(dataRowCount));
        }});
        
        int [] validRows = {4};

        try{

            double result = DataUtilities.calculateColumnTotal(values, 3, validRows);
            assertNull("Valid Rows Should not Exceed Rows in Values Table", result);

        } catch (Exception e){

        }
    }

    @After
    public void tearDown_ValidRowsExceeded() throws Exception {
        context = null;
    }

    //Testing whether Invalid Row (<0) is accepted. Exception thrown by methods means test passes
    @Test
    public void calculateColumnTotal_RowsBelowZero(){
        DefaultKeyedValues2D test = new DefaultKeyedValues2D();
        test.addValue(1, 0, 0);
        org.jfree.data.Values2D values = (org.jfree.data.Values2D) test;

        int [] validRows = {-1};


        try{
           double value = DataUtilities.calculateColumnTotal((org.jfree.data.Values2D) values, 0, validRows);
           assertNull("Calculation should throw exception to out of bounds value (<0) for Rows",value);
        } catch (Exception e){

        }         
    }
 


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
