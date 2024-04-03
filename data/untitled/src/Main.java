// public class Main {
//     public static void main(String[] args) {
//         // Test LinearProbingHashTable
//         System.out.println("Testing LinearProbingHashTable:");
//         LinearProbingHashTable linearProbingHashTable = new LinearProbingHashTable(20);
//         linearProbingHashTable.put("key1", "value1");
//         linearProbingHashTable.put("key2", "value2");
//         linearProbingHashTable.put("key3", "value3"); // This should cause a collision
//         linearProbingHashTable.put("key4", "value4");
//         linearProbingHashTable.put("key5", "value5"); // This should trigger a resize

//         System.out.println("Search result for 'key3': " + linearProbingHashTable.search("key3"));
//         linearProbingHashTable.delete("key3");
//         System.out.println("Search result for 'key3' after deletion: " + linearProbingHashTable.search("key3"));

//         // Test ChainingHashTable
//         System.out.println("\nTesting ChainingHashTable:");
//         ChainingHashTable chainingHashTable = new ChainingHashTable(5);
//         chainingHashTable.insert("key1", "value1");
//         chainingHashTable.insert("key2", "value2");
//         chainingHashTable.insert("key3", "value3"); // This should cause a collision
//         chainingHashTable.insert("key4", "value4");
//         chainingHashTable.insert("key5", "value5"); // This should not trigger a resize

//         System.out.println("Search result for 'key3': " + chainingHashTable.search("key3"));
//         chainingHashTable.delete("key3");
//         System.out.println("Search result for 'key3' after deletion: " + chainingHashTable.search("key3"));

//         // Display the inner structure of the hash tables
//         System.out.println("\nInner structure of LinearProbingHashTable:");
//         linearProbingHashTable.displayStructure();

//         System.out.println("\nInner structure of ChainingHashTable:");
//         chainingHashTable.displayStructure();
//     }
// }

public class Main{
public static void main(String[] args) {
    LinearProbingHashTable hashTable = new LinearProbingHashTable();

    // Inserting 15 key-value pairs
    hashTable.put("apple", "red");
    hashTable.printHashTable(hashTable);
    hashTable.put("banana", "yellow");
    hashTable.printHashTable(hashTable);
    hashTable.put("cherry", "red");
    hashTable.printHashTable(hashTable);
    hashTable.put("date", "brown");
    hashTable.printHashTable(hashTable);
    hashTable.put("eggplant", "purple");
    hashTable.printHashTable(hashTable);
    hashTable.put("fig", "green");
    hashTable.printHashTable(hashTable);
    hashTable.put("grape", "purple");
    hashTable.printHashTable(hashTable);
    hashTable.put("honeydew", "green");
    hashTable.printHashTable(hashTable);
    hashTable.put("kiwi", "brown");
    hashTable.printHashTable(hashTable);
    hashTable.put("lemon", "yellow");
    hashTable.printHashTable(hashTable);
    hashTable.put("mango", "orange");
    hashTable.printHashTable(hashTable);
    hashTable.put("nectarine", "orange");
    hashTable.printHashTable(hashTable);
    hashTable.put("orange", "orange");
    hashTable.printHashTable(hashTable);
    hashTable.put("pear", "green");
    hashTable.printHashTable(hashTable);
    hashTable.put("quince", "yellow");
    hashTable.printHashTable(hashTable);

    // Additional key-value pair to trigger resize
    hashTable.put("raspberry", "red");
    hashTable.printHashTable(hashTable);
}
}
