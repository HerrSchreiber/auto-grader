public class Project2Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	private static final double TOLERANCE = 1E-3;

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
		printTest("pointInstantiationTest", pointInstantiationTest());
		printTest("pointToStringTest", pointToStringTest());
		printTest("lineInstantiationTest", lineInstantiationTest());
		printTest("lineSlopeTest", lineSlopeTest());
		printTest("linePerpSlopeTest", linePerpSlopeTest());
		printTest("lineLengthTest", lineLengthTest());
		printTest("lineMidpointTest", lineMidpointTest());
		printTest("lineToStringTest", lineToStringTest());		
		//printTest("linePerpBisectorTest", linePerpBisectorTest());
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
	}

	private boolean pointInstantiationTest() {
		boolean success = true;
		
		try {
			Point p = new Point(1.5, 2.5);
			if (p.getX() != 1.5) success = false;
			if (p.getY() != 2.5) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean pointToStringTest() {
		boolean success = true;
		
		try {
			Point p = new Point(1.5, 2.5);
			String expected = "(1.5, 2.5)";
			if (!expected.equals(p.toString())) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean lineInstantiationTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l = new Line(p1, p2);
			if (l.getPointA() != p1) success = false;
			if (l.getPointB() != p2) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean lineSlopeTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l = new Line(p1, p2);
			if (!isClose(l.getSlope(), -1.125)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean linePerpSlopeTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l = new Line(p1, p2);
			if (!isClose(l.getPerpSlope(), 1/1.125)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean lineLengthTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l = new Line(p1, p2);
			if (!isClose(l.getLength(), Math.sqrt(36.25))) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean lineMidpointTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l = new Line(p1, p2);
			Point mp = l.getMidpoint();
			if (!isClose(mp.getX(), -0.5)) success = false;
			if (!isClose(mp.getY(), 4.75)) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean lineToStringTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l = new Line(p1, p2);
			if (!"(1.5, 2.5) -> (-2.5, 7.0)".equals(l.toString())) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}
	
	/*private boolean linePerpBisectorTest() {
		boolean success = true;
		
		try {
			Point p1 = new Point(1.5, 2.5);
			Point p2 = new Point(-2.5, 7);
			Line l1 = new Line(p1, p2);
			Line l2 = l1.getPerpBisector();
			if (!isClose(l2.getSlope(), 1/1.125)) success = false;
			if (!isClose(l2.getLength(), Math.sqrt(36.23))) success = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}*/

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