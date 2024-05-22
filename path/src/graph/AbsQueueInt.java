package graph; // or whatever
/**
 * Abstract class to be sub-classed by class(es) that represent stacks of ints
 *
 * You may change the package name for this, but you should not
 * modify it in any other way.
 *
 */
abstract public class AbsQueueInt {

    protected int queue[];
    protected int size; // 0 <= size <= capacity
    protected final int capacity;
    public AbsQueueInt(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.queue = new int[capacity];
    }
    public int getCapacity() {return capacity;}
    public int getSize() {return size;}
    /**
     * @param n node to be added
     * @pre getSize() != getCapacity()
     * @post n has been added to back of queue
     */
    abstract public void addToBack(int n);
    /**
     * @pre getSize() != 0
     * @post element at front of queue has been removed
     * @return value that has been removed */
    abstract public int removefromFront();
}
