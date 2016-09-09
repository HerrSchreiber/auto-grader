public class Project3Test {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = 1E-3;

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
        printTest("triangleInstantiationTest", triangleInstantiationTest());
        printTest("triangleToStringTest", triangleToStringTest());
        printTest("triangleGetSidesTest", triangleGetSidesTest());
        printTest("triangleGetCenterTest", triangleGetCenterTest());
        printTest("triangleGetPerimeterTest", triangleGetPerimeterTest());
        printTest("triangleGetAreaTest", triangleGetAreaTest());
        printTest("triangleGetAltitudeTest *EC*", triangleGetAltitudeTest());
        /////////////////
        //final verdict
        /////////////////
        printFinalSummary();
    }

    private boolean triangleInstantiationTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            if (t.getPointA() != p1 && t.getPointA() != p2 && t.getPointA() != p3) success = false;
            if (t.getPointB() != p1 && t.getPointB() != p2 && t.getPointB() != p3) success = false;
            if (t.getPointC() != p1 && t.getPointC() != p2 && t.getPointC() != p3) success = false;
            if (t.getPointA() == t.getPointB() || t.getPointB() == t.getPointC()) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean triangleToStringTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            if (!t.toString().equals("(1.0, 2.0) <-> (-1.0, 3.0) <-> (3.0, -2.0)")
             && !t.toString().equals("(-1.0, 3.0) <-> (1.0, 2.0) <-> (3.0, -2.0)") 
             && !t.toString().equals("(3.0, -2.0) <-> (1.0, 2.0) <-> (-1.0, 3.0)") 
             && !t.toString().equals("(-1.0, 3.0) <-> (3.0, -2.0) <-> (1.0, 2.0)") 
             && !t.toString().equals("(3.0, -2.0) <-> (-1.0, 3.0) <-> (1.0, 2.0)") 
             && !t.toString().equals("(1.0, 2.0) <-> (3.0, -2.0) <-> (-1.0, 3.0)"))
             success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean triangleGetSidesTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            Line[] sides = t.getSides();
            if (!contains(sides, new Line(p1, p2)) &&
                !contains(sides, new Line(p2, p1))) success = false;
            if (!contains(sides, new Line(p2, p3)) &&
                !contains(sides, new Line(p3, p2))) success = false;
            if (!contains(sides, new Line(p1, p3)) &&
                !contains(sides, new Line(p3, p1))) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean triangleGetCenterTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            if (t.getCenter().getX() != 1 || t.getCenter().getY() != 1) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean triangleGetPerimeterTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            if (!isClose(t.getPerimeter(), 13.111)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean triangleGetAreaTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            if (!isClose(t.getArea(), 3)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }   

    private boolean triangleGetAltitudeTest() {
        boolean success = true;
        total--; // Extra Credit
        
        try {
            Point p1 = new Point(1, 2);
            Point p2 = new Point(-1, 3);
            Point p3 = new Point(3, -2);
            Triangle t = new Triangle(p1, p2, p3);
            Line alt = t.getAltitude();

            if (isClose(alt.getPointA().getX(), 1.0) && isClose(alt.getPointA().getY(), 2.0)) {
                if (!isClose(alt.getPointB().getX(), 0.268) || !isClose(alt.getPointB().getY(), 1.415))
                    success = false;
            }
            else if (isClose(alt.getPointB().getX(), 1.0) && isClose(alt.getPointB().getY(), 2.0)) {
                if (!isClose(alt.getPointA().getX(), 0.268) || !isClose(alt.getPointA().getY(), 1.415))
                    success = false;
            }

            else if (isClose(alt.getPointA().getX(), -1.0) && isClose(alt.getPointA().getY(), 3.0)) {
                if (!isClose(alt.getPointB().getX(), 0.2) || !isClose(alt.getPointB().getY(), 3.6))
                    success = false;
            }
            else if (isClose(alt.getPointB().getX(), -1.0) && isClose(alt.getPointB().getY(), 3.0)) {
                if (!isClose(alt.getPointA().getX(), 0.2) || !isClose(alt.getPointA().getY(), 3.6))
                    success = false;
            }

            else if (isClose(alt.getPointA().getX(), 3.0) && isClose(alt.getPointA().getY(), -2.0)) {
                if (!isClose(alt.getPointB().getX(), 4.2) || !isClose(alt.getPointB().getY(), 0.4))
                    success = false;
            }
            else if (isClose(alt.getPointB().getX(), 3.0) && isClose(alt.getPointB().getY(), -2.0)) {
                if (!isClose(alt.getPointA().getX(), 4.2) || !isClose(alt.getPointA().getY(), 0.4))
                    success = false;
            }
            else success = false;

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
				System.out.println((int)(((double)passes/total)*20) + "/20");
    }

    /** Compare two doubles and return true if they are within TOLERANCE, else false
     * @param v1 first value to compare
     * @param v2 second value to compare
     * @return true if v1 and v2 are within TOLERANCE, else false
     */
    private boolean isClose(double v1, double v2) {
        return (Math.abs(v1 - v2) < TOLERANCE);
    }
    private boolean contains(Line[] sides, Line s) {
        boolean success = false;
        for (Line side : sides) {
            if ((isClose(side.getPointA().getX(), s.getPointA().getX()) ||
                isClose(side.getPointA().getX(), s.getPointB().getX())) &&
                (isClose(side.getPointA().getY(), s.getPointA().getY()) ||
                isClose(side.getPointA().getY(), s.getPointB().getY()))) success = true; 
            if ((isClose(side.getPointB().getX(), s.getPointA().getX()) ||
                isClose(side.getPointB().getX(), s.getPointB().getX())) &&
                (isClose(side.getPointB().getY(), s.getPointA().getY()) ||
                isClose(side.getPointB().getY(), s.getPointB().getY()))) success = true; 
        }
    return success;
    }
}
