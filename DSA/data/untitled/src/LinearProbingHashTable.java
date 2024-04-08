public class LinearProbingHashTable {
    private static int INITIAL_CAPACITY = 20;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;

    private String[] keys;
    private String[] values;
    private int size;

    public LinearProbingHashTable() {
        keys = new String[INITIAL_CAPACITY];
        values = new String[INITIAL_CAPACITY];
        size = 0;
    }
//hash function
    private int hash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = (hash * 31 + c) % keys.length;
        }
        return hash;
    }
//add key
    public void put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        if (size >= LOAD_FACTOR_THRESHOLD * keys.length) {
            resize();
        }
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index]==key) {
                // Key already exists, update value
                values[index] = value;
                return;
            }
            index = (index + 1) % keys.length;
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }
//search
    public String search(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index]==key) {
                return values[index];
            }
            index = (index + 1) % keys.length;
        }
        return null; // Key not found
    }
//access value
    public String get(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index]==key) {
                return values[index];
            }
            index = (index + 1) % keys.length;
        }
        return null; // Key not found
    }

    public void delete(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                keys[index] = null;
                values[index] = null;
                size--;
                return;
            }
            index = (index + 1) % keys.length;
        }
    }
//increase capacity
    private void resize() {
        int newCapacity = keys.length * 2;
        String[] newKeys = new String[newCapacity];
        String[] newValues = new String[newCapacity];

        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                int newIndex = hash(keys[i]);
                while (newKeys[newIndex] != null) {
                    newIndex = (newIndex + 1) % newCapacity;
                }
                newKeys[newIndex] = keys[i];
                newValues[newIndex] = values[i];
            }
        }

        keys = newKeys;
        values = newValues;
    }

    public double getLoadFactor() {
        return (double) size / keys.length;
    }

    public void printHashTable(LinearProbingHashTable hashTable) {
        System.out.println("Liner Hash Table:");
        for (int i = 0; i < hashTable.keys.length; i++) {
            if (hashTable.keys[i] != null) {
                System.out.print("Index " + i + ": " + hashTable.keys[i] + " -> " + hashTable.values[i]);
                if (hashTable.hash(hashTable.keys[i]) != i) {
                    System.out.print(" (Collision, rehashed to index " + hashTable.hash(hashTable.keys[i]) + ")");
                }
                System.out.println();
            }
        }
        System.out.println("Load factor: " + hashTable.getLoadFactor());
        System.out.println();
    
}
}


