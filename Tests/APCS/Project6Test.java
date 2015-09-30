import java.util.*;
import java.io.*;

/**
 * Tests Project 6 - Better Magpie
 * @author Rob Schreiber
 */
public class Project6Test {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = 1E-3;
    private String[] keywords = new String[100];
    private String[] responses = new String[100];

    /**
     * Reads in the keywords and responses from README.TXT and
     * stores them in two String arrays called keywords and
     * responses. Then it calls the runTests method.
     * @param args Not used
     */
    public static void main(String[] args) {
    	Project6Test tester = new Project6Test(); //to avoid every method being static
    	Scanner fileInput = null;
        try {
        	fileInput = new Scanner(new File("README.TXT"));
        }
        catch (FileNotFoundException e) {
        	System.out.println("README.TXT doesn't exist");
        	System.exit(1);
        }
        String[] ary = new String[100];
        String temp = null;
        int i = 0;
        while (fileInput.hasNext()) {
            temp = fileInput.nextLine();
            if (temp.length()>0) {
                if (temp.charAt(0) == '"') temp = temp.substring(1);
                if (temp.charAt(temp.length() - 1) == '"') temp = temp.substring(0, temp.length() - 1);
            }
            if(temp.toLowerCase().indexOf("keyword") >= 0) {
                ary = tester.keywords;
                tester.keywords[0] = "dog";
                tester.keywords[1] = "cat";
                tester.keywords[2] = "";
                i = 3;
            }
            else if(temp.toLowerCase().indexOf("response") >= 0
                    && temp.toLowerCase().indexOf("rand") >=0) {
                String[] tempArray = new String[i];
                for (int j = 0; j < i; j++) {
                    tempArray[j] = tester.keywords[j];
                }
                tester.keywords = tempArray;
                ary = tester.responses;
                tester.responses[0] = "Interesting, tell me more.";
                tester.responses[1] = "Hmmm.";
                tester.responses[2] = "Do you really think so?";
                tester.responses[3] = "You don't say.";
                i = 4;
            }
            else if (!temp.equals("") && !temp.equals("Schreiber") && !temp.equals("dog") && !temp.equals("cat") && !temp.equals("Interesting, tell me more.") && !temp.equals("Hmmm.") && !temp.equals("Do you really think so?") && !temp.equals("You don't say.")){
                ary[i++] = temp;
            }
        }
        String[] tempArray = new String[i];
        for (int j = 0; j < i; j++) {
            tempArray[j] = tester.responses[j];
        }
        tester.responses = tempArray;
        for (String s : tester.keywords) {
            System.out.println(s);
        }
        System.out.println();
        for (String s : tester.responses) {
            System.out.println(s);
        }
        Arrays.sort(tester.keywords);
        Arrays.sort(tester.responses);
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
        printTest("randomResponsesTest", randomResponsesTest());
        printTest("iWantResponseTest", iWantResponseTest());
        printTest("iYouResponseTest", iYouResponseTest());
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
        try {
            Magpie m = new Magpie();
            boolean[] responseFound = new boolean[responses.length];
            for(int i = 0; i < responses.length * 10; i++) {
                String response = m.getResponse("fdafdsafdsa");
                responseFound[Arrays.binarySearch(responses, response)] = true;
            }
            for (int i = 0; i < responses.length; i++) {
                if(!responseFound[i]) success = false;
                System.out.printf("%-46s\t%s\n", responses[i], (responseFound[i] ? "   FOUND" : "***NOT FOUND***"));
            }
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    private boolean iWantResponseTest() {
        boolean success = true;
        try {
            Magpie m = new Magpie();
            System.out.println(m.getResponse("I want pie"));
            success = "Would you really be happy if you had pie?".equals(m.getResponse("I want pie"));
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    private boolean iYouResponseTest() {
        boolean success = true;
        try {
            Magpie m = new Magpie();
            System.out.println(m.getResponse("I tolerate you"));
            success = "Why do you tolerate me?".equals(m.getResponse("I tolerate you"));
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
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
