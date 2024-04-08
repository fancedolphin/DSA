 package CHC5223;


public class MemberBST implements IMemberDB {
        public Node root;

        private class Node {
            private Member data;
            private Node left, right;

            public Node(Member data) {
                this.data = data;
                this.left = null;
                this.right = null;
            }

        }

        //constructor
        public MemberBST() {
            System.out.println("Binary Search Tree");
            root = null;
        }
        public MemberBST(Node member){
                this.root=new Node (member);
                 System.out.println("Binary Search Tree");
        }
       
        // Implementations of IMemberDB interface methods
        @Override
        public void clearDB() {
            root = null;
        }

        @Override
        public boolean containsName(String name) {
//            if(getNode(name)!=null){
//                return true;
//            }
//            else{return false;}
            return getNode(name)!=null;
        }

        @Override
        public Member get(String name) {
            Node node = getNode(name);
            return node != null ? node.data : null;
        }

        @Override
        public int size() {
            System.out.println("ENter root of tree");
            return size(root);
        }

        @Override
        public boolean isEmpty() {
            return root == null;
        }

        @Override
        public Member put(Member member) {
//            if (member == null || member.getName().isEmpty()) {
//                throw new IllegalArgumentException("Member cannot be null");
//            }
// ·µ»ØÉÏÒ»½Úµãdata
            Node[] nodes = {null}; // To store the previous value
            root = put(root, member.getName(), member, nodes);
            return nodes[0] != null ? nodes[0].data : null;
        }

        @Override
        public Member remove(String name) {
            Node[] nodes = {null}; // To store the removed node
            root = remove(root, name, nodes);
            return nodes[0] != null ? nodes[0].data : null;
        }

        @Override
        public void displayDB() {
            displayDB(root);
        }

        private Node getNode(String name) {
            Node current = root;
            while (current != null) {
                int cmp = name.compareTo(current.data.getName());
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    return current; // match found
                }
            }
            return null; // no match found
        }



    private Node put(Node node, String name, Member member, Node[] prevValue) {
        if (node == null) {
            return new Node(member);
        }
        int cmp = name.compareTo(node.data.getName());
        if (cmp < 0) {
            node.left = put(node.left, name, member, prevValue);
        } else if (cmp > 0) {
            node.right = put(node.right, name, member, prevValue);
        } else {
            prevValue[0] = node;
            node.data = member;
        }
        return node;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);//recu
    }

    private Node remove(Node node, String name, Node[] removedNode) {
        if (node == null) {
            return null;
        }
        int cmp = name.compareTo(node.data.getName());
        if (cmp < 0) {
            node.left = remove(node.left, name, removedNode);
        } else if (cmp > 0) {
            node.right = remove(node.right, name, removedNode);
        } else {
            // Node to be removed found
            removedNode[0] = node;
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node minNode = findMin(node.right);
                node.data = minNode.data;
                node.right = removeMin(node.right);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private void displayDB(Node node) {
        if (node != null) {
            displayDB(node.left);
            System.out.println(node.data.getName() + " - " + node.data.getAffiliation());
            displayDB(node.right);
        }
    }

}




