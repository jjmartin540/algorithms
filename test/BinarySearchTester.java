import martinez.james.BinarySearch;
import org.junit.Test;

/**
 * Tests both recursive and iterative solutions
 *
 * Created by james on 4/22/16.
 */
public class BinarySearchTester {
    @Test
    public void sortOneElement() {
        float[] array = {1};
        BinarySearch b = new BinarySearch();
        assert b.closestIndex(array, 2) == 0;
        assert b.iterativeSearch(array, 2) == 0;
    }

    @Test
    public void sortTest2() {
        float[] array = {1, 2, 3};
        BinarySearch b = new BinarySearch();
        assert b.closestIndex(array, 2) == 1;
        assert b.iterativeSearch(array, 2) == 1;
    }

    @Test
    public void sortTest3() {
        float[] array = {-2, 2.3f, 4.3f, 6};
        BinarySearch b = new BinarySearch();
        assert b.closestIndex(array, 2) == 1;
        assert b.iterativeSearch(array, 2) == 1;
    }

    @Test
    public void sortTest4() {
        float[] array = {-12, -7.1f, -3, -2, 2.3f, 4.3f, 6};
        BinarySearch b = new BinarySearch();
        assert b.closestIndex(array, 2) == 4;
        assert b.iterativeSearch(array, 2) == 4;
    }

    @Test
    public void sortTest5() {
        float[] array = {-12, -7.1f, -3, -2, 2, 2.3f, 4.3f, 6};
        BinarySearch b = new BinarySearch();
        assert b.closestIndex(array, 2) == 4;
        assert b.iterativeSearch(array, 2) == 4;
    }
}
