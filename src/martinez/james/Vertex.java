package martinez.james;

/**
 * Created by James Martinez on 4/20/16.
 */
public class Vertex<E> {
    public static final boolean OUT = true;
    public static final boolean IN = false;
    public static final short WHITE = 0;
    public static final short GRAY = 1;
    public static final short BLACK = 2;

    public E value;
    public int index;
    public boolean direction;
    public short color;
}
