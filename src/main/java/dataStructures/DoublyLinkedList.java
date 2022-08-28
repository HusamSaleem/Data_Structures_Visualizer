package dataStructures;

import node.ListNode;

/**
 * @Author: Husam Saleem
 */
public class DoublyLinkedList<E> implements LinkedList<E> {
    private ListNode<E> head, tail;
    private int size, capacity;

    public DoublyLinkedList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public DoublyLinkedList(DoublyLinkedList<E> copy) {
        size = copy.size;
        capacity = copy.capacity;
        this.head = copy.head;
        this.tail = copy.tail;
    }

    /**
     * O(1) run-time
     *
     * @param val
     */
    @Override
    public void insert(E val) {
        if (this.isEmpty()) {
            this.head = new ListNode<E>(val);
            this.tail = this.head;

            this.size++;
            return;
        }

        this.tail.next = new ListNode<E>(val);
        this.tail.next.prev = this.tail;
        this.tail = this.tail.next;
        this.size++;
    }

    /**
     * O(n) run-time
     */
    @Override
    public void insert(E val, int pos) {
        if (this.isEmpty()) {
            this.head = new ListNode<E>(val);
            this.tail = this.head;

            this.size++;
            return;
        }

        if (pos < 0) {
            System.out.println("Invalid index");
            return;
        }

        if (pos == 0) {
            ListNode<E> newRoot = new ListNode<E>(val);
            newRoot.next = this.head;
            this.head = newRoot;
            this.size++;
            return;
        }

        ListNode<E> curNode = this.head;
        ListNode<E> prevNode = null;
        int curPos = 0;

        while (curNode != null) {
            if (curPos == pos)
                break;

            prevNode = curNode;
            curNode = curNode.next;

            curPos++;
        }

        if (curNode == null) {
            insert(val);
        } else {
            ListNode<E> newNode = new ListNode<E>(val);
            newNode.prev = prevNode;
            newNode.next = curNode;
            curNode.prev = newNode;
            prevNode.next = newNode;

            this.size++;
        }
    }

    /**
     * O(n) Run-time b/c of the helper function to find the node
     * Just swaps two nodes's data
     */
    @Override
    public boolean swap(int pos1, int pos2) {
        if (((pos1 < 0 || pos1 > this.size) || (pos2 < 0 || pos2 > this.size)) && !this.isEmpty()) {
            return false;
        }

        ListNode<E> node1 = getNodeAtPosition(pos1);
        ListNode<E> node2 = getNodeAtPosition(pos2);
        E temp = node1.data;

        node1.data = node2.data;
        node2.data = temp;

        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * O(n)
     * Returns the specified node at a given position
     */
    private ListNode<E> getNodeAtPosition(int pos) {
        ListNode<E> curNode = this.head;
        int i = 0;

        while (curNode != null) {
            if (i == pos)
                break;
            curNode = (ListNode<E>) curNode.next;
            i++;
        }
        return curNode;
    }

    /**
     * Finds the first value given by the parameter and returns true if the value exists
     */
    @Override
    public <E extends Comparable<E>> boolean contains(E val) {
        if (this.isEmpty()) {
            return false;
        }

        ListNode<E> curNode = (ListNode<E>) this.head;
        while (curNode != null) {
            if (curNode.data.compareTo(val) == 0) {
                return true;
            }

            curNode = (ListNode<E>) curNode.next;
        }

        return false;
    }

    /**
     * O(n) run-time
     * Deletes an element by value
     */
    @Override
    public <E extends Comparable<E>> boolean delete(E val) {
        if (!this.contains(val)) {
            System.out.println("No value of '" + val + "' found to delete");
            return false;
        }

        ListNode<E> curNode = (ListNode<E>) this.head;
        ListNode<E> prevNode = null;

        while (curNode != null) {
            if (curNode.data.compareTo(val) == 0)
                break;
            prevNode = curNode;
            curNode = (ListNode<E>) curNode.next;
        }

        // Delete head case...
        if (prevNode == null) {
            this.head = this.head.next;
        } else if (this.tail == curNode) {
            prevNode.next = null;
            this.tail = this.tail.prev;
        } else {
            curNode.next.prev = prevNode;
            prevNode.next = curNode.next;
        }
        this.size--;
        return true;
    }

    @Override
    public String toString() {
        String result = "";

        ListNode<E> curNode = this.head;
        while (curNode != null) {
            result += curNode.data + ", ";
            curNode = (ListNode<E>) curNode.next;
        }
        result += "\n";
        return result;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.tail = null;
        this.head = null;
    }

    public ListNode<E> getHead() {
        return this.head;
    }

    @Override
    public void reverse() {
        ListNode<E> prev = null;
        tail = head;

        while (head != null) {
            ListNode<E> nextNode = head.next;
            head.next = prev;
            head.prev = nextNode;

            prev = head;
            head = nextNode;
        }


        head = prev;
    }
}
