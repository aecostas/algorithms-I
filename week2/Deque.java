import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    

    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        //public void remove() {
        //    throw UnsupportedOperationException();
        //}

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<Item> iterator() { return new ListIterator(); }

    public boolean isEmpty() { 
        return this.first == null; 
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        Node newfirst = new Node();

        checkNullArgument(item);

        newfirst.item = item;
        newfirst.next = this.first;

        this.first = newfirst;

        this.size++;
    }

    public void addLast(Item item) {
        Node newlast = new Node();

        checkNullArgument(item);

        newlast.item = item;
        newlast.next = null;

        if (this.last != null) {
            this.last.next = newlast;
            this.last = newlast;
        } else {
            this.first = newlast;
            this.last = newlast;
        }

        this.size++;
    }

    public Item removeFirst() {
        Node temp = this.first;

        checkEmptyList();

        this.first = this.first.next;

        this.size--;
        return temp.item;

    }

    public Item removeLast() {
        Node temp;
        Item retValue;

        checkEmptyList();

        if (this.first == this.last) {
            retValue = this.first.item;

            this.size--;

        } else {
            temp = first;
            while (temp != null && temp.next != null && temp.next.next != null) {
                temp = temp.next;
            }

            this.last = temp;

            temp = this.last.next;
            retValue = temp.item;
            this.last.next = null;
            this.size--;
        }

        return retValue;
    }



    private void checkEmptyList() {
        if (this.first == null) {
            throw new java.util.NoSuchElementException();
        }
    }

    private void checkNullArgument(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        Deque d = new Deque();

        // d.removeFirst();

        d.addLast(1);
        d.addLast(2);
        d.addLast(3);

        System.out.println(d.removeLast());

        d.addFirst(4);

        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());

    }

}