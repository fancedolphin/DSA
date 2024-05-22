package graph;

public class ListInt extends AbsListInt {
    public ListInt(int capacity) {
        super(capacity);
    }

    @Override
    public void append(int n) {
        assert getSize() != getCapacity() : "List capacity exceeded";
        list[size++] = n;
    }

    @Override
    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            if (list[i] == x) {
                return true;
            }
        }
        return false;
    }
}