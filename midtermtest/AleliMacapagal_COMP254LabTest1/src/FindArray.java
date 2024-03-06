//Exercise 3
//Running time:

class FindArray {
    //int startTime =
       public static int arrayFind(int[] data, int val) {
        int n = data.length;
        int j = 0;
        while (j < n) {
            if (data[j] == val)
                return j;
            j++;
        }
        return -1;
    }
    //int endTime =
    //int runningTime = endTime - startTime;
    //System.out.println("Running time: " + runningTime);
}