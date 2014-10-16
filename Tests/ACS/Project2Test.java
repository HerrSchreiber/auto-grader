import java.io.FileNotFoundException;

/**
 * Console app to test GridMonitor class. Needs tests for more than one input scenario.
 * @author mvail
 */
public class Project2Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	
	private static final double TOLERANCE = Math.pow(10, -14);

	/** @param args not used */
	public static void main(String[] args) {
		Project2Test tester = new Project2Test(); //to avoid every method being static
		tester.runTests();
	}
	
	/** Run tests on GridMonitor constructor and expected methods */
	private void runTests() {
		//////////////////////////////////////////////////////////////////
		// run tests on all interface methods to confirm correct results
		// and behavior under normal and exceptional use cases
		//////////////////////////////////////////////////////////////////
		printTest("ArrayQueueInterfaceTest", ArrayQueueInterfaceTest());
		printTest("SLLStackInterfaceTest", SLLStackInterfaceTest());

		
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
	}
		
	/** Confirm that ArrayQueue implements ArrayQueueInterface */
	private boolean ArrayQueueInterfaceTest() {
		boolean success = true;
		
		try {
			ArrayQueue aq = new ArrayQueue();
			if (! (aq instanceof ArrayQueueInterface)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	/** Confirm that SLLStack implements SLLStackInterface */
	private boolean SLLStackInterfaceTest() {
		boolean success = true;
		
		try {
			SLLStack st = new SLLStack();
			if (! (st instanceof SLLStackInterface)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean EmptyStackTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean OneElementStackTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean MultiElementStackTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean EmptyQueueTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean OneElementQueueTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean MultiElementQueueTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean pushMiniTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean popMiniTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean peekMiniTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean enqueueMiniTest() {
		boolean success = true;

		try {

		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean dequeueMiniTest() {
		boolean success = true;

		try {

		}	 catch (Exception e) {
			e.printStackTrace();
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
}