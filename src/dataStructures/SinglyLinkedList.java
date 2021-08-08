package dataStructures;

import Nodes.ListNode;
import dataStructuresInterfaces.LinkedList;

/**
 * @Author: Husam Saleem
 */
public class SinglyLinkedList<E> implements LinkedList<E> {
    private ListNode<E> head;
    private int size, capacity;

    public SinglyLinkedList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = null;
    }

    /**
     * Classic O(n) run-time implementation with no tail pointer
     */
    @Override
    public void insert(E val) {
        if (this.head == null) {
            this.head = new ListNode<E>(val);
            this.size++;
            return;
        }

        ListNode<E> curNode = (ListNode<E>) this.head;
        while (curNode.next != null) {
            curNode = (ListNode<E>) curNode.next;
        }

        curNode.next = new ListNode<E>(val);
        this.size++;
    }

    /**
     * O(n) run-time
     * Inserts a element at any given valid position
     */
    @Override
    public void insert(E val, int pos) {
        if (this.head == null) {
            this.head = new ListNode<E>(val);
            this.size++;
            return;
        }

        if (pos < 0) {
            System.out.println("Invalid index");
            return;
        }

        if (pos == 0) {
            ListNode<E> newHead = new ListNode<E>(val);
            newHead.next = this.head;
            this.head = newHead;
            this.size++;
            return;
        }

        int curPos = 0;
        ListNode<E> curNode = this.head;
        ListNode<E> prevNode = null;
        while (curNode != null) {
            if (curPos == pos)
                break;
            prevNode = curNode;
            curNode = (ListNode<E>) curNode.next;
            curPos++;
        }

        if (curNode == null) {
            prevNode.next = new ListNode<E>(val);
        } else {
            ListNode<E> newNode = new ListNode<E>(val);
            prevNode.next = newNode;
            newNode.next = curNode;
        }
        this.size++;
    }

    /**
     * O(n) run-time
     * Deletes the first instance of said element
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
            if (curNode.data.compareTo((E) val) == 0)
                break;
            prevNode = curNode;
            curNode = curNode.next;
        }

        // Delete head case...
        if (prevNode == null) {
            this.head = this.head.next;
        } else {
            prevNode.next = curNode.next;
        }
        this.size--;
        return true;
    }

    /**
     * O(n) run-time
     * Overwrites the method "toString()" from the object class.
     */
    @Override
    public String toString() {
        ListNode<E> curNode = this.head;
        String result = "";

        while (curNode != null) {
            result += curNode.data + " -> ";
            curNode = curNode.next;
        }

        result += "\n";
        return result;
    }


    /**
     * O(n) run-time as it just iterates through the list trying to find the element
     */
    @Override
    public <E extends Comparable<E>> boolean contains(E val) {
        if (this.isEmpty()) {
            return false;
        }

        ListNode<E> curNode = (ListNode<E>) this.head;
        while (curNode != null) {
            if (curNode.data.compareTo((E) val) == 0) {
                return true;
            }

            curNode = (ListNode<E>) curNode.next;
        }

        return false;
    }

    /**
     * O(n) run-time since positions are given and not the specific nodes
     * Helper method is used to find the nodes which is why this is O(n)
     * Swap nodes by passing in the position of them by simply changing their data values
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
        ListNode<E> curNode = (ListNode<E>) this.head;
        int i = 0;

        while (curNode != null) {
            if (i == pos)
                break;
            curNode = (ListNode<E>) curNode.next;
            i++;
        }
        return curNode;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public ListNode<E> getHead() {
        return (ListNode<E>) this.head;
    }

    @Override
    public void reverse() {
        ListNode<E> prev = null;

        while (head != null) {
            ListNode<E> nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }

        head = prev;
    }
}