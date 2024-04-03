
// public class LinearProbingHashTable {
//     private Entry[] table;
//     private int size;
//     private int capacity;
//     private final double loadFactorThreshold;

//     // Entry class to hold key-value pairs
//     private static class Entry {
//         final String key;
//         String value;
//         boolean isActive; // to handle deleted items

//         Entry(String key, String value) {
//             this.key = key;
//             this.value = value;
//             this.isActive = true;
//         }
//     }

//     // Constructor
//     public LinearProbingHashTable(int capacity) {
//         this.capacity = capacity;
//         this.table = new Entry[capacity];
//         this.size = 0;
//         this.loadFactorThreshold = 0.75; // Resize when 75% full
//     }

//     // Custom hash function for strings
//     private int hash(String key) {
//         int hashValue = 0;
//         for (int i = 0; i < key.length(); i++) {
//             hashValue = (31 * hashValue + key.charAt(i)) % capacity;
//         }
//         return hashValue;
//     }

//     // Insert method
//     public void insert(String key, String value) {
//         if (key == null) throw new IllegalArgumentException("Key cannot be null");
//         if (loadFactor() > loadFactorThreshold) {
//             resize();
//         }
//         int index = hash(key);
//         while (table[index] != null && table[index].isActive) {
//             if (table[index].key.equals(key)) {
//                 table[index].value = value; // Update existing key
//                 return;
//             }
//             index = (index + 1) % capacity;
//         }
//         table[index] = new Entry(key, value);
//         size++;
//     }

//     // Search method
//     public String search(String key) {
//         int index = hash(key);
//         while (table[index] != null) {
//             if (table[index].isActive && table[index].key.equals(key)) {
//                 return table[index].value;
//             }
//             index = (index + 1) % capacity;
//         }
//         return null; // Not found
//     }

//     // Delete method
//     public void delete(String key) {
//         int index = hash(key);
//         while (table[index] != null) {
//             if (table[index].isActive && table[index].key.equals(key)) {
//                 table[index].isActive = false;
//                 size--;
//                 return;
//             }
//             index = (index + 1) % capacity;
//         }
//         throw new RuntimeException("Key not found");
//     }

//     // Load factor method
//     public double loadFactor() {
//         return (double) size / capacity;
//     }

//     // Resize method
//     // Modified displayStructure method to use the table array
//     public void displayStructure() {
//         System.out.println("Table structure:");
//         for (int i = 0; i < capacity; i++) {
//             if (table[i] != null && table[i].isActive) {
//                 System.out.println("Index " + i + ": Key = " + table[i].key + ", Value = " + table[i].value);
//             } else {
//                 System.out.println("Index " + i + ": null or inactive");
//             }
//         }
//     }

//     private void resize() {
//         int newCapacity = capacity * 2; // Double the size
//         LinearProbingHashTable temp = new LinearProbingHashTable(newCapacity);
//         for (int i = 0; i < capacity; i++) {
//             if (table[i] != null && table[i].isActive) {
//                 temp.insert(table[i].key, table[i].value);
//             }
//         }
//         table = temp.table;
//         capacity = temp.capacity;
//     }
// }
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

    private int hash(String key) {
        // Custom hash function for string keys
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = (hash * 31 + c) % keys.length;
        }
        return hash;
    }

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
        System.out.println("Hash Table:");
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


