package martinez.james;

import java.util.List;

/**
 * Pretty much what it sounds like, I didn't feel like taking Guava or the Apache Commons libraries on
 * as depedencies for a simple repeat function
 * Created by james on 4/9/16.
 */
public class StringUtils {

    public static String repeat(String s, int n) {
        String output = "";
        while (n > 0) {
            output = output + s; //optimized to a string buffer under the hood in modern JVMs
            n = n - 1;
        }
        return output;
    }

    public static String repeat(String s, double n) {
        String output = "";
        while (n > 0) {
            output = output + s; //optimized to a string buffer under the hood in modern JVMs
            n = n - 1;
        }
        return output;
    }

    /**
     * A little more powerful than the stock join ;)
     * */
    public static String join(String delimiter, List<Object> elements) {
        boolean first = true;
        String output = "";
        for (Object element : elements) {
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
