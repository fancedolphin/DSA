package CHC5223;

/**
 *
 * an objects of a class implementing this interface holds a
 * database of member information

 * DO NOT CHANGE THIS INTERFACE
 * You must create a class that implements this interface
 *
 */

public interface IMemberDB {

    /**
     * Empties the database.
     * @pre true
     */
    public void clearDB();

    /**
     * Determines whether a member's name exists as a key inside the database
     * @pre name is not null and not empty string
     * @param name the member name (key) to locate
     * @return true if the name exists as a key in the database
     */
    public boolean containsName(String name);

    /**
     * Returns a Member object mapped to the supplied name.
     * @pre name not null and not empty string
     * @param name The Member name (key) to locate
     * @return the Member object mapped to the key name if the name
    exists as key in the database, otherwise null
     */
    public Member get(String name);

    /**
     * Returns the number of members in the database
     * @pre true
     * @return number of members in the database.
     */
    public int size();

    /**
     * Determines if the database is empty or not.
     * @pre true
     * @return true iff the database is empty
     */
    public boolean isEmpty();

    /**
     * Inserts a Member object into the database, with the key of the supplied
     * member's name.
     * Note: If the name already exists as a key, then then the original entry
     * is overwritten.
     * This method must return the previous associated value
     * if one exists, otherwise null
     *
     * @pre member not null and member name not empty string
     */
    public Member put(Member member);

    /**
     * Removes and returns a member from the database, with the key
     * the supplied name.
     * @param name The name (key) to remove.
     * @pre name not null and name not empty string
     * @return the removed member object mapped to the name, or null if
     * the name does not exist.
     */
    public Member remove(String name);

    /**
     * Prints the names and affiliations of all the members in the database in
     * alphabetic order.
     * @pre true
     */
    public void displayDB();
}


