public class Main {
    public static void main(String[] args) {
        // Initialize both hash tables
        LinearProbingHashTable linearHashTable = new LinearProbingHashTable();
        ChainingHashTable chainingHashTable = new ChainingHashTable(3);

        // Insert key-value pairs into both hash tables
        String[] keys = {"key1", "key2", "key3", "key4", "key5", "key6", "key7", "key8", "key9", "key10", "key11", "key12", "key13", "key517"};
        String[] values = {"value1", "value2", "value3", "value4", "value5", "value6", "value7", "value8", "value9", "value10", "value11", "value12", "value13", "value517"};

        for (int i = 0; i < keys.length; i++) {
            linearHashTable.put(keys[i], values[i]);
            linearHashTable.printHashTable(linearHashTable);
        }
        for (int i = 0; i < keys.length; i++) {
            chainingHashTable.insert(keys[i], values[i]);
            chainingHashTable.displayStructure();
        }

        // Search and delete operations for 'key3' in both hash tables
        System.out.println("Search result for 'key3' in Linear Probing Hash Table: " + linearHashTable.search("key3"));
        linearHashTable.delete("key3");
        System.out.println("Search result for 'key3' after deletion in Linear Probing Hash Table: " + linearHashTable.search("key3"));

        System.out.println("Search result for 'key517' in Chaining Hash Table: " + chainingHashTable.search("key517"));
        chainingHashTable.delete("key517");
        System.out.println("Search result for 'ke517' after deletion in Chaining Hash Table: " + chainingHashTable.search("key517"));
        chainingHashTable.displayStructure();
    }
}
