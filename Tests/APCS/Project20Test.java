import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 20 - Merge Sort &amp; Quick Sort
 * @author Rob Schreiber
 */
public class Project20Test {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = 1E-3;
    private String[] keywords = new String[100];
    private String[] responses = new String[100];

    /**
     * Calls the runTests method.
     * @param args Not used
     */
    public static void main(String[] args) {
    	Project20Test tester = new Project20Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////
        
        
        printTest("randomOrderMergeSortTest", randomOrderMergeSortTest());
        printTest("ascendingOrderMergeSortTest", ascendingOrderMergeSortTest());
        printTest("descendingOrderMergeSortTest", descendingOrderMergeSortTest());
        printTest("randomOrderWithRepeatsMergeSortTest", randomOrderWithRepeatsMergeSortTest());
        printTest("randomOrderQuickSortTest", randomOrderQuickSortTest());
        printTest("ascendingOrderQuickSortTest", ascendingOrderQuickSortTest());
        printTest("descendingOrderQuickSortTest", descendingOrderQuickSortTest());
        printTest("randomOrderWithRepeatsQuickSortTest", randomOrderWithRepeatsQuickSortTest());

        printFinalSummary();
    	
    }

    // Ascending Selection Sort Tests

	private boolean randomOrderMergeSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 1, 3, 2, 4};
            IntegerArraySorter.mergeSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean ascendingOrderMergeSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
            IntegerArraySorter.mergeSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean descendingOrderMergeSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 4, 3, 2, 1};
            IntegerArraySorter.mergeSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean randomOrderWithRepeatsMergeSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{3, 3, 1, 2, 4, 5, 5, 1, 4};
            IntegerArraySorter.mergeSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

       // Ascending Insertion Sort Tests

    private boolean randomOrderQuickSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 1, 3, 2, 4};
            IntegerArraySorter.quickSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean ascendingOrderQuickSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
            IntegerArraySorter.quickSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean descendingOrderQuickSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 4, 3, 2, 1};
            IntegerArraySorter.quickSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean randomOrderWithRepeatsQuickSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{3, 3, 1, 2, 4, 5, 5, 1, 4};
            IntegerArraySorter.quickSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }


    ////////////////////////////////
    // utility methods for testing
    ////////////////////////////////

    /** Print test results in a consistent format
     * @param testDesc description of the test
     * @param result indicates if the test passed or failed
     */
    private void printTest(String testDesc, boolean result) {
        total++;
        if (result) {
            passes++;
        } else {
            failures++;
        }
        System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
    }

    /** Print a final summary */
    private void printFinalSummary() {
        System.out.printf("\nTotal Tests: %d,  Passed: %d,  Failed: %d\n", total, passes, failures);
    }

    /** Compare two doubles and return true if they are within TOLERANCE, else false
     * @param v1 first value to compare
     * @param v2 second value to compare
     * @return true if v1 and v2 are within TOLERANCE, else false
     */
    private boolean isClose(double v1, double v2) {
        return (Math.abs(v1 - v2) < TOLERANCE);
    }

    /** Returns true or false whether or not an element is in a sorted String array
     * @param ary The sorted String array
     * @param s The element to be found in the String array
     */
    private boolean contains(String[] ary, String s) {
        return Arrays.binarySearch(ary, s) >= 0 ;
    }
}
