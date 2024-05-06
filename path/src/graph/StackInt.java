package graph;

public class StackInt extends AbsStackInt {
    public StackInt(int capacity) {
        super(capacity);
    }

    @Override
    public void push(int n) {
        assert getSize() != getCapacity() : "Stack capacity exceeded";
        stack[size++] = n;
    }

    @Override
    public int pop() {
        assert getSize() != 0 : "Stack is empty";
        return stack[--size];
    }

    @Override
    public int peek() {
        assert getSize() != 0 : "Stack is empty";
        return stack[size - 1];
    }
}
