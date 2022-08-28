package dataStructures;

import node.ListNode;

public interface LinkedList<E> {
    public void insert(E val); // At end of the list

    public void insert(E val, int pos); // At a specified position

    public <E extends Comparable<E>> boolean delete(E val); // Find and delete the element. True = success/ false = not success

    public String toString();

    public <E extends Comparable<E>> boolean contains(E val);

    public boolean swap(int pos1, int pos2); // Swap two elements if they exist and return true if it was successful

    public boolean isEmpty();

    public int getSize();

    public void clear();

    public int getCapacity();

    public ListNode<E> getHead();

    public void reverse();
}
