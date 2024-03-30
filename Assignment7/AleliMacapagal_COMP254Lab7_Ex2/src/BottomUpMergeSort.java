import java.util.LinkedList;
import java.util.Queue;

public class BottomUpMergeSort<T extends Comparable<T>> {

    public Queue<T> bottomUpMergeSort(Queue<T> input) {
        Queue<Queue<T>> queueOfQueues = new LinkedList<>();

        // Place each item in its own queue
        for (T item : input) {
            Queue<T> q = new LinkedList<>();
            q.add(item);
            queueOfQueues.add(q);
        }

        // Merge pairs of queues until there is only one queue left
        while (queueOfQueues.size() > 1) {
            Queue<T> q1 = queueOfQueues.poll();
            Queue<T> q2 = queueOfQueues.poll();
            Queue<T> mergedQueue = mergeQueues(q1, q2);
            queueOfQueues.add(mergedQueue);
        }

        // Return the single sorted queue
        return queueOfQueues.poll();
    }

    // Merge two sorted queues into one sorted queue
    private Queue<T> mergeQueues(Queue<T> q1, Queue<T> q2) {
        Queue<T> result = new LinkedList<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek().compareTo(q2.peek()) <= 0) {
                result.add(q1.poll());
            } else {
                result.add(q2.poll());
            }
        }
        // Add remaining elements from q1 or q2
        result.addAll(q1);
        result.addAll(q2);
        return result;
    }

    public static void main(String[] args) {
        BottomUpMergeSort<Integer> mergeSort = new BottomUpMergeSort<>();

        // Example usage
        Queue<Integer> input = new LinkedList<>();
        input.add(15);
        input.add(3);
        input.add(8);
        input.add(12);
        input.add(11);
        input.add(7);
        input.add(14);

        System.out.println("Original Queue: " + input);
        Queue<Integer> sortedQueue = mergeSort.bottomUpMergeSort(input);
        System.out.println("Sorted Queue: " + sortedQueue);
    }
}
