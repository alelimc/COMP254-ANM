//Exercise 1
//Running time:

import java.util.Arrays;
import java.util.Random;

public class MaxArray {
    public static void main(String[ ] args) {
        //int startTime =

        int data[ ] = new int[5];
        Random r = new Random( );
        r.setSeed(System.currentTimeMillis( ));

        for (int i = 0; i < data.length; i++)
            data[i] = r.nextInt(100);
        int[ ] orig = Arrays.copyOf(data, data.length);
        //Math.max([orig])

        //int endTime =
        //int runningTime = endTime - startTime;

        System.out.println("original = " + Arrays.toString(orig));
        System.out.println("data = " + Arrays.toString(data));
        //System.out.println("Running time: " + runningTime);
    }

}
