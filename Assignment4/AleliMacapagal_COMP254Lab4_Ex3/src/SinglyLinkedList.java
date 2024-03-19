
class SinglyLinkedList<E> {
    public Node<E> tail;
    public Node<E> head = null;
    private int size = 0;

    public SinglyLinkedList() {
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E first() {
        return this.isEmpty() ? null : this.head.getElement();
    }

    public E last() {
        return this.isEmpty() ? null : this.tail.getElement();
    }

    public void addFirst(E e) {
        this.head = new Node(e, this.head);
        if (this.size == 0) {
            this.tail = this.head;
        }

        ++this.size;
    }

    public void addLast(E e) {
        Node<E> newest = new Node(e, (Node)null);
        if (this.isEmpty()) {
            this.head = newest;
        } else {
            this.tail.setNext(newest);
        }

        this.tail = newest;
        ++this.size;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            E answer = this.head.getElement();
            this.head = this.head.getNext();
            --this.size;
            if (this.size == 0) {
                this.tail = null;
            }

            return answer;
        }
    }

    static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        public E getElement() {
            return this.element;
        }

        public Node<E> getNext() {
            return this.next;
        }

        public void setNext(Node<E> n) {
            this.next = n;
        }

        public void setNext(SinglyLinkedList singlyLinkedList) {
        }
    }
}
