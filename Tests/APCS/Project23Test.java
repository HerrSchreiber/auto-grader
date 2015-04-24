import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 23 - ArrayStack &amp; SLLQueue
 * @author Rob Schreiber
 */
public class Project23Test {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = 1E-10;
    private String[] keywords = new String[100];
    private String[] responses = new String[100];

    /**
     * Calls the runTests method.
     * @param args Not used
     */
    public static void main(String[] args) {
    	Project23Test tester = new Project23Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////
        
        printTest("popNoSuchElementExceptionTest", popNoSuchElementExceptionTest());
        printTest("peekNoSuchElementExceptionTest", peekNoSuchElementExceptionTest());
        printTest("emptyStackPushTest", emptyStackPushTest());
        printTest("oneElementStackPushTest", oneElementStackPushTest());
        printTest("twoElementStackPushTest", twoElementStackPushTest());
        printTest("hundredElementStackPushTest", hundredElementStackPushTest());
        printTest("randomPushPopTest", randomPushPopTest());
        printTest("dequeueNoSuchElementExceptionTest", dequeueNoSuchElementExceptionTest());
        printTest("emptyQueueEnqueueTest", emptyQueueEnqueueTest());
        printTest("oneElementQueueEnqueueTest", oneElementQueueEnqueueTest());
        printTest("twoElementQueueEnqueueTest", twoElementQueueEnqueueTest());
        printTest("hundredElementQueueEnqueueTest", hundredElementQueueEnqueueTest());
        printTest("randomEnqueueDequeueTest", randomEnqueueDequeueTest());
        printTest("queueDepletionTest", queueDepletionTest());
    	
    }

    // ArrayStack Tests

	private boolean popNoSuchElementExceptionTest() {
        boolean success = true;
        
        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            stack.pop();
            success = false;
        }
        catch (NoSuchElementException e) {
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean peekNoSuchElementExceptionTest() {
        boolean success = true;
        
        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            stack.peek();
            success = false;
        }
        catch (NoSuchElementException e) {
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean emptyStackPushTest() {
        boolean success = true;

        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            stack.push(1);
            Integer peek = stack.peek();
            Integer pop = stack.pop();
            if (peek != 1 || pop != 1) {
                success = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean oneElementStackPushTest() {
        boolean success = true;

        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            stack.push(1);
            stack.push(2);
            Integer peek = stack.peek();
            Integer pop = stack.pop();
            if (peek != 2 || pop != 2) {
                success = false;
            }
            peek = stack.peek();
            pop = stack.pop();
            if (peek != 1 || pop != 1) {
                success = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean twoElementStackPushTest() {
        boolean success = true;

        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            Integer peek = stack.peek();
            Integer pop = stack.pop();
            if (peek != 3 || pop != 3) {
                success = false;
            }
            peek = stack.peek();
            pop = stack.pop();
            if (peek != 2 || pop != 2) {
                success = false;
            }
            peek = stack.peek();
            pop = stack.pop();
            if (peek != 1 || pop != 1) {
                success = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean hundredElementStackPushTest() {
        boolean success = true;

        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            
            for (int i = 0; i < 100; i++) {
                stack.push(i);
            }
            for (int i = 99; i > -1; i--) {
                Integer peek = stack.peek();
                Integer pop = stack.pop();
                if (peek != i || pop != i) {
                    success = false;
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean randomPushPopTest() {
        boolean success = true;

        try {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            Integer[] returnOrder = new Integer[5];
            stack.push(1);
            stack.push(2);
            returnOrder[0] = stack.pop();
            stack.push(3);
            stack.push(4);
            returnOrder[1] = stack.pop();
            returnOrder[2] = stack.pop();
            stack.push(5);
            returnOrder[3] = stack.pop();
            returnOrder[4] = stack.pop();

            Integer[] expectedOrder = {2, 4, 3, 5, 1};

            for (int i = 0; i < 5; i++) {
                if (returnOrder[i].intValue() != expectedOrder[i].intValue()) {
                    success = false;
                }
            }

            
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    // SLLQueue Tests
    
    private boolean dequeueNoSuchElementExceptionTest() {
        boolean success = true;
        
        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            queue.dequeue();
            success = false;
        }
        catch (NoSuchElementException e) {
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean emptyQueueEnqueueTest() {
        boolean success = true;

        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            queue.enqueue(1);
            if (queue.dequeue != 1) {
                success = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

    }

    private boolean oneElementQueueEnqueueTest() {
        boolean success = true;

        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            queue.enqueue(1);
            queue.enqueue(2);
            if (queue.dequeue != 1) {
                success = false;
            }
            if (queue.dequeue != 2) {
                success = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

    }

    private boolean twoElementQueueEnqueueTest() {
        boolean success = true;

        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            if (queue.dequeue != 1) {
                success = false;
            }
            if (queue.dequeue != 2) {
                success = false;
            }
            if (queue.dequeue != 3) {
                success = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

    }

    private boolean hundredElementQueueEnqueueTest() {
        boolean success = true;

        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            for (int i = 0; i < 100; i++) {
                queue.enqueue(i);
            }
            for (int i = 0; i < 100; i++) {
                if (queue.dequeue() != i) {
                    success = false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

    }

    private boolean randomEnqueueDequeueTest() {
        boolean success = true;

        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            Integer[] returnOrder = new Integer[5];
            queue.enqueue(1);
            queue.enqueue(2);
            returnOrder[0] = queue.dequeue();
            queue.enqueue(3);
            returnOrder[1] = queue.dequeue();
            returnOrder[2] = queue.dequeue();
            queue.enqueue(4);
            queue.enqueue(5);
            returnOrder[3] = queue.dequeue();
            returnOrder[4] = queue.dequeue();

            Integer[] expectedOrder = {1, 2, 3, 4, 5};

            for (int i = 0; i < 5; i++) {
                if (returnOrder[i].intValue() != expectedOrder[i].intValue()) {
                    success = false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

    }

    private boolean queueDepletionTest() {
        boolean success = true;
        try {
            SLLQueue<Integer> queue = new SLLQueue<Integer>();
            for (int i = 0; i < 5; i++) {
                queue.enqueue(i);
            }

            for (int i = 0; i < 5; i++) {
                queue.dequeue();
            }

            for (int i = 0; i < 5; i++) {
                queue.enqueue(i);
            }

            for (int i = 0; i < 5; i++) {
                if (queue.dequeue() != i) {
                    success = false;
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
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

    /** Returns true or false whether or not an element is in a sorted String array
     * @param ary The sorted String array
     * @param s The element to be found in the String array
     */
    private boolean contains(String[] ary, String s) {
        return Arrays.binarySearch(ary, s) >= 0 ;
    }
}
