import java.util.*;
import java.io.*;

/**
 * Tests Project 9 - Pig Latin Translator
 * @author Rob Schreiber
 */
public class Project9Test {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = 1E-3;
    private String[] keywords = new String[100];
    private String[] responses = new String[100];

    /**
     * Reads in the keywords and responses from README.TXT and
     * stores them in two String arrays called keywords and
     * responses. Then it calls the runTests method.
     * @param args Not used
     */
    public static void main(String[] args) {
    	Project9Test tester = new Project9Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("isVowelTest", isVowelTest());
        printTest("e2pConsonantTest", e2pConsonantTest());
        printTest("e2pVowelTest", e2pVowelTest());
        printTest("p2eTest", p2eTest());
    }

    private boolean isVowelTest() {
        boolean success = true;
        String testString;
        try {
            PigLatinTranslator p = new PigLatinTranslator();
            for(int i = 65; i < 123; i++) {
                testString = "" + (char)i;
                if (i == 65 || i == 69 || i == 73 || i == 79 || i == 85 || i == 97 || i == 101 || i == 105 || i == 111 || i == 117) {
                    if (!p.isVowel(testString)) {
                        success = false;
                        System.out.println("error:" + testString + " is a vowel");
                    }
                }
                else if (i == 89 || i == 121 || i > 90 && i < 97); // behavior poorly defined for "y" & symbols
                else {
                    if (p.isVowel(testString)) {
                        success = false;
                        System.out.println("error:" + testString + " is not a vowel");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean e2pConsonantTest() {
        boolean success = true;
        
        try {
            PigLatinTranslator p = new PigLatinTranslator();
            String testString = "test";
            String expectedValue = "esttay";
            String actualValue = p.translateE2P(testString);
            if (!expectedValue.equals(actualValue)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean e2pVowelTest() {
        boolean success = true;
        
        try {
            PigLatinTranslator p = new PigLatinTranslator();
            String testString = "anthill";
            String expectedValue = "anthillway";
            String actualValue = p.translateE2P(testString);
            if (!expectedValue.equals(actualValue)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean p2eTest() {
        boolean success = true;
        
        try {
            PigLatinTranslator p = new PigLatinTranslator();
            String testString = "estray";
            String expectedValue = "rest";
            String actualValue = p.translateP2E(testString);
            if (!expectedValue.equals(actualValue)) success = false;
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
