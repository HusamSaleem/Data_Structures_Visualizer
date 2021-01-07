package abstractClasses;

import DataStructuresInterfaces.LinkedListInterface;
import Nodes.ListNode;

/**
 * Linked list super class
 *
 * @Author: Husam Saleem
 */
public abstract class LinkedList<E> implements LinkedListInterface<E> {
    protected int size, capacity;

    public LinkedList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
    }

    public LinkedList() {
    }

    @Override
    public abstract void insert(E val);

    @Override
    public abstract void insert(E val, int pos);

    @Override
    public abstract boolean swap(int pos1, int pos2);

    @Override
    public abstract <E extends Comparable<E>> boolean contains(E val);

    @Override
    public abstract <E extends Comparable<E>> boolean delete(E val);

    public abstract ListNode<E> getHead();

    public abstract void clear();

    public final int getSize() {
        return this.size;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }


}
