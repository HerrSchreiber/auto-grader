import java.io.*;
import java.util.*;

/**
 * Console app to test GridMonitor class. Needs tests for more than one input scenario.
 * @author mvail
 */
public class Project2Test {
	private int passes = 0;
	private int failures = 0;
	private int total = 0;
	private boolean sectionPass;
	
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
		printTest("SLLStackInterfaceTest", SLLStackInterfaceTest());
		printTest("ArrayQueueInterfaceTest", ArrayQueueInterfaceTest());
		printTest("SLLStackBigOTest", SLLStackBigOTest());
		printTest("ArrayQueueBigOTest", ArrayQueueBigOTest());
		printTest("EmptyStackTest", EmptyStackTest());
		printTest("OneElementStackTest", OneElementStackTest());
		printTest("MultiElementStackTest", MultiElementStackTest());
		printTest("EmptyQueueTest", EmptyQueueTest());
		printTest("OneElementQueueTest", OneElementQueueTest());
		printTest("MultiElementQueueTest", MultiElementQueueTest());

		
		
		/////////////////
		//final verdict
		/////////////////
		printFinalSummary();
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
    
    /**
     * Confirms SLLStack operates at constant time
     */
	private boolean SLLStackBigOTest() {
		boolean success = true;
		try {
			SLLStack<Integer> st = new SLLStack<Integer>();
			double timeStart = System.nanoTime();
			for (int i = 0; i < 1000; i++) {
				st.push(i);
				st.pop(i);
			}
			double thousandTime = System.nanoTime() - timeStart;
			timeStart = System.nanoTime();
			for (int i = 0; i < 10000; i++) {
				st.push(i);
				st.pop(i);
			}
			double tenKTime = System.nanoTime() - timeStart;
			double thousandTimePerOp = thousandTime / 1000;
			double tenKTimePerOp = tenKTime / 10000;
			success = Math.abs((thousandTimePerOp / tenKTimePerOp) - 1) < 0.1;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}
    
    /**
     * Confirms ArrayQueue operates at constant time
     */
	private boolean ArrayQueueBigOTest() {
		boolean success = true;
		try {
			ArrayQueue<Integer> q = new ArrayQueue<Integer>();
			double timeStart = System.nanoTime();
			for (int i = 0; i < 1000; i++) {
				q.enqueue(i);
				q.dequeue(i);
			}
			double thousandTime = System.nanoTime() - timeStart;
			timeStart = System.nanoTime();
			for (int i = 0; i < 10000; i++) {
				q.enqueue(i);
				q.dequeue(i);
			}
			double tenKTime = System.nanoTime() - timeStart;
			double thousandTimePerOp = thousandTime / 1000;
			double tenKTimePerOp = tenKTime / 10000;
			success = Math.abs((thousandTimePerOp / tenKTimePerOp) - 1) < 0.1;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			success = false;
		}
		return success;
	}

	private boolean EmptyStackTest() {
		boolean success = true;
		sectionPass = true;

		try {
			SLLStack<String> st = new SLLStack<String>();
			printTest("pushMiniTest", pushMiniTest(st, "A", "A"));
			printTest("popMiniTest", popMiniTest(st, null, true));
			printTest("peekMiniTest", peekMiniTest(st, null, true));
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		success = sectionPass;
		return success;
	}

	private boolean OneElementStackTest() {
		boolean success = true;
		sectionPass = true;

		try {
			SLLStack<String> st = new SLLStack<String>();
			st.push("A");
			printTest("pushMiniTest", pushMiniTest(st, "B", "B"));
			printTest("popMiniTest", popMiniTest(st, "A", false));
			printTest("peekMiniTest", peekMiniTest(st, "A", false));
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		success = sectionPass;
		return success;
	}

	private boolean MultiElementStackTest() {
		boolean success = true;
		sectionPass = true;

		try {
			SLLStack<String> st = new SLLStack<String>();
			st.push("A");
			st.push("B");
			st.push("C");
			printTest("pushMiniTest", pushMiniTest(st, "D", "D"));
			printTest("popMiniTest", popMiniTest(st, "C", false));
			printTest("peekMiniTest", peekMiniTest(st, "C", false));
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		success = sectionPass;
		return success;
	}

	private boolean EmptyQueueTest() {
		boolean success = true;
		sectionPass = true;

		try {
			ArrayQueue<String> q = new ArrayQueue<String>();
			printTest("enqueueMiniTest", enqueueMiniTest(q, "A", "A"));
			printTest("dequeueMiniTest", dequeueMiniTest(q, null, true));
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		success = sectionPass;
		return success;
	}

	private boolean OneElementQueueTest() {
		boolean success = true;
		sectionPass = true;

		try {
			ArrayQueue<String> q = new ArrayQueue<String>();
			q.enqueue("A");
			printTest("enqueueMiniTest", enqueueMiniTest(q, "B", "A"));
			printTest("dequeueMiniTest", dequeueMiniTest(q, "A", false));
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		success = sectionPass;
		return success;
	}

	private boolean MultiElementQueueTest() {
		boolean success = true;
		sectionPass = true;

		try {
			ArrayQueue<String> q = new ArrayQueue<String>();
			q.enqueue("A");
			q.enqueue("B");
			q.enqueue("C");
			printTest("enqueueMiniTest", enqueueMiniTest(q, "D", "A"));
			printTest("dequeueMiniTest", dequeueMiniTest(q, "A", false));
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		success = sectionPass;
		return success;
	}

	private boolean pushMiniTest(SLLStack<String> st, String toAdd, String expected) {
		boolean success = true;

		try {
			st.push(toAdd);
			success = expected.equals(st.pop());
			if (!success) sectionPass = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			sectionPass = false;
		}
		return success;
	}

	private boolean popMiniTest(SLLStack st, String expected, boolean error) {
		boolean success = false;

		try {
			success = st.pop().equals(expected);
			if (!success) sectionPass = false;
		}
		catch (NoSuchElementException e) {
			if (error) success = true;
			else sectionPass = false;
		}
		catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean peekMiniTest(SLLStack st, String expected, boolean error) {
		boolean success = false;

		try {
			success = st.peek().equals(expected);
			if (!success) sectionPass = false;
		}
		catch (NoSuchElementException e) {
			if (error) success = true;
			else sectionPass = false;
		}
		catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private boolean enqueueMiniTest(ArrayQueue<String> q, String toAdd, String expected) {
		boolean success = true;

		try {
			q.enqueue(toAdd);
			success = expected.equals(q.dequeue());
			if (!success) sectionPass = false;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			sectionPass = false;
		}
		return success;
	}

	private boolean dequeueMiniTest(ArrayQueue<String> q, String expected, boolean error) {
		boolean success = false;

		try {
			success = q.dequeue().equals(expected);
			if (!success) sectionPass = false;
		}
		catch (NoSuchElementException e) {
			if (error) success = true;
			else sectionPass = false;
		}
		catch (Exception e) {
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