package martinez.james;

/**
 * Created by james on 4/9/16.
 */
class CLRFNode implements Comparable {
    private static final boolean BLACK = true;
    CLRFNode right;
    CLRFNode left;
    CLRFNode parent;
    boolean color;
    int key;

    public CLRFNode(Integer key) {
        if (key != null) {
            this.key = key;
        }
        color = BLACK;
        parent = CLRFRedBlackTree.nil;
        right = CLRFRedBlackTree.nil;
        left = CLRFRedBlackTree.nil;
    }

    @Override
    public String toString() {
        return String.valueOf(key) + (color?" (black)":" (red)");
    }

    @Override
    public int compareTo(Object o) {
        int key = 0;
        if (o == null) {
            throw new NullPointerException();
        }

        if (CLRFRedBlackTree.nil.hashCode() == o.hashCode()
                && CLRFRedBlackTree.nil.hashCode() == this.hashCode()) {
            return 0;// nil is a singleton, this should work
        }

        if (this.hashCode() == CLRFRedBlackTree.nil.hashCode()) {
            return -1;// nil is chosen to be less than everything except nil
        }

        if (o.hashCode() == CLRFRedBlackTree.nil.hashCode()) {
            return 1;
        }
        if (o instanceof CLRFNode) {
            key = ((CLRFNode) o).key;
            if (this.key < key) {
                return -1;
            }
            if (this.key > key) {
                return 1;
            }
            if (this.key == key) {
                return 0;
            }
        } else {
            throw new ClassCastException("Type compared is not a CLRFNode");
        }
        throw new NullPointerException();
    }
}