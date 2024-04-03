public class ChainingHashTable {
    private DoubleLinkedL<Entry>[] table;
    private int size;
    private int capacity;
    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
//cons
    public ChainingHashTable(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 8);
        this.table = (DoubleLinkedL<Entry>[]) new DoubleLinkedL[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new DoubleLinkedL<>();
        }
    }
//hash func
    private int hash(String key) {
        int hashValue = 0;
        int prime = 1031; // Prime number near 2^10
        for (int i = 0; i < key.length(); i++) {
            hashValue = (prime * hashValue + key.charAt(i)) ;
        }
        if (hashValue < 0) {
            hashValue +=Integer.MAX_VALUE;
             }
        return hashValue % capacity;
    }
//add
    public void insert(String key, String value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (size >= 0.75 * capacity) resize(2 * capacity); // Resize if necessary
        int index = hash(key);
        DoubleLinkedL<Entry> chain = table[index];
        for (int j = 0; j < chain.getSize(); j++) {
            Entry entry = chain.get(j);
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        chain.insertAtFront(new Entry(key, value));
        if (chain.getSize() > 8) {
            throw new IllegalStateException("Chain size exceeded the limit of 8");
        }
        size++;
        displayLoadFactor();
    }
//search
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
        return null;
    }

    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        DoubleLinkedL<Entry> chain = table[index];
        for (int j = 0; j < chain.getSize(); j++) {
            Entry entry = chain.get(j);
            if (entry.key.equals(key)) {
                chain.delete(j); // Delete the entry
                size--;
                displayLoadFactor();
                return;
            }
        }
        throw new RuntimeException("Key not found");
    }

    public double loadFactor() {
        return (double) size / capacity;
    }

    private void displayLoadFactor() {
        System.out.println("Current load factor: " + loadFactor());
    }
//increase capacity
    private void resize(int newCapacity) {
        ChainingHashTable temp = new ChainingHashTable(newCapacity);
        for (int i = 0; i < capacity; i++) {
            DoubleLinkedL<Entry> chain = table[i];
            for (int j = 0; j < chain.getSize(); j++) {
                Entry entry = chain.get(j);
                temp.insert(entry.key, entry.value); // Rehash
            }
        }
        table = temp.table;
        capacity = temp.capacity;
        size = temp.size;
    }

    public void displayStructure(ChainingHashTable hashTable) {
        System.out.println("Hash Table:");
        for (int i = 0; i < hashTable.capacity; i++) {
            DoubleLinkedL<ChainingHashTable.Entry> chain = hashTable.table[i];
            if (chain.getSize() > 0) {
                System.out.print("Index " + i + ": ");
                for (int j = 0; j < chain.getSize(); j++) {
                    ChainingHashTable.Entry entry = chain.get(j);
                    System.out.print(entry.key + " -> " + entry.value);
                    if (j < chain.getSize() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("Load factor: " + hashTable.loadFactor());
        System.out.println();
    }
}








