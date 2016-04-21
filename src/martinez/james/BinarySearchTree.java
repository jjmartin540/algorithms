package martinez.james;

/**
 * Created by james on 4/6/16.
 */
class BinarySearchTree<E extends Comparable> {
    private BSTNode<E> root;
    private int count = 0;

    public void insert(BSTNode<E> node) {
        BSTNode y = null;
        BSTNode x = root;
        while (x != null) {
            y = x;
            if (node.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key.compareTo(y.key) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }
        node.left = null;
        node.right = null;
        count++;
    }

    public void inorderTreeWalk(BSTNode<E> x) {
        if (x != null) {
            inorderTreeWalk(x.left);
            System.out.println("Key value: " + x.key);
            inorderTreeWalk(x.right);
        }
    }

    public BSTNode<E> search(E k) {
        if (k == null) {
            return null;
        }
        return fullSearch(root, k);
    }

    private BSTNode<E> fullSearch(BSTNode<E> node, E k) {
        if (node == null || k.compareTo(node.key) == 0) {
            return node;
        }
        if (k.compareTo(node.key) < 0) {
            return fullSearch(node.left, k);
        } else {
            return fullSearch(node.right, k);
        }
    }

    private void swap(BSTNode<E> u, BSTNode<E> v){
        if(u.parent == null){
            root = v;
        }else if(u.key.compareTo(u.parent.left.key) == 0){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        if(v != null){
            v.parent = u.parent;
        }
    }

    public E delete(BSTNode<E> node) {
        if(node.left == null){
            swap(node,node.right);
        }else if(node.right == null){
            swap(node, node.left);
        }else{
            BSTNode<E> y = treeMinimum(node.right);
            if(y.parent.key.compareTo(node.key) != 0){
                swap(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            swap(node,y);
            y.left = node.left;
            y.left.parent = y;
        }
        return node.key;
    }

    private BSTNode<E> treeMinimum(BSTNode<E> node) {
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }
}
