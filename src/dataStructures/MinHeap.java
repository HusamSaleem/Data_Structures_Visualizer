package dataStructures;

import abstractClasses.Heap;

/**
 * @Author: Husam Saleem
 */
public class MinHeap<E extends Comparable<E>> extends Heap<E> {
    public MinHeap(int capacity) {
        super(capacity);
    }

    @Override
    public void insert(E data) {
        this.heapArr[this.size] = data;
        this.size++;
        heapifyUp();
    }

    @Override
    public E extractNum() {
        if (this.isEmpty())
            return null;

        E dataExtracted = this.heapArr[0];
        swap(this.heapArr, 0, this.size - 1);
        this.heapArr[this.size - 1] = null;
        this.size--;
        heapifyDown();

        return dataExtracted;
    }

    @Override
    public void heapifyUp() {
        int index = this.size - 1;
        E lastElem = this.heapArr[this.size - 1];

        while (!isParentNull(index)) {
            E parent = getParent(index);

            if (parent.compareTo(lastElem) > 0) {
                swap(this.heapArr, index, getParentIndex(index));
                index = getParentIndex(index);
            } else {
                break;
            }
        }
    }

    @Override
    public void heapifyDown() {
        int index = 0;
        while (!isLeftNull(index)) {
            E data = this.heapArr[index];
            boolean swapRight = false, swapLeft = false;

            if (getLeftChild(index).compareTo(data) < 0) {
                swapLeft = true;
            }

            if (!isRightNull(index) && getRightChild(index).compareTo(data) < 0) {
                swapRight = true;
            }

            if (swapLeft && swapRight) {
                if (getRightChild(index).compareTo(getLeftChild(index)) > 0) {
                    swapRight = false;
                } else {
                    swapLeft = false;
                }
            }

            if (swapLeft) {
                swap(this.heapArr, getLeftIndex(index), index);
                index = getLeftIndex(index);
            } else if (swapRight) {
                swap(this.heapArr, getRightIndex(index), index);
                index = getRightIndex(index);
            } else {
                break;
            }
        }
    }
}
