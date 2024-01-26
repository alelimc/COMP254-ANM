/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class PrefixAverage  {


    /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
    public static double[] prefixAverage1(double[] x) {
        int n = x.length;
        double[] a = new double[n];    // filled with zeros by default
        for (int j=0; j < n; j++) {
            double total = 0;            // begin computing x[0] + ... + x[j]
            for (int i=0; i <= j; i++)
                total += x[i];
            a[j] = total / (j+1);        // record the average
        }
        return a;
    }

    /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
    public static double[] prefixAverage2(double[] x) {
        int n = x.length;
        double[] a = new double[n];    // filled with zeros by default
        double total = 0;              // compute prefix sum as x[0] + x[1] + ...
        for (int j=0; j < n; j++) {
            total += x[j];               // update prefix sum to include x[j]
            a[j] = total / (j+1);        // compute average based on current sum
        }
        return a;
    }

    /**
     * Tests the two versions of the prefixAverage algorithm, doubling the
     * size of n each trial, beginning with the given start value. The
     * first command line argument can be used to change the number of
     * trials, and the second to adjust the start value.
     */
    public static void main(String[] args) {
        int n = 5000; // starting value
        int trials = 10;
        try {
            if (args.length > 0)
                trials = Integer.parseInt(args[0]);
            if (args.length > 1)
                n = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
        }
        int start = n; // remember the original starting value

        double[][] data = new double[2][trials];
        String[] algorithms = {"prefixAverage1", "prefixAverage2"};

        // Test prefixAverage2
        System.out.println("Testing prefixAverage2...");
        for (int t = 0; t < trials; t++) {
            long startTime = System.currentTimeMillis();
            double[] temp = prefixAverage2(new double[n]);
            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            data[1][t] = elapsed;
            System.out.printf("n: %9d took %12d milliseconds%n", n, elapsed);
            n *= 2; // double the problem size
        }

        // Reset n to the start value
        n = start;

        // Test prefixAverage1
        System.out.println("Testing prefixAverage1...");
        for (int t = 0; t < trials; t++) {
            long startTime = System.currentTimeMillis();
            double[] temp = prefixAverage1(new double[n]);
            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            data[0][t] = elapsed;
            System.out.printf("n: %9d took %12d milliseconds%n", n, elapsed);
            n *= 2; // double the problem size
        }

    }
}
