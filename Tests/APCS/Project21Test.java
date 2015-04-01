import java.util.*;
import java.io.*;
import java.lang.reflect.Modifier;

/**
 * Tests Project 21 - Matrix Math
 * @author Rob Schreiber
 */
public class Project21Test {
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
    	Project21Test tester = new Project21Test(); //to avoid every method being static
    	tester.runTests();
    }
    
    /** Run tests on GridMonitor constructor and expected methods */
    private void runTests() {
        //////////////////////////////////////////////////////////////////
        // run tests on all interface methods to confirm correct results
        // and behavior under normal and exceptional use cases
        //////////////////////////////////////////////////////////////////
        
        printTest("positiveSquareAddTest", positiveSquareAddTest());
        printTest("negativeSquareAddTest", negativeSquareAddTest());
        printTest("rectangleAddTest", rectangleAddTest());
        printTest("invalidDimensionAddTest", invalidDimensionAddTest());
        printTest("positiveSquareSubtractTest", positiveSquareSubtractTest());
        printTest("negativeSquareSubtractTest", negativeSquareSubtractTest());
        printTest("rectangleSubtractTest", rectangleSubtractTest());
        printTest("invalidDimensionSubtractTest", invalidDimensionSubtractTest());
        printTest("positiveSquareMultiplyTest", positiveSquareMultiplyTest());
        printTest("negativeSquareMultiplyTest", negativeSquareMultiplyTest());
        printTest("rectangleMultiplyTest", rectangleMultiplyTest());
        printTest("invalidDimensionMultiplyTest", invalidDimensionMultiplyTest());
        printTest("positiveDeterminantTest", positiveDeterminantTest());
        printTest("negativeDeterminantTest", negativeDeterminantTest());
        printTest("inverseTest", inverseTest());
        
    	
    }

    // Add Tests

	private boolean positiveSquareAddTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3}, {4, 1}};
            double[][] b = {{5, 1}, {3, 2}};
            double[][] sum = MatrixMath.add(a, b);
            double[][] correctSum = {{7, 4}, {7, 3}};
            for (int i = 0; i < sum.length; i++) {
                for (int j = 0; j < sum[0].length; j++) {
                    if (!isClose(sum[i][j], correctSum[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean negativeSquareAddTest() {
        boolean success = true;
        
        try {
            double[][] a = {{-2, 3}, {-4, 1}};
            double[][] b = {{5, 1}, {3, 2}};
            double[][] sum = MatrixMath.add(a, b);
            double[][] correctSum = {{3, 4}, {-1, 3}};
            for (int i = 0; i < sum.length; i++) {
                for (int j = 0; j < sum[0].length; j++) {
                    if (!isClose(sum[i][j], correctSum[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean rectangleAddTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3, 1}, {4, 1, 6}};
            double[][] b = {{5, 1, 2}, {3, 2, 1}};
            double[][] sum = MatrixMath.add(a, b);
            double[][] correctSum = {{7, 4, 3}, {7, 3, 7}};
            for (int i = 0; i < sum.length; i++) {
                for (int j = 0; j < sum[0].length; j++) {
                    if (!isClose(sum[i][j], correctSum[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }     

    private boolean invalidDimensionAddTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3, 1}, {4, 1, 6}};
            double[][] b = {{5, 1, 2}, {3, 2}};
            double[][] sum = MatrixMath.add(a, b);
            success = false;

        } 
        catch (IllegalArgumentException e) {
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    } 

    // Subtract Tests   

    private boolean positiveSquareSubtractTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3}, {4, 1}};
            double[][] b = {{5, 1}, {3, 2}};
            double[][] diff = MatrixMath.subtract(a, b);
            double[][] correctDiff = {{-3, 2}, {1, -1}};
            for (int i = 0; i < diff.length; i++) {
                for (int j = 0; j < diff[0].length; j++) {
                    if (!isClose(diff[i][j], correctDiff[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean negativeSquareSubtractTest() {
        boolean success = true;
        
        try {
            double[][] a = {{-2, 3}, {-4, 1}};
            double[][] b = {{5, 1}, {3, 2}};
            double[][] diff = MatrixMath.subtract(a, b);
            double[][] correctDiff = {{-7, 2}, {-7, -1}};
            for (int i = 0; i < diff.length; i++) {
                for (int j = 0; j < diff[0].length; j++) {
                    if (!isClose(diff[i][j], correctDiff[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean rectangleSubtractTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3, 1}, {4, 1, 6}};
            double[][] b = {{5, 1, 2}, {3, 2, 1}};
            double[][] diff = MatrixMath.subtract(a, b);
            double[][] correctDiff = {{-3, 2, -1}, {1, -1, 5}};
            for (int i = 0; i < diff.length; i++) {
                for (int j = 0; j < diff[0].length; j++) {
                    if (!isClose(diff[i][j], correctDiff[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }     

    private boolean invalidDimensionSubtractTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3, 1}, {4, 1, 6}};
            double[][] b = {{5, 1, 2}, {3, 2}};
            double[][] diff = MatrixMath.subtract(a, b);
            success = false;

        } 
        catch (IllegalArgumentException e) {
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    } 

    // Multiply Tests
     
    private boolean positiveSquareMultiplyTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3}, {4, 1}};
            double[][] b = {{5, 1}, {3, 2}};
            double[][] diff = MatrixMath.multiply(a, b);
            double[][] correctProduct = {{19, 8}, {23, 6}};
            for (int i = 0; i < diff.length; i++) {
                for (int j = 0; j < diff[0].length; j++) {
                    if (!isClose(diff[i][j], correctProduct[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }   

    private boolean negativeSquareMultiplyTest() {
        boolean success = true;
        
        try {
            double[][] a = {{-2, 3}, {-4, 1}};
            double[][] b = {{5, 1}, {3, 2}};
            double[][] product = MatrixMath.multiply(a, b);
            double[][] correctProduct = {{-1, 4}, {-17, -2}};
            for (int i = 0; i < product.length; i++) {
                for (int j = 0; j < product[0].length; j++) {
                    if (!isClose(product[i][j], correctProduct[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    private boolean rectangleMultiplyTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3, 1}, {4, 1, 6}};
            double[][] b = {{5, 1}, {3, 2}, {1, 2}};
            double[][] product = MatrixMath.multiply(a, b);
            double[][] correctProduct = {{20, 10}, {29, 18}};
            for (int i = 0; i < product.length; i++) {
                for (int j = 0; j < product[0].length; j++) {
                    if (!isClose(product[i][j], correctProduct[i][j])) success = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }     

    private boolean invalidDimensionMultiplyTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3, 1}, {4, 1, 6}};
            double[][] b = {{5, 1, 2}, {3, 2}};
            double[][] product = MatrixMath.multiply(a, b);
            success = false;

        } 
        catch (IllegalArgumentException e) {
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    // Determinant tests

    private boolean positiveDeterminantTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3}, {4, 1}};
            double det = MatrixMath.det(a);
            double correctDet = -10;
            if (!isClose(det, correctDet)) success = false;

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    } 

    private boolean negativeDeterminantTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, -3}, {4, 1}};
            double det = MatrixMath.det(a);
            double correctDet = 14;
            if (!isClose(det, correctDet)) success = false;

        } catch (Exception e) {
            e.printStackTrace(System.out);
            success = false;
        }

        return success;
    }

    // Inverse test     

    private boolean inverseTest() {
        boolean success = true;
        
        try {
            double[][] a = {{2, 3}, {4, 1}};
            double[][] inverse = MatrixMath.inverse(a);
            double[][] correctInverse = {{-.1, .3}, {.4, -.2}};
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse[0].length; j++) {
                    if (!isClose(inverse[i][j], correctInverse[i][j])) success = false;
                }
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
