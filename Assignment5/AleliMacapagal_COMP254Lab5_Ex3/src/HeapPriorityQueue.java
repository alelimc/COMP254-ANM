import java.util.Arrays;

public class HeapPriorityQueue {
    private int[] heapArray;
    private int size;

    public HeapPriorityQueue(int capacity) {
        heapArray = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size == heapArray.length) {
            throw new IllegalStateException("Heap is full");
        }

        heapArray[size] = value;
        size++;
        upheap(size - 1);
    }

    //computes and prints, for every position p of a tree T, the element of p followed by the height of p’s subtree
    //loop continues until either the current node reaches the root of the heap or its parent node is smaller than
    // or equal to it, ensuring that the heap property is maintained throughout the heap.
    private void upheap(int index) {
        if (index > 0) {
            int parentIndex = (index - 1) / 2;      //calculates the index of the parent node for the current node

            if (heapArray[parentIndex] > heapArray[index]) {
                swap(parentIndex, index);       //swap the parent and the current node to restore the heap property
                upheap(parentIndex);
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public void printHeap() {
        System.out.println(Arrays.toString(heapArray));
    }

    public static void main(String[] args) {
        HeapPriorityQueue heap = new HeapPriorityQueue(10);

        heap.insert(10);
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);
        heap.insert(5);

        heap.printHeap();
    }
}
