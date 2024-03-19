import java.util.Map;

// ProbeHashMap implementation
public class ProbeHashMap<K, V> extends CustomAbstractHashMap<K, V> {
    private Object[] table;
    private static final int PRIME = 109345121;
    private int size;

    public ProbeHashMap(double maxLoadFactor) {
        super(maxLoadFactor);
        table = new Object[11]; // Initial size of the table
        size = 0;
    }

    private int hash(K key, int i) {
        return Math.abs(key.hashCode() + i * (PRIME - (key.hashCode() % PRIME))) % table.length;
    }

    @Override
    public V get(K key) {
        int i = 0;
        int index = hash(key, i);

        while (table[index] != null) {
            if (table[index] instanceof Map.Entry && ((Map.Entry<K, V>) table[index]).getKey().equals(key)) {
                return ((Map.Entry<K, V>) table[index]).getValue();
            }
            i++;
            index = hash(key, i);
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if ((double) (size + 1) / table.length > maxLoadFactor) {
            rehash();
        }

        int i = 0;
        int index = hash(key, i);

        while (table[index] != null && !(table[index] instanceof Tombstone) &&
                !((Map.Entry<K, V>) table[index]).getKey().equals(key)) {
            i++;
            index = hash(key, i);
        }

        if (table[index] == null || table[index] instanceof Tombstone) {
//            table[index] = new AbstractMap.SimpleEntry<>(key, value);
            size++;
            return null;
        }

        V oldValue = ((Map.Entry<K, V>) table[index]).getValue();
        ((Map.Entry<K, V>) table[index]).setValue(value);
        return oldValue;
    }

    @Override
    public V remove(K key) {
        int i = 0;
        int index = hash(key, i);

        while (table[index] != null) {
            if (table[index] instanceof Map.Entry && ((Map.Entry<K, V>) table[index]).getKey().equals(key)) {
                V oldValue = ((Map.Entry<K, V>) table[index]).getValue();
                table[index] = new Tombstone();
                size--;
                return oldValue;
            }
            i++;
            index = hash(key, i);
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterable<Map.Entry<K, V>> entrySet() {
//        LinkedList<Map.Entry<K, V>> entries = new LinkedList<>();
//        for (Object entry : table) {
//            if (entry instanceof Map.Entry) {
//                entries.add((Map.Entry<K, V>) entry);
//            }
//        }
//        return entries;
        return null;
    }

    private void rehash() {
        Object[] oldTable = table;
        table = new Object[2 * oldTable.length + 1];

        for (Object entry : oldTable) {
            if (entry != null && !(entry instanceof Tombstone)) {
                Map.Entry<K, V> e = (Map.Entry<K, V>) entry;
                int i = 0;
                int index = hash(e.getKey(), i);

                while (table[index] != null) {
                    i++;
                    index = hash(e.getKey(), i);
                }

                table[index] = e;
            }
        }
    }

    private static class Tombstone {
    }
}
