/*
public class LinearProbingHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 20;
    private int capacity;
    private int size;
    private K[] keys;
    private V[] values;

    public LinearProbingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    private int hash(K key) {
        return (key.toString().length() * 31) % capacity;
    }

    public void insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (size >= capacity * 0.75) resize(capacity * 2);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        keys[i] = key;
        values[i] = value;
        size++;
        displayLoadFactor();
    }

    public V search(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(K key) {
        if (!contains(key)) {
            throw new RuntimeException("Key not found: " + key);
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % capacity;
        while (keys[i] != null) {
            K keyToRehash = keys[i];
            V valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            insert(keyToRehash, valToRehash);
            i = (i + 1) % capacity;
        }

        size--;
        if (size > 0 && size <= capacity / 8) resize(capacity / 2);
        displayLoadFactor();
    }

    private boolean contains(K key) {
        return search(key) != null;
    }

    private void resize(int newCapacity) {
        LinearProbingHashTable<K, V> temp = new LinearProbingHashTable<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                temp.insert(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        capacity = temp.capacity;
    }

    private void displayLoadFactor() {
        System.out.println("Load factor: " + (double) size / capacity);
    }
}*/
public class LinearProbingHashTable {
    private Entry[] table;
    private int size;
    private int capacity;
    private final double loadFactorThreshold;

    // Entry class to hold key-value pairs
    private static class Entry {
        final String key;
        String value;
        boolean isActive; // to handle deleted items

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
            this.isActive = true;
        }
    }

    // Constructor
    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
        this.loadFactorThreshold = 0.75; // Resize when 75% full
    }

    // Custom hash function for strings
    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (31 * hashValue + key.charAt(i)) % capacity;
        }
        return hashValue;
    }

    // Insert method
    public void insert(String key, String value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (loadFactor() > loadFactorThreshold) {
            resize();
        }
        int index = hash(key);
        while (table[index] != null && table[index].isActive) {
            if (table[index].key.equals(key)) {
                table[index].value = value; // Update existing key
                return;
            }
            index = (index + 1) % capacity;
        }
        table[index] = new Entry(key, value);
        size++;
    }

    // Search method
    public String search(String key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].isActive && table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + 1) % capacity;
        }
        return null; // Not found
    }

    // Delete method
    public void delete(String key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].isActive && table[index].key.equals(key)) {
                table[index].isActive = false;
                size--;
                return;
            }
            index = (index + 1) % capacity;
        }
        throw new RuntimeException("Key not found");
    }

    // Load factor method
    public double loadFactor() {
        return (double) size / capacity;
    }

    // Resize method
    // Modified displayStructure method to use the table array
    public void displayStructure() {
        System.out.println("Table structure:");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].isActive) {
                System.out.println("Index " + i + ": Key = " + table[i].key + ", Value = " + table[i].value);
            } else {
                System.out.println("Index " + i + ": null or inactive");
            }
        }
    }

    private void resize() {
        int newCapacity = capacity * 2; // Double the size
        LinearProbingHashTable temp = new LinearProbingHashTable(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].isActive) {
                temp.insert(table[i].key, table[i].value);
            }
        }
        table = temp.table;
        capacity = temp.capacity;
    }
}

