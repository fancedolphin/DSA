package graph;

public class test {
    public static void main(String[] args) {
        // Test StackInt
        StackInt stack = new StackInt(10);
        testStack(stack);

        // Test QueueInt
        QueueInt queue = new QueueInt(10);
        testQueue(queue);

        // Test ListInt
        ListInt list = new ListInt(10);
        testList(list);

        // Test SetInt
        SetInt set = new SetInt(10);
        testSet(set);
    }

    private static void testStack(StackInt stack) {
        System.out.println("Testing StackInt...");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Expected size after pushes: 3, Actual size: " + stack.getSize());
        System.out.println("Expected top after pushes: 3, Actual top: " + stack.peek());
        stack.pop();
        System.out.println("Expected size after pop: 2, Actual size: " + stack.getSize());
    }

    private static void testQueue(QueueInt queue) {
        System.out.println("Testing QueueInt...");
        queue.addToBack(1);
        queue.addToBack(2);
        queue.addToBack(3);
        System.out.println("Expected size after adds: 3, Actual size: " + queue.getSize());
        System.out.println("Expected front after adds: 1, Actual front: " + queue.removefromFront());
        System.out.println("Expected size after remove: 2, Actual size: " + queue.getSize());
    }

    private static void testList(ListInt list) {
        System.out.println("Testing ListInt...");
        list.append(1);
        list.append(2);
        list.append(3);
        System.out.println("Expected size after appends: 3, Actual size: " + list.getSize());
        System.out.println("Does the list contain 2? " + list.contains(2));
    }

    private static void testSet(SetInt set) {
        System.out.println("Testing SetInt...");
        set.include(1);
        set.include(2);
        set.include(3);
        System.out.println("Expected size after includes: 3, Actual size: " + set.getSize());
        set.exclude(2);
        System.out.println("Expected size after exclude: 2, Actual size: " + set.getSize());
        System.out.println("Does the set contain 2? " + set.contains(2));
    }
}



