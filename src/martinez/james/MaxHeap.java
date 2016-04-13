package martinez.james;

import java.util.ArrayList;

/**
 * Created by james on 4/12/16.
 */
public class MaxHeap {
    public ArrayList<Integer> elements;

    public MaxHeap(){
        elements = new ArrayList<>();
    }
    public int parent(int index){
        return (int)Math.floor(index);
    }
    public int left(int index){
        return 2*index;
    }
    public int right(int index){
        return 2*index + 1;
    }

    public void maxHeapify(int i){
        int left = left(i);
        int right = right(i);
        int largest;
        if(left <= elements.size() && elements.get(left) > elements.get(right)){
            largest = left;
        }else{
            largest = i;
        }
        if(right <= elements.size() && elements.get(right) > elements.get(largest)){
            largest = right;
        }
        if(largest!=i){
            int tmp = elements.get(i);
            //swap
            elements.set(i,elements.get(largest));
            elements.set(largest, tmp);
            //recursively traverse the rest
            maxHeapify(largest);
        }
    }

    public void heapSort(){

    }

    public void maxHeapInsert(){

    }

    public void heapExtractMax(){

    }

    public void heapIncreaseKey(){

    }

    public void heapMaximum(){

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
