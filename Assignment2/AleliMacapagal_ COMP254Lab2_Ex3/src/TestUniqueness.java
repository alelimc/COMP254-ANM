public class TestUniqueness {
    public static void main(String[] args) {
        int startN = 1000000; // starting value of n 1000000;  size of the dataset.
        int maxN = startN;
        long maxTime = 60000; // maximum time in milliseconds (1 minute)

        int currentN = startN;
        long startTime, endTime, elapsedTime;

    //Test unique1 algorithm
        System.out.println("Testing unique1 algorithm");
        while (true) {
            int[] data = generateData(currentN); // Generate data of size currentN; generates an array data of size currentN by calling the generateData method.
            startTime = System.currentTimeMillis(); // records the current time before starting the execution of the algorithm.
            boolean result = Uniqueness.unique1(data);  //test the uniqueness of the generated data
            endTime = System.currentTimeMillis();   //records the current time after the execution of the algorithm.
            elapsedTime = endTime - startTime;  //calculates the elapsed time
            System.out.println("n = " + currentN + ", Time: " + elapsedTime + " ms");

            if (elapsedTime > maxTime) {        //checks if the elapsed time exceeds the maximum allowed time. If so, it breaks out of the loop.
                maxN = currentN - 1;
                break;
            }
            currentN *= 2; // Double the problem size; doubles the value of currentN, effectively increasing the size of the dataset for the next iteration of testing.
        }
        System.out.println("Maximum effective value of n for unique1: " + maxN);

        // Reset currentN to startN for the next algorithm
        currentN = startN;

    //Test unique2 algorithm
        System.out.println("Testing unique2 algorithm");
        maxN = startN;
        while (true) {
            int[] data = generateData(currentN); // Generate data of size currentN
            startTime = System.currentTimeMillis();
            boolean result = Uniqueness.unique2(data);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("n = " + currentN + ", Time: " + elapsedTime + " ms");

            if (elapsedTime > maxTime) {
                maxN = currentN - 1;
                break;
            }
            currentN *= 2; // Double the problem size
        }

        System.out.println("Max effective value of n for unique2: " + maxN);
    }

    private static int[] generateData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i; // Generate data with no duplicates
        }
        return data;
    }
}

