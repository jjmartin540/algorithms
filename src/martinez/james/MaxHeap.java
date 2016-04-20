package martinez.james;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Finish implementation and test it
 * Created by james on 4/12/16.
 */
public class MaxHeap<E extends NegativeInfinity> {
    private ArrayList<E> elements;
    private long heapSize;

    /**
     * Creates a MaxHeap
     * Runtime/Analysis:
     * The array copy is O(n) and buildMaxHeap is O(n log n)
     * So the runtime for heap construction is O((n log n) + n)
     * or O(n log n)
     */
    public MaxHeap(final List<E> elements) {
        this.elements = new ArrayList<>();
        //keeps references simple,
        // algorithm is still O(n)
        // if we were super memory constrained,
        // we could do the conversion in-place instead
        this.elements.addAll(elements);
        buildMaxHeap();
    }

    /**
     * Constructs a base heap from a collection
     * Runtime/Analysis: O(n log n)
     * maxHeapify is log n, and this calls it approximately n times
     * so n* log n is O(n log n). Interestingly it is also Theta(n log n)
     * and Omega(n log n)
     * <p>
     * Algorithm:
     * maxHeapify from the last element through the first
     */
    public void buildMaxHeap() {
        heapSize = elements.size();
        for (double i = Math.floor(elements.size()); i >= 1; i--) {
            maxHeapify((int) i);
        }
    }

    /**
     * Returns the parent of the current element
     *
     * @return the index of the parent element
     */
    public int parent(final int index) {
        return (int) Math.floor(index / 2.0);
    }

    /**
     * Closed linear map for finding the "left" element in the flat collection
     *
     * @return the index of the left element
     */
    public int left(final int index) {
        return 2 * index;
    }

    /**
     * Closed linear map for finding the "right" element in the flat collection
     *
     * @return the index of the right element
     */
    public int right(final int index) {
        return 2 * index + 1;
    }

    /**
     * Corrects the heap property
     * <p>
     * Runtime: O(log n)
     * <p>
     * Algorithm:
     * 1) Grab the left and right of the current element
     * 2) If the left is not the last element,
     * set the largest to the left if it is bigger than the current element
     * <p>
     * ELSE
     * <p>
     * set the largest to the current element
     * 3) the symmetric case with the right element
     * 4) if the current element wasn't the largest, swap it with the current largest
     * and call ourselves recursively keyed by the current largest
     */
    public void maxHeapify(final int i) {
        int left = left(i);
        int right = right(i);
        int largest;
        if (left <= elements.size() && elements.get(left).compareTo(elements.get(right)) > 0) {
            largest = left;
        } else {
            largest = i;
        }
        if (right <= elements.size() && elements.get(right).compareTo(elements.get(largest)) > 0) {
            largest = right;
        }
        if (largest != i) {
            E tmp = elements.get(i);
            //swap
            elements.set(i, elements.get(largest));
            elements.set(largest, tmp);
            //recursively traverse the rest
            maxHeapify(largest);
        }
    }

    /**
     * Convert the current array to a heap
     * <p>
     * From the last till the 2nd to last element
     * swap the current element with first, decrease the heapsize
     * and correct the heap property from the first element.
     */
    public void heapSort() {
        buildMaxHeap();
        for (int i = elements.size(); i >= 2; i--) {
            E tmp = elements.get(i);
            elements.set(i, elements.get(1));
            elements.set(1, tmp);
            heapSize = heapSize - 1;
            maxHeapify(1);
        }
    }

    /**
     * Returns the current maximum without removing it
     * <p>
     * Runtime: O(1)
     * Algorithm:
     * As this is a MaxHeap, the first element will always be the maximum
     *
     * @return the maximum valued element of the heap
     */
    public E heapMaximum() {
        return elements.get(1);
    }

    /**
     * Extracts the current maximum element, removing it from the heap.
     * <p>
     * Runtime: O(log n)
     * <p>
     * Algorithm:
     * If the heap has any elements:
     * 1) grab the first element (the current max of the heap)
     * 2) move the last element in the collection range that is allocated as heap
     * to the first element (removing the current max from the heap)
     * 3) re-heapify starting at the first element
     * 4) return the extracted max
     *
     * @return the maximum value of the heap before calling this function
     */
    public E heapExtractMax() {
        if (heapSize < 1) {
            throw new ArrayIndexOutOfBoundsException("Heap Underflow");
        }
        E max = elements.get(1);
        elements.set(1, elements.get((int) heapSize));
        heapSize = heapSize - 1;
        maxHeapify(1);
        return max;
    }

    public void heapIncreaseKey(int i, E key) throws Exception {
        if (key.compareTo(elements.get(i)) < 0) {
            throw new Exception("new key is smaller than current key");
        }
        elements.set(i, key);
        while (i > 1 && elements.get(parent(i)).compareTo(elements.get(i)) < 0) {
            E tmp = elements.get(i);
            elements.set(i, elements.get(parent(i)));
            elements.set(parent(i), tmp);
            i = parent(i);
        }
    }


    public void maxHeapInsert(E key) {
        heapSize = heapSize + 1;
        //find some way for this to return negative infinity
        key.setNegativeInfinity();
        elements.set((int) heapSize, key);

        try {
            heapIncreaseKey((int) heapSize, key);
            key.restoreFiniteness();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Exercises
     *
     * 6.1.1
     *  What are the minimum and maximum numbers of elements in a heap of height h?
     *      max 2^h
     *      min 2^(h-1) + 1
     * 6.1.2
     *  Show that an n-element heap has height floor(lg n).
     *      A node has either a higher value or a lower value
     *      but no more.
     *      this means that a parent can have not
     * */

}
