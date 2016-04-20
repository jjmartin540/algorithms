package martinez.james;

/**
 * Created by james on 4/19/16.
 */
public class NegativeInfinity<E extends Comparable<E>> implements Comparable<E> {
    public boolean isNegativeInfinity = false;
    public E value;

    public NegativeInfinity(E input){
        value = input;
    }

    @Override
    public int compareTo(E o) {
        if(isNegativeInfinity){
            return -1;
        }
        return value.compareTo(o);
    }

    public void setNegativeInfinity(){
        isNegativeInfinity = true;
    }

    public void restoreFiniteness() {
        isNegativeInfinity = false;
    }
}
