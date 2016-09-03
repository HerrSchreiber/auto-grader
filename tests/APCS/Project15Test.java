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
        printTest("deckConstructorTest", deckConstructorTest());
    	printTest("deckEmptyTest", deckEmptyTest());
    	printTest("deckSizeTest", deckSizeTest());
    	printTest("deckShuffleTest", deckShuffleTest());

        printFinalSummary();

        
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
                
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }
    
    private boolean deckEmptyTest() {
        boolean success = true;
        
        try {
            String[] ranks = {"1", "2"};
            String[] suits = {"red", "blue"};
            int[] points = {2, 4};
            Deck deck = new Deck(ranks, suits, points); 
            for (int i = deck.size(); i > 0; i--) {
                deck.deal();
            }
            if(!deck.isEmpty()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean deckSizeTest() {
        boolean success = true;
        
        try {
            String[] ranks = {"1", "2"};
            String[] suits = {"red", "blue"};
            int[] points = {2, 4};
            Deck deck = new Deck(ranks, suits, points); 
    	    for(int i = ranks.length*suits.length; i > 0; i--) {
    	        if(i != deck.size()) success = false;
                deck.deal();
    	    }
    	    if(0 != deck.size()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    
    private boolean deckShuffleTest() {
        boolean success = false;
        
        try {
            String[] ranks = {"1", "2"};
            String[] suits = {"red", "blue"};
            int[] points = {2, 4};
            Deck deck = new Deck(ranks, suits, points); 
    	    Card isThisYourCard = deck.deal();
    	    for(int i = 0; i < 100; i++) {
                deck.shuffle();
                Card tempCard = deck.deal();
                if (!tempCard.matches(isThisYourCard)) {
                    success = true;
                    break;
                }
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
