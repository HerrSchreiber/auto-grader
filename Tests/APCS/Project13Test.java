import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 13 - Fractions
 * @author Rob Schreiber
 */
public class Project13Test {
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
    	Project13Test tester = new Project13Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("mercuryWeightTest", mercuryWeightTest());
        printTest("venusWeightTest", venusWeightTest());
        printTest("earthWeightTest", earthWeightTest());
        printTest("marsWeightTest", marsWeightTest());
        printTest("jupiterWeightTest", jupiterWeightTest());
        printTest("saturnWeightTest", saturnWeightTest());
        printTest("uranusWeightTest", uranusWeightTest());
        printTest("neptuneWeightTest", neptuneWeightTest());
        printTest("arbitraryWeightTest", arbitraryWeightTest());
        
        printFinalSummary();
    }


    private boolean mercuryWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.mercuryWeight(50);
            if (!isClose(result, 50 * 3.70)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean venusWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.venusWeight(50);
            if (!isClose(result, 50 * 8.87)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean earthWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.earthWeight(50);
            if (!isClose(result, 50 * 9.81)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean marsWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.marsWeight(50);
            if (!isClose(result, 50 * 3.71)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean jupiterWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.jupiterWeight(50);
            if (!isClose(result, 50 * 23.12)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean saturnWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.saturnWeight(50);
            if (!isClose(result, 50 * 10.44)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean uranusWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.uranusWeight(50);
            if (!isClose(result, 50 * 8.69)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean neptuneWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.neptuneWeight(50);
            if (!isClose(result, 50 * 11.00)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean arbitraryWeightTest() {
        boolean success = true;

        try {
            double result = SpaceWeights.arbitraryPlanetWeight(100, 100, 100); //TODO: Double check these values
            if (!isClose(result, 6.67E-11)) success = false;
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
				System.out.println((int)(((double)passes/total)*20) + "/20");
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
