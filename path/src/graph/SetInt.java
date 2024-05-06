package graph;

public class SetInt extends AbsSetInt {
    public SetInt(int capacity) {
        super(capacity);
    }

    @Override
    public void include(int n) {
        assert size < capacity : "Set already contains element or capacity exceeded";
        if (!contains(n) && size < capacity) {
            set[size++] = n;
        }
    }

    @Override
    public void exclude(int n) {
        for (int i = 0; i < size; i++) {
            if (set[i] == n) {
                set[i] = set[size - 1];
                size--;
                break;
            }
        }
    }

    @Override
    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            if (set[i] == x) {
                return true;
            }
        }
        return false;
    }
}
