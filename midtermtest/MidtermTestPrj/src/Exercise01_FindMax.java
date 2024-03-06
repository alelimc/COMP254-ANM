//Exercise01_FindMax

public class Exercise01_FindMax {

    //takes an array as input and returns the maximum element in that array.
    //calls a recursive helper method findMaxRecursive, which divides the array into smaller sub-arrays
    // until it reaches arrays of size 1, where it simply returns the single element as the maximum.
    // Then, it compares the maximums of the left and right sub-arrays and returns the greater of the two.
    public static int findMax(int[] array) {
        return findMaxRecursive(array, 0, array.length - 1);
    }

    //The running time of this algorithm is O(n), where n is the number of elements in the array.
    //This is because the algorithm divides the array in half recursively until it reaches arrays of size 1,
    //so it performs O(log n) recursive calls. In each call, it compares two numbers using Math.max,
    // which is a constant-time operation. Therefore, the overall time complexity is O(n).

    private static int findMaxRecursive(int[] array, int start, int end) {
        if (start == end) {
            return array[start];
        } else {
            int mid = (start + end) / 2;
            int max1 = findMaxRecursive(array, start, mid);
            int max2 = findMaxRecursive(array, mid + 1, end);
            return Math.max(max1, max2);
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 7, 3, 15, 6, 14};
        int max = findMax(array);
        System.out.println("Maximum element in the array is: " + max);
    }
}
