package CHC5223;

//
//public class MemberBST implements IMemberDB {
//        public Node root;
//
//        private class Node {
//            private Member data;
//            private Node left, right;
//
//            public Node(Member data) {
//                this.data = data;
//                this.left = null;
//                this.right = null;
//            }
//
//        }
//
//        //constructor
//        public MemberBST() {
//            System.out.println("Binary Search Tree");
//            root = null;
//        }
//
//
//        // Implementations of IMemberDB interface methods
//        @Override
//        public void clearDB() {
//            root = null;
//        }
//
//        @Override
//        public boolean containsName(String name) {
//            if(getNode(name)!=null){
//                return true;
//            }
//            else{return false;}
//        }
//
//        @Override
//        public Member get(String name) {
//            assert name != null && !name.isEmpty() : "Name cannot be null or empty";
//            log.log("Start getting", name, "Root");
//            Node node = getNode(name);
//            return node != null ? node.data : null;
//        }
//
//        @Override
//        public int size() {
//            System.out.println("ENter root of tree");
//            return size(root);
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return root == null;
//        }
//
//        @Override
//        public Member put(Member member) {
//            assert member != null && !member.getName().isEmpty() : "Invalid member or empty name";
//            Node[] nodes = {null}; // To store the previous value
//            root = put(root, member.getName(), member, nodes);
//            return nodes[0] != null ? nodes[0].data : null;
//        }
//
//        @Override
//        public Member remove(String name) {
//            Node[] nodes = {null}; // To store the removed node
//            root = remove(root, name, nodes);
//            return nodes[0] != null ? nodes[0].data : null;
//        }
//
//        @Override
//        public void displayDB() {
//            displayDB(root);
//        }
//
//        private Node getNode(String name) {
//            Node current = root;
//           String path="root"
//            while (current != null) {
//                path += " -> " + current.data.getName();  // Update path
//                if (name.equals(current.data.getName())) {
//                    log.log("Found", name, path);
//                    return current;
//                } else if (name.compareTo(current.data.getName()) < 0) {
//                    current = current.left;
//                } else {
//                    current = current.right;
//                }
//            }
//            log.log(name + " not found. Visited Path:", path);
//            return null;
//        }
//
//
//
//    private Node put(Node node, String name, Member member, Node[] prevValue) {
//        if (node == null) {
//            return new Node(member);
//        }
//        int cmp = name.compareTo(node.data.getName());
//        if (cmp < 0) {
//            node.left = put(node.left, name, member, prevValue);
//        } else if (cmp > 0) {
//            node.right = put(node.right, name, member, prevValue);
//        } else {
//            prevValue[0] = node;
//            node.data = member;
//        }
//        return node;
//    }
//
//    private int size(Node node) {
//        if (node == null) {
//            return 0;
//        }
//        return 1 + size(node.left) + size(node.right);//recu
//    }
//
//    private Node remove(Node node, String name, Node[] removedNode) {
//        if (node == null) {
//            return null;
//        }
//        int cmp = name.compareTo(node.data.getName());
//        if (cmp < 0) {
//            node.left = remove(node.left, name, removedNode);
//        } else if (cmp > 0) {
//            node.right = remove(node.right, name, removedNode);
//        } else {
//            // Node to be removed found
//            removedNode[0] = node;
//            if (node.left == null) {
//                return node.right;
//            } else if (node.right == null) {
//                return node.left;
//            } else {
//                Node minNode = findMin(node.right);
//                node.data = minNode.data;
//                node.right = removeMin(node.right);
//            }
//        }
//        return node;
//    }
//
//    private Node findMin(Node node) {
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
//
//    private Node removeMin(Node node) {
//        if (node.left == null) {
//            return node.right;
//        }
//        node.left = removeMin(node.left);
//        return node;
//    }
//
//    private void displayDB(Node node) {
//        if (node != null) {
//            displayDB(node.left);
//            System.out.println(node.data.getName() + " - " + node.data.getAffiliation());
//            displayDB(node.right);
//        }
//    }
//
//}


public class MemberBST implements IMemberDB {
    private class Node {
        Member data;
        Node left, right;

        Node(Member data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public MemberBST() {
        System.out.println("Binary Search Tree");
        this.root = null;
    }

    @Override
    public void clearDB() {
        root = null;
        Logger.log("Database cleared");
    }

    @Override
    public boolean containsName(String name) {
        boolean found = find(name, "Root") != null;
        Logger.log("Check existence", name, found ? "Found" : "Not Found");
        return found;
    }

    @Override
    public Member get(String name) {
        Node result = find(name, "Root");
        if (result != null) {
            Logger.log("Retrieve", name, "Found");
            return result.data;
        } else {
            Logger.log("Retrieve", name, "Not Found");
            return null;
        }
    }

    @Override
    public int size() {
        int count = countNodes(root);
        Logger.log("Tree Size", "Total Members", String.valueOf(count));
        return count;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = root == null;
        Logger.log("Check if empty", "Tree is " + (empty ? "empty" : "not empty"), "");
        return empty;
    }


    @Override
    public Member put(Member member) {
        assert member != null && !member.getName().isEmpty() : "Member or name cannot be null";
        Logger.log("Start putting", member.getName(), "Root");
        root = insert(root, member, "Root");

        return member; // Indicate a new insertion
    }



    @Override
        public Member remove(String name) {
            Node[] nodes = {null}; // To store the removed node
            root = remove(root, name, nodes);
            return nodes[0] != null ? nodes[0].data : null;
        }

    @Override
    public void displayDB() {
        Logger.log("Display DB", "Start", "");
        printInOrder(root,"");
    }

    private Node insert(Node current, Member member, String path) {
        if (current == null) {
            Logger.log("Inserting", member.getName(), path);
            return new Node(member);
        }

        path += " -> " + current.data.getName();
        if (member.getName().compareTo(current.data.getName()) < 0) {
            current.left = insert(current.left, member, path);
        } else if (member.getName().compareTo(current.data.getName()) > 0) {
            current.right = insert(current.right, member, path);
        } else {
            Logger.log("Updating", member.getName(), path);
            current.data = member; // Replace existing member
        }
        return current;
    }
    private Node find(String name, String path) {
        Node current = root;
        while (current != null) {
            path += " -> " + current.data.getName();
            if (name.equals(current.data.getName())) {
                Logger.log("Found", name, path);
                return current;
            } else if (name.compareTo(current.data.getName()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        Logger.log("Not Found", name, path);
        return null;
    }
    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    private void printInOrder(Node node, String path) {
        if (node != null) {
            printInOrder(node.left, path + " (L)");
            Logger.log("Display Member", node.data.getName(), path + " -> " + node.data.getName());
            printInOrder(node.right, path + " (R)");
        }
    }


    private Node remove(Node node, String name, Node[] removedNode) {
        if (node == null) {
            Logger.log("Remove Attempt", name, "Node not found - Base case reached");
            return null; // Base case: name not found
        }

        int cmp = name.compareTo(node.data.getName());
        String path = "Visiting Node: " + (node.data != null ? node.data.getName() : "null");
        if (cmp < 0) {
            Logger.log("Remove", name, path + " - Going left");
            node.left = remove(node.left, name, removedNode);
        } else if (cmp > 0) {
            Logger.log("Remove", name, path + " - Going right");
            node.right = remove(node.right, name, removedNode);
        } else {
            // Node to be removed found
            removedNode[0] = node; // Store the removed node for external use
            Logger.log("Remove", name, path + " - Node found and will be removed");
            if (node.left == null) {
                return node.right; // If no left child, return right child to reconnect
            } else if (node.right == null) {
                return node.left; // If no right child, return left child to reconnect
            } else {
                // Node with two children, find the minimum node on the right subtree
                Node minNode = findMin(node.right);
                Logger.log("Remove", name, path + " - Two children, replacing with min node: " + minNode.data.getName());
                node.data = minNode.data; // Replace the data with min node from right subtree
                node.right = removeMin(node.right); // Remove the minimum node from the right subtree
            }
        }
        return node; // Return the modified tree root
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            Logger.log("Find Min", "Traversing", "Visiting Node: " + node.data.getName() + " - Going left");
            node = node.left;
        }
        Logger.log("Find Min", "Minimum found", "Node: " + node.data.getName());
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Logger.log("Remove Min", "Removing", "Node: " + node.data.getName() + " - No left child, returning right");
            return node.right;
        }
        Logger.log("Remove Min", "Traversing", "Visiting Node: " + node.data.getName() + " - Going left");
        node.left = removeMin(node.left);
        return node;
    }

}


