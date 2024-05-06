package graph; // or whatever
/**
 * Abstract class to be sub-classed by class(es) that represent stacks of ints
 *
 * You may change the package name for this, but you should not
 * modify it in any other way.
 *
 */
abstract public class AbsStackInt {
    protected int stack[];
    protected int size; // 0 <= size <= capacity
    protected final int capacity;
    /**
     * @param capacity -- maximum capacity of this queue
     * @pre capacity >= 0
     * @post new stack of current size zero has been created
     */
    public AbsStackInt(int capacity){
        this.capacity = capacity;
        this.size = 0;
        stack = new int[capacity];
    }
    public int getCapacity() {return capacity;}
    public int getSize() {return size;}
    /**
     * @param n node to be added
     * @pre getSize() != getCapacity()
     * @post n has been pushed on to top of stack
     */
    abstract public void push(int n);
    /**
     * @pre getSize() != 0
     * @post element on top of stack has been removed
     * @return value that has been removed */
    abstract public int pop();
    /**
     * @pre getSize() != 0
     * @return value on top of stack */
    abstract public int peek() ;

}
