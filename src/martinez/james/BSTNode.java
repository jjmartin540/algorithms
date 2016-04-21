package martinez.james;

/**
 * Created by James Martinez on 4/20/16.
 */
public class BSTNode<E extends Comparable> {
    BSTNode<E> right;
    BSTNode<E> left;
    BSTNode<E> parent;
    E key;

    public BSTNode(E key) {
        if (key != null) {
            this.key = key;
        }
        parent = null;
        right = null;
        left = null;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    //@Override
    //public int compareTo(Object o) {
    //    int key = 0;
    //    if (o == null) {
    //        throw new NullPointerException();
    //    }
    //
    //    if (CLRFRedBlackTree.nil.hashCode() == o.hashCode()
    //            && CLRFRedBlackTree.nil.hashCode() == this.hashCode()) {
    //        return 0;// nil is a singleton, this should work
    //    }
    //
    //    if (this.hashCode() == CLRFRedBlackTree.nil.hashCode()) {
    //        return -1;// nil is chosen to be less than everything except nil
    //    }
    //
    //    if (o.hashCode() == CLRFRedBlackTree.nil.hashCode()) {
    //        return 1;
    //    }
    //    if (o instanceof CLRFNode) {
    //        key = ((CLRFNode) o).key;
    //        if (this.key < key) {
    //            return -1;
    //        }
    //        if (this.key > key) {
    //            return 1;
    //        }
    //        if (this.key == key) {
    //            return 0;
    //        }
    //    } else {
    //        throw new ClassCastException("Type compared is not a CLRFNode");
    //    }
    //    throw new NullPointerException();
    //}
}
