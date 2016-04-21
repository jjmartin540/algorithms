import martinez.james.HashTable;
import org.junit.Test;

/**
 * Basic Smoke tests for HashTable class
 *
 * Created by James Martinez on 4/20/16.
 */
public class HashTableTester {

    @Test
    public void makeHashTableTest(){

        HashTable h = new HashTable(2,50);
        h.setElement(684,"welcome");
        h.setElement(43, "to");
        h.setElement(35, "hash table");
        h.setElement(12, "land");
        h.setElement(342, "boo");
        System.out.println("Initial State: ");
        System.out.println(h.toString());
        h.deleteElement(43);
        System.out.println("\nAfter delete: ");
        System.out.println(h.toString());

        System.out.println("\nTesting Fetch: ");
        System.out.println("Fetching 12: "+h.getElement(12));
    }
}
