import martinez.james.CLRFRedBlackTree;
import org.junit.Test;

/**
 * Created by james on 4/9/16.
 */
public class CLRFRedBlackTreeTester {
    @Test
    public void makeTreeTest(){
        CLRFRedBlackTree t = new CLRFRedBlackTree();
        t.insert(5);
        t.insert(3);
        t.insert(13);
        t.insert(4);
        System.out.println(t.toString());
        t.delete(5);
        System.out.println(t.toString());
    }
}
