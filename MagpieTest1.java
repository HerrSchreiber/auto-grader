import java.util.*;
import java.io.*;

/**
 * Tests Project 5 - Intro to Magpie
 * @author Rob Schreiber
 */
public class MagpieTest1 {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = Math.pow(10, -1);
    private String[] keywords = new String[100];
    private String[] responses = new String[100];

    /**
     * Reads in the keywords and responses from README.TXT and
     * stores them in two String arrays called keywords and
     * responses. Then it calls the runTests method.
     * @param args Not used
     */
    public static void main(String[] args) {
        Scanner fileInput = new Scanner(new File("README.TXT"));
        String[] ary = null;
        String temp = null;
        int i = 0;
        while (fileInput.hasNext()) {
            temp = fileInput.nextLine();
            if(temp.toLowerCase().indexOf("keyword") >= 0) {
                ary = keywords;
                keywords[0] = "Schreiber";
                keywords[1] = "dog";
                keywords[2] = "cat";
                keywords[3] = "";
                keywords[4] = "    ";
                i = 5;
            }
            else if(temp.toLowerCase().indexOf("response") >= 0) {
                ary = responses;
                responses[] = "Interesting, tell me more.";
                responses[] = "Hmmm.";
                responses[] = "Do you really think so?";
                responses[] = "You don't say.";
                i = 4;
            }
            else {
                ary[i++] = temp;
            }
        }
        Arrays.sort(keywords);
        Arrays.sort(reponses);
        MagpieTest1 tester = new MagpieTest1(); //to avoid every method being static
        tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////
        Magpie m = new Magpie();
        for (String k: keywords) {
            printTest(k + " test", !contains(responses, m.getResponse(k)));
        }
        printTest("randomResponsesTest", randomResponseseTest());
        /////////////////
        //final verdict
        /////////////////
        printFinalSummary();
    }

    /**
     * Tests to make sure all the Random Responses in the readme are present
     * in the actual program operation.
     */
    private boolean randomResponsesTest() {
        boolean success = true;
        Magpie m = new Magpie();
        boolean[] responseFound = new boolean[response.length];
        for(int i = 0; i < responses.length * 10; i++) {
            String response = m.getResponse("fdafdsafdsa");
            responseFound[Arrays.binarySearch(responses, response)] = true;
        }
        for (int = 0; i < response.length; i++) {
            if(!responseFound[i]) success = false;
            System.out.println(responses[i] + "\t\t\t" + responseFound[i]? "found":"not found");
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