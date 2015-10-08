import java.util.*;
import java.io.*;

/**
 * Tests Project 8 - Text Editor Part 2
 * @author Rob Schreiber
 */
public class Project8Test {
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
    	Project8Test tester = new Project8Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("edInterfaceTest", edInterfaceTest());
        printTest("constructorTest", constructorTest());
        printTest("rightArrowTest", rightArrowTest());
        printTest("leftArrowTest", leftArrowTest());
        printTest("deleteTest", deleteTest());
        printTest("backspaceTest", backspaceTest());
        printTest("homeTest", homeTest());
        printTest("endTest", endTest());
        printTest("rightArrowExceptionTest", rightArrowExceptionTest());
        printTest("deleteExceptionTest", deleteExceptionTest());
        printTest("leftArrowExceptionTest", leftArrowExceptionTest());
        printTest("backspaceExceptionTest", backspaceExceptionTest());
        printTest("toStringTest", toStringTest());
    }

    private boolean edInterfaceTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hi", 1);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean constructorTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           if (!c.getFirst().equals("hi") || !c.getRest().equals("there")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rightArrowTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.rightArrow();
           if (!c.getFirst().equals("hit") || !c.getRest().equals("here")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean leftArrowTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.leftArrow();
           if (!c.getFirst().equals("h") || !c.getRest().equals("ithere")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean deleteTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.delete();
           if (!c.getFirst().equals("hi") || !c.getRest().equals("here")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean backspaceTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.backspace();
           if (!c.getFirst().equals("h") || !c.getRest().equals("there")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean insertTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.insertString("f");
           if (!c.getFirst().equals("hif") || !c.getRest().equals("there")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean homeTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.homeKey();
           if (!c.getFirst().equals("")|| !c.getRest().equals("hithere")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean endTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           c = c.endKey();
           if (!c.getFirst().equals("hithere") || !c.getRest().equals("")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rightArrowExceptionTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor("hithere", "");
           c = c.rightArrow();
           if (!c.getFirst().equals("hithere") || !c.getRest().equals("")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean deleteExceptionTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor("hithere", "");
           c = c.delete();
           if (!c.getFirst().equals("hithere") || !c.getRest().equals("")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean leftArrowExceptionTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor("", "hithere");
           c = c.leftArrow();
           if (!c.getFirst().equals("") || !c.getRest().equals("hithere")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean backspaceExceptionTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor("", "hithere");
           c = c.backspace();
           if (!c.getFirst().equals("") || !c.getRest().equals("hithere")) {
                success = false;
           }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean toStringTest() {
        boolean success = true;
        
        try {
           Ed c = new Editor2("hithere", 2);
           String expected = c.toString();
           if (!expected.equals("hi|there")) {
                success = false;
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