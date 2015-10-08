import java.io.*;
import java.util.*;

/**
 * Test for Project 3 - Sorting algorithms
 * @author Rob Schreiber
 */
public class Project3Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	private boolean sectionPass;
	
	private static final double TOLERANCE = Math.pow(10, -14);

	/** @param args not used */
	public static void main(String[] args) {
		Project3Test tester = new Project3Test(); //to avoid every method being static
		tester.runTests();
	}
	
	/** Run tests on GridMonitor constructor and expected methods */
	private void runTests() {
		//////////////////////////////////////////////////////////////////
		// run tests on all interface methods to confirm correct results
		// and behavior under normal and exceptional use cases
		//////////////////////////////////////////////////////////////////
		printTest("mergeSortComparableTest", mergeSortComparableTest());
		printTest("mergeSortComparatorTest", mergeSortComparatorTest());
		printTest("heapSortComparableTest", heapSortComparableTest());
		printTest("heapSortComparatorTest", heapSortComparatorTest());
		printTest("insertionSortComparableTest", insertionSortComparableTest());
		printTest("insertionSortComparatorTest", insertionSortComparatorTest());
		printTest("quickSortComparableTest", quickSortComparableTest());
		printTest("quickSortComparatorTest", quickSortComparatorTest());
		printTest("selectionSortComparableTest", selectionSortComparableTest());
		printTest("selectionSortComparatorTest", selectionSortComparatorTest());
		printTest("shellSortComparableTest", shellSortComparableTest());
		printTest("shellSortComparatorTest", shellSortComparatorTest());

		
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
	}
		
	private boolean mergeSortComparableTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			MergeSort.sort(ary);
			if (! isSortedComparable(ary)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean mergeSortComparatorTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ComparableReverseComparator<Integer> c = new ComparableReverseComparator<Integer>();
			MergeSort.sort(ary, c);
			if (! isSortedComparator(ary, c)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean heapSortComparableTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			HeapSort.sort(ary);
			if (! isSortedComparable(ary)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean heapSortComparatorTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ComparableReverseComparator<Integer> c = new ComparableReverseComparator<Integer>();
			HeapSort.sort(ary, c);
			if (! isSortedComparator(ary, c)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean insertionSortComparableTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			InsertionSort.sort(ary);
			if (! isSortedComparable(ary)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean insertionSortComparatorTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ComparableReverseComparator<Integer> c = new ComparableReverseComparator<Integer>();
			InsertionSort.sort(ary, c);
			if (! isSortedComparator(ary, c)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean quickSortComparableTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			QuickSort.sort(ary);
			if (! isSortedComparable(ary)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean quickSortComparatorTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ComparableReverseComparator<Integer> c = new ComparableReverseComparator<Integer>();
			QuickSort.sort(ary, c);
			if (! isSortedComparator(ary, c)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean selectionSortComparableTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			SelectionSort.sort(ary);
			if (! isSortedComparable(ary)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean selectionSortComparatorTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ComparableReverseComparator<Integer> c = new ComparableReverseComparator<Integer>();
			SelectionSort.sort(ary, c);
			if (! isSortedComparator(ary, c)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean shellSortComparableTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ShellSort.sort(ary);
			if (! isSortedComparable(ary)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean shellSortComparatorTest() {
		boolean success = true;
		
		try {
			Integer[] ary = generateRandomArray(10);
			ComparableReverseComparator<Integer> c = new ComparableReverseComparator<Integer>();
			ShellSort.sort(ary, c);
			if (! isSortedComparator(ary, c)) success = false;
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

	/**
	 * Checks to see if the specified array of Integers is properly sorted
	 * @param  ary The array of integers to check
	 * @return     True if the array is sorted
	 */
	private boolean isSortedComparable(Integer[] ary) {
		for (int i = 0; i < ary.length - 1; i++) {
			if (ary[i].compareTo(ary[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks to see if the specified array of Integers is properly sorted
	 * according to the provided Comparator
	 * @param  ary The array of Integers to check
	 * @param  c   The comparator to check the sorting
	 * @return     True if the array is sorted
	 */
	private boolean isSortedComparator(Integer[] ary, Comparator<Integer> c) {
		for (int i = 0; i < ary.length - 1; i++) {
			if (c.compare(ary[i], ary[i + 1]) > 0) {
				return false;
			}
		}
		return true;
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