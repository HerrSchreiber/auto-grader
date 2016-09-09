public class Project4Test {
    private int passes = 0;
    private int failures = 0;
    private int total = 0;
    private static final double TOLERANCE = 1E-3;

    public static void main(String[] args) {
        Project4Test tester = new Project4Test(); //to avoid every method being static
        tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////
        printTest("circleSubclassTest", circleSubclassTest());
        printTest("circleInstantiationTest", circleInstantiationTest());
        printTest("circlePerimeterTest", circlePerimeterTest());
        printTest("circleAreaTest", circleAreaTest());
        printTest("polygonSubclassTest", polygonSubclassTest());
        printTest("polygonInstantiationTest", polygonInstantiationTest());
        printTest("polygonPerimeterTest", polygonPerimeterTest());
        printTest("polygonAreaTest *EC*", polygonAreaTest());
        /////////////////
        //final verdict
        /////////////////
        printFinalSummary();
    }

    private boolean circleSubclassTest() {
        boolean success = true;
        
        try {
           Shape c = new Circle(new Point(1,2), 2);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean circleInstantiationTest() {
        boolean success = true;
        
        try {
            Circle c = new Circle(new Point(1,2), 2);
            if (!isClose(c.getRadius(), 2)) success = false;
            if (!isClose(c.getCenter().getX(), 1)) success = false;
            if (!isClose(c.getCenter().getY(), 2)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean circlePerimeterTest() {
        boolean success = true;
        
        try {
            Circle c = new Circle(new Point(1,2), 2);
            if (!isClose(c.getPerimeter(), 4 * Math.PI)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean circleAreaTest() {
        boolean success = true;
        
        try {
            Circle c = new Circle(new Point(1,2), 2);
            if (!isClose(c.getArea(), 4 * Math.PI)) success = false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean polygonSubclassTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(-1, 1);
            Point p3 = new Point(0, 2);
            Point p4 = new Point(1, 1);
            Point p5 = new Point(1, 0);
            Shape p = new Polygon(new Line[] {new Line(p1, p2),
                                              new Line(p2, p3),
                                              new Line(p3, p4),
                                              new Line(p4, p5),
                                              new Line(p5, p1)});

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean polygonInstantiationTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(-1, 1);
            Point p3 = new Point(0, 2);
            Point p4 = new Point(1, 1);
            Point p5 = new Point(1, 0);
            Polygon p = new Polygon(new Line[] {new Line(p1, p2),
                                              new Line(p2, p3),
                                              new Line(p3, p4),
                                              new Line(p4, p5),
                                              new Line(p5, p1)});
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean polygonPerimeterTest() {
        boolean success = true;
        
        try {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(-1, 1);
            Point p3 = new Point(0, 2);
            Point p4 = new Point(1, 1);
            Point p5 = new Point(1, 0);
            Polygon p = new Polygon(new Line[] {new Line(p1, p2),
                                              new Line(p2, p3),
                                              new Line(p3, p4),
                                              new Line(p4, p5),
                                              new Line(p5, p1)});
            if (!isClose(p.getPerimeter(),2 + 3 * Math.sqrt(2))) success = false;
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }
        return success;
    }

    private boolean polygonAreaTest() {
        boolean success = true;
        total--; // Extra Credit
        
        try {
            Point p1 = new Point(0, 0);
            Point p2 = new Point(-1, 1);
            Point p3 = new Point(0, 2);
            Point p4 = new Point(1, 1);
            Point p5 = new Point(1, 0);
            Polygon p = new Polygon(new Line[] {new Line(p1, p2),
                                              new Line(p2, p3),
                                              new Line(p3, p4),
                                              new Line(p4, p5),
                                              new Line(p5, p1)});
            if (!isClose(p.getArea(), 2.5)) success = false;
            
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
