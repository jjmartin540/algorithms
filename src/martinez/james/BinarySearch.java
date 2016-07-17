package martinez.james;

/**
 * Created by james on 4/22/16.
 */
public class BinarySearch {
    public int closestIndex(float[] array, float target) {
        int size = array.length;
        return search(array, target, 0, size);
    }

    private int search(float[] array, float target, int start, int stop) {
        int testIndex = (int) (start + Math.floor((stop - start) / 2));
        if (array[testIndex - 1] > target && testIndex > 2
                && array[testIndex - 1] < target) {
            if (Math.abs(array[testIndex - 2] - target)
                    < Math.abs(array[testIndex - 2] - target)) {
                return testIndex;
            } else {
                return testIndex - 1;
            }
        }

        if (array[testIndex - 1] == target) {
            return testIndex;
        }
        if (array[testIndex - 1] > target) {
            return search(
                    array,
                    target,
                    start,
                    testIndex
            );
        } else {
            return search(
                    array,
                    target,
                    testIndex,
                    stop
            );
        }
    }

    public int iterativeSearch(float[] array, float target) {
        int start = 0;
        int stop = array.length;
        int testIndex;
        while (true) {
            testIndex = (int) (start + Math.floor((stop - start) / 2));
            if (array[testIndex - 1] > target && testIndex > 2 && array[testIndex - 1] < target) {
                if (Math.abs(array[testIndex - 2] - target)
                        < Math.abs(array[testIndex - 2] - target)) {
                    return testIndex;
                } else {
                    return testIndex - 1;
                }
            }

            if (array[testIndex - 1] == target) {
                return testIndex;
            }

            if (array[testIndex - 1] > target) {
                start = (int) target;
                stop = testIndex;
            } else {
                start = testIndex;
            }
        }
    }
}
