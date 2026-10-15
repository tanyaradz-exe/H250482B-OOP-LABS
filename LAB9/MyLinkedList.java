import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    // STATIC nested class: a Node needs no reference to the list that owns it
    private static class Node<T> {
        final T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) head = tail = node;
        else { tail.next = node; tail = node; }
        size++;
    }

    public int size() { return size; }

    // INNER (non-static) class: it reads this list's 'head' field directly
    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override public boolean hasNext() { return current != null; }

        @Override public T next() {
            if (current == null) throw new NoSuchElementException();
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    // Implementing Iterable makes the for-each loop work
    @Override
    public Iterator<T> iterator() { return new ListIterator(); }
}
