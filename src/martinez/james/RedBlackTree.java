package martinez.james;

/**
 * Created by james on 4/5/16.
 */
public class RedBlackTree {
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    /**
     * Returns the grandparent of a node in a binary tree
     *
     * @return the grandparent if it exists, null otherwise
     */
    private RedBlackNode grandparent(RedBlackNode n) {
        if ((n != null) && n.parent != null) {
            return n.parent.parent;
        } else {
            return null;
        }
    }

    /**
     * Returns the sibling of the parent node if it exists.
     * Because this is a binary tree though, if it exists it can be only one
     * other node besides the parent, hence the test below.
     *
     * @return the uncle if it exists, null otherwise
     */
    private RedBlackNode uncle(RedBlackNode n) {
        RedBlackNode g = grandparent(n);
        if (n == null) {
            return null; // no grandparent means no uncle
        }
        if (n.parent == g.left) {
            return g.right;
        } else {
            return g.left;
        }
    }

    /**
     * Paint the root node black, and handle the others
     */
    public void insert_case1(RedBlackNode n) {
        if (n.parent == null) { // we are at the root of the tree
            n.color = BLACK; // therefore we paint black
        } else {
            insert_case2(n); //handle other cases
        }
    }

    /**
     * If the parent is not black there may be corrections to be made
     */
    private void insert_case2(RedBlackNode n) {
        if (n.parent.color == BLACK) {
            return;
        }
        insert_case3(n);
    }

    /**
     * In this case the parent isn't the root and isn't black.
     * If the uncle is red and the parent is red,
     * we're red by default so the red/black property has to be restored.
     * <p>
     * So we make the parent and uncle black, stay red and goto case 1.
     */
    private void insert_case3(RedBlackNode n) {
        RedBlackNode u = uncle(n), g;
        if (u != null && u.color == RED) {
            n.parent.color = BLACK;
            u.color = BLACK;
            g = grandparent(n);
            g.color = RED;
            insert_case1(g);
        } else {
            insert_case4(n);
        }
    }

    private void insert_case4(RedBlackNode n) {
        RedBlackNode g = grandparent(n);
        if (n == n.parent.right && n.parent == g.left) {
            rotate_left(n.parent, g);
            n = n.left;
        } else if (n == n.parent.left && n.parent == g.right) {
            rotate_right(n.parent, g);
            n = n.right;
        }
        insert_case5(n);
    }

    private void insert_case5(RedBlackNode n) {
        RedBlackNode g = grandparent(n);
        n.parent.color = BLACK;
        g.color = RED;
        if (n == n.parent.left) {
            rotate_right(g, null);
        } else {
            rotate_left(g, null);
        }
    }

    private void rotate_left(RedBlackNode n, RedBlackNode g) {
        if (g == null) {
            g = grandparent(n);
        }
        RedBlackNode parent = g.left;
        RedBlackNode leftOfN = n.left;
        g.left = n;
        n.left = parent;
        parent.right = leftOfN;

        //fix the parent nodes?
    }

    private void rotate_right(RedBlackNode n, RedBlackNode g) {
        if (g == null) {
            g = grandparent(n);
        }
        RedBlackNode p = g.right;
        RedBlackNode r = n.right;
        g.right = n;
        n.right = p;
        p.left = r;
    }

    private RedBlackNode sibling(RedBlackNode n) {
        if (n == null || n.parent == null) {
            return null; // no parent means no sibling
        }
        if (n == n.parent.left) {
            return n.parent.right;
        } else {
            return n.parent.left;
        }
    }

    /**
     * Precondition: n has at most one non-null child.
     */
    public void delete_one_child(RedBlackNode n) {
        RedBlackNode child = is_leaf(n.right) ? n.left : n.right;

        replace_node(n, child);

        if (n.color == BLACK) {
            if (child.color == RED) {
                child.color = BLACK;
            } else {
                delete_case1(child);
            }
        }
        n = null;
        System.gc();//optional, might mess with performance
    }

    private boolean is_leaf(RedBlackNode n) {
        return n.left == null && n.right == null;
    }

    private void delete_case1(RedBlackNode n) {
        if (n.parent != null) {
            delete_case2(n);
        }
    }

    private void delete_case2(RedBlackNode n) {
        RedBlackNode s = sibling(n);

        if (s.color == RED) {
            n.parent.color = RED;
            s.color = BLACK;
            if (n == n.parent.left) {
                rotate_left(n.parent, null);
            } else {
                rotate_right(n.parent, null);
            }
        }
        delete_case3(n);
    }

    private void delete_case3(RedBlackNode n) {
        RedBlackNode s = sibling(n);
        if (n.parent.color == BLACK &&
                s.color == BLACK &&
                s.left.color == BLACK &&
                s.right.color == BLACK) {
            s.color = RED;
            delete_case1(n);
        } else {
            delete_case4(n);
        }
    }

    private void delete_case4(RedBlackNode n) {
        RedBlackNode s = sibling(n);
        if (n.parent.color == RED &&
                s.color == BLACK &&
                s.left.color == BLACK &&
                s.right.color == BLACK) {
            s.color = RED;
            n.parent.color = BLACK;
        } else {
            delete_case5(n);
        }
    }

    private void delete_case5(RedBlackNode n) {
        RedBlackNode s = sibling(n);
        if(s.color == BLACK){
            if(n == n.parent.left &&
                    s.right.color == BLACK &&
                    s.left.color == RED){
                s.color = RED;
                s.left.color = BLACK;
                rotate_right(s,null);
            }else if (n == n.parent.right &&
                    s.left.color == BLACK &&
                    s.right.color == RED){
                s.color = RED;
                s.right.color = BLACK;
                rotate_left(s,null);
            }
        }
        delete_case6(n);
    }

    private void delete_case6(RedBlackNode n) {
        RedBlackNode s = sibling(n);
        s.color = n.parent.color;
        n.parent.color = BLACK;
        if(n == n.parent.left){
            s.right.color = BLACK;
            rotate_left(n.parent, null);
        }else{
            s.left.color = BLACK;
            rotate_right(n.parent, null);
        }
    }

    private void replace_node(RedBlackNode a, RedBlackNode b) {
        RedBlackNode a_parent = a.parent;
        RedBlackNode a_right = a.right;
        RedBlackNode a_left = a.left;
        a.parent = b.parent;
        a.right = b.right;
        a.left = b.left;
        b.parent = a_parent;
        b.right = a_right;
        b.left = a_left;
    }

    class RedBlackNode extends Node {

        RedBlackNode parent;
        RedBlackNode right;
        RedBlackNode left;
        boolean color;
    }
}
