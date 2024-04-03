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

    System.out.println("Search result for 'pear': " + hashTable.search("pear"));
    hashTable.delete("pear");
    System.out.println("Search result for 'pear' after deletion: " + hashTable.search("pear"));

    // Test ChainingHashTable
     System.out.println("\nTesting ChainingHashTable:");
     ChainingHashTable chainingHashTable = new ChainingHashTable(3);
     chainingHashTable.insert("key1", "value1");
    chainingHashTable.displayStructure(chainingHashTable);
     chainingHashTable.insert("key2", "value2");
    chainingHashTable.displayStructure(chainingHashTable);
     chainingHashTable.insert("key3", "value3");
    chainingHashTable.displayStructure(chainingHashTable);
     chainingHashTable.insert("key4", "value4");
    chainingHashTable.displayStructure(chainingHashTable);
     chainingHashTable.insert("key5", "value5");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key6", "value6");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key7", "value7");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key8", "value8");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key9", "value9");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key10", "value10");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key11", "value11");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key12", "value12");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key13", "value13");
    chainingHashTable.displayStructure(chainingHashTable);
    chainingHashTable.insert("key517", "value517");
    chainingHashTable.displayStructure(chainingHashTable);

     System.out.println("Search result for 'key3': " + chainingHashTable.search("key3"));
     chainingHashTable.delete("key3");
     System.out.println("Search result for 'key3' after deletion: " + chainingHashTable.search("key3"));

}
}
