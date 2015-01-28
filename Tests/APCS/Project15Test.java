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

        deckTest();
        cardTest();

    }

    /**
     *  Checks the Deck operations for consistency.
     *  
     */
    public void deckTest() {
        // Note: Student solutions will vary.  The following is an example.

        String[] ranks = {"jack", "queen", "king"};
        String[] suits = {"blue", "red"};
        int[] pointValues = {11, 12, 13};
        Deck d = new Deck(ranks, suits, pointValues);

        System.out.println("**** Original Deck Methods ****");
        System.out.println("  toString:\n" + d.toString());
        System.out.println("  isEmpty: " + d.isEmpty());
        System.out.println("  size: " + d.size());
        System.out.println();
        System.out.println();

        System.out.println("**** Deal a Card ****");
        System.out.println("  deal: " + d.deal());
        System.out.println();
        System.out.println();

        System.out.println("**** Deck Methods After 1 Card Dealt ****");
        System.out.println("  toString:\n" + d.toString());
        System.out.println("  isEmpty: " + d.isEmpty());
        System.out.println("  size: " + d.size());
        System.out.println();
        System.out.println();

        System.out.println("**** Deal Remaining 5 Cards ****");
        for (int i = 0; i < 5; i++) {
            System.out.println("  deal: " + d.deal());
        }
        System.out.println();
        System.out.println();

        System.out.println("**** Deck Methods After All Cards Dealt ****");
        System.out.println("  toString:\n" + d.toString());
        System.out.println("  isEmpty: " + d.isEmpty());
        System.out.println("  size: " + d.size());
        System.out.println();
        System.out.println();

        System.out.println("**** Deal a Card From Empty Deck ****");
        System.out.println("  deal: " + d.deal());
        System.out.println();
        System.out.println();

        System.out.println("\n**** 52 Card Deck shuffle Tests ****");
        String[] ranks52 =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        String[] suits52 =
            {"spades", "hearts", "diamonds", "clubs"};
        int[] pointValues52 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Deck d52 = new Deck(ranks52, suits52, pointValues52);
        System.out.println("  After Creation:\n" + d52.toString());

        Card c = d52.deal();
        System.out.println("  After Dealing the " + c + ":\n" + d52.toString());

        for (int i = 1; i <= 3; i++) {
            d52.shuffle();
            System.out.println("  After " + i + " shuffle(s):\n" + d52.toString());
        }
    }

    /**
     *  Checks the Card operations for consistency.
     */
    public void cardTest() {
        // Note: Student solutions will vary.  The following is an example.

        Card aceClubs1 = new Card("ace", "clubs", 1);
        Card aceClubs2 = new Card("ace", "clubs", 1);
        Card sixHearts = new Card("6", "hearts", 6);

        System.out.println("**** ace of clubs #1 Tests ****");
        System.out.println("  rank: " + aceClubs1.rank());
        System.out.println("  suit: " + aceClubs1.suit());
        System.out.println("  pointValue: " + aceClubs1.pointValue());
        System.out.println("  toString: " + aceClubs1.toString());
        System.out.println();

        System.out.println("**** ace of clubs #2 Tests ****");
        System.out.println("  rank: " + aceClubs2.rank());
        System.out.println("  suit: " + aceClubs2.suit());
        System.out.println("  pointValue: " + aceClubs2.pointValue());
        System.out.println("  toString: " + aceClubs2.toString());
        System.out.println();

        System.out.println("**** six of hearts Tests ****");
        System.out.println("  rank: " + sixHearts.rank());
        System.out.println("  suit: " + sixHearts.suit());
        System.out.println("  pointValue: " + sixHearts.pointValue());
        System.out.println("  toString: " + sixHearts.toString());
        System.out.println();

        System.out.println("**** matches Tests ****");
        System.out.println("  matching: " + aceClubs1.matches(aceClubs2));
        System.out.println("  not matching: " + aceClubs1.matches(sixHearts));
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