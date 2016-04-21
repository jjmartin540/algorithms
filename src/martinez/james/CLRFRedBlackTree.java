package martinez.james;

import java.util.LinkedList;
import java.util.List;

import static martinez.james.MathTools.log2;

/**
 * Created by james on 4/6/16.
 */
public class CLRFRedBlackTree {
    private static final boolean BLACK = true;
    private static final boolean RED = false;
    static final CLRFNode nil = new CLRFNode(null);
    private CLRFNode root = nil;
    private int count;

    public CLRFRedBlackTree() {
        nil.right = null;
        nil.left = null;
        nil.parent = null;
        count = 0;
    }

    /**
     * Convenience method for adding a node by value
     */
    public void insert(int value) {
        insert(this, new CLRFNode(value));
    }

    /**
     * This convenience method allows the deletion of a node
     * of a tree without a reference to the node at the cost of
     * a O(log(n)) search of the tree for the node to be deleted.
     */
    public void delete(int value) {
        CLRFNode node = iterativeSearch(root, value);
        delete(this, node);
    }

    /**
     * Traditional insert method cleaned up for public consumption
     */
    public void insert(CLRFNode node) {
        insert(this, node);
    }

    /**
     * Traditional delete method cleaned up for public consumption
     */
    public void delete(CLRFNode node) {
        delete(this, node);
    }


    private void insert(CLRFRedBlackTree t, CLRFNode z) {
        CLRFNode y = nil;
        CLRFNode x = t.root;
        while (nil.compareTo(x) != 0) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (nil.compareTo(y) == 0) {
            t.root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = RED;
        fixColorForInsert(t, z);
        count++;
    }

    /**
     * 1. Every node is either red or black.
     * 2. The root is black.
     * 3. Every leaf (NIL) is black.
     * 4. If a node is red, then both its children are black.
     * 5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.
     */
    private void fixColorForInsert(CLRFRedBlackTree t, CLRFNode z) {
        while (z != null && nil.compareTo(z) != 0 && z.parent.color == RED) {
            if (z.parent.compareTo(z.parent.parent.left) == 0) {
                CLRFNode y = z.parent.parent.right;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z.compareTo(z.parent.right) == 0) {
                        z = z.parent;
                        leftRotate(t, z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(t, z.parent.parent);
                }
            } else {
                CLRFNode y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z.compareTo(z.parent.left) == 0) {
                        z = z.parent;
                        rightRotate(t, z);//?
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(t, z.parent.parent);
                }

            }
        }
        t.root.color = BLACK;
    }

    private void transplant(CLRFRedBlackTree t, CLRFNode u, CLRFNode v) {
        if (nil.compareTo(u.parent) == 0) {
            t.root = v;
        } else if (u.compareTo(u.parent.left) == 0) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void delete(CLRFRedBlackTree t, CLRFNode z) {
        CLRFNode y = z;
        boolean yOriginalColor = y.color;
        CLRFNode x;
        if (nil.compareTo(z.left) == 0) {
            x = z.right;
            transplant(t, z, z.right);
        } else if (nil.compareTo(z.right) == 0) {
            x = z.left;
            transplant(t, z, z.left);
        } else {
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (z.compareTo(y.parent) == 0) {
                x.parent = y;
            } else {
                transplant(t, y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(t, z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == BLACK) {
            fixColorForDelete(t, x);
        }
        count--;
    }

    private void fixColorForDelete(CLRFRedBlackTree t, CLRFNode x) {
        while (x != t.root && x.color == BLACK) {
            if (x.compareTo(x.parent.left) == 0) {
                CLRFNode w = x.parent.right;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(t, x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.right.color == BLACK) {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(t, w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(t, x.parent);
                    x = t.root;
                }
            } else {
                CLRFNode w = x.parent.left;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(t, x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(t, w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(t, x.parent);
                    x = t.root;
                }
            }
            x.color = BLACK;
        }
    }

    private CLRFNode treeMinimum(CLRFNode node) {
        while (nil.compareTo(node.left) != 0) {
            node = node.left;
        }
        return node;
    }

    private void leftRotate(CLRFRedBlackTree t, CLRFNode x) {
        CLRFNode y = x.right;
        x.right = y.left;
        if (nil.compareTo(y.left) != 0) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (nil.compareTo(x.parent) == 0) {
            root = y;
        } else if (x.compareTo(x.parent.left) == 0) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(CLRFRedBlackTree t, CLRFNode x) {
        CLRFNode y = x.left;
        x.left = y.right;
        if (nil.compareTo(y.right) != 0) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (nil.compareTo(x.parent) == 0) {
            t.root = y;
        } else if (x.compareTo(x.parent.right) == 0){
            x.parent.right = y;
        }else{
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public CLRFNode search(CLRFNode x, int key) {
        if (nil.compareTo(x) == 0 || x == null || key == x.key) {
            return x;
        }
        if (key < x.key) {
            return search(x.left, key);
        } else {
            return search(x.right, key);
        }
    }

    public CLRFNode iterativeSearch(CLRFNode x, int key) {
        while (x != null && nil.compareTo(x) != 0 && key != x.key) {
            if (key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    public String toString() {
        if (root.compareTo(nil) == 0) {
            return "Tree is currently empty";
        }
        int height = getHeight();
        if (height == 0) {
            return "Tree is currently empty";
        }

        if (count == 1) {
            return "The tree currently has one node whose value is: " + String.valueOf(this.root.key);
        }

        String delimiter = "\t";
        String padding, spacing;
        List<Object> oldChildren;
        List<Object> children = new LinkedList<>();
        children.add(root);
        List<String> rows = new LinkedList<>();
        while (height > 0) {
            padding = StringUtils.repeat(delimiter, Math.pow(2, height >= 1 ? height - 1 : 0));
            spacing = StringUtils.repeat(delimiter, Math.pow(2, height));
            rows.add(padding + StringUtils.join(spacing, children) + padding);
            //recompute children
            oldChildren = children;
            children = new LinkedList<>();
            CLRFNode current;
            for (Object child : oldChildren) {
                if (child instanceof CLRFNode) {
                    current = (CLRFNode) child;
                    if (current.left.compareTo(nil) != 0) {
                        children.add(current.left);
                    } else {
                        children.add("\t");
                    }
                    if (current.right.compareTo(nil) != 0) {
                        children.add(current.right);
                    } else {
                        children.add("\t");
                    }
                }
            }
            height = height - 1;
        }
        return String.join("\n", rows);
    }

    private int getHeight() {
        if (count <= 0) {
            return 0;
        }
        return 2 * log2(count + 1);
    }

}

