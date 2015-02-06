import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 17 - Grocery List
 * @author Rob Schreiber
 */
public class Project17Test {
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
    	Project17Test tester = new Project17Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("addTest", addTest());
        printTest("insertTest", insertTest());
        printTest("removeTest", removeTest());
        printTest("completeAndClearTest", completeAndClearTest());
        printTest("exportTest", exportTest());
    	
    }

	private boolean addTest() {
        boolean success = true;
        
        try {
            GroceryList gl = new GroceryList();
            gl.add("blorgons");
            gl.add("inspector");
            gl.add("spacetime");
            if (gl.toString().contains("blorgons") != true) success = false;
            if (gl.toString().contains("inspector") != true) success = false;
            if (gl.toString().contains("spacetime") != true) success = false;
            if (gl.toString().indexOf("blorgons") > gl.toString().indexOf("inspector")) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }   

    private boolean insertTest() {
        boolean success = true;
        
        try {
            GroceryList gl = new GroceryList();
            gl.add("blorgons");
            gl.add("inspector");
            gl.insert("spacetime", 1);
            if (gl.toString().contains("blorgons") != true) success = false;
            if (gl.toString().contains("inspector") != true) success = false;
            if (gl.toString().contains("spacetime") != true) success = false;
            if (gl.toString().indexOf("inspector") < gl.toString().indexOf("spacetime")) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean removeTest() {
        boolean success = true;
        
        try {
            GroceryList gl = new GroceryList();
            gl.add("blorgons");
            gl.add("inspector");
            gl.add("spacetime");
            gl.remove(1);
            if (gl.toString().contains("blorgons") != true) success = false;
            if (gl.toString().contains("inspector") != false) success = false;
            if (gl.toString().contains("spacetime") != true) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean completeAndClearTest() {
        boolean success = true;
        
        try {
            GroceryList gl = new GroceryList();
            gl.add("blorgons");
            gl.add("inspector");
            gl.add("spacetime");
            gl.complete(1);
            gl.removeAllCompleted();
            if (gl.toString().contains("blorgons") != true) success = false;
            if (gl.toString().contains("inspector") != false) success = false;
            if (gl.toString().contains("spacetime") != true) success = false;

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean exportTest() {
        boolean success = false;
        
        try {
            GroceryList gl = new GroceryList();
            gl.add("blorgons");
            gl.add("inspector");
            gl.add("spacetime");
            
            gl.exportAsHTML();

            Scanner fileReader = new Scanner(new File(list.html));
            while (fileReader.hasNext()) {
                String temp = fileReader.nextLine();
                if (temp.contains("li") && temp.contains("blorgons")) success = true;
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
