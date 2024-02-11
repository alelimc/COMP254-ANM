
public class MultiplyNum {
    public static int multiply(int m, int n) {
        if (m == 0 || n == 0) {
            return 0; // If either m or n is zero, the product is zero
        } else if (n == 1) {
            return m; // If n is 1, the product is m
        } else {
            // If n is greater than 1, recursively add m to the product of m and (n - 1)
            return m + multiply(m, n - 1);
        }
    }

    public static void main(String[] args) {
        // Test the multiply method
        int m = 3;
        int n = 9;
        int product = multiply(m, n);
        System.out.println("Product of " + m + " and " + n + " is: " + product); // Output: Product of 5 and 4 is: 20
    }
}
