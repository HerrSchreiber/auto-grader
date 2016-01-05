import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 12 - Binary Numbers
 * @author Rob Schreiber
 */
public class Project12Test {
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
    	Project12Test tester = new Project12Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("binaryNumberInterfaceTest", binaryNumberInterfaceTest());
        printTest("intConstructorTest", intConstructorTest());
        printTest("stringConstructorTest", stringConstructorTest());
        printTest("negativeStringConstructorTest", negativeStringConstructorTest());
        printTest("addImmutabilityTest", addImmutabilityTest());
        printTest("subtractImmutabilityTest", subtractImmutabilityTest());
        printTest("negateImmutabilityTest", negateImmutabilityTest());
        printTest("shiftLeftImmutabilityTest", shiftLeftImmutabilityTest());
        printTest("uShiftRightImmutabilityTest", uShiftRightImmutabilityTest());
        printTest("andImmutabilityTest", andImmutabilityTest());
        printTest("orImmutabilityTest", orImmutabilityTest());
        printTest("xorImmutabilityTest", xorImmutabilityTest());
        printTest("addTest", addTest());
        printTest("subtractTest", subtractTest());
        printTest("negateTest", negateTest());
        printTest("shiftLeftTest", shiftLeftTest());
        printTest("uShiftRightTest", uShiftRightTest());
        printTest("andTest", andTest());
        printTest("orTest", orTest());
        printTest("xorTest", xorTest());
        printTest("toIntTest", toIntTest());
        printTest("toBinaryStringTest", toBinaryStringTest());
        printTest("toHexStringTest", toHexStringTest());

        printFinalSummary();
    }

    // Rational Number Tests

    private boolean binaryNumberInterfaceTest() {
        boolean success = true;

        try {
            BinaryNumberInterface r = new BinaryNumber(5);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean intConstructorTest() {
        boolean success = true;
        
        try {
            BinaryNumber b = new BinaryNumber(5);
            if (!(b.toInt() == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }
    
    private boolean stringConstructorTest() {
        boolean success = true;
        
        try {
            BinaryNumber b = new BinaryNumber("0b000000101");
            if (!(b.toInt() == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }
    
    private boolean negativeStringConstructorTest() {
        boolean success = true;
        
        try {
            BinaryNumber b = new BinaryNumber("0b111111111");
            if (!(b.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }
    
    private boolean addImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            x.add(y);
            if (!(x.toInt() == 5) || !(y.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean subtractImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            x.subtract(y);
            if (!(x.toInt() == 5) || !(y.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean negateImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            x.negate();
            if (!(x.toInt() == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean shiftLeftImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            x.shiftLeft();
            if (!(x.toInt() == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean uShiftRightImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            x.uShiftRight();
            if (!(x.toInt() == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean andImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            x.and(y);
            if (!(x.toInt() == 5) || !(y.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean orImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            x.or(y);
            if (!(x.toInt() == 5) || !(y.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean xorImmutabilityTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            x.xOr(y);
            if (!(x.toInt() == 5) || !(y.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean addTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            BinaryNumber result = x.add(y);
            if (!(result.toInt() == 4)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean subtractTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            BinaryNumber result = x.subtract(y);
            if (!(result.toInt() == 6)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean negateTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber result = x.negate();
            if (!(result.toInt() == -5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean shiftLeftTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber result = x.shiftLeft();
            if (!(result.toInt() == 10)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean uShiftRightTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber result = x.uShiftRight();
            if (!(result.toInt() == 2)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean andTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            BinaryNumber result = x.and(y);
            if (!(result.toInt() == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean orTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            BinaryNumber result = x.or(y);
            if (!(result.toInt() == -1)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean xorTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            BinaryNumber y = new BinaryNumber("0b111111111");
            BinaryNumber result = x.xOr(y);
            if (!(result.toInt() == -6)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean toIntTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            int result = x.toInt();
            if (!(result == 5)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean toBinaryStringTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber("0b000000101");
            String result = x.toBinaryString();
            if (!(result.equals("0b000000101")) && !(result.equals("000000101"))) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean toHexStringTest() {
        boolean success = true;
        
        try {
            BinaryNumber x = new BinaryNumber(216);
            String result = x.toHexString();
            if (!(result.equals("0xD8")) && !(result.equals("D8"))) success = false;
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