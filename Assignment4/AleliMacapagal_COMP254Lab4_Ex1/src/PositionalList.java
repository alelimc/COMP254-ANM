import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    Position<E> first();

    Position<E> last();

    Position<E> before(Position<E> var1) throws IllegalArgumentException;

    Position<E> after(Position<E> var1) throws IllegalArgumentException;

    Position<E> addFirst(E var1);

    Position<E> addLast(E var1);

    Position<E> addBefore(Position<E> var1, E var2) throws IllegalArgumentException;

    Position<E> addAfter(Position<E> var1, E var2) throws IllegalArgumentException;

    E set(Position<E> var1, E var2) throws IllegalArgumentException;

    E remove(Position<E> var1) throws IllegalArgumentException;

    Iterator<E> iterator();

    Iterable<Position<E>> positions();

    int indexOf(Position<E> var1) throws IllegalArgumentException;
}
