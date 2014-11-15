import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 10 - Payroll
 * @author Rob Schreiber
 */
public class Project10Test {
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
    	Project10Test tester = new Project10Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////

        printTest("staffMemberAbstractTest", staffMemberAbstractTest());
        printTest("staffMemberSuperclassTest", staffMemberAbstractTest());
        printTest("employeeSuperclassTest", employeeSuperclassTest());
        printTest("internPayTest", internPayTest());
        printTest("employeePayTest", employeePayTest());
        printTest("hourlyEmployeePayTest", hourlyEmployeePayTest());
        printTest("executivePayTest", executivePayTest());
        printTest("hourlyEmployeeAddHoursTest", hourlyEmployeeAddHoursTest());
        printTest("executiveAwardBonusTest", executiveAwardBonusTest());
        printTest("toStringTests", toStringTests());
    }

    private boolean staffMemberAbstractTest() {
        boolean success = true;
        
        try {
            Class staffMemberClass = StaffMember.class;
            int modifiers = staffMemberClass.getModifiers();
            if (!Modifier.isAbstract(modifiers)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean staffMemberSuperclassTest() {
        boolean success = true;
        
        try {
            StaffMember sm = new Intern("bill", "nowhere", "123");
            sm = new Employee("Bill", "nowhere", "123", "1233", 2.0);
            sm = new HourlyEmployee("Bill", "nowhere", "123", "1233", 2.0);
            sm = new Executive("Bill", "nowhere", "123", "1233", 2.0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean employeeSuperclassTest() {
        boolean success = true;
        
        try {
            Employee sm = new HourlyEmployee("Bill", "nowhere", "123", "1233", 2.0);
            sm = new Executive("Bill", "nowhere", "123", "1233", 2.0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean internPayTest() {
        boolean success = true;
        
        try {
            Intern i = new Intern("bill", "nowhere", "123");
            if(!isClose(i.pay(), 0.0)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean employeePayTest() {
        boolean success = true;
        
        try {
            Employee i = new Employee("bill", "nowhere", "123", "1233", 30000);
            if(!isClose(i.pay(), 30000.0)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean hourlyEmployeePayTest() {
        boolean success = true;
        
        try {
            HourlyEmployee i = new HourlyEmployee("bill", "nowhere", "123", "1233", 30000);
            if(!isClose(i.pay(), 0.0)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean executivePayTest() {
        boolean success = true;
        
        try {
            Executive i = new Executive("bill", "nowhere", "123", "1233", 30000);
            if(!isClose(i.pay(), 30000.0)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean hourlyEmployeeAddHoursTest() {
        boolean success = true;
        
        try {
            HourlyEmployee i = new HourlyEmployee("bill", "nowhere", "123", "1233", 30000);
            i.addHours(2);
            if(!isClose(i.pay(), 60000.0)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean executiveAwardBonusTest() {
        boolean success = true;
        
        try {
            Executive i = new Executive("bill", "nowhere", "123", "1233", 30000);
            i.awardBonus(1000000.0);
            if(!isClose(i.pay(), 1030000.0)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean toStringTests() {
        boolean success = true;
        try {
            StaffMember[] sms = new StaffMember[4];
            sms[0] = new Intern("bill", "nowhere", "123");
            sms[1] = new Employee("bill", "nowhere", "123", "1233", 30000);
            sms[2] = new HourlyEmployee("bill", "nowhere", "123", "1233", 30000);
            sms[3] = new Executive("bill", "nowhere", "123", "1233", 30000);

            for(StaffMember sm: sms) System.out.println("\n" + sm.toString());
        } catch(Exception e) {
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