/*
public class ChainingHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 8;
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;

    public ChainingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public ChainingHashTable(int capacity) {
        this.capacity = capacity;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        int hashValue = 0;
        String keyString = key.toString();
        for (int i = 0; i < keyString.length(); i++) {
            hashValue = (hashValue + keyString.charAt(i) * i) % capacity;
        }
        return hashValue;
    }

    public void insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].insertAtFront(new Entry<>(key, value));
        size++;
        if ((1.0 * size) / capacity >= 0.75) {
            resize(2 * capacity);
        }
        displayLoadFactor();
    }

    public V search(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void delete(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> entries = table[index];
        for (int i = 0; i < entries.getSize(); i++) {
            if (entries.get(i).key.equals(key)) {
                entries.delete(i);
                size--;
                displayLoadFactor();
                return;
            }
        }
        throw new RuntimeException("Key not found: " + key);
    }

    private void resize(int newCapacity) {
        ChainingHashTable<K, V> temp = new ChainingHashTable<>(newCapacity);
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                temp.insert(entry.key, entry.value);
            }
        }
        table = temp.table;
        capacity = temp.capacity;
    }

    private void displayLoadFactor() {
        System.out.println("Load factor: " + (double) size / capacity);
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
*/
public class ChainingHashTable {
    private DoubleLinkedL<Entry>[] table;
    private int size;
    private int capacity;

    // Entry class to hold key-value pairs
    private static class Entry {
        final String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public ChainingHashTable(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 8); // Ensure capacity is at least 8
        this.table = (DoubleLinkedL<Entry>[]) new DoubleLinkedL[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new DoubleLinkedL<>();
        }
    }

    // Custom hash function for strings
    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue = 31 * hashValue + key.charAt(i);
        }
        return Math.abs(hashValue) % capacity;
    }

    // Insert method
    // Insert method using traditional for loop and get method
    public void insert(String key, String value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (size >= 0.75 * capacity) resize(2 * capacity);
        int index = hash(key);
        DoubleLinkedL<Entry> chain = table[index];
        for (int j = 0; j < chain.getSize(); j++) {
            Entry entry = chain.get(j);
            if (entry.key.equals(key)) {
                entry.value = value; // Update existing key
                return;
            }
        }
        chain.insertAtFront(new Entry(key, value));
        size++;
        displayLoadFactor();
    }

    // Search method using traditional for loop and get method
    public String search(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        DoubleLinkedL<Entry> chain = table[index];
        for (int j = 0; j < chain.getSize(); j++) {
            Entry entry = chain.get(j);
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Not found
    }


    // Delete method
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        DoubleLinkedL<Entry> chain = table[index];
        for (int j = 0; j < chain.getSize(); j++) {
            Entry entry = chain.get(j);
            if (entry.key.equals(key)) {
                chain.delete(j); // Delete the entry at the found index
                size--;
                displayLoadFactor();
                return;
            }
        }
        throw new RuntimeException("Key not found");
    }

    // Load factor method
    public double loadFactor() {
        return (double) size / capacity;
    }

    // Display load factor
    private void displayLoadFactor() {
        System.out.println("Current load factor: " + loadFactor());
    }

    // Resize method

    private void resize(int newCapacity) {
        ChainingHashTable temp = new ChainingHashTable(newCapacity);
        for (int i = 0; i < capacity; i++) {
            DoubleLinkedL<Entry> chain = table[i];
            for (int j = 0; j < chain.getSize(); j++) {
                Entry entry = chain.get(j);
                temp.insert(entry.key, entry.value);
            }
        }
        table = temp.table;
        capacity = temp.capacity;
        size = temp.size;
    }// Method to display the structure of the ChainingHashTable
    public void displayStructure() {
        System.out.println("Hash Table Structure:");
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            DoubleLinkedL<Entry> chain = table[i];
            if (chain.isEmpty()) {
                System.out.println("empty");
            } else {
                for (int j = 0; j < chain.getSize(); j++) {
                    Entry entry = chain.get(j);
                    System.out.print("[Key: " + entry.key + ", Value: " + entry.value + "] -> ");
                }
                System.out.println("null");
            }
        }
    }
}


// DoubleLinkedL class and Node class remain unchanged




