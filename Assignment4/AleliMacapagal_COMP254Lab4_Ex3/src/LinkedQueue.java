
import java.util.NoSuchElementException;

public class LinkedQueue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList();

    public LinkedQueue() {
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void enqueue(E element) {
        this.list.addLast(element);
    }

    public E dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        } else {
            return this.list.removeFirst();
        }
    }

    //Concatenates the contents of another queue `Q2` to the current queue by linking the tail of
    // the current queue to the head of `Q2`. It sets the tail of the current queue to be the tail
    // of `Q2`, effectively merging the two queues.
    public void concatenate(LinkedQueue<E> Q2) {
        if (!Q2.isEmpty()) {
            this.list.tail.setNext(Q2.list.head.getNext());
            this.list.tail = Q2.list.tail;
            Q2.list.head.setNext((SinglyLinkedList)null);
            Q2.list.tail = null;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> queue1 = new LinkedQueue();
        LinkedQueue<Integer> queue2 = new LinkedQueue();
        queue1.enqueue(10);
        queue1.enqueue(20);
        queue1.enqueue(30);

        queue2.enqueue(40);
        queue2.enqueue(50);

        queue1.concatenate(queue2);

        while(!queue1.isEmpty()) {
            System.out.println(queue1.dequeue());
        }

    }
}
