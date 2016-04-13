package martinez.james;

public class Main {


    /**
     1. Every node is either red or black.
     2. The root is black.
     3. Every leaf (NIL) is black.
     4. If a node is red, then both its children are black.
     5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.
     * */
    public static void main(String[] args) {
	// write your code here
        CLRFRedBlackTree tree = new CLRFRedBlackTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(12);
        tree.insert(15);
        tree.insert(2);
        tree.insert(3);
        tree.insert(13);
        tree.insert(1);
        tree.delete(3);
        tree.delete(5);
        System.out.println(tree.toString());
    }
}
