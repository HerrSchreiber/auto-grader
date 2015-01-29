import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 15 - Card and Deck
 * @author Rob Schreiber
 */
public class Project15Test {
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
    	Project15Test tester = new Project15Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("cardConstructorTest", cardConstructorTest());
        printTest("cardMatchTest", cardMatchTest());
        printTest("cardToStringTest", cardToStringTest());

        
    }

    // Card Tests


    private boolean cardConstructorTest() {
        boolean success = true;
        
        try {
            Card c = new Card("jack", "hearts", 7);
            if (!c.rank().equals("jack") || !c.suit().equals("hearts") || c.pointValue() != 7) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean cardMatchTest() {
        boolean success = true;
        
        try {
            Card c1 = new Card("jack", "hearts", 7);
            Card c2 = new Card("jack", "hearts", 7);
            Card c3 = new Card("5", "spades", 7);
            if (!c1.matches(c2) || c1.matches(c3)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean cardToStringTest() {
        boolean success = true;
        
        try {
            Card c1 = new Card("jack", "hearts", 7);
            String actualValue = c1.toString();
            if (!actualValue.contains("jack") || !actualValue.contains("hearts") || !actualValue.contains("7") || !actualValue.contains("point")) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }


    // Deck tests


    private boolean deckConstructorTest() {
        boolean success = true;
        
        try {
            String[] ranks = {"1", "2"};
            String[] suits = {"red", "blue"};
            int[] points = {2, 4};
            for (String r : ranks) {
                for (String s : suits) {
                    Deck deck = new Deck(ranks, suits, points);
                    boolean loopSuccess = false;
                    for (int i = 0; i < ranks.length * suits.length; i++) {
                        Card tempCard = deck.deal();
                        if (tempCard.matches(new Card(r, s, Integer.parseInt(r) * 2))) loopSuccess = true;
                    }
                    if (!loopSuccess) success = false;
                    if (!deck.isEmpty()) success = false;
                }
            }
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