import java.io.*;
import java.util.*;

/**
 * Test for Project 5 - Red-Black Binary Search Tree
 * @author Rob Schreiber
 */
public class Project5Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	private boolean sectionPass;
	
	private static final double TOLERANCE = Math.pow(10, -14);

	/** @param args not used */
	public static void main(String[] args) {
		Project5Test tester = new Project5Test(); //to avoid every method being static
		tester.runTests();
	}
	
	/** Run tests */
	private void runTests() {
		//////////////////////////////////////////////////////////////////
		// run tests on all interface methods to confirm correct results
		// and behavior under normal and exceptional use cases
		//////////////////////////////////////////////////////////////////
	    
	    printTest("putGetTest",putGetTest());
	    printTest("containsTest",containsTest());
	    printTest("isEmptyTest",isEmptyTest());
	    printTest("sizeTest",sizeTest());
	    printTest("minTest",minTest());
	    printTest("maxTest",maxTest());
	    printTest("keysTest",keysTest());

		
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
	}

	private boolean putGetTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				rbbst.put(ary[i], i);
			}
			for (int i = 0; i < 10; i++) {
				if(rbbst.get(ary[i]) != i) success = false;
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}	


	private boolean containsTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				rbbst.put(ary[i], i);
			}
			for (int i = 0; i < 10; i++) {
				if(rbbst.contains(ary[i]) != true) success = false;
			}
			

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}
	
	private boolean isEmptyTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			if (!rbbst.isEmpty()) success = false;
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				rbbst.put(ary[i], i);
			}
			if (rbbst.isEmpty()) success = false;
			
			

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean sizeTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				if (rbbst.size() != i) success = false;
				rbbst.put(ary[i], i);
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean minTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				rbbst.put(ary[i], i);
			}
			Arrays.sort(ary);
			if (!rbbst.min().equals(ary[0])) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean maxTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				rbbst.put(ary[i], i);
			}
			Arrays.sort(ary);
			if (!rbbst.max().equals(ary[9])) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}


	private boolean keysTest() {
		boolean success = true;
		
		try {
			RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<Integer, Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				rbbst.put(ary[i], i);
			}
			Arrays.sort(ary);
			Iterable<Double> keys = rbbst.keys();
			int i = 0;
			for (Double key : keys) {
				if (!key.equals(ary[i])) success = false;
				i++;
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}
	
	////////////////////////////////
	// utility methods for testing
	////////////////////////////////

	/** Print test results in a consrbbstent format
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
