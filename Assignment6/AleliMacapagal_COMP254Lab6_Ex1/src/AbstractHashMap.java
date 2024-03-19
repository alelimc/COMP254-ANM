import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
    protected int n = 0;                 // number of entries in the dictionary
    protected int capacity;              // length of the table
    private int prime;                   // prime factor
    private long scale, shift;           // the shift and scaling factors
    private double maxLoadFactor;        // maximum load factor

    /** Creates a hash table with the given capacity, prime factor, and maximum load factor. */
    public AbstractHashMap(int cap, int p, double maxLoad) {
        prime = p;
        capacity = cap;
        maxLoadFactor = maxLoad;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    /** Creates a hash table with given capacity, prime factor, and default maximum load factor. */
    public AbstractHashMap(int cap, int p) {
        this(cap, p, 0.5); // default maximum load factor
    }

    /** Creates a hash table with given capacity, default prime factor, and maximum load factor. */
    public AbstractHashMap(int cap, double maxLoad) {
        this(cap, 109345121, maxLoad); // default prime factor
    }

    /** Creates a hash table with given capacity, default prime factor, and default maximum load factor. */
    public AbstractHashMap(int cap) {
        this(cap, 109345121, 0.5); // default prime factor and maximum load factor
    }

    /** Creates a hash table with capacity 17, default prime factor, and default maximum load factor. */
    public AbstractHashMap() {
        this(17, 109345121, 0.5); // default capacity, prime factor, and maximum load factor
    }

    // public methods
    /**
     * Tests whether the map is empty.
     *
     * @return true if the map is empty, false otherwise
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    /**
     * Removes the entry with the specified key, if present, and returns
     * its associated value. Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no such entry exists
     */
    @Override
    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    /**
     * Associates the given value with the given key. If an entry with
     * the key was already in the map, this replaces the previous value
     * with the new one and returns the old value. Otherwise, a new
     * entry is added and null is returned.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such entry)
     */
    @Override
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if ((double) n / capacity > maxLoadFactor)
            resize(2 * capacity - 1); // (or find a nearby prime)
        return answer;
    }

    // private utilities
    /** Hash function applying MAD method to default hash code. */
    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /** Updates the size of the hash table and rehashes all entries. */
    private void resize(int newCap) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
        for (Entry<K, V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();              // based on updated capacity
        n = 0;                      // will be recomputed while reinserting entries
        for (Entry<K, V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    // protected abstract methods to be implemented by subclasses
    /** Creates an empty table having length equal to current capacity. */
    protected abstract void createTable();

    /**
     * Returns value associated with key k in bucket with hash value h.
     * If no such entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    protected abstract V bucketGet(int h, K k);

    /**
     * Associates key k with value v in bucket with hash value h, returning
     * the previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    protected abstract V bucketPut(int h, K k, V v);

    /**
     * Removes entry having key k from bucket with hash value h, returning
     * the previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    protected abstract V bucketRemove(int h, K k);
}
