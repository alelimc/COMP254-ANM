import java.util.HashMap;
import java.util.Map;

public abstract class CustomAbstractHashMap<K, V> {
    protected double maxLoadFactor;

    public CustomAbstractHashMap(double maxLoadFactor) {
        if (maxLoadFactor <= 0 || maxLoadFactor > 1) {
            throw new IllegalArgumentException("Invalid max load factor");
        }
        this.maxLoadFactor = maxLoadFactor;
    }

    public abstract V get(K key);

    public abstract V put(K key, V value);

    public abstract V remove(K key);

    public abstract int size();

    public abstract boolean isEmpty();

    public abstract Iterable<Map.Entry<K, V>> entrySet();
}
