import java.util.Random;

//This test application creates a ConcreteHashMap,
// populates it with key-value pairs, displays its size and contents,
// removes an entry, and then displays the updated size and contents.
public class HashMapTest {
    public static void main(String[] args) {
//        ChainHashMap<String, Integer> chainHashMap = new ChainHashMap<>((int) 0.5); // Example with ChainHashMap
        ChainHashMap<String, Integer> chainHashMap = new ChainHashMap<>(3); // Explicitly providing type arguments
        ProbeHashMap<String, Integer> probeHashMap = new ProbeHashMap<>(0.5); // Example with ProbeHashMap

        Random random = new Random();

        // Test with random key sets
        for (int i = 0; i < 1000; i++) {        // iterates 1000 times, to generate random key-value pairs and insert them into the hash maps
            String key = String.valueOf(random.nextInt(100));       //generates a random integer between 0 and 99, converts it to a string, and assigns it to the variable key
            int value = random.nextInt(100);                        //This line generates a random integer between 0 and 99 and assigns it to the variable value.

            chainHashMap.put(key, value);       //inserts the generated key-value pair into the chainHashMap.
            probeHashMap.put(key, value);       //inserts the generated key-value pair into the probeHashMap.
        }

        System.out.println("ChainHashMap size: " + chainHashMap.size());
        System.out.println("ProbeHashMap size: " + probeHashMap.size());
    }
}


