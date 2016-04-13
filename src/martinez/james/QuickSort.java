package martinez.james;

/**
 * Created by james on 4/11/16.
 */
public class QuickSort {

    public static void main(String[] args) {
        String[] testArray = {"zoo","bar", "foo"};
        sort(testArray);
        for (String element : testArray) {
            System.out.println(element);
        }
    }

    public static void sort(String[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(String[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }

    private static int partition(String[] array, int p, int r) {
        String x = array[r];
        int i = p - 1;
        String e;
        for (int j = p; j < r - 1; j++) {
            if (array[j].compareTo(x) == -1) {
                i = i + 1;
                e = array[i];
                array[i] = array[j];
                array[j] = e;
            }
        }
        e = array[i + 1];
        array[i + 1] = array[r];
        array[r] = e;
        return i + 1;
    }
}
