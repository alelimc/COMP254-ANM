import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {
    private Node<E> header = new Node((Object)null, (Node)null, (Node)null);
    private Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        this.trailer = new Node((Object)null, this.header, (Node)null);
        this.header.setNext(this.trailer);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E> node)) {
            throw new IllegalArgumentException("Invalid p");
        } else if (node.getNext() == null) {
            throw new IllegalArgumentException("p is no longer in the list");
        } else {
            return node;
        }
    }

    private Position<E> position(Node<E> node) {
        return node != this.header && node != this.trailer ? node : null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Position<E> first() {
        return this.position(this.header.getNext());
    }

    public Position<E> last() {
        return this.position(this.trailer.getPrev());
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return this.position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return this.position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        ++this.size;
        return newest;
    }

    public Position<E> addFirst(E e) {
        return this.addBetween(e, this.header, this.header.getNext());
    }

    public Position<E> addLast(E e) {
        return this.addBetween(e, this.trailer.getPrev(), this.trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return this.addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        return this.addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = this.validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        --this.size;
        E answer = node.getElement();
        node.setElement((E) null);
        node.setNext((Node)null);
        node.setPrev((Node)null);
        return answer;
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    //Method indexOf - returns the current index of the element stored at position p
    // takes a `Position<E>` object `p` as a parameter, finds the index of the given position `p` within the positional list.
    public int indexOf(Position<E> p) throws IllegalArgumentException {
        int index = 0;

        for(Iterator var3 = this.positions().iterator(); var3.hasNext(); ++index) {
            Position<E> current = (Position)var3.next();
            if (current.equals(p)) {
                return index;
            }
        }

        throw new IllegalArgumentException("Position is not in the list");
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = this.header.getNext();

        while(walk != this.trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != this.trailer) {
                sb.append(", ");
            }
        }

        sb.append(")");
        return sb.toString();
    }

    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            this.element = e;
            this.prev = p;
            this.next = n;
        }

        public E getElement() throws IllegalStateException {
            if (this.next == null) {
                throw new IllegalStateException("Position no longer valid");
            } else {
                return this.element;
            }
        }

        public Node<E> getPrev() {
            return this.prev;
        }

        public Node<E> getNext() {
            return this.next;
        }

        public void setElement(E e) {
            this.element = e;
        }

        public void setPrev(Node<E> p) {
            this.prev = p;
        }

        public void setNext(Node<E> n) {
            this.next = n;
        }
    }

    private class PositionIterable implements Iterable<Position<E>> {
        private PositionIterable() {
        }

        public Iterator<Position<E>> iterator() {
            return LinkedPositionalList.this.new PositionIterator();
        }
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = LinkedPositionalList.this.new PositionIterator();

        private ElementIterator() {
        }

        public boolean hasNext() {
            return this.posIterator.hasNext();
        }

        public E next() {
            return (E) ((Position)this.posIterator.next()).getElement();
        }

        public void remove() {
            this.posIterator.remove();
        }
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = LinkedPositionalList.this.first();
        private Position<E> recent = null;

        private PositionIterator() {
        }

        public boolean hasNext() {
            return this.cursor != null;
        }

        public Position<E> next() throws NoSuchElementException {
            if (this.cursor == null) {
                throw new NoSuchElementException("nothing left");
            } else {
                this.recent = this.cursor;
                this.cursor = LinkedPositionalList.this.after(this.cursor);
                return this.recent;
            }
        }

        public void remove() throws IllegalStateException {
            if (this.recent == null) {
                throw new IllegalStateException("nothing to remove");
            } else {
                LinkedPositionalList.this.remove(this.recent);
                this.recent = null;
            }
        }
    }
}
