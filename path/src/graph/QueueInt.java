package graph;

public class QueueInt extends AbsQueueInt {
    private int front = 0;
    private int rear = -1;

    public QueueInt(int capacity) {
        super(capacity);
    }

    @Override
    public void addToBack(int n) {
        assert getSize() != getCapacity() : "Queue capacity exceeded";
        rear = (rear + 1) % capacity;
        queue[rear] = n;
        size++;
    }

    @Override
    public int removefromFront() {
        assert getSize() != 0 : "Queue is empty";
        int value = queue[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }
}

