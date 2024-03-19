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

    private void upheap(int index) {
        if (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heapArray[parentIndex] > heapArray[index]) {
                swap(parentIndex, index);
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
