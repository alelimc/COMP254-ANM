public class Exercises {

    /** Returns the sum of the integers in given array.
     * Big-O: O(n)
     * This method iterates through the array once, performing a constant-time operation in each iteration.
     * The running time grows linearly with the size of the input array, hence O(n).
     */
    public static int example1(int[] arr) {
        int n = arr.length, total = 0;
        for (int j=0; j < n; j++)       // loop from 0 to n-1
            total += arr[j];
        return total;
    }

    /** Returns the sum of the integers with even index in given array.
     * Big-O: O(n)
     * This method iterates through the array once, skipping every other element, which effectively halves the number of iterations.
     * However, the running time still grows linearly with the size of the input array, hence O(n).
     */
    public static int example2(int[] arr) {
        int n = arr.length, total = 0;
        for (int j=0; j < n; j += 2)    // note the increment of 2
            total += arr[j];
        return total;
    }

    /** Returns the sum of the prefix sums of given array.
     * Big-O: O(n^2)
     * This method has nested loops where the outer loop iterates 'n' times and the inner loop iterates 'j' times for each value of 'n'.
     * Hence, the total number of iterations is roughly proportional to the sum of the first 'n' integers, which is O(n^2).
     */
    public static int example3(int[] arr) {
        int n = arr.length, total = 0;
        for (int j=0; j < n; j++)       // loop from 0 to n-1
            for (int k=0; k <= j; k++)  // loop from 0 to j
                total += arr[j];
        return total;
    }

    /** Returns the sum of the prefix sums of given array.
     * Big-O: O(n)
     * This method iterates through the array once, performing constant-time operations in each iteration.
     * The running time grows linearly with the size of the input array, hence O(n).
     */
    public static int example4(int[] arr) {
        int n = arr.length, prefix = 0, total = 0;
        for (int j=0; j < n; j++) {     // loop from 0 to n-1
            prefix += arr[j];
            total += prefix;
        }
        return total;
    }

    /** Returns the number of times second array stores sum of prefix sums from first.
     * Big-O: O(n^3)
     * This method has three nested loops where each loop iterates 'n' times.
     * Hence, the total number of iterations is proportional to n^3, making the overall complexity O(n^3).
     */
    public static int example5(int[] first, int[] second) { // assume equal-length arrays
        int n = first.length, count = 0;
        for (int i=0; i < n; i++) {         // loop from 0 to n-1
            int total = 0;
            for (int j=0; j < n; j++)       // loop from 0 to n-1
                for (int k=0; k <= j; k++)  // loop from 0 to j
                    total += first[k];
            if (second[i] == total) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        // Test example1: Return the sum of the integers in given array.
        int[] arr1 = {10, 2, 23, 4, 15};
        System.out.println("Example 1 output: " + Exercises.example1(arr1)); // Expected output: 54

        // Test example2:  Return the sum of the integers with even index in given array.
        int[] arr2 = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println("Example 2 output: " + Exercises.example2(arr2)); // Expected output: 15

        // Test example3: Return the sum of the prefix sums of given array.
        // (used small input to avoid long wait times)
        int[] arr3 = {1, 2, 3};
        System.out.println("Example 3 output: " + Exercises.example3(arr3)); // Expected output: 14

        // Test example4: Return the sum of the prefix sums of given array.
        int[] arr4 = {1, 2, 3, 4, 5};
        System.out.println("Example 4 output: " + Exercises.example4(arr4)); // Expected output: 35

        // Test example5: Return the number of times second array stores sum of prefix sums from first.
        // (used small input to avoid long wait times)
        int[] first = {1, 2, 3, 4, 5};
        int[] second = {35, 7, 18, 35, 1};
        System.out.println("Example 5 output: " + Exercises.example5(first, second)); // Expected output: 2
    }
}
