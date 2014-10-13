import java.io.FileNotFoundException;

/**
 * Console app to test GridMonitor class. Needs tests for more than one input scenario.
 * @author mvail
 */
public class Project1Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	
	private static final double TOLERANCE = Math.pow(10, -14);

	/** @param args not used */
	public static void main(String[] args) {
		Project1Test tester = new Project1Test(); //to avoid every method being static
		tester.runTests();
	}
	
	/** Run tests on GridMonitor constructor and expected methods */
	private void runTests() {
		//////////////////////////////////////////////////////////////////
		// run tests on all interface methods to confirm correct results
		// and behavior under normal and exceptional use cases
		//////////////////////////////////////////////////////////////////
		printTest("gridMonitorInterfaceTest", gridMonitorInterfaceTest());

		//tests using a 3x3 sample grid
		printTest("loadSampleTest", loadSampleTest());
		printTest("getBaseGridSampleTest", getBaseGridSampleTest());
		printTest("getSurroundingSumGridSampleTest", getSurroundingSumGridSampleTest());
		printTest("getSurroundingAvgGridSampleTest", getSurroundingAvgGridSampleTest());
		printTest("getDeltaGridSampleTest", getDeltaGridSampleTest());
		printTest("getDangerGridSampleTest", getDangerGridSampleTest());
		printTest("lameToStringTest", lameToStringTest());

		//test encapsulation from get___() methods
		printTest("getBaseEncapsulationTest", getBaseGridEncapsulationTest());
		printTest("getSurroundingSumGridEncapsulationTest", getSurroundingSumGridEncapsulationTest());
		printTest("getSurroundingAvgGridEncapsulationTest", getSurroundingAvgGridEncapsulationTest());
		
		//tests to confirm expected Exceptions
		printTest("noSuchFileTest", noSuchFileTest());

		//TODO: additional tests using other input files
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
	}
		
	/** Confirm that GridMonitor implements GridMonitorInterface */
	@SuppressWarnings("unused")
	private boolean gridMonitorInterfaceTest() {
		boolean success = true;
		
		try {
			GridMonitor grid = null;
			//if GridMonitor does not implement GridMonitorInterface, the following
			// invalid assignment will prevent the test class from even compiling
			GridMonitorInterface gridInterface = grid;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}
	
	/////////////////////////////
	// Tests using "sample.txt"
	/////////////////////////////

	/** Call GridMonitor constructor with "sample.txt" */
	@SuppressWarnings("unused")
	private boolean loadSampleTest() {
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {			
			GridMonitor grid = new GridMonitor(testFile); //should load without Exception
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Compare grid from getBaseGrid() to expected grid from "sample.txt" */
	private boolean getBaseGridSampleTest() {
		final double[][] expected = {
			{2, 10, 7},
			{4, 5, 8},
			{5, 6, 9}};
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {			
			GridMonitor grid = new GridMonitor(testFile);

			if(!equivalent2DArrays(expected, grid.getBaseGrid())) {
				success = false;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Compare result from getSurroundingSumGrid() to expected grid for "sample.txt" */
	private boolean getSurroundingSumGridSampleTest() {
		final double[][] expected = {
				{18, 24, 32},
				{16, 28, 29},
				{20, 25, 32}};
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {			
			GridMonitor grid = new GridMonitor(testFile);
			
			double[][] returned = grid.getSurroundingSumGrid();
			if(!equivalent2DArrays(expected, returned)) {
				success = false;
				System.out.println("Expected :");
				print2DArray(expected);
				System.out.println("Returned :");
				print2DArray(returned);
			}
		} catch (Exception e) {
			System.out.println("getSurroundingSumGridSampleTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Compare result from getSurroundingAvgGrid() to expected grid for "sample.txt" */
	private boolean getSurroundingAvgGridSampleTest() {
		final double[][] expected = {
				{4.5, 6.0, 8.0},
				{4.0, 7.0, 7.25},
				{5.0, 6.25, 8.0}};
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {
			GridMonitor grid = new GridMonitor(testFile);
			
			double[][] returned = grid.getSurroundingAvgGrid();
			if(!equivalent2DArrays(expected, returned)) {
				success = false;
				System.out.println("Expected :");
				print2DArray(expected);
				System.out.println("Returned :");
				print2DArray(returned);
			}
		} catch (Exception e) {
			System.out.println("getSurroundingAvgGridSampleTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Compare result from getDeltaGrid() to expected grid for "sample.txt" */
	private boolean getDeltaGridSampleTest() {
		final double[][] expected = {
				{2.25, 3.0, 4.0},
				{2.0, 3.5, 3.625},
				{2.5, 3.125, 4.0}};
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {
			GridMonitor grid = new GridMonitor(testFile);
			
			double[][] returned = grid.getDeltaGrid();
			if(!equivalent2DArrays(expected, returned)) {
				success = false;
				System.out.println("Expected :");
				print2DArray(expected);
				System.out.println("Returned :");
				print2DArray(returned);
			}
		} catch (Exception e) {
			System.out.println("getDeltaGridSampleTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Compare result from getDangerGrid() to expected grid for "sample.txt" */
	private boolean getDangerGridSampleTest() {
		final boolean[][] expected = {
				{true, true, false},
				{false, false, false},
				{false, false, false}};
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {
			GridMonitor grid = new GridMonitor(testFile);
			
			boolean[][] returned = grid.getDangerGrid();
			if(!equivalent2DArrays(expected, returned)) {
				success = false;
				System.out.println("Expected :");
				print2DArray(expected);
				System.out.println("Returned :");
				print2DArray(returned);
			}
		} catch (Exception e) {
			System.out.println("getDangerGridSampleTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Exercise toString() after loading "sample.txt" 
	 *    testing toString() is a little challenging because unit tests should
	 *    be automated such that no user evaluation is necessary to determine
	 *    if a test has succeeded or failed - need to compare toString() output
	 *    to expected output */
	private boolean lameToStringTest() {
		final String testFile = "sample.txt";
		boolean success = true;
		
		try {			
			GridMonitor grid = new GridMonitor(testFile);

			String str = grid.toString();
			System.out.printf("toString() output:\n%s\n", str);
			if(str.length() == 0 || str.startsWith("GridMonitor@")) {
				success = false;
			}
		} catch (Exception e) {
			System.out.println("lameToStringTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** check that a copy of base grid is being returned by getBaseGrid() */
	private boolean getBaseGridEncapsulationTest() {
		final double[][] expected = {
				{2, 10, 7},
				{4, 5, 8},
				{5, 6, 9}};
		final String testFile = "sample.txt";
		boolean success = true;

		try {			
			GridMonitor grid = new GridMonitor(testFile);
			double[][] bm = grid.getBaseGrid();
			bm[0][0] = 555;

			if(!equivalent2DArrays(expected, grid.getBaseGrid())) {
				success = false;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}
	
	/** get sum grid, make a change via returned reference, confirm original is unchanged */
	private boolean getSurroundingSumGridEncapsulationTest() {
		final double[][] expected = {
				{18, 24, 32}, 
				{16, 28, 29}, 
				{20, 25, 32}};
		final String testFile = "sample.txt";
		boolean success = true;

		try {			
			GridMonitor grid = new GridMonitor(testFile);
			double[][] sem = grid.getSurroundingSumGrid();
			sem[0][0] = 555;

			double[][] returned = grid.getSurroundingSumGrid();
			if(!equivalent2DArrays(expected, returned)) {
				success = false;
				System.out.println("Expected :");
				print2DArray(expected);
				System.out.println("Returned :");
				print2DArray(returned);
			}
		} catch (Exception e) {
			System.out.println("getSurroundingSumGridEncapsulationTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}
	
	/** get avg grid, make a change via returned reference, confirm original is unchanged */
	private boolean getSurroundingAvgGridEncapsulationTest() {
		final double[][] expected = {
				{4.5, 6.0, 8.0},
				{4.0, 7.0, 7.25},
				{5.0, 6.25, 8.0}};
		final String testFile = "sample.txt";
		boolean success = true;

		try {			
			GridMonitor grid = new GridMonitor(testFile);
			double[][] sem = grid.getSurroundingAvgGrid();
			sem[0][0] = 555;

			double[][] returned = grid.getSurroundingAvgGrid();
			if(!equivalent2DArrays(expected, returned)) {
				success = false;
				System.out.println("Expected :");
				print2DArray(expected);
				System.out.println("Returned :");
				print2DArray(returned);
			}
		} catch (Exception e) {
			System.out.println("getSurroundingAvgGridEncapsulationTest: no Exception expected");
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}
	
	/////////////////////////////////////
	// tests using invalid input files
	/////////////////////////////////////

	/** Confirm that FileNotFoundException is thrown when GridMonitor constructor is called with "noSuch.txt" */
	@SuppressWarnings("unused")
	private boolean noSuchFileTest() {
		final String testFile = "noSuch.txt";
		boolean success = true;
		
		try {			
			GridMonitor grid = new GridMonitor(testFile);
			System.out.println("noSuchFileTest: FileNotFoundException expected");
			success = false;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {	//We expect a specific exception
			System.out.println("noSuchFileTest: FileNotFoundException expected");
			e.printStackTrace(System.out);
			success = false;
		}
		System.out.println();
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

	/** Compare two two-dimensional double arrays for equivalence.
	 * @param a1 first double[][]
	 * @param a2 second double[][]
	 * @return true if all values in a1 and a2 are within the given tolerance, else false
	 */
	private boolean equivalent2DArrays(double[][] a1, double[][] a2) {
		boolean equivalent = true;
		if (a1.length != a2.length || (a1.length > 1 && a1[0].length != a2[0].length)) {
			equivalent = false;
		} else {
			for (int row = 0; row < a1.length; row++) {
				for (int col = 0; col < a1[0].length; col++) {
					if (row >= a2.length || a1[row].length != a2[row].length) {
						equivalent = false;
					} else {
						if (!isClose(a1[row][col], a2[row][col])) {
							equivalent = false;
						}
					}
				}
			}
		}
		return equivalent;
	}
	
	/** Compare two two-dimensional boolean arrays for equivalence.
	 * @param a1 first boolean[][]
	 * @param a2 second boolean[][]
	 * @return true if all values in a1 and a2 are identical, else false
	 */
	private boolean equivalent2DArrays(boolean[][] a1, boolean[][] a2) {
		boolean equivalent = true;
		if (a1.length != a2.length || (a1.length > 1 && a1[0].length != a2[0].length)) {
			equivalent = false;
		} else {
			for (int row = 0; row < a1.length; row++) {
				for (int col = 0; col < a1[0].length; col++) {
					if (row >= a2.length || a1[row].length != a2[row].length) {
						equivalent = false;
					} else {
						if (a1[row][col] != a2[row][col]) {
							equivalent = false;
						}
					}
				}
			}
		}
		return equivalent;
	}
	
	/** Output a 2D double array in tabular format
	 * @param array 2D double array to print */
	private void print2DArray(double[][] array) {
		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				System.out.printf("%14.3f ", array[row][col]);
			}
			System.out.println();
		}
	}

	/** Output a 2D boolean array in tabular format
	 * @param array 2D boolean array to print */
	private void print2DArray(boolean[][] array) {
		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				System.out.printf("%-5b ", array[row][col]);
			}
			System.out.println();
		}
	}
}