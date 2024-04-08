public class ChainingHashTable {
    private DoubleLinkedL<Integer>[] chains; // Array of indices
    private String[] keys;
    private String[] values;
    private int size;
    private int capacity;
    // Constructor
    public ChainingHashTable(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 8);
        this.chains = (DoubleLinkedL<Integer>[]) new DoubleLinkedL[capacity];
        this.keys = new String[capacity];
        this.values = new String[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            chains[i] = new DoubleLinkedL<>();
        }
    }

    // Hash function
    private int hash(String key) {
        int hashValue = 0;
        int prime = 1031; // Prime number near 2^10
        for (int i = 0; i < key.length(); i++) {
            hashValue = (prime * hashValue + key.charAt(i)) ;
        }
        if(hashValue<0){
            hashValue+=0x7fffffff;
        }
        return hashValue % capacity;
    }

    public void insert(String key, String value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        // Check if the key already exists and update the value
        for (int i = 0; i < chains[index].getSize(); i++) {
            int chainIndex = chains[index].getData(i);
            if (keys[chainIndex].equals(key)) {
                values[chainIndex] = value;
                return;
            }
        }
        chains[index].insertAtFront(size); // Insert the index of the new key-value pair
        keys[size] = key;
        values[size] = value;
        size++;
        if (size == capacity) {
            resize(2 * capacity); // Resize if necessary
        }
    }

    public String search(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        for (int i = 0; i < chains[index].getSize(); i++) {
            int chainIndex = chains[index].getData(i);
            if (keys[chainIndex].equals(key)) {
                return values[chainIndex];
            }
        }
        return null; // Key not found
    }

    public String[] access(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");
        return new String[]{keys[index], values[index]};
    }

    // Delete method to remove a key-value pair
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int index = hash(key);
        for (int i = 0; i < chains[index].getSize(); i++) {
            int chainIndex = chains[index].getData(i); //
            if (keys[chainIndex].equals(key)) {
                chains[index].delete(i); // Delete the index from the chain
                keys[chainIndex] = null;
                values[chainIndex] = null;
                size--;
                return;
            }
        }
        throw new RuntimeException("Key not found");
    }


    // Resize method to increase the capacity of the hash table
    private void resize(int newCapacity) {
        ChainingHashTable temp = new ChainingHashTable(newCapacity);
        for (int i = 0; i < size; i++) {
            if (keys[i] != null) {
                temp.insert(keys[i], values[i]); // Rehash
            }
        }
        chains = temp.chains;
        keys = temp.keys;
        values = temp.values;
        capacity = temp.capacity;
    }

    public double loadFactor() {
        return (double) size / capacity;
    }

    public void displayStructure() {
        System.out.println("Chain Hash Table:");
        for (int i = 0; i < this.capacity; i++) {
            DoubleLinkedL<Integer> chain = this.chains[i];
            if (!chain.isEmpty()) {
                System.out.print("Index " + i + ": ");
                for (int j = 0; j < chain.getSize(); j++) {
                    int chainIndex = chain.getData(j);
                    System.out.print(keys[chainIndex] + " -> " + values[chainIndex]);
                    if (j < chain.getSize() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("Load factor: " + this.loadFactor());
        System.out.println();
    }


}








