import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 18 - ArrayList
 * @author Rob Schreiber
 */
public class Project18Test {
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
    	Project18Test tester = new Project18Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("noSuchElementExceptionTest", noSuchElementExceptionTest());
        printTest("indexOutOfBoundsExceptionTest", indexOutOfBoundsExceptionTest());
        printTest("addAndResizeTest", addAndResizeTest());
        printTest("addAtIndexTest", addAtIndexTest());
        printTest("removeTest", removeTest());
        printTest("setTest", setTest());
    	
    }

	private boolean noSuchElementExceptionTest() {
        boolean success = true;
        
        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.get(0);
            success = false;
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.set(0, 5);
            success = false;
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.remove(0);
            success = false;
        } catch (NoSuchElementException e) {

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }   

    private boolean indexOutOfBoundsExceptionTest() {
        boolean success = true;
        
        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.get(-1);
            success = false;
        } catch (IndexOutOfBoundsException e) {

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.set(2, 5);
            success = false;
        } catch (IndexOutOfBoundsException e) {

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.remove(-1);
            success = false;
        } catch (IndexOutOfBoundsException e) {

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.add(1, 5);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }   

    private boolean addAndResizeTest() {
        boolean success = true;
        
        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int i = 0; i < 1000; i++) {
                al.add(i);
            }

            for (int i = 0; i < 1000; i++) {
                if (al.get(i).intValue() != i) success = false;
            }
            if (al.size() != 1000) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean addAtIndexTest() {
        boolean success = true;
        
        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int i = 0; i < 1000; i++) {
                al.add(0, i);
            }

            for (int i = 0; i < 1000; i++) {
                if (al.get(i).intValue() != 999 - i) success = false;
            }
            if (al.size() != 1000) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean removeTest() {
        boolean success = true;
        
        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int i = 0; i < 100; i++) {
                al.add(i);
            }
            al.remove(42);
            for (int i = 0; i < 99; i++) {
                if (al.get(i).intValue() == 42) success = false;
                if (i > 41 && al.get(i).intValue() != i + 1) success = false;
            }
            if (al.size() != 99) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean setTest() {
        boolean success = true;
        
        try {
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int i = 0; i < 100; i++) {
                al.add(i);
            }
            al.set(42, 9001);
            if (al.get(42).intValue() != 9001) success = false;
            for (int i = 0; i < 99; i++) {
                if (i != 42 && al.get(i).intValue() != i) success = false;
            }
            if (al.size() != 100) success = false;
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
