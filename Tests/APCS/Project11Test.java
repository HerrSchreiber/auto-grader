import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 11 - Fractions
 * @author Rob Schreiber
 */
public class Project11Test {
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
    	Project11Test tester = new Project11Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("rationalNumberInterfaceTest", rationalNumberInterfaceTest());
        printTest("rationalNumberConstructorTest", rationalNumberConstructorTest());
        printTest("rationalNumberAddRationalNumberTest", rationalNumberAddRationalNumberTest());
        printTest("rationalNumberAddMixedNumberTest", rationalNumberAddMixedNumberTest());
        printTest("rationalNumberSubtractRationalNumberTest", rationalNumberSubtractRationalNumberTest());
        printTest("rationalNumberSubtractMixedNumberTest", rationalNumberSubtractMixedNumberTest());
        printTest("rationalNumberMultiplyRationalNumberTest", rationalNumberMultiplyRationalNumberTest());
        printTest("rationalNumberMultiplyMixedNumberTest", rationalNumberMultiplyMixedNumberTest());
        printTest("rationalNumberDivideRationalNumberTest", rationalNumberDivideRationalNumberTest());
        printTest("rationalNumberDivideMixedNumberTest", rationalNumberDivideMixedNumberTest());
        printTest("rationalNumberConvertToMixedTest", rationalNumberConvertToMixedTest());
        printTest("rationalNumberToStringTest", rationalNumberToStringTest());

        printTest("mixedNumberInterfaceTest", mixedNumberInterfaceTest());
        printTest("mixedNumberConstructorTest", mixedNumberConstructorTest());
        printTest("mixedNumberAddRationalNumberTest", mixedNumberAddRationalNumberTest());
        printTest("mixedNumberAddMixedNumberTest", mixedNumberAddMixedNumberTest());
        printTest("mixedNumberSubtractRationalNumberTest", mixedNumberSubtractRationalNumberTest());
        printTest("mixedNumberSubtractMixedNumberTest", mixedNumberSubtractMixedNumberTest());
        printTest("mixedNumberMultiplyRationalNumberTest", mixedNumberMultiplyRationalNumberTest());
        printTest("mixedNumberMultiplyMixedNumberTest", mixedNumberMultiplyMixedNumberTest());
        printTest("mixedNumberDivideRationalNumberTest", mixedNumberDivideRationalNumberTest());
        printTest("mixedNumberDivideMixedNumberTest", mixedNumberDivideMixedNumberTest());
        printTest("mixedNumberConvertToMixedTest", mixedNumberConvertToRationalTest());
        printTest("mixedNumberToStringTest", mixedNumberToStringTest());

        printFinalSummary();
    }

    // Rational Number Tests

    private boolean rationalNumberInterfaceTest() {
        boolean success = true;

        try {
            RationalNumberInterface r = new RationalNumber(22, 7);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberConstructorTest() {
        boolean success = true;
        
        try {
            RationalNumber r = new RationalNumber(22, 7);
            if (r.getNumerator() != 22 || r.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberAddRationalNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            RationalNumber result = r1.add(r2);
            if (result.getNumerator() != 29 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberAddMixedNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            RationalNumber result = r1.add(r2);
            if (result.getNumerator() != 29 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberSubtractRationalNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            RationalNumber result = r1.subtract(r2);
            if (result.getNumerator() != 15 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberSubtractMixedNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            RationalNumber result = r1.subtract(r2);
            if (result.getNumerator() != 15 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberMultiplyRationalNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            RationalNumber result = r1.multiply(r2);
            if (result.getNumerator() != 22 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberMultiplyMixedNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            RationalNumber result = r1.multiply(r2);
            if (result.getNumerator() != 22 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberDivideRationalNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            RationalNumber result = r1.divide(r2);
            if (result.getNumerator() != 22 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberDivideMixedNumberTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            RationalNumber result = r1.divide(r2);
            if (result.getNumerator() != 22 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberConvertToMixedTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            MixedNumber actualValue = r1.convertToMixed();
            MixedNumber expectedValue = new MixedNumber(3, 1, 7);
            if (expectedValue.getWhole() != actualValue.getWhole() || expectedValue.getNumerator() != actualValue.getNumerator() || expectedValue.getDenominator() != actualValue.getDenominator()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean rationalNumberToStringTest() {
        boolean success = true;
        
        try {
            RationalNumber r1 = new RationalNumber(22, 7);
            String actualValue = r1.toString();
            String expectedValue1 = "22/7";
            String expectedValue2 = "22 / 7";
            if (!expectedValue1.equals(actualValue) && !expectedValue2.equals(actualValue)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    // Mixed Number Tests

    private boolean mixedNumberInterfaceTest() {
        boolean success = true;

        try {
            MixedNumberInterface m = new MixedNumber(3, 1, 7);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberConstructorTest() {
        boolean success = true;
        
        try {
            MixedNumber r = new MixedNumber(0, 22, 7);
            if (r.getWhole() != 3 || r.getNumerator() != 1 || r.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberAddRationalNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            MixedNumber result = r1.add(r2);
            if (result.getWhole() != 4 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberAddMixedNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            MixedNumber result = r1.add(r2);
            if (result.getWhole() != 4 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberSubtractRationalNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            MixedNumber result = r1.subtract(r2);
            if (result.getWhole() != 2 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberSubtractMixedNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            MixedNumber result = r1.subtract(r2);
            if (result.getWhole() != 2 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberMultiplyRationalNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            MixedNumber result = r1.multiply(r2);
            if (result.getWhole() != 3 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberMultiplyMixedNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            MixedNumber result = r1.multiply(r2);
            if (result.getWhole() != 3 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberDivideRationalNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            RationalNumber r2 = new RationalNumber(14, 14);
            MixedNumber result = r1.divide(r2);
            if (result.getWhole() != 3 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberDivideMixedNumberTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            MixedNumber r2 = new MixedNumber(0, 14, 14);
            MixedNumber result = r1.divide(r2);
            if (result.getWhole() != 3 || result.getNumerator() != 1 || result.getDenominator() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberConvertToRationalTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            RationalNumber actualValue = r1.convertToRational();
            RationalNumber expectedValue = new RationalNumber(22, 7);
            if (expectedValue.getNumerator() != actualValue.getNumerator() || expectedValue.getDenominator() != actualValue.getDenominator()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean mixedNumberToStringTest() {
        boolean success = true;
        
        try {
            MixedNumber r1 = new MixedNumber(0, 22, 7);
            String actualValue = r1.toString();
            String expectedValue1 = "3 1/7";
            String expectedValue2 = "3 1 / 7";
            if (!expectedValue1.equals(actualValue) && !expectedValue2.equals(actualValue)) success = false;
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
