package martinez.james;

import java.util.List;

/**
 * Created by admin on 4/20/16.
 */
public class GenericStringUtils<E> {
    /**
     * A little more powerful than the stock join ;)
     * */
    public String join(String delimiter, List<E> elements) {
        boolean first = true;
        String output = "";
        for (E element : elements) {
            if (!first) {
                output = output + delimiter;
            } else {
                first = false;
            }
            output = output + element.toString();
        }
        return output;
    }
}
