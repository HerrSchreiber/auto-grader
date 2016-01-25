import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 16 - Board and ElevensBoard
 * @author Rob Schreiber
 */
public class Project16Test {
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
    	Project16Test tester = new Project16Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

    	printTest("elevensBoardInheritanceTest", elevensBoardInheritanceTest());
    	printTest("boardAbstractTest", boardAbstractTest());
    	printTest("elevensBoardConstructorTest", elevensBoardConstructorTest());
    	printTest("elevensBoardNewGameTest", elevensBoardNewGameTest());
    	printTest("elevensBoardDeckSizeTest", elevensBoardDeckSizeTest());
    	printTest("elevensBoardIsEmptyTest", elevensBoardIsEmptyTest());
        printTest("elevensBoardCardIndexesTest", elevensBoardCardIndexesTest());
        printTest("elevensBoardGameIsWonTest", elevensBoardGameIsWonTest());
        printTest("elevensBoardAnotherPlayTest", elevensBoardAnotherPlayTest());

        printFinalSummary();

               
    }

	private boolean elevensBoardInheritanceTest() {
        boolean success = true;
        
        try {
            Board board = new ElevensBoard();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }    



    // Board Tests


    private boolean boardAbstractTest() {
        boolean success = true;
        
        try {
            Class boardClass = Board.class;
            int modifiers = boardClass.getModifiers();
            if (!Modifier.isAbstract(modifiers)) success = false;

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }


    // ElevensBoard Tests

    private boolean elevensBoardConstructorTest() {
        boolean success = true;
        
        try {
            ElevensBoard eb = new ElevensBoard();
            if (eb.cardAt(0) == null) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean elevensBoardNewGameTest() {
        boolean success = false;
        
        try {
        	for (int i = 0; i < 10; i++) {
	            ElevensBoard eb = new ElevensBoard();
	            Card c1 = eb.cardAt(0);
	            Card c2 = eb.cardAt(1);
	            eb.newGame();
	            if (!(c1.matches(eb.cardAt(0)) && c2.matches(eb.cardAt(1)))) {
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

    private boolean elevensBoardDeckSizeTest() {
    	boolean success = true;
        
        try {
        	ElevensBoard eb = new ElevensBoard();
        	for (int i = 43; i > 0; i--) {
        		if (i != eb.deckSize()) {
        			success = false;
        			break;
        		}
        		eb.deal(0);
        	}
        	if (eb.deckSize() != 0) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean elevensBoardIsEmptyTest() {
    	boolean success = true;
        
        try {
        	ElevensBoard eb = new ElevensBoard();
        	for (int i = 52; i > -1; i--) {
                if (i < 8) {
        			eb.deal(8-i);
        		}
        		else {
        			eb.deal(0);
        		}
        	}
        	if (!eb.isEmpty()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean elevensBoardCardIndexesTest() {
        boolean success = true;
        
        try {
            ElevensBoard eb = new ElevensBoard();
            for (int i = 52; i > -1; i--) {
                if (i < 8) {
                    eb.deal(8-i);
                    if (eb.cardIndexes().size() != i) success = false;
                }
                else {
                    eb.deal(0);
                }
            }
            if (eb.cardIndexes().size() != 0) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean elevensBoardGameIsWonTest() {
        boolean success = true;
        
        try {
            ElevensBoard eb = new ElevensBoard();
            for (int i = 52; i > -1; i--) {
                if (i < 8) {
                    eb.deal(8-i);
                }
                else {
                    eb.deal(0);
                }
            }
            if (!eb.gameIsWon()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean elevensBoardAnotherPlayTest() {
        boolean success = true;
        ArrayList<Integer> selectedCards = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) selectedCards.add(i);
        try {
            ElevensBoard eb;

            // 11-pair test
            for (int i = 0; i < 20; i++) {
                eb = new ElevensBoard();
                boolean has11Pair = false;
                for (int sk1 = 0; sk1 < selectedCards.size(); sk1++) {
                    int k1 = selectedCards.get(sk1).intValue();
                    for (int sk2 = sk1 + 1; sk2 < selectedCards.size(); sk2++) {
                        int k2 = selectedCards.get(sk2).intValue();
                        if (eb.cardAt(k1).pointValue() + eb.cardAt(k2).pointValue() == 11) {
                            has11Pair = true;
                        }
                    }
                }
                if (has11Pair) {
                    if (!eb.anotherPlayIsPossible()) success = false;
                    break;
                }
            }
            
            // JQK test
            for (int i = 0; i < 20; i++) {
                eb = new ElevensBoard();
                boolean hasJQK = false;
                boolean foundJack = false;
                boolean foundQueen = false;
                boolean foundKing = false;
                for (Integer kObj : selectedCards) {
                    int k = kObj.intValue();
                    if (eb.cardAt(k).rank().equals("jack")) {
                        foundJack = true;
                    } else if (eb.cardAt(k).rank().equals("queen")) {
                        foundQueen = true;
                    } else if (eb.cardAt(k).rank().equals("king")) {
                        foundKing = true;
                    }
                }
                hasJQK = foundJack && foundQueen && foundKing;
                if (hasJQK) {
                    if (!eb.anotherPlayIsPossible()) success = false;
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
