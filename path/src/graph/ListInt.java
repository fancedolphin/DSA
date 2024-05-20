package graph;

public class ListInt extends AbsListInt {
    public ListInt(int capacity) {
        super(capacity);
    }

    @Override
    public void append(int n) {
        assert getSize() != getCapacity() : "List capacity exceeded";
        this.list[this.size]=n;
        size++;
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