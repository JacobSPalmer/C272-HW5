
import java.sql.Array;
import java.util.*;

public class MaxHeap<E extends Comparable<E>> extends ArrayList<E>   {

    public ArrayList<E> mheap;

    // construct an empty Heap using ArrayList
    // with root at index 0.
    // don't use binary tree for implementing the heap.
    public MaxHeap(){
            this.mheap = new ArrayList<E>();
    }

    //Basic quality of life methods to make code easier to read

    private static int parent_index(int child_index){return (child_index - 1)/2;}

    private static int left_child_index(int parent_index){return 2 * parent_index + 1;}

    private static int right_child_index(int parent_index){return 2 * parent_index + 2;}

    public void swap_elements(int pos_index, int des_index){Collections.swap(this.mheap, pos_index, des_index);}

    public ArrayList getMHeap(){
        return this.mheap;
    }

    @Override
    public boolean isEmpty(){
        return this.mheap.size() == 0;
    }

    // returns max value
    public E findMax() {
        if(this.isEmpty()){
            throw new ArrayIndexOutOfBoundsException();
        }
        else{
            return this.mheap.get(0);
        }
    }

    private void heapifyUp(int i){
        int l;
        int r;
        int largest = 0;
        while(true){
            l = left_child_index(i);
            r = right_child_index(i);
            if (l <= this.mheap.size() - 1){
                if(this.mheap.get(l).compareTo(this.mheap.get(i)) > 0){
                    largest = l;
                }
                else{
                    largest = i;
                }
            }
            if (r <= this.mheap.size() -1){
                E r_val = this.mheap.get(r);
                if(this.mheap.get(r).compareTo(this.mheap.get(largest)) > 0){
                    largest = r;
                }
            }
            if(largest != i){
                int temp = parent_index(i);
                swap_elements(largest, i);
                i = temp;
                continue;
            }
            else{
                break;
            }
        }
    }

    private void heapifyDown(int i){
        int l;
        int r;
        int largest = 0;
        while(true){
            l = left_child_index(i);
            r = right_child_index(i);
            if (l <= this.mheap.size() - 1){
                if(this.mheap.get(l).compareTo(this.mheap.get(i)) > 0){
                    largest = l;
                }
                else{
                    largest = i;
                }
            }
            if (r <= this.mheap.size() -1){
                E r_val = this.mheap.get(r);
                if(this.mheap.get(r).compareTo(this.mheap.get(largest)) > 0){
                    largest = r;
                }
            }
            if(largest != i){
                swap_elements(largest, i);
                i = largest;
                continue;
            }
            else{
                break;
            }
        }
    }
    // adds a new value to the heap at the end of the Heap and 
    // adjusts values up to the root to ensure Max heap property is satisfied.
    // parent of node at i is given by the formula (i-1)/2
    // throw appropriate exception
    public void addHeap(E val) {
        if (this.isEmpty()) {
            this.mheap.add(val);
        }
        else{
            this.mheap.add(val);
            int i = parent_index(this.mheap.size() - 1);
            heapifyUp(i);
        }
    }
    //returns the max value at the root of the heap by swapping the last value 
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap() {
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        else{
            E removed = this.mheap.remove(0);
            heapifyDown(0);
            return removed;
        }
    }
    
    // takes a list of items E and builds the heap and then prints 
    // decreasing values of E with calls to removeHeap().  
    public void heapSort(List<E> list){
        MaxHeap<E> a1 = new MaxHeap<E>();
        for (E items : list){
            a1.addHeap(items);
        }
        TreePrinter.print(a1.getMHeap().toArray());
        while(a1.mheap.size() > 0){
            System.out.println(a1.removeHeap());
        }
    }
    
    // merges the other maxheap with this maxheap to produce a new maxHeap.
    public void heapMerge(MaxHeap<E> other){
        if(this.mheap.size() != 0 & other.mheap.size() != 0) {
            if (this.mheap.get(0).getClass() != other.getMHeap().get(0).getClass()) {
                throw new InputMismatchException();
            }
            while (other.mheap.size() > 0) {
                this.addHeap(other.removeHeap());
            }
        }
    }
    
    //takes a list of items E and builds the heap by calls to addHeap(..)
    public void buildHeap(List<E> list) {
        for(E item: list){
            this.addHeap(item);
        }
    }
}
