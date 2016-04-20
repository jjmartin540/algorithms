package martinez.james;

/**
 * I would usually use libraries for these
 * but thought this was a good way to walk through interesting aspects of
 * integer arithmetic
 * <p>
 * Created by james on 4/9/16.
 */
public class MathTools {
    /**
     * Credit to http://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
     * <p>
     * This function has many caveats but it serves our purpose
     * <p>
     * 1) It does not support an analytic continuation to the negative integers, but we don't need that because
     * the tree can't have a negative count
     * 2) It assumes that it is running on a recent enough version of the JVM that an use intrinstics to replace the
     * call to numberOfLeadingZeros with an appropriate assembly instruction, otherwise bitshifting would be faster.
     * 3) for non power of two inputs if computes the log2 of the highest power of two less than the input.
     * <p>
     * However it has the following advantages:
     * <p>
     * 1) It avoids error due to floating point arithmetic
     * 2) it is probably faster than a divide and whatever series expansion is used in Math.log
     */
    public static int log2(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

}
