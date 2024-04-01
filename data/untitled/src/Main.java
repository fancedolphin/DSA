public class Main {
    public static void main(String[] args) {
        // Test LinearProbingHashTable
        System.out.println("Testing LinearProbingHashTable:");
        LinearProbingHashTable linearProbingHashTable = new LinearProbingHashTable(20);
        linearProbingHashTable.insert("key1", "value1");
        linearProbingHashTable.insert("key2", "value2");
        linearProbingHashTable.insert("key3", "value3"); // This should cause a collision
        linearProbingHashTable.insert("key4", "value4");
        linearProbingHashTable.insert("key5", "value5"); // This should trigger a resize

        System.out.println("Search result for 'key3': " + linearProbingHashTable.search("key3"));
        linearProbingHashTable.delete("key3");
        System.out.println("Search result for 'key3' after deletion: " + linearProbingHashTable.search("key3"));

        // Test ChainingHashTable
        System.out.println("\nTesting ChainingHashTable:");
        ChainingHashTable chainingHashTable = new ChainingHashTable(5);
        chainingHashTable.insert("key1", "value1");
        chainingHashTable.insert("key2", "value2");
        chainingHashTable.insert("key3", "value3"); // This should cause a collision
        chainingHashTable.insert("key4", "value4");
        chainingHashTable.insert("key5", "value5"); // This should not trigger a resize

        System.out.println("Search result for 'key3': " + chainingHashTable.search("key3"));
        chainingHashTable.delete("key3");
        System.out.println("Search result for 'key3' after deletion: " + chainingHashTable.search("key3"));

        // Display the inner structure of the hash tables
        System.out.println("\nInner structure of LinearProbingHashTable:");
        linearProbingHashTable.displayStructure();

        System.out.println("\nInner structure of ChainingHashTable:");
        chainingHashTable.displayStructure();
    }
}


