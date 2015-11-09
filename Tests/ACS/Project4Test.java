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
	
	/** Run tests */
	private void runTests() {
		//////////////////////////////////////////////////////////////////
		// run tests on all interface methods to confirm correct results
		// and behavior under normal and exceptional use cases
		//////////////////////////////////////////////////////////////////
	    
	    printTest("putGetTest",putGetTest());
	    printTest("deleteTest",deleteTest());
	    printTest("containsTest",containsTest());
	    printTest("isEmptyTest",isEmptyTest());
	    printTest("sizeTest",sizeTest());
	    printTest("minTest",minTest());
	    printTest("maxTest",maxTest());
	    printTest("floorTest",floorTest());
	    printTest("ceilingTest",ceilingTest());
	    printTest("rankTest",rankTest());
	    printTest("selectTest",selectTest());
	    printTest("deleteMinTest",deleteMinTest());
	    printTest("deleteMaxTest",deleteMaxTest());
	    printTest("sizeWithKeysTest",sizeWithKeysTest());
	    printTest("keysWithKeysTest",keysWithKeysTest());
	    printTest("keysTest",keysTest());

		
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
	}

	private boolean putGetTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			for (int i = 0; i < 10; i++) {
				if(ist.get((double)ary[i]) != i) success = false;
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}	

	private boolean deleteTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			ist.delete((double)ary[2]);
			if (ist.size() != 9) success = false;
			if (ist.get((double)ary[2]) != null) success = false;
			

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean containsTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			for (int i = 0; i < 10; i++) {
				if(ist.contains((double)ary[i]) != true) success = false;
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
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			if (!ist.isEmpty()) success = false;
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			if (ist.isEmpty()) success = false;
			
			

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean sizeTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				if (ist.size() != i) success = false;
				ist.put((double)ary[i], i);
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
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			if (!ist.min().equals((double)ary[0])) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean maxTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			if (!ist.max().equals((double)ary[9])) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean floorTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Double> ist = new InterpolationSymbolTable<Double>();
			for (int i = 0; i < 10; i++) {
				if (ist.size() != i) success = false;
				ist.put(i+0.5, i);
			}
			if (!ist.floor(7).equals(6.5)) success = false;
			if (!ist.floor(7.5).equals(7.5)) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean ceilingTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Double> ist = new InterpolationSymbolTable<Double>();
			for (int i = 0; i < 10; i++) {
				if (ist.size() != i) success = false;
				ist.put(i+0.5, i);
			}
			if (!ist.ceiling(7).equals(7.5)) success = false;
			if (!ist.ceiling(7.5).equals(7.5)) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean rankTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			for (int i = 0; i < 10; i++) {
				if (ist.rank((double)ary[i]) != i) success = false;
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean selectTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			for (int i = 0; i < 10; i++) {
				if (!ist.select(i).equals((double)ary[i])) success = false;
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean deleteMinTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)(double)ary[i], i);
			}
			ist.deleteMin();
			Arrays.sort(ary);
			if (ist.get((double)ary[0]) != null) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean deleteMaxTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			ist.deleteMax();
			Arrays.sort(ary);
			if (ist.get((double)ary[9]) != null) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean sizeWithKeysTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			if (ist.size((double)ary[2], (double)ary[6]) != 5) success = false;

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean keysWithKeysTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			Iterable<Integer> keys = ist.keys(2, 6);
			int i = 2;
			for (Integer key : keys) {
				if (!key.equals((double)ary[i])) success = false;
				i++;
			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	private boolean keysTest() {
		boolean success = true;
		
		try {
			InterpolationSymbolTable<Integer> ist = new InterpolationSymbolTable<Integer>();
			Integer[] ary = generateRandomArray(10);
			for (int i = 0; i < 10; i++) {
				ist.put((double)ary[i], i);
			}
			Arrays.sort(ary);
			Iterable<Integer> keys = ist.keys();
			int i = 0;
			for (Integer key : keys) {
				if (!key.equals((double)ary[i])) success = false;
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
