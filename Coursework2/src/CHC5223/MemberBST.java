package CHC5223;


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
        root = insert(root, member, "Root");

        return member; // Indicate a new insertion
    }


    @Override
    public Member remove(String name){         
        Node parent = null, del, p = null, q = null; 
        Member result; 
        del = root; 
        Logger.log("Remove", "Start removing", name);
        while (del != null && !del.data.getName().equals(name)) { 
            String path=null;
             path= path+" -> " + del.data.getName();
            Logger.log("Remove", "Node found", path);
             parent = del; 
         if (name.compareTo(del.data.getName()) < 0) 
             del = del.left; 
         else  
      del = del.right; 
  }// del == null || del.data.getName().equals(name)) 
     if(del != null) { // del.data.getName().equals(name) 
         if (del.right == null) p = del.left; 
         else if (del.right.left == null) { 
             p = del.right; p.left = del.left; 
         } else { 
             p = del.right; 
             while (p.left != null) {q = p; p = p.left;} 
             q.left = p.right; p.left = del.left; p.right = del.right; 
             } 
             if(del == root) root = p; 
             else if (del.data.getName().compareTo(parent.data.getName()) < 0)  
                 parent.left = p; 
             else parent.right = p; 
             Logger.log("Remove", "Node removed", name);
             result = del.data; 
         } 
         
         else {
         Logger.log("Remove", "Node not found", name);
         result = null;} 
                  
         return result; 
     } // remove 
 

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
}


