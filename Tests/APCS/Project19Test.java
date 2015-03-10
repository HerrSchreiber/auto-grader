import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 19 - Selection Sort &amp; Insertion Sort
 * @author Rob Schreiber
 */
public class Project19Test {
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
    	Project19Test tester = new Project19Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////
        
        
        printTest("randomOrderAscendingSelectionSortTest", randomOrderAscendingSelectionSortTest());
        printTest("ascendingOrderAscendingSelectionSortTest", ascendingOrderAscendingSelectionSortTest());
        printTest("descendingOrderAscendingSelectionSortTest", descendingOrderAscendingSelectionSortTest());
        printTest("randomOrderWithRepeatsAscendingSelectionSortTest", randomOrderWithRepeatsAscendingSelectionSortTest());
        printTest("randomOrderDescendingSelectionSortTest", randomOrderDescendingSelectionSortTest());
        printTest("ascendingOrderDescendingSelectionSortTest", ascendingOrderDescendingSelectionSortTest());
        printTest("descendingOrderDescendingSelectionSortTest", descendingOrderDescendingSelectionSortTest());
        printTest("randomOrderWithRepeatsDescendingSelectionSortTest", randomOrderWithRepeatsDescendingSelectionSortTest());
        printTest("randomOrderAscendingInsertionSortTest", randomOrderAscendingInsertionSortTest());
        printTest("ascendingOrderAscendingInsertionSortTest", ascendingOrderAscendingInsertionSortTest());
        printTest("descendingOrderAscendingInsertionSortTest", descendingOrderAscendingInsertionSortTest());
        printTest("randomOrderWithRepeatsAscendingInsertionSortTest", randomOrderWithRepeatsAscendingInsertionSortTest());
        printTest("randomOrderDescendingInsertionSortTest", randomOrderDescendingInsertionSortTest());
        printTest("ascendingOrderDescendingInsertionSortTest", ascendingOrderDescendingInsertionSortTest());
        printTest("descendingOrderDescendingInsertionSortTest", descendingOrderDescendingInsertionSortTest());
        printTest("randomOrderWithRepeatsDescendingInsertionSortTest", randomOrderWithRepeatsDescendingInsertionSortTest());
    	
    }

    // Ascending Selection Sort Tests

	private boolean randomOrderAscendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 1, 3, 2, 4};
            IntegerArraySorter.ascendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean ascendingOrderAscendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
            IntegerArraySorter.ascendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean descendingOrderAscendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 4, 3, 2, 1};
            IntegerArraySorter.ascendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean randomOrderWithRepeatsAscendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{3, 3, 1, 2, 4, 5, 5, 1, 4};
            IntegerArraySorter.ascendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    // Descending Selection Sort Tests


    private boolean randomOrderDescendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 1, 3, 2, 4};
            IntegerArraySorter.descendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean ascendingOrderDescendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
            IntegerArraySorter.descendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean descendingOrderDescendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 4, 3, 2, 1};
            IntegerArraySorter.descendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean randomOrderWithRepeatsDescendingSelectionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{3, 3, 1, 2, 4, 5, 5, 1, 4};
            IntegerArraySorter.descendingSelectionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    // Ascending Insertion Sort Tests

    private boolean randomOrderAscendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 1, 3, 2, 4};
            IntegerArraySorter.ascendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean ascendingOrderAscendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
            IntegerArraySorter.ascendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean descendingOrderAscendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 4, 3, 2, 1};
            IntegerArraySorter.ascendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean randomOrderWithRepeatsAscendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{3, 3, 1, 2, 4, 5, 5, 1, 4};
            IntegerArraySorter.ascendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) > 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    // Descending Insertion Sort Tests


    private boolean randomOrderDescendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 1, 3, 2, 4};
            IntegerArraySorter.descendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean ascendingOrderDescendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
            IntegerArraySorter.descendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean descendingOrderDescendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{5, 4, 3, 2, 1};
            IntegerArraySorter.descendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean randomOrderWithRepeatsDescendingInsertionSortTest() {
        boolean success = true;
        
        try {
            Integer[] nums = new Integer[]{3, 3, 1, 2, 4, 5, 5, 1, 4};
            IntegerArraySorter.descendingInsertionSort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i].compareTo(nums[i + 1]) < 0) success = false;
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
