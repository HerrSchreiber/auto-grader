import java.io.*;
import java.util.*;

/**
 * Test for Project 4 - Binary Search Symbol Table
 * @author Rob Schreiber
 */
public class Project4Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	private boolean sectionPass;
	
	private static final double TOLERANCE = Math.pow(10, -14);

	/** @param args not used */
	public static void main(String[] args) {
		Project4Test tester = new Project4Test(); //to avoid every method being static
		tester.runTests();
	}
	
	/** Run tests on GridMonitor constructor and expected methods */
	private void runTests() {
		//////////////////////////////////////////////////////////////////
		// run tests on all interface methods to confirm correct results
		// and behavior under normal and exceptional use cases
		//////////////////////////////////////////////////////////////////
	    
	    // TESTS THAT STILL NEED COVERAGE
	    // put(Double key, Value v);
	    // get(Double key);
	    // delete(Double key);
	    // contains(Double key);
	    // isEmpty();
	    // size();
	    // min();
	    // max();
	    // floor(Double key);
	    // ceiling(Double key);
	    // rank(Double key);
	    // select(int rank);
	    // deleteMin();
	    // deleteMax();
	    // size(Double keyLo, Double keyHi);
	    // keys(Double keyLo, Double keyHi);
	    // keys();

		
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
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

	/**
	 * Generates a random array of Integers of the specified length
	 * @param  length The length of the generated array
	 * @return        The generated array of random Integers
	 */
	private Integer[] generateRandomArray(int length) {
		Random rdm = new Random();
		Integer[] ary = new Integer[length];
		for (int i = 0; i < ary.length; i++) {
			ary[i] = rdm.nextInt();
		}
		return ary;
	}
}