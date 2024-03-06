//Exercise03_FindLargestTenInArray

import java.util.Arrays;
public class FindTen {
    public static int[] findTenLargest(int[] array) {
        int[] largestIndices = new int[10];
        Arrays.fill(largestIndices, -1);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (largestIndices[j] == -1 || array[i] > array[largestIndices[j]]) {
                    // Shift elements in the array to make room for the new index
                    for (int k = 9; k > j; k--) {
                        largestIndices[k] = largestIndices[k - 1];
                    }
                    largestIndices[j] = i;
                    break;
                }
            }
        }

        // Extract the ten largest elements from the array
        int[] result = new int[10];
        for (int i = 0; i < 10; i++) {
            result[i] = array[largestIndices[i]];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 8, 2, 9, 3, 7, 10, 4, 6, 11, 15, 13, 12, 14};
        int[] largest = findTenLargest(array);

        System.out.println("Ten largest elements in Array:");
        for (int num : largest) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
