public class DoubleLinkedL<T> {
    private Node<T> head;
    private int size;

    public DoubleLinkedL() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void insertAtFront(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void deleteFromFront() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot delete from an empty list.");
        } else {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
        }
    }
    public Node<T> getHead() {
        return head;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            deleteFromFront();
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        size--;
    }

    public T getData(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

}

