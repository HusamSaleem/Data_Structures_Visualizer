package abstractClasses;

import DataStructures.BinaryTree;
import DataStructuresInterfaces.HeapInterface;

/**
 * The Heap super class
 *
 * @Author: Husam Saleem
 */
public abstract class Heap<E extends Comparable<E>> implements HeapInterface<E> {
    protected E[] heapArr;
    protected int size, capacity;

    public Heap(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.heapArr = (E[]) new Comparable[capacity];
    }

    public abstract <E extends Comparable<E>> void heapifyUp();

    public abstract <E extends Comparable<E>> void heapifyDown();

    public abstract void insert(E data);

    public abstract E extractNum();

    public final void swap(E[] heapArr, int index1, int index2) {
        E tempData = heapArr[index1];
        heapArr[index1] = heapArr[index2];
        heapArr[index2] = tempData;
    }

    @Override
    public final E peek() {
        return heapArr[0];
    }

    @Override
    public final int getSize() {
        return this.size;
    }

    @Override
    public final int getCapacity() {
        return this.capacity;
    }

    @Override
    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isParentNull(int index) {
        if (index == 0)
            return true;
        else
            return false;
    }

    public final void printHeap() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.heapArr[i] + ", ");
        }
        System.out.println();
    }

    public final BinaryTree<E> convertToTree() {
        BinaryTree<E> tree = new BinaryTree<E>(this.capacity);

        for (int i = 0; i < this.size; i++) {
            tree.insert(this.heapArr[i]);
        }

        return tree;
    }

    public final void clear() {
        this.size = 0;
        this.heapArr = (E[]) new Comparable[capacity];
    }

    public final int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public final int getRightIndex(int index) {
        return (2 * index) + 2;
    }

    public final int getLeftIndex(int index) {
        return (2 * index) + 1;
    }

    @Override
    public final E getParent(int index) {
        return this.heapArr[(index - 1) / 2];
    }

    @Override
    public final E getRightChild(int index) {
        return this.heapArr[2 * index + 2];
    }

    @Override
    public final E getLeftChild(int index) {
        return this.heapArr[2 * index + 1];
    }

    @Override
    public final boolean isRightNull(int index) {
        try {
            if (this.getRightChild(index) != null)
                return false;
            else
                return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public final boolean isLeftNull(int index) {
        try {
            if (this.getLeftChild(index) != null)
                return false;
            else
                return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
