package graph; // or whatever
/**
 * Abstract class to be sub-classed by class(es) that represent stacks of ints
 *
 * You may change the package name for this, but you should not
 * modify it in any other way.
 *
 */
abstract public class AbsSetInt {
    protected int set[];
    protected int size; // 0 <= size <= capacity
    protected final int capacity;
    /**
     * @param capacity -- maximum capacity of this queue
     * @pre capacity >= 0
     * @post new set of current size zero has been created
     */
    public AbsSetInt(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.set = new int[capacity];
    }
    public int getCapacity() {return capacity;}
    public int getSize() {return size;}

    /**
     * @param x -- value to be sought
     * @pre true
     * @return true iff x is in list*/
    abstract public boolean contains(int x);

    /**
     * @param n node to be added
     * @pre contains(n) || getSize() != getCapacity()
     * @post contains(n)
     */
    abstract public void include(int n);
    /**
     * @pre true
     * @post !contains(n)
     */
    abstract public void exclude(int n);
}

