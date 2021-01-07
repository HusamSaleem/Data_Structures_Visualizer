package Nodes;

public class ListNode<E> {
    public E data;
    public ListNode<E> next, prev;

    public ListNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
