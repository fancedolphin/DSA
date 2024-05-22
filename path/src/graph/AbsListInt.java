package graph; // or whatever
/**
 * Abstract class to be sub-classed by class(es) that represent lists of ints
 *
 * You may change the package name for this, but you should not
 * modify it in any other way.
 *
 */
abstract public class AbsListInt {
    protected int list[];
    protected int size; // 0 <= size <= capacity
    protected final int capacity;
    /**
     * @param capacity -- maximum capacity of this list
     * @post new list of current size zero has been created
     */
    public AbsListInt(int capacity){
        // implements a bounded list of int values
        this.capacity = capacity;
        this.size = 0;
        list = new int[capacity];
    }
    public int getCapacity() {return capacity;}
    public int getSize() {return size;}
    /**
     * @param n node to be added
     * @pre getSize() != getCapacity()
     * @post n has been appended to list
     */
    abstract public void append(int n);

    /**
     * @param x -- value to be sought
     * @pre true
     * @return true if x is in list*/
    abstract public boolean contains(int x);

}

