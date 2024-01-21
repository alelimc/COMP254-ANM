public class DoublyLinkedList<E> {

    //---------------- nested Node class ----------------
    /**
     * Node of a doubly linked list, which stores a reference to its
     * element and to both the previous and next node in the list.
     */
    private static class Node<E> {

        /** The element stored at this node */
        private E element;               // reference to the element stored at this node

        /** A reference to the preceding node in the list */
        private Node<E> prev;            // reference to the previous node in the list

        /** A reference to the subsequent node in the list */
        private Node<E> next;            // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param p  reference to a node that should precede the new node
         * @param n  reference to a node that should follow the new node
         */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        // public accessor methods
        /**
         * Returns the element stored at the node.
         * @return the element stored at the node
         */
        public E getElement() { return element; }

        /**
         * Returns the node that precedes this one (or null if no such node).
         * @return the preceding node
         */
        public Node<E> getPrev() { return prev; }

        /**
         * Returns the node that follows this one (or null if no such node).
         * @return the following node
         */
        public Node<E> getNext() { return next; }

        // Update methods
        /**
         * Sets the node's previous reference to point to Node n.
         * @param p    the node that should precede this one
         */
        public void setPrev(Node<E> p) { prev = p; }

        /**
         * Sets the node's next reference to point to Node n.
         * @param n    the node that should follow this one
         */
        public void setNext(Node<E> n) { next = n; }

    } //----------- end of nested Node class -----------

    // instance variables of the DoublyLinkedList
    /** Sentinel node at the beginning of the list */
    private Node<E> header;                    // header sentinel

    /** Sentinel node at the end of the list */
    private Node<E> trailer;                   // trailer sentinel

    /** Number of elements in the list (not including sentinels) */
    private int size = 0;                      // number of elements in the list

    /** Constructs a new empty list. */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);      // create header
        trailer = new Node<>(null, header, null);   // trailer is preceded by header
        header.setNext(trailer);                    // header is followed by trailer
    }

    // public accessor methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }

    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Returns (but does not remove) the first element of the list.
     * @return element at the front of the list (or null if empty)
     */
    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();   // first element is beyond header
    }

    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();    // last element is before trailer
    }

    // public update methods
    /**
     * Adds an element to the front of the list.
     * @param e   the new element to add
     */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());    // place just after the header
    }

    /**
     * Adds an element to the end of the list.
     * @param e   the new element to add
     */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
    }

    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {
        if (isEmpty()) return null;                  // nothing to remove
        return remove(header.getNext());             // first element is beyond header
    }

    /**
     * Removes and returns the last element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeLast() {
        if (isEmpty()) return null;                  // nothing to remove
        return remove(trailer.getPrev());            // last element is before trailer
    }

    // private update methods
    /**
     * Adds an element to the linked list in between the given nodes.
     * The given predecessor and successor should be neighboring each
     * other prior to the call.
     *
     * @param predecessor   node just before the location where the new element is inserted
     * @param successor     node just after the location where the new element is inserted
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     * @param node    the node to be removed (must not be a sentinel)
     */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     * This includes a check for null elements in the loop. If an element is null,
     * it will be skipped in the string representation.  After making this change, the output should not
     * include null in the concatenated list.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != null && walk != trailer) {
            if (walk.getElement() != null) {
                sb.append(walk.getElement());
                if (walk.getNext() != trailer) {
                    sb.append(", ");
                }
            }
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    // Method to concatenate two doubly linked lists
    public void concatenate(DoublyLinkedList<E> nList) {
        if (nList.size == 0) {
            return; // Nothing to concatenate if the other list is empty
        }

        // Connect the end of this list to the beginning of the other list
        //trailer.prev.next = nList.header.next;
        trailer.prev.next = nList.header.next;
        nList.header.next.prev = trailer;
        //nList.header.next = trailer;

        // Update the size of this list
        size += nList.size;

        // Clear the other list
        nList.header.next = nList.trailer;
        nList.trailer.prev = nList.header;
        nList.size = 0;
    }


    //main method
    public static void main(String[] args)
    {
        DoublyLinkedList<Integer> listL = new DoublyLinkedList<>();
        listL.addLast(1);
        listL.addLast(2);
        listL.addLast(3);

        DoublyLinkedList<Integer> listM = new DoublyLinkedList<>();
        listM.addLast(4);
        listM.addLast(5);
        listM.addLast(6);

        System.out.println("Original List L:");
        System.out.println(listL);

        System.out.println("Original List M:");
        System.out.println(listM);

        listL.concatenate(listM);

        System.out.println("Concatenated List L:");
        System.out.println(listL);

    }

} //----------- end of DoublyLinkedList class -----------
